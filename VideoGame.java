/*
 * CS310 Assignment 2 - Abstract Classes
 */
package cs310datastructures;

import java.util.Scanner;

public class VideoGame extends ShelfItem
{
    private String gameSystem;  // the system the game is played on
    private int timesFinished;  // the number of times the game has been finished
    private boolean gamePlayed; // whether the game has ever been played

  
 // Default Constructor    
    public VideoGame (){    
     //  super("");
       this.gameSystem ="";
       this.timesFinished = 0;
       this.gamePlayed = false;
}
  /**
     * Constructor that sets the data member values to the argument values passed into the constructor  
     *
     * @param gameTitle empty string 
     * @param gameSystem empty string 
     * @param timesFinished 
     * @param gamePlayed 
     * 
     */
    public VideoGame (String gameTitle,  String gameSystem, int timesFinished, boolean gamePlayed){
      super(gameTitle);
      this.gameSystem =gameSystem;
      this.timesFinished = timesFinished;
      this.gamePlayed = gamePlayed;
       
    }
    
   /**
     * Gets the title of the book.
     *
     * @return a string reference to the book title
     */
    public String getGameTitle()
    {
        return super.getItemName();
    }
     
    /**
     * Sets the game title.
     */
    public void  setGameTitle(String gameTitle){
    
        super.setItemName(gameTitle);
    }
    /**
     * Gets the system of the game.
     *
     * @return a string reference to the game system
     */
    public String getGameSystem()
    {
        return super.getItemName();
    }
     
    /**
     * Sets the game system.
     */
    public void setGameSystem(String gameSystem){
         this.gameSystem= gameSystem;
    }
   
    /**
     * Gets the times finished.
     *
     * @return a int reference to times finished
     */
    public int getTimesFinished()
    {
        return timesFinished;
    }
    
    /**
     * Sets the times finished.
     */
    public void setTimesFinished(int timesFinished){
         this.timesFinished= timesFinished;
    }
    
    /**
     * Gets the games played.
     *
     * @return games played
     */
    public boolean isGamePlayed()
    {
        return gamePlayed;
    }
    /**
     * Sets the game system.
     */
    public void setGamePlayed(boolean gamePlayed){
         this.gamePlayed= gamePlayed;
    }
   
    // Increas the times a game has been finished by one 
    public void increaseTimesFinished(){
       timesFinished++;
       }
    // Increas the times a game has been finished by one 
    public void decreaseTimesFinished(){
       if (timesFinished > 0){
          timesFinished--;
       }
    }
    /**
     * Implements abstract methods from ShelfItem
     */
    @Override
    public  void enterItem (Scanner input){
       enterGameTitle (input);
       enterGameSystem (input);
       enterTimesFinished(input);
       enterGamePlayed(input);
       System.out.println();
       
    }
    /**
     * Implements abstract methods from ShelfItem
     */
    @Override 
    public void displayItem(){
       System.out.println("** Video Game Information **");
       System.out.println("Title: " + getGameTitle());
       System.out.println("System: " + gameSystem);
       System.out.println("Times Finished: " + timesFinished);
       System.out.println("Game Played: " + (gamePlayed ?  "Yes" : "No"));
       System.out.println();
    }
     /**
     * Implements method for game title
     * @param input reads the input game title.
     */
    private void enterGameTitle(Scanner input){
       String titleOfGame;
       boolean validTitle = false;
       while (!validTitle){
          System.out.print("Enter the game title: ");
          titleOfGame = input.nextLine();
          
          if (titleOfGame.isEmpty()){
             System.out.println("ERROR: Game title cannot be empty!");
          }
          else { 
             super.setItemName(titleOfGame);
             validTitle = true;      
          }
       }
       
    }
    /**
     * Implements method for game system
     * @param input reads the input game system.
     */
    private void enterGameSystem(Scanner input){
       String readSystem;
       boolean gameSystem = false;
       while (!gameSystem){
          System.out.print("Enter the game system: ");
          readSystem = input.nextLine();
          
          if (readSystem.isEmpty()){
             System.out.println("ERROR: Game system cannot be empty!");
          }
          else { 
             this.gameSystem = readSystem;
             gameSystem = true;      
          }
       }
       
    }
    /**
     * Implements method for number of games times finished 
     * @param input reads the input times finished a game.
     */
    private void enterTimesFinished(Scanner input){
       String inputTimes;
       boolean gameFinished = false;
       while (!gameFinished){
          System.out.print("Enter number of times finished: ");
          inputTimes = input.nextLine();
          
          
          if (ShelfItem.isPositiveInteger(inputTimes)){
             this.timesFinished = Integer.parseInt(inputTimes);
             gameFinished  = true;
          }
          else{
             System.out.println("ERROR: Number of times finished must be a positive integer value!");
          }
         
       }
       
    }
    
    /**
     * Implements method for if game has been played with 
     * @param input reads the input times finished a game.
     */
    private void enterGamePlayed(Scanner input){
       String gamePlayedInput;
       boolean gameFinished = false;
       char firstChar;
       while (!gameFinished){
          System.out.print("Has the game been played (y/n): ");
          gamePlayedInput = input.nextLine();
          
          if (!gamePlayedInput.isEmpty()){
              firstChar = gamePlayedInput.charAt(0);
              this.gamePlayed = firstChar == 'y' || firstChar == 'Y';
              gameFinished = true;
          }
            else {
             System.out.println("ERROR: Wether the game has been played cannot be empty!");
          }
          
       }
    }
     /**
     * Data values placed in the string 
     */
   
   @Override
    public String toString() {
    return "GAME: "+ getGameTitle() + " for " + gameSystem;
            
}
}
      
