package developer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import project.Tag;
import project.Task;
import util.TagUtil;
import util.TaskUtil;

public class DevelopmentHistory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5981004394174127704L;

	public static double DEFAULT_TAG_VALUE = 1.0;
	public static double MININUM_TAG_VALUE = 0.0;
	public static double MININUM_MAPPED_DESCRIPTION_COUNT = 0.0;
	private ArrayList<Task> taskList;
	private HashMap<String, Double> taskPersonalizedDescriptionMap;
	private HashMap<String, Double> normalizedTaskPersonalizedDescriptionMap;
	private ArrayList<Tag> developmentHistoryTagList;
	private ArrayList<Tag> normalizedDevelopmentHistoryTagList;

	public DevelopmentHistory() {
		this.taskList = new ArrayList<Task>();
		this.developmentHistoryTagList = new ArrayList<Tag>();
		this.normalizedDevelopmentHistoryTagList = new ArrayList<Tag>();
		this.taskPersonalizedDescriptionMap = new HashMap<String, Double>();
		this.normalizedTaskPersonalizedDescriptionMap = new HashMap<String, Double>();
	}

	public ArrayList<Task> getTaskList() {
		return taskList;
	}

	public void setTaskList(ArrayList<Task> taskList) {
		this.taskList = taskList;
	}

	public ArrayList<Tag> getDevelopmentHistoryTagList() {
		return developmentHistoryTagList;
	}

	public void setDevelopmentHistoryTagList(ArrayList<Tag> developmentHistoryTagList) {
		this.developmentHistoryTagList = developmentHistoryTagList;
	}

	public ArrayList<Tag> getNormalizedDevelopmentHistoryTagList() {
		return normalizedDevelopmentHistoryTagList;
	}

	public void setNormalizedDevelopmentHistoryTagList(ArrayList<Tag> normalizedDevelopmentHistoryTagList) {
		this.normalizedDevelopmentHistoryTagList = normalizedDevelopmentHistoryTagList;
	}

	
	public HashMap<String, Double> getTaskPersonalizedDescriptionMap() {
		return taskPersonalizedDescriptionMap;
	}

	public void setTaskPersonalizedDescriptionMap(HashMap<String, Double> taskPersonalizedDescriptionMap) {
		this.taskPersonalizedDescriptionMap = taskPersonalizedDescriptionMap;
	}

	public HashMap<String, Double> getNormalizedTaskPersonalizedDescriptionMap() {
		return normalizedTaskPersonalizedDescriptionMap;
	}

	public void setNormalizedTaskPersonalizedDescriptionMap(
			HashMap<String, Double> normalizedTaskPersonalizedDescriptionMap) {
		this.normalizedTaskPersonalizedDescriptionMap = normalizedTaskPersonalizedDescriptionMap;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DEVELOPMENT HISTORY TASK LIST");
		builder.append(System.lineSeparator());
		builder.append(TaskUtil.toString(getTaskList()));
		builder.append(System.lineSeparator());
		builder.append("DEVELOPMENT HISTORY TAG LIST: ");
		builder.append(TagUtil.toString(getDevelopmentHistoryTagList()));
		builder.append(System.lineSeparator());
		builder.append("DEVELOPMENT HISTORY NOMALIZED TAG LIST: ");
		builder.append(TagUtil.toString(getNormalizedDevelopmentHistoryTagList()));
		builder.append(System.lineSeparator());
		builder.append("DEVELOPMENT HISTORY TASK MAPPED DESCRIPTION: ");
		builder.append(TaskUtil.toString(getTaskPersonalizedDescriptionMap()));
		builder.append(System.lineSeparator());
		builder.append("DEVELOPMENT HISTORY NORMALIZED TASK MAPPED DESCRIPTION: ");
		builder.append(TaskUtil.toString(getNormalizedTaskPersonalizedDescriptionMap()));
		
		return builder.toString();
	}

}
