
public class utility extends boardLocation
{
   //Description:
   //Electric Company, Cost: 150, Tile: 12
   //Water Works, Cost : 150, Tile: 28

   private static final uCost;//represents the cost to own 1 utility

   public utility()
   //POST:
   {

   }//Default Constructor

   public utility(String name)
   {
      super();
      this.uCost = 150.0;
   }//input Constructor

   public void caltUtilRent(int n, int diceNumber)
   {
      if(n == 1)
      {
         uCost = (4*diceNumber);
      }
      else if(n == 2)
      {
         uCost = (10*diceNumber);
      }
   }


}
