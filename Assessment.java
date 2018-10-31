import java.util.*;

public class Assessment{

    private String assessmentName;
    private Double weightage;

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

}