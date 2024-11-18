import java.util.Scanner;
/**
 *Program Description - Creates an object that represents one product item that could be purchased
 * in a store
 * 
 * @author lindytatum
 * @version 1.0
 */

public class OneProductDisplay {
  public static void main(String[] args) {
     String productDescription;
     String productMeasure;
     int productQuantity;
     double productItemPrice;
     double productUnitPrice;
     String productMeasureSingular;
     
    Scanner keyboard = new Scanner(System.in); 
    ProductItem productItem = new ProductItem();      //Default constructore to define and instantiate ProductItem
    
    System.out.println("Program to examine one product in a store");
    System.out.println();
    readProductInfo(keyboard,productItem);
      
 
     productDescription = productItem.getDescription();
     productMeasure = productItem.getMeasure(); 
     productQuantity = productItem.getQuantityInItem();
     productItemPrice = productItem.getItemPrice();
     productUnitPrice = productItem.calcUnitPrice();
     productMeasureSingular = productItem.generateSingularMeasure();
     
     
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
   
  System.out.println( "Product measure choices");
  System.out.println( "  O - ounces");
  System.out.println( "  I - inches");
  System.out.println( "  U - units"); 
 
  System.out.println("How is this product measured?");
  inputChoice = keyboard.next().toUpperCase();
  
  while ((!inputChoice.equals("O")) && (!inputChoice.equals("I")) && (!inputChoice.equals("U") )){
    System.out.println("Invalid choice. Must enter O, I, or U.");
    inputChoice = keyboard.next().toUpperCase();
}   
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
  
  
   System.out.println("Product description?");
      productInput = keyboard.nextLine();
     
   productItem.setDescription(productInput);
    
   measuredInput = readMeasure(keyboard);
   productItem.setMeasure(measuredInput);
   System.out.println("Number of " +  measuredInput + " in product?");
      ouncesInput = keyboard.nextInt();
   productItem.setQuantityInItem(ouncesInput);
      
   System.out.println("Product price?");
      priceInput = keyboard.nextDouble();
      productItem.setItemPrice(priceInput);
      System.out.println();
}


    
 
   
   
}
