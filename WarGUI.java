/*    Jonathan Slimovitch
      CS 110: Intermediate Programming
      Assignment #10
      
      GUI Implementation
*/

import java.awt.*;         //Needed for Swing classes
import javax.swing.*;      //Needed for Color class
import java.awt.event.*;   //Needed for event listening
import javax.swing.JOptionPane; //Needed for JOptionPane

public class WarGUI extends JFrame
{
   
   public JPanel panelCenter, panelNorth, panel1, panel2, panel3, panelWest, panelEast, panel4, panel5, panel6, panel7;
   public JPanel panel8, panel9;
   public JLabel pic1, pic2;
   private ImageIcon ufront, cfront, back;
   public JTextField myRunner;
   private JMenuBar menuBar;
   private JMenu fileMenu;
   private JMenuItem helpItem, exitItem;
   private JButton button, button1;
   private final int WINDOW_WIDTH=900;
   private final int WINDOW_HEIGHT=350;
   public int round=1;
   public War myWar;
   public Card userCard, computerCard;
   public Pile hand1, hand2;
   public Deck myDeck;
   public JLabel messageLabel6;
   
   /**
      Constructor sets up frame size, layout, border, and colors. It also creates panel1-panel7, as 
      well as panelCENTER, panelWEST, and panelEast.
   */
   
   public WarGUI()
   {
      panelNorth=new JPanel();
      menuBar=new JMenuBar();
      helpItem=new JMenuItem("Instructions");
      helpItem.addActionListener(new OptionListener());
      exitItem=new JMenuItem("Exit");
      exitItem.addActionListener(new OptionListener());
      fileMenu=new JMenu("Options");
      fileMenu.add(helpItem);
      fileMenu.add(exitItem);
      menuBar.add(fileMenu);
      panelNorth.add(menuBar);
      setJMenuBar(menuBar);
      setVisible(true);
      add(panelNorth, BorderLayout.NORTH);
      
      //Set window title
      setTitle("Card Game: WAR");
                    
      //Set size of window
      setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
      
      //Set window layout
      setLayout(new BorderLayout());
      
      //Create panelCenter
      panelCenter=new JPanel(new GridLayout(1,2));
      add(panelCenter, BorderLayout.CENTER);
      
      //Create panelWest
      panelWest=new JPanel(new GridLayout(4,1));
      panelWest.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Actions: "),BorderFactory.createLineBorder(Color.BLACK,2)));
      add(panelWest,BorderLayout.WEST);
      
      //Create panelEast
      panelEast=new JPanel(new GridLayout(2,1));
      panelEast.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Player Totals: "),BorderFactory.createLineBorder(Color.BLACK,2)));
      panelEast.revalidate();
      add(panelEast,BorderLayout.EAST);
      
      //Create panel1
      panel1=new JPanel();
      JLabel messageLabel=new JLabel("User");
      //Add the label components to the panel
      panel1.add(messageLabel);
      //Specify what happens when close button is clicked
      panel1.setBackground(Color.ORANGE);      
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      //Set picture icons
      ufront=new ImageIcon("back.jpg");
      back=new ImageIcon("back.jpg");
      pic1=new JLabel(back);
      //Add panel1 to panelCenter
      panelCenter.add(panel1);
      //Display the window
      setVisible(true);
      
      //Create panel2
      panel2=new JPanel();
      JLabel messageLabel2=new JLabel("Computer");
      //Add the label components to the panel
      panel2.add(messageLabel2);
      panel2.setBackground(Color.GREEN);
      //Specify what happens when close button is clicked
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      //Set picture icons
      pic2=new JLabel(back);
      //Add panel2 to panelCenter
      panelCenter.add(panel2);
      //Display the window
      setVisible(true);
      
      //Create panel3
      panel3=new JPanel();
      JLabel messageLabel3=new JLabel("User Total");
      //Add the label components to the panel
      panel3.add(messageLabel3);
      panel3.setBackground(Color.CYAN);
      //Specify what happens when close button is clicked
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      //Add panel3 to panelEast
      panelEast.add(panel3);
      //Display the window
      setVisible(true);
      
      //Create panel4
      panel4=new JPanel();
      JLabel messageLabel5=new JLabel("Computer Total");
      //Add the label components to the panel
      panel4.add(messageLabel5);
      panel4.setBackground(Color.CYAN);
      //Specify what happens when close button is clicked
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      //Add panel4 to panelEast
      panelEast.add(panel4);
      //Display the window
      setVisible(true);

      //Create panel6
      panel6=new JPanel();
      //Create button
      button=new JButton("DRAW!");
      button.addActionListener(new ButtonListener());
      panel6.add(button);
      panel6.setBackground(Color.CYAN);
      //Add panel6 to panelWest
      panelWest.add(panel6);
      panelWest.revalidate();
      
      //Create panel7
      panel7=new JPanel();
      //Add panel7 to panelWest
      button1=new JButton("New Game");
      button1.addActionListener(new ButtonListener());
      panel7.add(button1);
      panel7.setBackground(Color.CYAN);
      panelWest.add(panel7);
      panelWest.revalidate();

      panel8=new JPanel();
      //Add panel7 to panelWest
      panel8.setBackground(Color.CYAN);
      panelWest.add(panel8);
      panelWest.revalidate();
      
      panel9=new JPanel();
      //Add panel7 to panelWest
      panel9.setBackground(Color.CYAN);
      panelWest.add(panel9);
      panelWest.revalidate();

      newGame();
   }
   

