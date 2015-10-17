//Programmer:  Christian M. Valladares
//Assignment:  Monopoly
//Date:        October 8, 2015
//Description: This class represents the four different corners of a monopoly
//             game:  Go, Jail/ Just Visiting,  Free Parking, and Go to Jail.
//             For this game, we do not implement Jail, Free Parking, and go to
//             jail.   For this reason, as of now,  cornerSquare will be concrete
//             class, only used to determine whether it is a jail corner or not
//
public class cornerSquare extends boardLocation
{
   private boolean isJail;       // isJail determines whether this corner Square
                                 //    is a jail location.

   private boolean isGo;         // isGo determines whether this corner Square
                                 //    is the Go block.

   private boolean isFreeSpace;  // isFreeSpace determines whether this corner is
                                 //    is just a free space.

   private double costOnLanding; // constOnLanding determines what the cost for
                                 //    landing on this corner implies   

   //CONSTRUCTORS
   //........................................................................
   public cornerSquare()
   // POST: cornerSquare() creates an instance of cornerSquare, with boolean
   //       values isJail, isGo = false.   Will assume a given corner is a
   //       freeSpace as it has no side effects therefore isFreeSpace = true
   {
      super();             //construct boardLocation superClass
      setToFreeSpace();
   }

   public cornerSquare(String nameOfLocation, int spacesFromGo, int chooseCornerMode)
   // PRE: nameOfLocation is non-empty.   spacesFromGo corresponds to spaces from
   //      go to the target cornerSquare.   0<= chooseCornermode <= 2, where 0
   //      determines the cornerSquare as Jail, 1 to Go block, and 2 to freeSpace
   //      block
   // POST: cornerSuqre(...) creates an instance of cornerSquare. Calls the boardLocation
   //       constructor.  Booleans isJail, isGo, and isFreeSpace are dependent on
   //       chooseCornermode.  If chooseCornermode == 0,  cornerSquare summons setToJail.
   //       If chooseCornermode == 1, cornerSquare summons setToGo.  If
   //       chooseCornermode == 2, cornerSquare summons setToFreeSpace.
   //
   {
      super(nameOfLocation, spacesFromGo);

      if(chooseCornerMode == 0)        // If CornerMode == 0,  the cornerBlock is determined
         setToJail();                  //   to be a jail block

      else if (chooseCornerMode == 1)  // If chooseCornerMode == 1, the cornerBlock is
         setToGo();                    //   determined to be a Go block

      else                             // if chooseCornerMode >= 2, the cornerBlock is
         setToFreeSpace();             //   determined to be a Free Space block
   }

   //MODIFIERS
   //........................................................................
   public void setToJail()
   // PRE: cornerSquare instance has been constructed, and isJail = false
   // POST: cornerSquare's isJail = true.  isGo, isFreeSpace = false.
   {
      isJail = true;
      isGo   = false;
      isFreeSpace = false;
   }

   public void setToGo()
   // PRE: cornerSquare instance has been constructed, and isGo = false
   // POST: cornerSquare's isGo = true.  isJail, isFreeSpace = false
   {
      isGo = true;
      isJail = false;
      isFreeSpace = false;
   }

   public void setToFreeSpace()
   // PRE: cornerSquare instance has been constructed, and isFreeSpace = false
   // POST: cornerSquare's isFreeSpace = true.  isJail, isGo = false
   {
      isFreeSpace = true;
      isJail = false;
      isGo = false;
   }

   //GETTERS
   //...........................................................................
   public boolean getJailStatus()
   // POST: Determines whether this cornerSquare instance is a Jail square
   //       FCTVAL = isJail
   {
      return isJail;
   }

   public boolean getGoStatus()
   // POST: Determines whether this cornerSquare instance is a Go square
   //
   {
      return isGo;
   }

   public boolean getFreeSpaceStatus()
   // POST: Determines whether this cornerSquare instance is a Free Space square
   //       FCTVAL = isFreeSpace
   {
      return isFreeSpace;
   }
   
   @Override
   public String[] getPossibleActions(player player)
   {
       // 1) Populate a string array containing all possible actions.
       
       if (isJail == true)      // verify the case when our cornerSquare is a Jail
       {
           super.possibleActions[0] = "1) Wait a turn.\n"; 
       }
       
       if (isGo == true)       // verify the case when our cornerSquare is a Free Space
       {
           super.possibleActions[0] = "1) Wait a turn.";
           super.possibleActions[1] = "2) Buy a hotel within your properties (unimplemented)";
           super.possibleActions[2] = "3) Buy a house within your properties (unimplemented)" ;
           super.possibleActions[3] = "4) End Game (unimplemented)";
       }
       
       if (isFreeSpace == true)
       {
           super.possibleActions[0] = "1) Wait a turn and recieve $200.00 reward.";
           super.possibleActions[1] = "2) Buy a hotel within your properties and" +
                                           "recieve $200.00 reward. (unimplemented)";
           super.possibleActions[2] = "3) Buy a house within your properties" +
                                           "and recieve $200.00 reward. (unimplemented)";
           super.possibleActions[3] = "4) End Game (unimplemented)";
       }
       
       return super.possibleActions;
   }

    @Override
    public boolean performAction(player thePlayer, player theBank, char choice) 
    {
        // TODO Auto-generated method stub
        return true;
    }


}
