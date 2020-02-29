package validation;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class SaveResultsInExcel {

	public static void saveParamters(String filename, ArrayList<Parameter> parameterList) throws IOException {

		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet(parameterList.get(0).getConfigurationId());

		HSSFRow rowhead = sheet.createRow(0);
		rowhead.createCell(0).setCellValue("Configuration");
		rowhead.createCell(1).setCellValue("Distance Measure");
		rowhead.createCell(2).setCellValue("Fitness Function");
		rowhead.createCell(3).setCellValue("Optimal Solution");
		rowhead.createCell(4).setCellValue("Found Solution");
		rowhead.createCell(5).setCellValue("Offspring Selector");
		rowhead.createCell(6).setCellValue("Suvivor Selector");
		rowhead.createCell(7).setCellValue("Crossover Rate");
		rowhead.createCell(8).setCellValue("Mutation Rate");
		rowhead.createCell(9).setCellValue("Population Size");
		rowhead.createCell(10).setCellValue("Steady Fitness");
		rowhead.createCell(11).setCellValue("Origin Generation");
		rowhead.createCell(12).setCellValue("Generation Count");
		rowhead.createCell(13).setCellValue("Fitness Value");
		rowhead.createCell(14).setCellValue("Match Rate");
		rowhead.createCell(15).setCellValue("Time");

		for (int i = 0; i < parameterList.size(); i++) {
			Parameter parameter = parameterList.get(i);
			HSSFRow row = sheet.createRow(i + 1);
			row.createCell(0).setCellValue(parameter.getConfigurationId());
			row.createCell(1).setCellValue(parameter.getDistanceMeasure());
			row.createCell(2).setCellValue(parameter.getfitnessFunctionType());
			row.createCell(3).setCellValue(parameter.getOptimalSolution());
			row.createCell(4).setCellValue(parameter.getFoundSolution());
			row.createCell(5).setCellValue(parameter.getOffspringSelector());
			row.createCell(6).setCellValue(parameter.getSurvivorsSelector());
			row.createCell(7).setCellValue(Double.valueOf(parameter.getCrossOverRate()));
			row.createCell(8).setCellValue(Double.valueOf(parameter.getMutationRate()));
			row.createCell(9).setCellValue(Integer.valueOf(parameter.getPopulationSize()));
			row.createCell(10).setCellValue(Integer.valueOf(parameter.getSteadyFitness()));
			row.createCell(11).setCellValue(Integer.valueOf(parameter.getOriginGeneration()));
			row.createCell(12).setCellValue(Integer.valueOf(parameter.getGenerationCount()));
			row.createCell(13).setCellValue(Double.valueOf(parameter.getFitnessValue()));
			row.createCell(14).setCellValue(Double.valueOf(parameter.getMatchRate()));
			row.createCell(15).setCellValue(Double.valueOf(parameter.getTime()));
		}

		for (int i = 0; i < rowhead.getLastCellNum(); i++) {
			sheet.autoSizeColumn(i);
		}

		FileOutputStream fileOut = new FileOutputStream(filename);
		workbook.write(fileOut);
		fileOut.close();
		workbook.close();
		System.out.println("Your excel file with stats has been generated!");
	}

	public static void saveAverageStats(String filename, ArrayList<AverageStats> averageStatsList)
			throws IOException {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet(averageStatsList.get(0).getConfigurationId());

		HSSFRow rowhead = sheet.createRow((int) 0);
		rowhead.createCell(0).setCellValue("Configuration");
		rowhead.createCell(1).setCellValue("Population Size");
		rowhead.createCell(2).setCellValue("Steady Fitness");
		rowhead.createCell(3).setCellValue("Origin Generation");
		rowhead.createCell(4).setCellValue("Generation Count");
		rowhead.createCell(5).setCellValue("Match Rate Average");
		rowhead.createCell(6).setCellValue("Time Average");

		for (int i = 0; i < averageStatsList.size(); i++) {
			AverageStats averageStats = averageStatsList.get(i);
			HSSFRow row = sheet.createRow((int) i + 1);
			row.createCell(0).setCellValue(averageStats.getConfigurationId());
			row.createCell(1).setCellValue(Double.valueOf(averageStats.getPopulationSize()));
			row.createCell(2).setCellValue(Double.valueOf(averageStats.getSteadyFitness()));
			row.createCell(3).setCellValue(Double.valueOf(averageStats.getOriginGenerationAverage()));
			row.createCell(4).setCellValue(Double.valueOf(averageStats.getGenerationCount()));
			row.createCell(5).setCellValue(Double.valueOf(averageStats.getMatchRateAverage()));
			row.createCell(6).setCellValue(Double.valueOf(averageStats.getTimeAverage()));
		}
		for (int i = 0; i < rowhead.getLastCellNum(); i++) {
			sheet.autoSizeColumn(i);
		}
		FileOutputStream fileOut = new FileOutputStream(filename);
		workbook.write(fileOut);
		fileOut.close();
		workbook.close();
		System.out.println("Your excel file with medians has been generated!");

	}

}
