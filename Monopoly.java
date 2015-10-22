// Programmer:  Harsh Patel, Christian Valaderas, Adolfo Moreno
// Assignment:  Monopoly
// Date:        October, 22 2015
// Description: This class represents a Monopoly board, it holds all the 
//              boardLocations and the players.  It is the main way of communication
//              with the back and the front end.
public class Monopoly
{
    public boardLocation [] monopolyBoard;          // The monopoly board.
    public player [] playerArr;                     // The array of players.

    public int currentDiceValue;                    // currentDiceValue Holds the
                                                    // current dice value

    public int currentPlayer;                       // currentPlayer holds the index
                                                    // of the current player's turn

    public int numberOfPlayers;                     // numberOfPlayers holds the
                                                    // number of players for a given game

    public int [] playerOrders;                     // an array to hold the order in which
                                                    // players will throw

    public String [] locationNames;                 // an array to hold the


    public Monopoly()
    // POST : A default monopoly game is created with an initialized board,
    //        and two players.
    {
        monopolyBoard = initializeBoard();
        playerArr     = initializeTwoPlayers();
        numberOfPlayers = playerArr.length;
    }

    public Monopoly (player [] playerArr)
    // PRE: the player array must be initialized.
    // POST: A new monopoly game is created with an initialized board and
    //       the class member playerArr set to playerArr.
    //       the class member numberOfPlayers is set to the length of playerArr
    //       class member playerOrders is initialized.
    {
        monopolyBoard = initializeBoard();
        this.playerArr = playerArr;
        numberOfPlayers = playerArr.length;
        playerOrders = new int [numberOfPlayers];
        
        for(player curr : playerArr)             // Setting board locations to 0
        {
            curr.setBoardLocation(0);
            curr.setCurrentLocation(monopolyBoard[0]);
        }
    }

    public String [] getLocationNames()
    //POST: will return a string containing the name of all the board locations.
    {
        int numberOfLocations = monopolyBoard.length;       // numberOfLocations will
                                                            //   count the number
                                                            //   of locations in the board

        String [] allNames = new String [numberOfLocations];// allNames will hold
                                                            //   all names for
                                                            //   all Locations


        for(int i = 0; i < numberOfLocations ; i++)         // go through all the 
        {                                                   // boardLocation and save the 
                                                            // names.
            allNames[i] = ((i + 1) + ") " + monopolyBoard[i].getName());
        }

        return allNames;
    }

    public int[] getPlayerOrder ()
    //POST: Initializes playerOrders[] with player indexes, where the first
    //      player is randomly chosen, and all subsequent players are in
    //      ascending order, with wrap around after the limit is crossed.
    {
        int slotsLeft;        // To count how many player slots are left to be assigned
        int firstPlayerIndex; // To hold the index of the first player, generated
                              //    randomly


        slotsLeft = numberOfPlayers;
        firstPlayerIndex = (int) (Math.random() * numberOfPlayers);

        for(int aSlot = 0; aSlot < numberOfPlayers; aSlot ++)
        {
            //STEP 1: Randomly Generate the first player

            if (aSlot == 0) // if aSlot == 0, the player index will be a randomly
                            //   firstPlayerIndex
            {
                playerOrders[aSlot] = firstPlayerIndex;
            }

            // STEP 2: "Assign turns by moving to the right."  This implies assigning
            //          player Indexes incrementally, and wrapping around if we
            //          exceed the number of players.

            else if (firstPlayerIndex + aSlot  // So long as the index of the currentPlayer
                            < numberOfPlayers) //   does not exceed the number of players,
                                               //   do not wrap around player index
            {
                playerOrders[aSlot] = firstPlayerIndex + aSlot;

            }

            else    // If the index of the currentPlayer exceeds the number of players,
                    //    wrap around and assign players left over.
            {
                playerOrders[aSlot] = numberOfPlayers - aSlot - 1;
            }
        }

                return playerOrders;
    }