   /**
      newGame() creates a new deck, randomly deals the cards between two groups, and creates a new instance of
      the War class. It also updates panel5 to show a welcome message, and sets the card images to the back of card.
   */
   
   public void newGame()
   {
   
      //Create a deck
      myDeck=new Deck();
      
      //Deal deck randomly in between two groups
      myDeck.randomDeal();
      
      //Assign each group to a variable of type Pile
      hand1=new Pile(myDeck.getList1());
      hand2=new Pile(myDeck.getList2());
      
      //Shuffle each hand
      hand1.shuffle();
      hand2.shuffle();
      
      //Create an instance of War class titled myWar using hand1 and hand2 as arguments
      myWar=new War(hand1,hand2);
      
      userTotal(hand1.myPile.size());
            
      computerTotal(hand2.myPile.size());
            
      round=1;
      
      //Create panel5
      panel5=new JPanel();
      JLabel messageLabel6=new JLabel("New Game Started...Click Draw to Flip First Card!");
      //Add the label components to the panel
      panel5.add(messageLabel6);
      //Specify what happens when close button is clicked
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      panel5.setBackground(Color.CYAN);
      //Add panel5 to frame
      add(panel5, BorderLayout.SOUTH);
      //Display the window
      setVisible(true);
      
      
      pic1.setIcon(back);
      panel1.add(pic1);
      pic2.setIcon(back);
      panel2.add(pic2);
      panelCenter.revalidate();

   }
   
   /**
      Flips User Picture (from back to front or vice versa)
      @param myString String address of Card
   */
   public void FlipPanelUser(String myString)
   {      
           
      panel1.removeAll();
      JLabel messageLabel=new JLabel("User");
      //Add the label components to the panel
      panel1.add(messageLabel);
      //Specify what happens when close button is clicked
      pic1.setIcon(null);                  
      
      ufront=new ImageIcon(myString+".jpg");
      back=new ImageIcon("back.jpg");
      pic1=new JLabel(back);
      pic1.setIcon(back);
      panel1.add(pic1);

   }
   
   /**
      Flips Computer Picture (from back to front or vice versa)
      @param myString String address of Card
   */
   
   public void FlipPanelComputer(String myString)
   {      
      panel2.removeAll();     
      JLabel messageLabel2=new JLabel("Computer");
      //Add the label components to the panel
      panel2.add(messageLabel2);
          
      pic2.setIcon(null);
      cfront=new ImageIcon(myString+".jpg");
      back=new ImageIcon("back.jpg");
      pic2=new JLabel(back);
      pic2.setIcon(back);
      panel2.add(pic2);
      panel2.revalidate();
   }
   
   /**
      ButtonListener implementsActionListener. If the source is from the Draw button, it causes a new card to flip.
      The cards are compared, and it updates who won the round, card totals, and round number.
      If the source is from the New Game button, it causes a new Game to start.
   */
   
