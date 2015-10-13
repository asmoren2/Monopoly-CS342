public class player
{
  private double money; //The amount of money that the player has
  private int numRailroad; //Number of railroads that the player owns
  private int numUtility; //Number of utilities that the player owns
  private int spaceFromGo; //Number of spaces the player is from Go tile
  private String playerToken; //Differentiates the player from
  private int boardLocation; //Location where the player resides on the
  private int diceLand;      // The value of the dice as it lands.
//THIS IS CHRISTIAN

player()
// POST: Sets a new board location for the specific player.
{
  this(1500, 0, 0, 0," ", 0);
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

public void setBoardLocation(int location)
//PRE: Assume 0 < location < 41, location is in block
//POST: Set a new board location for the specific player.
{
   this.boardLocation = location;
}

public double getMoney()
// POST: FCTVAL == The amount of money a player has.
{
  return this.money;
}

public int getBoardLocation()
//POST: FCTVAL == The current location of the player.
{
   return boardLocation;
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

public void addMoney(double amount)
// PRE: money must be initialized, money is in dollars.
// POST: adds money to member variable money. If money is a
//    negative value, then we subtract from the member variable money.
{
   this.money += amount;
}

public void addNumRailroad()
// POST: adds one to the class member numRailroad.
{
   this.numRailroad += 1;
}

public void addNumUtility()
//POST: Adds one to the class member numUtilities.
{
   this.numUtility += 1;
}

public void bankrupt()
// POST: Will reduce the amount of money a player has to $0.
{
   this.money = 0;
}

public int throwDice()
// POST: returns a random number between 1 and 12.
{
    return (int)Math.random()*13;
}

public String getToken()
// POST: FCTVAL ==  the token of the player.
{
    return this.playerToken;
}

@Override
public String toString()
{
   return "Player: " + playerToken + "Has $"+money + "Railroads owned: "+
         numRailroad + "Utilities owned" + numUtility + "Board location: " +
         boardLocation;
}
}//End Player Class
