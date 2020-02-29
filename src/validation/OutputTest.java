package validation;

import java.io.IOException;
import java.util.ArrayList;

import dataset.Dataset;
import dataset.DeveloperDataReading;
import dataset.ProjectDataLoading;
import dataset.ScenarioDataLoading;
import developer.Developer;
import project.Project;
import similarity.Allocation;
import util.AllocationUtil;

public class OutputTest {

	public static void main(String[] args) {
		// Create dataset from excel files
		String projectsFilePath = "data/dataset/test/output_test_projetos.xls";
		ArrayList<Project> projectList = null;

		String developersFilePath = "data/dataset/test/output_test_desenvolvedores.xls";
		ArrayList<Developer> developerList = null;

		String scenariosFilePath = "data/dataset/test/output_test_cenarios.xls";
		ArrayList<Scenario> scenarioList = null;
		
	

		try {
			projectList = ProjectDataLoading.createAllProjectData(projectsFilePath);
			developerList = DeveloperDataReading.createAllDevelopertData(developersFilePath);
			scenarioList = ScenarioDataLoading.createAllScenarioData(scenariosFilePath);
		} catch (IOException e) {
			e.printStackTrace();
		}

		Dataset dataset = new Dataset();
		dataset.setDeveloperList(developerList);
		dataset.setProjectList(projectList);
		
		scenarioList.get(0).createScenarioData(dataset);
		Allocation allocation = new Allocation(scenarioList.get(0));
		
//		System.out.println("PRINT SCENARIO");
//		System.out.println(allocation.getScenario().toString());
//		System.out.println();
//		System.out.println(">>> PRINT TFIDF VALUES <<<");
//		System.out.println(allocation.getTfidf().toString());
//		System.out.println();
//		System.out.println(">>> PRINT ALLOCATION INFO LIST <<<");
//		System.out.println(AllocationUtil.toString(allocation.getAllocationInfoList()));
		

	
	}

}
