package similarity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import dataset.ProjectDataLoading;
import project.Project;
import project.Tag;
import project.Task;
import util.HashMapUtil;
import util.ArrayListUtil;
import util.ProjectUtil;
import util.TagUtil;
import util.TaskUtil;

public class TFIDF {

	private ArrayList<Project> projectList;
	private HashMap<String, Double> termMapping;

	public TFIDF(ArrayList<Project> projectList) {
		this.projectList = projectList;
		this.termMapping = new HashMap<String, Double>();
		createTermMapping();

	}

	public double getTermWeight(String term) {
		return this.termMapping.get(term);
	}

	public void createTermMapping() {
		ArrayList<Task> taskList = ProjectUtil.getTasks(this.projectList);
		ArrayList<Tag> tagList = TaskUtil.getTags(taskList);
		ArrayList<String> tagKeyList = TagUtil.getUniqueTagKeys(tagList);
		for (int i = 0; i < tagKeyList.size(); i++) {
			String term = tagKeyList.get(i);
			int termFrequencyInDocument = calcNumberOfTimesTermAppearsInThedocument(term);
			int numberOfTermsInDocument = calcTotalNumberOfTermsInTheDocument();
			int totalNumberOfDocuments = calcTotalNumberOfDocuments();
			int numberOfDocumentsWithTermInIt = calcNumberOfDocumentsWithTermInIt(term);
//			System.out.print(
//					"termFrequencyInDocument = " + termFrequencyInDocument + " | " + " numberOfTermsInDocument = "
//							+ numberOfTermsInDocument + " totalNumberOfDocuments = " + totalNumberOfDocuments
//							+ " numberOfDocumentsWithTermInIt = " + numberOfDocumentsWithTermInIt + " | ");
//			System.out.print(term + " | ");
			double tfidf = calcTFIDF(termFrequencyInDocument, numberOfTermsInDocument, totalNumberOfDocuments,
					numberOfDocumentsWithTermInIt);
		//	System.out.println();

			this.termMapping.put(term, tfidf);
		}
	}

	public static double calcTFIDF(int termFrequencyInDocument, int numberOfTermsInDocument, int totalNumberOfDocuments,
			int numberOfDocumentsWithTermInIt) {
		double tf = (double) termFrequencyInDocument / numberOfTermsInDocument;
		double idf = Math.log10(1 + ((double) totalNumberOfDocuments / numberOfDocumentsWithTermInIt));
		double tfidf = tf * idf;

		return tfidf;
	}

	public int calcNumberOfTimesTermAppearsInThedocument(String term) {
		int times = 0;
		ArrayList<Task> taskList = ProjectUtil.getTasks(this.projectList);
		for (int i = 0; i < taskList.size(); i++) {
			if (TagUtil.containsTag(taskList.get(i).getTagList(), term)) {
				times++;
			}
		}
		return times;
	}

	public int calcTotalNumberOfTermsInTheDocument() {
		int total = 0;
		ArrayList<Task> taskList = ProjectUtil.getTasks(this.projectList);
		for (int i = 0; i < taskList.size(); i++) {
			total += taskList.get(i).getTagList().size();
		}
		return total;
	}

	public int calcTotalNumberOfDocuments() {
		return this.projectList.size();
	}

	public int calcNumberOfDocumentsWithTermInIt(String term) {
		int documentWithTermsInIt = 0;

		for (int i = 0; i < this.projectList.size(); i++) {
			ArrayList<Task> taskList = ProjectUtil.getTasks(this.projectList.get(i));
			for (int j = 0; j < taskList.size(); j++) {
				if (TagUtil.containsTag(taskList.get(j).getTagList(), term)) {
					documentWithTermsInIt++;
					break;
				}
			}
		}
		return documentWithTermsInIt;
	}

	public String toString() {
		return HashMapUtil.toStringStringDouble(this.termMapping);
	}
}
