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
            for(Course course:courseCatalog){
                //remove the assessment results for that student
                ArrayList<Assessment> assessments = course.getAssessment();
                for(Assessment assessment: assessments){
                    assessment.removeAssessmentResult(student);
                }
                //deregister student and increase number of vacancy by 1
                ArrayList<Session> indexList = course.getAllSession();
                for(Session session : indexList){
                    ArrayList<Student> studentList = session.getStudentRegistered();
                    for(Student obtainedStudent:studentList){
                        if(obtainedStudent.equals(student)){
                            studentList.remove(student);
                            session.setNumberRegistered(session.getNumberRegistered()-1);
                            break;
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
                for(Student student: studentCatalog){
                    student.deregisterCourse(course);
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
        int i = 1;
        System.out.println("Course in current Catalog: ");
        System.out.println("========================================================================================");
        System.out.println("======================================= Course =========================================");
        for (Course course: courseCatalog){
            System.out.println(i + ". " +course);
            i++;
        }
        System.out.println("========================================================================================");
        System.out.println();
    }

    /**
     * A method to print out all existing {@link Student} in the database of this application.
     */
    public void printStudentCatalog(){
        int i = 1;
        System.out.println("All students in record: ");
        System.out.println("===============================================================================");
        System.out.println("================================== Student ====================================");
        for (Student student: studentCatalog){
            System.out.println(i + ". " + student );
            i++;
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
        for(Course course: courseCatalog){
            if(course.getCourseCode().equals(courseCode)) return course;
        }
        return null;
    }

    /**
     * A method to obtain the object {@link Student} stored in this database.
     * @param matricNumber  String matriculation number of the target student.
     * @return  Student target student object, null if not in database.
     */
    public Student getStudent(String matricNumber){
        for(Student student: studentCatalog){
            if(student.getMatricNumber().equals(matricNumber)) return student;
        }
        return null;
    }
}