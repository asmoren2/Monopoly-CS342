import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;

public class GUI extends JApplet implements ActionListener, ItemListener
{
    private player [] playerList;       // The list of players
    private player theBank;             // The bank
    private Monopoly theGame;           // The monopoly game
    private boolean isNextTurn;         // This boolean represents if
                                        // the user pressed next turn.
    private int turnCounter;            // Counter for turn.
    private JButton nextTurn;
    private JButton buyLocation;

    //For East side
    JButton playerProp [];              // playerInfo is an array of buttons
                                        //   to fetch  properties for players 1-4
    JLabel playerStatus[];              // A label to determine basic player infor
                                        //   like current position, and funds
    JComboBox allLocations;             // A drop down list that contains all locations
    JButton getLocation;                // getLocation will prompt the system to
                                        //   fetch the information for a given location

    // Common to all Functions
    int tmpPlayerLocation;              // An Integer to hold where a player's location
                                        //   at a given moment.
    double tmpPlayerFunds;              // An Integer to hold a player's fund on a
                                        //   given instant

    // Related to West Side
    private JButton improveProperty;    // Button to improve property
    private JButton sellHouses;         // Sell houses when you have no money
    private JButton nextPlayer;         // End turn go to next player
    private JButton endGame;            // Player can choose to end game

    //SouthCenter
    private JTextArea textArea;
    private JScrollPane scrollPane;
    private String message;
    private int []playerOrder;
    //////////////////CHANGED////////////////////////////////
    private JFrame frame; 				//JoptionPane
    private String result; 				// Output message displayed in the window
    private JTextArea area;             // Text area to hold the message
    private JScrollPane pane;           // Window pane with scrollbar containing text area
    /////////////////END CHANGE//////////////////////////////
    


    // Related to Layout and panels
    JPanel south;
    JPanel north;
    JPanel east ;
    JPanel west;
    JPanel center;
    JPanel northCenter;
    JPanel southCenter;
    BorderLayout layout;

    @Override
    public void init()
    {

        initializeMonopoly();
        initializeWidgets();
        initializePanels();
        
        ///////////////////CHANGED THIS/////////////////////////////////////////
        playerOrder = theGame.getPlayerOrder();
        player cur = playerList[playerOrder[0]];
        String []a =  theGame.getBoardLocate(cur).getPossibleActions(cur);
        setButtonStatus(theGame.getBoardLocate(playerList[playerOrder[0]]).getActionStatus());
        //////////////////END CHANGE///////////////////////////////////////////
        
        
        //Set layout now that panels are set up
        setLayout(layout);

        turnCounter = 0;
        isNextTurn = true;

        initializePanels();

        //Populate the players with their information.
        for(int i = 0; i < 4; i++)
        {
            tmpPlayerLocation = playerList[i].getBoardLocation();
            tmpPlayerFunds = playerList[i].getMoney();
            //Initialize the actual buttons and labels with the expected
            //   information
            playerProp[i] = new JButton("Player " + i + " Properties.");
            playerStatus[i] = new JLabel("Location: " + tmpPlayerLocation +
                                         "\nFunds: "  + tmpPlayerFunds);
        }

        //southCenter
        textArea = new JTextArea(5,20);
        scrollPane = new JScrollPane(textArea);
        textArea.setEditable(false);
        textArea.append("Harsh paid $200 to Christian \n");
        textArea.append("Adolfo has passed go collected $200");

        addActionListeners();
        addToPanel();
       

}
    ///////////////CHANGED THIS////////////////////
    public void setButtonStatus(boolean []status)
    {	
    	for(int i = 0; i < 5; i++){
    		if(status[i] == true){
    			System.out.println("True");
    		}
    		else
    			System.out.println("False");
    	}
    	improveProperty.setEnabled(status[0]);
    	sellHouses.setEnabled(status[1]);
    	nextPlayer.setEnabled(status[2]);
    	buyLocation.setEnabled(status[3]);
    	endGame.setEnabled(status[4]);

    }
    //////////////END CHANGE/////////////////////
    
