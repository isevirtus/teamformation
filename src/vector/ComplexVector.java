package vector;

import java.io.Serializable;

public class ComplexVector implements Serializable {

	private Vector taskVector;
	private Vector curriculumVector;
	private Vector developmentHistoryVector;
	private Vector knowledgeVector;

	private double curriculumSimilarity;
	private double developmentHistorySimilarity;
	private double knowledgeSimilarity;

	public ComplexVector() {

	}

	public Vector getTaskVector() {
		return taskVector;
	}

	public void setTaskVector(Vector taskVector) {
		this.taskVector = taskVector;
	}

	public Vector getCurriculumVector() {
		return curriculumVector;
	}

	public void setCurriculumVector(Vector curriculumVector) {
		this.curriculumVector = curriculumVector;
	}

	public Vector getDevelopmentHistoryVector() {
		return developmentHistoryVector;
	}

	public void setDevelopmentHistoryVector(Vector developmentHistoryVector) {
		this.developmentHistoryVector = developmentHistoryVector;
	}

	public Vector getKnowledgeVector() {
		return knowledgeVector;
	}

	public void setKnowledgeVector(Vector knowledgeVector) {
		this.knowledgeVector = knowledgeVector;
	}

	public double getCurriculumSimilarity() {
		return curriculumSimilarity;
	}

	public void setCurriculumSimilarity(double curriculumSimilarity) {
		this.curriculumSimilarity = curriculumSimilarity;
	}

	public double getDevelopmentHistorySimilarity() {
		return developmentHistorySimilarity;
	}

	public void setDevelopmentHistorySimilarity(double developmentHistorySimilarity) {
		this.developmentHistorySimilarity = developmentHistorySimilarity;
	}

	public double getKnowledgeSimilarity() {
		return knowledgeSimilarity;
	}

	public void setKnowledgeSimilarity(double knowledgeSimilarity) {
		this.knowledgeSimilarity = knowledgeSimilarity;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TASK VECTOR: " + getTaskVector().toString());
		builder.append(System.lineSeparator());
		// builder.append("CURRICULUM VECTOR:
		// "+getCurriculumVector().toString()+ " | "+"Similarity =
		// "+getCurriculumSimilarity());
		builder.append("DEVELOPMENT HISTORY VECTOR: " + getDevelopmentHistoryVector().toString() + " (SIMILARITY = "
				+ getDevelopmentHistorySimilarity() + ")");

		return builder.toString();
	}

}
