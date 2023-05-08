public class Bishop implements Piece{
    private int xPos;                       // The x-position of the Bishop on the board
    private int yPos;                       // The y-position of the Bishop on the board
    private final String color;             // The color of the Bishop


    /**
     * This is a constructor to create a Bishop with the specified x-position, y-position, and color
     * @param xPos the x-position of the Bishop on the board (a-h scale)
     * @param yPos the y-position of the Bishop on the board (1-8 scale)
     * @param color the color of the Bishop ("Black" or "White")
     */
    public Bishop(int xPos, int yPos, String color) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.color = color;
    }


    /**
     * This method returns the x-position of the Bishop
     * @return the x-position of the Bishop on the board (a-h scale)
     */
    @Override
    public int getXPos() { return this.xPos; }


    /**
     * This method returns the y-position of the Bishop
     * @return the y-position of the Bishop on the board (1-8 scale)
     */
    @Override
    public int getYPos() { return this.yPos; }


    /**
     * This method checks if the Bishop can move to the specified coordinates on the board
     * @param xPos the a-h position the bishop wants to move to
     * @param yPos the 1-8 position the bishop wants to move to
     * @param board the chessboard
     * @return if the bishop can move to the entered coordinate or not
     */
    @Override
    public boolean canMove(int xPos, int yPos, Board board) {
        // uses a universal method because queen also moves like a bishop
        return UniversalMethods.canMoveBishop(this.xPos, this.yPos, xPos, yPos, board);
    }


    /**
     * This method moves the Bishop to the specified coordinates on the board
     * @param xPos the x-position (scale a-h) the bishop wants to move to
     * @param yPos the y-position (scale 1-8) the bishop wants to move to
     * @param board the chessboard
     */
    @Override
    public void move(int xPos, int yPos, Board board) {
        // checks if coordinate the bishop wants to move to has a piece there
        // checks if the bishop can move to that position
        if(canMove(xPos, yPos, board) && board.getBoard()[yPos-1][xPos-1] == null) {
            board.editBoard(this, this.xPos, this.yPos, xPos, yPos);
            this.yPos = yPos;
            this.xPos = xPos;
        } else System.out.println("Can't move there");
        // won't throw this exception unless program would crash, unless we use try, catch... but no need for that
        //throw new IllegalArgumentException("Can't move there")
    }


    /**
     * This method checks if the Bishop can capture a piece at the specified coordinates on the board
     * @param xPos the x-position the bishop wants to move to and capture
     * @param yPos the y-position the bishop wants to move to and capture
     * @param board the chessboard
     */
    @Override
    public boolean canCapture(int xPos, int yPos, Board board) {
        if(xPos < 0 || yPos < 0 || yPos > 8 || xPos > 8){
            System.out.println("Can't capture off the board");
            return false;
        }

        Piece currPiece = board.getBoard()[yPos-1][xPos-1];

        // checks if there's a piece where the bishop wants to capture
        // checks if the piece on that square is not the same color as the bishop ( cannot capture your own pieces )
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
     * This method determines if this bishop is attacking a given piece on the given chessboard
     * @param piece the piece we are attacking
     * @param board the chessboard
     * @return true if this (the bishop) is attacking the given piece, false otherwise
     */
    @Override
    public boolean isAttacking(Piece piece, Board board) {
        return this.canMove(piece.getYPos(), piece.getXPos(), board);
    }


    /**
     * This method return the color of the Bishop
     * @return the color of the bishop "Black" or "White"
     */
    @Override
    public String getColor() {
        return this.color;
    }


    /**
     * This method returns a unique display of this bishop, including its color
     * and position on the board
     * @return a unique display of the bishop with its color and its position
     */
    @Override
    public String toString() {
        return UniversalMethods.changeColor(this.color) +
                "Bishp " +
                UniversalMethods.changeCord(xPos) + yPos + "\u001B[0m";
    }


}



