// Programmers:  Harsh Patel, Christian Valaderas and Adolfo Moreno
// Assignment:  Monopoly
// Date:        October, 14 2015
// Description: This class acts as a super class to all the other
//              monopoly classes, it represents a board location such that
//              through the use of inheritance, we can change this
//              class to represent more specific locations of the board.
public abstract class boardLocation
{
    
    protected String nameOfLocation;                // The name of the location.
    protected static final String [] PACTIONS =     // A hard-coded array of possible
                                     {"Do Nothing", // actions. 
                                      "Buy",       
                                      "Sell", 
                                      "Improve Property",
                                      "End Game"};              
  
    protected String [] possibleActions;            // Array stroing possibleActions
    public boolean [] actionStatus;                 // An array to hold which actions
                                                    //   are enabled and disabled
                                                    //   for a given board location.
                                                    //   Used to enforce fair Play.
    
    protected int spacesFromGo;          // The distance from "go". 
    
    public boardLocation()
    // POST: A default board location is created.
    //       the class member nameOfLocation is set to "".
    //       the class member spacesFromGo is set to 0.
    //       the class member possibleActions initialized.
    //       the class member actionStatus is initialized to false.
    {
        this("",0);
    }
    
    public boardLocation(String nameOfLocation, int spacesFromGo)
    // PRE: nameOfLocation and spacesFromGo must be initialized.
    // POST: A new board location is initialize.
    //       the class member nameOfLocation is set to nameOfLocation.
    //       the class member spacesFromGo is set to spacesFromGo.
    //       the class member possibleActions initialized.
    {
        this.nameOfLocation = nameOfLocation;       // Initializing class members.
        this.spacesFromGo = spacesFromGo;        
        
        this.actionStatus = new boolean [5];
        possibleActions = new String [5];
        
        for(String pActions : possibleActions)      // Initializing possibleActions to
        {                                           // ""
            pActions = "";
        }
                                                    // Initializing the aStatus boolean
        for(boolean aStatus : actionStatus)         // array to false.
        {
            aStatus = false;
        }
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
    
    
    public abstract String [] getPossibleActions(player player);
    // PRE: player must be initialized.
    // POST: This is an abstract class that returns a string array
    //       of all the possible actions a player can perform at a 
    //       certain state of the game.
    
    public boolean [] getActionStatus()
    // POST: FCTVAL == actionStatus, An array of booleans that represent
    //                 possible actions.
    {
        return actionStatus;
    }
    
    @Override
    public String toString()
    // POST:  returns a string representing an object of the board location
    //        class.
    {
        return "The name of this location is : " + nameOfLocation
              +"\nThe location is " + spacesFromGo +" away from go.";
    }
    
}
