public class Monopoly
{

	public static void main()
	{
		//initialize board
		boardLocation[] monopolyBoard = new boardLocation[40];
		monopolyBoard[0] = new cornerSquare("Go", 0, 2);//Go
		double []rent1 = {2,10,30,90,160};
		monopolyBoard[1] = new lot("Dark Purple",60.0,50.0,0, rent1);
		monopolyBoard[2] = new cardSquare();//Community chest
		double []rent2 = {4,20,60,180,320,450};
		monopolyBoard[3] = new lot("Dark Purple",60.0,50.0,0, rent2);
		monopolyBoard[4] = new taxSquare("Income Tax",4, 0);//Income Tax
		monopolyBoard[5] = new railroad("Reading Railroad", 5);//Reading Railroad
		double []rent3 = {6,30,90,270,400,550};
		monopolyBoard[6] = new lot("Light Blue",100,50,0, rent3);//Oriental Ave
		monopolyBoard[7] = new cardSquare();//Chance
		double []rent4 = {6,30,90,270,400,550};
		monopolyBoard[8] = new lot("Light Blue", 100, 50, 0,rent4);//Vermont Ave
		double []rent5 = {8,40,100,300,450,600};
		monopolyBoard[9] = new lot("Light Blue", 140, 100, 0, rent5);//Conn. Ave
		monopolyBoard[10] = new cornerSquare();//Just Visiting
		double []rent6 = {10,50,150,450,625,750};
		monopolyBoard[11] = new lot("Light Purple", 140, 100,0, rent6);//St. Charles
		monopolyBoard[12] = new utility("Electric Company", 12);//Electric Company
		double []rent7 = {10,50,150,450,625,750};
		monopolyBoard[13] = new lot("Light Purple", 140, 100, 0,rent7);//States Ave
		double []rent8 = {12,60,180,500,700,900};
		monopolyBoard[14] = new lot("Light Purple", 160, 100, 0, rent8);//Virginia Ave
		monopolyBoard[15] = new railroad("Pennsylvania Railroad", 15);//Penn Railroad
		double []rent9 = {14,70,200,550,750,950};
		monopolyBoard[16] = new lot("Orange", 180, 100, 0, rent9);//St. James Place
		monopolyBoard[17] = new lot();//Community Chest
		monopolyBoard[18] = new lot("Orange", 200, 100, 0, rent9);//Tennessee Ave
		double []rent10 = {16,80,220,600,800,1000};
		monopolyBoard[19] = new lot("Orange", 200, 100, 0,rent10);//New York Ave
		monopolyBoard[20] = new lot();//Free Parking
		double []rent11 = {18,90,250,700,875,1050};
		monopolyBoard[21] = new lot("Red", 220, 150, 0,rent11);//Kentucky Avenue
		monopolyBoard[22] = new lot();//Chance
		monopolyBoard[23] = new lot("Red", 220, 150, 0,rent11);//Indiana Ave.
		double []rent12 = {20,100,300,750,925,1100};
		monopolyBoard[24] = new lot("Red", 240, 150, 0, rent12);//Illinois Ave
		monopolyBoard[25] = new railroad("B&O Railroad", 25);//B & O Railroad
		double []rent13 = {22,110,330,800,975,1150};
		monopolyBoard[26] = new lot("Yellow", 260, 150, 0, rent13);//Atlantic Ave
		monopolyBoard[27] = new lot("Yellow", 260, 150, 0, rent13);//Ventnor Ave
		monopolyBoard[28] = new utility("Water Works", 28);//Water Works
		double []rent14 = {24,120,360,850,1025,1200};
		monopolyBoard[29] = new lot("Yellow", 280, 150, 0, rent14);//Marvin Gardens
		monopolyBoard[30] = new lot();//Go to jail
		double []rent15 = {26,130,390,900,1100,1275};
		monopolyBoard[31] = new lot("Green", 300, 200, 0, rent15);//Pacific Ave
		monopolyBoard[32] = new lot("Green", 300, 200, 0, rent15);//No. Carolina Ave
		monopolyBoard[33] = new lot();//Community Chest
		double []rent16 = {28,150,450,1000,1200,1400};
		monopolyBoard[34] = new lot("Green", 320, 200, 0, rent16);//Penn Ave
		monopolyBoard[35] = new railroad("Short Line Railroad", 35);//Short Line Railroad
		monopolyBoard[36] = new lot();//Chance
		double []rent17 = {35,175,500,1100,1300, 1500};
		monopolyBoard[37] = new lot("Dark Blue",350,200, 0, rent17);//Park Place
		monopolyBoard[38] = new taxSquare("Luxury Tax", 38, 1);//Luxury Tax
		double []rent18 = {50,200,600,1400,1700, 2000};
		monopolyBoard[39] = new lot("Dark Blue",400,200,0, rent18);//BoardWalk




		
	}
}
