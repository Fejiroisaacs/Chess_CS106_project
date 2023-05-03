public class Pawn implements Piece{
    private int xPos;
    private int yPos;
    private final String color;
    private boolean firstMove = true;


    /**
     * This is a constructor to create a Pawn object with a specified x-position, y-position, and color
     * @param xPos The x-position of the Pawn on a-f scale
     * @param yPos the y-position of the Pawn on 1-8 scale
     * @param color the color of the Pawn
     */
    Pawn(int xPos, int yPos, String color){
        this.color = color;
        this.xPos = xPos;
        this.yPos = yPos;
    }


    /**
     * This method returns the x-position of the Pawn on a-f scale
     * @return the x-position of the Pawn on a-f scale
     */
    @Override
    public int getXPos() { return this.xPos; }


    /**
     * This method returns the y-position of the Pawn on a-f scale
     * @return the y-position of the Pawn on a-f scale
     */
    @Override
    public int getYPos() { return this.yPos; }


    /**
     * This method determines if the Pawn can move to the specified position on the board
     * @param xPos the a-h position the Pawn wants to move to
     * @param yPos the 1-8 position the Pawn wants to move to
     * @param board the board where the Pawn is
     * @return true if the Pawn can move to the entered coordinate, false otherwise
     */
    @Override
    public boolean canMove(int xPos, int yPos, Board board) {
        return ((Math.abs(yPos - this.yPos) == 2 && this.firstMove) || (Math.abs(yPos - this.yPos) == 1)) && board.getBoard()[yPos-1][xPos-1] == null;
    }


    /**
     * This method moves the Pawn to the specified coordinates on the board
     * @param xPos the a-h position the Pawn wants to move to
     * @param yPos the 1-8 position the Pawn wants to move to
     * @param board the board where the Pawn is
     */
    @Override
    public void move(int xPos, int yPos, Board board) {
        if(canMove(xPos, yPos, board)){
            this.firstMove = false;
            board.editBoard(this, this.xPos, this.yPos, this.xPos, yPos );
            this.yPos = yPos;
            this.xPos = xPos;
        } else System.out.println("Can't move there, invalid move");
        //throw new IllegalArgumentException("Can't move there");
    }


    /**
     * This method determines whether the en passant move is valid for the given Pawn
     * piece at the specified position on the board
     * @param xPos the x-coordinate of the current pawn's position on the board
     * @param yPos the y-coordinate of the current pawn's position on the board
     * @param board the board where the Pawn is
     * @return true if the en passant move is valid, false otherwise
     */
    public boolean enPassant(int xPos, int yPos, Board board){
        Object[] previousMove = board.getPreviousMoves().get(board.getPreviousMoves().size() - 1);
        if (previousMove[0] instanceof Pawn) {
            return true;
        }
        return false;
    }


    /**
     * This method checks if the Pawn can capture a piece at the specified coordinates on the board
     * @param xPos the a-h position the Pawn wants to move to and capture
     * @param yPos the 1-8 position the Pawn wants to move to and capture
     * @param board the board where the Pawn is
     * @return true if Pawn can capture the given coordinates, false otherwise
     */
    @Override
    public boolean canCapture(int xPos, int yPos, Board board) {
        try {
            assert Math.abs(this.xPos - xPos) == 1 && Math.abs(this.yPos - yPos) == 1;
            assert board.getBoard()[yPos-1][xPos-1] != null;
            board.editBoard(this, this.xPos, this.yPos, xPos, yPos);
            this.yPos = yPos;
            this.xPos = xPos;
        } catch (AssertionError error) {
            System.out.println("Invalid capture");
            return false;
        }
        return true;
    }


    /**
     * This method determines if this Pawn is attacking a given piece on the given chessboard
     * @param piece the piece we might be attacking
     * @param board the chessboard
     * @return if we're attacking that piece or not
     */
    @Override
    public boolean isAttacking(Piece piece, Board board) { return this.canMove(piece.getXPos(), piece.getYPos(), board); }


    /**
     * This method return the color of the Pawn
     * @return the color of the Pawn "Black" or "White"
     */
    @Override
    public String getColor(){
        return this.color;
    }


    /**
     * This method returns a unique display of this Pawn, including its color
     * and position on the board
     * @return a unique display of the Pawn with its color and its position
     */
    @Override
    public String toString() {
        return UniversalMethods.changeColor(this.color) +
                " Pawn " +
                UniversalMethods.changeCord(xPos) + yPos + "\u001B[0m";
    }


}


