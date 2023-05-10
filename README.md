# Chess_CS106_project

<br/>

## Interface: Piece

This interface defines the methods that should be implemented by any class that represents a piece on the chessboard game. The methods are as follows:

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

- **`getPreviousMoves()`**: Returns the ArrayList of Object arrays containing a piece and its previous moves made on the board.

- **`setPreviousMoves(Object[] prevMove)`**: Adds an Object array to the ArrayList of a piece and its previous moves made on the board.

- **`getBoard()`**: Returns the 2D array representing the chess board.

- **`toString()`**: Overrides the built-in toString() method and returns a unique String representation of the chess board for display purposes.



<br/>
<br/>




## Player Class

The Player class represents a chess player in a game and includes methods for updating the player's list of owned pieces, determining whether the player is checkmated or in check, and getting and setting the player's turn.

### Constructors
**`Player(String color, Board board)`**: A constructor for creating a new player with the given color and board.

### Methods

- **`getIsMated()`**: Returns if the player is checkmated or not

- **`setMated()`**: Sets the player's checkmated boolean to true

- **`setMyTurn(boolean myTurn)`**: Sets the boolean indicating whether it is currently the player's turn or not

- **`getTurn()`**: Returns whether it is currently the player's turn or not

- **`updatePieces()`**: Updates the player's list of owned pieces based on the current state of the board

- **`getMyking()`**: Returns the king owned by this player

- **`isCheckmated(Piece piece)`**: Returns whether the player is checkmated by the given attacking piece or not

- **`isChecked(Piece piece)`**: Determines whether a player's king is in check or not

- **`getColor()`**: Returns the color of the player "Black" or "White"

- **`canCaptureAttackingPiece(Piece piece)`**: Returns if any of this players piece can capture an attacking piece (used to determine invalid checkmate)



<br/>
<br/>



## UniversalMethods

The UniversalMethods class contains a set of methods that can be used across different classes of the Chess game. These methods provide utility functions for different purposes, such as converting between chessboard coordinates and integer positions, getting the name of the piece being moved, or checking whether a move is valid.

### Methods

The following methods are available in the **UniversalMethods** class:

- **`changeCord(int xPos)`**: This method takes an integer value between 1-8 inclusive and returns the corresponding string value of the alphabet (a-h) as a chessboard coordinate.

- **`changeColor(String color)`**: This method takes a string value of a chess piece color (either "Black" or "White") and returns the color code for the color.

- **`changeLetCord(String cord)`**: This method takes a string value of a chessboard coordinate (a-h) and returns the corresponding integer position value.

- **`getPiece(String move)`**: This method takes a string value of a move (e.g., "q", "k", "b", or "n") and returns the name of the piece being moved (e.g., "pawn", "knight", "bishop", "rook", "queen", or "king").

- **`getCord(String move)`**: This method takes a string value of a move and returns the x and y coordinates of the destination position.

- **`isAttackingSquare(Piece piece, Board board, int xPos, int yPos)`**: This method determines if a piece is attacking a square.

- **`canMoveBishop(int thisxPos, int thisyPos, int xPos, int yPos, Board board)`**: This method determines if a bishop can move to a given xPos and yPos.

- **`canMoveRook(int thisxPos, int thisyPos, int xPos, int yPos, Board board)`**: This method determines if a rook can move to a given xPos and yPos.

- **`getMoveSequence(String move)`**: This method takes in a move and returns an Object array of the piece name, its initial position (square), and the position (square) we are trying to move to.

- **`move(Player player, Board board, String move)`**: This method takes a player, a chessboard, and a move, then makes the move if possible.

- **`kingSideCastle(King king, Rook rook, Board board)`**: This method determines if a player can perform a king side (short) castle.

- **`queenSideCastle(King king, Rook rook, Board board)`**: This method determines if a player can perform a queen side (long) castle.

- **`castle(Board board, Player player, String move)`**: This method determines if a player can castle or not.



<br/>
<br/>







## Bishop Class

The Bishop class is an implementation of the Piece interface, representing a Bishop chess piece.


### Constructors

**`Bishop(int xPos, int yPos, String color)`**: This is a constructor to create a Bishop with the specified x-position, y-position, and color.


### Methods

- **`getXPos():`** This method returns the x-position of the Bishop.

