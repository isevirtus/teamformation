package project;

import java.io.Serializable;
import java.util.ArrayList;

public class Task implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4250731284973819722L;
	private String id;
	private String originalDescription;
	private String mappedDescription;
	private double estimatedEffort;
	private double realEffort;
	private String developerName;
	private String layer;
	private String language;
	private String framework;
	private String api;
	private String persistence;
	private String plataform;
	private String personalizedDescription;
	private ArrayList<Tag> tagList;

	public Task(String id) {
		setId(id);
		this.tagList = new ArrayList<Tag>();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getEstimatedEffort() {
		return estimatedEffort;
	}

	public void setEstimatedEffort(double estimatedEffort) {
		this.estimatedEffort = estimatedEffort;
	}

	public double getRealEffort() {
		return realEffort;
	}

	public void setRealEffort(double realEffort) {
		this.realEffort = realEffort;
	}

	public String getDeveloperName() {
		return developerName;
	}

	public void setDeveloperName(String developerName) {
		this.developerName = developerName;
	}

	public String getOriginalDescription() {
		return originalDescription;
	}

	public void setOriginalDescription(String originalDescription) {
		this.originalDescription = originalDescription;
	}

	public String getMappedDescription() {
		return mappedDescription;
	}

	public void setMappedDescription(String mappedDescription) {
		this.mappedDescription = mappedDescription;
	}

	public String getLayer() {
		return layer;
	}

	public void setLayer(String layer) {
		this.layer = layer;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getFramework() {
		return framework;
	}

	public void setFramework(String framework) {
		this.framework = framework;
	}

	public String getApi() {
		return api;
	}

	public void setApi(String api) {
		this.api = api;
	}

	public String getPersistence() {
		return persistence;
	}

	public void setPersistence(String persistence) {
		this.persistence = persistence;
	}

	public String getPlataform() {
		return plataform;
	}

	public void setPlataform(String plataform) {
		this.plataform = plataform;
	}

	public String getPersonalizedDescription() {
		return personalizedDescription;
	}

	public void setPersonalizedDescription(String personalizedDescription) {
		this.personalizedDescription = personalizedDescription;
	}

	public ArrayList<Tag> getTagList() {
		return tagList;
	}

	public void setTagList(ArrayList<Tag> tagList) {
		this.tagList = tagList;
	}

	public void addTag(String key, double value) {
		if (key != null) {
			this.tagList.add(new Tag(key, value));
		}

	}

	public void removeTag(Tag tag) {
		this.tagList.remove(tag);
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("-------| TASK = " + this.id + " | ");
		builder.append("ORIGINAL = " + this.originalDescription + " | ");
		builder.append("MAPPED = " + this.mappedDescription + " | ");
		builder.append("LAYER = " + this.layer + " | ");
		builder.append("LANGUAGE = " + this.language + " | ");
		builder.append("API = " + this.api + " | ");
		builder.append("FRAMEWORK = " + this.framework + " | ");
		builder.append("PERSISTENCE = " + this.persistence + " | ");
		builder.append("ESTIMATED EFFORT = " + this.estimatedEffort + " | ");
		builder.append("REAL EFFORT = " + this.realEffort + " | ");
		builder.append("DEVELOPER NAME = " + this.developerName + " | ");

		return builder.toString();
	}

}
