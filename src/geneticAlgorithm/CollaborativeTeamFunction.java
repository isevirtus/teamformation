package geneticAlgorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

import io.jenetics.util.ISeq;
import project.Project;
import project.Sprint;
import project.TargetInfo;
import project.Task;
import similarity.Allocation;
import similarity.AllocationInfo;
import similarity.SimilarityMetric;
import util.ProjectUtil;
import util.SprintUtil;
import util.VectorUtil;
import vector.Vector;
import vector.VectorType;

public class CollaborativeTeamFunction {

	public synchronized static double evaluate(ISeq<String> seq, Allocation allocation,
			ChromosomeMapping chromosomeMapping) {
		double chromosomeFitness = 0;
		ArrayList<TargetInfo> targetInfoList = chromosomeMapping.getScenario().getTargetInfoList();

		for (int i = 0; i < targetInfoList.size(); i++) {
			TargetInfo targetInfo = targetInfoList.get(i);
			// System.out.println("TARGET FITNESS:
			// "+targetInfo.getTargetConfiguration()+targetInfo.getProjectId());
			chromosomeFitness += calculateProjectFitness(allocation, chromosomeMapping, targetInfo, seq);
			// System.out.println("=============================");
		}
		chromosomeFitness = chromosomeFitness / targetInfoList.size();

		return chromosomeFitness;
	}

	public synchronized static double calculateProjectFitness(Allocation allocation,
			ChromosomeMapping chromosomeMapping, TargetInfo targetInfo, ISeq<String> seq) {

		String targetConfiguration = targetInfo.getTargetConfiguration();
		String projectId = targetInfo.getProjectId();
		Project project = targetInfo.getProject();
		String targetSprintId = targetInfo.getSprintId();
		Sprint sprint = ProjectUtil.getSprint(project, targetSprintId);
		ArrayList<Task> taskList = SprintUtil.getTasks(sprint);

		int startChromosomeIndex = chromosomeMapping.getStartEndProjectIndex(targetConfiguration, projectId)[0];
		int endChromosomeIndex = chromosomeMapping.getStartEndProjectIndex(targetConfiguration, projectId)[1];

		ArrayList<String> developerIdList = new ArrayList<String>();
		for (int i = startChromosomeIndex; i <= endChromosomeIndex; i++) {
			int chromossomeIndex = i;
			String developerId = seq.get(chromossomeIndex).toString();
			developerIdList.add(developerId);
		}
		// System.out.println("MEMBERS:
		// "+ArrayListUtil.toString(developerIdList));
		double collaborativeFitness = calculateTaskListFitness(allocation, taskList, targetInfo, developerIdList);

		return collaborativeFitness;
	}

	public synchronized static double calculateTaskListFitness(Allocation allocation, ArrayList<Task> taskList,
			TargetInfo targetInfo, ArrayList<String> developerIdList) {

		double membersFitnessSum = 0;
		for (int i = 0; i < taskList.size(); i++) {
			Task task = taskList.get(i);
			membersFitnessSum += calculateTaskFitness(allocation, task, targetInfo, developerIdList, i);
		}

		double membersFitness = membersFitnessSum / taskList.size();

		return membersFitness;
	}

	public synchronized static double calculateTaskFitness(Allocation allocation, Task task, TargetInfo targetInfo,
			ArrayList<String> developerIdList, int index) {

		Vector taskVector = new Vector(task, VectorType.TASK);
		taskVector.fillVector(null, null, targetInfo.getWeithedTagMap());

		Vector vectorSum = new Vector(task, VectorType.TASK);
		vectorSum.fillVector(null, null, targetInfo.getWeithedTagMap());
		VectorUtil.resetValues(vectorSum);

		double normalizedNumberOfTasksDevelopedSum = 0;
		// System.out.println(index + " taskVector: " + taskVector);
		for (int i = 0; i < developerIdList.size(); i++) {
			String developerId = developerIdList.get(i);
			AllocationInfo allocationInfo = allocation.getAllocationInfo(targetInfo.getTargetConfiguration(),
					developerId, task.getId());
			Vector developmentHistoryVector = allocationInfo.getComplexVector().getDevelopmentHistoryVector();
			vectorSum = VectorUtil.sumVectorsValues(vectorSum, developmentHistoryVector);

			// vectorSum = VectorUtil.maxVectorsValues(vectorSum,
			// developmentHistoryVector);
			normalizedNumberOfTasksDevelopedSum += allocationInfo.getNormalizedNumberOfTasksDeveloped();

		}
		vectorSum = VectorUtil.didivebyNumber(vectorSum, developerIdList.size());

		double developerTaskSimilarityAverage = SimilarityMetric.getInstance().computeSimililarity(taskVector,
				vectorSum, targetInfo.getWeithedTagMap());

		double normalizedNumberOfTasksDevelopedAverage = normalizedNumberOfTasksDevelopedSum / developerIdList.size();

		double taskFitness = (1 + developerTaskSimilarityAverage) * (1 + normalizedNumberOfTasksDevelopedAverage);

		return taskFitness;
	}

