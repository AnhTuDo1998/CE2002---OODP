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
     * A method to add in new {@link Session} under the calling Course object (making use of ArrayList for storage). 
     * @return boolean false if the new Session object is not added, true if the new Session object is added
     * @see <a href = https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html"> HashMap </a>
     */
    public boolean addSession() throws StringIndexOutOfBoundsException{
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
        if(type.isEmpty() || group.isEmpty() || dayTime.isEmpty() || location.isEmpty()|| tutorName.isEmpty()){
            throw new StringIndexOutOfBoundsException();
        }
        if(getSession(group, type) != null){
            System.out.println("This session is already in!");
        }else if(maxCapacity < 1){
            System.out.println("Please enter a valid capacity!");
        }else{
            success = indexList.add(new Session(type, group, dayTime, location, tutorName, maxCapacity, 0));
        }
        return success;
    }

    /**
     * A method to modify information about existing {@link Session}. 
     * <p> The method parse in the Session object need to be modified, following by accepting inputs from users to change the wanted
     * field of information for that session.
     * @param session The Session that need modification (for example changing any of the following: Type, Group ID, Timing, ...)
     * @return boolean true if the modification is successful and false if otherwise.
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
                    System.out.println("Old session's type: " + session.getType());
                    System.out.println("Enter new session's type: (LEC/TUT/LAB)");
                    String type = sc.nextLine();
                    session.setType(type);
                    success = true;
                    break;
                case 2:
                    System.out.println("Old session's group: " + session.getGroup());
                    System.out.println("Enter new session's group : (SEP1/CE3/SEA2)");
                    String group = sc.nextLine();
                    session.setGroup(group);
                    success = true;
                    break;
                case 3:
                    System.out.println("Old session's timing: " + session.getDayTime());
                    System.out.println("Enter new session's timing: (Mon 15:00 - 17:00/ Fri 09:00 - 11:00)");
                    String dayTime = sc.nextLine();
                    session.setDayTime(dayTime);
                    success = true;
                    break;
                case 4:
                    System.out.println("Old session's location: " + session.getLocation());
                    System.out.println("Enter new session's location: (LT19a/TRx44/SWLAB3)");
                    String location = sc.nextLine();
                    session.setLocation(location);
                    success = true;
                    break;
                case 5:
                    System.out.println("Old session's tutor name: " + session.getTutorName());
                    System.out.println("Enter new session's tutor name: ");
                    String tutorName = sc.nextLine();
                    session.setTutorName(tutorName);
                    success = true;
                    break;
                case 6:
                    System.out.println("Old session's max capacity: " + session.getMaxCapacity());
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
            System.out.println("Modified session: "+ session.toString() + " | Max Capacity: " +session.getMaxCapacity());
            System.out.println("Do you wish to continue modifying the session (Y/N)");
            conti = sc.nextLine().toUpperCase().charAt(0);
        }while(conti == 'Y');
        return success;         
    }

    /**
     * An utility method to print out all {@link Session} existing under the calling Course object.
     * <p> This method traverse the ArrayList of sessions stored in the Course and print out the Session objects visited.
     * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html">ArrayList</a>
     */
    public void printIndexList(){
        int i;

        System.out.println("Sessions for " + this.courseName + " " + this.courseCode);
        for(i = 0; i < indexList.size(); i++){
            System.out.println(indexList.get(i));
        }
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
     * A method to store the {@link Assessment} information under the calling Course object.
     * <p> Making use of the ArrayList of Assessment by adding the parsed in Assessment object into it.
     * @param assessment Assessment component of this Course object. 
     * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html">ArrayList</a>
     */
    public void setAssessment(Assessment assessment){
        results.add(assessment);
    }

    /**
     * A method to register {@link Student} into {@link Session} stored in this Course.
     * <p> This method take in Student object, the group and type of Session needed and then traverse the array of Session objects of the calling Course object and add the Student in by addStudent method from Session class.
     * @param student the Student we need to register
     * @param group the group ID of the Session
     * @param type the type of the Session (LEC, TUT or LAB)
     * @return int 0 if added successfully, -1 if full, -2 if student is inside -3 if group does not exist
     * @see Session#addStudent(Student)
     * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html">ArrayList</a>
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
     * A method to print out all {@link Session} under the calling Course object
     * <p> Traverse the ArrayList of Session objects and print out the information needed.
     * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html">ArrayList</a>
     */
    public void printSessions(){
        for (int i = 0; i < indexList.size(); i++){
            System.out.println(indexList.get(i) + " | Vacancy: " 
            + indexList.get(i).getVacancy() + "/" + indexList.get(i).getMaxCapacity());
        }
    }

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

    /**
     * A method to store result of {@link Student} for an {@link Assessment} component under this Course.
     * @param assessment the component that we need to store the result in (for instance finals, coursework, etc)
     * @param student the student who we need to key in result.
     * @param marks the grade of Student
     * @see Assessment#storeAssessmentResult(Student, double)
     */
    public void enterResults(Assessment assessment, Student student, double marks){
        assessment.storeAssessmentResult(student, marks);
    }
}