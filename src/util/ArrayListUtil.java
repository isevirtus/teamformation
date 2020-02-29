package util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.SerializationUtils;

import io.jenetics.util.ISeq;

public class ArrayListUtil {

	public static String toString(ArrayList<String> list) {
		StringBuilder builder = new StringBuilder();
		builder.append("{");
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) != null) {
				builder.append(list.get(i).toString() + ",");
			} else {
				builder.append("null" + ",");
			}
		}
		builder.delete(builder.length() - 1, builder.length());
		builder.append("}");
		return builder.toString();
	}

	public static boolean containDuplicate(List list) {
		for (int i = 0; i < list.size(); i++) {
			String element = list.get(i).toString();
			for (int j = 0; j < list.size(); j++) {
				String otherElement = list.get(j).toString();
				if (element.equalsIgnoreCase(otherElement) && i != j) {
					return true;
				}
			}
		}
		return false;
	}

	public static void sort(ArrayList<String> list) {
		Collections.sort(list);
	}

	public static ArrayList<String> iSeqToList(ISeq iseq) {
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < iseq.length(); i++) {
			list.add(iseq.get(i).toString());
		}

		return list;

	}

	// public static ArrayList<String> removeFromList(ArrayList<String>
	// originalList, ArrayList<String> elementsForRemoval) {
	// ArrayList<String> list = SerializationUtils.clone(originalList);
	// for (int i = 0; i < elementsForRemoval.size(); i++) {
	// if (list.contains(elementsForRemoval.get(i))) {
	// list.remove(elementsForRemoval.get(i));
	// }
	// }
	// return list;
	// }

}
