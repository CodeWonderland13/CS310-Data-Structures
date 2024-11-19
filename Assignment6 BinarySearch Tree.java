/*
 * CS310 Assignment 11 - Java TreeSet
 */
package cs310datastructures;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
class is designed to assist in solving and validating Sudoku puzzles by providing various utility 
 * methods for analyzing the current state of the Sudoku board. 
 * It operates on a SudokuBoard object and provides methods to retrieve specific sets of filled values 
 * in rows, columns, and mini-grids of the board. 
 *
 * @author Jeffrey LaMarche
 * 
 * @version 1.0 2020-Oct-11 
 * Template Version
 * 
 * @author Selinde Tatum
 * @version 2.0 2024-April-28
 * Student Version
 */
public final class SudokuBoard
{

    /*
    Stores the values of the Sudoku board game
    */
    private Integer[][] board;      
    
    /*
    The size of one board side - the N value
    */
    private int boardSize;          

    /**
     * Constructor that attempts to open file and then read the value for the 
     * puzzle and put them into 2D array
     * @param filename
     * @throws FileNotFoundException 
     */
    public SudokuBoard(String filename) throws FileNotFoundException
    {
        
      //Create a new File object with the filename passed in
       File file = new File (filename);
      //Try creating a Scanner object to read from the file
       try (Scanner scanner = new Scanner(file)){
      
         if (!scanner.hasNextLine()){
            throw new IllegalArgumentException(" was not found or could not be opened\n!"
                  + "SudokuBoard: File input/foobar.txt");
            }
        //Try reading the first line of the file and integer parse it
        try{
           boardSize = Integer.parseInt(scanner.nextLine());
           
         }catch (NumberFormatException e){
            throw new IllegalArgumentException("SudokuBoard: Board size " + boardSize +
                  " is not an integer!");
         }
        // If the board size is not a perfect square
        if (!isPerfectSquare(boardSize)){
           throw new IllegalArgumentException("SudokuBoard: Board size " +  boardSize + 
                 " is not a perfect square number!");
        }
        //Initialize the 2D array with the correct board size dimensions
        board = new Integer[boardSize][boardSize];
        
        int rowCount = 0;
        int lineCount = 1;
        
        //Loop while the file has more lines to read
        while (scanner.hasNextLine()){
           lineCount++;
           String line;
            line = scanner.nextLine();
            String[] values = line.split(" ");
            
            //If the split line length is greater than the board size
            if (values.length > boardSize){
               throw new IllegalArgumentException("SudokuBoard: Line " +  lineCount + "length of " +
                     boardSize + " is incorrect!");
            }
            // Loop over the split line array
            for (int col = 0; col<values.length; col++){
               try {
                  int cellValue = Integer.parseInt(values[col]);
                  //Try setCell method with the row, column, and value
                  setCell(rowCount, col, cellValue);
               }catch (NumberFormatException e){
                  throw new NumberFormatException("SudokuBoard: Board cell (" + rowCount + ", " +
                        col + ") value of " + values[col] + " is not an integer!");
               }catch (IndexOutOfBoundsException e){
                  throw new IllegalArgumentException("SudokuBoard: Invalid row or column index!");
               }
            }
           rowCount++;
        }
        if (rowCount != boardSize){
           throw new IllegalArgumentException("SudokuBoard: " + boardSize 
                 + " lines in file is incorrect!");
        }
        
       }catch (FileNotFoundException e){
          throw new FileNotFoundException("SudokuBoard: File input/" + filename +
                " was not found or could not be opened!");
       }

    }
    
    /**
     * gets the size of the Sudoku board puzzle
     * @return board puzzle
     */
    public int getBoardSize()
    {
        
        return boardSize;
    }

    /**
     * Gets the integer cell value at the row and column location 
     * @param row
     * @param col
     * @return integer value 
     */
    public Integer getCell(int row, int col)
    {
        if (row < 0 || row >= boardSize){
           throw new IndexOutOfBoundsException("SudokuBoard.getCell: Row " + row + " is out of bounds!");
        }
        if (col < 0 || col >= boardSize){
           throw new IndexOutOfBoundsException("SudokuBoard.getCell: Column " + 
                 col + " is out of bounds!");
        }
        return board[row][col];
    }

    /**
     * Sets integer value cell in the 2D array
     * @param row
     * @param col
     * @param value 
     */
    public void setCell(int row, int col, Integer value)
    {
       if (row < 0 || row >= boardSize){
           throw new IndexOutOfBoundsException("SudokuBoard.setCell: Row " + row + " is out of bounds!");
        }
        if (col < 0 || col >= boardSize){
           throw new IndexOutOfBoundsException("SudokuBoard.setCell: Column " + 
                 col + " is out of bounds!");
        }
        if (value == null){
           throw new IllegalArgumentException("SudokuBoard.setCell: Cell value is null!");
        }
        board[row][col] = value;
    }

    /**
     * Creates a string representation of the SudokuBoard object
     * @return 
     */
    @Override
    public String toString()
    {
       StringBuilder sb = new StringBuilder();
       String horizontalLine = "-".repeat(boardSize * 4 + 1) + "\n";
       sb.append("  ");
       for (int i = 0; i<boardSize; i++){
          sb.append(" ").append(i).append("  ");
       }
       sb.append("\n").append(horizontalLine);
       
       for(int row = 0; row < boardSize; row++){
          sb.append(row).append("|");
          
          for(int col = 0; col < boardSize; col++){
          sb.append(" ").append(board[row][col]).append(" ");
          }
          sb.append("\n");
       }
       sb.append(horizontalLine);
        return sb.toString();
    }

   private boolean isPerfectSquare(int boardSize) {
      int sqrt = (int) Math.sqrt(boardSize);
      return sqrt *sqrt == boardSize;
   }

}
