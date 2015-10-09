public class railroad extends boardLocation
{
//Railroad class
//Short Line Railroad = cost 200, Tile 35
//Reading Railroad = cost 200, Tile 5
//Pennsylvania Railroad = cost 200, Tile 15
//B&O Railroad = cost 200, Tile 25

   //Data Dictionary
   private static final rCost;//represents the total cost to own 1 railroad

   public void railroad()
   //POST:
   {

   }//End Default Constructor

//Method 1. Pass as String
//Method 2. declare a number for each radilRoad type and pass in
//          a number having already given it a signification.
   public void railroad(String rType)
   //POST: Create a railroad object with a cost of 200 and a tile number
   {
      super();
   }

   public double calcRent(int n)
   //PRE: n is the number of radilRoads a player owns. It is assumed that the
   //    player can own no less than 0 and no more than 4.
   {
      double rentPayable = 0.0;
      if(int n == 1)
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
   }//End calcRent
   public double getRailCost()
   //POST: FCTVAL == the class member rCost, it represents the cost to own
   //      the railroad.
   {
      return rCost;
   }
}
