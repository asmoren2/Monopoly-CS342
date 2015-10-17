import java.util.Scanner;

public class testDriver {

    public static void main(String[] args) {
        player [] playerList = new player [3];
        playerList[0] = new player(1500, 0, 0, 0, "Harsh");
        playerList[1] = new player(1500, 0, 0, 0, "Adolfo");
        playerList[2] = new player(1500, 0, 0, 0, "Christian");
        
        player theBank = new player (9999, 0,0,0, "Bank");
        
        String choice;
        
        Scanner in = new Scanner(System.in);
        
        Monopoly theGame = new Monopoly(playerList);

        //TEST MODE ON.  Expect to notice different options for actions on demo
        //      mode
        //theGame.demoMode();
        
        theGame.printAllLocations();
        theGame.printAllPlayers();
        

        
        // TEST ONLY: GET POSSIBLE ACTIONS
        //  Will take a player linearly through 10 locations, from go.
        for (int i = 0; i < 10; i++)
        {
            System.out.println();
            for(String anOption : theGame. monopolyBoard[i].getPossibleActions(playerList[0]))
                if (anOption != null)
                    System.out.println(anOption);

            System.out.println("\n Type in the appropriate option");            
            choice = in.nextLine();
            
            while(!theGame.monopolyBoard[i].performAction(playerList[1], theBank, choice.charAt(0)))
            {
                System.out.println("\n Type in the appropriate option");
                choice = in.nextLine();
            }
        }
        
        //PRINT ALL INFO AFTER TRAVERSING        
        theGame.printAllLocations();
        theGame.printAllPlayers();
    }

}
