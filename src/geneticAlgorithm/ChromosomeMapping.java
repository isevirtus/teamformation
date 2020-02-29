package geneticAlgorithm;

import java.util.ArrayList;
import java.util.HashMap;

import project.Project;
import project.TargetInfo;
import util.ArrayListUtil;
import util.ChromosomeUtil;
import validation.Scenario;

public class ChromosomeMapping {

	private HashMap<Integer, TargetInfo> targetInfoIndexMap;
	private HashMap<String, Integer[]> startEndProjectIndexMap;
	private ArrayList<String> fixedChromosomeIdList;

	private int chromosomeLength;
	private Scenario scenario;

	public ChromosomeMapping(Scenario scenario) {
		this.scenario = scenario;
		this.startEndProjectIndexMap = ChromosomeUtil.createStartEndProjectIndexMap(scenario);
		this.chromosomeLength = ChromosomeUtil.calcCromosomeLenght(scenario);
		this.targetInfoIndexMap = ChromosomeUtil.createTargetInfoMap(scenario);

		if (ChromosomeUtil.containsFixedDeveloperIds(scenario.getTargetInfoList())) {
			this.fixedChromosomeIdList = ChromosomeUtil.createFixedChromosomeIdList(scenario.getTargetInfoList());
		} else {
			this.fixedChromosomeIdList = new ArrayList<String>();
		}

		//System.out.println("fixedChromosomeIdList: " + ArrayListUtil.toString(fixedChromosomeIdList));
	}

	public Integer[] getStartEndProjectIndex(String targetConfiguration, String projectId) {
		return this.startEndProjectIndexMap.get(targetConfiguration + projectId);
	}

	public HashMap<Integer, TargetInfo> getTargetInfoIndexMap() {
		return targetInfoIndexMap;
	}

	public void setTargetInfoIndexMap(HashMap<Integer, TargetInfo> targetInfoIndexMap) {
		this.targetInfoIndexMap = targetInfoIndexMap;
	}

	public Scenario getScenario() {
		return scenario;
	}

	public void setScenario(Scenario scenario) {
		this.scenario = scenario;
	}

	public int getChromosomeLength() {
		return chromosomeLength;
	}

	public void setChromosomeLength(int chromosomeLength) {
		this.chromosomeLength = chromosomeLength;
	}

	public HashMap<String, Integer[]> getStartEndProjectIndexMap() {
		return startEndProjectIndexMap;
	}

	public void setStartEndProjectIndexMap(HashMap<String, Integer[]> startEndProjectIndexMap) {
		this.startEndProjectIndexMap = startEndProjectIndexMap;
	}

	public ArrayList<String> getFixedChromosomeIdList() {
		return fixedChromosomeIdList;
	}

	public void setFixedChromosomeIdList(ArrayList<String> fixedChromosomeIdList) {
		this.fixedChromosomeIdList = fixedChromosomeIdList;
	}
	
	

}
