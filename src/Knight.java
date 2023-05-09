
public class Knight implements Piece{
    private int xPos;
    private int yPos;
    private final String color;
    private final String[] knightMoves = new String[8];


    /**
     * This is a constructor to create a Knight object with a specified x-position, y-position, and color
     * @param xPos The x-position of the Knight on a-f scale
     * @param yPos the y-position of the Knight on 1-8 scale
     * @param color the color of the Knight
     */
    public Knight(int xPos, int yPos, String color) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.color = color;
    }


    /**
     * This method returns the x-position of the Knight on a-f scale
     * @return the x-position of the Knight on a-f scale
     */
    @Override
    public int getXPos() { return this.xPos; }


    /**
     * This method returns the y-position of the Knight on 1-8 scale
     * @return the y-position of the Knight on 1-8 scale
     */
    @Override
    public int getYPos() { return this.yPos; }


    /**
     *  This method sets all possible moves for the Knight based on its current position
     */
    public void setKnightMoves(){
        int[] hold = new int[8];

        // corner cases here dealt with in enhanced for-loop below
        if (this.yPos <=7 ) {
            hold[0] = ((this.xPos + 2) * 10) + (this.yPos + 1);
            hold[2] = ((this.xPos - 2) * 10) + (this.yPos + 1);
        } if (this.yPos <= 6) {
            hold[1] = ((this.xPos + 1) * 10) + (this.yPos + 2);
            hold[3] = ((this.xPos - 1) * 10) + (this.yPos + 2);
        } if (this.yPos >= 1) {                                 //corner case 1
            hold[4] = ((this.xPos + 2) * 10) + (this.yPos - 1);
            hold[6] = ((this.xPos - 2) * 10) + (this.yPos - 1);
        } if (this.yPos >= 2) {                                 // corner case 2
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
     * This method determines if the Knight can move to the specified position on the board
     * @param xPos the a-h position the Knight wants to move to
     * @param yPos the 1-8 position the Knight wants to move to
     * @param board the board where the Knight is
     * @return true if the Knight can move to the entered coordinate, false otherwise
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
     * This method moves the Knight to the specified coordinates on the board
     * @param xPos the a-h position the Knight wants to move to
     * @param yPos the 1-8 position the Knight wants to move to
     * @param board the board where the Knight is
     */
    @Override
    public void move(int xPos, int yPos, Board board){
        // checks if the coordinate the knight wants to move to has a piece there
        // checks if the knight can move to that position
        if(board.getBoard()[yPos-1][xPos-1] == null && canMove(xPos, yPos, board)) {
            board.editBoard(this, this.xPos, this.yPos, xPos, yPos);
            this.yPos = yPos;
            this.xPos = xPos;
        } else {
            throw new IllegalArgumentException("Can't move there, invalid move -- Knight class");
        }
    }


    /**
     * This method checks if the Knight can capture a piece at the specified coordinates on the board
     * @param xPos the a-h position the Knight wants to move to and capture
     * @param yPos the 1-8 position the Knight wants to move to and capture
     * @param board the board where the Knight is
     * @return true if Knight can capture the given coordinates, false otherwise
     */
     @Override
    public boolean canCapture(int xPos, int yPos, Board board) {
        if (xPos < 0 || yPos < 0 || yPos > 8 || xPos > 8){
            throw new IllegalArgumentException("Can't capture off the board");
        }
        Piece currPiece = board.getBoard()[yPos-1][xPos-1];

        // checks if there's a piece where the knight wants to capture
        // checks if the piece on that square is not the same color as the knight ( cannot capture your own pieces )
         // cannot capture a king
        if(canMove(xPos,yPos,board)  && currPiece != null && !currPiece.getColor().equals(this.color)
                && !(currPiece instanceof King)){
            board.editBoard(this, this.xPos, this.yPos, xPos, yPos);
            this.yPos = yPos;
            this.xPos = xPos;
            return true;
        } else {
            throw new IllegalArgumentException("Can't capture. No piece there or square protected or piece is not a threat.");
        }
    }


    /**
     * This method determines if this Knight is attacking a given piece on the given chessboard
     * @param piece the piece we might be attacking
     * @param board the chessboard
     * @return if we're attacking that piece or not
     */
    @Override
    public boolean isAttacking(Piece piece, Board board) {
        return this.canMove(piece.getXPos(), piece.getYPos(), board);
    }


    /**
     * This method return the color of the Knight
     * @return the color of the knight "Black" or "White"
     */
    @Override
    public String getColor() { return this.color; }


    /**
     * This method returns a unique display of this Knight, including its color
     * and position on the board
     * @return a unique display of the Knight with its color and its position
     */
    @Override
    public String toString() {
        return UniversalMethods.changeColor(this.color) +
            "Knigt " +
            UniversalMethods.changeCord(xPos) + yPos + "\u001B[0m";
    }

}

