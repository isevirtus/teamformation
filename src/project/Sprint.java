package project;

import java.io.Serializable;
import java.util.ArrayList;

public class Sprint implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1736277404560250975L;
	private String id;
	private int duration;
	private ArrayList<UserStory> userStoryList;
	
	public Sprint(String id) {
		setId(id);
		this.userStoryList = new ArrayList<UserStory>();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public ArrayList<UserStory> getUserStoryList() {
		return userStoryList;
	}

	public void setUserStoryList(ArrayList<UserStory> userStoryList) {
		this.userStoryList = userStoryList;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("---| SPRINT = " + this.id + " | ");
		builder.append(System.lineSeparator());

		for (int i = 0; i < this.userStoryList.size(); i++) {
			builder.append(this.userStoryList.get(i).toString());
		}
		return builder.toString();
	}

}
