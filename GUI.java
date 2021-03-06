import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;

public class GUI extends JApplet implements ActionListener, ItemListener
{
    
    private static final int NUM_PLAYERS = 2;      // integer to hold the number 
                                                   //    of players for the monopoly
                                                   //    Game (from 2 to 4 players)
                                                   //    due to GUI constraints
    
    //An array to hold the player names for thi
    private static final String [] PLAYER_NAMES =    // array to hold the name of
                                     {"Christian",   //   all 4 possible players
                                      "Harsh",       //   for the game
                                      "Adolofo",
                                      "Cortellano"};
    
    
    private player [] playerList;       // The list of players
    private player theBank;             // The bank
    private player currPlayer;          // The current player.
    private String [] actions;          // The possible actions.
    private Monopoly theGame;           // The monopoly game;
    private boolean canImprove;         // Flag to enable or disable the improve
                                        //    button.
    private boolean canSell;            // Flag to enable or disable the sell
                                        //    button.
    private boolean gameContinues;      // Flag to determine whether the game continues
                                        //    or not
    private int numberOfLots;           // integer to hold the number of upgradable 
                                        //    lots

    
    private lot[]improvableLots;        // improvableLots holds an array of improvable lots 
    private lot[]sellableLots;          // sellableLats holds an array of sellable lots 
    
    private boolean isNextTurn;         // This boolean represents if
                                        // the user pressed next turn.
    // Harsh
    private boolean isBuyClicked;       // Turns to true when buy is clicked.
    
    private int turnCounter;            // Counter for turn.
    private int diceOne;                // The dice value, 1 < diceOne < 7
    private int diceTwo;                // The dice value, 1 < diceTwo < 7
    // HARSH 
    private int currentLocation;        // The current location
    
    private boardLocation currentLot;   // the current lot
    private boardLocation current;      // The current location.
    private JButton nextTurn;
    private JButton buyLocation;

    //For improving Locations
    private JOptionPane improvePanel;
    //For East side
    private JButton playerProp [];      // playerInfo is an array of buttons
                                        //   to fetch  properties for players 1-4
    private JLabel playerStatus[];      // A label to determine basic player infor
                                        //   like current position, and funds
    private JComboBox allLocations;     // A drop down list that contains all locations
    private JButton getLocation;        // getLocation will prompt the system to
                                        //   fetch the information for a given location

    // Common to all Functions
    int tmpPlayerLocation;              // An Integer to hold where a player's location
                                        //   at a given moment.
    double tmpPlayerFunds;              // An Integer to hold a player's fund on a
                                        //   given instant

    // Related to West Side
    private JButton improveProperty;    // Button to improve property
    private JButton sellHouses;         // Sell houses when you have no money
    private JButton endGame;            // Player can choose to end game

    //SouthCenter
    private JTextArea textArea;
    private JScrollPane scrollPane;
    private String message;
    private String prevMessage;         // Previous message.
    private int []playerOrder;
    private JFrame frame;               //JoptionPane
    private String result;              // Output message displayed in the window
    private JTextArea area;             // Text area to hold the message
    private JScrollPane pane;           // Window pane with scrollbar containing text area
    // Related to Layout and panels
    private JPanel south;
    private JPanel north;
    private JPanel east ;
    private JPanel west;
    private JPanel center;
    private JPanel northCenter;
    private JPanel southCenter;
    private JPanel northRightCenter;
    
    private BorderLayout layout;

