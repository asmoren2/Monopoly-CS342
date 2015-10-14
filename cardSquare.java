//Programmer:  Christian M. Valladares
//Assignment:  Monopoly
//Date:        October 8, 2015
//Description: This class represents the chance and community chest locations.
//             The class will simply generate an amount to be deducted or
//             accrued in a player's account.  within the player defined bounds
//
public class cardSquare extends boardLocation
{
   private double bound;        // bound determines the bounds to generate the random
                                //   values
   private double valueReturned;// valueReturned holds the last value returned for
                                //   the cardSquare
   private boolean isPositive;  // isPositive determines whether value returned will
                                //   be added or subtracted from the user account

   public cardSquare()
   // POST: cardSquare creates an instance of cardSquare, where bound = 200,
   //       isPositive = true, and valueReturned = 0
   {
       super();
       bound = 200.00;
       isPositive = true;
       valueReturned = 0;
   }

   public cardSquare(String nameOfLocation, int spacesFromGo, double bound, boolean isPositive)
   // POST: cardSquare creates an instance of cardSquare, where its super class is
   //       constructed.  Further, bound = bound,  and isPositive = isPositive
   //       valueReturned = 0;
   {
       this.nameOfLocation = nameOfLocation;
       this.spacesFromGo = spacesFromGo;
       this.bound = bound;
       this.isPositive = isPositive;
       valueReturned = 0;
   }

   public double getChanceValue ()
   // POST: FCTVAL = -bound <= x <= bound
   {
       isPositive = (Math.random() > .5);  // Update the isPositive value to a randomly
                                           //   generated boolean value, considering that
                                           //   Math.random generates decimals 0 < x < 1



       if (isPositive == true)                  // if isPositive is true, return
          return (Math.random() * bound);       //  a positive random value

       else                                     // if isPositive is negative, return a
         return (Math.random() * bound) * (-1); //   negative random value

   }

   public String[] getPossibleActions()
   // POST: FCTVAL = (string) "Get a chance value applied to your account"
   {
       super.possibleActions[0] = "Get a chance value applied to your account";
       return possibleActions;
   }

   public String toString()
// POST:  returns a string representing the object of the cardSquare class
//
{
    return "The name of this location is : " + nameOfLocation
          +"The location is " + spacesFromGo +" away from go."
          +"This location is a card square.";
}
}
