/*
 * CS310 Assignment 10 - Hash Tables - Chaining
 */
package cs310datastructures;

/**
 * This repository contains an implementation of a hash table with chaining, 
 * as part of the CS310 Data Structures course. 
 * the project demonstrates how hash tables can be used to efficiently store 
 * and retrieve key-value pairs. The implementation includes methods for adding, searching, and 
 * expanding the hash table when necessary. It also handles hash collisions through chaining, 
 * using linked lists to store multiple entries at the same index.
 * 
 * @author Jeffrey LaMarche
 * @version 1.0 2020-09-26 - Template Version
 * 
 * @author Selinde Tatum
 * @version 2.0 2024-04-26 - Student Version
 */
public class WordCountHashTable
{

    /*
    The load factor threshold value for the hash table. When this
    is exceeded, the hash table size will grow.
     */
    private static final double LOAD_FACTOR_THRESHOLD = 1.0;

    /*
    The default size of a new word count hash table array
     */
    private static final int DEFAULT_SIZE = 10;

    /*
    The array used for the hash table containing WordCountEntry objects
     */
    private WordCountNode[] wordCountHashTable;

    /*
    The number of entries currently held in the hash table
     */
    private int numberOfEntries;

    /* 
    * Constructor that initiates the hash table array to be the correct default length
    using the class constant value, also sets number of entries contained in hash table to be zero
    */
    public WordCountHashTable()
    {
      wordCountHashTable =  new WordCountNode [DEFAULT_SIZE];
      numberOfEntries = 0;
    }

    /**
     * Returns number of wordcount node objects stored in hash table 
     * @return 
     */
    public int getNumberOfEntries()
    {
        
        return numberOfEntries;
    }
    /**
     * Computes a hash code value of the characters in the key string 
     * @param key
     * @return null or hashValue
     */
    
    private int hashCodeValue(String key)
    {
        int hashValue = 0;
        if (key == null){
           return 0;
           
        }
        for (int i = 0; i< key.length(); i++){
           hashValue += key.charAt(i);
           
        }
       
        return hashValue;
    }

    /**
     * Computes the compressed hash code value of the hash code. This ensures
     * that the value will fit in the table correctly.
     *
     * @param hashCodeValue the integer hash code value
     *
     * @return the compressed version of the hash code value
     */
    private int compressHashCodeValue(int hashCodeValue)
    {
        int compressedValue = 0;

        compressedValue = hashCodeValue % wordCountHashTable.length;

        return compressedValue;
    }
    
    /**
     * Computes the current load factor of the hash table
     * @return loadFactor
     */
    private double computeLoadFactor()
    {
        double loadFactor;
        loadFactor = numberOfEntries / wordCountHashTable.length;
        return loadFactor;
    }
    /**
     * Adds a new word count entry into the hash table
     * @param key
     * @param value
     * @return 
     */
    public boolean addWordCountEntry(String key, Integer value)
    {
        boolean wasEntryAdded = false;
        // If key is not null 
        if (key != null && value != null){
           // If not located for key 
          if (findWordCountEntry(key) == null){
             double currentLoadFactor = computeLoadFactor();
             //Load factor is greater than or equal to threshold 
             if (currentLoadFactor >= LOAD_FACTOR_THRESHOLD){
                expandHashTable();
             }
             // If its okay to add new item 
             if (computeLoadFactor() < LOAD_FACTOR_THRESHOLD){
                int hashCode = hashCodeValue (key);
                int index = compressHashCodeValue (hashCode);
                
                WordCountEntry newEntry = new WordCountEntry (key,value);
                WordCountNode newNode = new WordCountNode (newEntry);
                
                newNode.setNextNode(wordCountHashTable[index]);
                wordCountHashTable [index] = newNode;
                numberOfEntries ++;
                wasEntryAdded = true;
             }
          }
        }
        
        return wasEntryAdded;
    }

