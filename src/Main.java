import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import javax.swing.plaf.synth.SynthSpinnerUI;

import dataset.Dataset;
import dataset.DeveloperDataReading;
import dataset.ProjectDataLoading;
import dataset.ScenarioDataLoading;
import developer.Developer;
import geneticAlgorithm.GaResult;
import geneticAlgorithm.GeneticAlgorithm;
import io.jenetics.util.ISeq;
import project.Project;
import similarity.Allocation;
import similarity.SimilarityMetric;
import util.AllocationUtil;
import util.ArrayListUtil;
import util.ChromosomeUtil;
import util.HashMapUtil;
import validation.AverageStats;
import validation.Parameter;
import validation.SaveResultsInCSV;
import validation.SaveResultsInExcel;
import validation.Scenario;
import validation.TestParameters;

public class Main {

	public static void main(String[] args) {
		
		
		String developersFilePath = "data/dataset/desenvolvedores.xls";
		ArrayList<Developer> developerList = null;
		
//		String projectsFilePath = "data/dataset/felipe/projetos-felipe.xls";
//		ArrayList<Project> projectList = null;
//		//String scenariosFilePath = "data/dataset/felipe/cenarios_best-individual-felipe.xls";
//		//String scenariosFilePath = "data/dataset/felipe/cenarios_specialist-felipe.xls";
//		//String scenariosFilePath = "data/dataset/felipe/cenarios_collaborative-felipe.xls";
//		String scenariosFilePath = "data/dataset/felipe/cenarios_fixed-dev-felipe.xls";
//		ArrayList<Scenario> scenarioList = null;
		
//		String projectsFilePath = "data/dataset/emanuel/projetos-emanuel.xls";
//		ArrayList<Project> projectList = null;
//		//String scenariosFilePath = "data/dataset/emanuel/cenarios_best-individual-emanuel.xls";
//		//String scenariosFilePath = "data/dataset/emanuel/cenarios_specialist-emanuel.xls";
//		//String scenariosFilePath = "data/dataset/emanuel/cenarios_collaborative-emanuel.xls";
//		String scenariosFilePath = "data/dataset/emanuel/cenarios_fixed-dev-emanuel.xls";
//		ArrayList<Scenario> scenarioList = null;
		
		
//		String projectsFilePath = "data/dataset/joao/projetos-joao.xls";
//		ArrayList<Project> projectList = null;
//		String scenariosFilePath = "data/dataset/joao/cenarios_best-individual-joao.xls";
//		//String scenariosFilePath = "data/dataset/joao/cenarios_specialist-joao.xls";
//		//String scenariosFilePath = "data/dataset/joao/cenarios_collaborative-joao.xls";
//		//String scenariosFilePath = "data/dataset/joao/cenarios_fixed-dev-joao.xls";
//		ArrayList<Scenario> scenarioList = null;
		
		
//		String projectsFilePath = "data/dataset/mirko/projetos-mirko.xls";
//		ArrayList<Project> projectList = null;
//		//String scenariosFilePath = "data/dataset/mirko/cenarios_best-individual-mirko.xls";
//		//String scenariosFilePath = "data/dataset/mirko/cenarios_specialist-mirko.xls";
//		//String scenariosFilePath = "data/dataset/mirko/cenarios_collaborative-mirko.xls";
//		String scenariosFilePath = "data/dataset/mirko/cenarios_fixed-dev-mirko.xls";
//		ArrayList<Scenario> scenarioList = null;
		
		
		String projectsFilePath = "data/dataset/todos/projetos-todos.xls";
		ArrayList<Project> projectList = null;
		String scenariosFilePath = "data/dataset/todos/cenarios_best-individual-todos.xls";
		ArrayList<Scenario> scenarioList = null;
		
		
		
//		String projectsFilePath = "data/dataset/teste_projetos.xls";
//		ArrayList<Project> projectList = null;
		
//		String developersFilePath = "data/dataset/desenvolvedores.xls";
//		ArrayList<Developer> developerList = null;
//		String scenariosFilePath = "data/dataset/cenarios_best-individual.xls";
//		ArrayList<Scenario> scenarioList = null;
		

//		String developersFilePath = "data/dataset/desenvolvedores.xls";
//		ArrayList<Developer> developerList = null;
//		String scenariosFilePath = "data/dataset/cenarios_specialist.xls";
//		ArrayList<Scenario> scenarioList = null;
		

//		String developersFilePath = "data/dataset/desenvolvedores.xls";
//		ArrayList<Developer> developerList = null;
//		String scenariosFilePath = "data/dataset/cenarios_collaborative.xls";
//		ArrayList<Scenario> scenarioList = null;
		

//		String developersFilePath = "data/dataset/desenvolvedores.xls";
//		ArrayList<Developer> developerList = null;
//		String scenariosFilePath = "data/dataset/cenarios_fixed-dev.xls";
//		ArrayList<Scenario> scenarioList = null;
		

		try {
			projectList = ProjectDataLoading.createAllProjectData(projectsFilePath);
			System.out.println(">>>>>> Project data loaded! <<<<<<");
			developerList = DeveloperDataReading.createAllDevelopertData(developersFilePath);
			System.out.println(">>>>>> Developer data loaded! <<<<<<");
			scenarioList = ScenarioDataLoading.createAllScenarioData(scenariosFilePath);
			System.out.println(">>>>>> Scenario data loaded! <<<<<<");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Load data in dataset object
		Dataset dataset = new Dataset();
		dataset.setDeveloperList(developerList);
		dataset.setProjectList(projectList);

		// Create scenario data from dataset
		for (int z = 0; z < scenarioList.size(); z++) {
			Scenario scenario = scenarioList.get(z);
			scenario.createScenarioData(dataset);
			Allocation allocation = new Allocation(scenario);

			GeneticAlgorithm ga = new GeneticAlgorithm(allocation);
			// ga.run();

			ArrayList<Parameter> parameterList = new ArrayList<Parameter>();
			ArrayList<AverageStats> averageStatsList = new ArrayList<AverageStats>();

			int numberOfExecutions = 10;
			int count = 1;
			int executionIndex = 1;
			for (int i = 0; i < TestParameters.crossOverRate.length; i++) {
				for (int j = 0; j < TestParameters.distanceMeasure.length; j++) {
					//for (int k = 0; k < TestParameters.fitnessFunctionType.length; k++) {
						for (int l = 0; l < TestParameters.mutationRate.length; l++) {
							for (int m = 0; m < TestParameters.offspringSelector.length; m++) {
								for (int n = 0; n < TestParameters.populationSize.length; n++) {
									for (int o = 0; o < TestParameters.steadyFitness.length; o++) {
										for (int p = 0; p < TestParameters.survivorsSelector.length; p++) {

											String configurationId = scenario.getName() + "_"
													+ Main.transformCount(count);
											double timeSum = 0;
											double mathRateSum = 0;
											double originGenerationSum = 0;
											double generationCountSum = 0;
											double populationSizeSum = 0;
											double steadyFitnessSum = 0;
											for (int t = 0; t < numberOfExecutions; t++) {
												SimilarityMetric.getInstance()
														.setDistanceMeasure(TestParameters.distanceMeasure[j]);
												AllocationUtil.buildVectorSimilarities(
														allocation.getAllocationInfoList(),
														SimilarityMetric.getInstance());

												GaResult gaResult = ga.run(TestParameters.crossOverRate[i],
														TestParameters.mutationRate[l],
														TestParameters.offspringSelector[m],
														TestParameters.populationSize[n],
														TestParameters.steadyFitness[o],
														TestParameters.survivorsSelector[p]);
												double time = gaResult.getStatistics().getEvolveDuration().getSum();
//												System.out.println(
//														"Execution " + executionIndex + ": " + configurationId);

												ArrayList<String> optimalSolution = allocation.getScenario()
														.getOptimalSolution();

												HashMap<String, Double> projectMatchRate = ChromosomeUtil
														.createProjectsMathRate(ga.getChromosomeMapping(),
																optimalSolution, gaResult.getFoundSolution());
												String projectMatchRateString = HashMapUtil
														.toStringStringDouble(projectMatchRate);
											
												double projectmatchRate = HashMapUtil.getValueAverage(projectMatchRate);
									
												System.out.println(ArrayListUtil.toString((gaResult.getFoundSolution())));
												System.out.println("Match Rate = "+projectmatchRate);

												Parameter parameter = new Parameter();
												parameter.setCrossOverRate(
														String.valueOf(TestParameters.crossOverRate[i]));
												parameter.setDistanceMeasure(String.valueOf(
														TestParameters.distanceMeasure[j].getClass().getSimpleName()));
//												parameter.setfitnessFunctionType(
//														String.valueOf(TestParameters.fitnessFunctionType[k]));
												parameter.setMutationRate(
														String.valueOf(TestParameters.mutationRate[l]));
												parameter.setOffspringSelector(
														String.valueOf(TestParameters.offspringSelector[m].getClass()
																.getSimpleName()));
												parameter.setPopulationSize(
														String.valueOf(TestParameters.populationSize[n]));
												parameter.setSteadyFitness(
														String.valueOf(TestParameters.steadyFitness[o]));

												if (TestParameters.survivorsSelector[p] == null) {
													parameter.setSurvivorsSelector("null");
												} else {
													parameter.setSurvivorsSelector(
															String.valueOf(TestParameters.survivorsSelector[p]
																	.getClass().getSimpleName()));
												}
												parameter.setOptimalSolution(ArrayListUtil.toString(optimalSolution));
												parameter.setFoundSolution(ArrayListUtil.toString(gaResult.getFoundSolution()));
												parameter.setFitnessValue(gaResult.getBest().getFitness().toString());
												parameter.setMatchRate(String.valueOf(projectmatchRate));
												parameter.setOriginGeneration(
														String.valueOf(gaResult.getBest().getGeneration()));
												parameter.setGenerationCount(String.valueOf(
														gaResult.getStatistics().getEvaluationDuration().getCount()));
												parameter.setTime(String.valueOf(time));
												parameter.setConfigurationId(configurationId);

												parameterList.add(parameter);
												timeSum += time;
												mathRateSum += projectmatchRate;
												originGenerationSum += gaResult.getBest().getGeneration();
												populationSizeSum += TestParameters.populationSize[n];
												generationCountSum += gaResult.getStatistics().getEvaluationDuration()
														.getCount();
												steadyFitnessSum += TestParameters.steadyFitness[o];

												executionIndex++;

											}
											double timeAverage = timeSum / numberOfExecutions;
											double matchRateAverage = mathRateSum / numberOfExecutions;
											double originGenerationAverage = originGenerationSum / numberOfExecutions;
											double generationCountAverage = generationCountSum / numberOfExecutions;
											double populationSizeAverage = populationSizeSum / numberOfExecutions;
											double steadyFitnessAverage = steadyFitnessSum / numberOfExecutions;
											AverageStats averageStats = new AverageStats();
											averageStats.setConfigurationId(configurationId);
											averageStats.setTimeAverage(timeAverage);
											averageStats.setMatchRateAverage(matchRateAverage);
											averageStats.setOriginGenerationAverage(originGenerationAverage);
											averageStats.setGenerationCount(generationCountAverage);
											averageStats.setPopulationSize(populationSizeAverage);
											averageStats.setSteadyFitness(steadyFitnessAverage);

											averageStatsList.add(averageStats);

											count++;
										}

									}
								}
							
						}
					}
				}
			}

			// System.out.println("Paramter List Size: " + parameterList.size());

			try {
				SaveResultsInCSV.saveParamters("data/results/" + scenario.getName() + ".csv", parameterList);
				SaveResultsInCSV.saveAverageStats("data/results/" + "Medians_" + scenario.getName() + ".csv",
						averageStatsList);

				// SaveResultsInExcel.saveParamters("data/results/" + scenario.getName() +
				// ".xls", parameterList);
				// SaveResultsInExcel.saveAverageStats("data/results/" +"Medians_"+
				// scenario.getName() + ".xls", averageStatsList);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public static String transformCount(int count) {
		if (count < 10) {
			return "#0" + count;
		} else {
			return "#" + String.valueOf(count);
		}
	}
}
