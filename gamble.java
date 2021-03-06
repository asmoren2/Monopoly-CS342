// Programmer:  Harsh Patel
// Assignment:  Monopoly
// Date:        October, 17 2015
// Description: This class represents a gamble square on the board
//              it is a special feature that allows the user that lands
//              on the square to gamble a fortune on the roll of a dice.

public class gamble extends boardLocation
{
    
    private double gambleAmount;
    
    public gamble()
    // POST: a gamble object is created with the class member gambleAmount
    //       initialized to $0.0.
    {
        this(0.0);
    }
    
    public gamble(double gambleAmount)
    // PRE: gambleAmount must be initialized.
    // POST: a gamble object is created with the class member gambleAmount
    //       initialized to gambleAmount.
    {
        nameOfLocation = "Gamble";
        this.gambleAmount = gambleAmount;
    }
    
    public void setGambleAmount(double gambleAmount)
    // PRE: gambleAmount must be initialized.
    // POST: sets the class member gambleAmount to gambleAmount
    {
        this.gambleAmount = gambleAmount;
    }
    
    public double gambleMoney(double money, int num, int randNum)
    // PRE:  money > 0,
    //       0 < num < 6
    // POST: returns the amount of money you have won or lost.
    //       if you guessed the number right, you win double 
    //       the money, else you loose double the money.
    {       
        if(randNum == num)          // if the number was guessed 
        {                           // correctly.
            return money*2;
        }
        else                        // number not guessed correctly.
        {
            return -1*money*2;
        }
    }
    
    @Override
    public String [] getPossibleActions(player player)
    // PRE: player must be initialized.
    // POST: FCTVAL == possibleActions, an array of Strings containing all
    //                 the possible actions a player can perform at a Gamble square
    //                 is returned.  
    //                 An array of booleans that represents the same thing is also 
    //                 altered in order to be used in the GUI.
    {
       actionStatus[4] = true;               // end game
       possibleActions[4] = PACTIONS[4];
       
       if(player.getMoney() < 0 &&           // the player has no money and can sell
          player.hasSellableProperty())
       {
           actionStatus[2] = true;           // sell
           possibleActions[2] = PACTIONS[2];
       }                                     // if player has improvable lots.
       else if (player.canImprove(player.getImprovingLots()))
       {
           actionStatus[3] = true;           // improve lots
           possibleActions[3] = PACTIONS[3];
       }
       
       return possibleActions;
    }
    
    @Override
    public String toString()
    // FCTVAL == a string that represents what the gamble square does,
    //           and the amount of money that is currently gambled.
    {
        return "This is the gamble square. The main purpose\n"
               + " of this class is to allow the user to gamble\n"
               + " a certain amount of money in hopes of getting\n"
               + " twice the money back, but gambeling has its consequences...\n"
               + " you gambled:" + gambleAmount;
               
    }
}
