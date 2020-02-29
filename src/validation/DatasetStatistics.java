package validation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.lang3.SerializationUtils;
import org.omg.CORBA.FREE_MEM;

import dataset.Dataset;
import dataset.DeveloperDataReading;
import dataset.ProjectDataLoading;
import dataset.ScenarioDataLoading;
import developer.Developer;
import io.jenetics.internal.collection.Array;
import project.Project;
import project.Tag;
import project.TargetInfo;
import project.Task;
import similarity.Allocation;
import util.DeveloperUtil;
import util.HashMapUtil;
import util.ProjectUtil;
import util.TagUtil;
import util.TargetInfoUtil;
import util.TaskUtil;
import vector.Vector;

public class DatasetStatistics {

	public void printDeveloperTaskPersonalizedDescriptionStatsForTargetProjects(ArrayList<Developer> developerList,
			ArrayList<Project> trainningProjectList, ArrayList<Project> targetProjectList) {
		ArrayList<Project> myProjectList = SerializationUtils.clone(targetProjectList);
		ArrayList<Task> allProjectsTaskList = ProjectUtil.getTasks(myProjectList);
		ArrayList<String> taskPersonalizedDescriptionList = TaskUtil
				.getTasksPersonalizedDescriptions(allProjectsTaskList);

		ArrayList<Project> myTrainningPorjectList = SerializationUtils.clone(trainningProjectList);
		ArrayList<Task> allTrainningProjectsTaskList = ProjectUtil.getTasks(myTrainningPorjectList);
		// ArrayList<String> trainningProjectUniqueTagKeyList =
		// TagUtil.getUniqueTagKeys(TaskUtil.getTags(allProjectsTaskList));

		for (int i = 0; i < developerList.size(); i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			for (int k = 0; k < taskPersonalizedDescriptionList.size(); k++) {
				map.put(taskPersonalizedDescriptionList.get(k), "0,00%");
			}

			Developer developer = developerList.get(i);
			ArrayList<Task> taskList = developer.getDeveloperProfile().getDevelopmentHistory().getTaskList();

			for (int j = 0; j < taskPersonalizedDescriptionList.size(); j++) {

				String description = taskPersonalizedDescriptionList.get(j);
				int count = TaskUtil.countTaskPersonalizedDescriptionOfDeveloper(taskList, description,
						developer.getName());
				double total = TaskUtil.count(allTrainningProjectsTaskList, description);

				double frequency = (double) count / total;
				if (total == 0) {
					frequency = 0;
				}

				String frequencyString = String.format("%." + 2 + "f", frequency * 100) + "%";
				map.put(description, frequencyString);

			}

			System.out.println(developer.getName() + " " + developer.getId() + " :");
			for (HashMap.Entry<String, String> entry : map.entrySet()) {
				System.out.println(entry.getKey() + "	" + entry.getValue());
			}
			System.out.println();

		}
	}

	public void printDeveloperTagsStatsForTargetProjects(ArrayList<Developer> developerList,
			ArrayList<Project> trainningProjectList, ArrayList<Project> targetProjectList,
			HashMap<String, Double> weightedMap) {
		ArrayList<Project> myProjectList = SerializationUtils.clone(targetProjectList);
		ArrayList<Task> allProjectsTaskList = ProjectUtil.getTasks(myProjectList);
		ArrayList<String> uniqueTagKeyList = TagUtil.getUniqueTagKeys(TaskUtil.getTags(allProjectsTaskList));

		ArrayList<Project> myTrainningPorjectList = SerializationUtils.clone(trainningProjectList);
		ArrayList<Task> allTrainningProjectsTaskList = ProjectUtil.getTasks(myTrainningPorjectList);
		// ArrayList<String> trainningProjectUniqueTagKeyList =
		// TagUtil.getUniqueTagKeys(TaskUtil.getTags(allProjectsTaskList));

		for (int i = 0; i < developerList.size(); i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			for (int k = 0; k < uniqueTagKeyList.size(); k++) {
				map.put(uniqueTagKeyList.get(k), "0,00%");
			}

			Developer developer = developerList.get(i);
			ArrayList<Task> taskList = developer.getDeveloperProfile().getDevelopmentHistory().getTaskList();
			for (int j = 0; j < uniqueTagKeyList.size(); j++) {
				String key = uniqueTagKeyList.get(j);

				double count = 0;
				double total = 0;
				double frequency=0;
				if (weightedMap == null || weightedMap.isEmpty()) {
					count = TaskUtil.countTagOfDeveloper(taskList, key, developer.getName());
					total = TaskUtil.countTagKey(allTrainningProjectsTaskList, key);
					frequency = (double) count / total;
				} else {
					if (weightedMap.containsKey(key)) {
						count = TaskUtil.countTagOfDeveloper(taskList, key, developer.getName());
						total = TaskUtil.countTagKey(allTrainningProjectsTaskList, key);
						frequency = (double) count / total;
						frequency = frequency * weightedMap.get(key);
						
					} else {
						count = TaskUtil.countTagOfDeveloper(taskList, key, developer.getName());
						total = TaskUtil.countTagKey(allTrainningProjectsTaskList, key);
						frequency = (double) count / total;

					}
				}
				if (total == 0) {
					frequency = 0;
				}

				String frequencyString = String.format("%." + 2 + "f", frequency * 100) + "%";
				map.put(key, frequencyString);

			}

			System.out.println(developer.getName() + " " + developer.getId() + " :");
			for (HashMap.Entry<String, String> entry : map.entrySet()) {
				System.out.println(entry.getKey() + "	" + entry.getValue());
			}
			System.out.println();

		}
	}

