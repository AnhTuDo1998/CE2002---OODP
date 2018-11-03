import java.util.*;

public class ScrameApp{
    public static void main(String[] args) {
        Scanner sc = new Scanner (System.in);
        StudentManager studMg = new StudentManager();
        CourseManager courseMg = new CourseManager();
        int choice;
        boolean cont = true;
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
                    courseMg.addCourse();
                    break;
                case 3: //register student for a course
                    //studMg.regStudentToCourse();
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