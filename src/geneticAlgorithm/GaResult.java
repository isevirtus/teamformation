package geneticAlgorithm;

import java.util.ArrayList;

import io.jenetics.EnumGene;
import io.jenetics.Phenotype;
import io.jenetics.engine.EvolutionStatistics;
import io.jenetics.stat.DoubleMomentStatistics;

public class GaResult {
	
	private Phenotype<EnumGene<String>, Double> best;
	private EvolutionStatistics<Double, DoubleMomentStatistics> statistics;
	ArrayList<String> foundSolution;
	
	public GaResult(){
		foundSolution = new ArrayList<String>();
	}

	public Phenotype<EnumGene<String>, Double> getBest() {
		return best;
	}

	public void setBest(Phenotype<EnumGene<String>, Double> best) {
		this.best = best;
	}

	public EvolutionStatistics<Double, DoubleMomentStatistics> getStatistics() {
		return statistics;
	}

	public void setStatistics(EvolutionStatistics<Double, DoubleMomentStatistics> statistics) {
		this.statistics = statistics;
	}

	public ArrayList<String> getFoundSolution() {
		return foundSolution;
	}

	public void setFoundSolution(ArrayList<String> foundSolution) {
		this.foundSolution = foundSolution;
	}
	
	
	
	

}
