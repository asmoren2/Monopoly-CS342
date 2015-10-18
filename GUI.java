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
    private JButton buyLocation;
    JButton playerProp [];              // playerInfo is an array of buttons
                                        //   to fetch  properties for players 1-4
    JLabel  playerStatus[];             // A label to determine basic player infor
                                        //   like current position, and funds
    int tmpPlayerLocation;              // An Integer to hold where a player's location
                                        //   at a given moment.
    double tmpPlayerFunds;                 // An Integer to hold a player's fund on a
                                        //   given instant

    JPanel south;
    JPanel north;
    JPanel east ;
    JPanel west;
    JPanel center;
    JPanel northCenter;
    JPanel southCenter;
    JPanel east1L;
    JPanel east2L;
    JPanel east3L;
    JPanel east4L;
    JPanel east5L;
    JPanel east1R;
    JPanel east2R;
    JPanel east3R;
    JPanel east4R;
    JPanel east5R;

    @Override
    public void init()
    {
        //Set up all panels
        south = new JPanel();
        north = new JPanel();
        east = new JPanel();
        west = new JPanel();
        center = new JPanel();
        northCenter = new JPanel();
        southCenter = new JPanel();
        east1L = new JPanel();
        east2L = new JPanel();
        east3L = new JPanel();
        east4L = new JPanel();
        east5L = new JPanel();
        east1R = new JPanel();
        east2R = new JPanel();
        east3R = new JPanel();
        east4R = new JPanel();
        east5R = new JPanel();

        //Test different colors
        south.setBackground(Color.BLUE);
        north.setBackground(Color.RED);
        east.setBackground(Color.GRAY);
        west.setBackground(Color.GREEN);
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

        //Initialize the Get Player Property section and Player values section.
        playerProp = new JButton [5];       //Initialize the array itself
        playerStatus = new JLabel [5];

        BorderLayout layout;
        layout = new BorderLayout();
        turnCounter = 0;
        isNextTurn = true;

        setLayout(layout);
      //  setSize(600,600);
        playerList = new player[4];
        playerList[0] = new player(1500, 0, "Harsh");
        playerList[1] = new player(1500, 0, "Adolfo");
        playerList[2] = new player(1500, 0, "Christian");
        playerList[3] = new player(1500, 0, "Cortellano");

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

        east1L.add(playerProp[0]);
        east2L.add(playerStatus[0]);
        east3L.add(playerProp[1]);
        east4L.add(playerStatus[1]);
        east5L.add(playerProp[2]);
        east1R.add(playerStatus[2]);
        east2R.add(playerProp[3]);
        east3R.add(playerStatus[3]);
//        east4R.add(playerProp[4]);
//        east5R.add(playerStatus[4]);


        theBank = new player (9999,0, "Bank");
        theGame = new Monopoly(playerList);

        nextTurn = new JButton("Next Turn");
        nextTurn.addActionListener(this);
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

        add(layout.SOUTH, south);
        add(layout.NORTH, north);
        add(layout.EAST, east);
        add(layout.WEST, west);
        add(layout.CENTER, center);



    }

    @Override
    public void paint(Graphics g)
    {
        while(isNextTurn)
        {
            super.paint(g);
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

        repaint();
    }


}
