import java.util.*;
import java.io.*;

public class Assessment implements Serializable{

    private String assessmentName;
    private Double weightage;
	private HashMap<Student, Double> assessmentResults = new HashMap<Student, Double>();
	
	
    public Assessment(String name, double weightage){
        this.assessmentName = name;
        this.weightage = weightage;
    }

    public String getAssessmentName(){
        return this.assessmentName;
    }

    public Double getWeightage(){
        return this.weightage;
    }

    public void setAssessmentName(String assessmentName){
        this.assessmentName = assessmentName;
    }
	
    public void setWeightage(Double weightage){
        this.weightage = weightage;
    } 
	
	public void storeAssessmentResult(Student student, double marks){
		assessmentResults.put(student, marks);
	}

	public double retrieveAssessmentResult(Student student){
		if(assessmentResults.get(student)!= null){
			return assessmentResults.get(student);
		}
		else{
			return 0;  // for checking purpose
		}
	}
	public boolean removeAssessmentResult(Student student){
		boolean success = false; 
		if(assessmentResults.get(student) != null){
			assessmentResults.remove(student);
			success = true;
		}
		return success;
	}
	

}