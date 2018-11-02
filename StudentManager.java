import java.util.*;

public class StudentManager{
    private ArrayList<Student> studentCatalog= new ArrayList<Student>();

    public boolean addStudent(){
        String studentName = "";
        String matricNumber = "";
        String school = "";
        int acadYear = 0;
        char gender = 'N';
        char confirm = 'N';
        Scanner sc = new Scanner(System.in);

        
        while(confirm != 'Y'){
            System.out.println("Enter student's name: ");
            studentName = sc.nextLine();
            System.out.println("Enter student's matric No.: ");
            matricNumber = sc.nextLine();
            System.out.println("Enter student's school (SCSE): ");
            school = sc.nextLine();
            System.out.println("Enter student's year of study: ");
            acadYear = sc.nextInt();
            sc.nextLine();
            System.out.println("Enter student's gender: ");
            gender = sc.next().charAt(0);
            if(studentExists(matricNumber)){
                System.out.println("Student with matric number " + matricNumber + " already exists!");
            }
            else{
                System.out.println("Student: "+ studentName +", Matric No.:" + matricNumber + ", "+ school + " Year " + acadYear + " , "+gender);
                System.out.println("Are you sure you want to add in this student? (Y/N)");
                confirm = sc.next().charAt(0);
            }
        }
        studentCatalog.add(new Student(studentName, matricNumber, gender, school, acadYear));
        System.out.println("Student: "+ studentName +", Matric No.:" + matricNumber + ", "+ school + " Year " + acadYear + " , "+gender +"is added!");
        //print out all student (after added)
        printAllStudent();
        return true;
    }

    public void printAllStudent(){
        int i;
        System.out.println("All students in record: ");
        for (i = 0; i < studentCatalog.size(); i++){
            System.out.println("Student: "+ studentCatalog.get(i).getName() +", Matric No.:" + studentCatalog.get(i).getMatricNumber() + ", "+ studentCatalog.get(i).getSchool() + " Year " + studentCatalog.get(i).getAcadYear() + " , "+ studentCatalog.get(i).getGender());
        }
    }
    
    public boolean studentExists(String matricNumber){
        for (int i = 0; i< studentCatalog.size(); i++){
            if(studentCatalog.get(i).getMatricNumber() == matricNumber) return true;
        }
        return false;
    }
}