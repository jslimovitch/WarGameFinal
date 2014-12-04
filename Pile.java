/*    Jonathan Slimovitch
      CS 110: Intermediate Programming
      Assignment #10

      Pile Class:

*/

/**      
   Pile Class: This class has several methods which operate on an ArrayList of Cards. These
   methods include shuffling the cards, returning the top card, adding a card, and checking 
   to see if the array is empty.
*/


//Import
import java.util.ArrayList;
import java.util.Random;

public class Pile extends Deck
{
   public ArrayList<Card> myPile;

   /**
      Constructor creates an empty arrayList called myPile
   */
   
   public Pile()
   {
      super();
      myPile=getList1();
      
   }
   
   /**
      Constructor accepts an arrayList as an argument and sets it to myPile
      @param userList CardList passed into Pile
   */
   
   public Pile(ArrayList<Card> userList)
   {
      myPile=userList;
   }
   
   /**
      dealTopCard method returns next card of pile
   */
   
   /** 
      Randomize the order of Cards in Deck
   */

   public void shuffle()
   {
      int randNum;
      Card temp;
      Random r = new Random();
      for (int i = 0; i < myPile.size(); i++)
      {
         randNum = r.nextInt(myPile.size());
         temp = myPile.get(i);
         myPile.set(i,myPile.get(randNum));
         myPile.set(randNum,temp);
      }      
   }
   
   /**
      dealNextCard method deals card at the front of ArrayList
      @return Top card
   */
   
   public Card dealNextCard()
   {
      Card nextCard= myPile.get(0);
      myPile.remove(0);
      return nextCard;
      
   }
   
   /**
      addCard method adds card to myPile
      @param newCard Card that will be added
   */
   
   public void addCard(Card newCard)
   {
      int size=myPile.size();
      myPile.add(size-1,newCard);
   }
   
   /**
      isEmpty() method checks to see whether myPile is empty
      @return true or false if myPile is empty or false
   */
   
   public boolean isEmpty()
   {
      if (myPile.size()==0)
      {
         return true;
      }
      else
      {
         return false;
      }
   }   
   
   //Tester Program
   public static void main(String[] args)
   {
      Deck myDeck=new Deck();
      myDeck.randomDeal();
      Pile hand1=new Pile(myDeck.getList1());
      hand1.shuffle();
                        //CHECK: This tested that the userList inputted into Pile contained 26 cards
                              
                              // int j = 0;
                        //       for(Card card: hand1.myPile)
                        //       {
                        //          j++;
                        //          System.out.println(card.toString());
                        //       }
                        //       //Check to make sure there are 26
                        //       System.out.println(j);
   }
      
   
   
   
}        