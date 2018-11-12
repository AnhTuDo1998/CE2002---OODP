import java.util.*;
import java.io.*;
/**
 * A controller class, in charge of most services related to Course objects
 */
public class CourseManager{
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
    public int regStudentToCourse(Student student, Course course, String group, String type){
        int result = -3;
        ArrayList<Session> indexList = course.getAllSession();
        for(int i = 0; i < indexList.size();  i++){
            if(indexList.get(i).getGroup().equals(group) && indexList.get(i).getType().equals(type)){
                result = indexList.get(i).addStudent(student);
                //result is -1 if full, -2 if student is already inside, 0 if success
            }
        }
        return result;
    }
    
    /**
     * A method to check and print out the vacancy of a {@link Course} object.
     */
    public void checkVacancy(Course course){
        if (course != null){
            course.printSessions();
        }
        else{
            System.out.println("Course does not exist");
        } 
    }

    /**
     * A method to print out {@link Student} based on {@link Session}. 
     * <p> The method get input about Session of interest from user and print out the Student under that Session.
     * @param course The Course where we need to print out Student listing based on Sessions.
     * @see Session#printSessionStudent()
     */
    public void printSessionStudent(Course course){
        Scanner sc = new Scanner(System.in);
        if(course != null){
            course.printIndexList();
            System.out.println("Please enter the group ID: (SEP1/CE3)");
            String sessionGroup = sc.nextLine();
            System.out.println("Please enter the session type: (LEC/TUT/LAB)");
            String sessionType = sc.nextLine();
            Session group = course.getSession(sessionGroup, sessionType); //return null if not found
            if(group != null){
                System.out.println(course);
                System.out.println(group);
                System.out.println("========================================================================================");
                group.printSessionStudent(); //if exist go ahead and print list
            }else{
                System.out.println("Session doesn't exist!"); 
            }
        }else{
            System.out.println("Course doesn't exist!");
        }
    }
    
    /**
     * A method to print out related {@link Course} statistics (for example number of Student in different year, different gender, grades,...)
     * @param course Course object we need to print out statistics.
     */
    public void printCourseStats(Course course){
        ArrayList<Assessment> assessmentList = course.getAssessment();
        ArrayList<Session> sessionList = course.getAllSession();
        ArrayList<Student> registeredStudents = new ArrayList<Student>();
        Set<Student> buffer = new HashSet<>();
        int[] results = new int[10];
        //index 0 = A+, 1 = A....9 = F
        int totalMale = 0;
        int totalFemale = 0;
        int[] year = new int[4];
        int totalResults = 0;
        int i = 0;
        //index 0 = year1, 1 = year2, 2 = year3, 3 = year4 
        for(i = 0; i < sessionList.size(); i++){
            buffer.addAll(sessionList.get(i).getStudentRegistered());
            //get all registered students, using set to remove duplicates
        }
        registeredStudents.addAll(buffer);
        ScrameApp.printSpaces();
        for(i = 0; i < registeredStudents.size(); i++){
            year[registeredStudents.get(i).getAcadYear()-1]++;
            if(registeredStudents.get(i).getGender() == ('M')) totalMale++;
            else totalFemale++;
            totalResults = 0;
            for(int j = 0; j < assessmentList.size(); j++){
                totalResults += assessmentList.get(j).retrieveAssessmentResult(registeredStudents.get(i)) * assessmentList.get(j).getWeightage() / 100;
            }
            if(totalResults > 90){
                results[0]++;
            }else if(totalResults >80){
                results[1]++;
            }else if(totalResults >75){
                results[2]++;
            }else if(totalResults >70){
                results[3]++;
            }else if(totalResults >60){
                results[4]++;
            }else if(totalResults >50){
                results[5]++;
            }else if(totalResults >45){
                results[6]++;
            }else if(totalResults >40){
                results[7]++;
            }else if(totalResults >30){
                results[8]++;
            }else results[9]++;
        }
        System.out.println("Printing course statistics for " + course);
        System.out.println("========== Gender Distribution ========");
        // i is currently the total number of students in this course
        System.out.printf("Number of Males    : %1$-6s (%2$-5.2f%%)\n", totalMale, ((double)(totalMale)/i)*100);
        System.out.printf("Number of Females  : %1$-6s (%2$-5.2f%%)\n", totalFemale, ((double)(totalFemale)/i)*100);
        System.out.println("========== Year Distribution ==========");
        System.out.printf("Number of Year 1   : %1$-6s (%2$-5.2f%%)\n", year[0], ((double)(year[0])/i)*100);
        System.out.printf("Number of Year 2   : %1$-6s (%2$-5.2f%%)\n", year[1], ((double)(year[1])/i)*100);
        System.out.printf("Number of Year 3   : %1$-6s (%2$-5.2f%%)\n", year[2], ((double)(year[2])/i)*100);
        System.out.printf("Number of Year 4   : %1$-6s (%2$-5.2f%%)\n", year[3], ((double)(year[3])/i)*100);
        System.out.println("========== Grade Distribution =========");
        System.out.printf("Number of A+       : %1$-6s (%2$-5.2f%%)\n", results[0], ((double)(results[0])/i)*100);
        System.out.printf("Number of A        : %1$-6s (%2$-5.2f%%)\n", results[1], ((double)(results[1])/i)*100);
        System.out.printf("Number of A-       : %1$-6s (%2$-5.2f%%)\n", results[2], ((double)(results[2])/i)*100);
        System.out.printf("Number of B+       : %1$-6s (%2$-5.2f%%)\n", results[3], ((double)(results[3])/i)*100);
        System.out.printf("Number of B        : %1$-6s (%2$-5.2f%%)\n", results[4], ((double)(results[4])/i)*100);
        System.out.printf("Number of B-       : %1$-6s (%2$-5.2f%%)\n", results[5], ((double)(results[5])/i)*100);
        System.out.printf("Number of C+       : %1$-6s (%2$-5.2f%%)\n", results[6], ((double)(results[6])/i)*100);
        System.out.printf("Number of C        : %1$-6s (%2$-5.2f%%)\n", results[7], ((double)(results[7])/i)*100);
        System.out.printf("Number of D        : %1$-6s (%2$-5.2f%%)\n", results[8], ((double)(results[8])/i)*100); System.out.printf("Number of A        : %1$-6s (%2$-5.2f%%)\n", results[0], ((double)(results[0])/i)*100);
        System.out.printf("Number of F        : %1$-6s (%2$-5.2f%%)\n", results[9], ((double)(results[9])/i)*100);
    }



