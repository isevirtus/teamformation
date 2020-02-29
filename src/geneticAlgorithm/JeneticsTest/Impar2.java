package geneticAlgorithm.JeneticsTest;

import java.util.Random;
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
import io.jenetics.util.Factory;
import io.jenetics.util.ISeq;

public class Impar2 implements Problem<ISeq<IntegerGene>, IntegerGene, Integer> {

	private int length;
	private int min;
	private int max;

	public Impar2(int length, int min, int max) {
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
		return new Codec<ISeq<IntegerGene>, IntegerGene>() {
			@Override
			public Function<Genotype<IntegerGene>, ISeq<IntegerGene>> decoder() {
				return new Function<Genotype<IntegerGene>, ISeq<IntegerGene>>() {
					@Override
					public ISeq<IntegerGene> apply(Genotype<IntegerGene> gtp) {
						return gtp.get(0).toSeq();
					}
				};
			}

			@Override
			public Factory<Genotype<IntegerGene>> encoding() {
				return new Factory<Genotype<IntegerGene>>() {
					@Override
					public Genotype<IntegerGene> newInstance() {
						IntegerGene[] integerGeneArray = new IntegerGene[length];
						for (int i = 0; i < integerGeneArray.length; i++) {
							int minGene = 0;
							int maxGene = 10;
							Random random = new Random();
							int value = random.nextInt(maxGene + 1);
							integerGeneArray[i] = IntegerGene.of(value, minGene, maxGene);
						}

						IntegerChromosome integerChromosome = IntegerChromosome.of(integerGeneArray);
						Genotype<IntegerGene> genotype = Genotype.of(integerChromosome);

						return genotype;
					}
				};

			}
		};
	}

	public static void main(final String[] args) {
		final Impar2 problem = new Impar2(10, 0, 100);

		// Configure and build the evolution engine.
		final Engine<IntegerGene, Integer> engine = Engine.builder(problem).populationSize(500)
				.survivorsSelector(new TournamentSelector<>(5)).offspringSelector(new RouletteWheelSelector<>())
				.alterers(new Mutator<>(0.115), new SinglePointCrossover<>(0.16)).build();

		final Phenotype<IntegerGene, Integer> best = engine.stream().limit(50)
				.collect(EvolutionResult.toBestPhenotype());

		System.out.println(best);

	}

}
