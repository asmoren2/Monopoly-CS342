
public abstract class boardLocation {

    protected String nameOfLocation;    // The name of the location.
    protected int spacesFromGo;          // The distance from "go".

    public void setSpacesFromGo (int numSpaces)
    // PRE: numSpaces must be initialized
    // POST: sets the class member spaceFromGo to numSpaces.
    {
        this.spacesFromGo = numSpaces;
    }

    public void setName (String name)
    // PRE: name must be initialized
    // POST: sets the class memeber nameOfLocation to name.
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

    public String getPossibleActions(Player player)
    // POST: returns all the possible action a player can do on a certain
    //       board location.
    {
        return "";
    }

    public String toString()
    // POST:  returns a string representing the object of the board location
    //        class.
    {
        return "The name of this location is : " + nameOfLocation
              +"The location is " + spacesFromGo +" away from go.";
    }

}
