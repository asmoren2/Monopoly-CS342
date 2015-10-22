// Programmer:  Harsh Patel, Christian Valaderas, Adolfo Moreno
// Assignment:  Monopoly
// Date:        October, 14 2015
// Description: This class acts as a super class to all the properties
//              on the monopoly board.  It represents a property on the
//              board, it can eventually be converted to a lot and other
//              properties.
public abstract class property extends boardLocation
{
    protected player owner;             // The owner of the property.
    protected double purchaseCost;      // The cost of purchasing.
    protected double [] rentStructure;  // The rent structure of this property.
    protected boolean isOwned;          // is the district owned or not

    public property()
    // POST: A default property is initialized.
    {
        super();
    }

    public property(String nameOfLocation, int spacesFromGo)
    // POST: the class member nameOfLocation is set to nameOfLocation
    //       the class member spacesFromGo is set to spacesFromGo.
    {
        super(nameOfLocation,spacesFromGo);
    }
    
    public void setRent(double rent, int option)
    // PRE: rent and option are initialized.
    //      rent >= 0
    //      option >= 0
    // POST: Sets the class member rentStructure[option] to the rent.
    {
        this.rentStructure[option] = rent;
    }

    public void buy(player owner)
    // PRE: owner must be initialized.
    // POST: sets the class member owner to owner.
    //       sets the class member isOwned to true.
    {
        this.owner = owner;
        this.isOwned = true;
        owner.buyProperty(this);
    }

    public boolean isOwned()
    // POST: FCTVAL == isOwned.  is the property owned or not?
    {
        return this.isOwned;
    }

    public double getPurcaseCost()
    // FCTVAL == purchaseCost.  The purchase cost of the current
    //           property.
    {
        return this.purchaseCost;
    }

    public player getOwner()
    // FCTVAL: The owner of this property.
    {
        return this.owner;
    }

    @Override
    public String[] getPossibleActions(player player)
    // PRE: player must be initialized.
    // POST: FCTVAL == possibleActions, an array of Strings containing all
    //                 the possible actions a player can perform at a property
    //                 is returned.  
    {
        return possibleActions;
    }

    @Override
    public String toString()
    // POST:  A string representing this property.
    {
        if (isOwned == false)             //Handle the case when the property is not owned
               return super.toString() +
               "\nThe property is not owned yet." +
               "\nThe property costs: " + purchaseCost;

        else                              //Handle the case when the property is owned
                return super.toString() +
                "\nThe property is owned by: " + owner.getToken() +
                "\nThe property costs: " + purchaseCost;
    }
}