    @Override
    public void init()
    {
    	   setSize(600,600);
        JPanel northRightCenter;			//Place where the dice resides
        initializeMonopoly();
        initializeWidgets();
        initializePanels();
        message = "";
        currentLocation = 0;
        
        playerOrder = theGame.getPlayerOrder();
        currPlayer = playerList[playerOrder[0]];
        actions =  theGame.getBoardLocate(currPlayer).getPossibleActions(currPlayer);
        setButtonStatus(theGame.getBoardLocate(playerList[playerOrder[0]]).getActionStatus());

        //Set layout now that panels are set up
        setLayout(layout);

        turnCounter = 0;
        isNextTurn = true;
        canImprove = false;
        gameContinues = true;

        initializePanels();

        //Populate the players with their information.
        for(int i = 0; i < playerList.length; i++)
        {
            tmpPlayerLocation = playerList[playerOrder[i]].getBoardLocation();
            tmpPlayerFunds = playerList[playerOrder[i]].getMoney();
            //Initialize the actual buttons and labels with the expected
            //   information
            playerProp[i] = new JButton("Player " + (i+1) + " Properties.");
            playerStatus[i] = new JLabel("Location: " + tmpPlayerLocation +
                                         "\nFunds: "  + tmpPlayerFunds);
        }

        //southCenter
        textArea = new JTextArea(5,20);
        scrollPane = new JScrollPane(textArea);
        textArea.setEditable(false);
        textArea.setText(" ");
        prevMessage = " ";
        addActionListeners();
        addToPanel();

     }
    public int getDiceVal()
    // POST: A random number between 1 and 6 will be returned.
    {
        return 1 + (int)(Math.random() * (6-1)) + 1;
    }

    public void setButtonStatus(boolean []status)
    {
        for(int i = 0; i < 5; i++){
            if(status[i] == true){
                System.out.println("True");
            }
            else
                System.out.println("False");
        }
        buyLocation.setEnabled(status[1]);
        improveProperty.setEnabled(canImprove);
        sellHouses.setEnabled(status[2]);
        endGame.setEnabled(status[4]);
    }

    public void addItemListeners()
    {
        allLocations.addItemListener(this);
    }


    public void addActionListeners()
    //POST:  Adds the action listerner to all widgets that will perform actions.
    {
        improveProperty.addActionListener(this);
        sellHouses.addActionListener(this);
        buyLocation.addActionListener(this);

        endGame.addActionListener(this);

        for(int i = 0; i < NUM_PLAYERS; i++)
            playerProp[i].addActionListener(this);

        nextTurn.addActionListener(this);
        getLocation.addActionListener(this);
    }
    
    public void setLables()
    // POST: sets the labels each turn to allow the player to see
    //       the funds and the locations of each player.
    {
        int currentPlayer;
        //Populate the players with their information.
        for(int i = 0; i < NUM_PLAYERS; i++)
        {
            currentPlayer = playerOrder[i];
            tmpPlayerLocation = playerList[currentPlayer].getBoardLocation();
            tmpPlayerFunds = Math.floor(playerList[currentPlayer].getMoney());
            
            //Initialize the actual buttons and labels with the expected
            //   information
            System.out.println("Location: " + tmpPlayerLocation);
            System.out.println("Funds: " + tmpPlayerFunds);
            playerProp[i].setText("Player " + (i+1) + " Properties.");
            playerStatus[i].setText("Location: " + tmpPlayerLocation
                                  + " Funds: "  + tmpPlayerFunds);
        }
    }
    
