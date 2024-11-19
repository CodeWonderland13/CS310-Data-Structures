/*
 * CS310 Assignment 3 - Arrays as Lists
 * This class represents a shelving system with the ability to add, remove and display items
 * @author Lindy Tatum
 * @version 1.0
 */
package cs310datastructures;

public class ShelvingSystem implements ShelvingOperations 
{

    /*
    The size of a shelf. 
     */
    private static final int SHELF_SIZE = 5;

    private ShelfItem[] shelves;
    private int numberOfShelfItems;

    // Constructor that ensures all data member values are intialized
    public ShelvingSystem (){
    
    shelves = new ShelfItem [SHELF_SIZE];
    numberOfShelfItems = 0;
    }
    
    /** Gets the current value of the number of shelf items 
     * 
     * @return numberOfShelfItems
     */
    
    public int getNumberOfShelfItems(){
       return numberOfShelfItems;
    }
    
    /**
    * Add a new item after the current items already on the shelf
    * @param item will be used to be added 
    * @return wasAddSuccessful boolean will represent true or false
    */
    
    @Override
   public boolean addItem(ShelfItem item)
   {
      boolean okToAddItem = true;
      if (numberOfShelfItems == shelves.length){
         if (!expandShelves()){
         return false;
         }
      }
      shelves[numberOfShelfItems] = item;
      numberOfShelfItems++;
      return true;
      }
   
      /**
    * Shifts conetents of the array to make room for the new entry and then adds
    * the item at the specific position 
    * @param item added or not added
    * @param position item is added to this 
    */
   
    @Override
    public boolean addItem(ShelfItem item, int position)
    {
      boolean expansionSuccesful;
         // If position is valid 
         if (position < 0 || position >= shelves.length){
            return false;
         }
         
         // No room for item 
         if (numberOfShelfItems == shelves.length){
         // Expand array 
          expansionSuccesful = expandShelves();
         if (!expansionSuccesful){
          return false;
         }
         }
         // If there is room to add new item 
         if (numberOfShelfItems < shelves.length){
         boolean shiftSuccessful = shiftContentsRight(position);
         if (shiftSuccessful){
            shelves [position] = item;
            numberOfShelfItems++;
            return true;
         }
         }
      return false;
    }  
     /**
    * Allows user to remove an item based on the string name provided 
    * @param itemName
    * @return this
    */
    @Override
    public ShelfItem removeItem(String itemName)
      {
         for (int i =0; i < numberOfShelfItems; i++){
            if (shelves[i].getItemName().equals(itemName)){
               ShelfItem removedItem = shelves[i];
               shelves[i] = shelves [numberOfShelfItems -1];
               shelves [numberOfShelfItems -1] = null;
               numberOfShelfItems--;
               return removedItem;
               
            }
         }
      return null;
}
    /**
    * Attempt to find an item based on the string name provided
    * @param itemName
    * @return this
    */
    @Override
    
       public ShelfItem findItem(String itemName){
       for (int i = 0; i < numberOfShelfItems; i++){
          if (shelves[i].getItemName().equals (itemName)){
             ShelfItem foundItem = shelves[i];
             return foundItem;
          }
       }
          return null;   
       }
       /**
    * Display the current state of the ShelvingSystem 
    * @param itemName
    * @return this
    */
    @Override
    public void displayShelves(){
      System.out.println("** Displaying All Shelf Contents **");
      
      // Iterate through each shelf
      for (int shelfNumber = 0; shelfNumber < shelves.length;shelfNumber++){
         System.out.printf("     Shelf %d Contents:%n", shelfNumber + 1);
         
      // Iterate through each item 
      for (int itemNumber = 0; itemNumber < SHELF_SIZE; itemNumber++){
         int index = shelfNumber * SHELF_SIZE + itemNumber; 
         System.out.printf("        %d. %s%n", itemNumber + 1, getItemDescription(index));
      }
      }
      System.out.println();
    }
    /** Method that will get the item description and indicate if space is empty
    * @param index
    * @return shelves
    */
    private String getItemDescription (int index){
       if (index < numberOfShelfItems){
         return shelves[index].toString();
       }
       else{
          return "Is Empty";
       }
    }
    /** Expand the shelves array capacity by five additional spaces 
    * 
    * @return true or false 
    */
    private boolean expandShelves(){
     // Create tempoaory array
     ShelfItem[] newShelves = new ShelfItem[shelves.length + SHELF_SIZE];
     
     // Use loop to copy over the items in the same order form the shelves
     for (int i = 0; i < shelves.length; i++){
        newShelves[i] = shelves[i];
     }
     //Assign tempoary array reference
     shelves = newShelves;
     
     return true;
    }
    /** Shifts the array contents to the right or down from a specfic position
    * @param position
    * @return true or false
    */
    private boolean shiftContentsRight (int position){
       // If current shelves arent full
       if (numberOfShelfItems < shelves.length){
       
       // Decrement until position is reached, copy value from index    
          for (int i = numberOfShelfItems - 1; i >= position; i--){
             shelves[i +1] = shelves [i];
          }
          // Set position index to null
          shelves [position] = null;
          
          // Return true if successful 
          return true;
       }
       // Return false if array is full 
       return false;
    }
}
