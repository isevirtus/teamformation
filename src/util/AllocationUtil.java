package util;

import java.util.ArrayList;
import java.util.HashMap;

import developer.Developer;
import project.Project;
import project.Tag;
import project.TargetInfo;
import project.Task;
import similarity.AllocationInfo;
import similarity.SimilarityMetric;
import similarity.TFIDF;
import vector.Vector;
import vector.VectorType;

public class AllocationUtil {

	public static void buildComplexVectors(ArrayList<AllocationInfo> allocationInfoList, TFIDF tfidf) {

		for (int i = 0; i < allocationInfoList.size(); i++) {
			AllocationInfo allocationInfo = allocationInfoList.get(i);

			Developer developer = allocationInfo.getDeveloper();
			Task task = allocationInfo.getTask();

			ArrayList<Tag> taskTagList = TaskUtil.getTags(task);
			ArrayList<Tag> normalizedDevelopmentHistoryTagList = developer.getDeveloperProfile().getDevelopmentHistory()
					.getNormalizedDevelopmentHistoryTagList();

			HashMap<String, Double> weithedTagMap = allocationInfo.getTargetInfo().getWeithedTagMap();
			Vector taskVector = new Vector(task, VectorType.TASK);
			taskVector.fillVector(taskTagList, tfidf, weithedTagMap);
			// Vector curriculumVector = new Vector(, VectorType.CURRICULUM,
			// tfidf);
			Vector developmentHistoryVector = new Vector(task, VectorType.DEVELOPMENT_HISTORY);
			developmentHistoryVector.fillVector(normalizedDevelopmentHistoryTagList, tfidf, weithedTagMap);
			allocationInfoList.get(i).getComplexVector().setTaskVector(taskVector);
			// allocationInfoList.get(i).getComplexVector().setCurriculumVector(curriculumVector);
			allocationInfoList.get(i).getComplexVector().setDevelopmentHistoryVector(developmentHistoryVector);

		}
	}

	public static void buildVectorSimilarities(ArrayList<AllocationInfo> allocationInfoList,
			SimilarityMetric similarityMetric) {

		for (int i = 0; i < allocationInfoList.size(); i++) {
			AllocationInfo allocationInfo = allocationInfoList.get(i);
			HashMap<String, Double> weithedTagMap = allocationInfo.getTargetInfo().getWeithedTagMap();
			Vector taskVector = allocationInfo.getComplexVector().getTaskVector();
			// Vector curriculumVector =
			// allocationInfo.getComplexVector().getCurriculumVector()
			Vector developmentHistoryVector = allocationInfo.getComplexVector().getDevelopmentHistoryVector();

			// double curriculumSimilarity =
			// similarityMetric.computeSimililarity(taskVector,
			// curriculumVector);

			double developmentHistorySimilarity = similarityMetric.computeSimililarity(taskVector,
					developmentHistoryVector, weithedTagMap);

			// allocationInfo.getComplexVector().setCurriculumSimilarity(curriculumSimilarity);
			allocationInfo.getComplexVector().setDevelopmentHistorySimilarity(developmentHistorySimilarity);

		}
	}

