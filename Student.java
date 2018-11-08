import java.util.*;
import java.io.*;

public class Student implements Serializable{
    private String studentName;
    private String matricNumber;
    private String school;
    private int acadYear;
    private char gender;
    private ArrayList<Course> coursesRegistered = new ArrayList<Course>(); //arraylist of course codes registered
    private int totalAU = 0;


    public Student(){};

    public Student(String studentName,String matricNumber, char gender, String school, int acadYear){
        this.studentName = studentName;
        this.matricNumber = matricNumber;
        this.gender = gender;
        this.school = school;
        this.acadYear = acadYear;
    }

    public void registerCourse(Course course){
        for(int i = 0; i < coursesRegistered.size(); i++){
            if(coursesRegistered.get(i) == course) return; //if course is already registered, ignore
        }
        coursesRegistered.add(course);
    }

    public boolean deregisterCourse(Course course){
        return coursesRegistered.remove(course);
    }

    public ArrayList<Course> getCourseRegistered(){
        return this.coursesRegistered;
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

    public String toString(){
        return (this.studentName + " | Matriculation Number: " + this.matricNumber + " | School: " + this.school + "/" + this.acadYear + " | Gender: " + this.gender);
    }
}
