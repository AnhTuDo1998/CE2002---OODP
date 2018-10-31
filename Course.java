import java.util.*;

public class Course{
    private ArrayList<Session> indexList = new ArrayList<Session>();
    private String courseName;
    private String courseCode;
    private int AU;
    private String courseCoordinator;
    private ArrayList<Assessment> results = new ArrayList<Assessment>(); 
    //index 0 is equivalent to exam marks, the rest are coursemarks

    public String getCourseName(){
        return this.courseName;
    }

    public String getCourseCode(){
        return this.courseCode;
    }

    public int getAU(){
        return this.AU;
    }

    public String getCourseCoordinator(){
        return this.courseCoordinator;
    }

    public int addSession(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter session type: (lec/tut/lab)");
        String type = sc.next();
        System.out.println("Enter session group : (SEP1/CE3/SEA2)");
        String group = sc.next();
        System.out.println("Enter session timing: (Mon 15:00 - 17:00/ Fri 09:00 - 11:00)");
        String dayTime = sc.next();
        System.out.println("Enter session location: (LT19a/TRx44/SWLAB3)");
        String location = sc.next();
        System.out.println("Enter session tutor name: ");
        String tutorName = sc.next();
        System.out.println("Enter session's max capacity: ");
        int maxCapacity = sc.nextInt();
        results.add(new Session(type, group, dayTime, location, tutorName, maxCapacity, 0));
    }

    public int setAssessment(){
        String name;
        int weightage;
        int totalWeightage; //total weightage must be equal to 100
        Scanner sc = new Scanner(System.in);
        while(totalWeightage!=100){
            System.out.println("Enter assessment type: (Finals, Quiz, Lab Report)");
            name = sc.next();
            System.out.println("Enter assessment weightage: (SEP1/CE3/SEA2)");
            weightage = sc.nextInt();
            if(weightage + totalWeightage > 100){
                System.out.println("Invalid weightage! Should not exceed a total of 100!");
            }
            else{
                System.out.println("Confirm entry of \"" + name + "\" weightage: " + weightage + "? (Y/N)");
                confirm = sc.next();
                if(confirm == Y){
                    totalWeightage += weightage;
                    results.add(new Assessment(name,weightage));
                    System.out.println("Results component \"" + name + "\" is added with a weightage of " + weightage);
                }
                else{
                    System.out.println("Results component not added.");
                }
            }
        }
        System.out.println("Results weightage completed....");
    }
}