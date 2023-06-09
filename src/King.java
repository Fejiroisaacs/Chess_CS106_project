import java.util.ArrayList;

public class King implements Piece{
    private int xPos;
    private int yPos;
    private final String color;
    private final String[] kingMoves = new String[8];
    private final ArrayList<Piece> allOtherPieces = new ArrayList<>();
    private boolean hasMoved=false;


    /**
     * This is a constructor to create a King object with a specified x-position, y-position, and color
     * @param xPos The x-position of the king on a-f scale
     * @param yPos the y-position of the king on 1-8 scale
     * @param color the color of the king
     */
    public King(int xPos, int yPos, String color) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.color = color;
    }

    /**
     * this method checks if the king has moved or not
     * @return if this king has moved or not
     */
    public boolean getHasMoved() { return this.hasMoved; }

    /**
     *  This method sets all possible moves for the king based on its current position
     */
    public void setKingMoves(Board board){
        int[] hold = new int[8];

        hold[0] = ((this.xPos) * 10) + (this.yPos + 1); // up
        hold[1] = ((this.xPos) * 10) + (this.yPos - 1); // down

        hold[2] = ((this.xPos + 1) * 10) + (this.yPos + 1); // up right
        hold[3] = ((this.xPos - 1) * 10) + (this.yPos + 1); // up left

        hold[4] = ((this.xPos + 1) * 10) + (this.yPos); // right
        hold[5] = ((this.xPos - 1) * 10) + (this.yPos); // left

        hold[6] = ((this.xPos + 1) * 10) + (this.yPos - 1); // down right
        hold[7] = ((this.xPos - 1) * 10) + (this.yPos - 1); // down left

        int counter = 0;
        for(int cord: hold){ // don't want to add invalid moves to the move list
            if(cord > 0 && cord%10 <= 8 && cord/10 <= 8){
                if (pieceNotOnSquare(cord/10, cord%10, board)) kingMoves[counter] = String.valueOf(cord);
                else kingMoves[counter] = null;
            }
            else kingMoves[counter] = null;
            counter++;
        }
    }

    /**
     * this method checks if the king has valid moves (would be inverted because used for checking stalemate)
     * @param board the chessboard
     * @return if the king has a valid move or not
     */
    public boolean kingHasMoves(Board board){
        setKingMoves(board);
        for(String moves: this.kingMoves){
            if(moves != null) {
                if (squareNotProtected(board, Integer.parseInt(moves) / 10, Integer.parseInt(moves) & 10)){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * this method checks if our king is not attacked by any piece
     * @param board the chessboard
     * @return if this king is not attacked by any of the opponents pieces
     */
    public boolean notAttacked(Board board){
        opponentsPieces(board); // updates opponents pieces

        for(Piece piece: this.allOtherPieces){ // checks if any of the opponents piece is attacking this king
            if(piece.isAttacking(this, board)) return false;
        }
        return true;
    }

    /**
     * this method checks if there is no piece on a specified square
     * @param xPos The x-position of the square we want to check
     * @param yPos the y-position of the square we want to check
     * @param board the chessboard
     * @return if there's a piece on a specified square
     */
    private boolean pieceNotOnSquare(int xPos, int yPos, Board board){
        Piece piece = null;
        try {
            piece = board.getBoard()[yPos - 1][xPos - 1];
            if(piece.isAttacking(this, board) && !piece.getColor().equals(this.color)) return true;
        } catch (Exception ignored){ }

        return piece == null;
    }

    /**
     * This method returns the x-position of the king on a-f scale
     * @return the x-position of the king on a-f scale
     */
    @Override
    public int getXPos() { return this.xPos; }


    /**
     * This method returns the y-position of the king on 1-8 scale
     * @return the y-position of the king on 1-8 scale
     */
    @Override
    public int getYPos() { return this.yPos; }

    /**
     * this method sets the new x and y coordinate of the king -- used only after castling
     * @param xPos The x-position of the king on a-f scale
     * @param yPos the y-position of the king on 1-8 scale
     */
    public void setCoordinate(int xPos, int yPos, Board board){

        board.editBoard(this, this.xPos, this.yPos, xPos, yPos);

        this.xPos = xPos;
        this.yPos = yPos;

    }

    /**
     * This method determines if the king can move to the specified position on the board
     * @param xPos the a-h position the king wants to move to
     * @param yPos the 1-8 position the king wants to move to
     * @param board the board where the king is
     * @return true if the king can move to the entered coordinate, false otherwise
     */
    @Override
    public boolean canMove(int xPos, int yPos, Board board) {
        if(xPos > 8 || xPos < 0 || yPos > 8 || yPos < 0) return false;      // king cannot move off the board
        setKingMoves(board);
        int tryMove = (xPos * 10) + yPos;       // converts the entered coordinate to the form which kingsMoves are saved
        for(String moves : this.kingMoves){     // checks if the entered coordinate matches any of the possible king moves
            if(String.valueOf(tryMove).equals(moves)) return true;
        }
        return false;
    }


    /**
     * This method moves the King to the specified coordinates on the board
     * @param xPos the a-h position the king wants to move to
     * @param yPos the 1-8 position the king wants to move to
     * @param board the board where the king is
     */
    @Override
    public void move(int xPos, int yPos, Board board) {
        // checks if coordinate the king wants to move to has a piece there
        // checks if the king can move to that position
        // checks if the square is attacked (king can't move to protected square)
        if(board.getBoard()[yPos-1][xPos-1] == null && canMove(xPos, yPos, board)
                && squareNotProtected(board, xPos, yPos)) {
            board.editBoard(this, this.xPos, this.yPos, xPos, yPos);
            this.yPos = yPos;
            this.xPos = xPos;
            this.hasMoved = true;
        } else{
            throw new IllegalArgumentException("Can't move there, invalid move -- King class");
        }

    }


    /**
     * This method checks if the King can capture a piece at the specified coordinates on the board
     * @param xPos the a-h position the king wants to move to and capture
     * @param yPos the 1-8 position the king wants to move to and capture
     * @param board the board where the king is
     * @return true if king can capture the given coordinates, false otherwise
     */
    @Override
    public boolean canCapture(int xPos, int yPos, Board board) {
        if(xPos < 0 || yPos < 0 || yPos > 8 || xPos > 8){ // king cannot capture off the board
            throw new IllegalArgumentException("Can't capture off the board");
        }
        Piece currPiece = board.getBoard()[yPos-1][xPos-1];
        // checks if there's a piece where the king wants to capture
        // cannot capture a king
        // checks if the piece on that square is not the same color as the king ( cannot capture your own pieces )
        // checks if the piece is protected ( can't capture protected pieces )
        if(canMove(xPos,yPos,board)  && currPiece != null && !(currPiece instanceof King)
                && !currPiece.getColor().equals(this.color)  && squareNotProtected(board, xPos, yPos)){
            board.editBoard(this, this.xPos, this.yPos, xPos, yPos);
            this.yPos = yPos;
            this.xPos = xPos;
            this.hasMoved = true;
            return true;
        } else {
            throw new IllegalArgumentException("Can't capture. No piece there or square protected or piece is not a threat.");
        }

    }


    /**
     * This method checks if a given square is protected by an opponent's piece
     * @param moveX the a-h position of the square the king wants to move to / capture on.
     * @param moveY the 1-8 position of the square the king wants to move to / capture on.
     * @param board the board where the pieces are.
     */
    public boolean squareNotProtected(Board board, int moveX, int moveY){
        opponentsPieces(board); // updates opponents pieces
        Piece pieceOnSquare = board.getBoard()[moveY-1][moveX-1]; // gets the piece on this square

        if(pieceOnSquare != null){
            this.allOtherPieces.remove(pieceOnSquare); // removes the piece on the square from opponents piece list

            // minor change for the castling method
            if(pieceOnSquare.getColor().equals(this.getColor())) return true; // square not protected but our piece is on it
        }

        // checks if any of the opponents piece is attacking the square we want to move to/ through
        for(Piece piece: this.allOtherPieces){
            if(UniversalMethods.isAttackingSquare(piece, board, moveX, moveY)){
                return false;
            }
        }
        return true; // square is not attacked
    }


    /**
     * This method determines if this King is attacking a given piece on the given chessboard
     * @param piece the piece we might be attacking
     * @param board the chessboard
     * @return if we're attacking that piece or not
     */
    @Override
    public boolean isAttacking(Piece piece, Board board) {
        return this.canMove(piece.getXPos(), piece.getYPos(), board); // if we can move to where the piece is or not
    }


    /**
     * this method updates all the opponents pieces, so we can check if those pieces are attacking the king / attacking
     * a square the king wants to move to
     * @param board the chess board
     */
    public void opponentsPieces(Board board){
        // clears the arrayList, so we don't have duplicate pieces / pieces that are not on the board anymore
        this.allOtherPieces.clear();
        for(Piece[] row: board.getBoard()){ // adds all the opponents pieces to the allOtherPieces arrayList
            for(Piece piece: row){
                if(piece != null && !piece.getColor().equals(this.color)) this.allOtherPieces.add(piece);
            }
        }
    }


    /**
     * This method determines if the given piece can move to the square occupied by the king on the board
     * @param board the chessboard
     * @param piece the piece that might be checking the king
     * @return if the king is checked or not
     */
    public boolean isChecked(Board board, Piece piece){
        return piece.isAttacking(this, board) || discoveryCheck(board);
    }

    /**
     * this method checks if the player made a discovery check
     * @return if there's a discovery check or not
     */
    public boolean discoveryCheck(Board board){

        opponentsPieces(board); // updates opponents pieces

        for(Piece opponentsPiece: this.allOtherPieces){ // loops through opponents pieces
            try {
                // if any piece is attacking our king, it is a discovery check
                if (opponentsPiece.isAttacking(this, board)) return true;
            } catch (Exception ignored){ }
        }
        return false;
    }

    /**
     * This method checks if the king is checkmated on the given chessboard
     * @param board the chessboard
     * @return if the king is checkmated or not
     */
    public boolean checkmated(Board board){

        setKingMoves(board); // update king moves
        opponentsPieces(board); // updates opponents pieces
        boolean [] squareAttacked = new boolean[8]; // mapped with kingMoves

        int counter = 0;
        // extended check for kings valid move
        for(String moves: this.kingMoves){
            if(moves != null) {
                int moveX = Integer.parseInt(moves) / 10;
                int moveY = Integer.parseInt(moves) % 10;
                for (Piece piece : allOtherPieces) { // checks if any piece attacks a particular square the king can move to
                    if (UniversalMethods.isAttackingSquare(piece, board, moveX, moveY)) {
                        squareAttacked[counter] = true;
                        break;
                    }
                }
            }
            counter++;
        }

        for(int i = 0; i < this.kingMoves.length; i++){ // checks if there's a square that is not attacked
            if(this.kingMoves[i] != null){
                if(!squareAttacked[i]) return false;
            }
        }

        return true;
    }


    /**
     * This method return the color of the King
     * @return the color of the king "Black" or "White"
     */
    @Override
    public String getColor() { return this.color; }


    /**
     * This method returns a unique display of this King, including its color
     * and position on the board
     * @return a unique display of the king with its color and its position
     */
    @Override
    public String toString() {
        return UniversalMethods.changeColor(this.color) +
                " King " +
                UniversalMethods.changeCord(xPos) + yPos + "\u001B[0m";
    }

}