    public void demoMode ()
    // POST: this method initialized the board location and the game so that
    //       the player are given some properties at the start, instead of 
    //       waiting for the game to take its turn.
    {
      int propIndex;    //To hold the index of a given property
         ((property) monopolyBoard[1]).buy(playerArr[0]);
         ((lot) monopolyBoard[1]).addNumHouses();
         ((lot) monopolyBoard[1]).addNumHouses();
         ((property) monopolyBoard[3]).buy(playerArr[1]);
         ((lot) monopolyBoard[3]).addNumHouses();
         ((property) monopolyBoard[5]).buy(playerArr[0]);
         ((property) monopolyBoard[6]).buy(playerArr[1]);
         ((lot) monopolyBoard[6]).addNumHouses();
         ((property) monopolyBoard[8]).buy(playerArr[0]);
         ((lot) monopolyBoard[8]).addNumHouses();
         ((lot) monopolyBoard[8]).addNumHouses();
         ((property) monopolyBoard[9]).buy(playerArr[0]);
         ((property) monopolyBoard[11]).buy(playerArr[1]);
         ((property) monopolyBoard[12]).buy(playerArr[1]);
         ((property) monopolyBoard[13]).buy(playerArr[0]);
         ((lot) monopolyBoard[13]).addNumHouses();
         ((lot) monopolyBoard[13]).addNumHouses();
         ((property) monopolyBoard[14]).buy(playerArr[0]);
         ((property) monopolyBoard[15]).buy(playerArr[0]);
         ((property) monopolyBoard[16]).buy(playerArr[1]);
         ((property) monopolyBoard[18]).buy(playerArr[1]);
         ((property) monopolyBoard[19]).buy(playerArr[1]);
         ((property) monopolyBoard[24]).buy(playerArr[0]);
         ((property) monopolyBoard[25]).buy(playerArr[0]);
         ((property) monopolyBoard[26]).buy(playerArr[0]);
    }

