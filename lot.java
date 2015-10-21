// Programmer:  Harsh Patel
// Assignment:  Monopoly
// Date:        October, 14 2015
// Description: This class represents a lot on the board,
//              the user can then buy or build houses on the lot.
public class lot extends property
{
    private String color;           // The color of the district that the
                                    // lot belongs to.
    private String hasHotel;        // Represents a string that changes based
                                    // on the hotel status of the lot.
    private double improveCost;     // Cost to build a house, or upgrade.
    private int numHouses;          // Number of houses in the lot.
    private boolean isHotel;        // Boolean representing if a hotel is created.

    public lot()
    // POST: a default lot is created, with no district, 
    //       no color, and no name.  It is 0 spaces away from go,
    //       it has a cost of 0.0 and an improve cost of 0.0.
    //       It has 0 houses on it, and it is also not a hotel.
    //       It initialized the class member hasHotel to "does not have a"
    //       It also has a rent structure of rentStructure.
    {
        this("",0,"",0.0,0.0,0,new double [7]);
    }

    public lot(String nameOfLocation, int spacesFromGo, String color,
               double purchaseCost, double improveCost,
               int numHouses, double [] rentStructure)
    // PRE: district, color, nameOfLocation must be initialize.
    //      spaceFromGo >= 0
    //      purchaseCost >= 0.0, improveCost >= 0.0
    //      rentStructure must be initialized.
    // POST: A lot object is initialized with the
    //       class member district set to district,
    //       class member color set to color,
    //       class member nameOfLocation set to nameOfLocation,
    //       class member spacesFromGo set to spacesFromGo,
    //       class member purchaseCost set to purchaseCost,
    //       class member improveCost set to improve cost,
    //       class member rentStructure set to rentStructure,
    //       class member hasHotel to "does not have a",
    //       and the class member isHotel set to false.
    //       the class member actionStatus is instantiated
    //       the class member actionStatus[i] = false   for 0 < i < actionStatus.Length
    {
        super(nameOfLocation,spacesFromGo);
        this.color = color;
        this.purchaseCost = purchaseCost;
        this.improveCost = improveCost;
        this.rentStructure = rentStructure;
        this.hasHotel = "does not";
        this.isHotel = false;
        actionStatus = new boolean[20];

        // Initialize action Statuses to allFalse
        for(boolean aStatus : actionStatus)
            aStatus = false;


    }

    public double sellHouse()
    // PRE: The lot must have a house to sell.
    // POST:  decreases the number of houses a player has and returns the
    //        amount of money it cost to sell a house to the bank.
    {
        numHouses--;
        if(numHouses < 4)
        {
            isHotel = false;
            hasHotel = "does not";
        }
        return improveCost/2;
    }

    public int getNumHouses()
    // POST: returns the number of houses this lot has.
    {
        return this.numHouses;
    }

    public double sellHotel()
    // PRE: the lot must have a hotel to sell.
    // POST: gets rid of the hotel in the lot
    //       returns half the money it cost to build the hotels.
    {
        isHotel = false;
        hasHotel = "does not";
        numHouses = 0;
        return (improveCost*5)/2;
    }

    public boolean getHotel()
    // POST: FCTVAL == does the lot have a hotel.
    {
        return isHotel;
    }

    public void addNumHouses()
    // POST: adds one to the number of houses on this lot.
    {
        this.numHouses++;
    }

    public void makeHotel()
    // POST: resets the class member numHouses to 0 and
    //       sets the class member isHotel to be true.
    //       It also sets the class member hasHotel to "has one".
    {
        this.numHouses = 0;
        this.hasHotel = "has one";
        this.isHotel = true;
    }

    public double getImproveCost()
    // POST: FCTVAL == the improve cost of this class.
    {
        return this.improveCost;
    }

    public double getRent()
    // FCTVAL == The rent of the property, with numHouses houses on it.
    {
        return this.rentStructure[numHouses];
    }

    public String getColor()
    // POST: FCTVAL == the color group this lot belongs to.
    {
        return this.color;
    }

