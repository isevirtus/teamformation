package similarity;

import developer.Developer;
import project.TargetInfo;
import project.Task;
import vector.ComplexVector;

public class AllocationInfo {

	private TargetInfo targetInfo;
	private Developer developer;
	private Task task;
	private double taskEffortAverage;

	private int numberOfTasksDeveloped;
	private double normalizedNumberOfTasksDeveloped;
	private ComplexVector complexVector;

	public AllocationInfo() {
		setComplexVector(new ComplexVector());
	}

	public TargetInfo getTargetInfo() {
		return targetInfo;
	}

	public void setTargetInfo(TargetInfo targetInfo) {
		this.targetInfo = targetInfo;
	}

	public Developer getDeveloper() {
		return developer;
	}

	public void setDeveloper(Developer developer) {
		this.developer = developer;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public double getTaskEffortAverage() {
		return taskEffortAverage;
	}

	public void setTaskEffortAverage(double taskEffortAverage) {
		this.taskEffortAverage = taskEffortAverage;
	}

	public int getNumberOfTasksDeveloped() {
		return numberOfTasksDeveloped;
	}

	public void setNumberOfTasksDeveloped(int numberOfTasksDeveloped) {
		this.numberOfTasksDeveloped = numberOfTasksDeveloped;
	}

	public ComplexVector getComplexVector() {
		return complexVector;
	}

	public void setComplexVector(ComplexVector complexVector) {
		this.complexVector = complexVector;
	}

	public double getNormalizedNumberOfTasksDeveloped() {
		return normalizedNumberOfTasksDeveloped;
	}

	public void setNormalizedNumberOfTasksDeveloped(double normalizedNumberOfTasksDeveloped) {
		this.normalizedNumberOfTasksDeveloped = normalizedNumberOfTasksDeveloped;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TARGET: " + targetInfo.getTargetConfiguration()+"_"+targetInfo.getProject().getId() + " | ");
		builder.append("DEVELOPER ID: " + developer.getId() + " | ");
		builder.append("NAME: " + developer.getName() + " | ");
		builder.append("TASK ID: " + task.getId() + " | ");
		builder.append("PERSONALIZED DESCRIPTION: " + task.getPersonalizedDescription() + " | ");
		builder.append("NUMBER OF TASKS DEVELOPED: " + getNumberOfTasksDeveloped() + " | ");
		builder.append("NORMALIZED NUMBER OF TASKS DEVELOPED: " + getNormalizedNumberOfTasksDeveloped() + " | ");
		builder.append("EFFORT AVERAGE: " + getTaskEffortAverage() + " | ");
		builder.append(System.lineSeparator());
		builder.append(getComplexVector().toString());
		builder.append(System.lineSeparator());

		return builder.toString();
	}

}
