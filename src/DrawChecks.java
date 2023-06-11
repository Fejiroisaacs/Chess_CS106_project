import java.util.ArrayList;
import java.util.Arrays;

public class DrawChecks {

    /**
     * this method satisfies one of the checks for stalemate --
     * if all the current players cannot move
     * @param player the current player
     * @param board the chessboard
     * @return if the current player has been stalemated ( one of the tests )
     */
    public static boolean stalemateCheck(Player player, Board board){

        ArrayList<Piece> myPieces = player.getMyPieces(); // gets this players pieces
        myPieces.remove(player.getMyking()); // removes the king ( king check for stalemate in king class )

        for(Piece piece: myPieces){ // checks all this players pieces, returns false if any piece can make a legal move
            if(piece instanceof Pawn){
                if(pawn(piece, board)) return false ;
            } else if (piece instanceof Bishop) {
                if(bishop(piece, board)) return false;
            } else if(piece instanceof Knight){
                if(knight(piece, board)) return false;
            } else if (piece instanceof Rook) {
                if(rook(piece, board)) return false;
            } else if (piece instanceof Queen) {
                if(bishop(piece, board) && rook(piece, board)) return false;
            }
        }

        return true; // none of this players pieces can move, return true -- stalemate
    }

    /**
     * this method checks the four squares surrounding the rook to see if it can move
     * ( if the rook can move to any of these squares, no stalemate )
     * @param piece the rook
     * @param board the chessboard
     * @return if this rook can move
     */
    private static boolean rook(Piece piece, Board board) {
        int xPos = piece.getXPos();
        int yPos = piece.getYPos();

        // if any of these positions are null, it means the rook can move there meaning no draw
        return  board.getBoard()[yPos][xPos-1] == null || board.getBoard()[yPos-2][xPos-1] == null
                || board.getBoard()[yPos-1][xPos] == null || board.getBoard()[yPos-1][xPos-2] == null;

    }

    private static boolean bishop(Piece piece, Board board) {
        int xPos = piece.getXPos();
        int yPos = piece.getYPos();

        // if any of these positions are null, it means the bishop can move there meaning no draw
        return board.getBoard()[yPos][xPos] == null || board.getBoard()[yPos-2][xPos-2] == null
                || board.getBoard()[yPos-2][xPos] == null || board.getBoard()[yPos][xPos-2] == null;
    }

    /**
     * this method checks the eight squares the knight can move to
     * @param piece the knight
     * @param board the chessboard
     * @return if this knight can move
     */
    private static boolean knight(Piece piece, Board board) {
        int xPos = piece.getXPos();
        int yPos = piece.getYPos();

        // if any of these positions are null, it means the knight can move there meaning no draw
        return board.getBoard()[yPos][xPos+1] == null || board.getBoard()[yPos][xPos-3] == null
                || board.getBoard()[yPos+1][xPos] == null || board.getBoard()[yPos+1][xPos-2] == null
                || board.getBoard()[yPos-2][xPos+1] == null || board.getBoard()[yPos-2][xPos-3] == null
                || board.getBoard()[yPos-3][xPos] == null || board.getBoard()[yPos-3][xPos-2] == null;
    }

    /**
     * this method checks if this pawn can move
     * @param piece the pawn
     * @param board the chessboard
     * @return if this pawn can move
     */
    private static boolean pawn(Piece piece, Board board){
        int change = piece.getColor().equals("White") ? 1:-1;
        return piece.canMove(piece.getXPos(), piece.getYPos()+change, board);
    }


    /**
     * @param board the chessboard
     * @return boolean, if there is a threefold repetition
     */
    public static boolean threefoldDraw(Board board) {

        ArrayList<Piece[][]> previousBoards = board.getPreviousBoardPositions();

        if (previousBoards.size() < 3)
            return false; // can't be a threefold repetition if there are less than 3 previous board states

        for (int i = 0; i < previousBoards.size() - 2; i++) {

            Piece[][] board1 = previousBoards.get(i);

            for (int j = i + 1; j < previousBoards.size() - 1; j++) { // if we've found a match for the first board, we check for another match

                Piece[][] board2 = previousBoards.get(j);

                if (boardEqualsTo(board1, board2)) {
                    for (int k = j + 1; k < previousBoards.size(); k++) {

                        Piece[][] board3 = previousBoards.get(k);
                        if (boardEqualsTo(board2, board3)) {
                            return true; // we found three matching boards, a threefold draw
                        }

                    }
                }
            }
        }
        return false; // no matches found, not a draw
    }

    /**
     * this method checks if two pieces are equal by comparing their toString values
     * @param piece the first piece
     * @param otherPiece the second piece we want to compare
     * @return if both pieces are equal
     */
    public static boolean equalsTO(Piece piece, Piece otherPiece) {
        return piece.toString().equals(otherPiece.toString());
    }

    /**
     *
     * this method checks if two boards are equal, by comparing the pieces at each squares on both boards
     * @param board the other chessboard
     * @param otherBoard the other chessboard
     * @return boolean, if this board is the same as a given board
     */
    public static boolean boardEqualsTo(Piece[][] board, Piece[][] otherBoard) {

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                // if either board has a piece and the other doesn't, boards are not equal
                if (otherBoard[i][j] != null && board[i][j] == null) return false;
                if (otherBoard[i][j] == null && board[i][j] != null) return false;

                // if there's a piece on a square on both boards, we check if those pieces are equal
                if (otherBoard[i][j] != null && board[i][j] != null) {
                    if (!equalsTO(otherBoard[i][j], board[i][j])) return false;
                }
            }
        }

        return true; // boards are equal
    }
}
