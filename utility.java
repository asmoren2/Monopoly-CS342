// Programmer:  Adolfo Moreno, Harsh Patel, Christian Valaderas
// Assignment:  Monopoly
// Date:        October, 14 2015
// Description: This class represents a utility on the board location.


public class utility extends property
{
   public utility()
   // POST: a default utility is created with the purchase
   //      cost of 150.
   {
       super();
       this.purchaseCost = 150;
   }

   public utility(String nameOfLocation, int spacesFromGo)
   // PRE: nameOfLocation and spacesFromGo must be initialized.
   // POST: a default utility is created with the name
   //       nameOfLocation and it is spacesFromGo away from
   //       the go block.  It cost purchaseCost.
   {
      super(nameOfLocation,spacesFromGo);
      this.purchaseCost = 150.0;
   }

   public double caltUtilRent(int n, int diceNumber)
   // n and diceNumber must be initialized.
   {
      double rentCost;
      
      rentCost = 0;
      
      if(n == 1)            // Calculating rent for utility.
      {
         rentCost = (4*diceNumber);
      }
      else if(n == 2)
      {
         rentCost = (10*diceNumber);
      }

      return rentCost;
   }

   @Override
   public String [] getPossibleActions(player thePlayer)
   // PRE: player must be initialized.
   // POST: FCTVAL == possibleActions, an array of Strings containing all
   //                 the possible actions a player can perform at a utiliy
   //                 is returned.  
   //                 An array of booleans that represents the same thing is also 
   {
       double rent = 0; 
       // End game
       actionStatus[4] = true;
       possibleActions[4] = PACTIONS[4];
           
       // resetting the actions.
       for(boolean action: actionStatus)
       {
           action = false;
       }
       
       if(isOwned == false                          // This Location is not owned
          && thePlayer.getMoney() > purchaseCost)         
       {
           actionStatus[1] = true;                  // buy
           possibleActions[1] = PACTIONS[1];
           
           actionStatus[0] = true;                  // Do nothing
           possibleActions[0] = PACTIONS[0];
       }
       else if(isOwned == true 
               && this.owner == thePlayer           // This Location is already owned
               && thePlayer.canImprove(thePlayer.getImprovingLots()))    
       {
               actionStatus[1] = false;                  // Do not buy owned utilities
               possibleActions[1] = PACTIONS[1];
               actionStatus[3] = true;
               possibleActions[3] = PACTIONS[3];
       }
       else if (isOwned == true && this.owner != thePlayer
                && thePlayer.getMoney() > rent)     // owned by another player
       {
           actionStatus[1] = false;                  // Do not buy owned utilities
           possibleActions[1] = PACTIONS[1];
           rent = caltUtilRent(owner.getNumberRailroad(), thePlayer.getDiceLand());
           thePlayer.payRent(owner, rent);
       }
       else if(thePlayer.getMoney() <= 0            // If can't pay rent and can sell
               && thePlayer.hasSellableProperty())  // houses.
       {
           actionStatus[2] = true;      // Sell
           possibleActions[2] = PACTIONS[2];
       }

       return possibleActions;
   }

}
