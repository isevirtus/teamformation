package util;

import java.util.ArrayList;

public class ArrayUtil {

	public static String toString(Object[] array) {
		String s = "|";
		for (int i = 0; i < array.length; i++) {
			s += array[i] + "|";
		}
		return s;
	}
	
	public static String doubleToString(double[] array) {
		String s = "|";
		for (int i = 0; i < array.length; i++) {
			s += array[i] + "|";
		}
		return s;
	}

	public static double sum(double[] array) {
		double sum = 0;
		for (int i = 0; i < array.length; i++) {
			sum += array[i];
		}
		return sum;
	}

	public static double mean(double[] array) {
		double sum = sum(array);
		double mean = sum / array.length;
		return mean;

	}

	public static ArrayList<String> arrayToList(String[] array) {
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < array.length; i++) {
			list.add(array[i]);
		}
		return list;
	}

}
