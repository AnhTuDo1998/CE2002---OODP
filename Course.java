import java.util.*;
import java.io.*;

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
     * A method to add in new Session under the calling Course object. 
     * @return boolean false if the new Session object is not added,
     * return true if the new Session object is added
     * @see Session for Session object usage.
     * @see #indexList
     */
    public boolean addSession(){
        boolean success = false;

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter session type: (LEC/TUT/LAB)");
        String type = sc.nextLine();
        System.out.println("Enter session group : (SEP1/CE3/SEA2)");
        String group = sc.nextLine();
        System.out.println("Enter session timing: (Mon 15:00 - 17:00/ Fri 09:00 - 11:00)");
        String dayTime = sc.nextLine();
        System.out.println("Enter session location: (LT19a/TRx44/SWLAB3)");
        String location = sc.nextLine();
        System.out.println("Enter session tutor name: ");
        String tutorName = sc.nextLine();
        System.out.println("Enter session's max capacity: ");
        int maxCapacity = sc.nextInt();
        sc.nextLine(); //capture \n
        if(sessionExist(group, type) >= 0){
            System.out.println("This session is already in!");
        }else if(maxCapacity < 1){
            System.out.println("Please enter a valid capacity!");
        }else{
            success = indexList.add(new Session(type, group, dayTime, location, tutorName, maxCapacity, 0));
        }
        return success;
    }

    //Remove session
    /**
     * A method to remove a Session under this calling Course object.
     * @return boolean false if the removal of the Session is unsuccessful, 
     * return true if the removal is successful.
     * @see #indexList
     */
    public boolean removeSession(){
        boolean success;
        Scanner sc = new Scanner(System.in);
        String group;
        int index;
        String type;

        printIndexList();
        System.out.println("Which session do you want to remove?");
        group = sc.nextLine();
        System.out.println("Remove TUT or LAB?");
        type = sc.nextLine();
        index = sessionExist(group, type);
        if(index >= 0){
            success = indexList.remove(indexList.get(index)); //if removed then return true
            return success;
        }else{
            return false; //else false
        }
    }

    //Modifying Session
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

    //print session catalogue
    public void printIndexList(){
        int i;

        System.out.println("Sessions for " + this.courseName + " " + this.courseCode);
        for(i = 0; i < indexList.size(); i++){
            System.out.println(indexList.get(i));
        }
    }

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
       

    //check if session exist according to group name first
    //may need to add more though
    public int sessionExist(String sessionGroup, String type){
        int i;
        int index = -1;

        for(i = 0; i < indexList.size(); i++){  //return index if found, return -1 if not found
            if(indexList.get(i).getGroup().equals(sessionGroup) && indexList.get(i).getType().equals(type)){
                index = i;
                break;
            }
        }
        return index;
    }


    /**
     * A method to set the Assessment information of the calling Course object.
     * @param assessment Assessment component of this Course object. 
     * @return
     */
    public int setAssessment(Assessment assessment){
        results.add(assessment);
        return 0;
    }

    //return 0 if added successfully, -1 if full, -2 if student is inside -3 if group does not exist
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

    public void printSessions(){
        for (int i = 0; i < indexList.size(); i++){
            System.out.println(indexList.get(i) + " | Vacancy: " 
            + indexList.get(i).getVacancy() + "/" + indexList.get(i).getMaxCapacity());
        }
    }

    public ArrayList<Assessment> getAssessment(){
        return this.results;
    }

    public String toString(){
        String codeFormat = "|| %1$6s :";
        String nameFormat = " %2$-30s ";
        String tutorFormat = "| %3$-30s | ";
        String AUFormat = "%4$-1s ||";
        String format = codeFormat.concat(nameFormat).concat(tutorFormat).concat(AUFormat);
        return String.format(format, this.courseCode, this.courseName, "Coordinator: " + this.courseCoordinator, "AU: " + this.AU);
    }

    public ArrayList<Session> getAllSession(){
        return this.indexList;
    }

    public boolean studentRegistered(Student student){
        for(int i = 0; i < indexList.size(); i++){
            if(indexList.get(i).getStudentRegistered().contains(student)) return true;
        }
        return false;
    }

    public void clearAssessments(){
        results.clear();
    }

    public void enterResults(Assessment assessment, Student student, double marks){
        assessment.storeAssessmentResult(student, marks);
    }
}