   class ButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent d)
      {
         String actionCommand=d.getActionCommand();
         if(actionCommand.equals("DRAW!"))
         {
            myWar.flip();
            if (myWar.empty.equals("Yes"))
            {
               if (hand1.myPile.size()==0)
               {
                  JOptionPane.showMessageDialog(null,"The Computer Won!");
                  System.exit(0);
               }
               else if (hand2.myPile.size()==0)
               {
                  JOptionPane.showMessageDialog(null,"Congratulations! You Won!");
                  System.exit(0);
               }   
            }
            else
            {
               myWar.compare();
               
               Card userCard=myWar.returnUserCard();
               FlipPanelUser(userCard.toString());
               
               //Creates a card computerCard from War class's method returnComputerCard
               Card computerCard=myWar.returnComputerCard();
               
               //Passes computerCard into frame's method FlipPanelComputer
               FlipPanelComputer(computerCard.toString());
               
               pic1.setIcon(ufront);
               pic2.setIcon(cfront);
               //Set newUserTotal in frame
                  
               userTotal(hand1.myPile.size());
               
               computerTotal(hand2.myPile.size());
                  
               bottom(myWar.compareValue);
            }
         }
         else if (actionCommand.equals("New Game"))
         {
            newGame();
         }
      }
   }
   
   /**
      This class responds to the event when the Instructions or Exit buttons are clicked.
      If the Instruction button is clicked, it creates a new tab with a list of instructions.
      If Exit is clicked, it exits the GUI.
   */
   
   class OptionListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         String actionCommand=e.getActionCommand();
         if(actionCommand.equals("Instructions"))
         {
            JFrame newFrame=new JFrame();
            JPanel newPanel=new JPanel();
            JLabel label1=new JLabel("Welcome to the Card Game War");
            JLabel label2=new JLabel("The goal of the game is to beat the computer by adding all of the computer's cards to your deck");
            JLabel label3=new JLabel("When you press DRAW! a card is flipped from your deck and from the computer's deck");
            JLabel label4=new JLabel("If your card is greater in rank than the computer's card, you add both cards to your deck");
            JLabel label5=new JLabel("If your card is less in rank than the computer's card, the computer adds both cards to its deck");
            JLabel label6=new JLabel("The total number of cards should always be 52.");
            JLabel label7=new JLabel("To start a new game, press New Game.");
            JLabel label8=new JLabel("Have Fun!");
            
            newPanel.add(label1);
            newPanel.add(label2);
            newPanel.add(label3);
            newPanel.add(label4);
            newPanel.add(label5);
            newPanel.add(label5);
            newPanel.add(label6);
            newPanel.add(label7);
            newPanel.add(label8);
            newPanel.revalidate();
            newFrame.add(newPanel);  
            newFrame.setSize(600,230);
		    
            newFrame.setVisible(true);
         }
         
         else if(actionCommand.equals("Exit"))
         {
           System.exit(0);
         }
      }
   }
         
               
   
   
   /**
      Method updates panel3 to include the total number of cards in User's Hand
      @param num The number of cards in User's Hand
   */
   
   public void userTotal(int num)
   {
         
      
      panel3.removeAll();
      JLabel messageLabel4=new JLabel("\nCards in User's Hand: "+num);
      panel3.add(messageLabel4);
   
        
      //Specify what happens when close button is clicked
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      panelEast.add(panel3);
      panelEast.revalidate();
   }
   
   /**
      Method updates panel4 to include the total number of cards in Computer's Hand
      @param num The number of cards in Computer's Hand
   */
   
   public void computerTotal(int num)
   {
      
      panel4.removeAll();
      JLabel messageLabel5=new JLabel("Cards in Computer's Hand: "+num);
      panel4.add(messageLabel5);
      panelEast.add(panel4);
   }
   
   /**
      Method declares whether User1 or User2 won
      @param compareValue The compareValue field from War class (1 if User 1 won, 2 if User 2 won)
        
   */
   
   public void bottom(int compareValue)
   {
      String s="";
      panel5.removeAll();
      panel5.updateUI();
      if(compareValue==1)
      {
         s="User won!";
      }
      else if (compareValue==2)
      {
         s="Computer won!";
      }
      
      System.out.println("Round Number: "+round);
      JLabel messageLabel6=new JLabel(s+"                     Round Number: "+round);
      round+=1;
      panel5.add(messageLabel6);
      panel5.revalidate();
      add(panel5, BorderLayout.SOUTH);
   
   }
   
   public static void main(String[] args)
   {
      WarGUI frame=new WarGUI();
   }
  
}
      
   

