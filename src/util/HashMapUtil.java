package util;

import java.util.HashMap;

public class HashMapUtil {

	public static String toStringStringDouble(HashMap<String, Double> map) {
		StringBuilder builder = new StringBuilder();
		for (HashMap.Entry<String, Double> entry : map.entrySet()) {
			builder.append("[" + entry.getKey() + " = " + entry.getValue() + "]");
		}

		return builder.toString();
	}
	
	public static String toStringStringIntegerArray(HashMap<String, Integer[]> map) {
		StringBuilder builder = new StringBuilder();
		for (HashMap.Entry<String, Integer[]> entry : map.entrySet()) {
			builder.append("[" + entry.getKey() + ": Start = " + entry.getValue()[0] +" End = "+ entry.getValue()[1] + "]");
		}

		return builder.toString();
	}

	public static double getValueAverage(HashMap<String, Double> map) {

		double sum = 0;
		int count = 0;
		for (HashMap.Entry<String, Double> entry : map.entrySet()) {
			sum += entry.getValue();
			count++;
		}
		double average = sum / count;
		return average;

	}

	public static String toStringInteger(HashMap<String, Integer> map) {
		StringBuilder builder = new StringBuilder();
		for (HashMap.Entry<String, Integer> entry : map.entrySet()) {
			builder.append("[" + entry.getKey() + " = " + entry.getValue() + "]");
		}

		return builder.toString();
	}
}
