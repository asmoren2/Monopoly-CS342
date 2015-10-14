
public class utility extends property
{
   //Description:
   //Electric Company, Cost: 150, Tile: 12
   //Water Works, Cost : 150, Tile: 28

   public utility()
   //POST:
   {
	   this.purchaseCost = 150;
       
   }//Default Constructor

   public utility(String name, int spacesFromGo)
   {
      this.nameOfLocation = name;
      this.spacesFromGo = spacesFromGo;
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
