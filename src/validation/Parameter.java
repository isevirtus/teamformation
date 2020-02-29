package validation;

public class Parameter {

	private String crossOverRate;
	private String distanceMeasure;
	private String fitnessFunctionType;
	private String mutationRate;
	private String offspringSelector;
	private String populationSize;
	private String steadyFitness;
	private String survivorsSelector;
	
	private String optimalSolution;
	private String foundSolution;
	private String detailedFoundSolution;
	private String fitnessValue;
	private String matchRate;
	private String originGeneration;
	private String generationCount;
	private String time;
	private String configurationId;

	public Parameter() {

	}

	public String getCrossOverRate() {
		return crossOverRate;
	}

	public void setCrossOverRate(String crossOverRate) {
		this.crossOverRate = crossOverRate;
	}

	public String getDistanceMeasure() {
		return distanceMeasure;
	}

	public void setDistanceMeasure(String distanceMeasure) {
		this.distanceMeasure = distanceMeasure;
	}

	public String getfitnessFunctionType() {
		return fitnessFunctionType;
	}

	public void setfitnessFunctionType(String fitnessFunctionType) {
		this.fitnessFunctionType = fitnessFunctionType;
	}

	public String getMutationRate() {
		return mutationRate;
	}

	public void setMutationRate(String mutationRate) {
		this.mutationRate = mutationRate;
	}

	public String getOffspringSelector() {
		return offspringSelector;
	}

	public void setOffspringSelector(String offspringSelector) {
		this.offspringSelector = offspringSelector;
	}

	public String getPopulationSize() {
		return populationSize;
	}

	public void setPopulationSize(String populationSize) {
		this.populationSize = populationSize;
	}

	public String getSteadyFitness() {
		return steadyFitness;
	}

	public void setSteadyFitness(String steadyFitness) {
		this.steadyFitness = steadyFitness;
	}

	public String getSurvivorsSelector() {
		return survivorsSelector;
	}

	public void setSurvivorsSelector(String survivorsSelector) {
		this.survivorsSelector = survivorsSelector;
	}
	
	
	
	public String getFitnessFunctionType() {
		return fitnessFunctionType;
	}

	public void setFitnessFunctionType(String fitnessFunctionType) {
		this.fitnessFunctionType = fitnessFunctionType;
	}

	public String getOptimalSolution() {
		return optimalSolution;
	}

	public void setOptimalSolution(String optimalSolution) {
		this.optimalSolution = optimalSolution;
	}

	public String getFoundSolution() {
		return foundSolution;
	}

	public void setFoundSolution(String foundSolution) {
		this.foundSolution = foundSolution;
	}

	public String getDetailedFoundSolution() {
		return detailedFoundSolution;
	}

	public void setDetailedFoundSolution(String detailedFoundSolution) {
		this.detailedFoundSolution = detailedFoundSolution;
	}

	public String getFitnessValue() {
		return fitnessValue;
	}

	public void setFitnessValue(String fitnessValue) {
		this.fitnessValue = fitnessValue;
	}

	public String getMatchRate() {
		return matchRate;
	}

	public void setMatchRate(String matchRate) {
		this.matchRate = matchRate;
	}

	public String getOriginGeneration() {
		return originGeneration;
	}

	public void setOriginGeneration(String originGeneration) {
		this.originGeneration = originGeneration;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
	

	public String getConfigurationId() {
		return configurationId;
	}

	public void setConfigurationId(String configurationId) {
		this.configurationId = configurationId;
	}
	
	

	public String getGenerationCount() {
		return generationCount;
	}

	public void setGenerationCount(String generationCount) {
		this.generationCount = generationCount;
	}

	public String getId() {
		StringBuilder builder = new StringBuilder();

		builder.append("[crossOverRate = " + crossOverRate + "]");
		builder.append("[distanceMeasure = " + distanceMeasure + "]");
		builder.append("[fitnessFunctionType = " + fitnessFunctionType + "]");
		builder.append("[mutationRate = " + mutationRate + "]");
		builder.append("[offspringSelector = " + offspringSelector + "]");
		builder.append("[populationSize = " + populationSize + "]");
		builder.append("[steadyFitness = " + steadyFitness + "]");
		builder.append("[survivorsSelector = " + survivorsSelector+"]");
		builder.append("[optimalSolution = "+optimalSolution+ "]");
		builder.append("[foundSolution = "+foundSolution+ "]");
		builder.append("[detailedFoundSolution = "+detailedFoundSolution+ "]");
		builder.append("[fitnessValue = "+fitnessValue+"]");
		builder.append("[averageMatch = "+matchRate+"]");
		builder.append("[generationCount = "+originGeneration+"]");

		return builder.toString();

	}
	

	public String toString() {
		StringBuilder builder = new StringBuilder();

		builder.append("| crossOverRate = " + crossOverRate + " | ");
		builder.append("distanceMeasure = " + distanceMeasure + " | ");
		builder.append("fitnessFunctionType = " + fitnessFunctionType + " | ");
		builder.append("mutationRate = " + mutationRate + " | ");
		builder.append("offspringSelector = " + offspringSelector + " | ");
		builder.append("populationSize = " + populationSize + " | ");
		builder.append("steadyFitness = " + steadyFitness + " | ");
		builder.append("survivorsSelector = " + survivorsSelector + " |");
		builder.append("optimalSolution = "+optimalSolution+ " |");
		builder.append("foundSolution = "+foundSolution+ " | ");
		builder.append("detailedFoundSolution = "+detailedFoundSolution+ " | ");
		builder.append("fitnessValue = "+fitnessValue+ " | ");
		builder.append("averageMatch = "+matchRate+ " | ");
		builder.append("generationCount = "+originGeneration+ " |");

		return builder.toString();

	}

}
