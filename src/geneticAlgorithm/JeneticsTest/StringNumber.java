package geneticAlgorithm.JeneticsTest;

import java.util.ArrayList;
import java.util.function.Function;

import io.jenetics.EnumGene;
import io.jenetics.Genotype;
import io.jenetics.PermutationChromosome;
import io.jenetics.engine.Codec;
import io.jenetics.engine.Problem;
import io.jenetics.util.Factory;
import io.jenetics.util.ISeq;

/**
 * Full Ones-Counting example.
 *
 * @author <a href="mailto:franz.wilhelmstoetter@gmail.com">Franz
 *         Wilhelmstötter</a>
 * @version 3.6
 * @since 3.5
 */
public class StringNumber implements Problem<ISeq<String>, EnumGene<String>, Integer> {

	private final int _length;

	/**
	 * Create a new Ones-Counting example with the given parameters.
	 *
	 * @param length
	 *            the length of the ones-vector
	 * @param onesProbability
	 *            the probability of ones in the created vector
	 */
	public StringNumber(final int length) {
		_length = length;
	}

	@Override
	public Function<ISeq<String>, Integer> fitness() {
		return new Function<ISeq<String>, Integer>() {

			@Override
			public Integer apply(ISeq<String> seq) {
				int sum = 0;
				for (int i = 0; i < seq.length(); i++) {
//					String stringValue = seq.get(i).getAllele().toString();
//					int number = Integer.valueOf(stringValue);
//					sum += number;

				}
				return sum;
			}
		};
	}

	@Override
	public Codec<ISeq<String>, EnumGene<String>> codec() {
		return new Codec<ISeq<String>, EnumGene<String>>() {

			@Override
			public Function<Genotype<EnumGene<String>>, ISeq<String>> decoder() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Factory<Genotype<EnumGene<String>>> encoding() {
				ISeq<String> alleles = ISeq.of("0","1");
				
				return Genotype.of(
					     PermutationChromosome.of(alleles));
			}
		};
	}

	public static void main(final String[] args) {
//		final StringNumber problem = new StringNumber(10);
//		final Engine<EnumGene<String>, Integer> engine = Engine.builder(problem).build();
//
//		final ISeq<EnumGene<String>> result = problem.codec().decoder()
//				.apply(engine.stream().limit(10).collect(EvolutionResult.toBestGenotype()));
//
//		System.out.println(result);
//		
		 final ISeq<String> alleles = ISeq.of("one", "two", "three", "four", "five");

		 // Create a new randomly permuted chromosome from the given alleles.
		 final PermutationChromosome<String> ch1 = PermutationChromosome.of(alleles);
		 System.out.println(ch1);
		 System.out.println(ch1.newInstance());

		 // Create a new randomly permuted chromosome from a subset of the given alleles.
		 final PermutationChromosome<String> ch2 = PermutationChromosome.of(alleles, 3);
		 System.out.println(ch2);
		 System.out.println(ch2.newInstance());
		 
		 //final ISeq<String> basicSet = ISeq.of("D1", "D2", "D3", "D4", "D5", "D6");
		 
		 ArrayList<String> list = new ArrayList<String>(); 
		 list.add("D1");
		 list.add("D2");
		 list.add("D3");
		 list.add("D4");
		 list.add("D5");
		 list.add("D6");
		 
		 final ISeq<String> basicSet = ISeq.of(list);

		 // The chromosome has a length of 3 and will only contain values from the
		 // given basic-set, with no duplicates.
		 final PermutationChromosome<String> ch3 = PermutationChromosome.of(basicSet, 3);
		 for(int i=0;i<10;i++){
			 System.out.println(ch3.newInstance());
		 }
	}

}
