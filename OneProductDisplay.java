import java.util.Scanner;
/**
 * Creates an object that represents one product item that could be purchased in a store.
 * This program allows the user to enter details about a product (description, measurement type, 
 * quantity, and price), and calculates and displays the unit price and other information.
 *
 * @author lindytatum
 * @version 1.0
 */

public class OneProductDisplay {
  public static void main(String[] args) {

    // Declare variables to store product information
     String productDescription;
     String productMeasure;
     int productQuantity;
     double productItemPrice;
     double productUnitPrice;
     String productMeasureSingular;
     
    Scanner keyboard = new Scanner(System.in);
    
    // Default constructore to define and instantiate ProductItem
    ProductItem productItem = new ProductItem();      
    
    System.out.println("Program to examine one product in a store");
    System.out.println();

    // Prompt user to enter product details and set the ProductItem's fields
    readProductInfo(keyboard,productItem);
      
    // Retrieve product details from the ProductItem object
     productDescription = productItem.getDescription();
     productMeasure = productItem.getMeasure(); 
     productQuantity = productItem.getQuantityInItem();
     productItemPrice = productItem.getItemPrice();
     productUnitPrice = productItem.calcUnitPrice();
     productMeasureSingular = productItem.generateSingularMeasure();
     
     // Displays product details
     System.out.print(productDescription + " contains " + productQuantity + " " + productMeasure + " of product and costs $");
     System.out.printf("%.2f\n",productItemPrice );
     
     System.out.println();
     System.out.print("Unit price of " + productDescription + " " + "is $");
     System.out.printf("%.4f per ", productUnitPrice);
     System.out.println(productMeasureSingular);
     
 }
 /**
 *readMeasure description- reads and validate the units of measurement from the user
 * in a store
 * 
 * @param keyboard -Scanner to read input from keyboard
 * @return unitsOfMeasure- units of measure string
 */
public static String readMeasure(Scanner keyboard) {
   String inputChoice;

  // Display measurement choices
  System.out.println( "Product measure choices");
  System.out.println( "  O - ounces");
  System.out.println( "  I - inches");
  System.out.println( "  U - units"); 

  // Prompts how to be measured
  System.out.println("How is this product measured?");
  inputChoice = keyboard.next().toUpperCase();

  // Validates the users choice
  while ((!inputChoice.equals("O")) && (!inputChoice.equals("I")) && (!inputChoice.equals("U") )){
    System.out.println("Invalid choice. Must enter O, I, or U.");
    inputChoice = keyboard.next().toUpperCase();
}   

  // Returns the choice to a word
   switch (inputChoice) {
      case "O":
         return "ounces";
      case "I":
         return "inches";
      default:
         return "units";
   }
}
 /**
 *readProductInfo description- reads user input for all data fields and uses them to 
 * set the data field values.
 * @param keyboard -Scanner to read input from keyboard
 * @param productItem - object that was instantiated using the default constructor
 * 
 */
  public static void readProductInfo(Scanner keyboard, ProductItem productItem) {
  String productInput;
  int ouncesInput;
  double priceInput;
  String measuredInput;
  
    // Prompts user for product description
   System.out.println("Product description?");
      productInput = keyboard.nextLine();
     
    productItem.setDescription(productInput);

    // Prompts user for product measurement
   measuredInput = readMeasure(keyboard);
   productItem.setMeasure(measuredInput);

    // Prompts user for product quality
   System.out.println("Number of " +  measuredInput + " in product?");
      ouncesInput = keyboard.nextInt();
   productItem.setQuantityInItem(ouncesInput);

    // Prompts user for product price
   System.out.println("Product price?");
      priceInput = keyboard.nextDouble();
      productItem.setItemPrice(priceInput);
      System.out.println();
}


    
 
   
   
}
