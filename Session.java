import java.util.*;
import java.io.*;

/**
 * This is an entity class to contain information about sessions/classes (Lecture, Tutorial, Laboratory)
 */
public class Session implements Serializable{
    private String type; //lec OR tut or lab
    private String group;
    private String dayTime;
    private String location;
    private String tutorName;
    private int maxCapacity;
    private int numberRegistered;
    private ArrayList<Student> studentList = new ArrayList<Student>();
    
    /**
     * Constructor for session object, instantiating the above attributes
     * @param type String type of session (LEC, TUT or LAB for Lecture, Tutorial and Lab)
     * @param group String group ID of the session (CE1, SE3, etc)
     * @param dayTime String timing for the session, following "Day of the week HH:MM - HH:MM" format
     * @param location String describing the venue for the session
     * @param tutorName String containing tutor name for that session
     * @param maxCapacity int vacancy of the session (number of students it can accomodate at the moment)
     * @param numberRegistered int describing number of student currently registered
     */
    public Session(String type, String group, String dayTime, String location, String tutorName, int maxCapacity, int numberRegistered){
        this.type = type;
        this.group = group;
        this.dayTime = dayTime;
        this.tutorName = tutorName;
        this.maxCapacity = maxCapacity;
        this.numberRegistered = numberRegistered;
        this.location = location;
    }

    /**
     * @param type String type of session (LEC, TUT or LAB for Lecture, Tutorial and Lab)
     */
    public void setType(String type){
        this.type = type;
    }

    /**
     * @param group String group ID of the session (CE1, SE3, etc)
     */
    public void setGroup(String group){
        this.group = group;
    }

    /**
     * @param dayTime String timing for the session, following "Day of the week HH:MM - HH:MM" format
     * e.g. Fri 14:30 - 16:30
     */
    public void setDayTime(String dayTime){
        this.dayTime = dayTime;
    }
    
    /**
     * @param tutorName String containing tutor name for that session
     */
    public void setTutorName(String tutorName){
        this.tutorName = tutorName;
    }

    /**
     * @param maxCapacity int describing max number of student can be accomodated by the session
     */
    public void setMaxCapacity(int maxCapacity){
        this.maxCapacity = maxCapacity;
    }

    /**
     * @param numberRegistered int describing number of student currently registered
     */
    public void setNumberRegistered(int numberRegistered){
        this.numberRegistered = numberRegistered;
    }

    /**
     * @param location String describing the venue for the session
     */
    public void setLocation (String location){
        this.location = location;
    }

    /**
     * @return String type of session e.g. LEC, LAB, TUT.
     */
    public String getType(){
        return this.type;
    }

    /**
     * @return String group ID of the session
     */
    public String getGroup(){ 
        return this.group;
    }

    /** 
     * @return String day and time of the session
     */
    public String getDayTime(){  
        return this.dayTime;
    }

    /**
     * @return String name of the tutor for the session
     */
    public String getTutorName(){ 
        return this.tutorName;
    }

    /**
     * @return int max number of students can be accomodated
     */
    public int getMaxCapacity(){
        return this.maxCapacity;
    }

    /**
     * @return String venue of the session
     */
    public String getLocation(){
        return this.location;
    }
    
    /**
     * @return int vacancy of the session (number of students it can accomodate at the moment)
     */
    public int getVacancy(){
        return this.maxCapacity - this.numberRegistered;
    }
    /**
     * @return int number of student currently registered
     */
    public int getNumberRegistered(){
        return this.numberRegistered;
    }

    /**
     * A method to deregister student from the calling session. 
     * @param student Student object to be deregistered (removed) from the calling session 
     * @return false if the process failed
     * @return true if the student is successfully removed from the student list of the session
     */
    public boolean deregisterStudent(Student student){
        if(student != null){
            return studentList.remove(student);
        }
        return false;
    }

    /**
     * A method to add in student into the calling session
     * @param student Student student object to be added into this session
     * @return int -1 if the session is full (vacancy = 0)
     * @return int -2 if the student being added is already inside the session
     * @return int 0 if the adding of student under the session is SUCCESS
     */
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
    
    /**
     * Print out the list of student currently registered in this session
     */
    public void printSessionStudent(){
        int i;
        for(i = 0; i < studentList.size(); i++){
            System.out.println(studentList.get(i));
        }
    }

    /**
     * Overided toString() method to generate a string with information about the session
     */
    public String toString(){
        return (this.type + " | Group ID: " + this.group + " | Timings:  " + this.dayTime + "  | Venue: " + this.location + " | TA Name: " + getTutorName());
    }

    /**
     * A getter method to access the array of student currently registered in this session
     * @return studentList ArrayList<Student> an array of students currently registered
     */
    public ArrayList<Student> getStudentRegistered(){
        return this.studentList;
    }
}