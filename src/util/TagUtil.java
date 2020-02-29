package util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import project.Tag;

public class TagUtil {

	public static boolean containsTag(ArrayList<Tag> tagList, String tagKey) {
		for (int i = 0; i < tagList.size(); i++) {
			if (tagList.get(i).getKey().equalsIgnoreCase(tagKey)) {
				return true;
			}
		}
		return false;
	}

	public static int countTag(ArrayList<Tag> tagList, String tagKey) {
		int count = 0;
		for (int i = 0; i < tagList.size(); i++) {
			if (tagList.get(i).getKey().equalsIgnoreCase(tagKey)) {
				count++;
			}
		}
		return count;
	}

	public static double countWeightedTag(ArrayList<Tag> tagList, HashMap<String, Double> weightedTags) {
		double count = 0;
		for (int i = 0; i < tagList.size(); i++) {
			if (weightedTags.containsKey(tagList.get(i).getKey())) {
				double weight = (weightedTags.get(tagList.get(i).getKey()));
				count = count + weight;
			} else {
				count++;
			}
		}
		return count;
	}

	public static int getIndexOf(ArrayList<Tag> tagList, String tagKey) {
		for (int i = 0; i < tagList.size(); i++) {
			if (tagList.get(i).getKey().equalsIgnoreCase(tagKey)) {
				return i;
			}
		}
		return -1;
	}

	public static ArrayList<String> getUniqueTagKeys(ArrayList<Tag> tagList) {
		ArrayList<String> tagKeysFromTagList = new ArrayList<String>();
		for (int i = 0; i < tagList.size(); i++) {
			String tagKey = tagList.get(i).getKey();
			tagKeysFromTagList.add(tagKey);
		}
		tagKeysFromTagList = (ArrayList<String>) tagKeysFromTagList.stream().distinct().collect(Collectors.toList());
		return tagKeysFromTagList;
	}

	public static ArrayList<Tag> getUniqueTags(ArrayList<Tag> tagList) {
		Set<Tag> set = tagList.stream()
				.collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Tag::getKey))));
		ArrayList<Tag> uniqueTagList = new ArrayList<Tag>(set);

		return uniqueTagList;
	}

	public static String toString(ArrayList<Tag> tagList) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < tagList.size(); i++) {
			builder.append(tagList.get(i).toString());
		}

		return builder.toString();
	}

	public static double getTagValue(ArrayList<Tag> tagList, String tagKey) {
		for (int i = 0; i < tagList.size(); i++) {
			if (tagList.get(i).getKey().equalsIgnoreCase(tagKey)) {
				return tagList.get(i).getValue();
			}
		}
		return 0;
	}
}