	// public synchronized static double calculateTaskFitnessTEST(Allocation
	// allocation, Task task, TargetInfo targetInfo,
	// ArrayList<String> developerIdList, int index) {
	//
	// Vector taskVector = new Vector(task, VectorType.TASK);
	// taskVector.fillVector(null, null, targetInfo.getWeithedTagMap());
	//
	// Vector collaborativeVector = new Vector(task, VectorType.TASK);
	// collaborativeVector.fillVector(null, null,
	// targetInfo.getWeithedTagMap());
	// VectorUtil.resetValues(collaborativeVector);
	//
	// HashMap<String, Double[]> developersTagValuesMap = new HashMap<String,
	// Double[]>();
	// for (int i = 0; i < taskVector.getLength(); i++) {
	// developersTagValuesMap.put(taskVector.getAttributeNameArray()[i], new
	// Double[developerIdList.size()]);
	// }
	// Double[] normalizedNumberOfTasksArray = new
	// Double[developerIdList.size()];
	// for (int i = 0; i < developerIdList.size(); i++) {
	// String developerId = developerIdList.get(i);
	// AllocationInfo allocationInfo =
	// allocation.getAllocationInfo(targetInfo.getTargetConfiguration(),
	// developerId, task.getId());
	// Vector developmentHistoryVector =
	// allocationInfo.getComplexVector().getDevelopmentHistoryVector();
	//
	// for (int j = 0; j < developmentHistoryVector.getLength(); j++) {
	// String tagKey = developmentHistoryVector.getAttributeNameArray()[j];
	// double tagValue = developmentHistoryVector.getValueArray()[j];
	// developersTagValuesMap.get(tagKey)[i] = tagValue;
	// }
	// normalizedNumberOfTasksArray[i] =
	// allocationInfo.getNormalizedNumberOfTasksDeveloped();
	//
	// }
	//
	// for (int i = 0; i < taskVector.getLength(); i++) {
	// Double[] developerTagValueArray =
	// developersTagValuesMap.get(taskVector.getAttributeNameArray()[i]);
	// Arrays.sort(developerTagValueArray, Collections.reverseOrder());
	// double collaborativeTagValueSum = 0;
	//
	// for (int j = 0; j < developerTagValueArray.length; j++) {
	// collaborativeTagValueSum += developerTagValueArray[j];
	// }
	//
	// double collaborativeTagValueAverage = collaborativeTagValueSum /
	// developerIdList.size();
	// collaborativeVector.getValueArray()[i] = collaborativeTagValueAverage;
	// }
	//
	// Arrays.sort(normalizedNumberOfTasksArray, Collections.reverseOrder());
	// double normalizedNumberOfTasksSum = 0;
	// for (int k = 0; k < normalizedNumberOfTasksArray.length; k++) {
	// normalizedNumberOfTasksSum += normalizedNumberOfTasksArray[k];
	//
	// }
	//
	//
	// double normalizedNumberOfTasksAverage = normalizedNumberOfTasksSum /
	// developerIdList.size();
	//
	// double developerTaskSimilarityAverage =
	// SimilarityMetric.getInstance().computeSimililarity(taskVector,
	// collaborativeVector, targetInfo.getWeithedTagMap());
	//
	// double taskFitness = (1 + developerTaskSimilarityAverage) * (1 +
	// normalizedNumberOfTasksAverage);
	//
	// return taskFitness;
	// }

}
