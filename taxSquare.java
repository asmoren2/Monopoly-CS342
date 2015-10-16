//Programmer:  Christian M. Valladares
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
    // POST: creates an instance of taxSquare.  It instantiates the super class
    //       board location, and sets the taxMode = taxMode
    {
        this.nameOfLocation = nameOfLocation;
        this.spacesFromGo = spacesFromGo;
        this.taxMode = taxMode;
    }

    public void setTaxMode (int taxMode)
    // POST: sets taxMode = taxMode
    {
        this.taxMode = taxMode;
    }

    @Override
    public String toString()
// POST:  returns a string representing the object of the cardSquare class
//
{
    return "The name of this location is : " + nameOfLocation
          +"\nThe location is " + spacesFromGo +" away from go.";
}

    @Override
    public String[] getPossibleActions(player player)
    // POST: FCTVAL possibleActions
    {
        if (taxMode == 1) //If taxMode == 1, then generate possibleActions for a
            //   luxury tax
            super.possibleActions[0] = "1) Pay 200$ to the bank due to income tax.";
        else
            super.possibleActions[0] = "1) Pay 50$ to the bank to income tax.";

        return possibleActions;
    }


}
