import java.util.*;

public class Course{
    private ArrayList<Index> indexList = new ArrayList<Index>();
    private String courseName;
    private String courseCode;
    private int AU;
    private String courseCoordinator;
    private ArrayList<Assessment> results = new ArrayList<Assessment>();

    public String getCourseName(){
        return this.courseName;
    }

    public String getCourseCode(){
        return this.courseCode;
    }

    public int getAU(){
        return this.AU;
    }

    public String getCourseCoordinator(){
        return this.courseCoordinator;
    }

}