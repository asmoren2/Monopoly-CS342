//Programmer:  Christian M. Valladares, Harsh Patel, Adolfo Moreno
//Assignment:  Monopoly
//Date:        October 8, 2015
//Description: This class represents the income tax and luxury tax board locations
//
public class taxSquare extends boardLocation
{
    private int taxMode; // taxMode defines whether the current taxSquare is an
                         //   income tax square (taxMode == 0)  or if it is a luxury
                         //   tax square (taxMode == 1)

    public taxSquare()
    // POST: creates an instance of taxSquare.  It instantiates the super class
    //       board location, and sets the taxMode to income tax by default
    {
        taxMode = 0;
    }

    public taxSquare(String nameOfLocation, int spacesFromGo, int taxMode)
    // PRE: nameOfLocation, spacesFromGo and taxMode must be initialized.
    // POST: creates an instance of taxSquare.  It instantiates the super class
    //       board location, and sets the taxMode = taxMode
    {
        this.nameOfLocation = nameOfLocation;
        this.spacesFromGo = spacesFromGo;
        this.taxMode = taxMode;
    }
    
    public int getTaxMode()
    {
    	return taxMode;
    }

    public void setTaxMode (int taxMode)
    // taxMode must be initialized.
    // POST: sets taxMode = taxMode
    {
        this.taxMode = taxMode;
    }

    @Override
    public String toString()
    // POST:  returns a string representing the object of the cardSquare class
    {
        return "The name of this location is : " + nameOfLocation
              +"\nThe location is " + spacesFromGo +" away from go.";
    }

    @Override
    public String [] getPossibleActions(player player)
    // PRE: player must be initialized.
    // POST: FCTVAL == possibleActions, an array of Strings containing all
    //                 the possible actions a player can perform at a taxSquare
    //                 is returned.  
    //                 An array of booleans that represents the same thing is also 
    //                 altered in order to be used in the GUI.
    {
        double rent;
        
        if (taxMode == 1)               // If taxMode == 1, then generate 
                                        // possibleActions for a
        {                               // Luxury tax
            rent = 200;
        }
        else                            // income tax
        {
            rent = 75;
        }
        
        for(boolean action: actionStatus)// resetting the actions.
        {
            action = false;
        }
        actionStatus[4] = true;          // end game
        possibleActions[4] = PACTIONS[4];

        if(player.getMoney() > rent)     // pay rent automatically.
        {
            player.addMoney(-1*rent);
            actionStatus[0] = true;
            possibleActions[0] = PACTIONS[0];
        }                                // if 
        else if (player.getMoney() < rent
                && player.hasSellableProperty())
        {
            // sell
            actionStatus[2] = true;
            possibleActions[2] = PACTIONS[2];
        }
        else if (player.canImprove(player.getImprovingLots()))
        {
            // improve
            actionStatus[3] = true;
            possibleActions[3] = PACTIONS[3];
        }
        return possibleActions;
    }

}
