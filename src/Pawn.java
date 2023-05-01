public class Pawn implements Piece{
    private int xPos;
    private int yPos;
    private final String color;
    private boolean firstMove = true;

    Pawn(int xPos, int yPos, String color){
        this.color = color;
        this.xPos = xPos;
        this.yPos = yPos;
    }
    @Override
    public int getXPos() { return this.xPos; }

    @Override
    public int getYPos() { return this.yPos; }

    @Override
    public boolean canMove(int xPos, int yPos, Board board) {
        return ((Math.abs(yPos - this.yPos) == 2 && this.firstMove) || (Math.abs(yPos - this.yPos) == 1)) && board.getBoard()[yPos-1][xPos-1] == null;
    }

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

    public boolean enPassant(int xPos, int yPos, Board board){
        Object[] previousMove = board.getPreviousMoves().get(board.getPreviousMoves().size() - 1);
        if(previousMove[0] instanceof Pawn) return true;
        return false;
    }
    @Override
    public boolean canCapture(int xPos, int yPos, Board board) {
        try{
            assert Math.abs(this.xPos - xPos) == 1 && Math.abs(this.yPos - yPos) == 1;
            assert board.getBoard()[yPos-1][xPos-1] != null;
            board.editBoard(this, this.xPos, this.yPos, xPos, yPos);
            this.yPos = yPos;
            this.xPos = xPos;
        } catch (AssertionError error){
            System.out.println("Invalid capture");
            return false;
        }
        return true;
    }

    @Override
    public boolean isAttacking(Piece piece, Board board) { return this.canMove(piece.getXPos(), piece.getYPos(), board); }

    @Override
    public String getColor(){ return this.color; }
    @Override
    public String toString(){ return UniversalMethods.changeColor(this.color) + " Pawn " + UniversalMethods.changeCord(xPos) + yPos + "\u001B[0m"; }


}
