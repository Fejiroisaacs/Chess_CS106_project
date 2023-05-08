public class Rook implements Piece {
    private int xPos;
    private int yPos;
    private final String color;
    private boolean hasMoved=false;


    /**
     * This is a constructor to create a Rook object with a specified x-position, y-position, and color
     * @param xPos The x-position of the Rook on a-f scale
     * @param yPos the y-position of the Rook on 1-8 scale
     * @param color the color of the Rook
     */
    Rook(int xPos, int yPos, String color){
        this.color = color;
        this.xPos = xPos;
        this.yPos = yPos;
    }


    /**
     * This method returns the x-position of the Rook on a-f scale
     * @return the x-position of the Rook on a-f scale
     */
    @Override
    public int getXPos() { return this.xPos; }


    /**
     * This method returns the y-position of the Rook on a-f scale
     * @return the y-position of the Rook on a-f scale
     */
    @Override
    public int getYPos() { return this.yPos; }

    /**
     *
     * @return if this rook has moved or not
     */
    public boolean getHasMoved() { return this.hasMoved; }

    /**
     * This method determines if the Rook can move to the specified position on the board
     * @param xPos the a-h position the Rook wants to move to
     * @param yPos the 1-8 position the Rook wants to move to
     * @param board the board where the Rook is
     * @return true if the Rook can move to the entered coordinate, false otherwise
     */
    @Override
    public boolean canMove(int xPos, int yPos, Board board) {
        // uses a universal method because queen also moves like a rook
        return UniversalMethods.canMoveRook(this.xPos, this.yPos, xPos, yPos, board);
    }


    /**
     * This method moves the Rook to the specified coordinates on the board
     * @param xPos the a-h position the Rook wants to move to
     * @param yPos the 1-8 position the Rook wants to move to
     * @param board the board where the Rook is
     */
    @Override
    public void move(int xPos, int yPos, Board board){
        // checks if coordinate the rook wants to move to has a piece there
        // checks if the rook can move to that position
        if(canMove(xPos, yPos, board) && board.getBoard()[yPos-1][xPos-1] == null) {
            board.editBoard(this, this.xPos, this.yPos, xPos, yPos);
            this.yPos = yPos;
            this.xPos = xPos;
            this.hasMoved = true;
        } else {
            System.out.println("Can't move there, invalid move");
            throw new IllegalArgumentException("Can't move there, invalid move");
        }
    }


    /**
     * This method checks if the Rook can capture a piece at the specified coordinates on the board
     * @param xPos the a-h position the Rook wants to move to and capture
     * @param yPos the 1-8 position the Rook wants to move to and capture
     * @param board the board where the Rook is
     * @return true if Rook can capture the given coordinates, false otherwise
     */
    @Override
    public boolean canCapture(int xPos, int yPos, Board board) {
        if(xPos < 0 || yPos < 0 || yPos > 8 || xPos > 8){
            System.out.println("Can't capture off the board");
            return false;
        }
        Piece currPiece = board.getBoard()[yPos-1][xPos-1];
        // checks if there's a piece where the rook wants to capture
        // checks if the piece on that square is not the same color as the rook ( cannot capture your own pieces )
        // cannot capture a king
        if(canMove(xPos,yPos,board)  && currPiece != null && !currPiece.getColor().equals(this.color)
                && !(currPiece instanceof King)){
            board.editBoard(this, this.xPos, this.yPos, xPos, yPos);
            this.yPos = yPos;
            this.xPos = xPos;
            return true;
        } else System.out.println("Can't capture there. No piece on square or piece is not a threat");
        //throw new IllegalArgumentException("Can't capture there");
        return false;
    }


    /**
     * This method determines if this Rook is attacking a given piece on the given chessboard
     * @param piece the piece we might be attacking
     * @param board the chessboard
     * @return if we're attacking that piece or not
     */
    @Override
    public boolean isAttacking(Piece piece, Board board) {
        return this.canMove(piece.getXPos(), piece.getYPos(), board);
    }


    /**
     * This method returns the color of the Rook
     * @return the color of the Rook "Black" or "White"
     */
    @Override
    public String getColor() {
        return this.color;
    }


    /**
     * This method returns a unique display of this Rook, including its color
     * and position on the board
     * @return a unique display of the Rook with its color and its position
     */
    @Override
    public String toString() {
        return UniversalMethods.changeColor(this.color) +
                " Rook " +
                UniversalMethods.changeCord(xPos) + yPos + "\u001B[0m";
    }


}


