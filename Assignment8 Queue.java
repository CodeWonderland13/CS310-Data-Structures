/*
 * CS310 Assignment 8 - Queues and Simulation
 */
package cs310datastructures;

/**
 * A queue structure that holds PlayingCards. The available operations are:
 * <br><br>
 * - Checking if the queue is empty<br>
 * - Getting the number of items in the queue (non-standard)<br>
 * - Enqueue a card (adding it to the queue)<br>
 * - Dequeue a card (removing it from the queue)<br>
 * <br>
 *
 * @author Jeffrey LaMarche
 * @version 0.9 2020-09-11 - Template Version
 * 
 * @author Selinde Tatum
 * @version 1.0 2024-05-11 - Student
 * Version
 */
public class PlayingCardQueue
{

    /*
    The head of the singly linked list
    */
    private PlayingCardNode queueFront;
    
    /*
    The tail of the singly linked list
    */
    private PlayingCardNode queueBack;
    
    /*
    The number of nodes stored in the singly linked list
    */
    private int numberOfCards;

    /*
    *Constructor that makes an empty queue
    */
    public PlayingCardQueue()
    {
        queueFront = null;
        queueBack = null;
        numberOfCards = 0;
    }
    
   /*
    *returns number of cards that are currently contained in the queue
    */
    public int getNumberOfCards()
    {
        return numberOfCards;
    }

    /*
    *Check if the deck is empty
    * @returns true or false
    */
    public boolean isQueueEmpty()
    {
       return numberOfCards == 0;
    }

    /*
    *Removes the first item in the queue and returns it
    * @return true or null
    */
    public PlayingCard dequeueCard()
    {
       if (isQueueEmpty()){
         return null;
       }else{
          //Remove the first item from the Queue
          PlayingCardNode deqeuedNode = queueFront;
          queueFront = queueFront.getNextNode();
          numberOfCards--;
          return deqeuedNode.getCard();
       }
    }
    /*
    *Adds a new non-null PLaying CArd object to the back of the queue
    * @return true or null
    */
    
    public boolean enqueueCard(PlayingCard card)
    {
        if (card!= null){
           PlayingCardNode newNode = new PlayingCardNode(card);
           if(isQueueEmpty()){
              queueFront = newNode;
              queueBack = newNode;
           }else{
              queueBack.setNextNode(newNode);
              queueBack = newNode;
           }
        numberOfCards++;
        return true;
    }else{
           return false;
}
    }
}