    public void gambleLogic(gamble location, player player)
    // PRE: location and player must be initialized
    {
        JPanel gamblePanel;         // A panel to store all the gamble GUI.
        JTextField gambleAmtTxt;    // A text field that has gamble amount.
        JTextField gambleDiceNum;   // A text field for the next dice value.
        JTextArea gambleArea;       // A text area will give the user a promp
                                    // about the gamble class.
        JLabel gambleInfo;          // A label that tells the user about the class.
        double gambleAmount = 10;   // The money the user wants to gamble.
        double gambleOutcome = 0;   // The result of the gamble operation.
        
        int diceVal = 20;           // The dice value the user wants to bet on.
        gamblePanel = new JPanel(); // Initializing the pane and its components.
        gambleAmtTxt = new JTextField(4);
        gambleDiceNum = new JTextField(2);

        gambleArea = new JTextArea("This is the gamble square, \n"
                + "you are allowed to gamble your\nfortune on the rolling of a dice!\n"
                + "Gambeling can be addictive.. be careful..\n",10,10);
        gambleArea.setEditable(false);
        gamblePanel.add(gambleArea);
        gamblePanel.add(new JLabel("Gamble Amount: "));
        gamblePanel.add(gambleAmtTxt);
        gamblePanel.add(new JLabel("Dice Number: "));
        gamblePanel.add(gambleDiceNum);
                                    // Popping the box.
        JOptionPane.showMessageDialog(null, gamblePanel,
                                      "The Gamble Square", 
                                      JOptionPane.OK_CANCEL_OPTION); 
        // Getting the value and parsing them.
        if(gambleAmtTxt.getText().toString() != null)
        {
            gambleAmount = Double.parseDouble(gambleAmtTxt.getText().toString());
        }
        if(gambleDiceNum.getText().toString() != null)
        {
            diceVal = Integer.parseInt(gambleDiceNum.getText().toString());
        }
        // getting the result of the gamble operation.
        diceOne = getDiceVal();
        diceTwo = getDiceVal();
        gambleOutcome = location.gambleMoney(gambleAmount, diceVal,diceOne+diceTwo);
        
        if(gambleOutcome < 0)
        {
            message = "Dice Value: " + (diceOne + diceTwo) + 
                    " You picked: " + diceVal +
                    " You lost " + (-1*gambleOutcome)+
                    "! HAHAH! good luck next time\n";
            JOptionPane.showMessageDialog(null, message,
                    "The Gamble Square", 
                    JOptionPane.PLAIN_MESSAGE); 
                
        }
        else
        {
            message = "Dice Value: " + (diceOne + diceTwo) + 
                    " You picked: " + diceVal +
                    " You Won   " + gambleOutcome+
                    "! \n";
            JOptionPane.showMessageDialog(null, 
                    message,
                    "The Gamble Square", 
                    JOptionPane.PLAIN_MESSAGE); 
        }
        // for setting the news feed.
        if(textArea.getText().toString() != null)
        {
            if(!message.equals(textArea.getText().toString()))
            {
                message = message + textArea.getText().toString();
            }
            textArea.setText("");
            textArea.setText(message);
        }
        
        player.addMoney(gambleOutcome);
    }
    @Override
    public void paint(Graphics g)
    {
        
        super.paint(g);
        textArea.setText(message);
        // turns and dice values.
        turnCounter %= playerList.length;
        
        if(isNextTurn)
        {
            
            isNextTurn = false;         // Reset
            
            // Moving the players.
            currPlayer = playerList[playerOrder[turnCounter]];
            
            // Calculating the current location
            currentLocation = (currPlayer.getBoardLocation() + diceOne + diceTwo) % 42;
            currPlayer.setBoardLocation(currentLocation);
            current = theGame.getBoardLocate(currPlayer);
            currPlayer.setCurrentLocation(current);
            actions =  current.getPossibleActions(currPlayer);
            setButtonStatus(current.getActionStatus());
            g.drawString("Player : " + turnCounter+ "\n", 250, 250);
            
            // if the turn counter is going off the array
            if(turnCounter > playerList.length-1)
            {
                turnCounter  = 0;
            }
                       
            // prepaing the message for the turn
            message = currPlayer.getToken() + " just rolled " + (diceOne + diceTwo)
                    + " and moved on to " + current.getName() +"\n\n"; 


        }
        // if buy is clicked.
        if(isBuyClicked)
        {

            isBuyClicked = false;       // Reset
            currentLot = theGame.getBoardLocate(currPlayer);
            currPlayer.buyProperty((property)currentLot);
            message = currPlayer.getToken() + " just bought "
                      + currentLot.getName() + " for "
                      + ((property) currentLot).getPurcaseCost() + "\n";
            
            // so the user cannot buy anymore.
            buyLocation.setEnabled(false);
            isBuyClicked = false;
        }
        
        // on gamble
        if(currentLocation == 20 || currentLocation == 41)
        {
            gambleLogic((gamble)current, currPlayer);
        }
        
        verifyImprove();
        verifySell();
        
        // for setting the news feed.
        if(textArea.getText().toString() != null)
        {
            if(!message.equals(textArea.getText().toString()))
            {
                message = message + textArea.getText().toString();
            }
        }
        
        // only write if the message is different.
        if(message != textArea.getText().toString())
        {
       	    // drawing the card
            textArea.setText("");
            textArea.setText(message);
        }

        drawMonopolyCard(g, 250, northCenter.getHeight(), theGame.getBoardLocate(currPlayer));
        setLables();
    }

    
    public void drawMonopolyCard(Graphics g, int panelWidth, int panelHeight, boardLocation current )
    // PRE: g is the graphic instance sent from paint()
    //      panelWidth is the width of the Center-North Panel, Which changes on resize
    //      panelHeight is the height of the Center-North Panel, Which changes on resize
    // POST: Will draw a monopoly Card according to the position in which the player
    //       resides
   {
       g.setColor(Color.WHITE);
       g.fillRect(east.getWidth()+ panelWidth/32,north.getHeight()+ panelHeight/32,
                    panelWidth - panelWidth*2/32, panelHeight - panelHeight*2/32);
      
      
       if(current instanceof lot){
     	  drawCardTop(g, panelWidth, (panelHeight/5), ((lot) current).getColor(), current);
       }
       else
     	  drawCardTop(g, panelWidth, (panelHeight/5), "White", current);
   }

