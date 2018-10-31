import java.util.*;
public class Session{
    private String type; //lec OR tut or lab
    private String group;
    private String dayTime;
    private String location;
    private String tutorName;
    private int maxCapacity;
    private int numberRegistered;
    private ArrayList<Student> studentList = new ArrayList<Student>();
    
    //create a session
    public Session(String type, String group, String dayTime, String location, String tutorName, int maxCapacity, int numberRegistered){
        this.type = type;
        this.group = group;
        this.dayTime = dayTime;
        this.tutorName = tutorName;
        this.maxCapacity = maxCapacity;
        this.numberRegistered = numberRegistered;
        this.location = location;
    }

    public String getType(){ //return type of session e.g. LEC, LAB, TUT
        return this.type;
    }

    public String getGroup(){ //return group name e.g. SEP1
        return this.group;
    }

    public String dayTime(){  //return the date and time for the session
        return this.dayTime;
    }

    public String tutorName(){ //return the name of tutor
        return this.tutorName;
    }

    public int maxCapacity(){
        return this.maxCapacity;
    }

    public String location(){
        return this.location;
    }
    
    public int numberRegistered(){
        return this.numberRegistered;
    }

    public addStudent(Student s){ //add a student into an existing group
        if(this.maxCapacity == this.numberRegistered){
            return -1; //group is already full
        }else{
            this.numberRegistered++;
            System.out.println("Student added to " + getType() + " " + getGroup());
            return 1; //student added
        }
    }
    

}