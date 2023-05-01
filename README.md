# Chess_CS106_project

# Interface: Pieces 
Classes implementing Pieces: Pawn, Knight, Bishop, Rook, Queen, and King. 


...


# UniversalMethods

The UniversalMethods class contains a set of static methods that can be used across different classes of the Chess game. These methods provide utility functions for different purposes, such as converting between chessboard coordinates and integer positions, getting the name of the piece being moved, or checking whether a move is valid.

### Methods

The following methods are available in the **UniversalMethods** class:

- **changeCord(int xPos)**: This method takes an integer value between 1-8 inclusive and returns the corresponding string value of the alphabet (a-h) as a chessboard coordinate.

- **changeColor(String color)**: This method takes a string value of a chess piece color (either "Black" or "White") and returns the color code for the color.

- **changeLetCord(String cord)**: This method takes a string value of a chessboard coordinate (a-h) and returns the corresponding integer position value.

- **validMove(Board board, Piece piece, int x_cord, int y_cord)**: This method takes a Board object, a Piece object, and the x and y coordinates of the destination position. It returns a boolean value indicating whether the move is valid or not.

- **validCapture(Board board, Piece piece, int x_cord, int y_cord)**: This method takes a Board object, a Piece object, and the x and y coordinates of the destination position. It returns a boolean value indicating whether the move results in a valid capture or not.

- **getPiece(String move)**: This method takes a string value of a move (e.g., "q", "k", "b", or "n") and returns the name of the piece being moved (e.g., "pawn", "knight", "bishop", "rook", "queen", or "king").

- **getCord(String move)**: This method takes a string value of a move and returns the x and y coordinates of the destination position.


....


# Class Board: 
Creates the chess board and displays it in the Console log. All pieces use this method. All board edits are done using this class.

# Class Player: 
Creates the Black and White players, has some other methods. 

# Class Main: 
Creates the game, and brings all methods together. 
