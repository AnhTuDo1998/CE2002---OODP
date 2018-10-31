import java.util.*;

public class CourseManager{
    private ArrayList<Course> courseCatalog= new ArrayList<Course>();
    
    public addCourse(){
        String courseName;
        String courseCode;
        String courseCoordinator;
        int AU;
        String confirm = "N";
        Scanner sc = new Scanner(System.in);
        
        while(confirm != "Y"){
            System.out.println("Enter course name: (Computer Vision/Object-Orientated Design & Programming)");
            courseName = sc.next();
            System.out.println("Enter course code: (CE2005/CE4001/CZ1023)");
            courseCode = sc.next();
            System.out.println("Enter course AU: (2/3)");
            AU = sc.nextInt();
            System.out.println("Enter name of course coordinator: ");
            courseCoordinator = sc.next();
            System.out.println(courseCode + " " + courseName + " AU: " + AU + " by " + courseCoordinator);
            System.out.println("Are you sure you want to add in this course? (Y/N)");
        }
        courseCatalog.add(new Course(courseName, courseCode, AU, courseCoordinator));
        System.out.println(courseCode + " " + courseName + " AU: " + AU + " by " + courseCoordinator +" is added.");
    }
}