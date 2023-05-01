public class Bishop implements Piece{
    private int xPos;
    private int yPos;
    private final String color;

    /**
     *
     * @param xPos the bishops position on  a-f scale
     * @param yPos the bishops position on the 1-8 scale
     * @param color the bishops color
     */
    public Bishop(int xPos, int yPos, String color) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.color = color;
    }

    /**
     *
     * @return the a-h position of the bishop ( in coordinate form )
     */
    @Override
    public int getXPos() { return this.xPos; }

    /**
     *
     * @return the 1-8 position of the bishop
     */
    @Override
    public int getYPos() { return this.yPos; }

    /**
     *
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
     *
     * @param xPos the a-h position the bishop wants to move to
     * @param yPos the 1-8 position the bishop wants to move to
     * @param board the chessboard
     */
    @Override
    public void move(int xPos, int yPos, Board board) {
        // checks if the coordinate the bishop wants to move to has a piece there
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
     *
     * @param xPos the a-h position the bishop wants to move to and capture
     * @param yPos the 1-8 position the bishop wants to move to and capture
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
        if(canMove(xPos,yPos,board)  && currPiece != null && !currPiece.getColor().equals(this.color)){
            board.editBoard(this, this.xPos, this.yPos, xPos, yPos);
            this.yPos = yPos;
            this.xPos = xPos;
            return true;
        } else System.out.println("Can't capture there. No piece on square or piece is not a threat");
        //throw new IllegalArgumentException("Can't capture there");
        return false;
    }

    /**
     *
     * @param piece the piece we might be attacking
     * @param board the chessboard
     * @return if this(the bishop) is attacking that piece or not
     */
    @Override
    public boolean isAttacking(Piece piece, Board board) {
        return this.canMove(piece.getXPos(), piece.getYPos(), board);
    }

    /**
     *
     * @return the color of the bishop "Black" or "White"
     */
    @Override
    public String getColor() { return this.color; }

    /**
     *
     * @return a unique display of the bishop with its color and its position
     */
    @Override
    public String toString(){ return UniversalMethods.changeColor(this.color) + "Bishp " + UniversalMethods.changeCord(xPos) + yPos + "\u001B[0m"; }
}
