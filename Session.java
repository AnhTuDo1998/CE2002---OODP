import java.util.*;
public class Session{
    private String type; //lec OR tut or lab
    private String group;
    private String dateTime;
    private String tutorName;
    private int maxCapacity;
    private int numberRegistered;
    private ArrayList<Student> studentList = new ArrayList<Student>();
}