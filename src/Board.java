import java.util.ArrayList;
import java.util.Arrays;


public class Board {
    private final Piece[][] board;                                              // 2D Array representing a piece
    private ArrayList<Object[]> previousMoves = new ArrayList<>();              // An ArrayList containing previous moves made on the board


    /**
     * This is a constructor for the Board class. This method creates a 2D 8x8 chess
     * board object and adds the initial pieces to their respective positions.
     */
    Board(){
        board = new Piece[8][8]; // instantiating the board

        //adding the black and white pawns
        for (int j = 0; j < 8; j++) {
            board[1][j] = new Pawn(j+1, 2, "White");
            board[6][j] = new Pawn(j+1, 7, "Black");
        }

        //adding the black rooks
        board[7][0] = new Rook(1, 8, "Black");
        board[7][7] = new Rook(8, 8, "Black");

        //adding the black knights
        board[7][1] = new Knight(2, 8, "Black");
        board[7][6] = new Knight(7, 8, "Black");

        //adding the black Bishops
        board[7][2] = new Bishop(3, 8, "Black");
        board[7][5] = new Bishop(6, 8, "Black");

        //adding the black queen
        board[7][4] = new King(5, 8, "Black");

        //Adding the black queen
        board[7][3] = new Queen(4, 8, "Black");

        //adding white rooks
        board[0][0] = new Rook(1, 1, "White");
        board[0][7] = new Rook(8, 1, "White");

        //adding the white knights
        board[0][1] = new Knight(2, 1, "White");
        board[0][6] = new Knight(7, 1, "White");

        //adding the white Bishops
        board[0][2] = new Bishop(3, 1, "White");
        board[0][5] = new Bishop(6, 1, "White");

        //adding the white queen
        board[0][4] = new King(5, 1, "White");

        //Adding the white queen
        board[0][3] = new Queen(4, 1, "White");
    }


    /**
     * This method updates the chess board to reflect a piece's move from its
     * initial position to its final position
     * @param piece the piece we want to move
     * @param initX the initial a-h coordinate of the piece
     * @param initY the initial 1-8 coordinate of the piece
     * @param finalX the final a-h coordinate of the piece
     * @param finalY the final 1-8 coordinate of the piece
     */
    public void editBoard(Piece piece, int initX, int initY, int finalX, int finalY){
        board[initY-1][initX-1] = null;             // set the current position of the piece to null ( empty )
        board[finalY-1][finalX-1] = piece;          // updates the position of the piece
    }


    /**
     * This method returns the ArrayList of Object arrays representing previous moves made on the board
     * @return The ArrayList of Object arrays representing previous moves made on the board
     */
    public ArrayList<Object[]> getPreviousMoves(){
        return this.previousMoves;
    }


    /**
     * This method adds an Object array to the ArrayList of previous moves representing
     * a new move made on the board
     * @param prevMove The Object array representing the new move made on the board
     */
    public void setPreviousMoves(Object[] prevMove){
        this.previousMoves.add(prevMove);
    }


    /**
     * This method returns the 2D array representing the chess board
     * @return The 2D array representing the chess board
     */
    public Piece[][] getBoard() {
        return board;
    }


    /**
     * This method overrides the built-in toString method and returns a String
     * representation of the chess board for display purposes
     * @return A unique String representation of the chess board for display purposes
     */
    public String toString() {
        StringBuilder value = new StringBuilder(); // initializing a StringBuilder
        value.append("----------------------------------------------------------------------------------------");
        value.append("\n");

        for(int i = 7; i >= 0; i--){
            value.append("|");
            for(int j = 0; j < 8; j++){
                Piece hold = board[i][j];
                if(hold != null) {
                    // if null, display the piece using its toString method
                    value.append(hold).append(" | ");
                } else {
                    // if null, display the coordinate of the board
                    value.append("   ").append(UniversalMethods.changeCord(j + 1)).append(i+1).append("   ").append(" | ");
                }
            }
            value.append("\n");
            value.append("----------------------------------------------------------------------------------------");
            value.append("\n");
        }

        return value.toString(); // returns our unique representation of the chessboard
    }


}


