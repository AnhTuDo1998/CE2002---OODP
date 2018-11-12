import java.util.*;
import java.io.*;

/**
 * A class for Assessment
 */
public class Assessment implements Serializable{

    private String assessmentName;
    private Double weightage;
	private HashMap<Student, Double> assessmentResults = new HashMap<Student, Double>();
	
	/**
	 * Constructor for an Assessment object/component. The attributes being instantiated are as below:
	 * @param name String the name of the Assessment object being constructed. 
	 * @param weightage double the weightage of the Assessment object (in percentage).
	 */
    public Assessment(String name, double weightage){
        this.assessmentName = name;
        this.weightage = weightage;
    }

	/**
	 * A method to return the name of the calling Assessment object/component. 
	 * @return String the name of calling Assessment object/component.
	 */
    public String getAssessmentName(){
        return this.assessmentName;
    }

	/**
	 * A method to return the weightage of the calling Assessment object/component. 
	 * @return double the weightage of the Assessment object/component.
	 */
    public Double getWeightage(){
        return this.weightage;
    }

	/**
	 * A method to set the name of calling Assessment object/component
	 * @param assessmentName String the name of Assessment object/component being set.
	 */
    public void setAssessmentName(String assessmentName){
        this.assessmentName = assessmentName;
    }
	
	/**
	 * A method to set the weightage of the calling Assessment object/component (in percentage).
	 * @param weightage double the weightage of the Assessment object/component.
	 */
    public void setWeightage(Double weightage){
        this.weightage = weightage;
    } 
}