  public void drawCardTop(Graphics g, int panelWidth, int panelHeight, String tileColor, boardLocation current)
  //PRE: take in the graphics object, the width and height of the panel and and a tile color as
  //		well as the current board Location.
  //POST://Draw the top rectangle of the monopoly card with the corresponding color.
  {
	  
	String temp = tileColor.toUpperCase();
	if(temp.equals("ORANGE"))
	{
		g.setColor(Color.ORANGE);
	}
	else if(temp.equals("RED"))
	{
		g.setColor(Color.RED);
	}
	else if(temp.equals("YELLOW"))
	{
		g.setColor(Color.YELLOW);
	}	
	else if(temp.equals("GREEN"))
	{
		g.setColor(Color.GREEN);
	}
	else if(temp.equals("DARK BLUE"))
	{
		g.setColor(new Color(0,87,158));
	}
	else if(temp.equals("LIGHT PURPLE"))
	{
		g.setColor(new Color(191,101,247));	
	}
	else if (temp.equals("DARK PURPLE"))
	{
		g.setColor(new Color(107,25,148));
	}
	else if(temp.equals("LIGHT BLUE"))
	{
		g.setColor(new Color(105,187,255));
	}
	else
		g.setColor(Color.WHITE);
	
  	g.fillRect(east.getWidth()+ panelWidth/32,north.getHeight() + panelHeight/32,
  			panelWidth - panelWidth*2/32, panelHeight-panelHeight*2/32);
  	
  	g.setColor(Color.BLACK);
  	g.setFont(new Font("SansSerif", Font.PLAIN, 20));
  	drawName(g, panelWidth, panelHeight, current);
  }
  
  public void drawName(Graphics g, int panelWidth, int panelHeight, boardLocation current)
  //PRE: take in the graphics object, the width and height of the panel and the current board
  //		location.
  //POST: Draw a string with the name of the current boardLocation as well as the 
  //		current location rent if there is any.
  {
	  String Name = current.getName();
	  g.drawString(Name, panelWidth, panelHeight);
	  g.setFont(new Font("SansSerif", Font.PLAIN, 15));
	  
	  if(current instanceof lot)
	  {
		  drawRent(g, panelWidth, panelHeight, current);
	  }
	  else if(current instanceof taxSquare && ((taxSquare) current).getTaxMode() == 1)
	  {
		  g.drawString("Rent: $200.0", panelWidth-15, panelHeight+100);
	  }
	  else if(current instanceof taxSquare && ((taxSquare) current).getTaxMode() == 0)
	  {
		  g.drawString("Rent: $75.0", panelWidth-15, panelHeight+100);
	  }
	  else if(current instanceof railroad)
	  {
		  g.drawString("Rent: $" + ((railroad) current).calcRent(currPlayer.getNumberRailroad())
				  					,panelWidth-15, panelHeight+100);
	  }
	  else if(current instanceof utility)
	  {
		 int diceval = diceOne+diceTwo;
		  g.drawString("Rent: $" +  ((utility) current).caltUtilRent(currPlayer.getNumberUtilities()
				  					,diceval), panelWidth-15, panelHeight+100);
	  }
	  
	  if(current instanceof property)
	  {
		  drawCurrOwner(g, panelWidth, panelHeight, current);
	  }
	  else return;
  }
    
