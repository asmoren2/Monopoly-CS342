public class Monopoly
{
    public boardLocation [] monopolyBoard;          // The monopoly board.
    public player [] playerArr;                     // The array of players.
    
    public Monopoly()
    // POST : A default monopoly game is created with an initialized board,
    //        and two players.
    {
        monopolyBoard = initializeBoard();
        playerArr     = initializeTwoPlayers();
    }
    
    public Monopoly (player [] playerArr)
    // PRE: the player array must be initialized.
    // POST: A new monopoly game is created with an initialized board and 
    //       the class member playerArr set to playerArr.
    {
        monopolyBoard = initializeBoard();
        this.playerArr = playerArr;
    }
    
    public void printAllLocations()
    // POST: This method prints out information about all board locations.
    {
        for(int i = 0; i < 40; i++)
        {
            System.out.println();
            System.out.println(monopolyBoard[i].toString());
        }
    }
    
    private player [] initializeTwoPlayers()
    // POST: Initialized two default players,
    //       FCTVL == 
    {
        playerArr = new player [2];
        playerArr[0] = new player();
        playerArr[1] = new player();
        return playerArr;
    }
    private boardLocation [] initializeBoard()
    {
        monopolyBoard = new boardLocation[40];
        monopolyBoard[0] = new cornerSquare("Go", 0, 2);//Go
        double []rent1 = {2,10,30,90,160};
        monopolyBoard[1] = new lot("MEDITERRANEAN AVE",1,"Dark Purple",60.0,50.0,0, rent1);
        monopolyBoard[2] = new cardSquare("COMMUNITY CHEST",2,200);//Community chest
        double []rent2 = {4,20,60,180,320,450};
        monopolyBoard[3] = new lot("BALTIC AVE.",3,"Dark Purple",60.0,50.0,0, rent2);
        monopolyBoard[4] = new taxSquare("Income Tax",4, 0);//Income Tax
        monopolyBoard[5] = new railroad("Reading Railroad", 5);//Reading Railroad
        double []rent3 = {6,30,90,270,400,550};
        monopolyBoard[6] = new lot("ORIENTAL AVE.",6,"Light Blue",100,50,0, rent3);//Oriental Ave
        monopolyBoard[7] = new cardSquare("CHANCE",2,200);//chance
        double []rent4 = {6,30,90,270,400,550};
        monopolyBoard[8] = new lot("VERMONT AVE.",8,"Light Blue", 100, 50, 0,rent4);//Vermont Ave
        double []rent5 = {8,40,100,300,450,600};
        monopolyBoard[9] = new lot("CONNECTICUT AVE.",9,"Light Blue", 140, 100, 0, rent5);//Conn. Ave
        monopolyBoard[10] = new cornerSquare("JUST VISITING",10,2);//Just Visiting
        double []rent6 = {10,50,150,450,625,750};
        monopolyBoard[11] = new lot("ST. CHARLES PLACE",11,"Light Purple", 140, 100,0, rent6);//St. Charles
        monopolyBoard[12] = new utility("Electric Company", 12);//Electric Company
        double []rent7 = {10,50,150,450,625,750};
        monopolyBoard[13] = new lot("STATES AVE.",13,"Light Purple", 140, 100, 0,rent7);//States Ave
        double []rent8 = {12,60,180,500,700,900};
        monopolyBoard[14] = new lot("VIRGINIA AVE.",14,"Light Purple", 160, 100, 0, rent8);//Virginia Ave
        monopolyBoard[15] = new railroad("Pennsylvania Railroad", 15);//Penn Railroad
        double []rent9 = {14,70,200,550,750,950};
        monopolyBoard[16] = new lot(" ST. JAMES PLACE",16,"Orange", 180, 100, 0, rent9);//St. James Place
        monopolyBoard[17] = new cardSquare("COMMUNITY CHEST",17,200);//Community chest
        monopolyBoard[18] = new lot("TENNESSEE AVE.",18,"Orange", 200, 100, 0, rent9);//Tennessee Ave
        double []rent10 = {16,80,220,600,800,1000};
        monopolyBoard[19] = new lot("NEW YORK AVE.",19,"Orange", 200, 100, 0,rent10);//New York Ave
        monopolyBoard[20] =  new cornerSquare("FREE PARKING",20,2);
        double []rent11 = {18,90,250,700,875,1050};
        monopolyBoard[21] = new lot("KENTUCKY AVE.",21,"Red", 220, 150, 0,rent11);//Kentucky Avenue
        monopolyBoard[22] = new cardSquare("CHANCE",22,200);//chance
        monopolyBoard[23] = new lot("INDIANA AVE.",23,"Red", 220, 150, 0,rent11);//Indiana Ave.
        double []rent12 = {20,100,300,750,925,1100};
        monopolyBoard[24] = new lot("ILLINOIS AVE.",24,"Red", 240, 150, 0, rent12);//Illinois Ave
        monopolyBoard[25] = new railroad("B&O Railroad", 25);//B & O Railroad
        double []rent13 = {22,110,330,800,975,1150};
        monopolyBoard[26] = new lot("ATLANTIC AVE.",26,"Yellow", 260, 150, 0, rent13);//Atlantic Ave
        monopolyBoard[27] = new lot("VENTNOR AVE.",27,"Yellow", 260, 150, 0, rent13);//Ventnor Ave
        monopolyBoard[28] = new utility("WATER WORKS", 28);//Water Works
        double []rent14 = {24,120,360,850,1025,1200};
        monopolyBoard[29] = new lot("MARVIN GARDENS",29,"Yellow", 280, 150, 0, rent14);//Marvin Gardens
        monopolyBoard[30] = new cornerSquare("GO TO JAIL",30,0);//Go to jail
        double []rent15 = {26,130,390,900,1100,1275};
        monopolyBoard[31] = new lot("PACIFIC AVE.",31,"Green", 300, 200, 0, rent15);//Pacific Ave
        monopolyBoard[32] = new lot("NO. CAROLINA AVE.",32,"Green", 300, 200, 0, rent15);//No. Carolina Ave
        monopolyBoard[33] = new cardSquare("COMMUNITY CHEST",33,200);//Community chest
        double []rent16 = {28,150,450,1000,1200,1400};
        monopolyBoard[34] = new lot("PENNSYLVANIA AVE.",34,"Green", 320, 200, 0, rent16);//Penn Ave
        monopolyBoard[35] = new railroad("Short Line Railroad", 35);//Short Line Railroad
        monopolyBoard[36] = new cardSquare("CHANCE",36,200);//chance
        double []rent17 = {35,175,500,1100,1300, 1500};
        monopolyBoard[37] = new lot("PARK PLACE",37,"Dark Blue",350,200, 0, rent17);//Park Place
        monopolyBoard[38] = new taxSquare("Luxury Tax", 38, 1);//Luxury Tax
        double []rent18 = {50,200,600,1400,1700, 2000};
        monopolyBoard[39] = new lot("BOARDWALK",39,"Dark Blue",400,200,0, rent18);//BoardWalk
        return monopolyBoard;
    }

}