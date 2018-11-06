import java.util.*;
import java.io.*;

public class ScrameApp{
    public static void main(String[] args) {
        Scanner sc = new Scanner (System.in);
        Student student;
        Course course;
        String fileName = "test.bat";
        Database db = loadData(fileName);
        StudentManager studMg = new StudentManager();
        CourseManager courseMg = new CourseManager();
        studMg.setStudentCatalog(db.getStudentCatalog());
        courseMg.setCourseCatalog(db.getCourseCatalog());
        int choice;
        int studentIndex;
        int courseIndex;
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
        while (cont){
            System.out.println("Menu");
            System.out.println("1. Add a student ");
            System.out.println("2. Add a course");
            System.out.println("3. Register student for a course");
            System.out.println("4. Check available slot in a class");
            System.out.println("5. Print student list");
            System.out.println("6. Enter course's assessment weightage");
            System.out.println("7. Enter coursework mark");
            System.out.println("8. Save data");
            System.out.println("9. Print course statistics");
            System.out.println("10. Print student transcript");
            System.out.println("11. Exit");
            System.out.print("Enter your action: ");
            choice = sc.nextInt();
            sc.nextLine();
            switch(choice){
                case 1: //add a student
                    confirm = 'N';
                    while(confirm != 'Y'){
                        System.out.println("Enter student's name: ");
                        studentName = sc.nextLine();
                        System.out.println("Enter student's matric No.: ");
                        matricNumber = sc.nextLine();
                        System.out.println("Enter student's school (SCSE): ");
                        school = sc.nextLine();
                        System.out.println("Enter student's year of study: ");
                        acadYear = sc.nextInt();
                        sc.nextLine();
                        System.out.println("Enter student's gender: (M/F)");
                        gender = sc.nextLine().charAt(0);
                        if(studMg.getStudent(matricNumber) != null){
                            System.out.println("Student with matric number " + matricNumber + " already exists!");
                        }
                        else{
                            System.out.println("Student: "+ studentName +", Matric Number: " + matricNumber + ", "+ school + " Year " + acadYear + " , "+gender);
                            System.out.println("Are you sure you want to add in this student? (Y/N)");
                            confirm = sc.nextLine().charAt(0);
                        }
                    }
                    student = studMg.addStudent(studentName, matricNumber, gender, school, acadYear);
                    System.out.println(student + " is added into the records.");
                    studMg.printAllStudent();
                    break;
                case 2: //add a course
                    boolean contin = true;
                    char addMore = 'Y';
                    confirm = 'N';
                    while(confirm != 'Y'){
                        System.out.println("Enter course name: (Computer Vision/Object-Orientated Design & Programming)");
                        courseName = sc.nextLine();
                        System.out.println("Enter course code: (CE2005/CE4001/CZ1023)");
                        courseCode = sc.nextLine();
                        System.out.println("Enter course AU: (2/3)");
                        AU = sc.nextInt();
                        sc.nextLine();
                        System.out.println("Enter name of course coordinator: ");
                        courseCoordinator = sc.nextLine();
                        System.out.println(courseCode + " " + courseName + " AU: " + AU + " by " + courseCoordinator);
                        System.out.println("Are you sure you want to add in this course? (Y/N)");
                        confirm = sc.nextLine().charAt(0);
                    }
                    if(courseMg.getCourse(courseCode) != null){
                        System.out.println(courseCode + " is already registered and used! Please choose another course code!");
                        break;
                    }
                    course = courseMg.addCourse(courseName, courseCode, AU, courseCoordinator);
                    System.out.println(course + " is added.");
                    courseMg.printCourseCatalog();
                    do{
                        course.addSession();
                        System.out.println("Do you want to add more session? Y/N");
                        addMore = sc.nextLine().charAt(0);
                        switch(addMore){
                            case 'N': contin = false;
                                        break;
                        }
                    }while(contin);
                    break;
                case 3: //register student for a course
                    System.out.println("Enter student's matriculation number: ");
                    matricNumber = sc.nextLine();
                    System.out.println("Enter course code to be registered: ");
                    courseCode = sc.nextLine();
                    studentIndex = studMg.studentExists(matricNumber);
                    courseIndex = courseMg.verifyCourse(courseCode);
                    //if any of them do not exist
                    if (studentIndex == -1 || courseIndex == -1){ 
                        System.out.println("Student or course is not in our records!");
                        break;
                    }
                    else{
                        courseMg.getSessions(courseIndex);
                        System.out.println("Please enter the group ID: (SEP1/CE3)");
                        group = sc.nextLine();
                        System.out.println("Please enter the session type: (LEC/TUT/LAB)");
                        type = sc.nextLine();
                        result = courseMg.regStudentToCourse(studMg.getStudent(matricNumber), courseIndex, group, type);
                        switch (result){
                            case 0: 
                                studMg.updateCourseTaken(courseMg.getCourse(courseCode), studentIndex); 
                                System.out.println("Student added to " + type + " with Group ID: " + group);
                                break;
                            case -1: System.out.println("Error! Group is already full!"); break;
                            case -2: System.out.println("Error! Student is already registered in that group session!"); break;
                            case -3: System.out.println("Error! You have entered a wrong group session!"); break;
                        }
                    }
                    break;
                case 4: //Check available slots in a class
                    courseMg.printCourseCatalog();
                    System.out.println("Enter the course code you need check vacancy for: ");
                    courseCode = sc.nextLine();
                    course = courseMg.getCourse(courseCode);
                    courseMg.checkVacancy(course);
          /*           System.out.println("Enter the course code you need check vacancy for: ")
                    courseMg.printCourseCatalog();
                    Course course;
                    String courseCode = sc.nextLine();
                    int index = courseMg.verifyCourse(courseCode);
                    if (index!=-1){
                        course = courseMg.getCourse(index);
                        System.out.println("Select the session of course to check vacancy: ")
                        course.printIndexList();
                        String group = sc.nextLine();
                        courseMg.checkVacancy(course, group);
                    }
                    else{
                        System.out.println("Course does not exist");
                    } */
                    break;
                case 5: //print student list
                    courseMg.printCourseCatalog();
                    System.out.println("Please enter the course code that you would like to print out the student list");
                    courseCode = sc.nextLine();
                    course = courseMg.getCourse(courseCode);
                    courseMg.printSessionStudent(course);
                    break;
                case 6: //enter course assessment weightage
                    System.out.println("================= ENTER COURSE WEIGHTAGE =================");
                    System.out.println("Enter course code:");
                    courseCode = sc.nextLine();
                    course = courseMg.getCourse(courseCode);
                    if(course == null){
                        System.out.println("Error! Course isn't in our records!");
                        break;
                    }
                    courseMg.setAssessment(course);
                    break;
                case 7: //enter coursework mark
                    System.out.println("Enter course: ");
                    courseCode = sc.nextLine();
                    System.out.println("Enter student's matriculation number: ");
                    matricNumber = sc.nextLine();
                    if(courseMg.verifyCourse(courseCode) == -1 || studMg.studentExists(matricNumber)== -1){
                        System.out.println("Student or course entered is not in our records!");
                    }
                    else {
                        course = courseMg.getCourse(courseCode);
                        student = studMg.getStudent(matricNumber);
                        results = courseMg.getAssessment(course);
                        if(course.studentRegistered(student)){
                            for (i = 0; i < results.size(); i++){
                                marks = 101; //just for it to satisfy the while statement
                                while(marks > 100 || marks < 0){
                                    System.out.println("Enter results the following component: " + results.get(i).getAssessmentName());
                                    marks = sc.nextDouble();
                                    sc.nextLine();
                                    courseMg.setResults(results.get(i), student, marks);
                                }
                            }
                            if(i == 0) System.out.println("Error! Course Weightage is not set yet!");
                        } else {
                            System.out.println("Student is not registered in this course!");
                        }
                    }
                    break;
                case 8: //save data
                    saveData(fileName, db);
                    System.out.println("============== DATA SAVED ==============");
                    break;
                case 9: //print course stats
                    System.out.println("Enter course code:");
                    courseCode = sc.nextLine();
                    course = courseMg.getCourse(courseCode);
                    if(course == null){
                        System.out.println("Error! Course is not in our records!");
                        break;
                    }
                    courseMg.printCourseStats(course);
                    break;
                case 10: //print student transcript
                    System.out.println("Enter student's matriculation number: ");
                    matricNumber = sc.nextLine();
                    student = studMg.getStudent(matricNumber);
                    if(student == null){
                        System.out.println("Error! Student is not in our records!");
                        break;
                    }
                    studMg.printTranscript(student);
                    break;
                case 11: //exit
                    cont = false;
                    saveData(fileName, db);
                    System.out.println("Exit....");
                    return;
                default:
                    System.out.println("Invalid choice, please try again!");
            }
        System.out.println("Press enter to continue...");
        sc.nextLine();
        }

    }

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
}