import java.util.*;
import java.io.*;

public class Session implements Serializable{
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

    public String getDayTime(){  //return the date and time for the session
        return this.dayTime;
    }

    public String getTutorName(){ //return the name of tutor
        return this.tutorName;
    }

    public int getMaxCapacity(){
        return this.maxCapacity;
    }

    public String getLocation(){
        return this.location;
    }
    
    public int getVacancy(){
        return this.maxCapacity - this.numberRegistered;
    }
    public int getNumberRegistered(){
        return this.numberRegistered;
    }

    //return -1 if full, -2 if student is already inside, 0 if success
    public int addStudent(Student student){ //add a student into an existing group
        if(this.maxCapacity == this.numberRegistered){
            return -1; //group is already full
        } else {
            for(int i = 0; i < studentList.size(); i++){
                if(studentList.get(i) == student) return -2;
            }
            this.numberRegistered++;
            studentList.add(student);
            return 0; //student added
        }
    }
    
    public void printSessionStudent(){
        int i;

        for(i = 0; i < studentList.size(); i++){
            System.out.println(studentList.get(i));
        }
    }

    public String toString(){
        return (this.type + " | Group ID: " + this.group + " | Timings:  " + this.dayTime + "  | Venue: " + this.location);
    }

    public ArrayList<Student> getStudentRegistered(){
        return this.studentList;
    }
}