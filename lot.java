
public class lot extends property
{
    private String color;
    private String district;
    private String hasHotel;
    private double improveCost;
    private int numHouses;
    private boolean isHotel;
    lot()
    // POST: a default lot is created, with no district, 
    //       no color, and no name.  It is 0 spaces away from go,
    //       it has a cost of 0.0 and an improve cost of 0.0.
    //       It has 0 houses on it, and it is also not a hotel.
    //       It initialized the class member hasHotel to "does not have a"
    //       It also has a rent structure of rentStructure.
    {
        this("","","",0,0.0,0.0,0,new double [7]);
    }
    lot(String district, String color, String nameOfLocation,
        int spacesFromGo,double purchaseCost, double improveCost,
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
    {
        this.district = district;
        this.color = color;
        this.nameOfLocation = nameOfLocation;
        this.spacesFromGo = spacesFromGo;
        this.purchaseCost = purchaseCost;
        this.improveCost = improveCost;
        this.rentStructure = rentStructure;
        this.hasHotel = "does not";
        this.isHotel = false;
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
    
    public String getDistrict()
    // POST: FCTVAL == the district this lot is a part of.
    {
        return this.district;
    }
    
    public String getColor()
    // POST: FCTVAL == the color group this lot belongs to.
    {
        return this.color;
    }
    
    @Override
    // TODO:: This is a little confusing , ask Prof.Hogan.
    public String [] getPossibleActions(player player)
    {
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
    
}
