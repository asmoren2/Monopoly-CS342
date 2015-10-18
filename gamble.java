// Programmer:  Harsh Patel
// Assignment:  Monopoly
// Date:        October, 17 2015
// Description: This class represents a gamble square on the board
//              it is a special feature that allows the user that lands
//              on the square to gamble a fortune on the next dice roll.

public class gamble extends boardLocation
{
    
    private double gambleAmount;
    
    public gamble()
    // POST: a gamble object is created with the class member gambleAmount
    //       initialized to $0.0.
    {
        this.gambleAmount = 0;
    }
    
    public gamble(double gambleAmount)
    // PRE: gambleAmount must be initialized.
    // POST: a gamble object is created with the class member gambleAmount
    //       initialized to gambleAmount.
    {
        this.gambleAmount = gambleAmount;
    }
    
    public void setGambleAmount(double gambleAmount)
    // PRE: gambleAmount must be initialized.
    // POST: sets the class member gambleAmount to gambleAmount
    {
        this.gambleAmount = gambleAmount;
    }
    
    public double gambleMoney(double money, int num)
    // PRE:  money > 0,
    //       0 < num < 6
    // POST: returns the amount of money you have won or lost.
    //       if you guessed the number right, you win double 
    //       the money, else you loose double the money.
    {
        int randNum = ((int)Math.random() * 7);
       
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
    public String[] getPossibleActions(player player)
    {
        if(player.getMoney() > 0)
        {
            possibleActions[0] = "'G' Gamble atleast $1";
        }
        else
        {
            possibleActions[0] = "";
            // end game.
        }
        return possibleActions;
    }
    
    @Override
    public boolean performAction(player thePlayer, player theBank, char choice)
    {
        
        return false;
    }

}