  public void drawCurrOwner(Graphics g, int panelWidth, int panelHeight, boardLocation current)
  //PRE: take in the graphics object, the width and height of the panel and the current board
  //		location.
  //POST: Draws a string with the name of the owner who owns the specific lot.
  {
	  if(((property) current).isOwned() == true)
	  {
		  String owner = ((property) current).getOwner().getToken();
		  g.setFont(new Font("SansSerif", Font.PLAIN, 15));
		  g.drawString("Current Owner: " + owner, panelWidth-15, panelHeight+50);
		  drawHouses(g, panelWidth,panelHeight, current);
	  } else
		  g.drawString("Current Owner: NONE", panelWidth-15, panelHeight+50);

  }
  
  public void drawHouses(Graphics g, int panelWidth, int panelHeight, boardLocation current)
  //PRE: take in the graphics object, the width and height of the panel and the current board
  //		location.
  //POST: Draws a string with the number of houses or hotels on a specified lot.
  
  {
	 if(current instanceof lot)
	 {
		 if(((lot) current).getHotel() == true)
		 {
			 g.drawString("Has Hotel", panelWidth-15, panelHeight+75);
		 }
		 else if(((lot) current).getNumHouses() >= 0)
		 {
			 g.drawString("Has "+ ((lot) current).getNumHouses() + " Houses", panelWidth-15, panelHeight+75);
		 }
		 
	 }
	 else
		 return;
  }
  
  public void drawRent(Graphics g, int panelWidth, int panelHeight, boardLocation current)
  //PRE: take in the graphics object, the width and height of the panel and the current board
  //		location.
  //POST: Draws a string with the rent value of a specified lot.
  {
	  g.drawString("Rent: $" + (((lot) current).getRent()), panelWidth-15, panelHeight+100);
  }
 
