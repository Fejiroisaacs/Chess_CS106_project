public class Queen implements Piece{
    private int xPos;
    private int yPos;
    private final String color;


    /**
     * This is a constructor to create a Queen object with a specified x-position, y-position, and color
     * @param xPos The x-position of the Queen on a-f scale
     * @param yPos the y-position of the Queen on 1-8 scale
     * @param color the color of the Queen
     */
    public Queen(int xPos, int yPos, String color) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.color = color;
    }


    /**
     * This method returns the x-position of the Queen on a-f scale
     * @return the x-position of the Queen on a-f scale
     */
    @Override
    public int getXPos() { return this.xPos; }


    /**
     * This method returns the y-position of the Queen on a-f scale
     * @return the y-position of the Queen on a-f scale
     */
    @Override
    public int getYPos() { return this.yPos; }


    /**
     * This method determines if the Queen can move to the specified position on the board
     * @param xPos the a-h position the Queen wants to move to
     * @param yPos the 1-8 position the Queen wants to move to
     * @param board the board where the Queen is
     * @return true if the Queen can move to the entered coordinate, false otherwise
     */
    @Override
    public boolean canMove(int xPos, int yPos, Board board) {
        // uses a universal method because queen moves like a queen and a rook
        return UniversalMethods.canMoveBishop(this.xPos, this.yPos, xPos, yPos, board)
                ||
                UniversalMethods.canMoveRook(this.xPos, this.yPos, xPos, yPos, board);
    }


    /**
     * This method moves the Queen to the specified coordinates on the board
     * @param xPos the a-h position the Queen wants to move to
     * @param yPos the 1-8 position the Queen wants to move to
     * @param board the board where the Queen is
     */
    @Override
    public void move(int xPos, int yPos, Board board){
        // checks if coordinate the queen wants to move to has a piece there
        // checks if the queen can move to that position
        if(canMove(xPos, yPos, board) && board.getBoard()[yPos-1][xPos-1] == null) {
            board.editBoard(this, this.xPos, this.yPos, xPos, yPos);
            this.yPos = yPos;
            this.xPos = xPos;
        } else System.out.println("Can't move there");
        // won't throw this exception unless program would crash, unless we use try, catch... but no need for that
        //throw new IllegalArgumentException("Can't move there");
    }


    /**
     * This method checks if the Queen can capture a piece at the specified coordinates on the board
     * @param xPos the a-h position the Queen wants to move to and capture
     * @param yPos the 1-8 position the Queen wants to move to and capture
     * @param board the board where the Queen is
     * @return true if Queen can capture the given coordinates, false otherwise
     */
    @Override
    public boolean canCapture(int xPos, int yPos, Board board) {
        if(xPos < 0 || yPos < 0 || yPos > 8 || xPos > 8){
            System.out.println("Can't capture off the board.");
            return false;
        }
        Piece currPiece = board.getBoard()[yPos-1][xPos-1];
        // checks if there's a piece where the queen wants to capture
        // checks if the piece on that square is not the same color as the queen ( cannot capture your own pieces )
        if(canMove(xPos,yPos,board)  && currPiece != null && !currPiece.getColor().equals(this.color)){
            board.editBoard(this, this.xPos, this.yPos, xPos, yPos);
            this.yPos = yPos;
            this.xPos = xPos;
            return true;
        } else {
            System.out.println("Can't capture there. Piece is not a threat or no piece there.");
        }
        //throw new IllegalArgumentException("Can't capture there");
        return false;
    }


    /**
     * This method determines if this Queen is attacking a given piece on the given chessboard
     * @param piece the piece we might be attacking
     * @param board the chessboard
     * @return if we're attacking that piece or not
     */
    @Override
    public boolean isAttacking(Piece piece, Board board) {
        return this.canMove(piece.getXPos(), piece.getYPos(), board);
    }


    /**
     * This method returns the color of the Queen
     * @return the color of the Queen "Black" or "White"
     */
    @Override
    public String getColor() {
        return this.color;
    }


    /**
     * This method returns a unique display of this Queen, including its color
     * and position on the board
     * @return a unique display of the Queen with its color and its position
     */
    @Override
    public String toString() {
        return UniversalMethods.changeColor(this.color) +
                "Queen " +
                UniversalMethods.changeCord(xPos) + yPos + "\u001B[0m";
    }


}


