import java.util.*;
import java.io.*;
/**
 * A controller class to be in-charge of most services related to Student objects
 */
public class StudentManager{
    /**
     * A utility method to update/synchronise the Course taken by Student.
     * @param course Course object that Student is registered but need update
     * @param student Student object that need to be updated
     * @see Student#registerCourse(Course)
     */
    public void updateCourseTaken(Course course, Student student){
        student.registerCourse(course);
    }

    /**
     * A method to print out the transcript of Student object parsed in.
     * The transcript involved total grade of the Student for the semester and the results of Assessment components under each Course object that
     * Student is registered in
     * <p> First get the list of Course that Student is registered in. Next, declare and instantiate an empty list of Assessment objects to traverse later.
     * <p> Traverse the list of Course courseRegistered for the Student and access the list of Assessment components under each Course object being visited.
     * By traversing this list of Assessment objects, print out the individual grade/marks for the components under the visited Course' Assessment. 
     * When last Assessment component is printed out, move to the next Course in the list. 
     * <p> Repeat until all Course objects in the list is visited. 
     * @param student Student object whose transcript need to be print out.
     * @see #courseRegistered 
     * @see #results
     */
    public void printTranscript(Student student){
        ScrameApp.printSpaces();
        System.out.println(student);
        System.out.println("==================================== TRANSCRIPT ======================================");
        ArrayList<Course> courseRegistered = student.getCourseRegistered();
        ArrayList<Assessment> results = null;
        double totalResults = 0;
        double totalGPA = 0;
        int totalAU = 0;
        String grade = "F";
        for(int i = 0; i < courseRegistered.size(); i++){
            totalResults = 0;
            results = courseRegistered.get(i).getAssessment();
            System.out.println(courseRegistered.get(i));
            for(int j = 0; j < results.size(); j++){
                System.out.printf("%1$35s Marks : %2$1s (%3$2s%%)\n", results.get(j).getAssessmentName(), results.get(j).retrieveAssessmentResult(student), results.get(j).getWeightage());
                totalResults += results.get(j).retrieveAssessmentResult(student) * results.get(j).getWeightage() / 100;
            }
            System.out.println("======================= Final Grade: " + marksToGrade(totalResults) + " ======= GPA: " + marksToGPA(totalResults) + " =============================");
            System.out.println("======================================================================================");
            System.out.println();
            totalAU += courseRegistered.get(i).getAU();
            totalGPA += courseRegistered.get(i).getAU()*marksToGPA(totalResults);
        }
        System.out.println("====================================== CGPA : " + totalGPA/totalAU + " ===================================");
    }

    /**
     * A utility method to convert number mark to letter grade.
     * @param double the numeric marks to be converted
     * @return String the letter grade of the numeric mark being queried.
     */
    public String marksToGrade(double marks){
        if(marks > 90) return "A+";
        if(marks > 80) return "A";
        if(marks > 75) return "A-";
        if(marks > 70) return "B+";
        if(marks > 60) return "B";
        if(marks > 50) return "B-";
        if(marks > 45) return "C+";
        if(marks > 40) return "C";
        if(marks > 30) return "D";
        return "F";
    }
    
    /**
     * A utility method to convert number mark to numerical grade-point average.
     * @param marks the numeric marks to be converted.
     * @return the GPA of the subject.
     */
    public double marksToGPA(double marks){
        if(marks > 90) return 5.00;
        if(marks > 80) return 5.00;
        if(marks > 75) return 4.50;
        if(marks > 70) return 4.00;
        if(marks > 60) return 3.50;
        if(marks > 50) return 3.00;
        if(marks > 45) return 2.50;
        if(marks > 40) return 2.00;
        if(marks > 30) return 1.50;
        if(marks > 20) return 1.00;
        return 0.00;
    }

    /**
     * A method to control the removal of Student from Course (both passed in)
     * @param student Student object that is of our interest to remove from the Course
     * @param course Course object where the Student must be removed from
     * @see Student#deregisterCourse(Course) for more information
     */
    public void deregisterCourse(Student student, Course course){
        student.deregisterCourse(course);
    }

    /**
     * A method to print all Course the Student object is registered under. This is done by getting the list of Course the student is registered under and print out.
     * @param student Student object who we need to print out all the Course objects he/she is registered under
     * @see Student#getCourseRegistered()
     */
    public void printCourseRegistered(Student student){
        int i =0;
        ArrayList<Course> courseRegistered = student.getCourseRegistered();
        System.out.println("======================================================================================");
        System.out.println("================================= Course Registered ==================================");
        for(i = 0; i < courseRegistered.size(); i++){
            System.out.println(courseRegistered.get(i));
        }
        System.out.println("======================================================================================");
    }
}