import java.util.*;

public class ScrameApp{
    public static void main(String[] args) {
        Scanner sc = new Scanner (System.in);
        Student student;
        Course course;
        StudentManager studMg = new StudentManager();
        CourseManager courseMg = new CourseManager();
        int choice;
        int studentIndex;
        int courseIndex;
        int result;
        String type;
        String courseCode = "";
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
            System.out.println("8. Enter exam mark");
            System.out.println("9. Print course statistics");
            System.out.println("10. Print student transcript");
            System.out.println("11. Exit");
            System.out.print("Enter your action: ");
            choice = sc.nextInt();
            sc.nextLine();
            switch(choice){
                case 1: //add a student
                    studMg.addStudent();
                    break;
                case 2: //add a course
                    boolean contin = true;
                    Course created = courseMg.addCourse();
                    do{
                        created.addSession();
                        System.out.println("Do you want to add more session? Y/N");
                        choice = sc.next().charAt(0);
                        switch(choice){
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
                    System.out.println(studentIndex + " " + courseIndex);
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
                                System.out.println("Student added to " + type + " " + group);
                                break;
                            case -1: System.out.println("Error! Group is already full!"); break;
                            case -2: System.out.println("Error! Student is already registered in that group session!"); break;
                            case -3: System.out.println("Error! You have entered a wrong group session!"); break;
                        }
                    }
                    break;
                case 4: //Check available slots in a class
                    courseMg.checkVacancy();
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
                    courseMg.printSessionStudent();
                    break;
                case 6: //enter course assessment weightage
                    break;
                case 7: //enter coursework mark
                    System.out.println("Enter course: ");
                    courseCode = sc.nextLine();
                    System.out.println("Enter student's matriculation number: ");
                    matricNumber = sc.nextLine();
                    sc.nextLine();
                    if(courseMg.verifyCourse(courseCode) == -1 || studMg.studentExists(matricNumber)== -1){
                        System.out.println("Student or course entered is not in our records!");
                    }
                    else {
                        course = courseMg.getCourse(courseCode);
                        student = studMg.getStudent(matricNumber);
                        results = courseMg.getAssessment(course);
                        for (i = 0; i < results.size(); i++){
                            while(marks > 100 || marks < 0){
                                System.out.println("Enter results the following component: " + results.get(i).getAssessmentName());
                                marks = sc.nextDouble();
                                sc.nextLine();
                                courseMg.setResults(results.get(i), student, marks);
                            }
                        }
                    }
                    break;
                case 8: //enter exam mark
                    break;
                case 9: //print course stats
                    break;
                case 10: //print student transcript
                    break;
                case 11: //exit
                    cont = false;
                    System.out.println("Exit....");
                    break;
                default:
                    System.out.println("Invalid choice, please try again!");
            }
        }

    }
}