package geneticAlgorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Function;

import io.jenetics.EliteSelector;
import io.jenetics.EnumGene;
import io.jenetics.Genotype;
import io.jenetics.PartiallyMatchedCrossover;
import io.jenetics.PermutationChromosome;
import io.jenetics.Phenotype;
import io.jenetics.RouletteWheelSelector;
import io.jenetics.Selector;
import io.jenetics.SwapMutator;
import io.jenetics.TournamentSelector;
import io.jenetics.engine.Codec;
import io.jenetics.engine.Engine;
import io.jenetics.engine.EvolutionResult;
import io.jenetics.engine.EvolutionStatistics;
import io.jenetics.engine.Limits;
import io.jenetics.engine.Problem;
import io.jenetics.stat.DoubleMomentStatistics;
import io.jenetics.tool.evaluation.engines;
import io.jenetics.util.Factory;
import io.jenetics.util.ISeq;
import similarity.Allocation;
import util.ArrayListUtil;
import util.ChromosomeUtil;
import util.HashMapUtil;

public class GeneticAlgorithm implements Problem<ISeq<String>, EnumGene<String>, Double> {

	public static final int CHROMOSOME_INDEX = 0;
	private int chromosomeLength;

	// trocar para targetdeveloperlist
	private ArrayList<String> targetDeveloperListId;
	private ChromosomeMapping chromosomeMapping;
	private Allocation allocation;

	public GeneticAlgorithm(Allocation allocation) {
		this.allocation = allocation;
		this.chromosomeMapping = new ChromosomeMapping(allocation.getScenario());
		this.chromosomeLength = chromosomeMapping.getChromosomeLength();
		this.targetDeveloperListId = allocation.getScenario().getTargetDeveloperIdList();
	}

	@Override
	public synchronized Function<ISeq<String>, Double> fitness() {
		return new Function<ISeq<String>, Double>() {
			public Double apply(ISeq<String> seq) {
//				 double fitnessValue = CollaborativeTeamFunction.evaluate(seq, allocation,
//				 chromosomeMapping);
				double fitnessValue = BestDevelopersFunction.evaluate(seq, allocation, chromosomeMapping);

				return fitnessValue;
			}
		};
	}

	@Override
	public synchronized Codec<ISeq<String>, EnumGene<String>> codec() {
		return new Codec<ISeq<String>, EnumGene<String>>() {
			@Override
			public synchronized Function<Genotype<EnumGene<String>>, ISeq<String>> decoder() {
				return new Function<Genotype<EnumGene<String>>, ISeq<String>>() {
					@Override
					public synchronized ISeq<String> apply(Genotype<EnumGene<String>> gtp) {

						ArrayList<String> chromosomeIdList = new ArrayList<String>();
						ISeq<EnumGene<String>> enumSeq = gtp.get(GeneticAlgorithm.CHROMOSOME_INDEX).toSeq();
						String[] seq = new String[enumSeq.length()];
						for (int i = 0; i < seq.length; i++) {
							seq[i] = enumSeq.get(i).getAllele().toString();
							chromosomeIdList.add(enumSeq.get(i).getAllele().toString());
						}

						if (chromosomeMapping.getFixedChromosomeIdList().size() == 0) {
							return ISeq.of(seq);
						} else {
							ArrayList<String> newPermutation = ChromosomeUtil
									.getNewPermutation(chromosomeMapping.getFixedChromosomeIdList(), chromosomeIdList);
							return ISeq.of(newPermutation);

							// ArrayList<String> newPermutation = new
							// ArrayList<String>();
							// String chromosomeSeqFormat =
							// ChromosomeUtil.getChromosomeSeqFormat(seq);
							// System.out.println("chromosomeSeqFormat:
							// "+chromosomeSeqFormat);
							// if
							// (!genetotypeMap.containsKey(chromosomeSeqFormat))
							// {
							// newPermutation =
							// ChromosomeUtil.getNewPermutation(
							// chromosomeMapping.getFixedChromosomeIdList(),
							// chromosomeIdList);
							// genetotypeMap.put(chromosomeSeqFormat,
							// newPermutation);
							// } else {
							// newPermutation =
							// genetotypeMap.get(chromosomeSeqFormat);
							// }
							//
							// return ISeq.of(newPermutation);
						}

					}
				};
			}

			@Override
			public synchronized Factory<Genotype<EnumGene<String>>> encoding() {
				return new Factory<Genotype<EnumGene<String>>>() {
					@Override
					public Genotype<EnumGene<String>> newInstance() {
						final ISeq<String> basicSet = ISeq.of(targetDeveloperListId);
						Genotype<EnumGene<String>> genotype = Genotype
								.of(PermutationChromosome.of(basicSet, chromosomeLength));
						return genotype;

					}
				};
			}

		};
	}

