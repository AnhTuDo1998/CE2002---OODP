import java.util.*;
import java.io.*;

public class Database implements Serializable{
    private ArrayList<Student> studentCatalog= new ArrayList<Student>();
    private ArrayList<Course> courseCatalog = new ArrayList<Course>();

    public ArrayList<Student> getStudentCatalog(){
        return this.studentCatalog;
    }

    public ArrayList<Course> getCourseCatalog(){
        return this.courseCatalog;
    }

    public Course addCourse(String courseName, String courseCode, int AU, String courseCoordinator){

        Course created = null;
        
        
        created = new Course(courseName, courseCode, AU, courseCoordinator);
        courseCatalog.add(created);
        return created;
    }
    
    
    public Student addStudent(String studentName,String  matricNumber,char gender,String school,int acadYear){
        Student student = new Student(studentName, matricNumber, gender, school, acadYear);
        studentCatalog.add(student);
        return student;
    }

    public void removeStudent(Student student){
        if(studentCatalog.remove(student)){
            for(int i = 0; i < courseCatalog.size(); i++){
                //remove the assessment results for that student
                ArrayList<Assessment> assessments = courseCatalog.get(i).getAssessment();
                for(int z = 0; z < assessments.size(); z++){
                    assessments.get(z).removeAssessmentResult(student);
                }
                //deregister student and increase number of vacancy by 1
                ArrayList<Session> indexList = courseCatalog.get(i).getAllSession();
                for(int j = 0; j < indexList.size(); j++){
                    ArrayList<Student> studentList = indexList.get(j).getStudentRegistered();
                    for(int k = 0; k < studentList.size(); k++){
                        if(studentList.get(i).equals(student)){
                            studentList.remove(student);
                            indexList.get(j).setNumberRegistered(indexList.get(j).getNumberRegistered()-1);
                        }
                    }
                }
            }
        }else{
            System.out.println("Student not removed!");
        }
    }

    public boolean removeCourse(Course course){
    
        //return deleted course, null of none
        if (course != null){
            if(courseCatalog.remove(course)){
                for(int i = 0 ; i < studentCatalog.size(); i++){
                    studentCatalog.get(i).deregisterCourse(course);
                }
                return true;
            } //will return true or false depends on whether the session is created
        }
        return false;
    }

    public void printCourseCatalog(){
        int i;
        System.out.println("Course in current Catalog: ");
        System.out.println("======================================================================================");
        for (i = 0; i < courseCatalog.size(); i++){
            System.out.println(courseCatalog.get(i));
        }
    }

    
    public void printStudentCatalog(){
        int i;
        System.out.println("All students in record: ");
        System.out.println("==============================================================================");
        for (i = 0; i < studentCatalog.size(); i++){
            System.out.println((i+1) + ". " + studentCatalog.get(i));
        }
    }

    public Course getCourse(String courseCode){
        for(int i = 0; i < courseCatalog.size(); i++){
            if(courseCatalog.get(i).getCourseCode().equals(courseCode)) return courseCatalog.get(i);
        }
        return null;
    }

    public Student getStudent(String matricNumber){
        for(int i = 0; i < studentCatalog.size(); i++){
            if(studentCatalog.get(i).getMatricNumber().equals(matricNumber)) return studentCatalog.get(i);
        }
        return null;
    }
}