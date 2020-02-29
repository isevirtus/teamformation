package project;

import java.util.ArrayList;
import java.util.HashMap;

import util.HashMapUtil;

public class TargetInfo {

	private Project project;
	private String targetConfiguration;
	private String projectId;
	private String sprintId;
	private int teamSize;
	private ArrayList<String> fixedDeveloperIdList;
	private HashMap<String, Double> weithedTagMap;

	public TargetInfo() {
		this.weithedTagMap = new HashMap<String, Double>();
		this.fixedDeveloperIdList = new ArrayList<String>();

	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}


	public String getTargetConfiguration() {
		return targetConfiguration;
	}

	public void setTargetConfiguration(String targetConfiguration) {
		this.targetConfiguration = targetConfiguration;
	}

	public String getProjectId() {
		return projectId;
	}

	public String getSprintId() {
		return sprintId;
	}

	public int getTeamSize() {
		return teamSize;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public void setSprintId(String sprintId) {
		this.sprintId = sprintId;
	}

	public void setTeamSize(int teamSize) {
		this.teamSize = teamSize;
	}

	public ArrayList<String> getFixedDeveloperIdList() {
		return fixedDeveloperIdList;
	}

	public void setFixedDeveloperIdList(ArrayList<String> fixedDeveloperIdList) {
		this.fixedDeveloperIdList = fixedDeveloperIdList;
	}

	public HashMap<String, Double> getWeithedTagMap() {
		return weithedTagMap;
	}

	public void setWeithedTagMap(HashMap<String, Double> weithedTagMap) {
		this.weithedTagMap = weithedTagMap;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(" | PROJECT ID: " + getProjectId() + " | ");
		builder.append("SPRINT ID: " + getSprintId() + " | ");
		builder.append("TEAM SIZE ID: " + getTeamSize() + " | ");
		builder.append("WEIGHTED TAGS: " + HashMapUtil.toStringStringDouble(getWeithedTagMap()));

		return builder.toString();
	}

}
