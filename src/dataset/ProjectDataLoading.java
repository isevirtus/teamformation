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
import project.Sprint;
import project.Tag;
import project.Task;
import project.UserStory;
import util.ApachePoiUtil;
import util.ProjectUtil;
import util.SprintUtil;
import util.UserStoryUtil;

public class ProjectDataLoading {

	private static final String PROJECT_ID = "Projeto_ID";
	private static final String PROJECT_NAME = "Projeto";
	private static final String PLATAFORM = "Plataforma";
	private static final String SPRINT_ID = "Sprint_ID";
	private static final String US_ID = "US_ID";
	private static final String TASK_ID = "Tarefa_ID";
	private static final String US_DESCRIPTION = "Descritivo_da_US";
	private static final String MAPPED_TASK = "Tarefa_mapeada";
	private static final String ORIGINAL_TASK = "Tarefa_original";
	private static final String LAYER = "Camada";
	private static final String LANGUAGE = "Linguagem";
	private static final String FRAMEWORK = "Framework";
	private static final String API = "API";
	private static final String PERSISTENCE = "Persistencia";
	private static final String OTHER_TAGS = "Outras_Tags";
	private static final String ESTIMATED_EFFORT = "Esforco_estimado_em_horas";
	private static final String REAL_EFFFORT = "Esforco_em_horas";
	private static final String RESPONSIBLE = "Responsavel";

	public ProjectDataLoading() {

	}

	public static ArrayList<Project> createAllProjectData(String filePath) throws IOException {
		InputStream ExcelFileToRead = new FileInputStream(filePath);
		HSSFWorkbook workbook = new HSSFWorkbook(ExcelFileToRead);

		ArrayList<Project> projectList = new ArrayList<Project>();
		ArrayList<HSSFSheet> sheetList = ApachePoiUtil.getSheetList(workbook);
		for (int i = 0; i < sheetList.size(); i++) {
			Project project = createProjectData(sheetList.get(i));
			projectList.add(project);
		}

		return projectList;
	}

