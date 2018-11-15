import java.util.*;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.*;
import java.awt.Graphics;
/**
 * Main boundary class for S.C.R.A.M.E Application, which is a console-base student register system.
 * This class is in charge of interacting with users and delegate jobs and data to {@link Database},
 * {@link CourseManager} and {@link StudentManager} for processing.
 */
public class ScrameApp{
	/**
	 * Main thread of the S.C.R.A.M.E Application. This method is called when the
	 * application is activated and remains open unitl the user exit the application.
	 * @param args empty String buffer.
	 */
    public static void main(String[] args) {
        Scanner sc = new Scanner (System.in);
        Student student;
        Course course;
        Session session;
        String fileName = "test.bat";
        Database db = loadData(fileName);
        StudentManager studMg = new StudentManager();
        CourseManager courseMg = new CourseManager();
        int choice;
        int result;
        String type;
        String studentName = "";
        String school = "";
        int acadYear = 0;
        char gender = 'N';
        char confirm = 'N';
        String courseCode = "";
        String courseName = "";
        String courseCoordinator = "";
        int AU = 0;
        String matricNumber = "";
        String group;
        boolean cont = true;
        ArrayList<Assessment> results;
        int i = 0;
        double marks = 0;
        printTitle();
        while (cont){
            try{
            System.out.printf("|========================================================|\n"
                            + "|                         MENU                           |\n"
                            + "|========================================================|\n"
                            + "||     1: Add/Remove a student.                         ||\n"
                            + "||     2: Add/Remove a course.                          ||\n"
                            + "||     3: Register/deregister student for a course.     ||\n"
                            + "||     4: Modify a session.                             ||\n"
                            + "||     5: Check available slot in a course.             ||\n"
                            + "||     6: Print student list by session.                ||\n"
                            + "||     7. Enter course assessment components weightage  ||\n"
                            + "||     8: Enter marks for students                      ||\n"
                            + "||     9: Save Data                                     ||\n"
                            + "||    10: Print course statistics                       ||\n"
                            + "||    11: Print student transcript.                     ||\n"
                            + "||    12: Show all course(s).                           ||\n"
                            + "||    13: Show all student(s).                          ||\n"
                            + "||    14: Quit                                          ||\n"
                            + "|========================================================|\n");
            System.out.print("Enter your action: ");
            choice = sc.nextInt();
            sc.nextLine();
            printSpaces();
            switch(choice){
                case 1: //add a student
                    printSpaces();
                    System.out.println("============================== STUDENT ==============================");                    
                    System.out.println("1. Add a student ");
                    System.out.println("2. Remove a student ");
                    System.out.println("3. Menu");
                    System.out.print("Enter your action: ");
                    choice = sc.nextInt();
                    sc.nextLine();
                    printSpaces();
                    switch(choice){
                        case 1: confirm = 'N';
                                while(confirm != 'Y'){
                                    System.out.println("============================== ADD NEW STUDENT ==============================");                    
                                    System.out.println("Enter student's name: ");
                                    studentName = sc.nextLine();
                                    System.out.println();
                                    if(!studentName.matches("[a-zA-Z\\s]*")){
                                        System.out.print("Enter a valid name: ");
                                        studentName = sc.nextLine();
                                        if(!studentName.matches("[a-zA-Z\\s]*"))  throw new InputMismatchException("Error: enter alphabets only");
                                    }
                                    System.out.println("Enter student's matric No.: ");
                                    matricNumber = sc.nextLine().toUpperCase();
                                    System.out.println();
                                    System.out.println("Enter student's school (SCSE): ");
                                    school = sc.nextLine().toUpperCase();
                                    System.out.println();
                                    System.out.println("Enter student's year of study: (1~4)");
                                    acadYear = sc.nextInt();
                                    sc.nextLine();
                                    if(acadYear < 1 || acadYear > 4){
                                        System.out.print("Enter a value between 1 and 4: ");
                                        acadYear = sc.nextInt();
                                        sc.nextLine();
                                        if(acadYear < 1 || acadYear > 4) throw new InputMismatchException("Error: entered an invalid academic year value");
                                    }
                                    System.out.println();
                                    System.out.println("Enter student's gender: (M/F)");
                                    gender = sc.nextLine().toUpperCase().charAt(0);
                                    if(gender != 'M' && gender != 'F'){
                                        System.out.println("Enter either M or F");
                                        gender = sc.nextLine().toUpperCase().charAt(0);
                                        sc.nextLine();
                                        if(gender != 'M' && gender != 'F') throw new InputMismatchException("Error: entered an invalid gender type");
                                    } 
                                    printSpaces();
                                
                                if(studentName.isEmpty() || matricNumber.isEmpty() || school.isEmpty()){
                                    throw new EmptyInputException();
                                }
                                if(db.getStudent(matricNumber) != null){
                                    System.out.println("Student with matric number " + matricNumber + " already exists!");
                                    break;
                                }
                                else{
                                    System.out.println("Student: "+ studentName +", Matric Number: " + matricNumber + ", "+ school + " Year " + acadYear + " , "+gender);
                                    System.out.println("Are you sure you want to add in this student? (Y/N)");
                                    confirm = sc.nextLine().toUpperCase().charAt(0);
                                    if(confirm == 'N') {
                                        System.out.println("Student is not added. ");
                                        break;
                                    }
                                }
                            
                            student = db.addStudent(studentName, matricNumber, gender, school, acadYear);
                            System.out.println(student + " is added into the records.");
                            db.printStudentCatalog();
                            }
                        
                        break;

                        case 2:
                            System.out.println("============================== REMOVE EXISTING STUDENT ==============================");                    
                            db.printStudentCatalog();
                            System.out.println("Enter the matriculation number of the student: ");
                            matricNumber = sc.nextLine();
                            if(matricNumber.isEmpty()) throw new EmptyInputException("matricNumber");
                            student = db.getStudent(matricNumber);
                            if(student!=null){
                                System.out.println(student);
                                System.out.println("Confirm to remove? (Y/N)");
                                confirm = sc.nextLine().toUpperCase().charAt(0);
                                if(confirm == 'Y'){
                                    db.removeStudent(student);
                                    System.out.println(student + " is removed!");
                                }else{
                                    System.out.println("Student not removed!");
                                }
                            }else{
                                System.out.println("Student is not in our records!");
                            }
                            break;
                        case 3:
                            break;
                        default:
                            break;
                    }
                        break;
                case 2: //add&remove a course
                        printSpaces();
                        System.out.println("============================== COURSE ==============================");                    
                        System.out.println("1. Add a course ");
                        System.out.println("2. Remove a course ");
                        System.out.println("3. Menu ");
                        System.out.print("Enter your action: ");
                        choice = sc.nextInt();
                        sc.nextLine();
                        printSpaces();
                        switch(choice){
                            case 1: //add a course
                                boolean contin = true;
                                boolean added = false;
                                char addMore = 'Y';
                                confirm = 'N';
                                while(confirm != 'Y'){
                                    System.out.println("============================== ADD NEW COURSE ==============================");                    
                                    System.out.println("Enter course name: (Computer Vision/Object-Orientated Design & Programming)");
                                    courseName = sc.nextLine();
                                    System.out.println();
                                    System.out.println("Enter course code: (CE2005/CE4001/CZ1023)");
                                    courseCode = sc.nextLine();
                                    System.out.println();
                                    System.out.println("Enter course AU: (2/3)");
                                    AU = sc.nextInt();
                                    sc.nextLine();
                                    System.out.println();
                                    System.out.println("Enter name of course coordinator: ");
                                    courseCoordinator = sc.nextLine();
                                    System.out.println();
                                    
                                    if(courseName.isEmpty() || courseCode.isEmpty() || courseCode.isEmpty()) 
                                        throw new EmptyInputException();
                                    System.out.println(courseCode + " " + courseName + " AU: " + AU + " by " + courseCoordinator);
                                    System.out.println("Are you sure you want to add in this course? (Y/N)");
                                    confirm = sc.nextLine().toUpperCase().charAt(0);
                                    if(confirm == 'N'){
                                        System.out.println("Course is not added");
                                        break;
                                    }
                                }
                                if(db.getCourse(courseCode) != null){
                                    System.out.println(courseCode + " is already registered and used! Please choose another course code!");
                                    break;
                                }
                                course = db.addCourse(courseName, courseCode, AU, courseCoordinator);
                                System.out.println(course + " is added.");
                                db.printCourseCatalog();
                                do{
                                    added = courseMg.addSession(course);
                                    if(!added) continue; //ensures at least one is added!
                                    System.out.println("Do you want to add more session? Y/N");
                                    addMore = sc.nextLine().toUpperCase().charAt(0);
                                    switch(addMore){
                                        case 'N': contin = false;
                                                    break;
                                    }
                                }while(contin);
                                break;
                            case 2: //remove a course
                                System.out.println("============================== REMOVE EXISTING COURSE ==============================");                    
                                db.printCourseCatalog();
                                System.out.println("Enter the course code you want to remove: ");
                                courseCode = sc.nextLine();
                                if(courseCode.isEmpty()) throw new EmptyInputException("courseCode");
                                course = db.getCourse(courseCode);
                                if(course != null){
                                    System.out.println("Are you sure you want to remove " + course + " ? (Y/N)");
                                    confirm = sc.nextLine().toUpperCase().charAt(0);
                                    if(confirm == 'Y'){
                                        db.removeCourse(course);
                                        System.out.println(course  + " is removed!");
                                    }
                                }else{
                                    System.out.println("Error! Course doesn't exist!");
                                }
                                break;
                            case 3:
                                break;
                            default:
                                break;
                        }
                        break;
                case 3: 
                    printSpaces();
                    System.out.println("=============================== REGISTRATION =============================");
                    System.out.println("1. Register a student ");
                    System.out.println("2. Unregister a student ");
                    System.out.println("3. Menu");
                    System.out.print("Enter your action: ");
                    choice = sc.nextInt();
                    sc.nextLine();
                    printSpaces();
                    switch(choice){
                        case 1: //register student for a course
                            System.out.println("============================== REGISTER STUDENT ==============================");                    
                            db.printStudentCatalog();
                            System.out.println("Enter student's matriculation number: ");
                            matricNumber = sc.nextLine();
                            db.printCourseCatalog();
                            System.out.println("Enter course code to be registered: ");
                            courseCode = sc.nextLine();
                            student = db.getStudent(matricNumber);
                            course = db.getCourse(courseCode);
                            if(courseCode.isEmpty() || matricNumber.isEmpty()) throw new EmptyInputException();
                            //if any of them do not exist
                            if (student == null || course == null){ 
                                System.out.println("Student or course is not in our records!");
                                break;
                            }
                            else{
                                courseMg.printSessions(course);
                                System.out.println("Please enter the group ID: (SEP1/CE3)");
                                group = sc.nextLine();
                                System.out.println("Please enter the session type: (LEC/TUT/LAB)");
                                type = sc.nextLine();
                                if(group.isEmpty() || type.isEmpty()) throw new EmptyInputException();
                                result = courseMg.regStudentToCourse(student, course, group, type);
                                switch (result){
                                    case 0: 
                                        studMg.updateCourseTaken(course, student); 
                                        System.out.println("Student added to " + type + " with Group ID: " + group);
                                        break;
                                    case -1: System.out.println("Error! Group is already full!"); break;
                                    case -2: System.out.println("Error! Student is already registered in that group session!"); break;
                                    case -3: System.out.println("Error! You have entered a wrong group session!"); break;
                                }
                            }
                            break;
                        case 2: //unregister a student
                            System.out.println("============================== UNREGISTER STUDENT ==============================");                    
                            db.printStudentCatalog();
                            System.out.println("Enter the student matriculation number: ");
                            matricNumber = sc.nextLine();
                            if(matricNumber.isEmpty()) throw new EmptyInputException("matricNumber");
                            student = db.getStudent(matricNumber);
                            if(student!=null) studMg.printCourseRegistered(student);
                            System.out.println("Enter the course code you want to remove the student from");
                            courseCode = sc.nextLine();
                            if(courseCode.isEmpty()) throw new EmptyInputException("courseCode");
                            course = db.getCourse(courseCode);
                            if(course != null && student != null){
                                if(courseMg.deregisterStudent(course, student) > 0){
                                    studMg.deregisterCourse(student, course);
                                    System.out.println("Student deregistered successfully!");
                                }else{
                                    System.out.println("Error! Student is not registered for this course!");
                                }
                            }else{
                                System.out.println("Course or student does not exist!");
                            }
                            break;
                        case 3: break;
                        default: break;
                    }
                    break;
                case 4: //modify Session
                    System.out.println("============================== MODIFY SESSION INFO ==============================");                    
                    db.printCourseCatalog();
                    System.out.println("Enter the course code you want to modify sessions: ");
                    courseCode = sc.nextLine();
                    if(courseCode.isEmpty()) throw new EmptyInputException("courseCode");
                    course = db.getCourse(courseCode);
                    // course exists
                    if(course != null){
                        //print out the course's session
                        courseMg.printIndexList(course);
                        //get the session from database
                        session = courseMg.getCourseSession(course);
                        if (session != null){
                            courseMg.modifySession(course, session);
                        }else{
                            System.out.println("Session does not exist!");
                        }
                    }
                    //course doesn't exist
                    else{
                        System.out.println("Error! Course doesn't exist!");
                    }
                    break;
                case 5: //Check available slots in a class
                    System.out.println("============================== SHOW VACANCIES ==============================");                    
                    db.printCourseCatalog();
                    System.out.println("Enter the course code you need check vacancy for: ");
                    courseCode = sc.nextLine();
                    if(courseCode.isEmpty()) throw new EmptyInputException("courseCode");
                    course = db.getCourse(courseCode);
                    courseMg.checkVacancy(course);
                    break;
                case 6: //print student list
                    System.out.println("============================== SHOW STUDENT REGISTERED ==============================");                    
                    db.printCourseCatalog();
                    System.out.println("Please enter the course code that you would like to print out the student list");
                    courseCode = sc.nextLine();
                    if(courseCode.isEmpty()) throw new EmptyInputException("courseCode");
                    course = db.getCourse(courseCode);
                    courseMg.printSessionStudent(course);
                    break;
                case 7: //enter course assessment weightage
                    System.out.println("============================== ENTER COURSE WEIGHTAGE ==============================");
                    db.printCourseCatalog();
                    System.out.println("Enter course code:");
                    courseCode = sc.nextLine();
                    if(courseCode.isEmpty()) throw new EmptyInputException("courseCode");
                    course = db.getCourse(courseCode);
                    if(course == null){
                        System.out.println("Error! Course isn't in our records!");
                        break;
                    }
                    courseMg.setAssessment(course);
                    break;
                case 8: //enter coursework mark
                    System.out.println("============================== ENTER MARKS ==============================");
                    db.printCourseCatalog();
                    System.out.println("Enter course: ");
                    courseCode = sc.nextLine();
                    if(courseCode.isEmpty()) throw new EmptyInputException("courseCode");
                    course = db.getCourse(courseCode);
                    if(course != null) courseMg.printStudentRegistered(course);
                    System.out.println("Enter student's matriculation number: ");
                    matricNumber = sc.nextLine();
                    if(matricNumber.isEmpty()) throw new EmptyInputException("matricNumber");
                    student = db.getStudent(matricNumber);
                    if(course == null || student == null){
                        System.out.println("Student or course entered is not in our records!");
                    }
                    else {
                        courseMg.setResults(course, student);
                    }
                    break;
                case 9: //save data
                    saveData(fileName, db);
                    System.out.println("============================== DATA SAVED ==============================");
                    break;
                case 10: //print course stats
                    System.out.println("============================== SHOW COURSE STATISTICS ==============================");
                    db.printCourseCatalog();
                    System.out.println("Enter course code:");
                    courseCode = sc.nextLine();
                    if(courseCode.isEmpty()) throw new EmptyInputException("courseCode");
                    course = db.getCourse(courseCode);
                    if(course == null){
                        System.out.println("Error! Course is not in our records!");
                        break;
                    }
                    courseMg.printCourseStats(course);
                    break;
                case 11: //print student transcript
                    System.out.println("============================== SHOW STUDENT TRANSCRIPT ==============================");
                    db.printStudentCatalog();
                    System.out.println("Enter student's matriculation number: ");
                    matricNumber = sc.nextLine();
                    if(matricNumber.isEmpty()) throw new EmptyInputException("matricNumber");
                    student = db.getStudent(matricNumber);
                    if(student == null){
                        System.out.println("Error! Student is not in our records!");
                        break;
                    }
                    studMg.printTranscript(student);
                    break;
                case 12:
                    db.printCourseCatalog();
                    break;
                case 13:
                    db.printStudentCatalog();
                    break;
                case 14: //exit
                    cont = false;
                    saveData(fileName, db);
                    System.out.println("Exiting the SCRAME APP...");
                    System.exit(0);

                
                default:
                    System.out.println("Invalid choice, please try again!");
            }
        }catch(EmptyInputException e){
            continue;
        }catch(InputMismatchException e){
            if(e.getMessage() != null)
                System.out.println(e.getMessage());
            else{
                System.out.println("Error: invalid input (Press enter)");
                sc.nextLine();
            }
            continue;
        }catch(Exception e){
            System.out.println("Error: input is invalid (Press enter)");
            sc.nextLine();
            continue;
        }finally{
                System.out.println();
                System.out.println("Returning you back to the main menu...");
                System.out.println("Press enter key to continue...");
                sc.nextLine();
                printSpaces();
                saveData(fileName, db);
            }
        }
    }
    /**
     * A method to load data stored inside a file into the application return the updated {@link Database}.
     * @param filename String name of the file data is load from
     * @return the updated Database for SCRAME
     */
    public static Database loadData(String filename) {
		Database db = null;
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = new FileInputStream(filename);
			in = new ObjectInputStream(fis);
			db = (Database) in.readObject();
			in.close();
		} catch (IOException ex) {
            // ex.printStackTrace();
            System.out.println("=================================================================================================");
            System.out.println("================== ERROR! NO DATA LOADED (ignore if this is your first loadup) ==================");
            System.out.println("=================================================================================================");
		} catch (ClassNotFoundException ex) {
            System.out.println("=================================================================================================");
            System.out.println("============= ERROR! CLASS NOT FOUND! Make sure you have all the required classes! ==============");
            System.out.println("=================================================================================================");
        }
		// print out the size
		//System.out.println(" Details Size: " + pDetails.size());
        //System.out.println();
        if(db != null){
            return db;
        }
        return (new Database());
	}
    /**
     * A method to save data stored in the {@link Database} into an external file.
     * @param filename String the name of the file to stored data in
     * @param db Database hosting all information relevant to SCRAME application.
     */
	public static void saveData(String filename, Database db) {
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream(filename);
			out = new ObjectOutputStream(fos);
			out.writeObject(db);
			out.close();
		//	System.out.println("Object Persisted");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
    }

    /**
     * A method to print out title of the application with nice format.
     */
    public static void printTitle(){
        int width = 110; //set the width and height of the view
        int height = 20;
        int x;
        int y;
        Scanner sc = new Scanner(System.in);

        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        g.setFont(new Font("TimesNewRoman", Font.ITALIC,15)); //set font style and size

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.drawString("S C R A M E", 10, 15);

        for(y = 0 ; y < height; y++){
            StringBuilder builder = new StringBuilder();
            for(x = 0; x < width; x++){
                builder.append(image.getRGB(x, y) == -16777216? "#": " "); //building the image output
            }
            System.out.println(builder);
        }
        System.out.println();
        System.out.println("                                     ~ Welcome to the SCRAME APP ~                                  ");
        System.out.println("                                        ~ Presented by Group 3 ~                                    ");
        System.out.println("                                      Press Enter Key to Continue                                   ");
        sc.nextLine(); 
    }
    /**
     * A method to print blank spaces for nice display.      
     */
    public static void printSpaces(){
        for(int i =0; i< 100; i++){
            System.out.println();
        }
    }
}