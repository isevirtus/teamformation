package geneticAlgorithm.JeneticsTest;

import java.util.function.Function;

import io.jenetics.Genotype;
import io.jenetics.IntegerChromosome;
import io.jenetics.IntegerGene;
import io.jenetics.Mutator;
import io.jenetics.Phenotype;
import io.jenetics.RouletteWheelSelector;
import io.jenetics.SinglePointCrossover;
import io.jenetics.TournamentSelector;
import io.jenetics.engine.Codec;
import io.jenetics.engine.Engine;
import io.jenetics.engine.EvolutionResult;
import io.jenetics.engine.Problem;
import io.jenetics.util.ISeq;

public class Impar implements Problem<ISeq<IntegerGene>, IntegerGene, Integer> {

	private int length;
	private int min;
	private int max;

	public Impar(int length, int min, int max) {
		this.length = length;
		this.min = min;
		this.max = max;
	}

	@Override
	public Function<ISeq<IntegerGene>, Integer> fitness() {
		return new Function<ISeq<IntegerGene>, Integer>() {
			public Integer apply(ISeq<IntegerGene> seq) {
				int sum = 0;
				for (int i = 0; i < seq.length(); i++) {
					int number = seq.get(i).intValue();
					if (number % 2 != 0) {
						sum += number;
					}
				}
				return sum;
			}
		};
	}

	@Override
	public Codec<ISeq<IntegerGene>, IntegerGene> codec() {
		return Codec.of(Genotype.of(IntegerChromosome.of(this.min, this.max, this.length)),
				gt -> gt.getChromosome().toSeq());
	}

	public static void main(final String[] args) {
		final Impar problem = new Impar(10, 0, 100);

		// Configure and build the evolution engine.
		final Engine<IntegerGene, Integer> engine = Engine.builder(problem).populationSize(500)
				.survivorsSelector(new TournamentSelector<>(5)).offspringSelector(new RouletteWheelSelector<>())
				.alterers(new Mutator<>(0.115), new SinglePointCrossover<>(0.16)).build();

		// Create evolution statistics consumer.
		// final EvolutionStatistics<Double, ?>
		// statistics = EvolutionStatistics.ofNumber();

		final Phenotype<IntegerGene, Integer> best = engine.stream().limit(50)
				.collect(EvolutionResult.toBestPhenotype());

		// System.out.println(statistics);
		System.out.println(best);

	}

}
