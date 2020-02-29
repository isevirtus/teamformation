package util;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.lang3.SerializationUtils;

import developer.Developer;
import developer.DevelopmentHistory;
import project.Project;
import project.Tag;
import project.Task;
import vector.Normalization;

public class DevelopmentHistoryUtil {

	public static void buildDevelopmentHistory(ArrayList<Developer> developerList, ArrayList<Project> projectList) {

		for (int i = 0; i < developerList.size(); i++) {
			buildDevelopmentHistoryTaskList(developerList.get(i), projectList);
			buildDevelopmentHistoryTagList(developerList.get(i), projectList);
			buildTaskPersonalizedDescriptionMap(developerList.get(i));
		}
		buildNormalizedDevelopmentHistoryTagList(developerList);
		buildNormalizedPersonalizedDescriptionMap(developerList);
	}

	private static void buildDevelopmentHistoryTagList(Developer developer, ArrayList<Project> projectList) {
		ArrayList<Task> taskList = ProjectUtil.getTasks(projectList);
		taskList = TaskUtil.getTasks(taskList, developer.getName());
		ArrayList<Tag> taskTagList = TaskUtil.getTags(taskList);

		ArrayList<Tag> developmentHistoryTagList = new ArrayList<>();

		for (int i = 0; i < taskTagList.size(); i++) {
			Tag tag = taskTagList.get(i);

			if (!TagUtil.containsTag(developmentHistoryTagList, tag.getKey())) {
				Tag newtag = new Tag(tag.getKey(), DevelopmentHistory.MININUM_TAG_VALUE);
				developmentHistoryTagList.add(newtag);
			}
			int tagIndex = TagUtil.getIndexOf(developmentHistoryTagList, tag.getKey());
			double currentValue = developmentHistoryTagList.get(tagIndex).getValue();
			developmentHistoryTagList.get(tagIndex).setValue(currentValue + 1);

		}
		developer.getDeveloperProfile().getDevelopmentHistory().setDevelopmentHistoryTagList(developmentHistoryTagList);

	}

	private static void buildDevelopmentHistoryTaskList(Developer developer, ArrayList<Project> projectList) {
		ArrayList<Task> projectTaskList = ProjectUtil.getTasks(projectList);
		ArrayList<Task> developerTaskList = TaskUtil.getTasks(projectTaskList, developer.getName());
		developer.getDeveloperProfile().getDevelopmentHistory()
				.setTaskList(SerializationUtils.clone(developerTaskList));
	}

	public static void buildNormalizedDevelopmentHistoryTagList(ArrayList<Developer> developerList) {
		HashMap<String, Double> maxValueMapping = createTagMaxValueMapping(developerList);

		for (int i = 0; i < developerList.size(); i++) {
			ArrayList<Tag> developmentHistoryTagList = developerList.get(i).getDeveloperProfile()
					.getDevelopmentHistory().getDevelopmentHistoryTagList();
			ArrayList<Tag> normalizedDevelopmentHistoryTagList = SerializationUtils.clone(developmentHistoryTagList);
			for (int j = 0; j < normalizedDevelopmentHistoryTagList.size(); j++) {
				Tag tag = normalizedDevelopmentHistoryTagList.get(j);
				double minValue = DevelopmentHistory.MININUM_TAG_VALUE;
				double maxValue = maxValueMapping.get(tag.getKey());
				double currentValue = tag.getValue();
				double normalizedValue = Normalization.calcNormalizedValue(minValue, maxValue, currentValue);
				normalizedDevelopmentHistoryTagList.get(j).setValue(normalizedValue);
			}
			developerList.get(i).getDeveloperProfile().getDevelopmentHistory()
					.setNormalizedDevelopmentHistoryTagList(normalizedDevelopmentHistoryTagList);
		}
	}

	public static void buildNormalizedPersonalizedDescriptionMap(ArrayList<Developer> developerList) {
		HashMap<String, Integer> maxValueMapping = createPersonalizedDescriptionMaxValueMapping(developerList);
		for (int i = 0; i < developerList.size(); i++) {
			Developer developer = developerList.get(i);
			HashMap<String, Double> taskPersonalizedDescriptionMap = developer.getDeveloperProfile().getDevelopmentHistory()
					.getTaskPersonalizedDescriptionMap();

			HashMap<String, Double> normalizedTaskMappedDescriptionMap = new HashMap<String, Double>();
			for (HashMap.Entry<String, Double> entry : taskPersonalizedDescriptionMap.entrySet()) {
				double minValue = DevelopmentHistory.MININUM_MAPPED_DESCRIPTION_COUNT;
				double maxValue = maxValueMapping.get(entry.getKey());
				double currentValue = entry.getValue();
				double normalizedValue = Normalization.calcNormalizedValue(minValue, maxValue, currentValue);
				normalizedTaskMappedDescriptionMap.put(entry.getKey(), normalizedValue);
			}
			developer.getDeveloperProfile().getDevelopmentHistory()
					.setNormalizedTaskPersonalizedDescriptionMap(normalizedTaskMappedDescriptionMap);
		}
	}

