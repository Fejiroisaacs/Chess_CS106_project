public class Pawn implements Piece{
    private int xPos;
    private int yPos;
    private final String color;
    private boolean firstMove=true;

    private boolean enPassantMove=false;


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

        // pawns cannot move backwards
        boolean conditionWhite = this.color.equals("White") && this.yPos < yPos;
        boolean conditionBlack = this.color.equals("Black") && this.yPos > yPos;

        // condition for basic move
        boolean mainCondition = (Math.abs(yPos - this.yPos) == 2 && this.firstMove) || (Math.abs(yPos - this.yPos) == 1);

        return (conditionWhite || conditionBlack) &&  mainCondition;
    }

    /**
     * this method edits the chess board
     * @param xPos the a-h position the Pawn wants to move to
     * @param yPos the 1-8 position the Pawn wants to move to
     * @param board the board where the Pawn is
     */
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

        // can only En passant **this** pawn on first move if it moves two squares up
        if(this.firstMove && Math.abs(yPos - this.yPos) == 2) this.enPassantMove=true;
        else if(!this.firstMove) this.enPassantMove=false; // can't En passant after the first move

        if(canMove(xPos, yPos, board) && board.getBoard()[yPos-1][xPos-1] == null){
            this.firstMove = false;
            moveHelper(xPos, yPos, board);
        } else {
            throw new IllegalArgumentException("Can't move there, invalid move -- Pawn class");
        }
        canPromote(board);
    }

    /**
     * this method checks if a pawn can be promoted after a move (in case the user doesn't specify a promotion) then
     * promotes the pawn to a queen
     * @param board the board where the Pawn is
     *
     */
    private void canPromote(Board board){
        if(this.yPos == 8) promote("q", this.xPos, this.yPos, board);
    }

    /**
     * this method promotes a pawn to a specified piece name -- default promotion is a queen
     * @param pieceName the name of the piece we want to promote to
     * @param xPos the a-h position the Pawn wants to move to
     * @param yPos the 1-8 position the Pawn wants to move to
     * @param board the board where the Pawn is
     *
     */
    public void promote(String pieceName, int xPos, int yPos, Board board){
        Piece piece;

        switch (pieceName) {
            case "b":
                piece = new Bishop(this.xPos, this.yPos, this.color);
                break;
            case "r":
                piece = new Rook(this.xPos, this.yPos, this.color);
                break;
            case "n":
                piece = new Knight(this.xPos, this.yPos, this.color);
                break;
            default:
                piece = new Queen(this.xPos, this.yPos, this.color);
        }
        board.getBoard()[yPos-1][xPos-1] = piece;
    }

    /**
     * This method determines whether the En passant move is valid for **this** pawn
     * @param board the board where the Pawn is
     * @return true if the en passant move is valid, false otherwise
     */
    public boolean enPassant(Board board){
        if(board.getPreviousMoves().size() < 4) return false; // can't En passant in 4 moves

        // gets the previous move + piece and checks if it's a pawn, and it played an En passant-able move
        Object[] previousMove = board.getPreviousMoves().get(board.getPreviousMoves().size() - 1);
        Piece otherPawn = (Piece) previousMove[0];

        // return if the piece is a pawn, and it made an enPassant move
        return otherPawn instanceof Pawn && ((Pawn) otherPawn).enPassantMove;
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
        if(xPos < 0 || yPos < 0 || yPos > 8 || xPos > 8){
            throw new IllegalArgumentException("Can't capture off the board");
        }

        // pawns can only capture one square diagonally
        boolean captureCondition = Math.abs(this.xPos - xPos) == 1 && Math.abs(this.yPos - yPos) == 1;

        if(!captureCondition) return false; // invalid capture, terminate

        // gets the piece we want to capture
        Piece currPiece = board.getBoard()[yPos-1][xPos-1];

        // checks if there's a piece where the pawn wants to capture
        // checks if the piece on that square is not the same color as the pawn ( cannot capture your own pieces )
        // cannot capture a king
        if(currPiece != null && !currPiece.getColor().equals(this.color)
                && !(currPiece instanceof King)){
            moveHelper(xPos, yPos, board);
            canPromote(board);
            this.firstMove = false;
            return true;
        }
        // no piece on basic capturing position because for en passant, the pawn is next to **this** pawn
        else if(currPiece == null && this.enPassant(board)){

            // if white, the pawn we want to en passant is 1 coordinate behind, if black, it is 1 coordinate ahead
            int deltaY = this.color.equals("White") ? -1 : 1;
            currPiece = board.getBoard()[yPos - 1 + deltaY][xPos - 1];

            // if piece is null, can't en passant
            if(currPiece != null && !currPiece.getColor().equals(this.color)){
                board.getBoard()[yPos - 1 + deltaY][xPos - 1] = null; // removes en passant-ed piece
                moveHelper(xPos, yPos, board); // edit the board
                System.out.println("Steezy En passant"); // so steezyyyyy
                canPromote(board); // checks if the piece can promote then it promotes it to a queen automatically
                return true;
            }

        } else{
            throw new IllegalArgumentException("Can't capture. No piece there or square protected or piece is not a threat.");
        }

        canPromote(board);

        return false;
    }


    /**
     * This method determines if this Pawn is attacking a given piece on the given chessboard
     * @param piece the piece we might be attacking
     * @param board the chessboard
     * @return if we're attacking that piece or not
     */
    @Override
    public boolean isAttacking(Piece piece, Board board) { return this.canCapture(piece.getYPos(), piece.getXPos(), board); }


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


