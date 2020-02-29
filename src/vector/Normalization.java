package vector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

import developer.Developer;
import similarity.AllocationInfo;

public class Normalization {

	public final static double MIN_VALUE = 0.0;
	

//	public static ArrayList<AllocationInfo> normalize(ArrayList<AllocationInfo> similarityItemList) {
//
//		HashMap<String, Double> curriculumMaxValueMap = getMaxValueMap(similarityItemList, VectorType.CURRICULUM);
//		HashMap<String, Double> developmentHistoryMaxValueMap = getMaxValueMap(similarityItemList,
//				VectorType.DEVELOPMENT_HISTORY);
//
//		for (int i = 0; i < similarityItemList.size(); i++) {
//
//			AllocationInfo similarityItem = similarityItemList.get(i);
//
//			Vector taskVector = similarityItem.getTaskVector();
//			Vector curriculumVector = similarityItem.getCurriculumVector();
//			Vector developmentHistoryVector = similarityItem.getDevelopmentHistoryVector();
//
//			for (int j = 0; j < taskVector.getLength(); j++) {
//
//				double curriculumTagValue = curriculumVector.getValueArray()[j];
//				double developmentHistoryTagValue = developmentHistoryVector.getValueArray()[j];
//
//				double minValue = MIN_VALUE;
//
//				String tagKey = curriculumVector.getAttributeNameArray()[j];
//				double maxCurriculumTagValue = curriculumMaxValueMap.get(tagKey);
//				double maxDevelopmentHistoryTagValue = developmentHistoryMaxValueMap.get(tagKey);
//
//				double normalizedcurriculumTagValue = calcNormalizedValue(minValue, maxCurriculumTagValue,
//						curriculumTagValue);
//				double normalizedDevelopmentHistoryTagValue = calcNormalizedValue(minValue,
//						maxDevelopmentHistoryTagValue, developmentHistoryTagValue);
//
//				curriculumVector.getValueArray()[j] = normalizedcurriculumTagValue;
//				developmentHistoryVector.getValueArray()[j] = normalizedDevelopmentHistoryTagValue;
//
//			}
//
//			similarityItem.getKnowledgeVector().fillVector(curriculumVector, developmentHistoryVector);
//		}
//
//		return similarityItemList;
//	}

	public static double calcNormalizedValue(double minValue, double maxValue, double value) {
		double numerator = value - minValue;
		double denominator = maxValue - minValue;

		if (denominator == 0) {
			return 0;
		}
		double normalizedValue = numerator / denominator;

		return normalizedValue;
	}

//	public static HashMap<String, Double> getMaxValueMap(ArrayList<AllocationInfo> similarityItemList,
//			VectorType vectorType) {
//
//		HashMap<String, Double> maxValueMap = new HashMap<>();
//
//		ArrayList<String> tagKeyList = getTagKeyList(similarityItemList);
//
//		for (int i = 0; i < tagKeyList.size(); i++) {
//			maxValueMap.put(tagKeyList.get(i), MIN_VALUE);
//		}
//
//		for (int i = 0; i < tagKeyList.size(); i++) {
//			String tagKey = tagKeyList.get(i);
//			for (int j = 0; j < similarityItemList.size(); j++) {
//				Vector vector;
//				if (vectorType.equals(VectorType.CURRICULUM)) {
//					vector = similarityItemList.get(j).getCurriculumVector();
//				} else {
//					vector = similarityItemList.get(j).getDevelopmentHistoryVector();
//				}
//
//				double tagValue = vector.getTagKeyValue(tagKey);
//
//				if (tagValue > maxValueMap.get(tagKey)) {
//					maxValueMap.put(tagKey, tagValue);
//				}
//
//			}
//		}
//
//		return maxValueMap;
//	}
//
//	public static ArrayList<String> getTagKeyList(ArrayList<AllocationInfo> similarityItemList) {
//		ArrayList<String> tagKeyList = new ArrayList<>();
//		for (int i = 0; i < similarityItemList.size(); i++) {
//			String[] tagKeyArray = similarityItemList.get(i).getTaskVector().getAttributeNameArray();
//			tagKeyList.addAll(Arrays.asList(tagKeyArray));
//		}
//
//		tagKeyList = (ArrayList<String>) tagKeyList.stream().distinct().collect(Collectors.toList());
//
//		return tagKeyList;
//	}

}
