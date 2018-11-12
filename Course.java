import java.util.*;
import java.io.*;

/**
 * Entity class which stores respective assessments and sessions available for this course.
 */
public class Course implements Serializable{

    /**
     * Record of {@link Session} (Tut, Lab and Lecture) under this course.
     */
    private ArrayList<Session> indexList = new ArrayList<Session>();

    /**
     * Record of {@link Assessment} (Exam and Coursework) under this course.
     * Index 0 is reserved for final marks, the rest are coursemarks.
     */
    private ArrayList<Assessment> results = new ArrayList<Assessment>();  //arrayList of hashmaps.

    //Other relevant information of a course.
    private String courseName;
    private String courseCode;
    private int AU;
    private String courseCoordinator;

    /**
     * Constructor for Course object, instantiating the following attributes
     * @param courseName Name of the course object being constructed, for instance "Object Oriented Design and Programming".
     * @param courseCode Course code of the course object being constructed, for instance CE2002
     * @param AU Academic Units allocated to the course object being constructed.
     * @param courseCoordinator Name of the overall coordinator of the course object being created. 
     */
    public Course(String courseName, String courseCode, int AU, String courseCoordinator){
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.AU = AU;
        this.courseCoordinator = courseCoordinator;
    }

    /**
     * A method to get the course name of the calling Course object. For example, Object Oriented Design and Programming.
     * @return The name of the calling Course object.
     */
    public String getCourseName(){
        return this.courseName;
    }
    /**
     * A method to get the course code of the calling Course objcect, for example CE2002.
     * @return The course code of the calling Course object.
     */
    public String getCourseCode(){
        return this.courseCode;
    }
    /**
     * A method to get the AU assigned to the calling Course object
     * @return The AU of the calling Course object.
     */
    public int getAU(){
        return this.AU;
    }

    /**
     * A method to return the name of the overall coordinator of the calling Course object.
     * @return The name of the coordinator of the calling Course object
     */
    public String getCourseCoordinator(){
        return this.courseCoordinator;
    }
    /**
     * A method to add in new Session under the calling Course object. 
     * @return <i>false</i> if the new Session object is not added,
     * return <i>true</i> if the new Session object is added
     * @see Session for Session object usage.
     * @see #indexList
     */
    public boolean addSession(){
        boolean success = false;
        ScrameApp.printSpaces();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter session type: (LEC/TUT/LAB)");
        String type = sc.nextLine();
        System.out.println();
        System.out.println("Enter session group : (SEP1/CE3/SEA2)");
        String group = sc.nextLine();
        System.out.println();
        System.out.println("Enter session timing: (Mon 15:00 - 17:00/ Fri 09:00 - 11:00)");
        String dayTime = sc.nextLine();
        System.out.println();
        System.out.println("Enter session location: (LT19a/TRx44/SWLAB3)");
        String location = sc.nextLine();
        System.out.println();
        System.out.println("Enter session tutor name: ");
        String tutorName = sc.nextLine();
        System.out.println();
        System.out.println("Enter session's max capacity: ");
        int maxCapacity = sc.nextInt();
        System.out.println();
        sc.nextLine(); //capture \n
        if(getSession(group, type) != null){
            System.out.println("This session is already in!");
        }else if(maxCapacity < 1){
            System.out.println("Please enter a valid capacity!");
        }else{
            success = indexList.add(new Session(type, group, dayTime, location, tutorName, maxCapacity, 0));
        }
        return success;
    }

    //Modifying Session
    /**
     * A method to modify information regarding a {@link Session} in a particular {@link Course}.
     * <ul>
     *  <li>Session Type</li>
     *  <li>Session Group ID</li>
     *  <li>Session Timing</li>
     *  <li>Session Location</li>
     *  <li>Tutor's Name</li>
     *  <li>Max capacity of session</li>
     * </ul>
     * @param session The target session to be modified.
     * @return <i>true</i> if modified successfully, <i>false</i> otherwise.
     */
    public boolean modifySession(Session session){
        boolean success = false;
        char conti = 'Y';
        int choice;
        Scanner sc = new Scanner(System.in);
        do{
            System.out.println("Select the field of Session to edit: ");
            System.out.println("1.Type");
            System.out.println("2.Group ID");
            System.out.println("3.Timing");
            System.out.println("4.Location");
            System.out.println("5.Tutor Name");
            System.out.println("6.Max Capacity");
            choice = sc.nextInt();
            sc.nextLine();
            switch(choice){
                case 1:
                    System.out.println("Enter new session type: (LEC/TUT/LAB)");
                    String type = sc.nextLine();
                    session.setType(type);
                    success = true;
                    break;
                case 2:
                    System.out.println("Enter new session group : (SEP1/CE3/SEA2)");
                    String group = sc.nextLine();
                    session.setGroup(group);
                    success = true;
                    break;
                case 3:
                    System.out.println("Enter new session timing: (Mon 15:00 - 17:00/ Fri 09:00 - 11:00)");
                    String dayTime = sc.nextLine();
                    session.setDayTime(dayTime);
                    success = true;
                    break;
                case 4:
                    System.out.println("Enter new session location: (LT19a/TRx44/SWLAB3)");
                    String location = sc.nextLine();
                    session.setLocation(location);
                    success = true;
                    break;
                case 5:
                    System.out.println("Enter new session tutor name: ");
                    String tutorName = sc.nextLine();
                    session.setTutorName(tutorName);
                    success = true;
                    break;
                case 6:
                    System.out.println("Enter session's new max capacity: ");
                    int maxCapacity = sc.nextInt();
                    if(session.getNumberRegistered()> maxCapacity){
                        System.out.println("Please enter a valid capacity!");
                        return false;
                    }
                    session.setMaxCapacity(maxCapacity);
                    sc.nextLine();
                    success = true;
                    break;
                default: 
                    System.out.println("Please select a valid field!");
            }
            System.out.println("Modified session: "+ session.toString());
            System.out.println("Do you wish to continue modifying the session (Y/N)");
            conti = sc.nextLine().toUpperCase().charAt(0);
        }while(conti == 'Y');
    return success;     
        
    }

