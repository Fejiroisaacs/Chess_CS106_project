import java.util.ArrayList;
import java.util.Arrays;

public class DrawChecks {

    public static boolean stalemateCheck(Player player, Board board){
        ArrayList<Piece> myPieces = player.getMyPieces();
        myPieces.remove(player.getMyking());
        for(Piece piece: myPieces){
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
        return true;
    }

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

    private static boolean knight(Piece piece, Board board) {
        int xPos = piece.getXPos();
        int yPos = piece.getYPos();

        // if any of these positions are null, it means the knight can move there meaning no draw
        return board.getBoard()[yPos][xPos+1] == null || board.getBoard()[yPos][xPos-3] == null
                || board.getBoard()[yPos+1][xPos] == null || board.getBoard()[yPos+1][xPos-2] == null
                || board.getBoard()[yPos-2][xPos+1] == null || board.getBoard()[yPos-2][xPos-3] == null
                || board.getBoard()[yPos-3][xPos] == null || board.getBoard()[yPos-3][xPos-2] == null;
    }

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
            return false; // can't be a threefold repetition if there are less than 3 board states

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

    public static boolean equalsTO(Piece piece, Piece otherPiece) {
        return piece.toString().equals(otherPiece.toString());
    }

    /**
     *
     *
     * this method checks if two boards are equal, by comparing each piece at each squares on both boards
     * @param board the other chessboard
     * @param otherBoard the other chessboard
     * @return boolean, if this board is the same as a given board
     */
    public static boolean boardEqualsTo(Piece[][] board, Piece[][] otherBoard) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                if (otherBoard[i][j] != null && board[i][j] == null) return false;
                if (otherBoard[i][j] == null && board[i][j] != null) return false;

                if (otherBoard[i][j] != null && board[i][j] != null) {
                    if (!equalsTO(otherBoard[i][j], board[i][j])) return false;
                }
            }
        }
        return true;
    }
}
