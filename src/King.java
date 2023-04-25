import java.util.ArrayList;

public class King implements Piece{
    private int xPos;
    private int yPos;
    private final String color;
    private final String[] kingMoves = new String[8];

    private final ArrayList<Piece> allOtherPieces = new ArrayList<>();

    /**
     *
     * @param xPos the kings position on  a - f scale
     * @param yPos the kings position on the 1 - 8 scale
     * @param color the kings color
     */
    public King(int xPos, int yPos, String color) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.color = color;
    }

    /**
     *  this method sets all the possible moves for the king
     */
    public void setKingMoves(){
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
            if(cord > 0 && cord%10 <= 8 && cord/10 <= 8)  kingMoves[counter] = String.valueOf(cord);
            else kingMoves[counter] = null;
            counter++;
        }

    }

    /**
     *
     * @return the a-h position of the king ( in coordinate form )
     */
    @Override
    public int getXPos() { return this.xPos; }

    /**
     *
     * @return the 1-8 position of the king
     */
    @Override
    public int getYPos() { return this.yPos; }

    /**
     *
     * @param xPos the a-h position the king wants to move to
     * @param yPos the 1-8 position the king wants to move to
     * @param board the board where the king is
     * @return if the king can move to the entered coordinate or not
     */
    @Override
    public boolean canMove(int xPos, int yPos, Board board) {
        if(xPos > 8 || xPos < 0 || yPos > 8 || yPos < 0) return false; // king cannot move off the board
        setKingMoves();
        int tryMove = (xPos * 10) + yPos; // converts the entered coordinate to the form which kingsMoves are saved
        for(String moves : this.kingMoves){ // checks if the entered coordinate matches any of the possible king moves
            if(String.valueOf(tryMove).equals(moves)) return true;

        }
        return false;
    }

    /**
     *
     * @param xPos the a-h position the king wants to move to
     * @param yPos the 1-8 position the king wants to move to
     * @param board the board where the king is
     */
    @Override
    public void move(int xPos, int yPos, Board board) {
        // checks if the coordinate the king wants to move to has a piece there
        // checks if the king can move to that position
        // checks if the square is attacked (king can't move to protected square)
        if(board.getBoard()[yPos-1][xPos-1] == null && canMove(xPos, yPos, board)
                && !squareProtected(board, xPos, yPos)) {
            board.editBoard(this, this.xPos, this.yPos, xPos, yPos);
            this.yPos = yPos;
            this.xPos = xPos;
        } else System.out.println("Can't move there");
        // won't throw this exception unless program would crash, unless we use try, catch... but no need for that
        //throw new IllegalArgumentException("Can't move there");
    }

    /**
     *
     * @param xPos the a-h position the king wants to move to
     * @param yPos the 1-8 position the king wants to move to
     * @param board the board where the king is
     */
    @Override
    public boolean canCapture(int xPos, int yPos, Board board) {
        if(xPos < 0 || yPos < 0 || yPos > 8 || xPos > 8){ // king cannot capture off the board
            System.out.println("Can't capture there");
            return false;
        }
        Piece currPiece = board.getBoard()[yPos-1][xPos-1];
        // checks if there's a piece where the king wants to capture
        // checks if the piece on that square is not the same color as the king ( cannot capture your own pieces )
        // checks if the piece is protected ( can't capture protected pieces )
        if(canMove(xPos,yPos,board)  && currPiece != null
                && !currPiece.getColor().equals(this.color)  && !squareProtected(board, xPos, yPos)){
            board.editBoard(this, this.xPos, this.yPos, xPos, yPos);
            this.yPos = yPos;
            this.xPos = xPos;
            return true;
        } else System.out.println("Can't capture there");
        //throw new IllegalArgumentException("Can't capture there");
        return false;
    }

    /**
     *
     * @param moveX the a-h position of the square the king wants to move to / capture on.
     * @param moveY the 1-8 position of the square the king wants to move to / capture on.
     * @param board the board where the pieces are.
     */
    private boolean squareProtected(Board board, int moveX, int moveY){
        for(Piece piece: this.allOtherPieces){
            if(UniversalMethods.isAttackingSquare(piece, board, moveX, moveY)) return true;
        }
        return false;
    }

    /**
     *
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
                if(!piece.getColor().equals(this.color)) this.allOtherPieces.add(piece);
            }
        }
    }

    /**
     *
     * @param board the chessboard
     * @param piece the piece that might be checking the king
     * @return if the king is checked or not
     */
    public boolean isChecked(Board board, Piece piece){
        return piece.canMove(this.xPos, this.yPos, board);
    }

    /**
     *
     * @param board the chessboard
     * @return if the king is checkmated or not
     */
    public boolean checkmated(Board board){

        setKingMoves(); // update king moves
        opponentsPieces(board); // updates opponents pieces
        boolean [] squareAttacked = new boolean[8]; // mapped with kingMoves

        // extended check for kings valid move
        for(String moves: this.kingMoves){
            int moveX = Integer.parseInt(moves) / 10;
            int moveY = Integer.parseInt(moves) % 10;
            int counter = 0;
            for(Piece piece: allOtherPieces){ // checks if any piece attacks a particular square the king can move to
                if(UniversalMethods.isAttackingSquare(piece, board, moveX, moveY)) squareAttacked[counter] = true;
                counter++;
            }
        }

        for(int i = 0; i < this.kingMoves.length; i++){ // checks if there's a square that is not attacked
            if(this.kingMoves[i] != null){
                if(!squareAttacked[i]) return false;
            }
        }

        return true;
    }


    /**
     *
     * @return the color of the king "Black" or "White"
     */
    @Override
    public String getColor() { return this.color; }

    /**
     *
     * @return a unique display of the king with its color and its position
     */
    @Override
    public String toString(){ return UniversalMethods.changeColor(this.color) + " King " + UniversalMethods.changeCord(xPos) + yPos + "\u001B[0m"; }
}