	public void printDeveloperTagsStats(ArrayList<Developer> developerList) {
		for (int i = 0; i < developerList.size(); i++) {
			ArrayList<Task> allDevelopersTaskList = DeveloperUtil.getTasks(developerList);
			ArrayList<String> uniqueTagKeyList = TagUtil.getUniqueTagKeys(TaskUtil.getTags(allDevelopersTaskList));
			HashMap<String, String> map = new HashMap<String, String>();
			for (int k = 0; k < uniqueTagKeyList.size(); k++) {
				map.put(uniqueTagKeyList.get(k), "0,0%");
			}
			Developer developer = developerList.get(i);
			ArrayList<Task> taskList = developer.getDeveloperProfile().getDevelopmentHistory().getTaskList();
			ArrayList<Tag> tagList = TaskUtil.getTags(taskList);
			ArrayList<String> keyList = TagUtil.getUniqueTagKeys(tagList);
			System.out.println(developer.getName() + " " + developer.getId() + " :");

			for (int j = 0; j < keyList.size(); j++) {
				String key = keyList.get(j);
				int count = TagUtil.countTag(tagList, key);
				double frequency = (double) count / tagList.size();
				String frequencyString = String.format("%." + 2 + "f", frequency * 100) + "%";
				map.put(key, frequencyString);

			}
			for (HashMap.Entry<String, String> entry : map.entrySet()) {
				System.out.println(entry.getKey() + "	" + entry.getValue());
			}
			System.out.println();
			System.out.println();

		}
	}

	public void printProjectTagStats(ArrayList<TargetInfo> targetInfoList) {
		for (int i = 0; i < targetInfoList.size(); i++) {
			ArrayList<Project> projects = TargetInfoUtil.getProjects(targetInfoList);
			ArrayList<Task> allProjectsTaskList = ProjectUtil.getTasks(projects);
			ArrayList<String> uniqueTagKeyList = TagUtil.getUniqueTagKeys(TaskUtil.getTags(allProjectsTaskList));
			HashMap<String, String> map = new HashMap<String, String>();
			for (int k = 0; k < uniqueTagKeyList.size(); k++) {
				map.put(uniqueTagKeyList.get(k), "0,0%");
			}

			Project project = targetInfoList.get(i).getProject();
			ArrayList<Task> taskList = ProjectUtil.getTasks(project);
			ArrayList<Tag> tagList = TaskUtil.getTags(taskList);
			ArrayList<String> keyList = TagUtil.getUniqueTagKeys(tagList);
			System.out.println(project.getName() + " " + project.getId() + " :");
			for (int j = 0; j < keyList.size(); j++) {
				String key = keyList.get(j);
				double count = 0;
				double frequency = 0;
				if (targetInfoList.get(i).getWeithedTagMap().isEmpty()) {
					count = TagUtil.countTag(tagList, key);
					frequency = (double) count / tagList.size();
				} else {
					if (targetInfoList.get(i).getWeithedTagMap().containsKey(key)) {
						// count = TagUtil.countTag(tagList, key) *
						// targetInfoList.get(i).getWeithedTagMap().get(key);
						// double total = TagUtil.countWeightedTag(tagList,
						// targetInfoList.get(i).getWeithedTagMap());
						// frequency = (double) count / total;
						count = TagUtil.countTag(tagList, key);
						frequency = (double) count / tagList.size();
						frequency = frequency * targetInfoList.get(i).getWeithedTagMap().get(key);

					} else {
						count = TagUtil.countTag(tagList, key);
						frequency = (double) count / tagList.size();
					}
				}

				String frequencyString = String.format("%." + 2 + "f", frequency * 100) + "%";
				map.put(key, frequencyString);

			}
			for (HashMap.Entry<String, String> entry : map.entrySet()) {
				System.out.println(entry.getKey() + "	" + entry.getValue());
			}
			System.out.println();
			System.out.println();

		}
	}