	public static void buildTaskPersonalizedDescriptionMap(Developer developer) {
		ArrayList<Task> developerTaskList = developer.getDeveloperProfile().getDevelopmentHistory().getTaskList();
		ArrayList<String> taskPersonalizedDescriptionList = TaskUtil.getTasksPersonalizedDescriptions(developerTaskList);
		for (int i = 0; i < taskPersonalizedDescriptionList.size(); i++) {
			String personalizedDescription = taskPersonalizedDescriptionList.get(i);
			double count = TaskUtil.count(developerTaskList, personalizedDescription);
			developer.getDeveloperProfile().getDevelopmentHistory().getTaskPersonalizedDescriptionMap().put(personalizedDescription,
					count);
		}
	}

	public static HashMap<String, Integer> createPersonalizedDescriptionMaxValueMapping(ArrayList<Developer> developerList) {
		HashMap<String, Integer> maxValueMapping = new HashMap<String, Integer>();
		ArrayList<Task> taskList = getDevelopmentHistoryTaskList(developerList);
		ArrayList<String> personalizedDescriptionList = TaskUtil.getTasksPersonalizedDescriptions(taskList);

		for (int i = 0; i < personalizedDescriptionList.size(); i++) {
			String personalizedDescription = personalizedDescriptionList.get(i);
			for (int j = 0; j < developerList.size(); j++) {
				ArrayList<Task> developmentHistoryTaskList = developerList.get(j).getDeveloperProfile()
						.getDevelopmentHistory().getTaskList();
				int count = TaskUtil.count(developmentHistoryTaskList, personalizedDescription);
				if (!maxValueMapping.containsKey(personalizedDescription)) {
					maxValueMapping.put(personalizedDescription, count);
				} else {
					double maxValue = maxValueMapping.get(personalizedDescription);
					if (count > maxValue) {
						maxValueMapping.put(personalizedDescription, count);
					}
				}
			}
		}

		return maxValueMapping;
	}

	public static HashMap<String, Double> createTagMaxValueMapping(ArrayList<Developer> developerList) {
		HashMap<String, Double> maxValueMapping = new HashMap<String, Double>();
		ArrayList<Tag> tagList = getDevelopmentHistoryTags(developerList);
		for (int i = 0; i < tagList.size(); i++) {
			String tagKey = tagList.get(i).getKey();
			double tagValue = tagList.get(i).getValue();
			if (!maxValueMapping.containsKey(tagKey)) {
				maxValueMapping.put(tagKey, tagValue);
			} else {
				double maxTagValue = maxValueMapping.get(tagKey);
				if (tagValue > maxTagValue) {
					maxValueMapping.put(tagKey, tagValue);
				}
			}
		}

		return maxValueMapping;
	}

//	public static ArrayList<String> getDevelopmentHistoryTagKeys(ArrayList<Developer> developerList) {
//		ArrayList<Tag> tagList = getDevelopmentHistoryTags(developerList);
//		tagList = TagUtil.getUniqueTags(tagList);
//		ArrayList<String> tagKeyList = TagUtil.getUniqueTagKeys(tagList);
//
//		return tagKeyList;
//	}

	public static ArrayList<Tag> getDevelopmentHistoryTags(ArrayList<Developer> developerList) {
		ArrayList<Tag> tagList = new ArrayList<>();
		for (int i = 0; i < developerList.size(); i++) {
			Developer developer = developerList.get(i);
			tagList.addAll(developer.getDeveloperProfile().getDevelopmentHistory().getDevelopmentHistoryTagList());
		}

		return tagList;
	}

	public static ArrayList<Task> getDevelopmentHistoryTaskList(ArrayList<Developer> developerList) {
		ArrayList<Task> taskList = new ArrayList<Task>();
		for (int i = 0; i < developerList.size(); i++) {
			taskList.addAll(developerList.get(i).getDeveloperProfile().getDevelopmentHistory().getTaskList());
		}
		return taskList;

	}
}