    public void addActionListeners()
    {
    	improveProperty.addActionListener(this);
    	sellHouses.addActionListener(this);
    	buyLocation.addActionListener(this);
    	nextPlayer.addActionListener(this);
    	endGame.addActionListener(this);
    	
    }

    public void addToPanel()
    {
      //East side add components to panels
        east.add(playerProp[0]);
        east.add(playerStatus[0]);
        east.add(playerProp[1]);
        east.add(playerStatus[1]);
        east.add(playerProp[2]);
        east.add(playerStatus[2]);
        east.add(playerProp[3]);
        east.add(playerStatus[3]);

        //West side add components to panels
        west.add(buyLocation);
        west.add(improveProperty);
        west.add(sellHouses);
        west.add(nextPlayer);
        west.add(endGame);
        south.add(nextTurn);
        center.add(northCenter);
        center.add(southCenter);
        
        southCenter.add(scrollPane);

        east.add(playerProp[0]);
        east.add(playerStatus[0]);
        east.add(playerProp[1]);
        east.add(playerStatus[1]);
        east.add(playerProp[2]);
        east.add(playerStatus[2]);
        east.add(playerProp[3]);
        east.add(playerStatus[3]);

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

        //West side
        nextTurn = new JButton("Next Turn");
        nextTurn.addActionListener(this);
        buyLocation = new JButton("Buy this Property");
        improveProperty = new JButton("Improve this Property");
        sellHouses = new JButton("Sell Houses");
        nextPlayer = new JButton("End Turn");
        endGame = new JButton("End Game");
    }

    public void initializeMonopoly()
    // POST: Will initialize Players and the Monopoly Game
    {

        //Initialize the bank Player
        theBank = new player (9999,0, "Bank");

        //Initialize the player List
        playerList = new player[4];
        playerList[0] = new player(1500, 0, "Harsh");
        playerList[1] = new player(1500, 0, "Adolfo");
        playerList[2] = new player(1500, 0, "Christian");
        playerList[3] = new player(1500, 0, "Cortellano");

        //////////////////CHANGED//////////////////////
        //Initialize the monopoly Game
        theGame = new Monopoly(playerList);
        theGame.demoMode();
        /////////////////END CHANGE///////////////////
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

//      Test different colors
//      south.setBackground(Color.BLUE);
//      north.setBackground(Color.RED);
//      east.setBackground(Color.GRAY);
//      west.setBackground(Color.GREEN);
//      northCenter.setBackground(Color.BLACK);
//      southCenter.setBackground(Color.YELLOW);
//      center.setBackground(Color.CYAN);

        center.setLayout(new GridLayout(2,1));
        west.setLayout (new GridLayout (5,1,0,30));
        east.setLayout(new GridLayout(5,2,0,30));
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        //g.drawString(message, 250,250);

        while(isNextTurn)
        {
            isNextTurn = false;         // Reset
            g.drawString("Player : " + turnCounter+ "\n", 250, 250);
            if(turnCounter >= playerList.length)
            {
                turnCounter  = 0;
            }
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == nextTurn)
        {
            turnCounter++;
            isNextTurn = true;
        }
        /////////////////////////CHANGED ADOLFO////////////////////////////////////
        if(e.getSource() == buyLocation)
        {
        	message = "Thank you, you just bought Illinois Ave.";
        }
        
        if(e.getSource() == sellHouses)
        {
        	message = "We are now selling your houses";
        	playerList[0].sell((playerList[0].getMoney()*-1));
        }
        if(e.getSource() == nextPlayer)
        {
        	turnCounter++;
            isNextTurn = true;
        }
        if(e.getSource() == endGame)
        {
        	result = playerList[0].toString() + "\n "+ playerList[1].toString() + "\n " + playerList[2].toString() +"\n " + playerList[3].toString();
            area = new JTextArea(result); 
            area.setRows(30); 
            area.setColumns(40);
            pane = new JScrollPane(area);
            JOptionPane.showMessageDialog(null, pane, "End Game Info.", JOptionPane.PLAIN_MESSAGE);   
        	System.exit(0);
        }
        //////////////////////////END CHANGE/////////////////////////////////////////////

        repaint();
    }

    @Override
    public void itemStateChanged(ItemEvent arg0) {
        // TODO Auto-generated method stub

    }


}
