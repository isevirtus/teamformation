package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

import org.apache.commons.lang3.SerializationUtils;
import org.apache.poi.ddf.EscherColorRef.SysIndexSource;
import org.apache.poi.xdgf.usermodel.section.geometry.RelMoveTo;

import developer.Developer;
import geneticAlgorithm.ChromosomeMapping;
import geneticAlgorithm.GeneticAlgorithm;
import io.jenetics.EnumGene;
import io.jenetics.Genotype;
import io.jenetics.Phenotype;
import project.Project;
import project.TargetInfo;
import validation.Scenario;

public class ChromosomeUtil {

	public static int calcCromosomeLenght(Scenario scenario) {
		int lenght = 0;
		for (int i = 0; i < scenario.getTargetInfoList().size(); i++) {
			lenght += scenario.getTargetInfoList().get(i).getTeamSize();
		}
		return lenght;
	}

	public static boolean containsFixedDeveloperIds(ArrayList<TargetInfo> targetInfoList) {
		for (int i = 0; i < targetInfoList.size(); i++) {
			TargetInfo targetInfo = targetInfoList.get(i);
			if (targetInfo.getFixedDeveloperIdList().size() > 0) {
				return true;
			}
		}
		return false;
	}

	public static ArrayList<String> createFixedChromosomeIdList(ArrayList<TargetInfo> targetInfoList) {
		ArrayList<String> fixedChromosomeIdList = new ArrayList<>();
		for (int i = 0; i < targetInfoList.size(); i++) {
			TargetInfo targetInfo = targetInfoList.get(i);
			ArrayList<String> fixedDeveloperIdList = targetInfo.getFixedDeveloperIdList();
			for (int j = 0; j < targetInfo.getTeamSize(); j++) {
				if (j < fixedDeveloperIdList.size()) {
					fixedChromosomeIdList.add(new String(fixedDeveloperIdList.get(j)));
				} else {
					fixedChromosomeIdList.add(null);
				}
			}
		}
		return fixedChromosomeIdList;
	}
	
	public static HashMap<String, ArrayList<String>> createProjectMembersMap(ChromosomeMapping chromosomeMapping,
			ArrayList<String> solution) {
		HashMap<String, ArrayList<String>> projectMembersMap = new HashMap<String, ArrayList<String>>();

		for (int i = 0; i < solution.size(); i++) {
			int chromosomeIndx = i;
			String projectId = chromosomeMapping.getTargetInfoIndexMap().get(chromosomeIndx).getProject().getId();
			String developerId = solution.get(i);
			if (!projectMembersMap.containsKey(projectId)) {
				projectMembersMap.put(projectId, new ArrayList<String>());
			}
			ArrayList<String> memberIdList = projectMembersMap.get(projectId);
			memberIdList.add(developerId);
		}

		return projectMembersMap;
	}

	public static HashMap<String, Double> createProjectsMatchRate(HashMap<String, ArrayList<String>> optimalSolution,
			HashMap<String, ArrayList<String>> foundSolution) {
		HashMap<String, Double> projectsMatchRateMap = new HashMap<String, Double>();

		for (HashMap.Entry<String, ArrayList<String>> entry : optimalSolution.entrySet()) {
			String optimalSolutionProjectId = entry.getKey();
			ArrayList<String> optimalSolutionDeveloperIdList = entry.getValue();
			ArrayList<String> foundlSolutionDeveloperIdList = foundSolution.get(optimalSolutionProjectId);
			int matchCount = 0;
			for (int i = 0; i < foundlSolutionDeveloperIdList.size(); i++) {
				String foundSolutionDeveloperId = foundlSolutionDeveloperIdList.get(i);
				if (optimalSolutionDeveloperIdList.contains(foundSolutionDeveloperId)) {
					matchCount++;
				}
			}
			double projectMatchRate = (double) matchCount / foundlSolutionDeveloperIdList.size();
			projectsMatchRateMap.put(optimalSolutionProjectId, projectMatchRate);
		}
		return projectsMatchRateMap;

	}

	public static HashMap<String, Double> createProjectsMathRate(ChromosomeMapping chromosomeMapping,
			ArrayList<String> optimalSolution, ArrayList<String> foundSolution) {
		HashMap<String, ArrayList<String>> optimalSolutionProjectMembersMap = createProjectMembersMap(chromosomeMapping,
				optimalSolution);
		HashMap<String, ArrayList<String>> foundSolutionProjectMembersMap = createProjectMembersMap(chromosomeMapping,
				foundSolution);

		HashMap<String, Double> projectsMatchRateMap = createProjectsMatchRate(optimalSolutionProjectMembersMap,
				foundSolutionProjectMembersMap);

		return projectsMatchRateMap;
	}

	public static HashMap<String, Integer[]> createStartEndProjectIndexMap(Scenario scenario) {
		HashMap<String, Integer[]> startEndProjectIndexMap = new HashMap<String, Integer[]>();
		ArrayList<TargetInfo> targetInfoList = scenario.getTargetInfoList();
		int index = 0;
		for (int i = 0; i < targetInfoList.size(); i++) {
			TargetInfo targetInfo = targetInfoList.get(i);
			int starIndex = index;
			int teamSize = targetInfo.getTeamSize();
			index = index + (teamSize - 1);
			int endIndex = index;
			index++;
			Integer[] indexArray = { starIndex, endIndex };
			startEndProjectIndexMap.put(targetInfo.getTargetConfiguration() + targetInfo.getProjectId(), indexArray);

		}
		return startEndProjectIndexMap;
	}

