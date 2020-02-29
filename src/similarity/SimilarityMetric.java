package similarity;

import java.util.HashMap;

import org.apache.commons.math3.ml.distance.CanberraDistance;
import org.apache.commons.math3.ml.distance.ChebyshevDistance;
import org.apache.commons.math3.ml.distance.DistanceMeasure;
import org.apache.commons.math3.ml.distance.EarthMoversDistance;
import org.apache.commons.math3.ml.distance.EuclideanDistance;
import org.apache.commons.math3.ml.distance.ManhattanDistance;

import io.jenetics.ext.moea.Vec;
import util.ArrayUtil;
import vector.Normalization;
import vector.Vector;

public class SimilarityMetric {

	private static SimilarityMetric similarityMetric;

	private DistanceMeasure distanceMeasure;

	private SimilarityMetric() {

	}

	public static synchronized SimilarityMetric getInstance() {
		if (similarityMetric == null)
			similarityMetric = new SimilarityMetric();

		return similarityMetric;
	}

	public DistanceMeasure getDistanceMeasure() {
		return distanceMeasure;
	}

	public void setDistanceMeasure(DistanceMeasure distanceMeasure) {
		this.distanceMeasure = distanceMeasure;
	}

	public double computeSimililarity(Vector taskVector, Vector developerVector,
			HashMap<String, Double> weithedTagMap) {

		double[] taskValueArray = taskVector.getValueArray();
		double[] developerValueArray = developerVector.getValueArray();

		// System.out.println("task vector: "+taskVector);
		// System.out.println("developer vector: "+developerVector);
		// System.out.println();

		double minDistance = 0;
		double distance = this.distanceMeasure.compute(taskValueArray, developerValueArray);
		double maxDistance = calcMaxDistance(this.distanceMeasure, taskVector);
		double similarity = 1 - Normalization.calcNormalizedValue(minDistance, maxDistance, distance);

		return similarity;
	}

	public double calcMaxDistance(DistanceMeasure distanceMeasure, Vector vector) {
		double[] maxValue = new double[vector.getLength()];
		double[] minValue = new double[vector.getLength()];
		for (int i = 0; i < vector.getLength(); i++) {
			maxValue[i] = vector.getValueArray()[i];
			minValue[i] = 0;
		}

		return distanceMeasure.compute(maxValue, minValue);
	}

	public static void main(String[] args) {

		DistanceMeasure distance = new ManhattanDistance();

		double[] taskArray1 = { 1.0, 1.0, 1.0, 1.0 };
		double[] devArray1 = { 0.1, 0.2, 0.2, 0.5 };
		double[] devArray2 = { 0.9, 0.9, 1.0, 1.0 };

		double sim1 = Normalization.calcNormalizedValue(0, taskArray1.length, distance.compute(taskArray1, devArray1));
		double sim2 = Normalization.calcNormalizedValue(0, taskArray1.length, distance.compute(taskArray1, devArray2));

		System.out.println("devArray1: " + (1 - sim1));
		System.out.println("devArray2: " + (1 - sim2));

		// double[] devArray3 = { 0, 0, 1, 0, 0 };
		// double[] devArray4 = { 0, 0, 0, 1, 0 };
		// double[] devArray5 = { 0, 0, 0, 0, 1 };
		// double[] devArraySum = { 0.2, 0.2, 0.2, 0.2, 0.2 };
		//
		// DistanceMeasure distance = new EuclideanDistance();
		// System.out.println("devArray1: " + distance.compute(taskArray,
		// devArray1));
		// System.out.println("devArray2: " + distance.compute(taskArray,
		// devArray2));
		// System.out.println("devArray3: " + distance.compute(taskArray,
		// devArray3));
		// System.out.println("devArray4: " + distance.compute(taskArray,
		// devArray4));
		// System.out.println("devArray5: " + distance.compute(taskArray,
		// devArray5));
		// System.out.println("devArraySum: " + distance.compute(taskArray,
		// devArraySum));

		// double[] taskArray = { 1, 1, 1, 1, 1 };
		//
		// double[] devArray1 = { 0, 0, 0, 0, 0 };
		// double[] devArray2 = { 0.2, 0.2, 0.5, 0.1, 0.5 };
		// double[] devArray3 = { 1, 1, 1, 1, 1 };
		//
		// double[] taskArray2 = { 1, 1, 1, 1, 1,1, 1, 1, 1, 1 };
		// double[] devArray4 = { 0, 0, 0, 0, 0,0, 0, 0, 0, 0 };
		// double[] devArray5 = { 0.8, 0.5, 0.5, 0.3, 0.5,0.5, 0.1, 0.4, 0.5, 0
		// };
		// double[] devArray6 = { 1, 1, 1, 1, 1,1, 1, 1, 1, 1 };

		// DistanceMeasure distance = new ManhattanDistance();
		// SimilarityMetric metric = new SimilarityMetric(distance);
		// System.out.println("Similarity 1: " + distance.compute(taskArray,
		// devArray1));
		// System.out.println("Similarity 2: " + distance.compute(taskArray,
		// devArray2));
		// System.out.println("Similarity 3: " + distance.compute(taskArray,
		// devArray3));
		//
		// System.out.println("Normalized Similarity 1: " +
		// metric.computeSimililarity1(taskArray, devArray1));
		// System.out.println("Normalized Similarity 2: " +
		// metric.computeSimililarity1(taskArray, devArray2));
		// System.out.println("Normalized Similarity 3: " +
		// metric.computeSimililarity1(taskArray, devArray3));
		//
		// System.out.println();
		//
		// System.out.println("Similarity 4: " + distance.compute(taskArray2,
		// devArray4));
		// System.out.println("Similarity 5: " + distance.compute(taskArray2,
		// devArray5));
		// System.out.println("Similarity 6: " + distance.compute(taskArray2,
		// devArray6));
		//
		// System.out.println("Normalized Similarity 4: " +
		// metric.computeSimililarity1(taskArray2, devArray4));
		// System.out.println("Normalized Similarity 5: " +
		// metric.computeSimililarity1(taskArray2, devArray5));
		// System.out.println("Normalized Similarity 6: " +
		// metric.computeSimililarity1(taskArray2, devArray6));
		// }

	}
}
