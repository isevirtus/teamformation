package geneticAlgorithm;

import java.util.ArrayList;

import io.jenetics.util.ISeq;
import project.Project;
import project.Sprint;
import project.TargetInfo;
import project.Task;
import similarity.Allocation;
import similarity.AllocationInfo;
import util.ProjectUtil;
import util.SprintUtil;

public class ScrumTeamFunction {

	public static double evaluate(ISeq<String> seq, Allocation allocation, ChromosomeMapping chromosomeMapping) {
		double chromosomeFitness = 0;
		// System.out.println(seq.toString());
		for (int i = 0; i < seq.length(); i++) {
			int chromossomeIndex = i;
			String developerId = seq.get(chromossomeIndex).toString();
			TargetInfo targetInfo = chromosomeMapping.getTargetInfoIndexMap().get(chromossomeIndex);
			Project project = targetInfo.getProject();
			String targetConfiguration = targetInfo.getTargetConfiguration();
			String targetSprintId = targetInfo.getSprintId();
			Sprint sprint = ProjectUtil.getSprint(project, targetSprintId);
			ArrayList<Task> taskList = SprintUtil.getTasks(sprint);

			chromosomeFitness += calculateDeveloperFitness(allocation, taskList, targetConfiguration, developerId);

		}
		chromosomeFitness = chromosomeFitness / chromosomeMapping.getChromosomeLength();

		return chromosomeFitness;
	}

	public static double calculateDeveloperFitness(Allocation allocation, ArrayList<Task> taskList,
			String targetConfiguration, String developerId) {
		
		double individualFitnessSum = 0;
		for (int i = 0; i < taskList.size(); i++) {
			String taskId = taskList.get(i).getId();
			AllocationInfo allocationInfo = allocation.getAllocationInfo(targetConfiguration, developerId, taskId);
			double developerTaskSimilarity = allocationInfo.getComplexVector().getDevelopmentHistorySimilarity();
			double normalizedNumberOfTasksDeveloped = allocationInfo.getNormalizedNumberOfTasksDeveloped();

			individualFitnessSum += (1 + developerTaskSimilarity) * (1 + normalizedNumberOfTasksDeveloped);

		}
		double individualFitness = individualFitnessSum / taskList.size();

		return individualFitness;
	}

}