    /**
     * A method to de-register {@link Student} from {@link Course}. This is done by calling {@link Course#deregisterStudent(Student)}
     * @param course Course object to be access and remove Student from
     * @param student Student object to remove from the course
     * @return int indicating status of the procedure
     */
    public int deregisterStudent(Course course, Student student){
        int sessionCount = 0;
        ArrayList<Session> indexList = course.getAllSession();
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
     * An utility method to print out all {@link Session} existing under the calling Course object.
     * <p> This method traverse the ArrayList of sessions stored in the Course and print out the Session objects visited.
     * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html">ArrayList</a>
     */
    public void printIndexList(Course course){
        int i;

        System.out.println("Sessions for " + this.courseName + " " + this.courseCode);
        for(i = 0; i < indexList.size(); i++){
            System.out.println(indexList.get(i));
        }
    }

    /**
     * A method to return {@link Session} object under a {@link Course} object being parsed in. This is done by calling {@link }
     * @param course Course object of interest.
     */
    public Session getCourseSession(Course course){
        int i;
        Session obtained = null;
        ArrayList<Session> indexList = course.getAllSession();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Session Group ID (CE1/SEP3)");
        String group = sc.nextLine();
        System.out.println("Enter the Session Type (LAB/TUT/LEC CE1)");
        String type = sc.nextLine();
        if(group.isEmpty() || type.isEmpty()) throw new StringIndexOutOfBoundsException();
        //get the session from database   

        for(i = 0; i < indexList.size(); i++){
            if(indexList.get(i).getType().equals(type) && indexList.get(i).getGroup().equals(group)){
                obtained = indexList.get(i);
                break;
            }
        }
        return obtained;
    }

    public void printStudentRegistered(Course course){
        ArrayList<Session> sessionList = course.getAllSession();
        ArrayList<Student> registeredStudents = new ArrayList<Student>();
        Set<Student> buffer = new HashSet<>();
        int i=0;
        for(i = 0; i < sessionList.size(); i++){
            buffer.addAll(sessionList.get(i).getStudentRegistered());
            //get all registered students, using set to remove duplicates
        }
        registeredStudents.addAll(buffer);
        System.out.println("============================================================================");
        System.out.println("========================== Student Registered ==============================");
        for(i = 0; i < registeredStudents.size(); i++){
            System.out.println(registeredStudents.get(i));
        }
        System.out.println("============================================================================");
    }


}