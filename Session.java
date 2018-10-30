import java.util.*;
public class Session{
    private String type; //lec OR tut or lab
    private String group;
    private String dateTime;
    private String tutorName;
    private int maxCapacity;
    private int numberRegistered;
    private ArrayList<Student> studentList = new ArrayList<Student>();

    public String getType(){ //return type of session e.g. LEC, LAB, TUT
        return this.type;
    }

    public String getGroup(){ //return group name e.g. SEP1
        return this.group;
    }

    public String dateTime(){
        return this.dateTime;
    }

    public String tutorName(){
        return this.tutorName;
    }

    
}