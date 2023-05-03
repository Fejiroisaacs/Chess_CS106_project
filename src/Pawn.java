public class Pawn implements Piece{
    private int xPos;
    private int yPos;
    private final String color;
    private boolean firstMove=true;

    private boolean enPassantMove= false;


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
        // condition for basic move
        return (Math.abs(yPos - this.yPos) == 2 && this.firstMove) || (Math.abs(yPos - this.yPos) == 1)
                && board.getBoard()[yPos-1][xPos-1] == null;
    }

    private void moveHelper(int xPos, int yPos, Board board){
        board.editBoard(this, this.xPos, this.yPos, xPos, yPos );
        this.yPos = yPos;
        this.xPos = xPos;
    }

    /**
     * This method moves the Pawn to the specified coordinates on the board
     * @param xPos the a-h position the Pawn wants to move to
     * @param yPos the 1-8 position the Pawn wants to move to
     * @param board the board where the Pawn is
     */
    @Override
    public void move(int xPos, int yPos, Board board) {

        if(this.firstMove && Math.abs(yPos - this.yPos) == 2) this.enPassantMove=true;
        else if(!this.firstMove) this.enPassantMove=false;

//        if(this.enPassant(xPos, yPos, board)){
//            int deltaY = this.color.equals("White") ? 1 : -1;
//            moveHelper(xPos, yPos+deltaY, board);
//        }
        if(canMove(xPos, yPos, board)){
            this.firstMove = false;
            moveHelper(xPos, yPos, board);
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
        if(board.getPreviousMoves().size() < 1) return false;

        Object[] previousMove = board.getPreviousMoves().get(board.getPreviousMoves().size() - 1);
        Piece otherPawn = (Piece) previousMove[0];
        if (otherPawn instanceof Pawn && ((Pawn) otherPawn).enPassantMove) {
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
        if (xPos < 0 || yPos < 0 || yPos > 8 || xPos > 8){
            System.out.println("Can't capture off the board");
            return false;
        }
        boolean captureCondition = Math.abs(this.xPos - xPos) == 1 && Math.abs(this.yPos - yPos) == 1;
        if(!captureCondition) return false;

        Piece currPiece = board.getBoard()[yPos-1][xPos-1];

        // checks if there's a piece where the pawn wants to capture
        // checks if the piece on that square is not the same color as the pawn ( cannot capture your own pieces )
        if(currPiece != null && !currPiece.getColor().equals(this.color)){
            moveHelper(xPos, yPos, board);
            return true;
        } else if(currPiece == null && this.enPassant(xPos,yPos,board) ){
            int deltaY = this.color.equals("White") ? -1 : 1;
            currPiece = board.getBoard()[yPos - 1 + deltaY][xPos - 1]; // make tests for this
            System.out.println(currPiece);
            if(currPiece != null){
                moveHelper(xPos, yPos + deltaY, board);
                System.out.println("I captured");
                return  true;
            }

        } else System.out.println("Can't capture there. No piece there or piece is not an opponent");
        //throw new IllegalArgumentException("Can't capture there");
        return false;
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


