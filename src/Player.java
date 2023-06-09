import java.util.ArrayList;

public class Player {
    private final String color;             // color of player, either "White" or "Black"
    private boolean myTurn = false;         // boolean indicating whether it is currently the player's turn
    private final Board board;              // the chess board associated with the player
    private final ArrayList<Piece> myPieces = new ArrayList<>();        // the pieces owned by this player
    private boolean isMated;                // boolean indicating whether the player is checkmated


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
     * This method returns an arrayList of this players pieces
     * @return the pieces that this player owns
     */
    public ArrayList<Piece> getMyPieces(){
        return this.myPieces;
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
     * This method updates the player's list of owned pieces based on the current state of the board.
     */
    public void updatePieces() {
        myPieces.clear();
        for (int i = 7; i >= 0; i--) {
            for (int j = 0; j < 8; j++) {
                Piece currPiece = board.getBoard()[i][j]; // gets a piece on a square
                if (currPiece != null && currPiece.getColor().equals(this.color)) {
                    myPieces.add(currPiece); // updates my pieces
                }
            }
        }
    }


    /**
     * This method returns the king owned by the player
     * @return the king owned by the player
     */
    public King getMyking() {
        updatePieces(); // updates the players pieces
        King king = null;
        for(Piece currPiece: myPieces){
            if(currPiece instanceof King){ // finds players king
                king = (King) currPiece;
                break; // ends loop after finding king ( only one king )
            }
        }

        assert king != null; // there must be a king on the board
        return king; // returns my king
    }


    /**
     * This method returns whether the player is checkmated by the given attacking piece or not
     * @param piece the attacking piece
     * @return true if the player is checkmated, false otherwise
     */
    public boolean isCheckmated(Piece piece) {
        King king = getMyking(); // gets my king
        return piece.isAttacking(king, board) && king.checkmated(board) && !canCaptureAttackingPiece(piece);
    }

    /**
     * this method checks for basic stalemate, where there is only a king
     * (more advanced stalemate would have to check if other pieces can't make a valid move and king can't move but is not attacked )
     * @return if there's a stalemate or not
     */
    public boolean isStalemated(){
        King king = getMyking(); // gets my king
        return this.myPieces.size() == 1 && !king.kingHasMoves(board) &&
                !king.notAttacked(board) && DrawChecks.stalemateCheck(this, board);
    }

    /**
     * this method checks if our piece can capture the piece threatening mate
     * @param piece the piece we are trying to capture
     * @return if we can capture a piece that is threatening to mate or not
     */
    public boolean canCaptureAttackingPiece(Piece piece){
        for(Piece myPiece: this.myPieces){
            try { // if our piece can capture the piece threatening mate, return true
                if(myPiece instanceof King){
                    // if this piece is our king, we check if we're attacking the attacking piece and
                    // if the square the piece is on is protected or not
                    if(myPiece.isAttacking(piece, board) &&
                     ((King) myPiece).squareNotProtected(board, piece.getXPos(), piece.getYPos())) return true;

                } else if(myPiece.isAttacking(piece, board)) return true; // else we check if our piece is attacking it
            } catch (Exception ignored){ } // ignore any errors thrown
        }
        return false; // no piece can capture attacking piece
    }


    /**
     * This method determines whether the player is in check or not
     * @param piece the piece to check
     * @return true if the player is in check, false otherwise
     */
    public boolean isChecked(Piece piece){

        return getMyking().isChecked(board, piece); // checks if our king is in check

    }


    /**
     * This method returns the color of the player
     * @return the color of the player
     */
    public String getColor() {
        return this.color;
    }


}