    /**
     * Expands the current hash table array and rehashes existing values
     * @return 
     */
    private boolean expandHashTable()
    {
        boolean wasExpandSuccessful = true;
        try {
           // Creat a new array that is double the length of current array 
           WordCountNode[] newHashTable = new WordCountNode[wordCountHashTable.length * 2];
           
           //Loop over the wordCountHashTable length (over the buckets)
        for (int i = 0; i < wordCountHashTable.length; i ++){
           WordCountNode currentNode = wordCountHashTable [i];
  
         while (currentNode != null){
            
         // Get the entry object from the current node
         WordCountEntry entry = currentNode.getWordCountEntryObj();
         
          // If the entry is not null
          if (entry != null){
             
          //  Call the hashCodeValue method with the entry key value
             int hashCode = hashCodeValue(entry.getKey());
             
          // Manually compute the compressed hash code value based on
          int newIndex = hashCode % newHashTable.length;
          
        //Create a new WordCountNode object with the current entry
        WordCountNode newNode = new WordCountNode (entry);
        
       // Set the new node next link to the front of bigger bucket
        newNode.setNextNode (newHashTable[newIndex]);
        
       // Set the front of bigger bucket to point to the new node
        newHashTable[newIndex] = newNode;
          }
          currentNode = currentNode.getNextNode();
         }
        }
   wordCountHashTable = newHashTable;
        
       } catch (OutOfMemoryError e){
                 System.out.println("ERROR: No Memory Available!");
                 wasExpandSuccessful = false;
                 }

        return wasExpandSuccessful;
    }

    /**
     * Attempts to find the entry associated witha given key
     * @param key
     * @return 
     */
    public WordCountEntry findWordCountEntry(String key)
    {
        WordCountEntry foundEntry = null;

        if (key != null){
           //Get hash code for the key 
           int hashCode = hashCodeValue(key);
           
           //Compute bucket index
           int index = compressHashCodeValue(hashCode);
           
           //Store the bucket value 
           WordCountNode currentNode = wordCountHashTable[index];
           while (currentNode != null && foundEntry == null){
              //Get key at current hash index 
              String currentKey = currentNode.getWordCountEntryObj().getKey();
              
              //If current key equals taget key 
              if (currentKey.equals(key)){
                 foundEntry = currentNode.getWordCountEntryObj();
              }
              currentNode = currentNode.getNextNode();
        }
        }

        return foundEntry;
    }

    /**
     * Attempts to find the value associated with the given key 
     * @param key
     * @return 
     */
    public Integer findWordCountValue(String key)
    {
        Integer foundValue = null;

        WordCountEntry foundEntry = findWordCountEntry(key);
        if (foundEntry!= null){
           foundValue = foundEntry.getValue();
        }

        return foundValue;
    }

    /**
     * Displays contents of the wordOunthashtabele by using override 
     */
    public void displayWordCountHashTable()
    {
      System.out.println(toString());
    }

    
    @Override
    public String toString()
    {
       StringBuilder sb = new StringBuilder();
       //Add the table heading to the string
       sb.append("Displaying Word Count Hash Table:\n");
       
       //Add the number of entries line to the string
       sb.append("   Number Entries: ").append(numberOfEntries).append("\n");
       
       //Add the load factor line to the string
       sb.append("  Load Factor: ").append(String.format("%.6f", computeLoadFactor())).append("\n");
       
       //Add the dashed line to the string
       sb.append("------------------------------------------\n");
       
       //If the number of entries is greater than zero
       if (numberOfEntries > 0){
          for (int i=0; i< wordCountHashTable.length; i++){
             WordCountNode currentNode = wordCountHashTable[i];
             //Store the bucket value in a current node reference variable
            //Add the "  Index[X]: " partial line to the string
            sb.append(" . Index[").append(i).append("} = ");
            
            if (currentNode == null){
               sb.append("Is Empty\n");
               
            }else{
               while (currentNode != null){
                  //Add the current entry object toString to the string
                  sb.append(currentNode.getWordCountEntryObj().toString());
                  
                  //Update current to move forward in the list
                  currentNode = currentNode.getNextNode();
                  
                  if (currentNode != null){
                     sb.append("->");
                  }
                        
               }
               sb.append("\n");
            }
          }
       }else{
          sb.append ("    Word Count Hash Table is Empty!\n");
       }
  
        return sb.toString();
    }

}
