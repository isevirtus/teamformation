package util;

import java.util.ArrayList;

import project.Sprint;
import project.Task;
import project.UserStory;

public class SprintUtil {

	public static Sprint getSprint(ArrayList<Sprint> sprintList, String sprintId) {
		for (int i = 0; i < sprintList.size(); i++) {
			if (sprintList.get(i).getId().equalsIgnoreCase(sprintId)) {
				return sprintList.get(i);
			}
		}
		return null;
	}

	public static Task getTask(ArrayList<Sprint> sprintList, String taskId) {
		ArrayList<Task> taskList = getTasks(sprintList, taskId);
		Task task = TaskUtil.getTask(taskList, taskId);

		return task;
	}

	public static Task getTask(Sprint sprint, String taskId) {
		Task task = UserStoryUtil.getTask(sprint.getUserStoryList(), taskId);

		return task;
	}

	public static ArrayList<String> getTaskIds(ArrayList<Sprint> sprintList) {
		ArrayList<String> idList = new ArrayList<String>();
		for (int i = 0; i < sprintList.size(); i++) {
			idList.addAll(getTaskIds(sprintList.get(i)));
		}

		return idList;
	}

	public static ArrayList<String> getTaskIds(Sprint sprint) {
		ArrayList<String> idList = UserStoryUtil.getTaskIds(sprint.getUserStoryList());

		return idList;
	}

	public static ArrayList<Task> getTasks(ArrayList<Sprint> sprintList) {
		ArrayList<Task> taskList = new ArrayList<Task>();
		for (int i = 0; i < sprintList.size(); i++) {
			taskList.addAll(getTasks(sprintList.get(i)));
		}

		return taskList;
	}

	public static ArrayList<Task> getTasks(ArrayList<Sprint> sprintList, String developerName) {
		ArrayList<Task> developerTaskList = new ArrayList<Task>();
		for (int i = 0; i < sprintList.size(); i++) {
			developerTaskList.addAll(getTasks(sprintList.get(i), developerName));
		}

		return developerTaskList;
	}

	public static ArrayList<Task> getTasks(Sprint sprint) {
		ArrayList<Task> taskList = UserStoryUtil.getTasks(sprint.getUserStoryList());

		return taskList;
	}

	public static ArrayList<Task> getTasks(Sprint sprint, String developerName) {
		ArrayList<Task> developerTaskList = UserStoryUtil.getTasks(sprint.getUserStoryList(), developerName);

		return developerTaskList;
	}

	public static ArrayList<UserStory> getUserStories(Sprint sprint) {
		ArrayList<UserStory> userStoryList = new ArrayList<UserStory>();
		userStoryList.addAll(sprint.getUserStoryList());
		return userStoryList;
	}

	public static UserStory getUserStory(Sprint sprint, String userStoryId) {
		UserStory userStory = UserStoryUtil.getUserStory(sprint.getUserStoryList(), userStoryId);

		return userStory;
	}

}
