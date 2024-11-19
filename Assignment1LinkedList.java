/* CS310 Assignment 1 - Linked List
/**
 * Program to manage a list of course sections for one department
 * within a university.
 * Ordered course data for the department will be read from a text file.
 * The user will be able to display the course list, add course sections, 
 * update enrollment in a course section, find courses with lowest enrollments, 
 * determine how many open seats are available in the department for 
 * registration, and re-save the course data to a new file.
 
 * @SelindeTatum
 * @version 1.0
 */

import java.io.IOException;
import java.util.Scanner;

public class ManageDeptCourses {

   /**
    * Creates a course list data structure for one department, 
    * reads a course list from an input file and manages the course list 
    * via a menu of options, until the user chooses to quit. 
    * 
    * @param args - no command line arguments for this program
    */
   public static void main(String[] args) {
      char choice;
      
      // Instantiate a Scanner object to read from the keyboard
      Scanner keyboard = new Scanner(System.in);
      
      // Instantiate a DeptCourseListImpl object named deptCourses
      // The object will contain an empty course list 
      DeptCourseListImpl deptCourses = new DeptCourseListImpl();
   
      
      // Store the user-entered department into deptCourses
      deptCourses.setDept(keyboard);
      
      // Created filename with format
	String fileName = deptCourses.getDept() + "dept.txt";
      try{
         // Uses deptCourses to call readCourseDataFile() method
         int storedCourseSec = deptCourses.readCourseDataFile(fileName);
        
         // Displays course section number stored
         System.out.println("Data for " + storedCourseSec + " course sections stored");
         
         // Loop calls displayMenu until user quits 
                
         do {
           choice = displayMenu(keyboard);
           switch (choice){
              
              // Displays course list
              case 'D':
                 deptCourses. displayCourseList();
                 break;
                 
              // Adds course section to list
              case 'A':
               String addCourseNum;
               String addSection;
               int addCapacity;
               addCourseNum =   readCourseNum(keyboard, "add");
               
               //Reads section
               System.out.println("Enter section of " + addCourseNum + " to add:");
               addSection = keyboard.next().toUpperCase();
               
               // Reads capacity
               System.out.println("Enter capacity of section:");
               addCapacity = keyboard.nextInt();
               deptCourses.addCourseOffering(addCourseNum,  addSection,  addCapacity);
               break;
                 
              // Enrollment change for one course section 
              case 'E':
                int addEnrollment;
              //Call reader to read course number from user
                String addCourseNumber = readCourseNum(keyboard, "change enrollment for");
              
              // Read section and new enrollment for user 
                 System.out.println("Enter section of " + addCourseNumber + " to change enrollment for:");
                 addSection = keyboard.next().toUpperCase();
                 System.out.println("Enter new enrollment:");
                 addEnrollment = keyboard.nextInt();
                  
               // Call chaneEnrollment() method 
                 deptCourses.changeEnrollment( addCourseNumber,  addSection, addEnrollment);
                 break;
                 
              // Number of open seats remaining
              case 'N':
                 int totalSeats;
                 String departmentName;
                 System.out.println();
                 
               // Calling calcTotalOpenSeats()
                 totalSeats =  deptCourses.calcTotalOpenSeats();
                 departmentName = deptCourses.getDept();
                 
               //Display results including deparment message
                System.out.println(totalSeats +  " total remaining open seats in all " + departmentName + " course sections");
                break;
                
              // Lowest displayed enrollement course sections
              case 'L':
                deptCourses.showLowestEnrollmentCourses();
                 break;
                 
              // Updated course data file 
              case 'U':  
                 //File name created 
                 String outputFile = deptCourses.getDept() + "deptV2.txt";
                 
                 //Call method
                 int writeCourse = deptCourses.writeUpdatedFile(outputFile);
                 
                 //File creaton is successful 
                 if (writeCourse != -1){
                    System.out.println(writeCourse + " course sections saved to file " + outputFile);
                 }
                 break;
                 
              // Quit program 
              case 'Q':
                 break;
           }
            
         }while (choice != 'Q');
         
         // Error message 
      }catch (IOException e){
         System.out.println("**Error reading input file " + fileName);
         System.out.println("**Program exiting");
      }
   }
   
   /**
    * Method displays menu to user and then
    * reads, uppercased, validates, and returns user choice from a menu
    * 
    * @param scanner
    * @return validUpperChar - Returns the user's valid uppercased choice character.
    */
   public static char displayMenu (Scanner scanner) {
    char charChoice;
   // Loop for menu of options
      do{ 
        System.out.println();
        System.out.println("MENU");
        System.out.println("  D = Display course list");
        System.out.println("  A = Add a course section to list");
        System.out.println("  E = Enrollment change for one course section");
        System.out.println("  N = Number of open seats remaining");
        System.out.println("  L = Lowest enrollment course sections displayed");
        System.out.println("  U = Update course data file");
        System.out.println("  Q = Quit program");
        System.out.println("Enter choice:");
        
   // Reads choice in upper case
        charChoice = scanner.next(). toUpperCase().charAt(0);
        if (charChoice != 'D'  && (charChoice != 'A' && charChoice != 'E' && charChoice != 'N'
              && charChoice != 'L' && charChoice != 'U' && charChoice != 'Q')){
             System.out.println("**Error: Invalid menu choice! Try again.");
      }
   // When choice in not valid
        }while (charChoice != 'D' && charChoice != 'A' && charChoice != 'E' && charChoice != 'N'
              && charChoice != 'L' && charChoice != 'U' && charChoice != 'Q');
       
      return charChoice;       
   }
   
   /**
    * Method prompts for a course number, using the operation in the prompt.
    * It then reads, uppercased, validates, and returns a course number
    * 
    * It validates that the course number contains 2-4 letters 
    * followed by 3 digits, and loops until a valid course number is entered.  
    * 
    * @param keyboard - to read user input
    * @param operation - what will be done with the specified course number
    * @return a valid courseNum String with uppercased letters
    */
   public static String readCourseNum (Scanner keyboard, String operation) {
      String courseNum = " ";
      boolean isValid = false;
      
      // Prompts for user to enter course number using operation 
     while (!isValid){
         System.out.println("Enter course number to " + operation + ":");
     
         courseNum = keyboard.next();
         if (courseNum.length() < 5 || courseNum.length() > 6){
            System.out.println("ERROR: Length " + courseNum.length() + " is invalid!");
         }
         
         if (courseNum.length() == 5){
     // String letters = courseNum.substring (0,Math.min(2,courseNum.length()));
            String letters; 
            String digits; 
            letters = courseNum.substring(0, 2);
            digits = courseNum.substring(2);
            
            if (!letters.matches("[A-Za-z]+") || !digits.matches("\\d{3}")){
       
               System.out.println("ERROR: Invalid character in course number!");
            }
            if (courseNum.length() == 5 && letters.matches("[A-Za-z]+") && digits.matches("\\d{3}")){
               isValid = true;
            }
         }
     
      if (courseNum.length() == 6){
      String letters;
      String digits; 
      letters = courseNum.substring(0, 3);
      digits = courseNum.substring(3);
      
       if ( !letters.matches("[A-Za-z]+") || !digits.matches("\\d{3}")){
         System.out.println("ERROR: Invalid character in course number!");
         if (courseNum.length() < 5 || courseNum.length() > 6){
         System.out.println("ERROR: Length " + courseNum.length() + " is invalid!");
      }
       }
      if (courseNum.length() == 6 && letters.matches("[A-Za-z]+") && digits.matches("\\d{3}")){
             isValid = true;
      }
      }
     }
      return courseNum.toUpperCase();       
   }
}
