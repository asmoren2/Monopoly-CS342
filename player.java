public class player
{
  private double money; //The amount of money that the player has
  private int numRailroad; //Number of railroads that the player owns
  private int numUtility; //Number of utilities that the player owns
  private int spaceFromGo; //Number of spaces the player is from Go tile
  private String playerToken; //Differentiates the player from
  private int boardLocation; //Location where the player resides on the

//THIS IS CHRISTIAN

player()
// POST: Sets a new board location for the specific player.
{
  this(1500, 0, 0, 0,0," ", 0);
}
player(double money, int railroads, int utility, int spaceGo, String token, int location)
//PRE : 0 < location < 41; location is in block.
{
  this.money = money;
  this.numRailroad = railroads;
  this.numUtility = utility;
  this.spaceFromGo = spaceGo;
  this.playerToken = token;
  this.boardLocation = location;
}

public int getMoney()
// POST: FCTVAL == 	The amount of money a player has.
{
  return this.money
}

public int getNumberRailroad()
// POST: FCTVAL == The number of railroads a player owns.
{
  return this.numRailroad;
}

public int getNumberUtilities()
// POST: FCTVAL == The number of utilities a player has.
{
  return this.numUtility;
}

}//End Player Class
