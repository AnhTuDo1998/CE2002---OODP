import java.util.*;
import java.io.*;
/**
 * A controller class, in charge of most services related to Course objects
 */
public class CourseManager{
    /**
     * A method to register {@link Student} object for a given {@link Course} object both being parsed in.
     * @param student Student object to be registered 
     * @param course Course object to add the Student object in
     * @param group String group ID of the Session the Student need to be added
     * @param type String type of Session
     * @return int indicating the status of lower level method
     * @see Course#registerStudent(Student, String, String)
     */
    public int regStudentToCourse(Student student, Course course, String group, String type){
        return course.registerStudent(student, group, type);
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
     * A method to return the array list of {@link Assessment} for a particular Course object being parsed in.
     */
    public ArrayList<Assessment> getAssessment(Course course){
        return course.getAssessment();
    }

    /**
     * A method to set the weightage of {@link Assessment} components of the {@link Course} parsed in.
     * @param course The Course object whose Assessment components need to be set.
     */
    public void setAssessment(Course course){
        String name;
        double weightage = 0;
        double totalWeightage = 0; //total weightage must be equal to 100
        boolean finalsSet = false;
        Scanner sc = new Scanner(System.in);
        char confirm = 'N';
        course.clearAssessments(); //reset assessments if already set;
        
        while(totalWeightage!=100){
            try{
                if(finalsSet){
                    System.out.println();
                    System.out.println("Enter assessment type: (Quiz, Lab Report)");
                    name = sc.nextLine();
                    System.out.println();
                }
                else name = "Finals";
                System.out.println("Enter " + name + " weightage: (50, 70, 20)");
                System.out.println("Remaining weightage left: " + (100-totalWeightage));
                weightage = Double.parseDouble(sc.nextLine());
                //sc.nextLine();
                System.out.println();
                if(weightage <= 0){
                    throw new ArithmeticException("Error: weightage must not be negative!");
                }

                if(weightage + totalWeightage > 100){
                    System.out.println("Invalid weightage! Should not exceed a total of 100!");
                }
                else{
                    System.out.println("Confirm entry of \"" + name + "\" weightage: " + weightage + "? (Y/N)");
                    confirm = sc.nextLine().toUpperCase().charAt(0);
                    if(confirm == 'Y'){
                        totalWeightage += weightage;
                        course.setAssessment(new Assessment(name,weightage));
                        if(name.equals("Finals")) finalsSet = true;
                        System.out.println("Results component \"" + name + "\" is added with a weightage of " + weightage);
                    }
                    else{
                        System.out.println(name + " component not added.");
                    }
                }
            }catch(ArithmeticException e){
                System.out.println(e.getMessage());
            }catch(NumberFormatException e){
                System.out.println();
                System.out.println("Error: input is invalid!");
                System.out.println("Please reenter!");
            }catch(Exception e){
                System.out.println("Error!");
            }
            
        }
        System.out.println("Results weightage completed....");
        return;
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
     * A method to add new {@link Session} to the {@link Course} parameter. This is based on {@link Course#addSession()}
     * @param course Course we need to add Session too
     * @return boolean true or false depends on the result of {@link Course#addSession()}
     */
    public boolean addSession(Course course){
        return course.addSession();
    }

    /**
     * A method to take in {@link Course} object and print out {@link Sessions} under it. This is based on {@link Course#printSessions()}
     * @param course Course object whose Sessions are to be printed out.
     */
    public void printSessions(Course course){
        course.printSessions();
    }

    /**
     * A method to de-register {@link Student} from {@link Course}. This is done by calling {@link Course#deregisterStudent(Student)}
     * @param course Course object to be access and remove Student from
     * @param student Student object to remove from the course
     * @return int indicating status of the procedure
     */
    public int deregisterStudent(Course course, Student student){
        return course.deregisterStudent(student);
    }

    /**
     * A method to print out all {@link Session} under the {@link Course} being supplied. This is done by calling {@link Course#printIndexList()}
     * @param course Course object of interest.
     */
    public void printIndexList(Course course){
        course.printIndexList();
    }

    /**
     * A method to return {@link Session} object under a {@link Course} object being parsed in. This is done by calling {@link }
     * @param course Course object of interest.
     */
    public Session getCourseSession(Course course){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Session Group ID (CE1/SEP3)");
        String group = sc.nextLine();
        System.out.println("Enter the Session Type (LAB/TUT/LEC CE1)");
        String type = sc.nextLine();
        if(group.isEmpty() || type.isEmpty()) throw new StringIndexOutOfBoundsException();
        //get the session from database
        return course.getSession(group, type);
    }

    /**
     * A method to modify {@link Session} of a {@link Course} being parsed in
     * @param course the Course where the modifying Session is lying in
     * @param session the Session to be modified.
     */
    public void modifySession(Course course, Session session){
        course.modifySession(session);
    }

    public void setResults(Course course, Student student){
        int i =0;
        Scanner sc = new Scanner(System.in);
        Double marks;

        ArrayList<Assessment> results = getAssessment(course);
        if(course.studentRegistered(student)){
            for (i = 0; i < results.size(); i++){
                marks = 101.0; //just for it to satisfy the while statement
                while(marks > 100 || marks < 0){
                    System.out.println("Enter results the following component: " + results.get(i).getAssessmentName());
                    marks = sc.nextDouble();
                    sc.nextLine();
                    course.enterResults(results.get(i), student, marks);
                }
            }
            if(i == 0) System.out.println("Error! Course Weightage is not set yet!");
        } else {
            System.out.println("Student is not registered in this course!");
        }
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