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

    @Override
    public void init()
    {
        JPanel south = new JPanel();
        BorderLayout layout;
        layout = new BorderLayout();
        turnCounter = 0;
        isNextTurn = true;
        
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
        add(BorderLayout.SOUTH, south);
        
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
