package developer;

import java.io.Serializable;

import org.apache.commons.math3.distribution.GeometricDistribution;

public class DeveloperProfile implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1880229101395048913L;
	private CurriculumSource curriculumSource;
	private DevelopmentHistory developmentHistory;

	public DeveloperProfile() {
		this.curriculumSource = new CurriculumSource();
		this.developmentHistory = new DevelopmentHistory();
	}

	public CurriculumSource getCurriculumSource() {
		return curriculumSource;
	}

	public void setCurriculumSource(CurriculumSource curriculumSource) {
		this.curriculumSource = curriculumSource;
	}

	public DevelopmentHistory getDevelopmentHistory() {
		return developmentHistory;
	}

	public void setDevelopmentHistory(DevelopmentHistory developmentHistory) {
		this.developmentHistory = developmentHistory;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>> CURRICULUM PROFILE <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
		builder.append(System.lineSeparator());
		builder.append(getCurriculumSource().toString());
		builder.append(System.lineSeparator());
		builder.append(System.lineSeparator());
		builder.append(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>> DEVELOPMENT HISTORY PROFILE <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
		builder.append(System.lineSeparator());
		builder.append(getDevelopmentHistory().toString());
		builder.append(System.lineSeparator());
		
		return builder.toString();
	}

}
