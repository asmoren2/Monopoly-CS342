// Programmer:  Adolfo Moreno, Christian Valaderas, Harsh Patel
// Assignment:  Monopoly
// Date:        October, 14 2015
// Description: This class represents a railroad block on the
//              monopoly game.
public class railroad extends property
{
   public railroad()
   //POST: Create a railroad object with defaults set
   {
      super();
      this.purchaseCost = 200;
   }

   public railroad(String nameOfLocation, int spacesFromGo)
   // PRE: nameOfLocation and spacesFromGo must be initialized.
   // POST: Create a railroad object with a cost of 200 and a tile number.
   {
      super(nameOfLocation,spacesFromGo);
      this.purchaseCost = 200;
   }

   public double calcRent(int n)
   // PRE: n is the number of railroads a player owns. It is assumed that the
   //     player can own no less than 0 and no more than 4.
   // POST: The amount rent do is returned
   {
      double rentPayable;
      
      rentPayable = 0.0;
                                          // Calculating the rent.
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
   // POST: A string representation of the railroad class.
   {
       return super.toString() + "\nThe purchase cost is : " + purchaseCost;
   }

   @Override
   public String [] getPossibleActions(player thePlayer)
   // PRE: player must be initialized.
   // POST: FCTVAL == possibleActions, an array of Strings containing all
   //                 the possible actions a player can perform at a railroad
   //                 is returned.  
   //                 An array of booleans that represents the same thing is also 
   //                 altered in order to be used in the GUI.
   {
       double rent = 0;
       // resetting the actions.
       for(boolean action: actionStatus)
       {
           action = false;
       }
       // End game
       actionStatus[4] = true;
       possibleActions[4] = PACTIONS[4];
                                                    // if this Location is not owned
       if(isOwned == false && thePlayer.getMoney() > purchaseCost)  
       {
           actionStatus[1] = true;                  // buy
           possibleActions[1] = PACTIONS[1];

           actionStatus[0] = true;                  // Do nothing
           possibleActions[0] = PACTIONS[0];

       }                                            // if this Location is already owned
                                                    // by the player
       else if(isOwned == true && this.owner == thePlayer
               && thePlayer.canImprove(thePlayer.getImprovingLots()))    
       {
               actionStatus[1] = false;  // do not buy a pre-Owned railRoad
               possibleActions[1] = PACTIONS[1];
               actionStatus[3] = true;
               possibleActions[3] = PACTIONS[3];
       }
       else if (this.owner != thePlayer
                && thePlayer.getMoney() > rent)     // if owned by another player
       {
           actionStatus[1] = false;  // do not buy a pre-Owned railRoad
           possibleActions[1] = PACTIONS[1];
           rent = calcRent(owner.getNumberRailroad());
           thePlayer.payRent(owner, rent);
       }
       else if(thePlayer.hasSellableProperty())
       {
           actionStatus[2] = true;                  // Sell
           possibleActions[2] = PACTIONS[2];
       }

       return possibleActions;
   }

}
