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
       possibleActions = new String[5];    // Holds all possible actions
       int utilitiesOwned;                  // Number of utilities owned by a 
                                            //   the owner of this particular
                                            //   utility box
       
       if(isOwned == false)         // This Location is not owned
       {
           possibleActions[0] = "'N'-> Do Nothing";
           actionStatus[0] = true;
           possibleActions[1] = "'B' -> Buy";
           actionStatus[1] = true;
       }
       
       else if(isOwned == true)    // This Location is already owned
       {
           if (this.owner == thePlayer) // owned by the current player,
           {
               possibleActions[0] = "'N'-> Do Nothing";
               actionStatus[0] = true;
               possibleActions[2] =  "'I' -> Improve another Property";
               actionStatus[2] = true;
               if(actionStatus[2] == true)
               {
            	   thePlayer.canImprove(thePlayer.getImprovingLots());
               }
           }
       }
           
           else if (this.owner != thePlayer)      // owned by another player 
           {               
        	   thePlayer.addMoney(this.caltUtilRent(this.getOwner().getNumberUtilities(), thePlayer.getDiceLand())* -1);
               this.getOwner().addMoney(this.caltUtilRent(this.getOwner().getNumberUtilities(), thePlayer.getDiceLand()));
           }
         
       
       if(thePlayer.getMoney() <= 0)
       {
    	   if(thePlayer.sell(thePlayer.getMoney()*-1) == false)
    	   {
    		   System.out.println("Game has ended you lost");
    		   System.exit(0);
    	   }
       }
       return possibleActions;
   }
   
   
}
