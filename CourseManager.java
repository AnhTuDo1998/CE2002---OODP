import java.util.*;
import java.io.*;

public class CourseManager{
    private ArrayList<Course> courseCatalog = new ArrayList<Course>();
    
    public Course getCourse(int index){
        return courseCatalog.get(index);
    }

    //rmb to implement return int
    public Course addCourse(String courseName, String courseCode, int AU, String courseCoordinator){

        Course created = null;
        
        
        created = new Course(courseName, courseCode, AU, courseCoordinator);
        courseCatalog.add(created);
        return created;
    }

    //return 0 if added successfully, -1 if full, -2 if student is inside -3 if group does not exist
    public int regStudentToCourse(Student student, int i, String group, String type){
        return courseCatalog.get(i).registerStudent(student, group, type);
    }

    public int removeCourse(){
        int i = 0;
        Scanner sc = new Scanner(System.in);
        int index = -1;
        String courseCode;

        printCourseCatalog();
        System.out.println("Please enter the course code that you want to remove the course");
        courseCode = sc.nextLine();

        index = verifyCourse(courseCode);

        //return -1 if failed, return index of the removed array element if success
        if (index >= 0){
            courseCatalog.remove(i); //will return true or false depends on whether the session is created
            return index;
        }

        else{
            return index;
        }

        /*for(i = 0; i < courseCatalog.size(); i++){
            if(courseCatalog.get(i).getCourseCode()== courseCode){
                success = courseCatalog.get(i).removeSession();
                break;
            }
        }*/
    }

    //return -1 if not exist , courseindex in arraylist otherwise
    public int verifyCourse(String courseCode){
        int i;
        int exist = -1;

        for (i = 0; i < courseCatalog.size(); i++){
            if (courseCatalog.get(i).getCourseCode().equals(courseCode)){
                exist = i;
            }
        }
        return exist;
    }

    public void printCourseCatalog(){
        int i;
        System.out.println("Course in current Catalog: ");
        for (i = 0; i < courseCatalog.size(); i++){
            System.out.println(courseCatalog.get(i));
        }
    }
    
    public Course getCourse(String courseCode){
        for(int i = 0; i < courseCatalog.size(); i++){
            if(courseCatalog.get(i).getCourseCode().equals(courseCode)) return courseCatalog.get(i);
        }
        return null;
    }
    public void addSession(){
        //int i;
        Scanner sc = new Scanner(System.in);
        String courseCode;
        boolean success = false;
        int index = -1;

        printCourseCatalog(); //print out all the course
        System.out.println("Please enter the course code that you want to create/add new session.");
        courseCode = sc.nextLine();

        /*for(i = 0; i < courseCatalog.size(); i++){
            if(courseCatalog.get(i).getCourseCode() == courseCode){
                success = courseCatalog.get(i).addSession(); //will return true or false depends on whether the session is created
                break;
            }
        }*/
        index = verifyCourse(courseCode);

        if (index >= 0){
            success = courseCatalog.get(index).addSession(); //will return true or false depends on whether the session is created
        }

        //session created successfully
        if(success){
            System.out.println("New session for " + courseCode + " is created successfully!");
        }else{
            System.out.println("Session is not added! Session does not exist");
        }
    }


    //find a way to delete seperate session ?
    public void removeSession(){
        int i;
        Scanner sc = new Scanner(System.in);
        boolean success = false;
        int index = -1;
        String courseCode;

        printCourseCatalog();
        System.out.println("Please enter the course code that you want to remove session.");
        courseCode = sc.nextLine();

        index = verifyCourse(courseCode);

        if (index >= 0){
            success = courseCatalog.get(index).removeSession(); //will return true or false depends on whether the session is created
        }

        //session created successfully
        if(success){
            System.out.println("Session for " + courseCode + " is deleted successfully!");
        }
        
        else{
            System.out.println("Session is not added! Session does not exist");
        }

        /*for(i = 0; i < courseCatalog.size(); i++){
            if(courseCatalog.get(i).getCourseCode()== courseCode){
                success = courseCatalog.get(i).removeSession();
                break;
            }
        }*/
    }
  
  public void getSessions(int i){
        courseCatalog.get(i).printSessions();
  }
  
    public void checkVacancy(Course course){
        if (course != null){
            course.printSessions();
        }
        else{
            System.out.println("Course does not exist");
        } 
    }

    public void printSessionStudent(Course course){
        Scanner sc = new Scanner(System.in);
        if(course != null){
            course.printIndexList();
            System.out.println("Enter the session name: ");
            String sessionGroup = sc.next();
            System.out.println("Enter the session type: ");
            String sessionType = sc.next();
            Session group = course.getSession(sessionGroup, sessionType); //return null if not found
            if(group != null){
                System.out.println(course + " ||| " + group);
                System.out.println("==========================================");
                group.printSessionStudent(); //if exist go ahead and print list
            }else{
                System.out.println("Session doesn't exist!"); 
            }
        }else{
            System.out.println("Course doesn't exist!");
        }
    }

