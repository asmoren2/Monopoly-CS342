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

   //CONSTRUCTOR
   //........................................................................
   public void cornerSquare()
   // POST: cornerSquare() creates an instance of cornerSquare, with boolean
   //       values isJail, isGo, isFreeSpace = false;
   {
      super();             //construct boardLocation superClass
      isJail = false;
      isGo = false;
      isFreeSpace = false;
   }

   //MODIFIER
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

   //GETTER
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
}
