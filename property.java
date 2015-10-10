
public abstract class property extends boardLocation
{
    protected player owner;             // The owner of the property.
    protected double purchaseCost;      // The cost of purchasing.
    protected double [] rentStructure;  // The rent structure of this property.
    protected boolean isOwned;          // is the district owned or not
    
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
    
    public double getRent(int numHouses)
    // FCTVAL == The rent of the property, with numHouses houses on it.
    {
        return this.rentStructure[numHouses];
    }
    
}