- **`getYPos()`**: This method returns the y-position of the Bishop.

- **`canMove(int xPos, int yPos, Board board)`**: This method checks if the Bishop can move to the specified coordinates on the board.

- **`move(int xPos, int yPos, Board board)`**: This method moves the Bishop to the specified coordinates on the board.

- **`canCapture(int xPos, int yPos, Board board)`**: This method checks if the Bishop can capture a piece at the specified coordinates on the board.

- **`isAttacking(Piece piece, Board board)`**: This method determines if this Bishop is attacking a given piece on the given chessboard.

- **`getColor()`**: This method returns the color of the Bishop.

- **`toString()`**: This method returns a unique display of this Bishop, including its color and position on the board.







<br/>
<br/>






## King Class

The King class implements the Piece interface and represents a king in a chess game.

### Constructor

**`King(int xPos, int yPos, String color)`**: a constructor to create a King object with a specified xPos, yPos, and color.
Methods

### Methods

- **`getHasMoved()`**: This method returns if the king has moved or not

- **`setKingMoves()`**: This method that sets all possible moves for the king based on its current position.

- **`pieceNotOnSquare(int xPos, int yPos, Board board)`**: This method returns if a piece is on a given square or not

- **`getXPos()`**: This method that returns the xPos of the king on a-f scale.

- **`getYPos()`**: This method that returns the yPos of the king on 1-8 scale.

- **`setCoordinate(int xPos, int yPos, Board board)`**: This method changes the position of the king 

- **`canMove(int xPos, int yPos, Board board)`**: This method that determines if the king can move to the specified position on the board.

- **`move(int xPos, int yPos, Board board)`**: This method that moves the king to the specified coordinates on the board.

- **`canCapture(int xPos, int yPos, Board board)`**: This method that checks if the king can capture a piece at the specified coordinates on the board.

- **`squareNotProtected(Board board, int moveX, int moveY)`**: This method checks if a given square is protected by an opponent's piece

- **`isAttacking(Piece piece, Board board)`**: This method determines if this King is attacking a given piece on the given chessboard.

- **`opponentsPieces(Board board)`**: This method updates all the opponents pieces, so we can check if those pieces are attacking the king / attacking

- **`setKingMoves()`**: This method sets all possible moves for the king based on its current position.

- **`getXPos()`**: This method returns the xPos of the king on a-f scale.

- **`getYPos()`**: This method returns the yPos of the king on 1-8 scale.

- **`canMove(int xPos, int yPos, Board board)`**: This method determines if the king can move to the specified position on the board. 

- **`move(int xPos, int yPos, Board board)`**: This method moves the king to the specified coordinates on the board. 

- **`canCapture(int xPos, int yPos, Board board)`**: This method checks if the king can capture a piece at the specified coordinates on the board. 

- **`isChecked(Board board, Piece piece)`**: This method determines if the given piece can move to the square occupied by the king on the board (its checked)

- **`checkmated(Board board)`**: This method checks if the king is checkmated on the given chessboard

- **`discoveryCheck(Board board)`**: This method checks if the player made a discovery check, or if the king is in check but wasn't specified

- **`getColor()`**: This method returns the color of the King.

- **`toString()`**: This method returns a unique display of this King, including its color and position on the board.


<br/>
<br/>





## Knight Class

The Knight implements the Piece interface and represents a knight on a chess board and includes methods for determining the possible moves of the knight, checking if the knight can move to a specified position, and moving the knight to a specified position on the board.

### Constructor

**`Knight(int xPos, int yPos, String color)`**: This is a constructor to create a Knight object with a specified x-position, y-position, and color.


### Methods

- **`getXPos()`**: This method returns the x-position of the knight on a-f scale.

- **`getYPos()`**: This method returns the y-position of the knight on 1-8 scale.

- **`setKnightMoves()`**: This method sets all possible moves for the knight based on its current position.

- **`canMove(int xPos, int yPos, Board board)`**: This method determines if the knight can move to the specified position on the board.

- **`move(int xPos, int yPos, Board board)`**: This method moves the knight to the specified coordinates on the board.

- **`canCapture(int xPos, int yPos, Board board)`**: This method checks if the knight can capture a piece at the specified coordinates on the board.

- **`isAttacking(Piece piece, Board board)`**: This method determines if this Knight is attacking a given piece on the given chessboard.

