//Programmers:  Christian M. Valladares, Harsh Patel, Adolfo Moreno
//Assignment:  Monopoly
//Date:        October 8, 2015
//Description: This class represents the chance and community chest locations.
//             The class will simply generate an amount to be deducted or
//             accrued in a player's account; Within the player defined bounds.
//
public class cardSquare extends boardLocation
{
   private double bound;        // bound determines the bounds to generate the random
                                // values
   private double valueReturned;// valueReturned holds the last value returned for
                                // the cardSquare
   private boolean isPositive;  // isPositive determines whether value returned will
                                // be added or subtracted from the user account

   public cardSquare()
   // POST: cardSquare creates an instance of cardSquare, where bound = 200,
   //       isPositive = true, and valueReturned = 0
   {
       super();
       bound = 200.00;
       isPositive = true;
       valueReturned = 0;
   }

   public cardSquare(String nameOfLocation, int spacesFromGo, double bound)
   // POST: cardSquare creates an instance of cardSquare, where its super class is
   //       constructed.  Further, bound = bound,  and isPositive = true by default
   //       valueReturned = 0
   {
       super(nameOfLocation,spacesFromGo);
       this.bound = bound;
       this.isPositive = true;
       valueReturned = 0;
   }

   public double getChanceValue ()
   // POST: FCTVAL = -bound <= x <= bound
   {
       isPositive = (Math.random() > .5);  // Update the isPositive value to a randomly
                                           // generated boolean value, considering that
                                           // Math.random generates decimals 0 < x < 1



       if (isPositive == true)                    // if isPositive is true, return
       {
           return (Math.random() * bound);        // a positive random value
       }

       else                                       // if isPositive is negative, return a
       {
           return (Math.random() * bound) * (-1); // negative random value
       }
   }

   public String toString()
    // POST:  returns a string representing the object of the cardSquare class
    {
        return "The name of this location is : " + nameOfLocation
              +"\nThe location is " + spacesFromGo +" away from go."
              +"\nThis location is a card square.";
    }

    @Override
    public String [] getPossibleActions(player player)
    // PRE: player must be initialized.
    // POST: FCTVAL == possibleActions, an array of Strings containing all
    //                 the possible actions a player can perform at a cardSquare
    //                 is returned.  
    //                 An array of booleans that represents the same thing is also 
    //                 altered in order to be used in the GUI.
    {

       // give the money to the player.
       player.addMoney(getChanceValue());

       actionStatus[4] = true;                                 // end game
       possibleActions[4] = PACTIONS[4];

       actionStatus[0] = true;                                 // Do nothing
       possibleActions[0] = PACTIONS[0];
       
       if(player.getMoney() > 0                                // if the player has 
          && player.canImprove(player.getImprovingLots()))     // money and can improve 
                                                               // lots
       {
           actionStatus[3] = true;
           possibleActions[3] = PACTIONS[3];
       }
       else if(player.getMoney() < 0                           // player has no money 
               && player.hasSellableProperty())                // and can sell lots
       {
           actionStatus[2] = true;                             // allow to sell
           possibleActions[2] = PACTIONS[2];
       }

       return possibleActions;
    }
}
