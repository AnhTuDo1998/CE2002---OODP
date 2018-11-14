import java.util.*;
import java.io.*;

/**
 * An entity class that mainly stores all data related to this application. 
 * This class is based on the interface
 */
public class Database implements Serializable{
    /**
     * The ArrayList of Students for the application at the moment
     */
    private ArrayList<Student> studentCatalog= new ArrayList<Student>();
    /**
     * The ArrayList of Courses for the application at the moment
     */
    private ArrayList<Course> courseCatalog = new ArrayList<Course>();

    /**
	 * A method to get the ArrayList of {@link Student} stored in this application.
	 * @return ArrayList of all students registered in this application.
     * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html">ArrayList</a> 
	 */
    public ArrayList<Student> getStudentCatalog(){
        return this.studentCatalog;
    }

    /**
     * A method to get the ArrayList of {@link Course} stored in this application.
     * @return ArrayList of all courses registered in this application.
     * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html">ArrayList</a>
     */
    public ArrayList<Course> getCourseCatalog(){
        return this.courseCatalog;
    }

    /**
     * A method to add a {@link Student} into the database of this application.
     * @param courseName String name of the new course.
     * @param courseCode String course code of the new course.
     * @param AU int academic units allocated to this course.
     * @param courseCoordinator String name of the course coordinator.
     * @return Course the newly created course.
     */
    public Course addCourse(String courseName, String courseCode, int AU, String courseCoordinator){

        Course created = null;
        
        
        created = new Course(courseName, courseCode, AU, courseCoordinator);
        courseCatalog.add(created);
        return created;
    }
    
    /**
     * A method to add a {@link Student} into the database of this application.
     * @param studentName String name of the new student.
     * @param matricNumber String matriculation number of the new student.
     * @param gender char gender of the new student.
     * @param school String name of the school of the new student.
     * @param acadYear int current academic year of the new student.
     * @return Student the newly created student.
     */
    public Student addStudent(String studentName,String  matricNumber,char gender,String school,int acadYear){
        Student student = new Student(studentName, matricNumber, gender, school, acadYear);
        studentCatalog.add(student);
        return student;
    }

    /**
     * A method to remove an existing {@link Student} from the database of this application.
     * <p>
     * This method will also remove all students from its registered sessions 
     * by iterating through its registered {@link Course} and all {@link Session} under
     * the courses, results of the student will be erased as well.
     * </p>
     * @param student Student the student object to be removed.
     * @see Course#getAssessment()
     * @see Course#getAllSession()
     * @see Session#getStudentRegistered()
     * @see Assessment#removeAssessmentResult(Student)
     */
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

    /**
     * A method to remove an existing {@link Course} from the database of this application.
     * <p>
     * All students that are currently registered to this course will be deregistered.
     * @param course Course the course to be removed.
     * @see Student#deregisterCourse(Course)
     * @return boolean true if there exist Course to be removed, else false
     */
    public boolean removeCourse(Course course){
    
        //return deleted course, null ff none
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

    /**
     * A method to print out all existing {@link Course} in the database of this application.
     */
    public void printCourseCatalog(){
        int i;
        System.out.println("Course in current Catalog: ");
        System.out.println("========================================================================================");
        System.out.println("======================================= Course =========================================");
        for (i = 0; i < courseCatalog.size(); i++){
            System.out.println((i+1) + ". " +courseCatalog.get(i));
        }
        System.out.println("========================================================================================");
        System.out.println();
    }

    /**
     * A method to print out all existing {@link Student} in the database of this application.
     */
    public void printStudentCatalog(){
        int i;
        System.out.println("All students in record: ");
        System.out.println("===============================================================================");
        System.out.println("================================== Student ====================================");
        for (i = 0; i < studentCatalog.size(); i++){
            System.out.println((i+1)+ ". " + studentCatalog.get(i) );
        }
        System.out.println("===============================================================================");
        System.out.println();
    }

    /**
     * A method to obtain the object {@link Course} stored in the database.
     * @param courseCode String course code of the target course.
     * @return Course target course object, null if not in database.
     */
    public Course getCourse(String courseCode){
        for(int i = 0; i < courseCatalog.size(); i++){
            if(courseCatalog.get(i).getCourseCode().equals(courseCode)) return courseCatalog.get(i);
        }
        return null;
    }

    /**
     * A method to obtain the object {@link Student} stored in this database.
     * @param matricNumber  String matriculation number of the target student.
     * @return  Student target student object, null if not in database.
     */
    public Student getStudent(String matricNumber){
        for(int i = 0; i < studentCatalog.size(); i++){
            if(studentCatalog.get(i).getMatricNumber().equals(matricNumber)) return studentCatalog.get(i);
        }
        return null;
    }
}