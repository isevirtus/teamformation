package validation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class SaveResultsInCSV {

	public static void saveParamters(String filename, ArrayList<Parameter> parameterList) throws IOException {

		PrintWriter pw = new PrintWriter(new File(filename));
		StringBuilder sb = new StringBuilder();
		sb.append("Configuration"); //#1
		sb.append(";");
		sb.append("Distance Measure"); //#2
		sb.append(";");
		sb.append("Optimal Solution"); //#3
		sb.append(";");
		sb.append("Found Solution"); //#4
		sb.append(";");
		sb.append("Offspring Selector"); //#5
		sb.append(";");
		sb.append("Suvivor Selector"); //#6
		sb.append(";");
		sb.append("Crossover Rate"); //#7
		sb.append(";");
		sb.append("Mutation Rate"); //#8
		sb.append(";");
		sb.append("Population Size"); //#9
		sb.append(";");
		sb.append("Steady Fitness"); //#10
		sb.append(";");
		sb.append("Origin Generation"); //#11
		sb.append(";");
		sb.append("Generation Count"); //#12
		sb.append(";");
		sb.append("Fitness Value"); //#13
		sb.append(";");
		sb.append("Match Rate"); //#14
		sb.append(";");
		sb.append("Time"); //#15
		sb.append(";");
		sb.append("\n");

		for (int i = 0; i < parameterList.size(); i++) {
			Parameter parameter = parameterList.get(i);
			sb.append(parameter.getConfigurationId()); //#1
			sb.append(";");
			sb.append(parameter.getDistanceMeasure()); //#2
			sb.append(";");
			sb.append(parameter.getOptimalSolution()); //#3
			sb.append(";");
			sb.append(parameter.getFoundSolution()); //#4
			sb.append(";");
			sb.append(parameter.getOffspringSelector()); //#5
			sb.append(";");
			sb.append(parameter.getSurvivorsSelector()); //#6
			sb.append(";");
			sb.append(String.format("%.2f", Double.valueOf(parameter.getCrossOverRate()))); //#7
			sb.append(";");
			sb.append(String.format("%.2f", Double.valueOf(parameter.getMutationRate()))); //#8
			sb.append(";");
			sb.append(Integer.valueOf(parameter.getPopulationSize())); //#9
			sb.append(";");
			sb.append(Integer.valueOf(parameter.getSteadyFitness())); //#10
			sb.append(";"); 
			sb.append(Integer.valueOf(parameter.getOriginGeneration())); //#11
			sb.append(";");
			sb.append(Integer.valueOf(parameter.getGenerationCount())); //#12
			sb.append(";");
			sb.append(String.format("%.4f", Double.valueOf(parameter.getFitnessValue()))); //#13
			sb.append(";");
			sb.append(String.format("%.4f", Double.valueOf(parameter.getMatchRate()))); //#14
			sb.append(";");
			sb.append(String.format("%.6f",Double.valueOf( parameter.getTime()))); //#15
			sb.append("\n");
		}

		pw.write(sb.toString());
		pw.close();
		System.out.println("Your CVS file with stats has been generated!");
	}

	public static void saveAverageStats(String filename, ArrayList<AverageStats> averageStatsList) throws IOException {
		PrintWriter pw = new PrintWriter(new File(filename));
		StringBuilder sb = new StringBuilder();

		sb.append("Configuration");
		sb.append(";");
		sb.append("Population Size");
		sb.append(";");
		sb.append("Steady Fitness");
		sb.append(";");
		sb.append("Origin Generation");
		sb.append(";");
		sb.append("Generation Count");
		sb.append(";");
		sb.append("Match Rate Average");
		sb.append(";");
		sb.append("Time Average");
		sb.append("\n");

		for (int i = 0; i < averageStatsList.size(); i++) {
			AverageStats averageStats = averageStatsList.get(i);
			sb.append(averageStats.getConfigurationId());
			sb.append(";");
			sb.append(String.format("%.2f",Double.valueOf(averageStats.getPopulationSize())));
			sb.append(";");
			sb.append(String.format("%.2f",Double.valueOf(averageStats.getSteadyFitness())));
			sb.append(";");
			sb.append(String.format("%.2f", Double.valueOf(averageStats.getOriginGenerationAverage())));
			sb.append(";");
			sb.append(String.format("%.2f", Double.valueOf(averageStats.getGenerationCount())));
			sb.append(";");
			sb.append(String.format("%.4f", Double.valueOf(averageStats.getMatchRateAverage())));
			sb.append(";");
			sb.append(String.format("%.6f",Double.valueOf( averageStats.getTimeAverage())));
			sb.append("\n");
		}

		pw.write(sb.toString());
		pw.close();
		System.out.println("Your CSV file with medians has been generated!");

	}

	public static void main(String[] args) {
		double a = 0.74338276272;
		System.out.println(String.format("%.2f",a));
	}

}
