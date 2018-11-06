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
}