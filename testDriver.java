
public class testDriver {

    public static void main(String[] args) {
        player [] playerList = new player [3];
        playerList[0] = new player(1500, 0, 0, 0, "Harsh");
        playerList[1] = new player(1500, 0, 0, 0, "Adolfo");
        playerList[2] = new player(1500, 0, 0, 0, "Christian");

        Monopoly theGame = new Monopoly(playerList);

        theGame.printAllLocations();
        theGame.printAllPlayers();
    }

}