package validation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class ReduceFileResult {

	public static void main(String[] args) throws IOException {
		
		String originPath = "data/results/";
		String filename = "best_individuals_01.csv";
		File file = new File(originPath+filename);

		BufferedReader br = new BufferedReader(new FileReader(file));

		String line = br.readLine();
		String head = line;
		line = br.readLine();

		HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();

		while (line != null) {
			//System.out.println(line);
			String[] attributeArray = line.split(";");
			if (!map.containsKey(attributeArray[0])) {
				map.put(attributeArray[0], new ArrayList<String>());
			}
			map.get(attributeArray[0]).add(line.replace(".", ","));
			line = br.readLine();
		}
		
//		for (HashMap.Entry<String, ArrayList<String>> entry : map.entrySet()) {
//			System.out.println("[" + entry.getKey() + " = " + entry.getValue() + "]");
//		}
//		
		int maxSize = 30;
		ArrayList<String> newLines = new ArrayList<String>();
		for (HashMap.Entry<String, ArrayList<String>> entry : map.entrySet()) {
			ArrayList<String> lines = entry.getValue();
			Collections.sort(lines);
			newLines.addAll(lines.subList(0, maxSize));
		}
		
		StringBuilder builder = new StringBuilder();
		builder.append(head);
		builder.append(System.lineSeparator());
		Collections.sort(newLines);
		for(int i =0;i<newLines.size();i++){
			builder.append(newLines.get(i));
			builder.append(System.lineSeparator());
		}
		
		String destinePath = "data/results/new/";
		PrintWriter pw = new PrintWriter(new File((destinePath+filename)));
		pw.write(builder.toString());
		pw.close();
		
	}

}
