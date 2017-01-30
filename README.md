# Monopoly-CS342

Group Members: Adolfo Moreno, Christian Valladares, Harsh Patel.

Project Description: 
As a project for our Computer Science class 342 we have decided to create a Monopoly based game that utilizes OOP concepts like objects, classes, inheritance, polymorphism, Abstraction and encapsulation.

Class Descriptions: 

Monopoly: This class represents a Monopoly board, it holds all the boardLocations and the players.  It is the main way of communication with the back and the front end.

BoardLocation: This class acts as a super class to all the other monopoly classes, it represents a board location such thatthrough the use of inheritance, we can change this class to represent more specific locations of the board..

CardSquare: This class represents the chance and community chest locations.The class will simply generate an amount to be deducted or accrued in a player's account; Within the player defined bounds.

CornerSquare: This class represents the four different corners of a monopoly game:  Go, Jail/ Just Visiting,  Free Parking, and Go to Jail. For this game, we do not implement Jail, Free Parking, and go to jail.   For this reason, as of now,  cornerSquare will be concrete class, only used to determine whether it is a jail corner or not

Lot: This class represents a lot on the board, the user can then buy or build houses on the lot.

Player: This class represents a player in the game.The player holds money, and property information.

Property: This class acts as a super class to all the properties on the monopoly board.  It represents a property on the board, it can eventually be converted to a lot and other properties.

Railroad: This class represents a railroad block on the monopoly game.

TaxSquare: This class represents the income tax and luxury tax board locations.

Utility: This class represents a utility on the board location.

Gamble: This class represents a gamble square on the board it is a special feature that allows the user that lands on the square to gamble a fortune on the roll of a dice.


