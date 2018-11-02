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
        //print out all courses after added in
        printCourseCatalog();
    }

    public void printCourseCatalog(){
        int i;
        System.out.println("Course in current Catalog: ");
        for (i = 0; i < courseCatalog.size(); i++){
            System.out.println(courseCatalog.get(i).getCourseCode() + " " + courseCatalog.get(i).getCourseName() + " AU: " + courseCatalog.get(i).getAU() + " by " + courseCatalog.get(i).getCourseCoordinator());
        }
    }
    
    public void addSession(){
        int i;
        Scanner sc = new Scanner(System.in);
        String courseCode;
        boolean success = false;

        printCourseCatalog(); //print out all the course
        System.out.println("Please enter the course code that you want to create/add new session.");
        courseCode = sc.next();

        for(i = 0; i < courseCatalog.size(); i++){
            if(courseCatalog.get(i).getCourseCode() == courseCode){
                success = courseCatalog.get(i).addSession(); //will return true or false depends on whether the session is created
                break;
            }
        }
        //session created successfully
        if(success){
            System.out.println("New session for " + courseCode + " is created successfully!");
        }else{
            System.out.println("Session is not added!");
        }
    }

    public void removeSession(){
        int i;
        Scanner sc = new Scanner(System.in);
        boolean success;

        printCourseCatalog();
        System.out.println("Please enter the course code that you want to remove session.");
        courseCode = sc.next();

        for(i = 0; i < courseCatalog.size(); i++){
            if(courseCatalog.get(i).getCourseCode()== courseCode){
                success = courseCatalog.get(i).removeSession();
                break;
            }
        }
    }
}