	private static Project createProjectData(HSSFSheet sheet) {
		Project project = null;
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
				String projectId = (String) ApachePoiUtil.getCellValue(row.getCell(columnNameIndexMap.get(PROJECT_ID)),
						true);
				String projectName = (String) ApachePoiUtil
						.getCellValue(row.getCell(columnNameIndexMap.get(PROJECT_NAME)), true);
				String plataform = (String) ApachePoiUtil.getCellValue(row.getCell(columnNameIndexMap.get(PLATAFORM)),
						true);
				String sprintId = (String) ApachePoiUtil.getCellValue(row.getCell(columnNameIndexMap.get(SPRINT_ID)),
						true);
				String userStoryId = (String) ApachePoiUtil.getCellValue(row.getCell(columnNameIndexMap.get(US_ID)),
						true);
				String userStoryDescription = (String) ApachePoiUtil
						.getCellValue(row.getCell(columnNameIndexMap.get(US_DESCRIPTION)), true);
				String taskId = (String) ApachePoiUtil.getCellValue(row.getCell(columnNameIndexMap.get(TASK_ID)), true);
				String mappedTask = (String) ApachePoiUtil
						.getCellValue(row.getCell(columnNameIndexMap.get(MAPPED_TASK)), true);
				String originalTask = (String) ApachePoiUtil
						.getCellValue(row.getCell(columnNameIndexMap.get(ORIGINAL_TASK)), true);
				String layer = (String) ApachePoiUtil.getCellValue(row.getCell(columnNameIndexMap.get(LAYER)), true);
				String language = (String) ApachePoiUtil.getCellValue(row.getCell(columnNameIndexMap.get(LANGUAGE)),
						true);
				String framework = (String) ApachePoiUtil.getCellValue(row.getCell(columnNameIndexMap.get(FRAMEWORK)),
						true);
				String api = (String) ApachePoiUtil.getCellValue(row.getCell(columnNameIndexMap.get(API)), true);
				String persistence = (String) ApachePoiUtil
						.getCellValue(row.getCell(columnNameIndexMap.get(PERSISTENCE)), true);
				String otherTagsString = (String) ApachePoiUtil
						.getCellValue(row.getCell(columnNameIndexMap.get(OTHER_TAGS)), true);

				String estimatedEffortString = (String) ApachePoiUtil
						.getCellValue(row.getCell(columnNameIndexMap.get(ESTIMATED_EFFORT)), true);
				String realEffortString = (String) ApachePoiUtil
						.getCellValue(row.getCell(columnNameIndexMap.get(REAL_EFFFORT)), true);
				String responsible = (String) ApachePoiUtil
						.getCellValue(row.getCell(columnNameIndexMap.get(RESPONSIBLE)), true);

				double estimatedEffort;
				if (estimatedEffortString == null) {
					estimatedEffort = 0.0;
				} else {
					estimatedEffort = Double.valueOf(estimatedEffortString).doubleValue();
				}

				double realEffort;
				if (realEffortString == null) {
					realEffort = 0.0;
				} else {
					realEffort = Double.valueOf(realEffortString).doubleValue();
				}

				String[] otherTags;
				if (otherTagsString == null) {
					otherTags = new String[0];
				} else {
					otherTags = otherTagsString.split(",");
				}

				String uniqueProjectId = projectId;
				String uniqueSprintID = uniqueProjectId + "-" + sprintId;
				String uniqueUserStoryId = uniqueSprintID + "-" + userStoryId;
				String uniqueTaskId = uniqueUserStoryId + "-" + taskId;

				if (project == null) {
					project = new Project(uniqueProjectId);
					project.setName(projectName);
				}

				Sprint sprint = ProjectUtil.getSprint(project, uniqueSprintID);
				if (sprint == null) {
					sprint = new Sprint(uniqueSprintID);
					project.getSprintList().add(sprint);
				}

				UserStory userStory = SprintUtil.getUserStory(sprint, uniqueUserStoryId);
				if (userStory == null) {
					userStory = new UserStory(uniqueUserStoryId);
					userStory.setDescription(userStoryDescription);
					userStory.setPlataform(plataform);
					sprint.getUserStoryList().add(userStory);

				}

				Task task = new Task(uniqueTaskId);
				task.setOriginalDescription(originalTask);
				task.setMappedDescription(mappedTask);
				task.setLayer(layer.trim());
				task.setLanguage(language);
				task.setFramework(framework);
				task.setApi(api);
				task.setPersistence(persistence);
				task.setPlataform(plataform);
				task.setEstimatedEffort(estimatedEffort);
				task.setRealEffort(realEffort);
				task.setDeveloperName(responsible);

				if (responsible == null) {
					System.err.println(
							"ATENTION!!! TASK " + task.getId() + " DOESN'T HAVE A DEVELOPER ASSIGNED!");
				}

				if (task.getPlataform() != null && task.getMappedDescription() != null && task.getLayer() != null) {
					//task.setPersonalizedDescription(task.getMappedDescription().trim());
					task.setPersonalizedDescription(task.getMappedDescription().trim()+"_"+task.getLayer().trim());
				} else {
					System.err.println("Task " + task.getId()
							+ ": coulnd't create personalized description, because one of the attributes is null!");
				}

				// task.addTag(task.getLayer(), 1);
				task.addTag(task.getLanguage(), 1);
				task.addTag(task.getFramework(), 1);
				task.addTag(task.getApi(), 1);
				task.addTag(task.getPersistence(), 1);

				for (int i = 0; i < otherTags.length; i++) {
					task.addTag(otherTags[i].trim(), 1);
				}

				if (task.getTagList().size() > 0) {
					userStory.getTaskList().add(task);
				} else {
					System.err.println(
							"ATENTION!!! TASK " + task.getId() + " WASN'T ADDED, BECAUSE IT DOESN'T HAVE TAGS!");
				}

			}
		}

		return project;
	}

}
