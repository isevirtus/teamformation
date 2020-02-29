package util;

import java.util.ArrayList;

import developer.Developer;
import project.Task;
import project.UserStory;

public class UserStoryUtil {

	public static Task getTask(ArrayList<UserStory> userStoryList, String taskId) {
		ArrayList<Task> taskList = getTasks(userStoryList, taskId);
		Task task = TaskUtil.getTask(taskList, taskId);

		return task;
	}

	public static Task getTask(UserStory userStory, String taskId) {
		Task task = TaskUtil.getTask(userStory.getTaskList(), taskId);

		return task;
	}

	public static ArrayList<String> getTaskIds(ArrayList<UserStory> userStoryList) {
		ArrayList<String> idList = new ArrayList<String>();
		for (int i = 0; i < userStoryList.size(); i++) {
			idList.addAll(getTaskIds(userStoryList.get(i)));
		}

		return idList;
	}

	public static ArrayList<String> getTaskIds(UserStory userStory) {
		ArrayList<String> idList = TaskUtil.getTaskIds(userStory.getTaskList());

		return idList;
	}

	public static ArrayList<Task> getTasks(ArrayList<UserStory> userStoryList) {
		ArrayList<Task> taskList = new ArrayList<Task>();
		for (int i = 0; i < userStoryList.size(); i++) {
			taskList.addAll(getTasks(userStoryList.get(i)));
		}
		return taskList;
	}

	public static ArrayList<Task> getTasks(ArrayList<UserStory> userStoryList, String developerName) {
		ArrayList<Task> developerTaskList = new ArrayList<Task>();
		for (int i = 0; i < userStoryList.size(); i++) {
			developerTaskList.addAll(getTasks(userStoryList.get(i), developerName));
		}

		return developerTaskList;
	}

	public static ArrayList<Task> getTasks(UserStory userStory) {
		ArrayList<Task> taskList = new ArrayList<Task>();
		for (int i = 0; i < userStory.getTaskList().size(); i++) {
			taskList.add(userStory.getTaskList().get(i));
		}
		return taskList;
	}

	public static ArrayList<Task> getTasks(UserStory userStory, String developerName) {
		ArrayList<Task> developerTaskList = TaskUtil.getTasks(userStory.getTaskList(), developerName);

		return developerTaskList;
	}

	public static UserStory getUserStory(ArrayList<UserStory> userList, String userStoryId) {
		for (int i = 0; i < userList.size(); i++) {
			if (userList.get(i).getId().equalsIgnoreCase(userStoryId)) {
				return userList.get(i);
			}
		}
		return null;
	}
	
//	public static ArrayList<Task> getDeveloperTasksFromUserStory(Developer developer, UserStory userStory) {
//		ArrayList<Task> developerTaskListFromUserStory = new ArrayList<Task>();
//		ArrayList<Task> userStoryTaskList = UserStoryUtil.getTasks(userStory);
//		for (int i = 0; i < userStoryTaskList.size(); i++) {
//			String developerName = userStoryTaskList.get(i).getDeveloperName();
//			if (developerName != null && !developerName.equalsIgnoreCase("null")
//					&& !developerName.equalsIgnoreCase("")) {
//				if (developerName.equalsIgnoreCase(developer.getName())) {
//					developerTaskListFromUserStory.add(userStoryTaskList.get(i));
//				}
//			}
//		}
//		return developerTaskListFromUserStory;
//	}

}
