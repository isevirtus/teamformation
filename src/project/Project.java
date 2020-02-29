package project;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Project implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6857295861417288368L;
	public static final int DEFAULT_DEMAND_TAG_VALUE = 1;
	private String id;
	private String name;
	private ArrayList<Sprint> sprintList;
//	private TargetInfo targetInfo;
	private ArrayList<String> teamMemberIdList;


	public Project(String id) {
		setId(id);
		this.teamMemberIdList = new ArrayList<String>();
		this.sprintList = new ArrayList<Sprint>();
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public ArrayList<Sprint> getSprintList() {
		return this.sprintList;
	}

//	public TargetInfo getTargetInfo() {
//		return targetInfo;
//	}

	public ArrayList<String> getTeamMemberIdList() {
		return this.teamMemberIdList;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSprintList(ArrayList<Sprint> sprintList) {
		this.sprintList = sprintList;
	}

//	public void setTargetInfo(TargetInfo targetInfo) {
//		this.targetInfo = targetInfo;
//	}

	public void setTeamMemberIdList(ArrayList<String> teamMemberIdList) {
		this.teamMemberIdList = teamMemberIdList;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("-| PROJECT = " + this.id + " | ");
		builder.append("PROJETC NAME = " + this.name + " | ");
		builder.append(System.lineSeparator());

		for (int i = 0; i < this.sprintList.size(); i++) {
			builder.append(this.sprintList.get(i).toString());
		}
		return builder.toString();
	}

}
