import java.util.ArrayList;
import java.util.Arrays;

public class Board {
    private final Piece[][] board;
    private ArrayList<Object[]> previousMoves = new ArrayList<>();

    /**
     * this method creates a chessBoard object
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
     *
     * @param piece the piece we want to move
     * @param initX the initial a-h coordinate of the piece
     * @param initY the initial 1-8 coordinate of the piece
     * @param finalX the final a-h coordinate of the piece
     * @param finalY the initial 1-8 coordinate of the piece
     */
    public void editBoard(Piece piece, int initX, int initY, int finalX, int finalY){
        board[initY-1][initX-1] = null; // set the current position of the piece to null ( empty )
        board[finalY-1][finalX-1] = piece; // updates the position of the piece
    }

    /**
     *
     * @return the Arraylist of objects of pieces + previous moves, in order.
     */
    public ArrayList<Object[]> getPreviousMoves(){
        return this.previousMoves;
    }
    /**
     *
     * @return the chessboard
     */
    public Piece[][] getBoard() { return board; }

    /**
     *
     * @return a unique representation of the chessboard
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
