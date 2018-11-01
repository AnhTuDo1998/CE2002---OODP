import java.util.*;

public class StudentManager{
    private ArrayList<Student> studentCatalog= new ArrayList<Student>();

    public addStudent(){
        String studentName;
        String matricNumber;
        String school;
        int acadYear;
        char gender;
        String confirm = "N";
        Scanner sc = new Scanner(System.in);

        while(confirm != "Y"){
            System.out.println("Enter student's name: ");
            studentName = sc.next();
            System.out.println("Enter student's matric No.: ");
            matricNumber = sc.next();
            System.out.println("Enter student's home school: ");
            school = sc.next();
            System.out.println("Enter student's year of study: ");
            acadYear = sc.nextInt();
            System.out.println("Enter student's gender: ");
            gender = sc.next.charAt(0);
            System.out.println("Student: "+ studentName +", Matric No.:" + matricNumber + ", "+ school + " Year " + acadYear + " , "+gender);
            System.out.println("Are you sure you want to add in this student? (Y/N)");
        }
        studentCatalog.add(new Student(studentName, matricNumber, gender, school, acadYear));
        System.out.println("Student: "+ studentName +", Matric No.:" + matricNumber + ", "+ school + " Year " + acadYear + " , "+gender +"is added!");
        //print out all student (after added)
        printAllStudent();
    }

    public printAllStudent(){
        int i;
        System.out.println("All students in record: ");
        for (i = 0; i < studentCatalog.size(); i++){
            System.out.println("Student: "+ studentCatalog.get(i).studentName +", Matric No.:" + studentCatalog.get(i).matricNumber + ", "+ studentCatalog.get(i).school + " Year " + studentCatalog.get(i).acadYear + " , "+ studentCatalog.get(i).gender);
        }
    }
    
}