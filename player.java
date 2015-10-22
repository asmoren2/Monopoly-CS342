// Programmer:  Adolfo Moreno, Harsh Patel, Christian Valaderas
// Assignment:  Monopoly
// Date:        October, 14 2015
// Description: This class represents a player in the game.
//              The player holds money, and property information.
public class player
{
  private double money;             // The amount of money that the player has
  private int numRailroad;          // Number of railroads that the player owns
  private int numUtility;           // Number of utilities that the player owns
  private int numLots;              // Number of lots a player owns.
  private int numProperties;        // The number of property a player owns.
  private int spaceFromGo;          // Number of spaces the player is from Go tile
  private String playerToken;       // Differentiates the player from
  private int diceLand;             // The value of the dice as it lands.
  private boardLocation current;    // The current board location.
  
  private boolean inDebt;           // isBankrupt determines whether a player is 
                                    //    in debt 
  
  private property [] propertyList; // List of the properties the player owns.

  lot [] canBeSold;      //An array to be populated with the properties that are
                         //   available for liquidation.
  lot [] canBeImproved;              // An array to be populated with the properties 
                                     // that are available for improvement.

    public player()
    // POST: Sets a new board location for the specific player.
    {
      this(1500, 0," ");
    }
    
    public player(double money,int spaceGo, String token)
    // PRE : 0 < location < 41; location is in block.
    // POST: sets class member money to money
    //       sets class member spaceFromGo to spaceGo
    //       sets class member playerToken to token
    //       initializes class member propertyList
    //       sets class member numProperties to 0
    //       sets class member numLots to 0
    //       sets class member numRailroad to 0
    //       sets class member numUtility to 0
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
    
    public void setCurrentLocation(boardLocation current)
    // PRE: current must be initialized
    // POST: sets the class member current to current.
    {
        this.current = current;
    }
    
    public boardLocation getCurrentLocation()
    // POST: FCTVAL == current location of the player
    {
        return this.current;
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
        throwDice();
        return this.diceLand;
    }
    
    public boolean canImprove(lot[] improvableLots)
    // PRE:  current player is initialized
    // POST: FCTVAL = false if a suitable property is found within the array of
    //                improvableLots
    //       FCTVAL = true whenever we find a suitable property within the array of
    //                improvableLots
    {
        for(lot aLot : improvableLots)          // finding the improvable lots.
        {
            if (aLot != null)
                return true;
        }
    
        return false;
    }
    
    public void payRent(player creditor, double rent)
    // PRE: creditor and rent must be initialized.
    // POST: pays the rent to creditor and re-calculates 
    //       the money in the players account.
    {
        creditor.addMoney(rent); // give money
        money-= rent;            // pay the rent
    }
    