    /**
     * A method to print all {@link Session} contained in this course.
     */
    public void printIndexList(){
        int i;

        System.out.println("Sessions for " + this.courseName + " " + this.courseCode);
        for(i = 0; i < indexList.size(); i++){
            System.out.println(indexList.get(i));
        }
    }

    /**
     * A method to set the {@link Assessment} information of the calling Course object
     * by storing it in an ArrayList of Assessment.
     * @param assessment Assessment component of this Course object. 
     * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html">ArrayList</a>
     */
    public void setAssessment(Assessment assessment){
        results.add(assessment);
    }

    /**
     * A method to register a {@link Student} for a {@link Session}.
     * @param student Student object to be registered.
     * @param group
     * @param type
     * @return 0 if added successfully, -1 if session is full, -2 if student was previously registered already, -3 if wrong group info was entered.
     */
    public int registerStudent(Student student, String group, String type){
        int result = -3;
        for(int i = 0; i < indexList.size();  i++){
            if(indexList.get(i).getGroup().equals(group) && indexList.get(i).getType().equals(type)){
                result = indexList.get(i).addStudent(student);
                //result is -1 if full, -2 if student is already inside, 0 if success
            }
        }
        return result;
    }

    /**
     * A method to deregister a {@link Student} from this course. All relevant sessions 
     * and results from the target student is also removed.
     * @param student Target student object to be removed from this course.
     * @return int Total count of sessions deregistered the target student.
     */
    public int deregisterStudent(Student student){
        int sessionCount = 0;
        for(int i = 0; i < indexList.size(); i++){
            ArrayList<Student> students = indexList.get(i).getStudentRegistered();
            for(int j = 0; j < students.size(); j++){
                if(students.get(j).equals(student)){
                    sessionCount++;
                    students.remove(student);
                    indexList.get(i).setNumberRegistered(indexList.get(i).getNumberRegistered()-1);
                }
            }

            for(int z = 0; z < results.size(); z++){
                results.get(z).removeAssessmentResult(student);
            }
        }
        return sessionCount;
    }

    /**
     * A method to print all {@link Session} in this course.
     */
    public void printSessions(){
        for (int i = 0; i < indexList.size(); i++){
            System.out.println(indexList.get(i) + " | Vacancy: " 
            + indexList.get(i).getVacancy() + "/" + indexList.get(i).getMaxCapacity());
        }
    }

    /**
     * @return {@link #results}
     */
    public ArrayList<Assessment> getAssessment(){
        return this.results;
    }

    /**
     * For formatted printing in table view.
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
     * @return {@link #indexList}
     */
    public ArrayList<Session> getAllSession(){
        return this.indexList;
    }

    /**
     * A method to check if {@link Student} is already registered in this course, by 
     * iterating through all the {@link Session}.
     * @param student target student object to check.
     * @return <i>true</i> if student is already registered, <i>false</i> if student is not registered.
     */
    public boolean studentRegistered(Student student){
        for(int i = 0; i < indexList.size(); i++){
            if(indexList.get(i).getStudentRegistered().contains(student)) return true;
        }
        return false;
    }

    /**
     * A method to reset existing {@link Assessment}, called in {@link CourseManager#setAssessment}.
     */
    public void clearAssessments(){
        results.clear();
    }

    /**
     * A method to enter marks for a {@link Student} for a particular {@link Assessment}.
     * @param assessment Assessment object to be entered.
     * @param student Student object to be graded.
     * @param marks Marks of the student for the particular assessment (Max marks: 100).
     */
    public void enterResults(Assessment assessment, Student student, double marks){
        assessment.storeAssessmentResult(student, marks);
    }
}