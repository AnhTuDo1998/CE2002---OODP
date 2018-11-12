import java.util.*;
import java.io.*;
public class AssessmentManager{
    /**
     * A method to return the array list of {@link Assessment} for a particular Course object being parsed in.
     */
    public ArrayList<Assessment> getAssessment(Course course){
        return course.getAssessment();
    }

    /**
     * A method to set the weightage of {@link Assessment} components of the {@link Course} parsed in.
     * @param course The Course object whose Assessment components need to be set.
     */
    public void setAssessment(Course course){
        String name;
        double weightage = 0;
        double totalWeightage = 0; //total weightage must be equal to 100
        boolean finalsSet = false;
        Scanner sc = new Scanner(System.in);
        char confirm = 'N';
        course.clearAssessments(); //reset assessments if already set;
        
        while(totalWeightage!=100){
            try{
                if(finalsSet){
                    System.out.println();
                    System.out.println("Enter assessment type: (Quiz, Lab Report)");
                    name = sc.nextLine();
                    System.out.println();
                }
                else name = "Finals";
                System.out.println("Enter " + name + " weightage: (50, 70, 20)");
                System.out.println("Remaining weightage left: " + (100-totalWeightage));
                weightage = Double.parseDouble(sc.nextLine());
                //sc.nextLine();
                System.out.println();
                if(weightage <= 0){
                    throw new ArithmeticException("Error: weightage must not be negative!");
                }

                if(weightage + totalWeightage > 100){
                    System.out.println("Invalid weightage! Should not exceed a total of 100!");
                }
                else{
                    System.out.println("Confirm entry of \"" + name + "\" weightage: " + weightage + "? (Y/N)");
                    confirm = sc.nextLine().toUpperCase().charAt(0);
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
            }catch(ArithmeticException e){
                System.out.println(e.getMessage());
            }catch(NumberFormatException e){
                System.out.println();
                System.out.println("Error: input is invalid!");
                System.out.println("Please reenter!");
            }catch(Exception e){
                System.out.println("Error!");
            }
            
        }
        System.out.println("Results weightage completed....");
        return;
    }

    public void setResults(Course course, Student student){
        int i =0;
        Scanner sc = new Scanner(System.in);
        Double marks;

        ArrayList<Assessment> results = getAssessment(course);
        if(course.studentRegistered(student)){
            for (i = 0; i < results.size(); i++){
                marks = 101.0; //just for it to satisfy the while statement
                while(marks > 100 || marks < 0){
                    System.out.println("Enter results the following component: " + results.get(i).getAssessmentName());
                    marks = sc.nextDouble();
                    sc.nextLine();
                    course.enterResults(results.get(i), student, marks);
                }
            }
            if(i == 0) System.out.println("Error! Course Weightage is not set yet!");
        } else {
            System.out.println("Student is not registered in this course!");
        }
    }

    /**
     * A method to store result of {@link Student} for an {@link Assessment} component under this Course.
     * @param assessment the component that we need to store the result in (for instance finals, coursework, etc)
     * @param student the student who we need to key in result.
     * @param marks the grade of Student
     * @see Assessment#storeAssessmentResult(Student, double)
     */
    public void enterResults(Assessment assessment, Student student, double marks){
        assessment.storeAssessmentResult(student, marks);
    }

    /**
	 * A method to store store the marks of {@link Student} by inserting
	 * into a <a href = https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html"> HashMap </a>
	 * @param student Student student object to store mark/grade in.
	 * @param marks double the mark/grad to be stored in for the Student object.
	 */
	public void storeAssessmentResult(Student student, double marks){
		assessmentResults.put(student, marks);
	}

	/**
	 * A method to return the grade/mark of the calling Assessment object for the {@link Student} object being parsed in.
	 * @param student Student student who grade/mark to be searched and returned.
	 * @return double the grade/mark of the Assessment of the student being parse in if succeed, else return 0.
	 * @see <a href = https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html"> HashMap </a>
	 */
	public double retrieveAssessmentResult(Student student){
		if(assessmentResults.get(student)!= null){
			return assessmentResults.get(student);
		}
		else{
			return 0;  // for checking purpose
		}
	}

	/**
	 * A method to remove the grade/mark of {@link Student} object under the calling Assessment object.
	 * @param student Student object whose Assessment result is removed.
	 * @return boolean false if the removal of Assessement result for the Student is failed, return true if the removal is successful.
	 * @see <a href = https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html"> HashMap </a>
	 */
	public boolean removeAssessmentResult(Student student, Course course){
        boolean success = false; 
        ArrayList<Assessment> assessmentResult = course.getAssessment();
		if(assessmentResults.get(student) != null){
			assessmentResults.remove(student);
			success = true;
		}
		return success;
	}
}