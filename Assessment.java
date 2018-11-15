import java.util.*;
import java.io.*;

/**
 * An entity class for storing information about Assessment components (for example Exam and Coursework) of {@link Student}.
 * The Assessment objects are stored in an ArrayList under the entity {@link Course} class.
 */
public class Assessment implements Serializable{
	/**
	 * The name of the Assessment component.
	 */
	private String assessmentName;
	/**
	 * The weightage of the Assessment component.
	 */
	private Double weightage;
	/**
	 * A HashMap storing marks of Student. Student is key and marks is the stored value.
	 */
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
	
	/**
	 * A method to store store the marks of {@link Student} by inserting
	 * into a HashMap of Student (key) and marks(value).
	 * @param student Student student object to store mark/grade in.
	 * @param marks double the mark/grad to be stored in for the Student object.
	 * @see <a href = "https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html"> HashMap </a>
	 */
	public void storeAssessmentResult(Student student, double marks){
		assessmentResults.put(student, marks);
	}

	/**
	 * A method to return the grade/mark of the calling Assessment object for the {@link Student} object being parsed in.
	 * @param student Student student who grade/mark to be searched and returned.
	 * @return double the grade/mark of the Assessment of the student being parse in if succeed, else return 0.
	 * @see <a href = "https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html"> HashMap </a>
	 */
	public double retrieveAssessmentResult(Student student){
		if(assessmentResults.get(student)!= null){
			return assessmentResults.get(student);
		}
		else{
			return 0;  // for checking purpose
		}
	}

	/**
	 * A method to remove the grade/mark of {@link Student} object under the calling Assessment object.
	 * @param student Student object whose Assessment result is removed.
	 * @return boolean false if the removal of Assessement result for the Student is failed, return true if the removal is successful.
	 * @see <a href = "https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html"> HashMap </a>
	 */
	public boolean removeAssessmentResult(Student student){
		boolean success = false; 
		if(assessmentResults.get(student) != null){
			assessmentResults.remove(student);
			success = true;
		}
		return success;
	}
	

}