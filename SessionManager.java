import java.util.*;
import java.io.*;

public class SessionManager{
    /**
     * A method to add in new {@link Session} under the calling Course object (making use of ArrayList for storage). 
     * @return boolean false if the new Session object is not added, true if the new Session object is added
     * @see <a href = https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html"> HashMap </a>
     */
    public boolean addSession(Course course) throws StringIndexOutOfBoundsException{
        boolean success = false;
        ArrayList<Session> indexList = course.getAllSession();
        ScrameApp.printSpaces();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter session type: (LEC/TUT/LAB)");
        String type = sc.nextLine();
        System.out.println();
        System.out.println("Enter session group : (SEP1/CE3/SEA2)");
        String group = sc.nextLine();
        System.out.println();
        System.out.println("Enter session timing: (Mon 15:00 - 17:00/ Fri 09:00 - 11:00)");
        String dayTime = sc.nextLine();
        System.out.println();
        System.out.println("Enter session location: (LT19a/TRx44/SWLAB3)");
        String location = sc.nextLine();
        System.out.println();
        System.out.println("Enter session tutor name: ");
        String tutorName = sc.nextLine();
        System.out.println();
        System.out.println("Enter session's max capacity: ");
        int maxCapacity = sc.nextInt();
        System.out.println();
        sc.nextLine(); //capture \n
        if(type.isEmpty() || group.isEmpty() || dayTime.isEmpty() || location.isEmpty()|| tutorName.isEmpty()){
            throw new StringIndexOutOfBoundsException();
        }
        if(getSession(group, type) != null){
            System.out.println("This session is already in!");
        }else if(maxCapacity < 1){
            System.out.println("Please enter a valid capacity!");
        }else{
            success = indexList.add(new Session(type, group, dayTime, location, tutorName, maxCapacity, 0));
        }
        return success;
    }

    /**
     * A method to modify information about existing {@link Session}. 
     * <p> The method parse in the Session object need to be modified, following by accepting inputs from users to change the wanted
     * field of information for that session.
     * @param session The Session that need modification (for example changing any of the following: Type, Group ID, Timing, ...)
     * @return boolean true if the modification is successful and false if otherwise.
     */
    public boolean modifySession(Session session){
        boolean success = false;
        char conti = 'Y';
        int choice;
        Scanner sc = new Scanner(System.in);
        do{
            System.out.println("Select the field of Session to edit: ");
            System.out.println("1.Type");
            System.out.println("2.Group ID");
            System.out.println("3.Timing");
            System.out.println("4.Location");
            System.out.println("5.Tutor Name");
            System.out.println("6.Max Capacity");
            choice = sc.nextInt();
            sc.nextLine();
            switch(choice){
                case 1:
                    System.out.println("Old session's type: " + session.getType());
                    System.out.println("Enter new session's type: (LEC/TUT/LAB)");
                    String type = sc.nextLine();
                    session.setType(type);
                    success = true;
                    break;
                case 2:
                    System.out.println("Old session's group: " + session.getGroup());
                    System.out.println("Enter new session's group : (SEP1/CE3/SEA2)");
                    String group = sc.nextLine();
                    session.setGroup(group);
                    success = true;
                    break;
                case 3:
                    System.out.println("Old session's timing: " + session.getDayTime());
                    System.out.println("Enter new session's timing: (Mon 15:00 - 17:00/ Fri 09:00 - 11:00)");
                    String dayTime = sc.nextLine();
                    session.setDayTime(dayTime);
                    success = true;
                    break;
                case 4:
                    System.out.println("Old session's location: " + session.getLocation());
                    System.out.println("Enter new session's location: (LT19a/TRx44/SWLAB3)");
                    String location = sc.nextLine();
                    session.setLocation(location);
                    success = true;
                    break;
                case 5:
                    System.out.println("Old session's tutor name: " + session.getTutorName());
                    System.out.println("Enter new session's tutor name: ");
                    String tutorName = sc.nextLine();
                    session.setTutorName(tutorName);
                    success = true;
                    break;
                case 6:
                    System.out.println("Old session's max capacity: " + session.getMaxCapacity());
                    System.out.println("Enter session's new max capacity: ");
                    int maxCapacity = sc.nextInt();
                    if(session.getNumberRegistered()> maxCapacity){
                        System.out.println("Please enter a valid capacity!");
                        return false;
                    }
                    session.setMaxCapacity(maxCapacity);
                    sc.nextLine();
                    success = true;
                    break;
                default: 
                    System.out.println("Please select a valid field!");
            }
            System.out.println("Modified session: "+ session.toString() + " | Max Capacity: " +session.getMaxCapacity());
            System.out.println("Do you wish to continue modifying the session (Y/N)");
            conti = sc.nextLine().toUpperCase().charAt(0);
        }while(conti == 'Y');
        return success;         
    }
    
    /**
     * A method to print out all {@link Session} under the calling Course object
     * <p> Traverse the ArrayList of Session objects and print out the information needed.
     * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html">ArrayList</a>
     */
    public void printSessions(Course course){
        ArrayList<Course> = course.getAllSession();
        for (int i = 0; i < indexList.size(); i++){
            System.out.println(indexList.get(i) + " | Vacancy: " 
            + indexList.get(i).getVacancy() + "/" + indexList.get(i).getMaxCapacity());
        }
    }

}