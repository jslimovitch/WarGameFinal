/*    Jonathan Slimovitch
      CS 110: Intermediate Programming
      Assignment #10
 
      Deck Class: This class should have several methods which randomly deal the cards from
      a complete deck between two users, put those cards into arrays, and return those arrays.
*/

//Import Statements
import java.util.ArrayList;
import java.util.Random;

public class Deck
{
   //Initialize/Declare private variables/fields
   public ArrayList<Card> cardList;
   public ArrayList<Card> user1List;
   public ArrayList<Card> user2List;

   
   /**
      Constructor creates a deck of 52 cards (1 of each type of suit)
   */
   
   public Deck()
   {
      cardList=new ArrayList<Card>();
      
      int i;
      int j =0;
      while (j<=3)
      {
         i=2;
         while (i<=14)
         {
            Card newCard=new Card(i,j);
            i++;
            cardList.add(newCard);
         }
         j++;
      }
                              // CHECK: Making sure cards were loaded into cardList
                              // for(Card card: cardList)
                              //         System.out.println(card.toString());
   }
   
   /**
      The randomDeal method deals cards evenly between two users
   */
   
   public void randomDeal()
   {
      //Create two arrayLists for user1 and user2
      user1List=new ArrayList<Card>();
      user2List=new ArrayList<Card>();
      
      //Create an arrayList called checkList
      ArrayList<Integer> checkList=new ArrayList<Integer>();
      
      //Fill checkList with 0's
      for(int i=0;i<=52;i++)
      {
         checkList.add(0);
      }
      
      //Run a while loop to randomly add 26 values to user1list()
      int count=0;      //count keeps track of the number of cards we've added to user1list()
      int i;            //i keeps track of the index in cardList and checkList we are using during this iteration
      while(count<26)
      {
         Random randomNumber=new Random();
         i=randomNumber.nextInt(52);
         
         //Check to see if we've already picked this card. If we haven't picked it, it's value should be 0
         if(checkList.get(i)==0)
         {
            //Read it into user1 array
            user1List.add(cardList.get(i));
            
            //Change corresponding index in checkList to be 1 (a nonzero number)
            checkList.set(i,1);
                        
            //Increment counter
            count++;
         }
         else
         {
            i=randomNumber.nextInt(52);
         }
         
         
      }
      
                           //CHECK: user1List received 26 random cards
                           //int j = 0;
                           // for(Card card: user1List)
                           //       {
                           //          j++;
                           //          System.out.println(card.toString());
                           //       }
                           //Check to make sure there are 26
                           //System.out.println(j);

      
      //Download values from checkList not in user1List into user2List
      int m=0;       //m performs a similar function as i: keeping track of the index. The difference is, this time we 
                     //are iterating through the entire list instead of randomly generating a number between 0 and 52.
      while(m<52)
      {
         if(checkList.get(m)==0)       //If we didn't put the card into user1List
         {
            //Read it into user1 array
            user2List.add(cardList.get(m));
            
            //Set corresponding index m to 0;
            checkList.set(m,1);
                        
            //Increment counter
            m++;
         }
         else                          //If we did put the card into user1List
         {
            m++;
         }
      }
      
        
   }
   
  
   /**
      getList1 method returns user1List
      @return user1List
   */
   
   public ArrayList<Card> getList1()
   {
      return user1List;
   }
   
   /**
      getList2 method returns user2List
      @return user2List
   */
   
   public ArrayList<Card> getList2()
   {
      return user2List;
   }
      
   //Tester Class   
   public static void main(String[] args)
   {
      Deck myDeck=new Deck();
      myDeck.randomDeal();
   }
  
   
}