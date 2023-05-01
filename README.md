# Chess_CS106_project



## Interface: Pieces 

This interface defines the methods that should be implemented by any class that represents a piece on a board game. The methods are as follows:

- **`getXPos()`**: This method returns the X position of the piece on the board, represented as a number between 1 and 8.

- **`int getYPos()`**: This method returns the Y position of the piece on the board, represented as a number between 1 and 8.

- **`canMove(int xPos, int yPos, Board board)`**: This method checks if the piece can move to the specified position on the board. The xPos and yPos parameters represent the position that the piece is trying to move to, and the board parameter represents the board we are playing with. This method returns true if the move is valid, false otherwise.

- **`move(int xPos, int yPos, Board board)`**: This method moves the piece to the specified position on the board. The xPos and yPos parameters represent the position that the piece is moving to, and the board parameter represents the board we are playing with.

- **`boolean canCapture(int xPos, int yPos, Board board)`**: This method checks if the piece can capture a piece at the specified position on the board. The xPos and yPos parameters represent the position of the piece to capture, and the board parameter represents the board we are playing with. This method returns true if the capture is valid, false otherwise.

- **`isAttacking(Piece piece, Board board)`**: This method checks if the piece is attacking another piece on the board. The piece parameter represents the other piece that is being attacked, and the board parameter represents the board we are playing with. This method returns true if the piece is attacking the other piece, false otherwise.

- **`getColor()`**: This method returns the color of the piece, either "Black" or "White".

- **`toString()`**: This method returns a unique display of the piece, including its color and position on the board. 




## UniversalMethods

The UniversalMethods class contains a set of static methods that can be used across different classes of the Chess game. These methods provide utility functions for different purposes, such as converting between chessboard coordinates and integer positions, getting the name of the piece being moved, or checking whether a move is valid.

#### Methods

The following methods are available in the **UniversalMethods** class:

- **`changeCord(int xPos)`**: This method takes an integer value between 1-8 inclusive and returns the corresponding string value of the alphabet (a-h) as a chessboard coordinate.

- **`changeColor(String color)`**: This method takes a string value of a chess piece color (either "Black" or "White") and returns the color code for the color.

- **`changeLetCord(String cord)`**: This method takes a string value of a chessboard coordinate (a-h) and returns the corresponding integer position value.

- **`validMove(Board board, Piece piece, int x_cord, int y_cord)`**: This method takes a Board object, a Piece object, and the x and y coordinates of the destination position. It returns a boolean value indicating whether the move is valid or not.

- **`validCapture(Board board, Piece piece, int x_cord, int y_cord)`**: This method takes a Board object, a Piece object, and the x and y coordinates of the destination position. It returns a boolean value indicating whether the move results in a valid capture or not.

- **`getPiece(String move)`**: This method takes a string value of a move (e.g., "q", "k", "b", or "n") and returns the name of the piece being moved (e.g., "pawn", "knight", "bishop", "rook", "queen", or "king").

- **`getCord(String move)`**: This method takes a string value of a move and returns the x and y coordinates of the destination position.


....


## Class Board: 
Creates the chess board and displays it in the Console log. All pieces use this method. All board edits are done using this class.

## Class Player: 
Creates the Black and White players, has some other methods. 

## Class Main: 
Creates the game, and brings all methods together. 
