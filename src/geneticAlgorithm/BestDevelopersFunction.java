package geneticAlgorithm;

import java.util.ArrayList;

import io.jenetics.util.ISeq;
import project.Project;
import project.Sprint;
import project.TargetInfo;
import project.Task;
import similarity.Allocation;
import similarity.AllocationInfo;
import util.ArrayListUtil;
import util.ProjectUtil;
import util.SprintUtil;

public class BestDevelopersFunction {

	public static synchronized double evaluate(ISeq<String> seq, Allocation allocation,
			ChromosomeMapping chromosomeMapping) {
		//System.out.println(seq.toString());
		double chromosomeFitness = 0;
		ArrayList<TargetInfo> targetInfoList = chromosomeMapping.getScenario().getTargetInfoList();
		for (int i = 0; i < targetInfoList.size(); i++) {
			TargetInfo targetInfo = targetInfoList.get(i);
			chromosomeFitness += calculateTargetFitness(allocation, chromosomeMapping, targetInfo, seq);
		}

		chromosomeFitness = chromosomeFitness / allocation.getScenario().getTargetDeveloperIdList().size();

		return chromosomeFitness;
	}

	public static synchronized double calculateTargetFitness(Allocation allocation, ChromosomeMapping chromosomeMapping,
			TargetInfo targetInfo, ISeq<String> seq) {

		String targetConfiguration = targetInfo.getTargetConfiguration();
		String projectId = targetInfo.getProjectId();
		Project project = targetInfo.getProject();
		String targetSprintId = targetInfo.getSprintId();
		Sprint sprint = ProjectUtil.getSprint(project, targetSprintId);
		ArrayList<Task> taskList = SprintUtil.getTasks(sprint);

		int startChromosomeIndex = chromosomeMapping.getStartEndProjectIndex(targetConfiguration, projectId)[0];
		int endChromosomeIndex = chromosomeMapping.getStartEndProjectIndex(targetConfiguration, projectId)[1];

		double developersFitnessSum = 0;
		for (int i = startChromosomeIndex; i <= endChromosomeIndex; i++) {
			int chromossomeIndex = i;
			String developerId = seq.get(chromossomeIndex).toString();
			developersFitnessSum += calculateDeveloperFitness(allocation, taskList, targetInfo, developerId);
		}

		return developersFitnessSum;
	}

	public static synchronized double calculateDeveloperFitness(Allocation allocation, ArrayList<Task> taskList,
			TargetInfo targetInfo, String developerId) {

		double taskFitnessSum = 0;
		for (int i = 0; i < taskList.size(); i++) {
			String taskId = taskList.get(i).getId();
			AllocationInfo allocationInfo = allocation.getAllocationInfo(targetInfo.getTargetConfiguration(),
					developerId, taskId);
			double developerTaskSimilarity = allocationInfo.getComplexVector().getDevelopmentHistorySimilarity();
			double normalizedNumberOfTasksDeveloped = allocationInfo.getNormalizedNumberOfTasksDeveloped();

			taskFitnessSum += (1 + developerTaskSimilarity) * (1 + normalizedNumberOfTasksDeveloped);
		
		}
		double developerFitness = taskFitnessSum / taskList.size();

		return developerFitness;
	}

}
