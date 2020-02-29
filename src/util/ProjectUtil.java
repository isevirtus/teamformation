package util;

import java.util.ArrayList;

import org.apache.commons.lang3.SerializationUtils;

import developer.Developer;
import project.Project;
import project.Sprint;
import project.Tag;
import project.TargetInfo;
import project.Task;
import project.UserStory;

public class ProjectUtil {

	public static ArrayList<Project> copyProjectList(ArrayList<Project> projectList, ArrayList<String> projectIdList) {
		ArrayList<Project> list = getProjects(projectList, projectIdList);
		ArrayList<Project> newProjectList = SerializationUtils.clone(list);

		return newProjectList;
	}

	public static void createDeepCopyByTargetInfoList(ArrayList<Project> projectList,
			ArrayList<TargetInfo> targetInfoList) {
		ArrayList<TargetInfo> targetProjectList = new ArrayList<>();
		for (int i = 0; i < targetInfoList.size(); i++) {
			String targetProjectId = targetInfoList.get(i).getProjectId();
			Project project = SerializationUtils.clone(getProject(projectList, targetProjectId));
			targetInfoList.get(i).setProject(project);
		}
	}

	public static int getNumberOfTasks(ArrayList<Project> projectList, String developerName, String mappedDescription) {
		ArrayList<Task> projectTaskList = getTasks(projectList);
		ArrayList<Task> developerTaskList = TaskUtil.getTasks(projectTaskList, developerName);
		developerTaskList = TaskUtil.getTasksBypersonalizedDescription(developerTaskList, mappedDescription);

		return developerTaskList.size();
	}

	public static int getNumberOfTasks(Project project, String developerName, String mappedDescription) {
		ArrayList<Task> projectTaskList = getTasks(project);
		ArrayList<Task> developerTaskList = TaskUtil.getTasks(projectTaskList, developerName);
		developerTaskList = TaskUtil.getTasksBypersonalizedDescription(developerTaskList, mappedDescription);

		return developerTaskList.size();
	}

	public static Project getProject(ArrayList<Project> projectList, String projectId) {
		for (int i = 0; i < projectList.size(); i++) {
			if (projectList.get(i).getId().equalsIgnoreCase(projectId)) {
				return projectList.get(i);
			}
		}

		return null;
	}

	public static boolean containTask(Project project, String taskId) {
		ArrayList<Task> taskList = getTasks(project);
		for (int i = 0; i < taskList.size(); i++) {
			if (taskList.get(i).getId().equalsIgnoreCase(taskId)) {
				return true;
			}
		}
		return false;
	}

	public static Project getProject(ArrayList<Project> projectList, Task task) {
		for (int i = 0; i < projectList.size(); i++) {
			Project project = projectList.get(i);
			if (containTask(project, task.getId())) {
				return project;
			}
		}

		return null;
	}

	public static ArrayList<Project> getProjects(ArrayList<Project> projectList, ArrayList<String> idList) {
		ArrayList<Project> list = new ArrayList<Project>();
		for (int i = 0; i < idList.size(); i++) {
			Project project = getProject(projectList, idList.get(i));
			if (project != null) {
				list.add(project);
			}
		}

		return list;
	}

	public static Sprint getSprint(Project project, String sprintId) {
		Sprint sprint = SprintUtil.getSprint(project.getSprintList(), sprintId);

		return sprint;
	}

	public static Task getTask(ArrayList<Project> projectList, String taskId) {
		ArrayList<Task> taskList = getTasks(projectList);
		Task task = TaskUtil.getTask(taskList, taskId);

		return task;
	}

	public static Task getTask(Project project, String taskId) {
		ArrayList<Task> taskList = getTasks(project);
		Task task = TaskUtil.getTask(taskList, taskId);

		return task;
	}

	public static double getTaskEffortAverage(ArrayList<Project> projectList, String mappedDescription) {
		ArrayList<Task> taskList = getTasks(projectList);
		double taskEffortAverage = TaskUtil.getTaskEffortAverage(taskList, mappedDescription);

		return taskEffortAverage;
	}

	public static ArrayList<Task> getTasks(ArrayList<Project> projectList) {
		ArrayList<Task> taskList = new ArrayList<>();
		for (int i = 0; i < projectList.size(); i++) {
			taskList.addAll(getTasks(projectList.get(i)));
		}
		return taskList;
	}

	public static ArrayList<Task> getTasks(ArrayList<Project> projectList, String developerName) {
		ArrayList<Task> taskList = getTasks(projectList);
		taskList = TaskUtil.getTasks(taskList, developerName);
		return taskList;
	}

	public static ArrayList<Task> getTasks(Project project) {
		ArrayList<Task> taskList = SprintUtil.getTasks(project.getSprintList());

		return taskList;
	}

	public static ArrayList<Task> getTasks(Project project, String developerName) {
		ArrayList<Task> taskList = getTasks(project);
		taskList = TaskUtil.getTasks(taskList, developerName);

		return taskList;
	}
	


	public static String toString(ArrayList<Project> projectList) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < projectList.size(); i++) {
			builder.append(projectList.get(i).toString());
			builder.append(System.lineSeparator());
		}
		return builder.toString();
	}

}
