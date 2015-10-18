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
        BorderLayout layout;
        layout = new BorderLayout();
        turnCounter = 0;
        isNextTurn = true;
        
        //Test different colors
        south.setBackground(Color.BLUE);
        north.setBackground(Color.RED);
        east.setBackground(Color.GRAY);
        west.setBackground(Color.GREEN);
        northCenter.setBackground(Color.BLACK);
        southCenter.setBackground(Color.YELLOW);
        center.setLayout(new GridLayout(2,1));
        center.setBackground(Color.CYAN);

        
        
        setLayout(layout);
        setSize(600,600);
        playerList = new player[3];        
        playerList[0] = new player(1500, 0, "Harsh");
        playerList[1] = new player(1500, 0, "Adolfo");
        playerList[2] = new player(1500, 0, "Christian");
        
        theBank = new player (9999,0, "Bank");
        theGame = new Monopoly(playerList);
        
        nextTurn = new JButton("Next Turn");
        nextTurn.addActionListener(this);
        south.add(nextTurn);
        center.add(northCenter);
        center.add(southCenter);
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
