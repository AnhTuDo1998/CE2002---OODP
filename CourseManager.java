import java.util.*;
import java.io.*;
/**
 * A controller class to handle most services related to Course objects.
 */
public class CourseManager{
    /**
     * A method to register {@link Student} object into {@link Course}, which object both being parsed in.
     * <p> In this application, we are storing Student objects inside an ArrayList, which is held by a {@link Session} object. This modeling corresponding to having Student
     * records stored under the Session (LEC, TUT, LAB) which the Students attain. 
     * <p> This method is therefore accessing the Course object's ArrayList of Session and then adding the Student into the corresponding Session. 
     * @param student Student object to be registered 
     * @param course Course object to add the Student object in
     * @param group String group ID of the Session we going to register to
     * @param type Type of Session we are going to register to.
     * @return int 0 if added successfully, -1 if full, -2 if student is inside -3 if group does not exist
     * @see Course#registerStudent(Student, String, String)
     */
    public int regStudentToCourse(Student student, Course course, String group, String type){
        return course.registerStudent(student, group, type);
    }
    /**
     * A method to check the current vacancy of {@link Course} object by printing
     * out all Session objects under it and their vacancy. Refer to {@link Course#printVacancy()} for more info.
     * @param course Course object which has vacancy of Sessions to be printed out.
     */
    public void checkVacancy(Course course){
        if (course != null){
            course.printVacancy();
        }
        else{
            System.out.println("Course does not exist");
        } 
    }

