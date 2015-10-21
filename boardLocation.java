// Programmer:  Adolfo Moreno
// Assignment:  Monopoly
// Date:        October, 14 2015
// Description: This class represents a player in the game.
//              The player holds money, and property information.
public class player
{
  private double money; //The amount of money that the player has
  private int numRailroad; //Number of railroads that the player owns
  private int numUtility; //Number of utilities that the player owns
  private int numLots;    // Number of lots a player owns.
  private int numProperties; // The number of property a player owns.
  private int spaceFromGo; //Number of spaces the player is from Go tile
  private String playerToken; //Differentiates the player from
  private int diceLand;      // The value of the dice as it lands.
  private property [] propertyList;

public player()
// POST: Sets a new board location for the specific player.
{
  this(1500, 0," ");
}

public player(double money,int spaceGo, String token)
//PRE : 0 < location < 41; location is in block.
{
  this.money = money;
  this.spaceFromGo = spaceGo;
  this.playerToken = token;
  this.propertyList = new property [28];
  this.numProperties = 0;
  this.numLots = 0;
  this.numRailroad = 0;
  this.numUtility = 0;
}

public void setBoardLocation(int location)
//PRE: Assume 0 < location < 41, location is in block
//POST: Set a new board location for the specific player.
{
   this.spaceFromGo = location;
}
public int getNumLots()
// POST: FCTVAL == The number of lots a player owns.
{
    return this.numLots;
}
public double getMoney()
// POST: FCTVAL == The amount of money a player has.
{
  return this.money;
}

public int getBoardLocation()
//POST: FCTVAL == The current location of the player.
{
   return this.spaceFromGo;
}

public int getNumberRailroad()
// POST: FCTVAL == The number of railroads a player owns.
{
  return this.numRailroad;
}

public int getNumberUtilities()
// POST: FCTVAL == The number of utilities a player has.
{
  return this.numUtility;
}

public int getDiceLand()
//POST: FCTVAL == The value of the dice for the latest turn.
{
    return this.diceLand;
}

public boolean canImprove(lot[] improvableLots)
// PRE:  current player is initialized
// POST: FCTVAL = false if a suitable property is found within the array of
//                improvableLots
//       FCTVAL = true whenever we find a suitable property within the array of
//                improvableLots
{
    for(lot aLot : improvableLots)
    {
        if (aLot != null)
            return true;
    }

    return false;
}

public void payRent(player creditor, double rent)
{
    creditor.addMoney(rent); // give money
    money-= rent;            // pay the rent
}

public lot [] getImprovingLots()
//POST: FCTVAL = canBeImproved: lots that can Be Improved
//      FCTVAL = null
{
    int propertyLength;    // propertyLength stores the length of the property array;
    int oneProperty;       // oneProperty stores an index when traversing through propertis
    int improveIndex;      // improveIndex is the index for populating our array of
                           //   improve enabled lots
    double improvementCost;// improvementCost holds the cost of improvement for a given lot
    double playerBalance;  // playerBalance holds the current balance for a player
    boolean hotelStatus;   // holds the hotel status for a given property during iteration

    lot [] canBeImproved;  //An array to be populated with the properties that are
                           //   available for improvement.

    improveIndex = 0;
    canBeImproved = new lot [28];
    playerBalance = this.money;
    propertyLength = propertyList.length;

    for(oneProperty = 0; oneProperty < propertyLength; oneProperty++)
    {

        if(canBeImproved[oneProperty] != null) //verify that the current property
                                               //   is not null
        {
            if (canBeImproved[oneProperty] instanceof lot) //make sure that this
                                                           //   property is a lot
            {
                hotelStatus = ((lot)propertyList[oneProperty]).getHotel();
                improvementCost = ((lot) propertyList[oneProperty]).getImproveCost();

                if(hotelStatus == false)   //Make sure there is no hotel on
                                           //The property
                {
                    if(improvementCost < playerBalance) //Make sure that this property
                                                        //   is affordable for player
                    {
                        canBeImproved[improveIndex] = canBeImproved[oneProperty];
                        improveIndex++;
                    }
                }
            }
        }
    }


    return canBeImproved;
}

public void addMoney(double amount)
// PRE: money must be initialized, money is in dollars.
// POST: adds money to member variable money. If money is a
//    negative value, then we subtract from the member variable money.
{
   this.money += amount;
}

public void addNumRailroad()
// POST: adds one to the class member numRailroad.
{
   this.numRailroad += 1;
}

public void addNumUtility()
//POST: Adds one to the class member numUtilities.
{
   this.numUtility += 1;
}

public void bankrupt()
// POST: Will reduce the amount of money a player has to $0.
{
   this.money = 0;
}

public int throwDice()
// POST: returns a random number between 1 and 12.
{
    return (int)Math.random()*13;
}

public String getToken()
// POST: FCTVAL ==  the token of the player.
{
    return this.playerToken;
}

public void buyProperty(property property)
// PRE:  property must be initialized
// POST: adds the property to the list of property a player owns.
//       increments the property counters as needed.
{
   propertyList[numProperties] = property;
   numProperties++;

   if(property instanceof lot)              // if the property is a lot
   {                                        // add one to numLots.
       numLots++;
   }
   else if (property instanceof railroad)   // if the property is a railroad
   {                                        // add one to numRailroad.
       numRailroad++;
   }
   else if (property instanceof utility)    // if the property is a utility
   {                                        // add one to the numUtility.
       numUtility++;
   }
   property.isOwned = true;
}
public boolean hasSellableProperty()
// POST: returns true if the player has sellable property
{
    for(property curr: propertyList)
    {
        if(curr instanceof lot && ((lot)curr).getNumHouses() > 0)
            return true;
    }
    return false;
}
public property [] getPropertyList()
// POST: FCTVAL == the list of properties a player owns.
{
	return this.propertyList;
}
@Override
public String toString()
{
   return "Player: " + playerToken + "\n" + "Has $"+money +"\n"+ "Railroads owned: "+
         numRailroad + "\n" + "Utilities owned: " + numUtility + "\n" + "Board location: " +
         spaceFromGo + "\n" + "Properties Owned:" +"\n"+  getLocationsOnwed().toString();
}

public String getLocationsOnwed(){
	
	String[] nameList = new String[numProperties];
	String finString = "";
	// Initializing the nameList
	for(int i = 0; i < numProperties; i++)
	{
		nameList[i] = "";
	}
	for(int i = 0; i < numProperties; i++)
	{
		nameList[i] = propertyList[i].nameOfLocation + "\n";
	}
	// Making a string
	for(int i = 0; i < numProperties; i++)
	{
		finString += nameList[i];
	}
	return finString;
}  

public boolean sell (double amount)
// PRE: amount >= 0
// POST: FCTVAL == returns true, if the user has sold enough houses
//       to pay the amount, else return false.
{
    double recoveredMoney = 0;
    for(property currProperty: propertyList)            // Go through the properties
    {
        if(currProperty instanceof lot)                 // for each lot check if there
        {                                               // you can build a house.
            if(((lot)currProperty).getHotel() == true)
            {
                recoveredMoney += ((lot)currProperty).sellHotel();
            }
            else if (((lot)currProperty).getNumHouses() > 0)
            {
                // keep selling houses until you have enough money to pay off the creditor.
                while(recoveredMoney <= amount && ((lot)currProperty).getNumHouses() > 0)
                {
                    recoveredMoney += ((lot)currProperty).sellHouse();
                }
            }
            if(recoveredMoney > amount)
            {
                return true;
            }
        }
    }
    return false;
}
}//End Player Class
