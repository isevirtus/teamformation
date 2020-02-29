package similarity;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.math3.ml.distance.ManhattanDistance;

import developer.Developer;
import project.Project;
import project.TargetInfo;
import project.Task;
import util.AllocationUtil;
import util.DeveloperUtil;
import util.DevelopmentHistoryUtil;
import util.ProjectUtil;
import util.TaskUtil;
import validation.Scenario;

public class Allocation {

	private ArrayList<AllocationInfo> allocationInfoList;
	HashMap<String, AllocationInfo> allocationInfoMap;
	private Scenario scenario;
	private TFIDF tfidf;

	public Allocation(Scenario scenario) {
		this.scenario = scenario;
		ArrayList<Developer> targetDeveloperList = scenario.getTargetDeveloperList();
		ArrayList<Project> trainningProjectList = scenario.getTrainningProjectsList();
		ArrayList<Task> trainningTaskList = ProjectUtil.getTasks(trainningProjectList);
		ArrayList<TargetInfo> targetInfoList = scenario.getTargetInfoList();
		DevelopmentHistoryUtil.buildDevelopmentHistory(targetDeveloperList, trainningProjectList);

		this.allocationInfoList = AllocationUtil.createAllocationInfoList(targetDeveloperList, targetInfoList);
		this.allocationInfoMap = AllocationUtil.createAllocationInfoMap(allocationInfoList);

		TFIDF tfidf = new TFIDF(trainningProjectList);
		setTfidf(tfidf);

		AllocationUtil.buildComplexVectors(allocationInfoList, tfidf);

		SimilarityMetric similarityMetric = SimilarityMetric.getInstance();
		similarityMetric.setDistanceMeasure(new ManhattanDistance());

		AllocationUtil.buildVectorSimilarities(allocationInfoList, similarityMetric);

		AllocationUtil.buildTaskStatistics(this.allocationInfoList, trainningTaskList);

//		 for(int i = 0;i<targetDeveloperList.size();i++){
//		 System.out.println("============================================================================");
//		 System.out.println(DeveloperUtil.tagProfileToString(targetDeveloperList.get(i)));
//		 System.out.println("============================================================================");
//		 }
		
		 //System.out.println(AllocationUtil.toString(allocationInfoList));

	}

	public Scenario getScenario() {
		return scenario;
	}

	public void setScenario(Scenario scenario) {
		this.scenario = scenario;
	}

	public ArrayList<AllocationInfo> getAllocationInfoList() {
		return allocationInfoList;
	}

	public void setAllocationInfoList(ArrayList<AllocationInfo> allocationInfoList) {
		this.allocationInfoList = allocationInfoList;
	}

	public AllocationInfo getAllocationInfo(String id, String developerId, String taskId) {
		return this.allocationInfoMap.get(id + developerId + taskId);
	}

	public TFIDF getTfidf() {
		return tfidf;
	}

	public void setTfidf(TFIDF tfidf) {
		this.tfidf = tfidf;
	}

}
