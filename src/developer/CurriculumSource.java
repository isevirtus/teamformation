package developer;

import java.io.Serializable;
import java.util.ArrayList;

import project.Tag;
import util.TagUtil;

public class CurriculumSource implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5563285832114379379L;
	private ArrayList<Tag> curriculumTagList;

	public CurriculumSource() {
		this.curriculumTagList = new ArrayList<Tag>();
	}

	public ArrayList<Tag> getCurriculumTagList() {
		return curriculumTagList;
	}

	public void setCurriculumTagList(ArrayList<Tag> curriculumTagList) {
		this.curriculumTagList = curriculumTagList;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CURRICULUM TAG LIST: ");
		builder.append(getCurriculumTagList().toString());
		
		return builder.toString();
	}

}