	public void printProjectTaskPersnonalizedDescriptionStats(ArrayList<Project> projectList) {
		for (int i = 0; i < projectList.size(); i++) {
			ArrayList<Task> allProjectsTaskList = ProjectUtil.getTasks(projectList);
			ArrayList<String> taskPersonalizedDescriptionList = TaskUtil
					.getTasksPersonalizedDescriptions(allProjectsTaskList);
			HashMap<String, String> map = new HashMap<String, String>();
			for (int k = 0; k < taskPersonalizedDescriptionList.size(); k++) {
				map.put(taskPersonalizedDescriptionList.get(k), "0,0%");
			}

			Project project = projectList.get(i);
			ArrayList<Task> taskList = ProjectUtil.getTasks(project);
			ArrayList<String> descriptionList = TaskUtil.getTasksPersonalizedDescriptions(taskList);
			System.out.println(project.getName() + " " + project.getId() + " :");
			for (int j = 0; j < descriptionList.size(); j++) {
				String description = descriptionList.get(j);
				int count = TaskUtil.count(taskList, description);
				double frequency = (double) count / taskList.size();
				String frequencyString = String.format("%." + 2 + "f", frequency * 100) + "%";
				map.put(description, frequencyString);

			}
			for (HashMap.Entry<String, String> entry : map.entrySet()) {
				System.out.println(entry.getKey() + "	" + entry.getValue());
			}
			System.out.println();
			System.out.println();

		}
	}

	public static void main(String[] args) {

		// Create dataset from excel files
		String projectsFilePath = "data/dataset/todos/projetos-todos.xls";
		ArrayList<Project> projectList = null;
		String developersFilePath = "data/dataset/desenvolvedores.xls";
		ArrayList<Developer> developerList = null;
		String scenariosFilePath = "data/dataset/todos/cenarios_best-individual-todos.xls";
		ArrayList<Scenario> scenarioList = null;

		try {
			projectList = ProjectDataLoading.createAllProjectData(projectsFilePath);
			System.out.println(">>>>>> Project data loaded! <<<<<<");
			developerList = DeveloperDataReading.createAllDevelopertData(developersFilePath);
			System.out.println(">>>>>> Developer data loaded! <<<<<<");
			scenarioList = ScenarioDataLoading.createAllScenarioData(scenariosFilePath);
			System.out.println(">>>>>> Scenario data loaded! <<<<<<");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Load data in dataset object
		Dataset dataset = new Dataset();
		dataset.setDeveloperList(developerList);
		dataset.setProjectList(projectList);

		// Create scenario data from dataset
		// for (int i = 0; i < scenarioList.size(); i++) {
		Scenario scenario = scenarioList.get(0);
		scenario.createScenarioData(dataset);
		Allocation allocation = new Allocation(scenario);

		DatasetStatistics stats = new DatasetStatistics();
		// stats.printProjectTagStats(projectList);
		// stats.printProjectTaskPersnonalizedDescriptionStats(projectList);
		// stats.printDeveloperTagsStats(scenario.getTargetDeveloperList());

		ArrayList<Project> targetProjects = new ArrayList<Project>();
		for (int i = 0; i < scenario.getTargetInfoList().size(); i++) {
			targetProjects.add(scenario.getTargetInfoList().get(i).getProject());
		}
		
//	 stats.printProjectTagStats(scenario.getTargetInfoList());
//		stats.printDeveloperTagsStatsForTargetProjects(scenario.getTargetDeveloperList(),
//				scenario.getTrainningProjectsList(), targetProjects,null);
		
//		HashMap<String,Double> map = new HashMap<String,Double>();
//		map.put("javascript", 5.0);
//		map.put("node", 5.0);
//		map.put("mongoose", 5.0);
//		map.put("mongodb", 5.0);
//		map.put("express", 5.0);
//		map.put("router", 5.0);
//		stats.printDeveloperTagsStatsForTargetProjects(scenario.getTargetDeveloperList(),
//				scenario.getTrainningProjectsList(), targetProjects,map);
		
		 stats.printProjectTaskPersnonalizedDescriptionStats(targetProjects);		
		 stats.printDeveloperTaskPersonalizedDescriptionStatsForTargetProjects(scenario.getTargetDeveloperList(),
		 scenario.getTrainningProjectsList(), targetProjects);


	}
}