    @Override
    public void actionPerformed(ActionEvent e) 
    {
         String input;               // input catches the user input when deciding which
                                     //   property to improve
         int improveLotIndex;        // improveLotIndex will hold the index of the lot
                                     //   to be improved
         int sellableLotIndex;       // sellableLotIndex will hold the index of the lot
                                     //   to be sold
         boolean lotSelectionFound;  // stopSearch determines when the user will stop
                                     //   searching for its input within the array of
                                     //   choices.
         int numberOfLots;           // numberOfLots determines the number of 
                                     //   improvable lots
         
         String []lotNames;          // an array to hold the name of the lots 
                                     //     to be improved

         String propVal;             // hotel when selling hotel, else house.
         
         lot lotToImprove;           //lotToImprove holds the lot to be improved
         lot lotToBeSold;            //lotToBeSold holds the lo
         
         
         lotSelectionFound = false;
         improveLotIndex = -1;  //Initialized to -1 to signify that no lot can
                                //   be improved or sold
         sellableLotIndex = -1;
         input = "";
         
         propVal = "house";
         
         //Verify whether: game continues,  can improve, or can sell
         verifyImprove();
         verifySell();        
         gameContinues = currPlayer.canContinue(sellableLots);
         
         if (gameContinues == false)    //If game is said to stop working, then
                                        //  disable all the buttons except for 
                                        //  end game
         {
             buyLocation.setEnabled(false);
             sellHouses.setEnabled(false);
             nextTurn.setEnabled(false);
             improveProperty.setEnabled(false);
             
         }
         
         
         if(e.getSource() == buyLocation)
         {
           isBuyClicked = true;

         }

         if(e.getSource() == sellHouses)
         {
           sellableLots = currPlayer.getSellableLots();  
           numberOfLots = sellableLots.length;
           lotNames = new String [numberOfLots]; 
           
           int j = 0;
           
           if (currPlayer.canSell(sellableLots))  //Make sure there exists a lot
                                                  //  that can be sold
           {
               for(int i = 0; i < numberOfLots; i++)
               {
                   
                   if(sellableLots[i] != null)  //Make sure the current lot is not null
                   {
                       lotNames[j] = sellableLots[i].getName();
                       System.out.println(lotNames[j]);
                       j++;
                   }
                   
               }
               
               //Catch the user input When selecting a lot to sell from
               input = (String) JOptionPane.showInputDialog(null, "Choose a lot",
                   "Lots you can sell on", JOptionPane.QUESTION_MESSAGE, null,lotNames, // Array of choices
                   lotNames[1]); // Initial choice
               
               //Search the user input within the array of possibe choices
               for(int i = 0; i < numberOfLots && lotSelectionFound == false; i++)
               {
                   if(lotNames[i] != null && input != null)  //Make sure the current choice not null
                   {
                       if(input.equals(lotNames[i]))
                       {
                           sellableLotIndex = i;
                           lotSelectionFound = true;      //Kill the loop once we find the index
                       }
                   }
               }
               
               if (lotSelectionFound == true)
               {
                   //Fetch a reference to the lot we intend to sell from
                   lotToBeSold = (lot) currPlayer.canBeSold[sellableLotIndex];
                   
                   
                   //increase money by half the improvement cost
                   currPlayer.addMoney(lotToBeSold.getImproveCost()/2);;
                   
                   if(lotToBeSold.getHotel() == true)     //Handle the case when the
                                                          //   current location is a
                                                          //   hotel
                   {
                       lotToBeSold.sellHotel();
                       propVal = "hotel";
                   }
                   
                   
                   
                   else //Handle the case when doesn't have any hotels
                   {
                       lotToBeSold.sellHouse();
                       propVal = "house";
                   }
                   
                                      // updating the message.
                   message = currPlayer.getToken() + " just sold a " + propVal
                           + " on " + lotToBeSold.getName();
                   
               }
               
               else
               {
                    // Disable sellHouses button on failure to find a suitable
                    //   location
                    message = currPlayer.getToken() + " cannot sell any property.\n";
                    canSell = false;
                    sellHouses.setEnabled(canSell);
                    
               }
               
               
               
           }
           
           

           playerList[0].sell((playerList[0].getMoney()*-1));
         }

         if(e.getSource() == endGame)
         {
	      message = "game is ended.\n";
              result = playerList[0].toString() + "\n "+ playerList[1].toString() + "\n " 
            		  		+ playerList[2].toString() +"\n " + playerList[3].toString();
              area = new JTextArea(result);
              area.setRows(30);
              area.setColumns(40);
              pane = new JScrollPane(area);
              JOptionPane.showMessageDialog(null, pane, "End Game Info.", JOptionPane.PLAIN_MESSAGE);
              System.exit(0);
         }

        if(e.getSource() == improveProperty)
        {      
            //Update improvable lots on every instance we try to improve a 
            //   property
            improvableLots = currPlayer.getImprovingLots();
            numberOfLots = improvableLots.length;
            
            lotNames = new String [numberOfLots]; 
            
            int j = 0;
            
            if (currPlayer.canImprove(improvableLots))  //Make sure there exists a lot
                                                        //  that can be improved
            {
    	        for(int i = 0; i < numberOfLots; i++)
    	        {
    	        	
    	            if(improvableLots[i] != null)  //Make sure the current lot is not null
    	        	{
    	        		lotNames[j] = improvableLots[i].getName();
    	        		System.out.println(lotNames[j]);
    	        		j++;
    	        	}
    	        }
    	        
                //Catch the user input When selecting a lot to improve
                input = (String) JOptionPane.showInputDialog(null, "Choose a lot",
                    "Lots you can improve", JOptionPane.QUESTION_MESSAGE, null,lotNames, // Array of choices
                    lotNames[1]); // Initial choice
                   
                //Search the user input within the array of possibe choices
                for(int i = 0; i < numberOfLots && lotSelectionFound == false; i++)
                {
                    if(lotNames[i] != null && input != null)  //Make sure the current choice not null
                    {
                        if(input.equals(lotNames[i]))
                        {
                            improveLotIndex = i;
                            lotSelectionFound = true;      //Kill the loop once we find the index
                        }
                    }
                }
                
                if (lotSelectionFound == true)
                {
                    //Fetch a reference to the lot we intend to improve
                    lotToImprove = (lot) currPlayer.canBeImproved[improveLotIndex];
                    
                    //Deduce the cost of improvement from the user
                    currPlayer.payRent(theBank, lotToImprove.getImproveCost());
                    
                    if(lotToImprove.getNumHouses() == 4)   //Handle the case when the
                                                           //   player has 4 houses and
                                                           //   must improve to hotel
                    {
                        lotToImprove.makeHotel();
                    }
                    
                    else //Handle the case when the player has less than 4 houses
                    {
                        lotToImprove.addNumHouses();
                    }
                    
                }
                
                lotSelectionFound = false;  // lot selection for next iteration
                improveLotIndex = 0; // Reset for next iteration
            }
            
            else
            {
                 // Disable improveProperty button on failure to find a suitable
                 //   location
                 canImprove = false;
                 improveProperty.setEnabled(canImprove);
                 
            }
        }
        if(e.getSource() == nextTurn)
        {   
            diceOne = getDiceVal();
            diceTwo = getDiceVal();
            
            if(diceOne == diceTwo)
            {
                message = "just rolled a double current player gets another"
                        + "turn!\n";
                message = message + textArea.getText().toString();
            }
            else
            {
                turnCounter++;
            }
            
            isNextTurn = true;
        }

        if(e.getSource() == getLocation)
        {
            int locationIndex;

            //Fetch the location index for the comboBox.
            locationIndex = allLocations.getSelectedIndex();

            if(locationIndex >= 0)           //Handle unselected index case
                popUpLocationInfo(theGame.monopolyBoard[locationIndex]);

        }

        if(e.getSource() == endGame)
        {
            System.exit(0);
        }

        if(e.getSource() == playerProp[0])
        {
            popUpPlayerInfo(playerList[playerOrder[0]]);
        }
        if(e.getSource() == playerProp[1])
        {
            popUpPlayerInfo(playerList[playerOrder[1]]);
        }
        if(e.getSource() == playerProp[2])
        {
            popUpPlayerInfo(playerList[playerOrder[2]]);
        }
        if(e.getSource() == playerProp[3])
        {
            popUpPlayerInfo(playerList[playerOrder[3]]);
        }
        
              // for setting the news feed.
        if(textArea.getText().toString() != null)
        {
            if(!message.equals(textArea.getText().toString()))
            {
                message = message + textArea.getText().toString();
            }
        }
        
        // only write if the message is different.
        if(message != textArea.getText().toString())
        {
            textArea.setText("");
            textArea.setText(message);
        }

        repaint();
    }

