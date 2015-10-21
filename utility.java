// Programmer:  Adolfo Moreno
// Assignment:  Monopoly
// Date:        October, 14 2015
// Description: This class represents a utility on the board location.


public class utility extends property
{
   //Description:
   //Electric Company, Cost: 150, Tile: 12
   //Water Works, Cost : 150, Tile: 28

   public utility()
   // POST: a default utility is created with the purchase
   //      cost of 150.
   {
       super();
       this.purchaseCost = 150;
   }//Default Constructor

   public utility(String nameOfLocation, int spacesFromGo)
   // POST: a default utility is created with the name
   //       nameOfLocation and it is spacesFromGo away from
   //       the go block.  It cost purchaseCost.
   {
      super(nameOfLocation,spacesFromGo);
      this.purchaseCost = 150.0;
   }//input Constructor

   public double caltUtilRent(int n, int diceNumber)
   {
      double rentCost = 0;
      if(n == 1)
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
   // PRE: player must be initialized
   // POST: FCTVAL == A string array representing all the actions
   //                 a player can currently perform is returned.
   {
       double rent = 0; 
       // End game
       actionStatus[4] = true;
       possibleActions[4] = PACTIONS[4];

       if(isOwned == false && thePlayer.getMoney() > purchaseCost)         // This Location is not owned
       {
           actionStatus[1] = true;  // buy
           possibleActions[1] = PACTIONS[1];
           actionStatus[0] = true;  // Do nothing
           possibleActions[0] = PACTIONS[0];

       }
       else if(isOwned == true && this.owner == thePlayer
               && thePlayer.canImprove(thePlayer.getImprovingLots()))    // This Location is already owned
       {
               actionStatus[3] = true;
               possibleActions[3] = PACTIONS[3];
       }
       else if (isOwned == true && this.owner != thePlayer
                && thePlayer.getMoney() > rent)     // owned by another player
       {
           rent = caltUtilRent(owner.getNumberRailroad(), thePlayer.getDiceLand());
           thePlayer.payRent(owner, rent);
       }
       else if(thePlayer.getMoney() <= 0
               && thePlayer.hasSellableProperty())
       {
           actionStatus[2] = true;      // Sell
           possibleActions[2] = PACTIONS[2];
       }

       return possibleActions;
   }

@Override
public boolean performAction(player thePlayer, player theBank, char choice) {
    // TODO Auto-generated method stub
    return false;
}


}
