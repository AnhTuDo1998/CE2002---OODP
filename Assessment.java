import java.util.*;

public class Assessment{

    private String assessmentName;
    private Double weightage;
	private HashMap<String, Double> assessmentResults = new HashMap<String, Double>();
	
	
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
	
	public void storeAssessmentResult(){
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter student's matriculation number: ");
		String matriculationNumber = sc.nextLine();
		System.out.println("Enter student's coursework mark: ");
		double marks = sc.nextDouble();
		System.out.println("Saving results.:");
		assessmentResults.put(matriculationNumber, marks);
		System.out.println("Done.");
	}

	public double retrieveAssessmentResult(String matriculationNumber){
		if(assessmentResults.get(matriculationNumber)!= null){
			return assessmentResults.get(matriculationNumber);
		}
		else{
			return 0;  // for checking purpose
		}
	}
	public boolean removeAssessmentResult(String matriculationNumber){
		boolean success = false;
		if(assessmentResults.get(matriculationNumber) != null){
			assessmentResults.remove(matriculationNumber);
			success = true;
		}
		return success;
	}
	

}