    public lot [] getImprovingLots()
    //POST: FCTVAL = canBeImproved: lots that can Be Improved
    {
        int propertyLength;    // propertyLength stores the length of the 
                               // property array
        int oneProperty;       // oneProperty stores an index when traversing
                               // through propertis
        int improveIndex;      // improveIndex is the index for populating our 
                               // array of improve properties.
        double improvementCost;// improvementCost holds the cost of improvement 
                               // for a given lot
        double playerBalance;  // playerBalance holds the current balance for a player
        boolean hotelStatus;   // holds the hotel status for a given property during
                               // iteration
    
    
        improveIndex = 0;                           
        canBeImproved = new lot [28];       //Reset the canBeImproved array by reallocating
        playerBalance = this.money;         
        propertyLength = this.propertyList.length;
    
        // going through the lots and finding the improvable lots.
        for(oneProperty = 0; oneProperty < propertyLength; oneProperty++)
        {
    
            if(this.propertyList[oneProperty] != null) //verify that the current property
                                                       //   is not null
            {
                if (this.propertyList[oneProperty] instanceof lot) 
                                                        // make sure that this
                                                        // property is a lot
                {
                    hotelStatus = ((lot)this.propertyList[oneProperty]).getHotel();
                    improvementCost = ((lot) this.propertyList[oneProperty]).
                                        getImproveCost();
    
                    if(hotelStatus == false)            //Make sure there is no hotel on
                                                        //The property
                    {
                        if(improvementCost < playerBalance) //Make sure that this property
                                                            //is affordable for player
                        {
                            canBeImproved[improveIndex] = ((lot)propertyList[oneProperty]);
                            System.out.println(canBeImproved[improveIndex].toString());
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
public lot [] getSellableLots()
//POST: FCTVAL = canBeSold: lots that can Be Sold
//      FCTVAL = canBeSold = null for all locations when no location meets the
//                           sellable case
{
  int propertyLength;    // propertyLength stores the length of the property array;
  int oneProperty;       // oneProperty stores an index when traversing through properties
  int sellingIndex;      // sellingIndex is the index for populating our array of
                         //   sell enabled lots
  
  double sellPrice;      
  double numHouses;      // improvementCost holds the cost of improvement for a given lot
  double playerBalance;  // playerBalance holds the current balance for a player
  boolean hotelStatus;   // holds the hotel status for a given property during iteration
  
  property singleProperty;

  sellingIndex = 0;                           
  canBeSold = new lot [28];       //Reset the canBeImproved array by reallocating
  playerBalance = this.money;         
  propertyLength = this.propertyList.length;

  for(oneProperty = 0; oneProperty < propertyLength; oneProperty++)
  {
      singleProperty = this.propertyList[oneProperty];  //Get reference to the 
                                                        //   current property

      if(singleProperty != null)                 //verify that the current property
                                                 //   is not null
      {
          if (singleProperty instanceof lot)     //make sure that this
                                                 //   property is a lot
          {
              
              //Update whether this place has a hotel, and the sell price, which will
              //    be half of the buy price.
              
              hotelStatus = ((lot) singleProperty).getHotel();
              sellPrice = ((lot) singleProperty).getImproveCost();
              numHouses = ((lot) singleProperty).getNumHouses();
              
              if(hotelStatus == true || numHouses > 0)  //Make sure that singleProperty
                                                        //   has some houses or a hotel
              {
                  
                  canBeSold[sellingIndex] = (lot) singleProperty;   // Populate the sellable  
                                                                    //    location
                  System.out.println(canBeSold[sellingIndex].toString());
                  sellingIndex++;
              }
 
          }
      }
  }


  return canBeSold;
}

public boolean canContinue(lot[] sellableLots)
// 
{
    int lotBound;
    
    lotBound = sellableLots.length;
    public void addNumRailroad()
    // POST: adds one to the class member numRailroad.
    {
       this.numRailroad += 1;
    }
    
    if(this.money < 0)           // Verify the user balance to check if he/she is in debt
        inDebt = true;      
    public void addNumUtility()
    //POST: Adds one to the class member numUtilities.
    {
       this.numUtility += 1;
    }
    
    else                    // If balance is not negative,  player is not in debt
        return true;
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
        for(int i = 0;i < lotBound; i++)
        {
           if(sellableLots[i] != null)  // If the current lot is not null
           {
               return true;             // return true if we have at least on
                                        //   one sellable lot that is not null
           }
        }
        return this.playerToken;
    }
    
    return false;                       // If the player is not in debt, then
                                        //    he/she may continue without issue
   
}

public boolean canSell(lot[] sellableLots)
//PRE:  current player is initialized
//POST: FCTVAL = false if a suitable property is not found within the array of
//               sellableLots
//    FCTVAL = true whenever we find a suitable property within the array of
//             sellableLots
{
 for(lot aLot : sellableLots)
 {
     if (aLot != null)
         return true;
 }

 return false;
}

    public void buyProperty(property property)
    // PRE:  property must be initialized
    // POST: adds the property to the list of property a player owns.
    //       increments the property counters as needed.
    {
        System.out.println("In buyProperty()");
       propertyList[numProperties] = property;
       numProperties++;
       property.owner = this;
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
       money -= property.getPurcaseCost();      // recalculate money and ownership.
       property.isOwned = true;
       
    }
    return false;
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
       return "Player: " + playerToken + "\n" + "Has $"+ Math.floor(money) +"\n"+ "Railroads owned: "+
             numRailroad + "\n" + "Utilities owned: " + numUtility + "\n" + "Board location: " +
             current.nameOfLocation + "\n" + "Properties Owned:" +"\n"+  getLocationsOnwed().toString();
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
                    // keep selling houses until you have enough money to 
                    // pay off the creditor.
                    while(recoveredMoney <= amount 
                          && ((lot)currProperty).getNumHouses() > 0)
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
}
