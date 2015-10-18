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

   
   public String [] getPossibleActions(player thePlayer)
   // PRE: player must be initialized
   // POST: FCTVAL == A string array representing all the actions
   //                 a player can currently perform is returned.
   {                
       possibleActions = new String[5];    // Holds all possible actions
       
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
               possibleActions[2] =  "'I' -> Improve another Property (not implemented)";
               actionStatus[2] = true;
               if(actionStatus[2] == true)
               {
            	   thePlayer.canImprove(thePlayer.getImprovingLots());
               }
           }
           
           else if (this.owner != thePlayer)      // owned by another player 
           {               
               thePlayer.addMoney(this.calcRent(this.getOwner().getNumberRailroad())* -1);
               this.getOwner().addMoney(this.calcRent(this.getOwner().getNumberRailroad()));
           }
           if(thePlayer.getMoney() <= 0){
        	   if(thePlayer.sell(thePlayer.getMoney()*-1) == false){
        		   System.out.println("Game has ended you lost");
        		   System.exit(0);
        	   }
           }
   
       }
       
       return possibleActions;
   }

   
}
