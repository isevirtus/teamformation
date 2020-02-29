package dataset;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import project.Project;
import project.Tag;
import project.TargetInfo;
import util.ApachePoiUtil;
import util.ArrayUtil;
import util.HashMapUtil;
import util.ArrayListUtil;
import validation.Scenario;

public class ScenarioDataLoading {

	private static final String SCENARIO = "Cenario";
	private static final String TARGET_CONFIGURATION = "CONFIGURACAO ALVO";
	private static final String AVAILABLE_DEVELOPERS = "Desenvolvedores para Alocacao";
	private static final String TRAINNING_PROJECTS = "Projetos para Treinamento";
	private static final String TARGET_PROJECT = "Projeto Alvo";
	private static final String FIXED_DEVELOPERS = "Desenvolvedores Fixos";
	private static final String WEIGHTED_TAGS = "Tags Ponderadas";
	private static final String OPTIMAL_SOLUTION = "Solucao Otima";
	private static final String TARGET_SPRINT = "Sprint Alvo";
	private static final String TEAM_SIZE = "Tamanho da Equipe";

	public static ArrayList<Scenario> createAllScenarioData(String filePath) throws IOException {
		InputStream ExcelFileToRead = new FileInputStream(filePath);
		HSSFWorkbook workbook = new HSSFWorkbook(ExcelFileToRead);

		ArrayList<Scenario> scenarioList = new ArrayList<Scenario>();
		ArrayList<HSSFSheet> sheetList = ApachePoiUtil.getSheetList(workbook);

		for (int i = 0; i < sheetList.size(); i++) {
			Scenario scenario = createScenarioData(sheetList.get(i));
			scenarioList.add(scenario);

		}

		return scenarioList;

	}

	private static Scenario createScenarioData(HSSFSheet sheet) {
		HashMap<String, Integer> columnNameIndexMap = ApachePoiUtil.createColumnNameIndexMap(sheet);

		Scenario scenario = null;

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

				String scenarioName = (String) ApachePoiUtil
						.getCellValue(row.getCell(columnNameIndexMap.get(SCENARIO)),true);
				String targetConfiguration = (String) ApachePoiUtil
						.getCellValue(row.getCell(columnNameIndexMap.get(TARGET_CONFIGURATION)),true);
				String avalilableDevelopers = (String) ApachePoiUtil
						.getCellValue(row.getCell(columnNameIndexMap.get(AVAILABLE_DEVELOPERS)),true);
				String trainningProjects = (String) ApachePoiUtil
						.getCellValue(row.getCell(columnNameIndexMap.get(TRAINNING_PROJECTS)),true);
				String targetProject = (String) ApachePoiUtil
						.getCellValue(row.getCell(columnNameIndexMap.get(TARGET_PROJECT)),true);
				String targetSprint = (String) ApachePoiUtil
						.getCellValue(row.getCell(columnNameIndexMap.get(TARGET_SPRINT)),true);
				String teamSizeString = (String) ApachePoiUtil
						.getCellValue(row.getCell(columnNameIndexMap.get(TEAM_SIZE)),true);
				String fixedDevelopersString = (String) ApachePoiUtil
						.getCellValue(row.getCell(columnNameIndexMap.get(FIXED_DEVELOPERS)),true);
				String weightedTags = (String) ApachePoiUtil
						.getCellValue(row.getCell(columnNameIndexMap.get(WEIGHTED_TAGS)),true);
				String optimumSolution = (String) ApachePoiUtil
						.getCellValue(row.getCell(columnNameIndexMap.get(OPTIMAL_SOLUTION)),true);

				
				if(targetConfiguration == null){
					System.err.println("ATENTION!!! NO ID WAS DEFINED!");
				}
				
				String[] avalilableDevelopersArray;
				if (avalilableDevelopers == null) {
					avalilableDevelopersArray = new String[0];
					System.err.println("ATENTION!!! NO AVALIABLE IDS WERE DEFINED!");
				} else {
					avalilableDevelopersArray = avalilableDevelopers.split(",");
				}

				String[] trainningProjectsArray;
				if (trainningProjects == null) {
					trainningProjectsArray = new String[0];
					System.err.println("ATENTION!!! NO TRAINNING PROJECTS IDS WERE DEFINED!");
				} else {
					trainningProjectsArray = trainningProjects.split(",");
				}

				String[] optimalSolutionArray;
				if (optimumSolution == null) {
					optimalSolutionArray = new String[0];
					System.err.println("ATENTION!!! NO OPTIMAL SOLUTION WAS DEFINED!");
				} else {
					optimumSolution = optimumSolution.substring(1, optimumSolution.length() - 1);
					optimalSolutionArray = optimumSolution.split(",");
				}
				
				
				String[] fixedDevelopersArray;
				if (fixedDevelopersString == null) {
					fixedDevelopersArray = new String[0];
					System.err.println("ATENTION!!! NO FIXED DEVELOPER IDS WERE DEFINED!");
				} else {
					fixedDevelopersArray = fixedDevelopersString.split(",");
				}

				String[] weightedTagsArray;
				HashMap<String, Double> weightedTagMap = new HashMap<String, Double>();
				if (weightedTags == null) {
					weightedTagsArray = new String[0];
					System.err.println("ATENTION!!! NO WEIGHTED TAGS WERE DEFINED!");
				} else {
					weightedTagsArray = weightedTags.split(",");
					for (int i = 0; i < weightedTagsArray.length; i++) {
						String[] tagArray = weightedTagsArray[i].split("_");
						String tagKey = tagArray[0];
						String tagWeight = tagArray[1];
						weightedTagMap.put(tagKey, Double.valueOf(tagWeight));
					}
				}

				//System.out.println("weightedTagMap: " + HashMapUtil.toStringDouble(weightedTagMap));

				if (scenario == null) {
					scenario = new Scenario(scenarioName);
					scenario.setTargetDeveloperIdList(ArrayUtil.arrayToList(avalilableDevelopersArray));
					scenario.setTrainningProjectIdList(ArrayUtil.arrayToList(trainningProjectsArray));
					scenario.setOptimalSolution(ArrayUtil.arrayToList(optimalSolutionArray));
				}

				int teamSize = Double.valueOf(teamSizeString).intValue();

				TargetInfo targetInfo = new TargetInfo();
				targetInfo.setTargetConfiguration(targetConfiguration);
				targetInfo.setProjectId(targetProject);
				targetInfo.setSprintId(targetSprint);
				targetInfo.setTeamSize(teamSize);
				targetInfo.setWeithedTagMap(weightedTagMap);
				targetInfo.setFixedDeveloperIdList(ArrayUtil.arrayToList(fixedDevelopersArray));

				scenario.getTargetInfoList().add(targetInfo);
			}

		}

		return scenario;

	}

//	public static void main(String[] args) {
//
//		try {
//			ArrayList<Scenario> scenarioList = ScenarioDataLoading
//					.createAllScenarioData("data/dataset/new_cenario.xls");
//			for (int i = 0; i < scenarioList.size(); i++) {
//				System.out.println(scenarioList.get(i).getName());
//				System.out.println(ArrayListUtil.toString(scenarioList.get(i).getOptimalSolution()));
//				System.out.println(scenarioList.get(i).getTargetInfoList());
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

}
