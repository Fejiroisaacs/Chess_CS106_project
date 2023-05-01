
public class Knight implements Piece{
    private int xPos;
    private int yPos;
    private final String color;
    private final String[] knightMoves = new String[8];

    /**
     *
     * @param xPos the knights position on  a-f scale
     * @param yPos the knights position on the 1-8 scale
     * @param color the knights color
     */
    public Knight(int xPos, int yPos, String color) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.color = color;
    }

    /**
     *
     * @return the a-h position of the knight ( in coordinate form )
     */
    @Override
    public int getXPos() { return this.xPos; }

    /**
     *
     * @return the 1-8 position of the knight
     */
    @Override
    public int getYPos() { return this.yPos; }

    /**
     *  this method sets all the possible moves for the knight
     */
    public void setKnightMoves(){
        int[] hold = new int[8];

        // corner cases here dealt with in enhanced for-loop below
        if(this.yPos <=7 ) {

            hold[0] = ((this.xPos + 2) * 10) + (this.yPos + 1);
            hold[2] = ((this.xPos - 2) * 10) + (this.yPos + 1);
        }
        if(this.yPos <= 6) {
            hold[1] = ((this.xPos + 1) * 10) + (this.yPos + 2);
            hold[3] = ((this.xPos - 1) * 10) + (this.yPos + 2);
        }
        if(this.yPos >= 1) { //corner case 1
            hold[4] = ((this.xPos + 2) * 10) + (this.yPos - 1);
            hold[6] = ((this.xPos - 2) * 10) + (this.yPos - 1);
        }
        if(this.yPos >= 2) { // corner case 2
            hold[5] = ((this.xPos + 1) * 10) + (this.yPos - 2);
            hold[7] = ((this.xPos - 1) * 10) + (this.yPos - 2);
        }

        int counter = 0;
        for(int cord: hold){ // don't want to add invalid moves to the move list
            if(cord > 0 && cord%10 <= 8 && cord/10 <= 8)  this.knightMoves[counter] = String.valueOf(cord);
            else this.knightMoves[counter] = null;
            counter++;
        }

    }

    /**
     *
     * @param xPos the a-h position the knight wants to move to
     * @param yPos the 1-8 position the knight wants to move to
     * @param board the chessboard
     * @return if the knight can move to the entered coordinate or not
     */
    @Override
    public boolean canMove(int xPos, int yPos, Board board) {
        // pre-condition
        if(xPos > 8 || xPos < 0 || yPos > 8 || yPos < 0) return false; // knight cannot move off the board

        setKnightMoves(); // updates the knights moves
        int tryMove = (xPos * 10) + yPos; // converts the entered coordinate to the form which knightsMoves are saved

        for(String moves : this.knightMoves){ // checks if the entered coordinate matches any of the possible knight moves
            if(String.valueOf(tryMove).equals(moves)) return true;
        }
        return false; // move not found, return false
    }

    /**
     *
     * @param xPos the a-h position the knight wants to move to
     * @param yPos the 1-8 position the knight wants to move to
     * @param board the chessboard
     */
    @Override
    public void move(int xPos, int yPos, Board board){
        // checks if the coordinate the knight wants to move to has a piece there
        // checks if the knight can move to that position
        if(board.getBoard()[yPos-1][xPos-1] == null && canMove(xPos, yPos, board)) {
            board.editBoard(this, this.xPos, this.yPos, xPos, yPos);
            this.yPos = yPos;
            this.xPos = xPos;
        } else System.out.println("Can't move there");
        // won't throw this exception unless program would crash, unless we use try, catch... but no need for that
        //throw new IllegalArgumentException("Can't move there");

    }

    /**
     *
     * @param xPos the a-h position the knight wants to move to and capture
     * @param yPos the 1-8 position the knight wants to move to and capture
     * @param board the chessboard
     */
    @Override
    public boolean canCapture(int xPos, int yPos, Board board) {
        if(xPos < 0 || yPos < 0 || yPos > 8 || xPos > 8){
            System.out.println("Can't capture off the board");
            return false;
        }
        Piece currPiece = board.getBoard()[yPos-1][xPos-1];

        // checks if there's a piece where the knight wants to capture
        // checks if the piece on that square is not the same color as the knight ( cannot capture your own pieces )
        if(canMove(xPos,yPos,board)  && currPiece != null && !currPiece.getColor().equals(this.color)){
            board.editBoard(this, this.xPos, this.yPos, xPos, yPos);
            this.yPos = yPos;
            this.xPos = xPos;
            return true;
        } else System.out.println("Can't capture there. No piece there or piece is not an opponent");
        //throw new IllegalArgumentException("Can't capture there");
        return false;
    }

    /**
     *
     * @param piece the piece we might be attacking
     * @param board the chessboard
     * @return if this(the knight) is attacking that piece or not
     */
    @Override
    public boolean isAttacking(Piece piece, Board board) {
        return this.canMove(piece.getXPos(), piece.getYPos(), board);
    }

    /**
     *
     * @return the color of the knight "Black" or "White"
     */
    @Override
    public String getColor() { return this.color; }

    /**
     *
     * @return a unique display of the knight with its color and its position
     */
    @Override
    public String toString(){ return UniversalMethods.changeColor(this.color) + "Knigt " + UniversalMethods.changeCord(xPos) + yPos + "\u001B[0m"; }
}
