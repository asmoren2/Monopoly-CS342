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
    private JFrame frame;               //JoptionPane
    private String result;              // Output message displayed in the window
    private JTextArea area;             // Text area to hold the message
    private JScrollPane pane;           // Window pane with scrollbar containing text area
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

        playerOrder = theGame.getPlayerOrder();
        player cur = playerList[playerOrder[0]];
        String []a =  theGame.getBoardLocate(cur).getPossibleActions(cur);
        setButtonStatus(theGame.getBoardLocate(playerList[playerOrder[0]]).getActionStatus());

        //Set layout now that panels are set up
        setLayout(layout);

        turnCounter = 0;
        isNextTurn = true;



        initializePanels();

        //Populate the players with their information.
        for(int i = 0; i < playerList.length; i++)
        {
            tmpPlayerLocation = playerList[playerOrder[i]].getBoardLocation();
            tmpPlayerFunds = playerList[playerOrder[i]].getMoney();
            //Initialize the actual buttons and labels with the expected
            //   information
            playerProp[i] = new JButton("Player " + i + " Properties.");
            playerStatus[i] = new JLabel("Location: " + tmpPlayerLocation +
                                         "\nFunds: "  + tmpPlayerFunds);
        }

        //southCenter

        textArea.setEditable(false);
        textArea.append("Harsh paid $200 to Christian \n");
        textArea.append("Adolfo has passed go collected $200");


        addActionListeners();
        addToPanel();

        for(int x : playerOrder)
            System.out.println(x);

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
        nextPlayer.addActionListener(this);
        endGame.addActionListener(this);

        playerProp[0].addActionListener(this);
        playerProp[1].addActionListener(this);
        playerProp[2].addActionListener(this);
        playerProp[3].addActionListener(this);

        nextTurn.addActionListener(this);
        getLocation.addActionListener(this);
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);

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




        if(e.getSource() == nextTurn)
        {
            turnCounter++;
            isNextTurn = true;
        }

        if(e.getSource() == buyLocation)
        {
            message = "Thank you, you just bought Illinois Ave.";
        }

        if(e.getSource() == sellHouses)
        {
            message = "We are now selling your houses";
        }
        if(e.getSource() == nextPlayer)
        {

        }

        if(e.getSource() == getLocation)
        {
            int locationIndex;

            //Fetch the location index fro the comboBox.
            locationIndex = allLocations.getSelectedIndex();

            if(locationIndex >= 0)           //Handle unseelected index case
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


        repaint();

        repaint();
    }

    @Override
    public void itemStateChanged(ItemEvent arg0) {
        // TODO Auto-generated method stub

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

        southCenter.add(scrollPane, BorderLayout.CENTER);

        east.add(playerProp[0]);
        east.add(playerStatus[0]);
        east.add(playerProp[1]);
        east.add(playerStatus[1]);
        east.add(playerProp[2]);
        east.add(playerStatus[2]);
        east.add(playerProp[3]);
        east.add(playerStatus[3]);
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


        nextTurn = new JButton("Next Turn");
        //West side
        buyLocation = new JButton("Buy this Property");
        improveProperty = new JButton("Improve this Property");
        sellHouses = new JButton("Sell Houses");
        nextPlayer = new JButton("End Turn");
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
        playerList = new player[4];
        playerList[0] = new player(1500, 0, "Harsh");
        playerList[1] = new player(1500, 0, "Adolfo");
        playerList[2] = new player(1500, 0, "Christian");
        playerList[3] = new player(1500, 0, "Cortellano");

        //Initialize the monopoly Game
        theGame = new Monopoly(playerList);
        theGame.demoMode();
    }

    public void updateUserLabels()
    {
        for(int i = 0; i < playerList.length; i++)
        {
            tmpPlayerLocation = playerList[playerOrder[i]].getBoardLocation();
            tmpPlayerFunds = playerList[playerOrder[i]].getMoney();
            //Initialize the actual buttons and labels with the expected
            //   information
            playerStatus[i].setText("Location: " + tmpPlayerLocation +
                                    "\nFunds: "  + tmpPlayerFunds);
        }
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
       area.setColumns(20);
       pane = new JScrollPane(area);
       JOptionPane.showMessageDialog(null, pane, "Monopoly Board Info.", JOptionPane.PLAIN_MESSAGE);
    }
}
//
