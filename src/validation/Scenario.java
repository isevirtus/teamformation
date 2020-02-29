package validation;

import java.util.ArrayList;

import dataset.Dataset;
import developer.Developer;
import project.Project;
import project.TargetInfo;
import util.DeveloperUtil;
import util.DevelopmentHistoryUtil;
import util.ArrayListUtil;
import util.ProjectUtil;
import util.TargetInfoUtil;

public class Scenario {

	private String name;
	private ArrayList<String> targetDeveloperIdList;
	private ArrayList<Developer> targetDeveloperList;
	private ArrayList<TargetInfo> targetInfoList;
	private ArrayList<String> trainningProjectIdList;
	private ArrayList<Project> trainningProjectsList;
	private ArrayList<String> optimalSolution;
	

	public Scenario(String name) {
		setName(name);
		this.targetDeveloperIdList = new ArrayList<String>();
		this.trainningProjectIdList = new ArrayList<String>();
		this.targetInfoList = new ArrayList<TargetInfo>();
		this.optimalSolution = new ArrayList<String>();
		this.optimalSolution = new ArrayList<String>();
	}

	public void createScenarioData(Dataset dataset) {
		this.trainningProjectsList = ProjectUtil.copyProjectList(dataset.getProjectList(), this.trainningProjectIdList);
		ProjectUtil.createDeepCopyByTargetInfoList(dataset.getProjectList(), targetInfoList);
		this.targetDeveloperList = DeveloperUtil.deepCopy(dataset.getDeveloperList(), this.targetDeveloperIdList);
	}

	public String getName() {
		return name;
	}

	public ArrayList<String> getTargetDeveloperIdList() {
		return targetDeveloperIdList;
	}

	public ArrayList<Developer> getTargetDeveloperList() {
		return targetDeveloperList;
	}

	public ArrayList<TargetInfo> getTargetInfoList() {
		return targetInfoList;
	}

	public ArrayList<String> getTrainningProjectIdList() {
		return trainningProjectIdList;
	}

	public ArrayList<Project> getTrainningProjectsList() {
		return trainningProjectsList;
	}

	public void loadSettings(String filePath) {

	}

	public void setName(String name) {
		this.name = name;
	}

	public void setTargetDeveloperIdList(ArrayList<String> targetDeveloperIdList) {
		this.targetDeveloperIdList = targetDeveloperIdList;
	}

	public void setTargetDeveloperList(ArrayList<Developer> targetDeveloperList) {
		this.targetDeveloperList = targetDeveloperList;
	}

	public void setTargetInfoList(ArrayList<TargetInfo> targetInfoList) {
		this.targetInfoList = targetInfoList;
	}

	public void setTrainningProjectIdList(ArrayList<String> trainningProjectsIdList) {
		this.trainningProjectIdList = trainningProjectsIdList;
	}

	public void setTrainningProjectsList(ArrayList<Project> trainningProjectsList) {
		this.trainningProjectsList = trainningProjectsList;
	}

	public ArrayList<String> getOptimalSolution() {
		return optimalSolution;
	}

	public void setOptimalSolution(ArrayList<String> optimalSolution) {
		this.optimalSolution = optimalSolution;
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TARGET INFO LIST: ");
		builder.append(TargetInfoUtil.toString(getTargetInfoList()));
		builder.append(System.lineSeparator());
		builder.append(System.lineSeparator());
		builder.append(">>>>> TRAINNING PROJECT LIST <<<<< SIZE: " + getTrainningProjectsList().size());
		builder.append(System.lineSeparator());
		builder.append(ProjectUtil.toString(getTrainningProjectsList()));
		// builder.append(">>>>> TARGET PROJECT LIST <<<<< SIZE: " +
		// getTargetProjectList().size());
		builder.append(System.lineSeparator());
		// builder.append(ProjectUtil.toString(getTargetProjectList()));
		builder.append(">>>>> TARGET DEVELOPERT LIST <<<<< SIZE: " + getTargetDeveloperList().size());
		builder.append(System.lineSeparator());
		builder.append(DeveloperUtil.toString(getTargetDeveloperList()));

		return builder.toString();
	}

}