    private player [] initializeTwoPlayers()
    // POST: Initialized two default players,
    //       FCTVL == playerArr, an array of players.
    {
        playerArr = new player [2];
        playerArr[0] = new player();
        playerArr[1] = new player();
        return playerArr;
    }
    private boardLocation [] initializeBoard()
    // POST: An array of board location is returned with all the locations
    //       initialized to the specifications of monopoly board.
    {
      monopolyBoard = new boardLocation[42];
      
      monopolyBoard[0] = new cornerSquare("Go", 0, 2);              // Go
      
      double []rent1 = {2,10,30,90,160};
      monopolyBoard[1] = new lot("Mediterranean Ave.",1,"Dark Purple",60.0,50.0,0, rent1);
      monopolyBoard[2] = new cardSquare("Community Chest",2,200);   // Community chest
      
      double []rent2 = {4,20,60,180,320,450};
      monopolyBoard[3] = new lot("Baltic Ave.",3,"Dark Purple",60.0,50.0,0, rent2);
      
      monopolyBoard[4] = new taxSquare("Income Tax",4, 0);          // Income Tax
      monopolyBoard[5] = new railroad("Reading Railroad", 5);       // Reading Railroad
      
      double []rent3 = {6,30,90,270,400,550};                       // Oriental Ave
      monopolyBoard[6] = new lot("Oriental Ave.",6,"Light Blue",100,50,0, rent3);
      
      monopolyBoard[7] = new cardSquare("Chance",2,200);            // chance
      
      double []rent4 = {6,30,90,270,400,550};                       // Vermont Ave
      monopolyBoard[8] = new lot("Vermont Ave.",8,"Light Blue", 100, 50, 0,rent4);
      
      double []rent5 = {8,40,100,300,450,600};                      // Conn. Ave
      monopolyBoard[9] = new lot("Connecticut Ave.",9,"Light Blue", 140, 100, 0, rent5);
      monopolyBoard[10] = new cornerSquare("Just Visiting",10,2);   //Just Visiting
      
      double []rent6 = {10,50,150,450,625,750};                     // St. Charles
      monopolyBoard[11] = new lot("St. Charles Place",11,"Light Purple", 140, 100,0, rent6);
      
      monopolyBoard[12] = new utility("Electric Company", 12);      // Electric Company
      
      double []rent7 = {10,50,150,450,625,750};                     // States Ave
      monopolyBoard[13] = new lot("Sates Ave.",13,"Light Purple", 140, 100, 0,rent7);
      
      double []rent8 = {12,60,180,500,700,900};                     // Virginia Ave
      monopolyBoard[14] = new lot("Virginia Ave.",14,"Light Purple", 160, 100, 0, rent8);
      monopolyBoard[15] = new railroad("Pennsylvania Railroad", 15);// Penn Railroad
      
      double []rent9 = {14,70,200,550,750,950};                     // St. James Place
      monopolyBoard[16] = new lot(" St. James Place",16,"Orange", 180, 100, 0, rent9);
      monopolyBoard[17] = new cardSquare("Community Chest",17,200); // Community chest
                                                                    // Tennessee Ave
      monopolyBoard[18] = new lot("Tennessee Ave.",18,"Orange", 200, 100, 0, rent9);
      
      double []rent10 = {16,80,220,600,800,1000};                   // New York Ave
      monopolyBoard[19] = new lot("New York Ave.",19,"Orange", 200, 100, 0,rent10);
      
      monopolyBoard[20] = new gamble();                             // Gamble class
      
      monopolyBoard[21] =  new cornerSquare("Free Parking",21,2);
      
      double []rent11 = {18,90,250,700,875,1050};                   // Kentucky Avenue
      monopolyBoard[22] = new lot("Kentucky Ave.",22,"Red", 220, 150, 0,rent11);
      
      monopolyBoard[23] = new cardSquare("Chance",23,200);          // chance
                                                                    // Indiana Ave.
      monopolyBoard[24] = new lot("Indiana Ave.",24,"Red", 220, 150, 0,rent11);
      
      double []rent12 = {20,100,300,750,925,1100};                  // Illinois Ave
      monopolyBoard[25] = new lot("Illinois Ave.",25,"Red", 240, 150, 0, rent12);
      
      monopolyBoard[26] = new railroad("B&O Railroad", 26);         // B & O Railroad
      
      double []rent13 = {22,110,330,800,975,1150};                  // Atlantic Ave
      monopolyBoard[27] = new lot("Atlantic Ave.",27,"Yellow", 260, 150, 0, rent13);
                                                                    // Ventnor Ave
      monopolyBoard[28] = new lot("Ventnor Ave.",28,"Yellow", 260, 150, 0, rent13);
      
      monopolyBoard[29] = new utility("Water Works", 29);           // Water Works
      
      double []rent14 = {24,120,360,850,1025,1200};                 // Marvin Gardens
      monopolyBoard[30] = new lot("Marvin Gardens",30,"Yellow", 280, 150, 0, rent14);
      
      monopolyBoard[31] = new cornerSquare("Go To Jail",31,0);      // Go to jail
      double []rent15 = {26,130,390,900,1100,1275};
                                                                    // Pacific Ave
      monopolyBoard[32] = new lot("Pacific Ave.",32,"Green", 300, 200, 0, rent15);
                                                                    
                                                                    // No. Carolina Ave
      monopolyBoard[33] = new lot("No. Carolina Ave.",33,"Green", 300, 200, 0, rent15);
      
      monopolyBoard[34] = new cardSquare("Community Chest",34,200); //Community chest
      
      double []rent16 = {28,150,450,1000,1200,1400};                // Penn Ave
      monopolyBoard[35] = new lot("Pennsylvania Ave.",35,"Green", 320, 200, 0, rent16);
     
      monopolyBoard[36] = new railroad("Short Line Railroad", 36);  // Short Line Railroad
      
      monopolyBoard[37] = new cardSquare("Chance",37,200);          //chance
      
      double []rent17 = {35,175,500,1100,1300, 1500};               // Park Place
      monopolyBoard[38] = new lot("Park Place",38,"Dark Blue",350,200, 0, rent17);
      
      monopolyBoard[39] = new taxSquare("Luxury Tax", 39, 1);       //Luxury Tax
      
      double []rent18 = {50,200,600,1400,1700, 2000};               //BoardWalk
      monopolyBoard[40] = new lot("Boardwalk",40,"Dark Blue",400,200,0, rent18);
      
      monopolyBoard[41] = new gamble();                             // Gamble
      return monopolyBoard;
    }

    public boardLocation getBoardLocate(player one)
    // PRE: one must be initialized.
    // POST: FCTVAL == the current board location of the player is returned.
    {
        return monopolyBoard[one.getBoardLocation()];
    }
}