	public static void buildTaskStatistics(ArrayList<AllocationInfo> allocationInfoList, ArrayList<Task> taskList) {

		for (int i = 0; i < allocationInfoList.size(); i++) {
			AllocationInfo allocationInfo = allocationInfoList.get(i);

			Developer developer = allocationInfo.getDeveloper();
			Task task = allocationInfo.getTask();
			String personalizedDescription = task.getPersonalizedDescription();
			double taskEffortAverage = TaskUtil.getTaskEffortAverage(taskList, personalizedDescription);

			HashMap<String, Double> taskPersonalizedDescriptionMap = developer.getDeveloperProfile()
					.getDevelopmentHistory().getTaskPersonalizedDescriptionMap();
			double numberOfTasksDeveloped = 0;
			if (taskPersonalizedDescriptionMap.containsKey(personalizedDescription)) {
				numberOfTasksDeveloped = taskPersonalizedDescriptionMap.get(personalizedDescription);
			}

			HashMap<String, Double> normalizedTaskMappedDescriptionMap = developer.getDeveloperProfile()
					.getDevelopmentHistory().getNormalizedTaskPersonalizedDescriptionMap();
			double normalizedNumberOfTasksDeveloped = 0;
			if (normalizedTaskMappedDescriptionMap.containsKey(personalizedDescription)) {
				normalizedNumberOfTasksDeveloped = normalizedTaskMappedDescriptionMap.get(personalizedDescription);
			}

			allocationInfo.setTaskEffortAverage(taskEffortAverage);
			allocationInfo.setNumberOfTasksDeveloped(Double.valueOf(numberOfTasksDeveloped).intValue());
			allocationInfo.setNormalizedNumberOfTasksDeveloped(normalizedNumberOfTasksDeveloped);

		}
	}

	public static ArrayList<AllocationInfo> createAllocationInfoList(ArrayList<Developer> developerList,
			ArrayList<TargetInfo> targetInfoList) {

		ArrayList<AllocationInfo> allocationInfoList = new ArrayList<AllocationInfo>();
		for (int i = 0; i < developerList.size(); i++) {
			Developer developer = developerList.get(i);

			for (int j = 0; j < targetInfoList.size(); j++) {
				TargetInfo targetInfo = targetInfoList.get(j);
				ArrayList<Task> projecTaskList = ProjectUtil.getTasks(targetInfo.getProject());

				for (int k = 0; k < projecTaskList.size(); k++) {
					Task task = projecTaskList.get(k);

					AllocationInfo allocationInfo = new AllocationInfo();
					allocationInfo.setDeveloper(developer);
					allocationInfo.setTask(task);
					allocationInfo.setTargetInfo(targetInfo);
					allocationInfoList.add(allocationInfo);
				}

			}
		}
		return allocationInfoList;
	}

	// public static ArrayList<AllocationInfo>
	// createAllocationInfoList2(ArrayList<Developer> developerList,
	// ArrayList<Project> targetProjectList) {
	// ArrayList<Task> taskList = ProjectUtil.getTasks(targetProjectList);
	// ArrayList<AllocationInfo> allocationInfoList = new
	// ArrayList<AllocationInfo>();
	// for (int i = 0; i < developerList.size(); i++) {
	// Developer developer = developerList.get(i);
	// for (int j = 0; j < taskList.size(); j++) {
	// Task task = taskList.get(j);
	// AllocationInfo allocationInfo = new AllocationInfo();
	// allocationInfo.setDeveloperId(developer.getId());
	// allocationInfo.setTaskId(task.getId());
	// allocationInfo.setDeveloperName(developer.getName());
	// allocationInfo.setTaskPersonalizedDescription(task.getPersonalizedDescription());
	// allocationInfo.setTaskPlataform(task.getPlataform());
	// allocationInfoList.add(allocationInfo);
	//
	// }
	// }
	// return allocationInfoList;
	// }

	public static HashMap<String, AllocationInfo> createAllocationInfoMap(
			ArrayList<AllocationInfo> allocationInfoList) {
		HashMap<String, AllocationInfo> allocationInfoMap = new HashMap<String, AllocationInfo>();

		for (int i = 0; i < allocationInfoList.size(); i++) {
			AllocationInfo allocationInfo = allocationInfoList.get(i);
			String key = allocationInfo.getTargetInfo().getTargetConfiguration() + allocationInfo.getDeveloper().getId()
					+ allocationInfo.getTask().getId();
			allocationInfoMap.put(key, allocationInfo);
		}
		return allocationInfoMap;
	}

	public static String toString(ArrayList<AllocationInfo> allocationInfoList) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < allocationInfoList.size(); i++) {
			builder.append(allocationInfoList.get(i).toString());
			builder.append(System.lineSeparator());
		}
		return builder.toString();
	}
}
