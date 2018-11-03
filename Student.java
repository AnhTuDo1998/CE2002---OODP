import java.util.*;
public class Student{
    private String studentName;
    private String matricNumber;
    private String school;
    private int acadYear;
    private char gender;
    private ArrayList<String> coursesRegistered = new ArrayList<String>(); //arraylist of course codes registered
    private int totalAU = 0;


    public Student(){};

    public Student(String studentName,String matricNumber, char gender, String school, int acadYear){
        this.studentName = studentName;
        this.matricNumber = matricNumber;
        this.gender = gender;
        this.school = school;
        this.acadYear = acadYear;
    }

    public void registerCourse(String courseCode){
        coursesRegistered.add(courseCode);
    }

    public ArrayList<String> printTranscript(){
        return coursesRegistered;
    }

    public String getName(){
        return this.studentName;
    }

    public String getMatricNumber(){
        return this.matricNumber;
    }

    public String getSchool(){
        return this.school;
    }

    public int getAcadYear(){
        return this.acadYear;
    }

    public char getGender(){
        return this.gender;
    }
}