    @Override
    public void itemStateChanged(ItemEvent arg0) {
        // TODO Auto-generated method stub

    }
    
    public void addToPanel()
    {
      //East side add components to panel
        
        for(int i = 0; i < NUM_PLAYERS; i++) // add button controls for players
                                             //     playing the game only.
        {
            east.add(playerProp[i]);
            east.add(playerStatus[i]);
        }


        //West side add components to panels
        west.add(buyLocation);
        west.add(improveProperty);
        west.add(sellHouses);
        west.add(endGame);

        south.add(nextTurn);
        center.add(northCenter);
        center.add(southCenter);
        
        northCenter.add(BorderLayout.EAST,northRightCenter);

        southCenter.add(scrollPane, BorderLayout.CENTER);

        for(int i = 0; i < NUM_PLAYERS; i++)
        {
            east.add(playerProp[i]);
            east.add(playerStatus[i]);
        }

        east.add(getLocation);
        east.add(allLocations);

        add(layout.SOUTH, south);
        add(layout.NORTH, north);
        add(layout.EAST, east);
        add(layout.WEST, west);
        add(layout.CENTER, center);

    }

    public void initializeWidgets()
    // POST: Will initialize all the widgets in the applet
    {
        //East Side
        playerProp = new JButton [5];       //Initialize the array itself
        playerStatus = new JLabel [5];
        allLocations = new JComboBox(theGame.getLocationNames());
        getLocation = new JButton("This Location - Info");

        //West side
        nextTurn = new JButton("Next Turn");
        buyLocation = new JButton("Buy this Property");
        improveProperty = new JButton("Improve this Property");
        sellHouses = new JButton("Sell Houses");
        endGame = new JButton("End Game");
        nextTurn = new JButton("Next Turn");

        //Center - South Side
        textArea = new JTextArea();
        scrollPane = new JScrollPane(textArea);
    }

    public void initializeMonopoly()
    // POST: Will initialize Players and the Monopoly Game
    {
        //Initialize the bank Player
        theBank = new player (9999,0, "Bank");

        //Initialize the player List
        playerList = new player[NUM_PLAYERS];
        
        for(int i = 0; i < NUM_PLAYERS; i++)
            playerList[i] = new player(1500,0,PLAYER_NAMES[i]);

        //Initialize the monopoly Game
        theGame = new Monopoly(playerList);
        //theGame.demoMode();
    }

