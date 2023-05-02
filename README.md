# Chess_CS106_project

<br/>
<br/>

## Interface: Piece

This interface defines the methods that should be implemented by any class that represents a piece on a board game. The methods are as follows:

- **`getXPos()`**: This method returns the X position of the piece on the board, represented as a number between 1 and 8.

- **`getYPos()`**: This method returns the Y position of the piece on the board, represented as a number between 1 and 8.

- **`canMove(int xPos, int yPos, Board board)`**: This method checks if the piece can move to the specified position on the board. The xPos and yPos parameters represent the position that the piece is trying to move to, and the board parameter represents the board we are playing with. This method returns true if the move is valid, false otherwise.

- **`move(int xPos, int yPos, Board board)`**: This method moves the piece to the specified position on the board. The xPos and yPos parameters represent the position that the piece is moving to, and the board parameter represents the board we are playing with.

- **`canCapture(int xPos, int yPos, Board board)`**: This method checks if the piece can capture a piece at the specified position on the board. The xPos and yPos parameters represent the position of the piece to capture, and the board parameter represents the board we are playing with. This method returns true if the capture is valid, false otherwise.

- **`isAttacking(Piece piece, Board board)`**: This method checks if the piece is attacking another piece on the board. The piece parameter represents the other piece that is being attacked, and the board parameter represents the board we are playing with. This method returns true if the piece is attacking the other piece, false otherwise.

- **`getColor()`**: This method returns the color of the piece, either "Black" or "White".

- **`toString()`**: This method returns a unique display of the piece, including its color and position on the board. 


<br/>
<br/>


## Board Class

The Board class represents a chess board with the ability to add and move pieces, and keep track of previous moves.

### Constructor

**`Board()`**: Creates a 2D 8x8 chess board and adds the initial pieces to their respective positions.

### Methods

- **`editBoard(Piece piece, int initX, int initY, int finalX, int finalY)`**: Updates the chess board to reflect a piece's move from its initial position to its final position.

- **`getPreviousMoves()`**: Returns the ArrayList of Object arrays representing previous moves made on the board.

- **`setPreviousMoves(Object[] prevMove)`**: Adds an Object array to the ArrayList of previous moves representing a new move made on the board.

- **`getBoard()`**: Returns the 2D array representing the chess board.

- **`toString()`**: Overrides the built-in toString() method and returns a unique String representation of the chess board for display purposes.



<br/>
<br/>




## Player Class

This Java class represents a chess player in a game and includes methods for updating the player's list of owned pieces, determining whether the player is checkmated or in check, and getting and setting the player's turn.

### Constructors
**`Player(String color, Board board)`**: A constructor for creating a new player with the given color and board.

### Methods

- **`getIsMated()`**: Returns whether the player is checkmated or not


- **`setMated()`**: Sets the player's checkmated boolean to true

- **`setMyTurn(boolean myTurn)`**: Sets the boolean indicating whether it is currently the player's turn or not

- **`getTurn()`**: Returns whether it is currently the player's turn or not

- **`updatePieces()`**: Updates the player's list of owned pieces based on the current state of the board

- **`getMyking()`**: Returns the king owned by the player

- **`isCheckmated(Piece piece)`**: Returns whether the player is checkmated by the given attacking piece or not

- **`isChecked(Piece piece)`**: Determines whether the player is in check or not

- **`getColor()`**: Returns the color of the player



<br/>
<br/>



## UniversalMethods

The UniversalMethods class contains a set of static methods that can be used across different classes of the Chess game. These methods provide utility functions for different purposes, such as converting between chessboard coordinates and integer positions, getting the name of the piece being moved, or checking whether a move is valid.

### Methods

The following methods are available in the **UniversalMethods** class:

- **`changeCord(int xPos)`**: This method takes an integer value between 1-8 inclusive and returns the corresponding string value of the alphabet (a-h) as a chessboard coordinate.

- **`changeColor(String color)`**: This method takes a string value of a chess piece color (either "Black" or "White") and returns the color code for the color.

- **`changeLetCord(String cord)`**: This method takes a string value of a chessboard coordinate (a-h) and returns the corresponding integer position value.

- **`validMove(Board board, Piece piece, int x_cord, int y_cord)`**: This method takes a Board object, a Piece object, and the x and y coordinates of the destination position. It returns a boolean value indicating whether the move is valid or not.

- **`validCapture(Board board, Piece piece, int x_cord, int y_cord)`**: This method takes a Board object, a Piece object, and the x and y coordinates of the destination position. It returns a boolean value indicating whether the move results in a valid capture or not.

- **`getPiece(String move)`**: This method takes a string value of a move (e.g., "q", "k", "b", or "n") and returns the name of the piece being moved (e.g., "pawn", "knight", "bishop", "rook", "queen", or "king").

- **`getCord(String move)`**: This method takes a string value of a move and returns the x and y coordinates of the destination position.





<br/>
<br/>





....
....




## Class Main: 
Creates the game, and brings all methods together. 
