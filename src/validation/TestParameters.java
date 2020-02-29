package validation;

import org.apache.commons.math3.ml.distance.CanberraDistance;
import org.apache.commons.math3.ml.distance.ChebyshevDistance;
import org.apache.commons.math3.ml.distance.DistanceMeasure;
import org.apache.commons.math3.ml.distance.EuclideanDistance;
import org.apache.commons.math3.ml.distance.ManhattanDistance;

import geneticAlgorithm.FitnessFunctionType;
import io.jenetics.EliteSelector;
import io.jenetics.LinearRankSelector;
import io.jenetics.RouletteWheelSelector;
import io.jenetics.Selector;
import io.jenetics.StochasticUniversalSelector;
import io.jenetics.TournamentSelector;

public class TestParameters {

//	public static double[] crossOverRate = { 0.7 };
//	public static DistanceMeasure[] distanceMeasure = {  new ManhattanDistance(), new EuclideanDistance(),
//			new CanberraDistance(), new ChebyshevDistance() };
//	public static FitnessFunctionType[] fitnessFunctionType = { FitnessFunctionType.BEST_INDIVIDUALS };
//	public static double[] mutationRate = { 0.1};
//	public static Selector[] offspringSelector = { new LinearRankSelector<>(), new RouletteWheelSelector<>(),
//	new StochasticUniversalSelector<>(), new TournamentSelector<>() };
//	
//	public static Selector[] survivorsSelector = {new EliteSelector<>()};
//	public static int[] populationSize = { 500};
//	public static int[] steadyFitness = { 10 };
	
	
//	public static double[] crossOverRate = { 0.5, 0.7, 0.9 };
//	public static DistanceMeasure[] distanceMeasure = { new ManhattanDistance(), new EuclideanDistance(),
//			new CanberraDistance(), new ChebyshevDistance() };
//	public static FitnessFunctionType[] fitnessFunctionType = { FitnessFunctionType.BEST_INDIVIDUALS };
//	public static double[] mutationRate = { 0.05, 0.1, 0.2 };
//	public static Selector[] offspringSelector = { new LinearRankSelector<>(), new RouletteWheelSelector<>(),
//			new StochasticUniversalSelector<>(), new TournamentSelector<>() };
//	
//	public static Selector[] survivorsSelector = { new EliteSelector<>(), null};
//	public static int[] populationSize = { 100, 500, 1000 };
//	public static int[] steadyFitness = { 5,10 };
	


	
	
	public static double[] crossOverRate = { 0.7 };
	public static DistanceMeasure[] distanceMeasure = { new ManhattanDistance()};
	public static FitnessFunctionType[] fitnessFunctionType = { FitnessFunctionType.BEST_INDIVIDUALS };
	public static double[] mutationRate = { 0.1};
	public static Selector[] offspringSelector = { new TournamentSelector<>() };
	public static Selector[] survivorsSelector = { new EliteSelector<>()};
	public static int[] populationSize = { 1000 };
	public static int[] steadyFitness = { 50 };

	
	

	// public static double[] crossOverRate = { 0.5, 0.7, 0.9 };
	// public static DistanceMeasure[] distanceMeasure = { new
	// ManhattanDistance(), new EuclideanDistance(),
	// new CanberraDistance(), new ChebyshevDistance(), new
	// EarthMoversDistance() };
	// public static FitnessFunctionType[] fitnessFunctionType = {
	// FitnessFunctionType.BEST_INDIVIDUALS };
	// public static double[] mutationRate = {0.025, 0.05, 0.1};
	// public static Selector[] offspringSelector = { new BoltzmannSelector<>(),
	// new EliteSelector<>(),
	// new ExponentialRankSelector<>(), new LinearRankSelector<>(), new
	// MonteCarloSelector<>(),
	// new RouletteWheelSelector<>(), new StochasticUniversalSelector<>(), new
	// TournamentSelector<>(),
	// new TruncationSelector<>() };
	// public static int[] populationSize = { 100, 200, 400,800 };
	// public static int[] steadyFitness = { 5, 10,20 };
	// public static Selector[] survivorsSelector = { new BoltzmannSelector<>(),
	// new EliteSelector<>(),
	// new ExponentialRankSelector<>(), new LinearRankSelector<>(), new
	// MonteCarloSelector<>(),
	// new RouletteWheelSelector<>(), new StochasticUniversalSelector<>(), new
	// TournamentSelector<>(),
	// new TruncationSelector<>() };
	// public static double[] crossOverRate = { 0.2,0.5};
	// public static DistanceMeasure[] distanceMeasure = { new
	// ManhattanDistance(),new EuclideanDistance() };
	// public static FitnessFunctionType[] fitnessFunctionType = {
	// FitnessFunctionType.BEST_INDIVIDUALS };
	// public static double[] mutationRate = { 0.05,0.1,0.2 };
	// public static Selector[] offspringSelector = { new
	// RouletteWheelSelector<>() };
	// public static int[] populationSize = {100, 500 };
	// public static int[] steadyFitness = { 15 };
	// public static Selector[] survivorsSelector = { new
	// TournamentSelector<>()};

}
