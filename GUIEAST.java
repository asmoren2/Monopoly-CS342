import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GUI extends JApplet implements ActionListener
{
    private player [] playerList;       // The list of players
    private player theBank;             // The bank
    private Monopoly theGame;           // The monopoly game
    private boolean isNextTurn;         // This boolean represents if
                                        // the user pressed next turn.
    private int turnCounter;            // Counter for turn.
    private JButton nextTurn;
    private JButton buyLocation;		// Buy current property
    private JButton improveProperty; 	// Button to improve property
    private JButton sellHouses;			// Sell houses when you have no money
    private JButton nextPlayer;			// End turn go to next player
    private JButton endGame;			// Player can choose to end game

    @Override
    public void init()
    {
        JPanel south = new JPanel();
        JPanel north = new JPanel();
        JPanel east = new JPanel();
        JPanel west = new JPanel();
        JPanel center = new JPanel();
        JPanel northCenter = new JPanel();
        JPanel southCenter = new JPanel();
        JPanel east1L = new JPanel();
        JPanel east2L = new JPanel();
        JPanel east3L = new JPanel();
        JPanel east4L = new JPanel();
        JPanel east5L = new JPanel();
        JPanel east1R = new JPanel();
        JPanel east2R = new JPanel();
        JPanel east3R = new JPanel();
        JPanel east4R = new JPanel();
        JPanel east5R = new JPanel();
        JPanel west1  = new JPanel();
        JPanel west2  = new JPanel();
        JPanel west3  = new JPanel();
        JPanel west4  = new JPanel();
        JPanel west5  = new JPanel();

        BorderLayout layout;
        layout = new BorderLayout();
        turnCounter = 0;
        isNextTurn = true;

        //Test different colors
        south.setBackground(Color.BLUE);
        north.setBackground(Color.RED);
        east.setBackground(Color.GRAY);
        west.setBackground(Color.GREEN);
        west.setLayout(new GridLayout(5,1));
        northCenter.setBackground(Color.BLACK);
        southCenter.setBackground(Color.YELLOW);
        center.setLayout(new GridLayout(2,1));
        east.setLayout(new GridLayout(5,2));
        center.setBackground(Color.CYAN);
        east1L.setBackground(Color.ORANGE);
        east2L.setBackground(Color.BLACK);
        east3L.setBackground(Color.RED);
        east4L.setBackground(Color.GRAY);
        east5L.setBackground(Color.WHITE);
        east1R.setBackground(Color.ORANGE);
        east2R.setBackground(Color.BLACK);
        east3R.setBackground(Color.RED);
        east4R.setBackground(Color.GRAY);
        east5R.setBackground(Color.WHITE);

//        west1.setBackground(Color.GRAY);
//        west2.setBackground(Color.BLACK);
//        west3.setBackground(Color.CYAN);
//        west4.setBackground(Color.ORANGE);
//        west5.setBackground(Color.YELLOW);

        setLayout(layout);
        setSize(600,600);
        playerList = new player[3];
        playerList[0] = new player(1500, 0, "Harsh");
        playerList[1] = new player(1500, 0, "Adolfo");
        playerList[2] = new player(1500, 0, "Christian");

        theBank = new player (9999,0, "Bank");
        theGame = new Monopoly(playerList);

        //initializing the buttons
        buyLocation = new JButton("Buy this Property");
        improveProperty = new JButton("Improve this Property");
        sellHouses = new JButton("Sell Houses");
        nextPlayer = new JButton("End Turn");
        endGame = new JButton("End Game");
        nextTurn = new JButton("Next Turn");

        //Add actionListeners to the buttons
        nextTurn.addActionListener(this);
        buyLocation.addActionListener(this);
        improveProperty.addActionListener(this);
        sellHouses.addActionListener(this);
        nextPlayer.addActionListener(this);
        endGame.addActionListener(this);
        //Add buttons to respective WEST Panel
        west1.add(buyLocation);
        west2.add(improveProperty);
        west3.add(sellHouses);
        west4.add(nextPlayer);
        west5.add(endGame);

        south.add(nextTurn);
        center.add(northCenter);
        center.add(southCenter);
        east.add(east1L);
        east.add(east2L);
        east.add(east3L);
        east.add(east4L);
        east.add(east5L);
        east.add(east1R);
        east.add(east2R);
        east.add(east3R);
        east.add(east4R);
        east.add(east5R);

        west.add(west1);
        west.add(west2);
        west.add(west3);
        west.add(west4);
        west.add(west5);

        add(BorderLayout.SOUTH, south);
        add(BorderLayout.NORTH, north);
        add(BorderLayout.EAST, east);
        add(BorderLayout.WEST, west);
        add(BorderLayout.CENTER, center);

    }

    @Override
    public void paint(Graphics g)
    {
        while(isNextTurn)
        {
            super.paint(g);
            isNextTurn = false;         // Reset
            g.drawString("Player #: " + turnCounter+ "\n", 250, 250);
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
        repaint();
    }


}
