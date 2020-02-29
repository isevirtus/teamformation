package dataset;

import java.util.ArrayList;

import developer.Developer;
import project.Project;

public class Dataset {
	private ArrayList<Developer> developerList;
	private ArrayList<Project> projectList;

	public Dataset() {
		this.developerList = new ArrayList<Developer>();
		this.projectList = new ArrayList<Project>();
	}

	public ArrayList<Developer> getDeveloperList() {
		return developerList;
	}

	public ArrayList<Project> getProjectList() {
		return projectList;
	}

	public void setDeveloperList(ArrayList<Developer> developerList) {
		this.developerList = developerList;
	}

	public void setProjectList(ArrayList<Project> projectList) {
		this.projectList = projectList;
	}

}
