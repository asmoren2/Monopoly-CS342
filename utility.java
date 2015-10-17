// Programmer:  Adolfo Moreno
// Assignment:  Monopoly
// Date:        October, 14 2015
// Description: This class represents a utility on the board location. 


public class utility extends property
{
   //Description:
   //Electric Company, Cost: 150, Tile: 12
   //Water Works, Cost : 150, Tile: 28

   public utility()
   // POST: a default utility is created with the purchase
   //      cost of 150.
   {
       super();
       this.purchaseCost = 150;
   }//Default Constructor

   public utility(String nameOfLocation, int spacesFromGo)
   // POST: a default utility is created with the name 
   //       nameOfLocation and it is spacesFromGo away from
   //       the go block.  It cost purchaseCost.
   {
      super(nameOfLocation,spacesFromGo);
      this.purchaseCost = 150.0;
   }//input Constructor

   public double caltUtilRent(int n, int diceNumber)
   {
      double rentCost = 0;
      if(n == 1)
      {
         rentCost = (4*diceNumber);
      }
      else if(n == 2)
      {
         rentCost = (10*diceNumber);
      }
      
      return rentCost;
   }

   @Override
   public String [] getPossibleActions(player thePlayer)
   // PRE: player must be initialized
   // POST: FCTVAL == A string array representing all the actions
   //                 a player can currently perform is returned.
   {                
       possibleActions = new String[20];    // Holds all possible actions
       int utilitiesOwned;                  // Number of utilities owned by a 
                                            //   the owner of this particular
                                            //   utility box
       
       if(isOwned == false)         // This Location is not owned
       {
           possibleActions[0] = "'N'-> Do Nothing";
           actionStatus[0] = true;
           possibleActions[1] = "'B' -> Buy";
           actionStatus[1] = true;
       }
       
       else if(isOwned == true)    // This Location is already owned
       {
           if (this.owner == thePlayer) // owned by the current player,
           {
               possibleActions[0] = "'N'-> Do Nothing";
               actionStatus[0] = true;
               possibleActions[2] =  "'I' -> Improve another Property (not implemented)";
               actionStatus[2] = true;
           }
           
           else if (this.owner != thePlayer)      // owned by another player 
           {               
               possibleActions[3] =  "'R' -> Pay Rent for this utility.";
               actionStatus[3] = true;
           }
           possibleActions[4] =  "'S' Sell Assets (not implemented)";
           actionStatus[4] = true;
           possibleActions[5] =  "'Q' End game (not implemented)";
           actionStatus[5] = true;
           
       }
       
       return possibleActions;
   }
   
   @Override
   public boolean performAction(player thePlayer, player theBank, char choice) 
   //PRE: thePlayer is the player that just threw the dice, and choice
   //     corresponds to the choices given by getPossibleActions
   //POST: - summons the functions necessary to perform actions by the user
   //      - Resets actionStatuses to false for the next user iteration.
   //      - FCTVAL = successFlag = true if the user picked a proper option
   //                             = false if the user picked a wrong action
   {
       boolean successFlag;    // successFlag Determines whether the user made a proper
                               //   choice or not

       char upperChoice;       // upperChoice Reformats choice to upper case   
      
       int numberOfUtilities;  // numberOfUtilities holds the number of utilities 
                               //   that the owner of this box has (a multiplier).
       double amountDue;       // amountDue holds the amount that is due for payment
       int diceValue;          // diceValue holds the dice value for this turn
       
       successFlag = false;
       
       upperChoice = Character.toUpperCase(choice);
       
       if (upperChoice == 'N' &&            //If the user enters character 'N', and this
               actionStatus[0] == true)     //   Choice has been enabled, execute 
       {            
           //Perform nothing, simply end turn
           
           successFlag = true;
       }
       
       else if (upperChoice == 'B' &&   //If the user enters character 'B', and this
               actionStatus[1] == true) //   Choice has been enabled, execute 
       {
           this.buy(thePlayer);                               //Set the title to the player
           thePlayer.addMoney(this.getPurcaseCost() * (-1));  //Deduce Money from the player
           theBank.addMoney(this.getPurcaseCost());           //Transfer Money to the Bank
          
           successFlag = true;
       }
       
       else if (upperChoice == 'I' &&   //If the user enters character 'I', and this
               actionStatus[2] == true) //   Choice has been enabled, execute 
       {
        
           // MUST IMPLEMENT
           
           successFlag = true;
       }
       
       else if (upperChoice == 'R' &&   //If the user enters character 'R', and this
               actionStatus[3] == true) //   Choice has been enabled, execute 
       {
           numberOfUtilities = this.getOwner().getNumberUtilities();
           diceValue = thePlayer.getDiceLand();
           amountDue = this.caltUtilRent(numberOfUtilities, diceValue);
                      
           successFlag = true;
       }
       
       return successFlag;
   }
}
