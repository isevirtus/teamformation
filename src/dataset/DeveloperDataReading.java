package dataset;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import developer.Developer;
import project.Tag;
import util.ApachePoiUtil;

public class DeveloperDataReading {

	private static final String ID = "ID";
	private static final String NAME = "Nome";
	private static final String EXPERIENCE_LEVEL = "Nivel";

	public DeveloperDataReading() {

	}

	public static ArrayList<Developer> createAllDevelopertData(String filePath) throws IOException {
		InputStream ExcelFileToRead = new FileInputStream(filePath);
		HSSFWorkbook workbook = new HSSFWorkbook(ExcelFileToRead);
		ArrayList<Developer> developerList = createDevelopertData(workbook.getSheetAt(0));
		return developerList;
	}

	private static ArrayList<Developer> createDevelopertData(HSSFSheet sheet) {
		ArrayList<Developer> developerList = new ArrayList<Developer>();
		HashMap<String, Integer> columnNameIndexMap = ApachePoiUtil.createColumnNameIndexMap(sheet);

		HSSFRow row;
		Iterator rows = sheet.rowIterator();
		while (rows.hasNext()) {
			row = (HSSFRow) rows.next();
			// jumps the first row, which has the column names
			int firstRow = 0;
			if (row.getRowNum() == firstRow) {
				row = (HSSFRow) rows.next();
			}

			if (!ApachePoiUtil.checkIfRowIsEmpty(row)) {

				String developerId = (String) ApachePoiUtil.getCellValue(row.getCell(columnNameIndexMap.get(ID)),true);
				String developerName = (String) ApachePoiUtil.getCellValue(row.getCell(columnNameIndexMap.get(NAME)),true);
				String experienceLevel = (String) ApachePoiUtil.getCellValue(row.getCell(columnNameIndexMap.get(EXPERIENCE_LEVEL)),true);
				Developer developer = new Developer(developerId, developerName);
				developer.setExperienceLevel(experienceLevel);

				for (Map.Entry<String, Integer> entry : columnNameIndexMap.entrySet()) {
					String columnName = entry.getKey();
					String columnValue = (String) ApachePoiUtil
							.getCellValue(row.getCell(columnNameIndexMap.get(columnName)),true);

					if (!columnName.equalsIgnoreCase(ID) && !columnName.equalsIgnoreCase(NAME) && !columnName.equalsIgnoreCase(EXPERIENCE_LEVEL)) {
						if (columnValue != null && !columnValue.equalsIgnoreCase("null")
								&& !columnValue.equalsIgnoreCase("")) {
							developer.getDeveloperProfile().getCurriculumSource().getCurriculumTagList()
									.add(new Tag(columnName, Double.valueOf(columnValue)));
						}
					}
				}
				developerList.add(developer);
			}
		}

		return developerList;
	}

}
