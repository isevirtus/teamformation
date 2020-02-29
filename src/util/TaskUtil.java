package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

import org.apache.commons.lang3.SerializationUtils;

import project.Tag;
import project.Task;

public class TaskUtil {

	public static int count(ArrayList<Task> taskList, String personalizedDescription) {
		int count = 0;
		for (int i = 0; i < taskList.size(); i++) {
			if (taskList.get(i).getPersonalizedDescription().equalsIgnoreCase(personalizedDescription)) {
				count++;
			}
		}

		return count;
	}

	public static int countTagKey(ArrayList<Task> taskList, String tagKey) {
		int count = 0;
		for (int i = 0; i < taskList.size(); i++) {
			ArrayList<Tag> tagList = taskList.get(i).getTagList();
			if (TagUtil.containsTag(tagList, tagKey)) {
				count++;
			}
		}

		return count;
	}
	
	public static int countTaskPersonalizedDescriptionOfDeveloper(ArrayList<Task> taskList, String description, String developerName) {
		int count = 0;
		for (int i = 0; i < taskList.size(); i++) {
			Task task = taskList.get(i);
			if (task.getDeveloperName() != null) {
				if (task.getDeveloperName().equalsIgnoreCase(developerName)) {
					if (task.getPersonalizedDescription().equalsIgnoreCase(description)) {
						count++;
					}
				}
			}
		}

		return count;
	}

	public static int countTagOfDeveloper(ArrayList<Task> taskList, String tagKey, String developerName) {
		int count = 0;
		for (int i = 0; i < taskList.size(); i++) {
			Task task = taskList.get(i);
			if (task.getDeveloperName() != null) {
				if (task.getDeveloperName().equalsIgnoreCase(developerName)) {
					if (TagUtil.containsTag(task.getTagList(), tagKey)) {
						count++;
					}
				}
			}
		}

		return count;
	}

	public static ArrayList<Tag> getTags(ArrayList<Task> taskList) {
		ArrayList<Tag> tagList = new ArrayList<Tag>();
		for (int i = 0; i < taskList.size(); i++) {
			tagList.addAll(getTags(taskList.get(i)));
		}
		// tagList = TagUtil.getUniqueTags(tagList);

		return tagList;
	}

	public static ArrayList<Tag> getTags(Task task) {
		ArrayList<Tag> tagList = new ArrayList<Tag>();
		for (int i = 0; i < task.getTagList().size(); i++) {
			Tag tag = task.getTagList().get(i);
			tagList.add(tag);
		}
		// tagList = TagUtil.getUniqueTags(tagList);

		return tagList;
	}

	public static Task getTask(ArrayList<Task> taskList, String taskId) {
		for (int i = 0; i < taskList.size(); i++) {
			if (taskList.get(i).getId().equalsIgnoreCase(taskId)) {
				return taskList.get(i);
			}
		}
		return null;
	}

	public static double getTaskEffortAverage(ArrayList<Task> taskList, String personalizedDescription) {
		int sum = 0;
		int count = 0;
		for (int i = 0; i < taskList.size(); i++) {
			Task task = taskList.get(i);
			if (task.getPersonalizedDescription().equalsIgnoreCase(personalizedDescription)
					&& task.getRealEffort() != 0) {
				sum += task.getRealEffort();
				count++;
			}
		}
		double average = (double) sum / count;

		return average;
	}

	public static ArrayList<String> getTaskIds(ArrayList<Task> taskList) {
		ArrayList<String> idList = new ArrayList<String>();
		for (int i = 0; i < taskList.size(); i++) {
			idList.add(taskList.get(i).getId());
		}
		return idList;

	}

	public static ArrayList<Task> getTasks(ArrayList<Task> taskList, String developerName) {
		ArrayList<Task> developerTaskList = new ArrayList<Task>();
		for (int i = 0; i < taskList.size(); i++) {
			if (taskList.get(i).getDeveloperName() != null) {
				if (taskList.get(i).getDeveloperName().equalsIgnoreCase(developerName)) {
					developerTaskList.add(taskList.get(i));
				}
			}
		}

		return developerTaskList;
	}

	public static ArrayList<Task> getTasksBypersonalizedDescription(ArrayList<Task> taskList,
			String personalizedDescription) {
		ArrayList<Task> taskPersonalizedDescriptionList = new ArrayList<Task>();
		for (int i = 0; i < taskList.size(); i++) {
			if (taskList.get(i).getPersonalizedDescription().equalsIgnoreCase(personalizedDescription)) {
				taskPersonalizedDescriptionList.add(taskList.get(i));
			}
		}

		return taskPersonalizedDescriptionList;
	}
	
	public static ArrayList<String> getTasksPersonalizedDescriptionsWithDuplicates(ArrayList<Task> taskList) {
		ArrayList<String> taskPersonalizedDescriptionList = new ArrayList<String>();
		for (int i = 0; i < taskList.size(); i++) {
			taskPersonalizedDescriptionList.add(taskList.get(i).getPersonalizedDescription());
		}

		return taskPersonalizedDescriptionList;
	}

	public static ArrayList<String> getTasksPersonalizedDescriptions(ArrayList<Task> taskList) {
		ArrayList<String> taskPersonalizedDescriptionList = new ArrayList<String>();
		for (int i = 0; i < taskList.size(); i++) {
			taskPersonalizedDescriptionList.add(taskList.get(i).getPersonalizedDescription());
		}

		taskPersonalizedDescriptionList = (ArrayList<String>) taskPersonalizedDescriptionList.stream().distinct()
				.collect(Collectors.toList());

		return taskPersonalizedDescriptionList;
	}

	public static ArrayList<Task> removeTaskWithoutTags(ArrayList<Task> taskList) {

		int index = 0;
		while (index < taskList.size()) {
			if (taskList.get(index).getTagList().size() == 0) {
				taskList.remove(index);
				index = 0;
			} else {
				index++;
			}
		}

		return taskList;
	}

	public static String toString(ArrayList<Task> taskList) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < taskList.size(); i++) {
			builder.append(taskList.get(i).toString());
			builder.append(System.lineSeparator());
		}

		return builder.toString();
	}

	public static String toString(HashMap<String, Double> taskPersonalizedDescriptionMap) {
		StringBuilder builder = new StringBuilder();
		for (HashMap.Entry<String, Double> entry : taskPersonalizedDescriptionMap.entrySet()) {
			builder.append("[" + entry.getKey() + " = " + entry.getValue() + "]");
		}

		return builder.toString();
	}

}
