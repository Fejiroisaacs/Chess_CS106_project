import java.util.ArrayList;

public class Player {
    private final String color;             // color of player, either "White" or "Black"
    private boolean myTurn = false;         // boolean indicating whether it is currently the player's turn
    private final Board board;              // the chess board associated with the player
    private final ArrayList<Piece> myPieces = new ArrayList<>();        // the pieces owned by the player
    private boolean isMated;                // boolean indicating whether the player is checkmated

    private boolean isChecked;

    /**
     * This is a constructs for new player with the given color and board.
     * @param color the color of the player, either "White" or "Black"
     * @param board the chess board associated with the player
     */
    public Player(String color, Board board) {
        this.color = color;
        this.board = board;
        if(color.equals("White")) myTurn = true;
    }


    /**
     * This method returns whether the player is checkmated or not
     * @return true if the player is checkmated, false otherwise
     */
    public boolean getIsMated() {
        return isMated;
    }


    /**
     * This method sets the player's checkmated boolean to true
     */
    public void setMated() {
        this.isMated = true;
    }


    /**
     * This method sets the boolean indicating whether it is currently the player's turn or not
     * @param myTurn true if it is the player's turn, false otherwise
     */
    public void setMyTurn(boolean myTurn) {
        this.myTurn = myTurn;
    }


    /**
     * This method returns whether it is currently the player's turn or not
     * @return true if it is the player's turn, false otherwise
     */
    public boolean getTurn(){
        return this.myTurn;
    }


    /**
     * This method updates the player's list of owned pieces based on the current state of the board
     */
    public void updatePieces() {
        for (int i = 7; i >= 0; i--) {
            for (int j = 0; j < 8; j++) {
                Piece currPiece = board.getBoard()[i][j];
                if (currPiece.getColor().equals(this.color)) {
                    myPieces.add(currPiece);
                }
            }
        }
    }


    /**
     * This method returns the king owned by the player
     * @return the king owned by the player
     */
    public King getMyking() {
        updatePieces();
        King king = null;
        for(Piece currPiece: myPieces){
            if(currPiece instanceof King){
                king = (King) currPiece;
                break;
            }
        }

        assert king != null;
        return king;
    }


    /**
     * This method returns whether the player is checkmated by the given attacking piece or not
     * @param piece the attacking piece
     * @return true if the player is checkmated, false otherwise
     */
    public boolean isCheckmated(Piece piece) {
        King king = getMyking();
        return piece.isAttacking(king, board) && king.checkmated(board);
    }


    /**
     * This method determines whether the player is in check or not
     * @param piece the piece to check
     * @return true if the player is in check, false otherwise
     */
    public boolean isChecked(Piece piece){
        this.isChecked = piece.isAttacking(getMyking(), board);
        return this.isChecked;
    }


    public void notChecked() {
        this.isChecked = false;
    }

    /**
     * This method returns the color of the player
     * @return the color of the player
     */
    public String getColor() {
        return this.color;
    }


}





