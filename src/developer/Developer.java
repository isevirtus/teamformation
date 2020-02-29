package developer;

import java.io.Serializable;

import util.DeveloperUtil;
import util.TagUtil;
import util.TaskUtil;

public class Developer implements Serializable {

	private static final long serialVersionUID = -2095275293073465862L;
	private String id;
	private String name;
	private String experienceLevel;
	private DeveloperProfile developerProfile;

	public Developer(String id, String name) {
		setId(id);
		setName(name);
		developerProfile = new DeveloperProfile();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExperienceLevel() {
		return experienceLevel;
	}

	public void setExperienceLevel(String experienceLevel) {
		this.experienceLevel = experienceLevel;
	}

	public DeveloperProfile getDeveloperProfile() {
		return developerProfile;
	}

	public void setDeveloperProfile(DeveloperProfile developerProfile) {
		this.developerProfile = developerProfile;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(
				"==================================================================================================================================");
		builder.append(System.lineSeparator());
		builder.append("ID: " + getId() + " | ");
		builder.append("NAME: " + getName() + " | ");
		builder.append("LEVEL: " + getExperienceLevel() + " | ");
		builder.append("NUMBER OF TASKS: " + getDeveloperProfile().getDevelopmentHistory().getTaskList().size());
		builder.append(System.lineSeparator());
		builder.append(System.lineSeparator());
		builder.append(getDeveloperProfile().toString());

		return builder.toString();
	}

}
