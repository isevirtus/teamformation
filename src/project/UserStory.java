package project;

import java.io.Serializable;
import java.util.ArrayList;

public class UserStory implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4521412914108651007L;
	private String id;
	private String description;
	private String plataform;
	private int storyPoints;
	private int duration;
	private ArrayList<Task> taskList;

	public UserStory(String id) {
		setId(id);
		this.taskList = new ArrayList<Task>();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPlataform() {
		return plataform;
	}

	public void setPlataform(String plataform) {
		this.plataform = plataform;
	}

	public int getStoryPoints() {
		return storyPoints;
	}

	public void setStoryPoints(int storyPoints) {
		this.storyPoints = storyPoints;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public ArrayList<Task> getTaskList() {
		return taskList;
	}

	public void setTaskList(ArrayList<Task> taskList) {
		this.taskList = taskList;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("-----| USER STORY = " + this.id + " | ");
		builder.append("DESCRIPTION = " + this.description + " | ");
		builder.append("PLATAFORM = " + this.plataform + " | ");
		builder.append(System.lineSeparator());

		for (int i = 0; i < this.taskList.size(); i++) {
			builder.append(this.taskList.get(i).toString());
			builder.append(System.lineSeparator());
		}
		return builder.toString();

	}

}
