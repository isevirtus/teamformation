package validation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class CountConfigs {

	public static void main(String[] args) throws IOException {
		String originPath = "data/dataset/";
		String filename = "configs.txt";
		File file = new File(originPath + filename);

		BufferedReader br = new BufferedReader(new FileReader(file));

		String line = br.readLine();

		HashMap<String, Integer> map = new HashMap<String, Integer>();

		ArrayList<String> configList = new ArrayList<String>();
		while (line != null) {
			line = line.trim();
			configList.add(line);
			if (!map.containsKey(line)) {
				map.put(line, 0);
			}

			line = br.readLine();
		}

		for (HashMap.Entry<String, Integer> entry : map.entrySet()) {
			String config = entry.getKey();
			int count = Collections.frequency(configList, config);
			map.put(config, count);
			System.out.println(config+"	"+count);
		}
	}

}
