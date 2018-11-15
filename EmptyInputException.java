/**
 * This is a user-defined exception class to handle empty input error from user.
 */
public class EmptyInputException extends Exception{

    String messages = "";

    /**
     * Constructor for EmptyInputException class
     * Call super class and print out default error messages
     */
    public EmptyInputException(){
        super("Error: input must not be blank.");
    }

    /**
     * Constructor for EmptyInputException class
     * @param arg variable name of the input that is empty
     * Prints out the appropriate error messages.
     */
    public EmptyInputException(String arg){
        String replacement = "";
        switch(arg){
            case "courseCode": replacement = "Course code"; break;
            case "matricNumber": replacement = "Matriculation number"; break;
            case "studentName": replacement = "Student name" ; break;
            case "school": replacement = "School"; break;
            case "tutorName": replacement = "Tutor's name"; break;
            case "dayTime": replacement = "Time"; break;
            case "location": replacement = "Location"; break;
            case "group": replacement = "Group"; break;
            case "type": replacement = "Session type"; break;
            default : replacement = "input"; break;
        }

        System.out.println("Error: " +replacement + " field must not be empty");
    }
}