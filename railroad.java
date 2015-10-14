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
      this.purchaseCost = 200;
   }//End Default Constructor

//Method 1. Pass as String
//Method 2. declare a number for each radilRoad type and pass in
//          a number having already given it a signification.
   public railroad(String name, int spaceGo)
   //POST: Create a railroad object with a cost of 200 and a tile number
   {
      this.purchaseCost = 200;
      this.nameOfLocation = name;
      this.spacesFromGo = spaceGo;
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
}