- **`getColor()`**: This method returns the color of the Knight.

- **`toString()`**: This method returns a unique display of this Knight, including its color and position on the board.






<br/>
<br/>





## Pawn Class

The Pawn class represents a pawn piece in a chess board and includes methods for determining the possible moves of the Pawn, checking if the Pawn can move to a specified position, and moving the Pawn to a specified position on the board.


### Constructor 

**`Pawn(int xPos, int yPos, String color)`**: This is a constructor that creates a new Pawn object with a specified x-position, y-position, and color.


### Methods 

- **`getXPos()`**: A method that returns the x-position of the Pawn on a-f scale.

- **`getYPos()`**: A method that returns the y-position of the Pawn on a-f scale.

- **`canMove(int xPos, int yPos, Board board)`**: A method that determines if the Pawn can move to the specified position on the board.

- **`moveHelper(int xPos, int yPos, Board board)`**: This method changes the position of the pawn on the board.

- **`move(int xPos, int yPos, Board board)`**: A method that moves the Pawn to the specified coordinates on the board.

- **`canPromote(Board board)`**: This method checks if a pawn can be promoted after a move (in case the user doesn't specify a promotion) then promotes the pawn to a queen

- **`promote(String pieceName, int xPos, int yPos, Board board)`**: This method promotes a pawn to a specified piece name -- default promotion is a queen

- **`enPassant(int xPos, int yPos, Board board)`**: A method that determines whether the en passant move is valid for the given Pawn piece at the specified position on the board.

- **`canCapture(int xPos, int yPos, Board board)`**: A method that checks if the Pawn can capture a piece at the specified coordinates on the board.

- **`isAttacking(Piece piece, Board board)`**: A method that determines if this Pawn is attacking a given piece on the given chessboard.

- **`getColor()`**: A method that returns the color of the Pawn.

- **`toString()`**: A method that returns a unique display of this Pawn, including its color and position on the board.





<br/>
<br/>





## Queen Class

The Queen class implements the Piece interface and represents a Queen piece in a chess game.


### Constructor

**`Queen(int xPos, int yPos, String color)`**: This is a constructor that creates a new Queen object with a specified x-position, y-position, and color.


### Methods

- **`getXPos()`**: Returns the x-position of the Queen on a-f scale.

- **`getYPos()`**: Returns the y-position of the Queen on 1-8 scale.

- **`canMove(int xPos, int yPos, Board board)`**: Determines if the Queen can move to the specified position on the board.

- **`move(int xPos, int yPos, Board board)`**: Moves the Queen to the specified coordinates on the board.

- **`canCapture(int xPos, int yPos, Board board)`**: Checks if the Queen can capture a piece at the specified coordinates on the board.

- **`isAttacking(Piece piece, Board board)`**: Determines if this Queen is attacking a given piece on the given chessboard.

- **`getColor()`**: Returns the color of the Queen.

- **`toString()`**: Returns a unique display of this Queen, including its color and position on the board.







<br/>
<br/>





## Rook Class

The Rook class implements the Piece interface, representing the Rook chess piece. This class contains methods to move, capture, and check if the Rook can attack a certain square.


### Constructor

**`Rook(int xPos, int yPos, String color)`**: This is a constructor that creates a new Rook object with a specified x-position, y-position, and color.


### Method

- **`getXPos()`**: Returns the x-coordinate of the Rook on a-f scale.

- **`getYPos()`**: Returns the y-coordinate of the Rook on 1-8 scale.

- **`getHasMoved()`**: This method checks if the rook has moved or not

- **`canMove(int xPos, int yPos, Board board)`**: Determines if the Rook can move to the specified position on the board.

- **`move(int xPos, int yPos, Board board)`**: Moves the Rook to the specified coordinates on the board.

- **`canCapture(int xPos, int yPos, Board board)`**: Checks if the Rook can capture a piece at the specified coordinates on the board.

- **`isAttacking(Piece piece, Board board)`**: Determines if this Rook is attacking a given piece on the given chessboard.

- **`getColor()`**: Returns the color of the Rook, either "Black" or "White".

- **`toString()`**: Returns a unique display of this Rook, including its color and position on the board.





<br/>
<br/>





....
....






<br/>
<br/>








## Class Main: 
Creates the game, and brings all methods together. 
