import java.util.*;
import java.io.*;

public class StudentManager{
    private ArrayList<Student> studentCatalog= new ArrayList<Student>();

    public Student addStudent(String studentName,String  matricNumber,char gender,String school,int acadYear){
        Student student = new Student(studentName, matricNumber, gender, school, acadYear);
        studentCatalog.add(student);
        return student;
    }

    public void printAllStudent(){
        int i;
        System.out.println("All students in record: ");
        for (i = 0; i < studentCatalog.size(); i++){
            System.out.println((i+1) + ". " + studentCatalog.get(i));
        }
    }
    
//return -1 if student is not inside, studentindex otherwise
    public int studentExists(String matricNumber){
        for (int i = 0; i< studentCatalog.size(); i++){
            if(matricNumber.equals(studentCatalog.get(i).getMatricNumber())){
                return i;
            } 
        }
        return -1;
    }

    public void updateCourseTaken(Course course, int i){
        studentCatalog.get(i).registerCourse(course);
    }

    public Student getStudent(String matricNumber){
        for(int i = 0; i < studentCatalog.size(); i++){
            if(studentCatalog.get(i).getMatricNumber().equals(matricNumber)) return studentCatalog.get(i);
        }
        return null;
    }

    public void printTranscript(Student student){
        ArrayList<Course> courseRegistered = student.getCourseRegistered();
        ArrayList<Assessment> results = null;
        double totalResults = 0;
        String grade = "F";
        for(int i = 0; i < courseRegistered.size(); i++){
            totalResults = 0;
            results = courseRegistered.get(i).getAssessment();
            for(int j = 0; j < results.size(); j++){
                totalResults += results.get(j).retrieveAssessmentResult(student) * results.get(j).getWeightage() / 100;
            }
            System.out.println(courseRegistered.get(i).getCourseCode() + " " + courseRegistered.get(i).getCourseName() + " - Final Grade :" + marksToGrade(totalResults));
        }
    }

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

    public void loadData(String filename) {
		ArrayList<Student> studentList = null;
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = new FileInputStream(filename);
			in = new ObjectInputStream(fis);
			studentList = (ArrayList) in.readObject();
			in.close();
		} catch (Exception ex) {
            // ex.printStackTrace();
            System.out.println("=================================================================================================");
            System.out.println("================== ERROR! NO DATA LOADED (ignore if this is your first loadup) ==================");
            System.out.println("=================================================================================================");
		}
		// print out the size
		//System.out.println(" Details Size: " + pDetails.size());
        //System.out.println();
        if(studentList != null){
            this.studentCatalog = studentList;
        }
	}

	public void saveData(String filename) {
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream(filename);
			out = new ObjectOutputStream(fos);
			out.writeObject(studentCatalog);
			out.close();
		//	System.out.println("Object Persisted");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}