    public void setResults(Assessment component, Student student, double marks){
        component.storeAssessmentResult(student, marks);
    }

    public ArrayList<Assessment> getAssessment(Course course){
        return course.getAssessment();
    }

    public int setAssessment(Course course){
        String name;
        double weightage = 0;
        double totalWeightage = 0; //total weightage must be equal to 100
        boolean finalsSet = false;
        Scanner sc = new Scanner(System.in);
        char confirm = 'N';
        
        while(totalWeightage!=100){
            if(finalsSet){
                System.out.println("Enter assessment type: (Quiz, Lab Report)");
                name = sc.nextLine();
            }
            else name = "Finals";
            System.out.println("Enter " + name + " weightage: (50, 70, 20)");
            System.out.println("Remaining weightage left: " + (100-totalWeightage));
            weightage = sc.nextDouble();
            sc.nextLine();
            if(weightage + totalWeightage > 100){
                System.out.println("Invalid weightage! Should not exceed a total of 100!");
            }
            else{
                System.out.println("Confirm entry of \"" + name + "\" weightage: " + weightage + "? (Y/N)");
                confirm = sc.nextLine().charAt(0);
                if(confirm == 'Y'){
                    totalWeightage += weightage;
                    course.setAssessment(new Assessment(name,weightage));
                    if(name.equals("Finals")) finalsSet = true;
                    System.out.println("Results component \"" + name + "\" is added with a weightage of " + weightage);
                }
                else{
                    System.out.println(name + " component not added.");
                }
            }
        }
        System.out.println("Results weightage completed....");
        return 0;
    }

    public void loadData(String filename) {
		ArrayList<Course> courseList = null;
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = new FileInputStream(filename);
			in = new ObjectInputStream(fis);
			courseList = (ArrayList) in.readObject();
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
        if(courseList != null){
            this.courseCatalog = courseList;
        }
	}

	public void saveData(String filename) {
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream(filename);
			out = new ObjectOutputStream(fos);
			out.writeObject(courseCatalog);
			out.close();
		//	System.out.println("Object Persisted");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
    }
    
    public void printCourseStats(Course course){
        ArrayList<Assessment> assessmentList = course.getAssessment();
        ArrayList<Session> sessionList = course.getAllSession();
        ArrayList<Student> registeredStudents = new ArrayList<Student>();
        Set<Student> buffer = new HashSet<>();
        int[] results = new int[10];
        //index 0 = A+, 1 = A....9 = F
        int totalMale = 0;
        int totalFemale = 0;
        int[] year = new int[4];
        int totalResults = 0;
        //index 0 = year1, 1 = year2, 2 = year3, 3 = year4 
        for(int i = 0; i < sessionList.size(); i++){
            buffer.addAll(sessionList.get(i).getStudentRegistered());
            //get all registered students, using set to remove duplicates
        }
        registeredStudents.addAll(buffer);
        for(int i = 0; i < registeredStudents.size(); i++){
            year[registeredStudents.get(i).getAcadYear()-1]++;
            if(registeredStudents.get(i).getGender() == ('M')) totalMale++;
            else totalFemale++;
            totalResults = 0;
            for(int j = 0; j < assessmentList.size(); j++){
                totalResults += assessmentList.get(j).retrieveAssessmentResult(registeredStudents.get(i)) * assessmentList.get(j).getWeightage() / 100;
            }
            if(totalResults > 90){
                results[0]++;
            }else if(totalResults >80){
                results[1]++;
            }else if(totalResults >75){
                results[2]++;
            }else if(totalResults >70){
                results[3]++;
            }else if(totalResults >60){
                results[4]++;
            }else if(totalResults >50){
                results[5]++;
            }else if(totalResults >45){
                results[6]++;
            }else if(totalResults >40){
                results[7]++;
            }else if(totalResults >30){
                results[8]++;
            }else results[9]++;
        }
        System.out.println("Printing course statistics for " + course);
        System.out.println("====== Gender Distribution =====");
        System.out.println("Male  : " + totalMale);
        System.out.println("Female: " + totalFemale);
        System.out.println("====== Year Distribution =====");
        System.out.println("Year 1: " + year[0]);
        System.out.println("Year 2: " + year[1]);
        System.out.println("Year 3: " + year[2]);
        System.out.println("Year 4: " + year[3]);
        System.out.println("====== Grade Distribution =====");
        System.out.println("A+ : " + results[0]);
        System.out.println("A  : " + results[1]);
        System.out.println("A- : " + results[2]);
        System.out.println("B+ : " + results[3]);
        System.out.println("B  : " + results[4]);
        System.out.println("B- : " + results[5]);
        System.out.println("C+ : " + results[6]);
        System.out.println("C  : " + results[7]);
        System.out.println("D  : " + results[8]);
        System.out.println("F  : " + results[9]);
    }
}