	public static HashMap<Integer, TargetInfo> createTargetInfoMap(Scenario scenario) {
		ArrayList<TargetInfo> targetInfoList = scenario.getTargetInfoList();
		HashMap<Integer, TargetInfo> mappingArray = new HashMap<Integer, TargetInfo>();

		int chromossomeIndex = 0;
		for (int i = 0; i < targetInfoList.size(); i++) {
			TargetInfo targetProject = targetInfoList.get(i);
			for (int j = 0; j < targetProject.getTeamSize(); j++) {
				mappingArray.put(chromossomeIndex, targetInfoList.get(i));
				chromossomeIndex++;
			}
		}
		return mappingArray;
	}

	public static String getChromosomeSeqFormat(String[] seq){
		StringBuilder builder = new StringBuilder();
		builder.append("[");
		for(int i =0;i<seq.length;i++){
			builder.append(seq[i]+"|");
		}
		builder.deleteCharAt(builder.length()-1);
		builder.append("]");
		return builder.toString();
	}

	public static ArrayList<String> getDeveloperIds(Phenotype<EnumGene<String>, Double> phenotype) {
		Genotype<EnumGene<String>> genotype = phenotype.getGenotype();
		ArrayList<String> developerIdList = new ArrayList<String>();
		for (int i = 0; i < genotype.get(GeneticAlgorithm.CHROMOSOME_INDEX).length(); i++) {
			int chromosomeIndx = i;
			String developerId = genotype.get(GeneticAlgorithm.CHROMOSOME_INDEX).getGene(i).toString();
			developerIdList.add(developerId);
		}
		return developerIdList;
	}

	public static ArrayList<String> getFixedIds(ArrayList<String> fixedPermutation) {
		ArrayList<String> fixedIds = new ArrayList<String>();
		for (int i = 0; i < fixedPermutation.size(); i++) {
			if (fixedPermutation.get(i) != null) {
				fixedIds.add(fixedPermutation.get(i));
			}
		}
		return fixedIds;
	}

	public static ArrayList<String> getNewPermutation(ArrayList<String> fixedChromosomeIdList,
			ArrayList<String> chromosomeIdList) {
		ArrayList<String> fixedIds = getFixedIds(fixedChromosomeIdList);
		ArrayList<String> newPermutation = SerializationUtils.clone(chromosomeIdList);

		for (int i = 0; i < fixedIds.size(); i++) {
			String fixedId = new String(fixedIds.get(i));
			int fixedPosition = fixedChromosomeIdList.indexOf(fixedId);

			if (newPermutation.contains(fixedId)) {
				int chromosomeIdPosition = newPermutation.indexOf(fixedId);
				String id = new String(newPermutation.get(fixedPosition));
				newPermutation.set(fixedPosition, fixedId);
				newPermutation.set(chromosomeIdPosition, id);
				

			} else {
				newPermutation.set(fixedPosition, fixedId);
			}

		}
//		System.out.println(fixedChromosomeIdList);
//		System.out.println(chromosomeIdList);
//		System.out.println(newPermutation);
//		System.out.println();
		return newPermutation;
	}

	public static void orderProjectMembersMapIds(HashMap<String, ArrayList<String>> projectMembersMap) {
		for (HashMap.Entry<String, ArrayList<String>> entry : projectMembersMap.entrySet()) {
			ArrayListUtil.sort(entry.getValue());
		}
	}

	public static String toString(ChromosomeMapping chromosomeMapping, ArrayList<String> solution, double fitness) {
		StringBuilder builder = new StringBuilder();

		builder.append("Chromosome: " + ArrayListUtil.toString(solution) + " | Fitness: " + fitness);
		builder.append(System.lineSeparator());
		HashMap<String, ArrayList<String>> projectMembersMap = createProjectMembersMap(chromosomeMapping, solution);
		for (HashMap.Entry<String, ArrayList<String>> entry : projectMembersMap.entrySet()) {
			String projectId = entry.getKey();
			Project project = ProjectUtil.getProject(
					TargetInfoUtil.getProjects(chromosomeMapping.getScenario().getTargetInfoList()), projectId);
			ArrayList<String> developerIdList = entry.getValue();
			ArrayListUtil.sort(developerIdList);
			builder.append(project.getName() + " (" + project.getId() + ") = {");
			for (int i = 0; i < developerIdList.size(); i++) {
				String developerId = developerIdList.get(i);
				Developer developer = DeveloperUtil
						.getDeveloper(chromosomeMapping.getScenario().getTargetDeveloperList(), developerId);
				builder.append(developer.getName() + " (" + developer.getId() + "), ");
			}
			builder.delete(builder.length() - 2, builder.length());
			builder.append("}");
			builder.append(System.lineSeparator());

		}

		return builder.toString();

	}

	// public static void main(String[] args) {
	//
	// ArrayList<String> fixedPermutation = new ArrayList<String>();
	// fixedPermutation.add("D2");
	// fixedPermutation.add("D8");
	// fixedPermutation.add("D6");
	// fixedPermutation.add("D10");
	// fixedPermutation.add("D4");
	//
	// ArrayList<String> currentPermutation = new ArrayList<String>();
	// currentPermutation.add("D1");
	// currentPermutation.add("D6");
	// currentPermutation.add("D8");
	// currentPermutation.add("D2");
	// currentPermutation.add("D9");
	//
	// ArrayList<String> newPermutation =
	// ChromosomeUtil.getNewPermutation(fixedPermutation, currentPermutation);
	// }

}
