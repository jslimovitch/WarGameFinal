/*    Jonathan Slimovitch
      CS 110: Intermediate Programming
      Assignment #10
      
      WarRunner: This program demonstrates the abilities of the War class and the WarGUI class. In other words,
      it uses WarGUI to implement the card game described in War.
*/


//Import Statements
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class WarRunner
{
   
   public static void main(String[] args)
   {
      //Create a deck
      Deck myDeck=new Deck();
      
      //Deal deck randomly in between two groups
      myDeck.randomDeal();
      
      //Assign each group to a variable of type Pile
      Pile hand1=new Pile(myDeck.getList1());
      Pile hand2=new Pile(myDeck.getList2());
      
      //Shuffle each hand
      hand1.shuffle();
      hand2.shuffle();
      
      //Create an instance of War class titled myWar using hand1 and hand2 as arguments
      War myWar=new War(hand1,hand2);
      
      //Initialize several variables, as well as prep the keyboard Scanner
      Scanner keyboard=new Scanner(System.in);
      String runner="y";
      int round=1;
      
      //Create an instance of WarGUI class titled frame      
      WarGUI frame = new WarGUI();
    
      //Set up loop
      while (runner.equals("y")&&hand1.myPile.size()!=0 && hand2.myPile.size()!=0)
      {
         //Ensures frame is current
         frame.revalidate();
         
         //Causes each hand to flip their top card
         myWar.flip();
         
         //Creates a card userCard from War class's method returnUserCard
         Card userCard=myWar.returnUserCard();
         
         //Passes userCard into frame's method FlipPanelUser
         frame.FlipPanelUser(userCard.toString());
         
         //Creates a card computerCard from War class's method returnComputerCard
         Card computerCard=myWar.returnComputerCard();
         
         //Passes computerCard into frame's method FlipPanelComputer
         frame.FlipPanelComputer(computerCard.toString());
         
         //Uses War class's method of compare to compare the two. This will set compareValue
         //to 1 (if User1Card is greater) or 2 (if User2Card is greater).   
         myWar.compare();
         
         //Set newUserTotal in frame
         frame.userTotal(hand1.myPile.size());
         
         //Set newComputerTotal in frame
         frame.computerTotal(hand2.myPile.size());
         
         //Use compareValue to declare who won
         frame.bottom(myWar.compareValue,round);
         
         System.out.println("Round Number: "+round);
         round++;
         
         System.out.println("\nWould you like to flip again? (Enter y/n)");
         runner=keyboard.nextLine(); 
      }
                     //           
                              // runner=JOptionPane.showInputDialog("Do you want to run again? (enter y/n)");
                     //             
                     //             System.out.println(runner);
                           
                              
                              
                              // System.out.println("\nWould you like to flip again? (Enter y/n)");
                     //          runner=keyboard.nextLine(); 
                     //             
                                       // if(frame.getRunner()=="y")
                     //          {
                     //             runner="y";
                     //          }
                                    
                           
                           
                           // int i=1;
                     //       while(i<2)
                     //       {
                     //          if(frame.getRunner()=="y")
                     //          {
                     //             runner="y";
                     //          }
                     //          
                     //       }
      
 
      //If the game is finished
      if (hand1.myPile.size()==0)
         System.out.println("USER 2 WON THE GAME!");
      else if (hand2.myPile.size()==0)
         System.out.println("USER 1 WON THE GAME!");
      
      
      
   }
}