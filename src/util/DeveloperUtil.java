package util;

import java.util.ArrayList;
import java.util.stream.Collectors;

import org.apache.commons.lang3.SerializationUtils;

import developer.Developer;
import project.Project;
import project.Tag;
import project.Task;

public class DeveloperUtil {

	public static ArrayList<Developer> deepCopy(ArrayList<Developer> developerList, ArrayList<String> developerIdList) {
		ArrayList<Developer> list = getDevelopers(developerList, developerIdList);
		ArrayList<Developer> newDeveloperList = SerializationUtils.clone(list);

		return newDeveloperList;
	}

	public static Developer getDeveloper(ArrayList<Developer> developerList, String developerId) {
		for (int i = 0; i < developerList.size(); i++) {
			if (developerList.get(i).getId().equalsIgnoreCase(developerId)) {
				return developerList.get(i);
			}
		}

		return null;
	}

	public static ArrayList<String> getDeveloperIds(ArrayList<Developer> developerList) {
		ArrayList<String> developerIdList = new ArrayList<>();
		for (int i = 0; i < developerList.size(); i++) {
			developerIdList.add(developerList.get(i).getId());
		}
		return developerIdList;
	}

	public static ArrayList<Developer> getDevelopers(ArrayList<Developer> developerList,
			ArrayList<String> developerIdList) {
		ArrayList<Developer> list = new ArrayList<Developer>();
		for (int i = 0; i < developerList.size(); i++) {
			String developerId = developerList.get(i).getId();
			if (developerIdList.contains(developerId)) {
				list.add(developerList.get(i));
			}
		}

		return list;
	}

	public static String tagProfileToString(Developer developer) {
		StringBuilder builder = new StringBuilder();
		ArrayList<Tag> tagList = developer.getDeveloperProfile().getDevelopmentHistory().getDevelopmentHistoryTagList();
		builder.append(developer.getName() + ": ");
		for (int i = 0; i < tagList.size(); i++) {
			builder.append(tagList.get(i).toString());
		}

		return builder.toString();
	}
	
	public static ArrayList<Task> getTasks(ArrayList<Developer> developerList) {
		ArrayList<Task> taskList = new ArrayList<>();
		for (int i = 0; i < developerList.size(); i++) {
			taskList.addAll(developerList.get(i).getDeveloperProfile().getDevelopmentHistory().getTaskList());
		}
		return taskList;
	}

	public static String toString(ArrayList<Developer> developerList) {

		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < developerList.size(); i++) {
			builder.append(developerList.get(i).toString());
			builder.append(System.lineSeparator());
		}

		return builder.toString();
	}
}
