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
}
