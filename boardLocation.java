// Programmer:  Harsh Patel
// Assignment:  Monopoly
// Date:        October, 14 2015
// Description: This class acts as a super class to all the other
//              monopoly classes, it represents a board location
//              through the use of inheritance, we can change this
//              class to represent more specific locations of the board.
public abstract class boardLocation
{
    
    protected String nameOfLocation;     // The name of the location.
    protected String [] possibleActions; // All the possible actions 
                                         // a player can have.
    protected int spacesFromGo;          // The distance from "go". 
    
    public boardLocation()
    // POST: A default board location is created.
    //       the class member nameOfLocation is set to "".
    //       the class member spacesFromGo is set to 0.
    //       the class member possibleActions initialized.
    {
        this("",0);
    }
    
    boardLocation(String nameOfLocation, int spacesFromGo)
    // PRE: nameOfLocation and spacesFromGo must be initialized.
    // POST: A new board location is initialize.
    //       the class member nameOfLocation is set to nameOfLocation.
    //       the class member spacesFromGo is set to spacesFromGo.
    //       the class member possibleActions initialized.
    {
        this.nameOfLocation = nameOfLocation;
        this.spacesFromGo = spacesFromGo;
        possibleActions = new String[20];

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

    
    public abstract String[] getPossibleActions(player player);
    
    @Override
    public String toString()
    // POST:  returns a string representing the object of the board location
    //        class.
    {
        return "The name of this location is : " + nameOfLocation
              +"\nThe location is " + spacesFromGo +" away from go.";
    }
    
}
