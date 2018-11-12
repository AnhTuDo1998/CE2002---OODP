import java.util.*;
import java.io.*;
/**
 * 
 */
public class Course implements Serializable{
    //Record of Session (Tut, Lab and Lecture) under a course.
    private ArrayList<Session> indexList = new ArrayList<Session>();
    //Record of Assessment (Exam and Coursework) under a course.
    //index 0 is equivalent to exam marks, the rest are coursemarks.
    private ArrayList<Assessment> results = new ArrayList<Assessment>();  //arrayList of hashmaps.

    //Other relevant information of a course.
    private String courseName;
    private String courseCode;
    private int AU;
    private String courseCoordinator;

    /**
     * Constructor for Course object, instantiating the following attributes
     * @param courseName String name of the course object being construct, for instance "Object Oriented Design and Programming".
     * @param courseCode String course code of the course object being construct, for instance CE2002
     * @param AU int AU of the course object being construct.
     * @param courseCoordinator String name of the overall coordinator of the course object being created. 
     */
    public Course(String courseName, String courseCode, int AU, String courseCoordinator){
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.AU = AU;
        this.courseCoordinator = courseCoordinator;
    }

    /**
     * A method to get the course name of the calling Course object. For example, Object Oriented Design and Programming.
     * @return String the name of the calling Course object.
     */
    public String getCourseName(){
        return this.courseName;
    }
    /**
     * A method to get the course code of the calling Course objcect, for example CE2002.
     * @return String the course code of the calling Course object.
     */
    public String getCourseCode(){
        return this.courseCode;
    }
    /**
     * A method to get the AU assigned to the calling Course object
     * @return int the AU of the calling Course object.
     */
    public int getAU(){
        return this.AU;
    }

    /**
     * A method to return the name of the overall coordinator of the calling Course object.
     * @return String the name of the coordinator of the calling Course object
     */
    public String getCourseCoordinator(){
        return this.courseCoordinator;
    }
    
        /**
     * A method to return a {@link Session} under the calling Course object
     * @param group the group ID of the Session of interest
     * @param type the type of Session of interest (LEC, TUT or LAB)
     * @return Session the session object of interest.
     */
    public Session getSession(String group, String type){
        int i;
        Session obtained = null;
        for(i = 0; i < indexList.size(); i++){
            if(indexList.get(i).getType().equals(type) && indexList.get(i).getGroup().equals(group)){
                obtained = indexList.get(i);
                break;
            }
        }
        return obtained;
    }

    /**
     * A method to remove {@link Student} from all {@link Session} and erase his/her {@link Assessment} data under the calling Course object.
     * <p> This is done by first traversing the ArrayList of Session, getting the ArrayList of Student objects registered under each
     * Session. Then we search for the matching Student in the current ArrayList of Student. 
     * <p>If there is a valid, matching entry of Student, 
     * we remove that Student and update the number of Student registered under that Session. 
     * Else, continue until no more unvisited Student under the current Session.
     * <p> We then remove all the Assessment results of the Student by traversing and removing from the array list of hashmap that stored all 
     * result for the student for the course.
     * @param student
     * @return int number of Session that the Student were removed from for debugging purpose.
     * @see Session#getStudentRegistered()
     * @see Session#setNumberRegistered(int)
     * @see Assessment#removeAssessmentResult(Student)
     * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html">ArrayList</a>
     * @see <a href = https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html"> HashMap </a>
     */


    /**
     * A method to return the ArrayList of {@link Assessment} objects under the calling Course
     * @return ArrayList<Assessment> the array storiing the Assessment objects (each object correspond to 1 component such as Final exam, quiz, etc)
     * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html">ArrayList</a>
     */
    public ArrayList<Assessment> getAssessment(){
        return this.results;
    }

    /**
     * A method to return String containing the information of the Course object. This can be use for printing out the object.
     * @return String the String containing information of the Course object, formatted.
     */
    public String toString(){
        String codeFormat = "|| %1$6s :";
        String nameFormat = " %2$-30s ";
        String tutorFormat = "| %3$-30s | ";
        String AUFormat = "%4$-1s ||";
        String format = codeFormat.concat(nameFormat).concat(tutorFormat).concat(AUFormat);
        return String.format(format, this.courseCode, this.courseName, "Coordinator: " + this.courseCoordinator, "AU: " + this.AU);
    }

    /**
     * A method to return an ArrayList storing all {@link Session} currently under the calling Course object
     * @return an ArrayList of all Session associated with the calling Course.
     * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html">ArrayList</a>
     */
    public ArrayList<Session> getAllSession(){
        return this.indexList;
    }
    /**
     * A method to validate if {@link Student} is registered under the calling Course or not.
     * @param student the object of Student who we are interest to know his status under this course
     * @return boolean true if the Student is under the Course, else false is returned.
     */
    public boolean studentRegistered(Student student){
        for(int i = 0; i < indexList.size(); i++){
            if(indexList.get(i).getStudentRegistered().contains(student)) return true;
        }
        return false;
    }

    /**
     * A method to clear all {@link Assessment} objects/components under the Course. 
     * <p> This is done my clearing the ArrayList of Assessment object under the Course.
     * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html">ArrayList</a>
     */
    public void clearAssessments(){
        results.clear();
    }


}