    @Override
    public String [] getPossibleActions(player player)
    // PRE: player must be initialized
    // POST: FCTVAL == A string array representing all the actions
    //                 a player can currently perform is returned.
    {
        /*
         * PACTIONS = {0:"Do Nothing", 1:"Buy",
                       2:"Sell", 3:"Improve Property",
                       4:"End Game"};    */
        // resetting the actoins.
        for(boolean action: actionStatus)
        {
            action = false;
        }
        
        boardLocation current = player.getCurrentLocation();
        double rent = 0.0;
        if(current instanceof lot)
        {
            rent = rentStructure[((lot) current).getNumHouses()];
        }
        
        actionStatus[4] = true;      // End game
        possibleActions[4] = PACTIONS[4];
        System.out.println("Location: " + nameOfLocation + isOwned);
        if(isOwned == false)            // If un-owned
        {
           if(player.getMoney() > this.purchaseCost)
           {
               actionStatus[1] = true;      // Buy
               possibleActions[1] = PACTIONS[1];
               actionStatus[0] = true;      // Do nothing
               possibleActions[0] = PACTIONS[0];
           }
           else
           {
               actionStatus[1] = false;
           }
        }
        
        else if (isOwned == true && this.owner != player
                && player.getMoney() > rent) //  if you don't own it
        {
           // pay rent automatic
           System.out.println(player.getToken() + "Paid rent:" + rent);
           player.payRent(owner, rent);
        }
        else if (isOwned == true && this.owner != player // Can't pay rent
                && player.getMoney() < rent
                && player.hasSellableProperty())
        {
            // Force to sell
            actionStatus[2] = true;
            possibleActions[2] = PACTIONS[2];
        }
        else if(isOwned == true && this.owner == player // Current player owns it
                && player.getMoney() > improveCost &&    // has money to improve
                player.canImprove(player.getImprovingLots()))// has improvable lots
        {
           actionStatus[3] = true;
           possibleActions[3] = PACTIONS[3];
        }
        return possibleActions;
    }

    @Override
    public String toString()
    // FCTVAL == a string representation of this lot.
    {
        return super.toString() +
               "\nThis property belongs to the color group: " + color +
               ".\nThis property has " + numHouses + " houses." +
               "\nIt costs " + improveCost + " to make a house on this property."+
               "\nThis property " + hasHotel + " hotel.";
    }

    @Override
    public boolean performAction(player thePlayer, player theBank, char choice)
   //PRE: thePlayer is the player that just threw the dice, and choice
   //     corresponds to the choices given by getPossibleActions
   //POST: - summons the functions necessary to perform actions by the user
   //      - Resets actionStatuses to false for the next user iteration.
   //      - FCTVAL = successFlag = true if the user picked a proper option
   //                             = false if the user picked a wrong action
    {
        boolean successFlag;    // successFlag Determines whether the user made a proper
                                //   choice or not

        char upperChoice;       // upperChoice Reformats choice to upper case

        successFlag = false;
        upperChoice = Character.toUpperCase(choice);

        if (upperChoice == 'N' &&        //If the user enters character 'N', and this
            actionStatus[0] == true)     //   Choice has been enabled, execute
        {
            //Perform nothing, simply end turn

            successFlag = true;
        }

        else if (upperChoice == 'B' &&   //If the user enters character 'B', and this
                actionStatus[1] == true) //   Choice has been enabled, execute
        {
            this.buy(thePlayer);                               //Set the title to the player
            thePlayer.addMoney(this.getPurcaseCost() * (-1));  //Deduce Money from the player
            theBank.addMoney(this.getPurcaseCost());           //Transfer Money to the Bank
            successFlag = true;
        }

        else if (upperChoice == 'H' &&   //If the user enters character 'H', and this
                actionStatus[2] == true) //   Choice has been enabled, execute
        {
            thePlayer.addMoney(this.getImproveCost() * (-1));  // Deduce Money from the player
            theBank.addMoney(this.getImproveCost());           // Transfer Money to the Bank
            this.addNumHouses();                               // Build house

            successFlag = true;
        }

        else if (upperChoice == 'O' &&   //If the user enters character 'O', and this
                actionStatus[3] == true) //   Choice has been enabled, execute
        {
            thePlayer.addMoney(this.getImproveCost() * (-1));  // Deduce Money from the player
            theBank.addMoney(this.getImproveCost());           // Transfer Money to the Bank
            this.makeHotel();                                  // Build Hotel
            successFlag = true;
        }

        else if (upperChoice == 'R' &&   //If the user enters character 'R', and this
                 actionStatus[4] == true) //   Choice has been enabled, execute
        {
            thePlayer.addMoney(this.getImproveCost() * (-1));  // Deduce Money from the player
            theBank.addMoney(this.getImproveCost());           // Transfer Money to the Bank
            successFlag = true;
        }

        else if (upperChoice == 'Q' &&   //If the user enters character 'Q', and this
                actionStatus[5] == true) //   Choice has been enabled, execute
        {

            //FIND A WAY TO KILL THE GAME
            successFlag = true;
        }
        // RESET FOR NEXT ITERATION

        if (successFlag == true)   // Initialize actionStatus to all False whenever
        {                          //   the user makes a proper choice.  Else, keep
                                   //   the actionStatuses for a retry
            for(boolean aStatus : actionStatus)
                aStatus = false;
        }

        return successFlag;
    }

}
