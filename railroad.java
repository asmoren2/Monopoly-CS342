// Programmer:  Adolfo Moreno
// Assignment:  Monopoly
// Date:        October, 14 2015
// Description: This class represents a railroad block on the
//              monopoly game.
public class railroad extends property
{
//Railroad class
//Short Line Railroad = cost 200, Tile 35
//Reading Railroad = cost 200, Tile 5
//Pennsylvania Railroad = cost 200, Tile 15
//B&O Railroad = cost 200, Tile 25

   //Data Dictionary

   public railroad()
   //POST: Create a railroad object with defaults set
   {
      super();
      this.purchaseCost = 200;
   }//End Default Constructor

//Method 1. Pass as String
//Method 2. declare a number for each radilRoad type and pass in
//          a number having already given it a signification.
   public railroad(String nameOfLocation, int spacesFromGo)
   //POST: Create a railroad object with a cost of 200 and a tile number
   {
      super(nameOfLocation,spacesFromGo);
      this.purchaseCost = 200;
   }

   public double calcRent(int n)
   //PRE: n is the number of railroads a player owns. It is assumed that the
   //    player can own no less than 0 and no more than 4.
   //POST: The amount rent do is returned
   {
      double rentPayable = 0.0;
      if( n == 1)
      {
         rentPayable = 25.0;
      }
      else if( n == 2)
      {
         rentPayable = 50.0;
      }
      else if(n == 3)
      {
         rentPayable = 100.0;
      }
      else if(n == 4)
      {
         rentPayable = 200.0;
      }
      return rentPayable;
   }//End calcRent

   @Override
   public String toString()
   {
       return super.toString() + "\nThe purchase cost is : " + purchaseCost;
   }

   @Override
   public String [] getPossibleActions(player thePlayer)
   // PRE: player must be initialized
   // POST: FCTVAL == A string array representing all the actions
   //                 a player can currently perform is returned.
   {
       double rent = 0;
       // resetting the actoins.
       for(boolean action: actionStatus)
       {
           action = false;
       }
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
               actionStatus[1] = false;  // do not buy a pre-Owned railRoad
               possibleActions[1] = PACTIONS[1];
               actionStatus[3] = true;
               possibleActions[3] = PACTIONS[3];
       }
       else if (this.owner != thePlayer
                && thePlayer.getMoney() > rent)     // owned by another player
       {
           actionStatus[1] = false;  // do not buy a pre-Owned railRoad
           possibleActions[1] = PACTIONS[1];
           rent = calcRent(owner.getNumberRailroad());
           thePlayer.payRent(owner, rent);
       }
       else if(thePlayer.hasSellableProperty())
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
