package vector;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

import io.jenetics.internal.collection.Array;
import project.Project;
import project.Tag;
import project.Task;
import similarity.TFIDF;
import util.ArrayListUtil;
import util.TagUtil;
import util.TaskUtil;
import util.VectorUtil;

public class Vector implements Serializable {

	public static double DEFAULT_DEVELOPER_VECTOR_VALUE = 0.0;
	public static double DEFAULT_TASK_VECTOR_VALUE = 1.0;
	private String[] attributeNameArray;
	private int length;
	private double[] valueArray;
	private VectorType vectorType;

	public Vector(Task task, VectorType vectorType) {
		ArrayList<Tag> taskTagList = task.getTagList();
		ArrayList<String> tagKeys = TagUtil.getUniqueTagKeys(taskTagList);
		setLength(tagKeys.size());
		setAttributeNameArray(tagKeys.toArray(new String[tagKeys.size()]));
		setValueArray(new double[tagKeys.size()]);
		setVectorType(vectorType);
	}

	public void fillVector(ArrayList<Tag> tagList, TFIDF tfidf, HashMap<String,Double> weithedTagMap) {

		switch (this.vectorType) {
		case DEVELOPMENT_HISTORY:
			VectorUtil.fillDeveloperVector(this, tagList, tfidf,weithedTagMap);
			break;
		case CURRICULUM:
			VectorUtil.fillDeveloperVector(this, tagList, tfidf,weithedTagMap);
			break;
		case KNOWLEDGE:
			break;
		case TASK:
			VectorUtil.fillTaskVector(this,weithedTagMap);
			break;
		default:
			break;
		}

	}

	public String[] getAttributeNameArray() {
		return attributeNameArray;
	}

	public int getLength() {
		return length;
	}

	public double[] getValueArray() {
		return valueArray;
	}

	public VectorType getVectorType() {
		return vectorType;
	}

	public void setAttributeNameArray(String[] attributeNameArray) {
		this.attributeNameArray = attributeNameArray;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public void setValueArray(double[] valueArray) {
		this.valueArray = valueArray;
	}

	public void setVectorType(VectorType vectorType) {
		this.vectorType = vectorType;
	}

	public String toString() {

		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < this.length; i++) {
			builder.append(" [");
			builder.append(this.attributeNameArray[i]);
			builder.append(" = ");
			builder.append(this.valueArray[i]);
			builder.append("] ");

		}
		return builder.toString();
	}
}
