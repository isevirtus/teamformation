package util;

import java.util.ArrayList;
import java.util.HashMap;

import project.Tag;
import similarity.TFIDF;
import vector.Vector;

public class VectorUtil {

	public static void fillTaskVector(Vector vector, HashMap<String, Double> weithedTagMap) {
		for (int i = 0; i < vector.getLength(); i++) {
			String tagKey = vector.getAttributeNameArray()[i];
			if (weithedTagMap.containsKey(tagKey) && weithedTagMap.get(tagKey) != 0) {
				vector.getValueArray()[i] = weithedTagMap.get(tagKey) * Vector.DEFAULT_TASK_VECTOR_VALUE;
			} else {
				vector.getValueArray()[i] = Vector.DEFAULT_TASK_VECTOR_VALUE;
			}
		}
	}

	public static void fillDeveloperVector(Vector vector, ArrayList<Tag> tagList, TFIDF tfidf,
			HashMap<String, Double> weithedTagMap) {

		for (int i = 0; i < vector.getLength(); i++) {
			String attributeName = vector.getAttributeNameArray()[i];
			if (TagUtil.containsTag(tagList, attributeName)) {
				int index = TagUtil.getIndexOf(tagList, attributeName);
				double value = tagList.get(index).getValue();
				String tagKey = vector.getAttributeNameArray()[i];
				if (weithedTagMap.isEmpty()) {
					vector.getValueArray()[i] = value;
				} else {
					if (weithedTagMap.containsKey(tagKey)) {
						vector.getValueArray()[i] = weithedTagMap.get(tagKey) * value;

					} else {
						vector.getValueArray()[i] = Vector.DEFAULT_DEVELOPER_VECTOR_VALUE;
					}
				}
			} else {
				vector.getValueArray()[i] = Vector.DEFAULT_DEVELOPER_VECTOR_VALUE;
			}
		}
	}

	public static void fillDeveloperVector2(Vector vector, ArrayList<Tag> tagList, TFIDF tfidf,
			HashMap<String, Double> weithedTagMap) {

		for (int i = 0; i < vector.getLength(); i++) {
			String attributeName = vector.getAttributeNameArray()[i];
			if (TagUtil.containsTag(tagList, attributeName)) {
				int index = TagUtil.getIndexOf(tagList, attributeName);
				double value = tagList.get(index).getValue();
				String tagKey = vector.getAttributeNameArray()[i];
				if (weithedTagMap.containsKey(tagKey)) {
					vector.getValueArray()[i] = weithedTagMap.get(tagKey) * value;
				} else {
					vector.getValueArray()[i] = value;
					// vector.getValueArray()[i] = value *
					// tfidf.getTermWeight(attributeName);
				}
			} else {
				vector.getValueArray()[i] = Vector.DEFAULT_DEVELOPER_VECTOR_VALUE;
			}
		}
	}

	public static double getValue(Vector vector, String key) {
		for (int i = 0; i < vector.getAttributeNameArray().length; i++) {
			if (key.equalsIgnoreCase(vector.getAttributeNameArray()[i])) {
				return vector.getValueArray()[i];
			}
		}
		return 0;
	}

	public static void resetValues(Vector vector) {
		for (int i = 0; i < vector.getLength(); i++) {
			vector.getValueArray()[i] = 0;
		}
	}

	public static Vector sumVectorsValues(Vector vector1, Vector vector2) {
		for (int i = 0; i < vector1.getLength(); i++) {
			vector1.getValueArray()[i] = vector1.getValueArray()[i] + vector2.getValueArray()[i];
		}

		return vector1;
	}

	public static Vector maxVectorsValues(Vector vector1, Vector vector2) {
		for (int i = 0; i < vector1.getLength(); i++) {
			if (vector1.getValueArray()[i] < vector2.getValueArray()[i]) {
				vector1.getValueArray()[i] = vector2.getValueArray()[i];
			}

		}

		return vector1;
	}

	public static Vector didivebyNumber(Vector vector, double number) {
		for (int i = 0; i < vector.getLength(); i++) {
			vector.getValueArray()[i] = vector.getValueArray()[i] / number;
		}
		return vector;
	}
	
	public static double getTagValue(Vector vector, String tagKey) {
		for (int i = 0; i < vector.getLength(); i++) {
			if (vector.getAttributeNameArray()[i].equalsIgnoreCase(tagKey)) {
				return vector.getValueArray()[i];
			}
		}
		return 0;
	}

}
