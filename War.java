/*    Jonathan Slimovitch
      CS 110: Intermediate Programming
      Assignment #10
      
      War Class: 
*/

//Import
import java.util.ArrayList;
import java.util.Scanner;

public class War
{
   //Initialize/Declare Variables
   public Pile myPile1;
   public Pile myPile2;
   public Card Pile1Card;
   public Card Pile2Card;
   public int compareValue, compareValueT;
   public int rank1=1;
   public int rank2=0;
   public String empty="No";
         
   /**
      No-arg Constructor: creates two new piles, setting them to myPile1, myPile2 respectively
   */
   
   public War()
   {
      myPile1=new Pile();
      myPile2=new Pile();
   }
   
   /**
      Constructor accepts objects of types Pile, setting them to myPile1, myPile2 respectively
      @param pile1 First Pile argument
      @param pile2 Second Pile argument   
   */
   
   public War(Pile pile1, Pile pile2)
   {
      myPile1=pile1;
      myPile2=pile2;
   }
   
   /**
      Method flip() first checks to make sure each pile isn't empty before dealing first
      card on each pile.
   */
   
   public void flip()
   {
      //Check to make sure piles aren't empty
      if (myPile1.isEmpty()||myPile2.isEmpty())
      {
         //System.out.println("ERROR: Someone's deck is empty");
         empty="Yes";
      }
      //If piles aren't empty
      else
      {
         //Shuffle piles to ensure true randomness
         // myPile1.shuffle();
//          myPile2.shuffle();
//          
         //Deal next Card
         Pile1Card=myPile1.dealNextCard();
         Pile2Card=myPile2.dealNextCard();    
         
         //Print out cards
         //System.out.println(Pile1Card.toString());
         //System.out.println(Pile2Card.toString());
         
      }
      
   }
   
   /**
      returnUserCard method returns Pile1Card
      @return Pile1Card (the user's card)
   */
   
   public Card returnUserCard()
   {
      return Pile1Card;
   }
   
   /**
      returnComputerCard method returns Pile2Card
      @return Pile2Card (the computer's card)
   */
   
   public Card returnComputerCard()
   {
      return Pile2Card;
   }
   
   /**
      returnCompareValue method returns compareValue field
      @return compareValue field (1 or 2)
   */
   
   public int returnCompareValue()
   {
      return compareValue;
   }
   
   /**
      compare() compares Pile1Card and Pile2 Card. If Pile1Card is greater,
      method sets compareValue=1; if Pile2Card is greater, method sets compareValue=2.
      If the two cards are equal, compare() calls tie().
   */
   
   public void compare() //throws NullPointerException
   {
      if(Pile1Card!=null)
      {
         int rank1=Pile1Card.getRank();
         int rank2=Pile2Card.getRank();
         
         if(rank1>rank2)
         {
            myPile1.addCard(Pile1Card);
            myPile1.addCard(Pile2Card);
            //System.out.println("User 1 won!");
            compareValue=1;
            
         }
         
         else if (rank2>rank1)
         {
            myPile2.addCard(Pile1Card);
            myPile2.addCard(Pile2Card);
            //System.out.println("User 2 won!");
            compareValue=2;
         }
         
               
         else 
         {
            tie();
         }
      }
      
      else
      {
         System.out.println("ERROR");
      }

   }
   
   /**
      compare(Card card1, Card card2) is overloaded method. It compares card1 and card2, setting
      compareValue field to 1 or 2 respectively.
      @param card1 First Card 
      @param card2 Second Card
   */
   
   public void compare(Card card1, Card card2)
   {
      Pile1Card=card1;
      Pile2Card=card2;
            
      int rank1=Pile1Card.getRank();
      int rank2=Pile2Card.getRank();
      
      if(rank1>rank2)
      {
         myPile1.addCard(Pile1Card);
         myPile1.addCard(Pile2Card);
         compareValueT=1;
      }
      
      else if (rank2>rank1)
      {
         myPile2.addCard(Pile1Card);
         myPile2.addCard(Pile2Card);
         compareValueT=2;
      }
      
      else 
      {
         tie();
      }
   
   }
   
   /**
      tie() deals the next 2 cards on top of each pile. It ignores the first cards respectively
      and calls the compare(Card1, Card2) method to compare the second cards.
   */
   
   public void tie()
   {
      try
      {
         Card Pile1Card1=myPile1.dealNextCard();
         Card Pile2Card1=myPile2.dealNextCard();
         
         Card Pile1Card2=myPile1.dealNextCard();
         //System.out.println(Pile1Card2.toString());
         Card Pile2Card2=myPile2.dealNextCard();
         //System.out.println(Pile2Card2.toString());
         
         compare(Pile1Card2, Pile2Card2);
      
         if (compareValueT==1)
         {
            myPile1.addCard(Pile1Card);
            myPile1.addCard(Pile2Card);
            myPile1.addCard(Pile1Card1);
            myPile1.addCard(Pile2Card1);
         }
         else if (compareValueT==2)
         {
            myPile2.addCard(Pile1Card);
            myPile2.addCard(Pile2Card);
            myPile2.addCard(Pile1Card1);
            myPile2.addCard(Pile2Card1);
         }

      }
      catch (IndexOutOfBoundsException i)
      {
         System.out.println("ERROR: No cards remaining in deck");
         if(myPile1.isEmpty())
         {
            System.out.println("USER TWO WINS");
            //How to add cards selectively?            
         }
         else
         {
            System.out.println("USER ONE WINS");
         }
      }
         
            
   }
  
   
 

}

//OLD MAIN METHOD
// public static void main(String[] args)
//    {
//       
//       Deck myDeck=new Deck();
//       myDeck.randomDeal();
//       Pile hand1=new Pile(myDeck.getList1());
//       Pile hand2=new Pile(myDeck.getList2());
//       hand1.shuffle();
//       hand2.shuffle();
//       War myWar=new War(hand1,hand2);
//       
//       Scanner keyboard=new Scanner(System.in);
//       String runner="y";
//       int round=1;
//       while (runner.equals("y") && hand1.myPile.size()!=0 && hand2.myPile.size()!=0)
//       {
//          myWar.flip();
//          myWar.compare();
//          System.out.println("\nCards in User 1's Hand: "+hand1.myPile.size());
//          System.out.println("Cards in User 2's Hand: "+hand2.myPile.size());
//          // System.out.println("\nWould you like to flip again? (Enter y/n)");
// //          runner=keyboard.nextLine(); 
//          
//          System.out.println("Round Number: "+round);
//          round++;
//       }
//       if (hand1.myPile.size()==0)
//          System.out.println("USER 2 WON THE GAME!");
//       else if (hand2.myPile.size()==0)
//          System.out.println("USER 1 WON THE GAME!");
//       
//       
//       
//    }

