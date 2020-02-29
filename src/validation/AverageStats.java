package validation;

public class AverageStats {
	
	private String configurationId;
	private double timeAverage;
	private double matchRateAverage;
	private double originGenerationAverage;
	private double generationCount;
	private double populationSize;
	private double steadyFitness;
	
	public AverageStats(){
		
	}

	public String getConfigurationId() {
		return configurationId;
	}

	public void setConfigurationId(String configurationId) {
		this.configurationId = configurationId;
	}

	public double getTimeAverage() {
		return timeAverage;
	}

	public void setTimeAverage(double timeAverage) {
		this.timeAverage = timeAverage;
	}

	public double getMatchRateAverage() {
		return matchRateAverage;
	}

	public void setMatchRateAverage(double matchRateAverage) {
		this.matchRateAverage = matchRateAverage;
	}

	public double getOriginGenerationAverage() {
		return originGenerationAverage;
	}

	public void setOriginGenerationAverage(double originGenerationAverage) {
		this.originGenerationAverage = originGenerationAverage;
	}

	public double getGenerationCount() {
		return generationCount;
	}

	public void setGenerationCount(double generationCount) {
		this.generationCount = generationCount;
	}

	public double getPopulationSize() {
		return populationSize;
	}

	public void setPopulationSize(double populationSize) {
		this.populationSize = populationSize;
	}

	public double getSteadyFitness() {
		return steadyFitness;
	}

	public void setSteadyFitness(double steadyFitness) {
		this.steadyFitness = steadyFitness;
	}

}