    public void initializePanels()
    // POST:  Will initialize all Panels and Layouts
    {
        //Set up Layout
        layout = new BorderLayout();
        
        //Set up all panels
        south = new JPanel();
        north = new JPanel();
        east = new JPanel();
        west = new JPanel();
        center = new JPanel();
        northCenter = new JPanel();
        southCenter = new JPanel();
        northRightCenter = new JPanel();

//      Test different colors
//      south.setBackground(Color.BLUE);
//      north.setBackground(Color.RED);
//      east.setBackground(Color.GRAY);
//      west.setBackground(Color.GREEN);
//      northCenter.setBackground(Color.BLACK);
//      southCenter.setBackground(Color.YELLOW);
//      center.setBackground(Color.CYAN);
//      northRightCenter.setBackground(Color.BLACK);

        center.setLayout(new GridLayout(2,1));
        west.setLayout (new GridLayout (5,1,0,30));
        east.setLayout(new GridLayout(5,2,0,30));
        east.setLayout(new GridLayout(10,1,0,30));
        northCenter.setLayout(new GridLayout(1,2));
        southCenter.setLayout(new BorderLayout());
    }

    public void popUpLocationInfo(boardLocation theLocation)
    // PRE:  locations was initialized.
    // POST: This method pops up a window with a scroll bar
    //       it prints out the location information.

    // NOTE: Most of this method was taken from
    // URL: http://www2.cs.uic.edu/~hogand/cs342/monopoly/food-for-thought.htm
    // 10/21/2015 , by Christian Valladares
    {
        String info;        //The information ofr a given location

        JTextArea area;
        JScrollPane pane;

        info = theLocation.toString();
        area = new JTextArea(info);
        area.setRows(10);
        area.setColumns(20);
        pane = new JScrollPane(area);
        JOptionPane.showMessageDialog(null, pane, "Monopoly Board Info.", JOptionPane.PLAIN_MESSAGE);

    }

    public void popUpPlayerInfo(player player)
    // PRE: player was initialized.
    // POST: This method pops up a window with a scroll bar
    //       it prints out all the player information.

    // NOTE: Most of this method was taken from
    // URL: http://www2.cs.uic.edu/~hogand/cs342/monopoly/food-for-thought.htm
    // 10/20/2015 , by Harsh Patel
    {
       String result;                                  // Output message displayed in the window

       JTextArea area;                                 // Text area to hold the message
       JScrollPane pane;                               // Window pane with scrollbar containing text area

       // You build the result message here...
       result = player.toString();
       area = new JTextArea(result);
       area.setRows(10);
       area.setColumns(10);
       pane = new JScrollPane(area);
       JOptionPane.showMessageDialog(null, pane, "Monopoly Board Info.", JOptionPane.PLAIN_MESSAGE);
    }
    
    public void verifyImprove()
    //POST: will enable or disable the improve button based on whether the
    //      player has improvable properties
    {
        //Verify whether the current player has a lot that can be 
        //  improved and enable or disable the improve button accordingly
        improvableLots = currPlayer.getImprovingLots();
        int numberOfLots = improvableLots.length;
        
        if (currPlayer.canImprove(improvableLots)) //check if the user can Improve
        {
            canImprove = true;
            improveProperty.setEnabled(canImprove);
        }
        
        else
        {
            canImprove = false;
            improveProperty.setEnabled(canImprove);
        }
    }
    
    
    public void verifySell()
    //POST: will enable or disable the sell button based on whether the
    //      player has sellable properties
    {
        //Verify whether the current player has a lot that can be 
        //  sold and enable or disable the sell button accordingly
        sellableLots = currPlayer.getSellableLots();
        int numberOfLots = sellableLots.length;
        
        if (currPlayer.canSell(sellableLots)) //check if the user can Improve
        {
            canSell = true;
            sellHouses.setEnabled(canSell);
        }
        
        else
        {
            canSell = false;
            sellHouses.setEnabled(canSell);
        }
    }
}