	public ChromosomeMapping getChromosomeMapping() {
		return chromosomeMapping;
	}

	public void setChromosomeMapping(ChromosomeMapping chromosomeMapping) {
		this.chromosomeMapping = chromosomeMapping;
	}

	public synchronized GaResult run(double crossOverRate, double mutationRate,
			Selector offspringSelector, int populationSize, int steadyFitness, Selector survivorsSelector) {

		GaResult gaResult = new GaResult();
		final Engine<EnumGene<String>, Double> engine;
		if (survivorsSelector == null) {
			engine = Engine.builder(this).populationSize(populationSize).offspringSelector(offspringSelector)
					.alterers(new SwapMutator<>(mutationRate), new PartiallyMatchedCrossover<>(crossOverRate)).build();
		} else {
			engine = Engine.builder(this).populationSize(populationSize).offspringSelector(offspringSelector)
					.survivorsSelector(new EliteSelector<>())
					.alterers(new SwapMutator<>(mutationRate), new PartiallyMatchedCrossover<>(crossOverRate)).build();
		}

		final EvolutionStatistics<Double, DoubleMomentStatistics> statistics = EvolutionStatistics.ofNumber();

		final Phenotype<EnumGene<String>, Double> best = engine.stream().limit(Limits.bySteadyFitness(steadyFitness))
				.peek(statistics).collect(EvolutionResult.toBestPhenotype());

		ISeq decodedSolution = codec().decode(best.getGenotype());
		ArrayList<String> foundSolution = ArrayListUtil.iSeqToList(decodedSolution);
		
		gaResult.setBest(best);
		gaResult.setFoundSolution(foundSolution);
		gaResult.setStatistics(statistics);

		return gaResult;

	}

	public void run() {

		final Engine<EnumGene<String>, Double> engine = Engine.builder(this).populationSize(1000)
				.offspringSelector(new RouletteWheelSelector<>()).survivorsSelector(new TournamentSelector<>(5))
				.alterers(new SwapMutator<>(0.2), new PartiallyMatchedCrossover<>(0.4)).build();

		// final Engine<EnumGene<String>, Double> engine =
		// Engine.builder(this).populationSize(1000)
		// .survivorsSelector(new TournamentSelector<>(5)).offspringSelector(new
		// RouletteWheelSelector<>())
		// .build();

		final Phenotype<EnumGene<String>, Double> best = engine.stream().limit(Limits.bySteadyFitness(5))
				.collect(EvolutionResult.toBestPhenotype());

		ArrayList<String> optimalSolution = this.allocation.getScenario().getOptimalSolution();
		ISeq decodedSolution = codec().decode(best.getGenotype());
		ArrayList<String> foundSolution = ArrayListUtil.iSeqToList(decodedSolution);

		HashMap<String, Double> projectMatchRate = ChromosomeUtil.createProjectsMathRate(chromosomeMapping,
				optimalSolution, foundSolution);
		System.out.println();
		System.out.println("SCENARIO: " + allocation.getScenario().getName());
		System.out.println(best);
		System.out.println();
		System.out.println(">>>>>> OPTIMAL SOLUTION <<<<<<");
		System.out.println(optimalSolution);
		System.out.println(ChromosomeUtil.toString(chromosomeMapping, optimalSolution, 0.0));
		System.out.println(">>>>>> FOUND SOLUTION <<<<<< " + " STATUS: ");
		System.out.println(ChromosomeUtil.toString(chromosomeMapping, foundSolution, best.getFitness()));
		System.out.println("Projects Match Rate: " + HashMapUtil.toStringStringDouble(projectMatchRate) + " Average: "
				+ HashMapUtil.getValueAverage(projectMatchRate));
		System.out.println();

	}

	// public static void main(String[] args) {
	//
	// ISeq<String> basicSet = ISeq.of("D01", "D02", "D03", "D04", "D05", "D06",
	// "D07", "D08", "D09", "D10");
	// int subsetLength = 3;
	// PermutationChromosome<String> chromosome =
	// PermutationChromosome.of(basicSet, subsetLength);
	//
	// ArrayList<String> list = new ArrayList<>();
	//
	// for (int i = 0; i < 10000; i++) {
	// System.out.println(chromosome.newInstance());
	// PermutationChromosome<String> chr = chromosome.newInstance();
	// for (int j = 0; j < chr.length(); j++) {
	// if (!list.contains(chr.getGene(j).getAllele().toString())) {
	// list.add(chr.getGene(j).getAllele().toString());
	// }
	// }
	// }
	// System.out.println();
	// ArrayListUtil.sort(list);
	// System.out.println("Explored Genes: " + ArrayListUtil.toString(list) + "
	// |Expected size:" + basicSet.length()
	// + " | Size: " + list.size());
	// }
}
