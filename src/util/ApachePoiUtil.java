package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

public class ApachePoiUtil {

	public static String getCellValue(Cell cell, boolean toLowerCase) {

		if (cell == null || cell.getCellTypeEnum() == CellType.BLANK || StringUtils.isBlank(cell.toString())) {
			return null;
		}
		if(toLowerCase){
			return cell.toString().trim().toLowerCase();
		}else{
			return cell.toString().trim();
		}
	}

	public static boolean checkIfRowIsEmpty(HSSFRow row) {
		if (row == null) {
			return true;
		}
		if (row.getLastCellNum() <= 0) {
			return true;
		}
		for (int cellNum = row.getFirstCellNum(); cellNum < row.getLastCellNum(); cellNum++) {
			Cell cell = row.getCell(cellNum);
			if (cell != null && cell.getCellTypeEnum() != CellType.BLANK && !StringUtils.isBlank(cell.toString())) {
				return false;
			}
		}
		return true;
	}

	public static ArrayList<HSSFSheet> getSheetList(HSSFWorkbook workbook) {
		ArrayList<HSSFSheet> sheetList = new ArrayList<HSSFSheet>();
		for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
			sheetList.add(workbook.getSheetAt(i));
		}
		return sheetList;
	}

	public static HashMap<String, Integer> createColumnNameIndexMap(HSSFSheet sheet) {
		HashMap<String, Integer> columnNameIndexMap = new HashMap<String, Integer>();
		HSSFRow row;
		Iterator rows = sheet.rowIterator();
		if (rows.hasNext()) {
			row = (HSSFRow) rows.next();
			Iterator cells = row.cellIterator();

			while (cells.hasNext()) {
				HSSFCell cell = (HSSFCell) cells.next();
				int columnIndex = cell.getColumnIndex();
				if (getCellValue(row.getCell(columnIndex),false) != null) {
					String columnName = (String) getCellValue(row.getCell(columnIndex),false);
					columnNameIndexMap.put(columnName, columnIndex);
				}
			}
		}
		return columnNameIndexMap;
	}
}
