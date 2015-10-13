
public abstract class boardLocation
{
    
    protected String nameOfLocation;     // The name of the location.
    protected String [] possibleActions; // All the possible actions 
                                         // a player can have.
    protected int spacesFromGo;          // The distance from "go". 
    
    boardLocation()
    {
        nameOfLocation = "";
        spacesFromGo = 0;
        possibleActions = new String[20];
    }
    
    boardLocation(String nameOfLocation, int spacesFromGo)
    {
        this.nameOfLocation = nameOfLocation;
        this.spacesFromGo = spacesFromGo;
    }
    
    public void setSpacesFromGo (int numSpaces)
    // PRE: numSpaces must be initialized
    // POST: sets the class member spaceFromGo to numSpaces.
    {
        this.spacesFromGo = numSpaces;
    }
    
    public void setName (String name)
    // PRE: name must be initialized
    // POST: sets the class member nameOfLocation to name.
    {
        this.nameOfLocation = name;
    }
    
    public int getSpacesFromGo()
    // POST: FCTVAL == The class member spacesFromGo, it represents
    //                 the distance away from "go"
    {
        return this.spacesFromGo;
    }
    
    public String getName()
    // POST: FCTVAL == The class member nameOfLocation, it represents
    //                 the name of the location.
    {
        return this.nameOfLocation;
    }
    // TODO:: This is a little confusing , ask Prof.Hogan.
    public String[] getPossibleActions(player player)
    // POST: FCTVAL == The class member possibleActions, it represents
    //                 all the possible actions a player can do at the 
    //                 current board location.
    {
        return possibleActions;
    }
    
    public String toString()
    // POST:  returns a string representing the object of the board location
    //        class.
    {
        return "The name of this location is : " + nameOfLocation
              +"The location is " + spacesFromGo +" away from go.";
    }
    
}
