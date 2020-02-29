package geneticAlgorithm.JeneticsTest;

import java.util.ArrayList;

import io.jenetics.EnumGene;
import io.jenetics.Genotype;
import io.jenetics.Mutator;
import io.jenetics.PermutationChromosome;
import io.jenetics.RouletteWheelSelector;
import io.jenetics.SinglePointCrossover;
import io.jenetics.TournamentSelector;
import io.jenetics.engine.Engine;
import io.jenetics.engine.EvolutionResult;
import io.jenetics.util.Factory;
import io.jenetics.util.ISeq;

public class HelloWorld {

	// 2.) Definition of the fitness function.
	private static int eval(Genotype<EnumGene<String>> gt) {
		int sum = 0;
		System.out.println(gt);
		for(int i=0; i <gt.get(0).length();i++){
			int value = Integer.valueOf(gt.get(0).getGene(i).getAllele());
			sum+= value;
		}
		return sum;
	}

	public static void main(String[] args) {
		// 1.) Define the genotype (factory) suitable
		// for the problem.
		
		//final ISeq<String> basicSet = ISeq.of("1", "2", "3", "4", "5", "6");
		 
		 ArrayList<String> list = new ArrayList<String>(); 
		 list.add("1");
		 list.add("2");
		 list.add("3");
		 list.add("4");
		 list.add("5");
		 list.add("6");
		 
		 final ISeq<String> basicSet = ISeq.of(list);

		 // The chromosome has a length of 3 and will only contain values from the
		 // given basic-set, with no duplicates.	
		Factory<Genotype<EnumGene<String>>> gtf = Genotype.of(PermutationChromosome.of(basicSet, 3),PermutationChromosome.of(basicSet, 2));

		// 3.) Create the execution environment.
		//Engine<EnumGene<String>, Integer> engine = Engine.builder(HelloWorld::eval, gtf).build();
		
		Engine<EnumGene<String>, Integer> engine = Engine.builder(HelloWorld::eval, gtf)
				.populationSize(500)
				.survivorsSelector(new TournamentSelector<>(5))
				.offspringSelector(new RouletteWheelSelector<>())
				.alterers(
					new Mutator<>(0.115),
					new SinglePointCrossover<>(0.16))
				.build();

		// 4.) Start the execution (evolution) and
		// collect the result.
		Genotype<EnumGene<String>> result = engine.stream().limit(1000).collect(EvolutionResult.toBestGenotype());

		System.out.println("Hello World: " + result);
	}
}