    /**
     * A method to print out list of {@link Student} registered under a {@link Session}.
     * This is done by finding the matching Session as described by user and print it out by {@link Session#printSessionStudent()} method.
     * @param course Course object for Student to be printed out based on Session.
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
     * A method to return the ArrayList of {@link Assessment} components stored under
     * the {@link Course} parsed in. This is done by {@link Course#getAssessment()} method
     * @param course Course object whose list of Assessments is to be returned
     * @return ArrayList of Assessments under the Course.
     */
    public ArrayList<Assessment> getAssessment(Course course){
        return course.getAssessment();
    }
    /**
     * A method to enter the weightage for {@link Assessment} components and relevant data (example exam, coursework, etc).
     * The ArrayList of Assessment (stored in {@link Course} object) is append the new component by {@link Course#setAssessment(Assessment)}
     * @param course Course object which to set up Assessment components for.
     */
    public void setAssessment(Course course){
        String name;
        double weightage = 0;
        double totalWeightage = 0; //total weightage must be equal to 100
        boolean finalsSet = false;
        Scanner sc = new Scanner(System.in);
        char confirm = 'N';
        if(course.getAssessment().size()>0){
            System.out.println("=============================== WARNING ==============================");
            System.out.println("Assessment weightages are already set! Are you sure you want to proceed?");
            System.out.println("Doing so will WIPE ALL existing results and weightages!");
            System.out.printf("(Y/N) : ");
            if (sc.nextLine().toUpperCase().charAt(0) != 'Y') return;
        }
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
     * A method to print out statistics for {@link Course} being parsed in.
     * These statistics are for example number of male/female, number of students by year of study and etc.
     * @param course Course object to display Statistics for.
     * @throws InputMismatchException if string is entered instead of a number.
     */
    public void printCourseStats(Course course) throws InputMismatchException{
        ArrayList<Assessment> assessmentList = course.getAssessment();
        ArrayList<Session> sessionList = course.getAllSession();
        ArrayList<Student> registeredStudents = new ArrayList<Student>();
        Set<Student> buffer = new HashSet<>();
        Scanner sc = new Scanner(System.in);
        int[] results = new int[10];
        //index 0 = A+, 1 = A....9 = F
        int totalMale = 0;
        int totalFemale = 0;
        int[] year = new int[4];
        double totalResults = 0;
        int i = 0;
        int choice = 0;
        int start=0;
        int size=0;
        double totalWeightage = 100;
        double totalAverageResults = 0;

        //index 0 = year1, 1 = year2, 2 = year3, 3 = year4 
        for(Session session : sessionList){
            buffer.addAll(session.getStudentRegistered());
            //get all registered students, using set to remove duplicates
        }
        registeredStudents.addAll(buffer);
        ScrameApp.printSpaces();
        System.out.println("Which statistics do you want to print (Enter a number)?");
        System.out.println("1. Overall marks");
        System.out.println("2. Finals marks");
        System.out.println("3. Coursework marks");
        choice = sc.nextInt();
        sc.nextLine();
        switch(choice){ 
            case 1:  size = assessmentList.size();
                start = 0;
                totalWeightage = 100;
                System.out.println("================================ OVERALL STATISTICS ================================");
                break;
            case 2:  size = 1;
                start = 0;
                totalWeightage = assessmentList.get(0).getWeightage();
                System.out.println("================================ FINALS STATISTICS ================================");
                break;
            case 3:  size = assessmentList.size();
                start = 1;
                totalWeightage = 100 - assessmentList.get(0).getWeightage();
                System.out.println("================================ COURSEWORK STATISTICS ================================");
                break;
            default: 
                size = assessmentList.size();
                start = 0;
                totalWeightage = 100;
                System.out.println("================================ OVERALL STATISTICS ================================");
                break;

        }        
        System.out.println("Printing course statistics for " + course);
        System.out.println("= MATRICULATION NUMBER == RESULT ====");
        for(i = 0; i < registeredStudents.size(); i++){
            year[registeredStudents.get(i).getAcadYear()-1]++;
            if(registeredStudents.get(i).getGender() == ('M')) totalMale++;
            else totalFemale++;
            totalResults = 0;
            for(int j = start; j < size; j++){
                totalResults += assessmentList.get(j).retrieveAssessmentResult(registeredStudents.get(i)) * assessmentList.get(j).getWeightage() /totalWeightage;
            }
            totalAverageResults += totalResults;
            System.out.printf("       %-15s  : %-5.2f%%\n", registeredStudents.get(i).getMatricNumber(), totalResults);
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
        System.out.printf("Average Result     : %1$-5.2f \n", (double)(totalAverageResults/i));
        System.out.println();
        System.out.printf("Total students     : %d", i);
    }

    /**
     * A method to access {@link Course} and add more {@link Session} into it. This is done by {@link Course#addSession()} which append the 
     * get related information about the new Session and append it at the end of the ArrayList of Session objects stored under Course object.
     * @param course Course object which new Session is to be added to.
     * @return true if success, false if failed.
     * @throws EmptyInputException for empty input from user instead of information needed.
     */
    public boolean addSession(Course course) throws EmptyInputException{
        return course.addSession();
    }

    /**
     * A method to access {@link Course} object and print out all {@link Session} objects stored under it, including their vacancy. This is done by
     * {@link Course#printVacancy()} method, which traverse the ArrayList of Session stored under Course object and print out the entries.
     * @param course Course object whose Session need to be printed out.
     */
    public void printVacancy(Course course){
        course.printVacancy();
    }

    /**
     * A method to access {@link Course} and remove {@link Student} from it. This is done by {@link Course#deregisterStudent(Student)}
     * which remove Student from all {@link Session} and erase his/her {@link Assessment} data.
     * @param course Course object which new Session is to be added to.
     * @param student Student object to be removed.
     * @return true if success, false if failed.
     */
    public int deregisterStudent(Course course, Student student){
        return course.deregisterStudent(student);
    }
    /**
     * A method to access {@link Course} object and print a list of {@link Session} objects stored under it. This is done by
     * {@link Course#printIndexList()} method, which traverse the ArrayList of Session stored under Course object and print out the entries.
     * @param course Course object whose Session need to be printed out.
     */
    public void printIndexList(Course course){
        course.printIndexList();
    }

    /**
     * A method to return a {@link Session} object of the parsed in {@link Course}.
     * <p> This is done by query user for group and type of the Session and use {@link Course#getSession(String, String)}
     * to find and return the matching Session. In Course object, the Session object can be found 
     * by accessing the ArrayList of Session.
     * @param course Course object which we need to get a Session from
     * @return Session object of interest.
     * @throws EmptyInputException for empty input from user.
     */
    public Session getCourseSession(Course course) throws EmptyInputException{
        //get the session from database
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Session Group ID (CE1/SEP3)");
        String group = sc.nextLine();
        if(group.isEmpty()) throw new EmptyInputException("group");
        System.out.println("Enter the Session Type (LAB/TUT/LEC CE1)");
        String type = sc.nextLine();
        if(type.isEmpty()) throw new EmptyInputException("type");
        return course.getSession(group, type);
    }

    /**
     * A method to access a {@link Course} object and edit the information of its {@link Session}.
     * This is done by getting the input from users and modify via setters and getters, 
     * shown in {@link Course#modifySession(Session)}
     * @param course Course object which have Session to be modified
     * @param session Session object that is being modified
     * @throws EmptyInputException for empty input from user.
     * @throws InputMismatchException if user's input is not matching with data type required.
     */
    public void modifySession(Course course, Session session) throws EmptyInputException, InputMismatchException{
        course.modifySession(session);
    }

    /**
     * A method to enter the result of {@link Student} for a particulat {@link Course}
     * <p> Note that we implemented a Hashmap with Student object being key and the Student's grade as stored data in {@link Assessment}.
     * Here we are going to access that data structure to enter the result.
     * <p> Firstly, we obtain the ArrayList of Assessment components of Course (for instance, Exam, Coursework and etc). Then we traverse
     * this ArrayList, getting the Assessment object and hence access and modify the HashMap implemented under it via {@link Course#enterResults(Assessment, Student, double)}
     * @param course Course object where Student's result is to be enter.
     * @param student Student whose result for Assessment components of a Course need to be stored.
     */
    public void setResults(Course course, Student student){
        int i =0;
        Scanner sc = new Scanner(System.in);
        Double marks;

        ArrayList<Assessment> results = getAssessment(course);
            if(course.studentRegistered(student)){
                for (Assessment assessment : results){
                    marks = 101.0; //just for it to satisfy the while statement
                    while(marks > 100 || marks < 0){
                        try{
                            System.out.println("Enter results the following component: " + assessment.getAssessmentName());
                            marks = sc.nextDouble();
                            sc.nextLine();
                            if(marks < 0){
                                throw new ArithmeticException("Error: marks cannot be negative");
                            }
                            course.enterResults(assessment, student, marks);
                        }catch(ArithmeticException e){
                            System.out.println(e.getMessage());
                            continue;
                        }   
                    }
                }
                if(results.size() == 0) System.out.println("Error! Course Weightage is not set yet!");
            } else {
                System.out.println("Student is not registered in this course!");
            }
            
    }
    /**
     * A method to print out all {@link Student} objects associated with the parsed in {@link Course} object.
     * <p> As Student objects are stored in {@link Session} objects, which are stored under the Course object, we first
     * traverse the ArrayList of Session, adding all Student objects stored in each entry into a Set.
     * <p> We then traverse and print out the Student objects from the Set.
     * @param course Course object whose Student objects need to be printed out.
     * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html">ArrayList</a>
     * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/util/Set.html">Set</a>
     */
    public void printStudentRegistered(Course course){
        ArrayList<Session> sessionList = course.getAllSession();
        ArrayList<Student> registeredStudents = new ArrayList<Student>();
        Set<Student> buffer = new HashSet<>();
        for(Session session : sessionList){
            buffer.addAll(session.getStudentRegistered());
            //get all registered students, using set to remove duplicates
        }
        registeredStudents.addAll(buffer);
        System.out.println("============================================================================");
        System.out.println("========================== Student Registered ==============================");
        for(Student student: registeredStudents){
            System.out.println(student);
        }
        System.out.println("============================================================================");
    }
}