import java.util.*;

public class CourseManager{
    private ArrayList<Course> courseCatalog= new ArrayList<Course>();
    
    public Course getCourse(int index){
        return courseCatalog.get(index);
    }

    //rmb to implement return int
    public int addCourse(){
        String courseName = "";
        String courseCode = "";
        String courseCoordinator = "";
        int AU = 0;
        char confirm = 'N';
        Scanner sc = new Scanner(System.in);
        
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
            confirm = sc.next().charAt(0);
        }
        courseCatalog.add(new Course(courseName, courseCode, AU, courseCoordinator));
        System.out.println(courseCode + " " + courseName + " AU: " + AU + " by " + courseCoordinator +" is added.");
        //print out all courses after added in
        printCourseCatalog();
        return 1;
    }

    public int removeCourse(){
        int i = 0;
        Scanner sc = new Scanner(System.in);
        int index = -1;
        String courseCode;

        printCourseCatalog();
        System.out.println("Please enter the course code that you want to remove the course");
        courseCode = sc.nextLine();

        index = verifyCourse(courseCode);

        //return -1 if failed, return index of the removed array element if success
        if (index >= 0){
            courseCatalog.remove(i); //will return true or false depends on whether the session is created
            return index;
        }

        else{
            return index;
        }

        /*for(i = 0; i < courseCatalog.size(); i++){
            if(courseCatalog.get(i).getCourseCode()== courseCode){
                success = courseCatalog.get(i).removeSession();
                break;
            }
        }*/
    }

    //return -1 if not exist 
    public int verifyCourse(String courseCode){
        int i;
        int exist = -1;

        for (i = 0; i < courseCatalog.size(); i++){
            if (courseCatalog.get(i).getCourseCode() == courseCode)
                exist = i;
        }
        return exist;
    }

    public void printCourseCatalog(){
        int i;
        System.out.println("Course in current Catalog: ");
        for (i = 0; i < courseCatalog.size(); i++){
            System.out.println(courseCatalog.get(i).getCourseCode() + " " + courseCatalog.get(i).getCourseName() + " AU: " + courseCatalog.get(i).getAU() + " by " + courseCatalog.get(i).getCourseCoordinator());
        }
    }
    
    public void addSession(){
        //int i;
        Scanner sc = new Scanner(System.in);
        String courseCode;
        boolean success = false;
        int index = -1;

        printCourseCatalog(); //print out all the course
        System.out.println("Please enter the course code that you want to create/add new session.");
        courseCode = sc.nextLine();

        /*for(i = 0; i < courseCatalog.size(); i++){
            if(courseCatalog.get(i).getCourseCode() == courseCode){
                success = courseCatalog.get(i).addSession(); //will return true or false depends on whether the session is created
                break;
            }
        }*/
        index = verifyCourse(courseCode);

        if (index >= 0){
            success = courseCatalog.get(index).addSession(); //will return true or false depends on whether the session is created
        }

        //session created successfully
        if(success){
            System.out.println("New session for " + courseCode + " is created successfully!");
        }else{
            System.out.println("Session is not added! Session does not exist");
        }
    }


    //find a way to delete seperate session ?
    public void removeSession(){
        int i;
        Scanner sc = new Scanner(System.in);
        boolean success = false;
        int index = -1;
        String courseCode;

        printCourseCatalog();
        System.out.println("Please enter the course code that you want to remove session.");
        courseCode = sc.nextLine();

        index = verifyCourse(courseCode);

        if (index >= 0){
            success = courseCatalog.get(index).removeSession(); //will return true or false depends on whether the session is created
        }

        //session created successfully
        if(success){
            System.out.println("Session for " + courseCode + " is deleted successfully!");
        }
        
        else{
            System.out.println("Session is not added! Session does not exist");
        }

        /*for(i = 0; i < courseCatalog.size(); i++){
            if(courseCatalog.get(i).getCourseCode()== courseCode){
                success = courseCatalog.get(i).removeSession();
                break;
            }
        }*/
    }

    public void checkVacancy(String courseCode){
        //find index or course you want to check vacancy
        int index = verifyCourse(courseCode);
        //print out all session and let user choose

    }
}