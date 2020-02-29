package util;

import java.util.ArrayList;

import project.Project;
import project.TargetInfo;

public class TargetInfoUtil {

	public static ArrayList<Project> getProjects(ArrayList<TargetInfo> targetInfoList) {
		ArrayList<Project> projectList = new ArrayList<Project>();
		for (int i = 0; i < targetInfoList.size(); i++) {
			projectList.add(targetInfoList.get(i).getProject());
		}
		return projectList;
	}
	
	
	public static String toString(ArrayList<TargetInfo> targetInfoList) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < targetInfoList.size(); i++) {
			builder.append(targetInfoList.get(i).toString());
			builder.append(System.lineSeparator());
		}
		return builder.toString();
	}

}
