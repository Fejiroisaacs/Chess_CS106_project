public class Queen implements Piece{
    private int xPos;
    private int yPos;
    private final String color;

    public Queen(int xPos, int yPos, String color) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.color = color;
    }

    @Override
    public int getXPos() { return this.xPos; }

    @Override
    public int getYPos() { return this.yPos; }

    @Override
    public boolean canMove(int xPos, int yPos, Board board) {
        return UniversalMethods.canMoveBishop(this.xPos, this.yPos, xPos, yPos, board)
                ||
                UniversalMethods.canMoveRook(this.xPos, this.yPos, xPos, yPos, board);
    }

    @Override
    public void move(int xPos, int yPos, Board board){
        if(canMove(xPos, yPos, board) && board.getBoard()[yPos-1][xPos-1] == null) {
            board.editBoard(this, this.xPos, this.yPos, xPos, yPos);
            this.yPos = yPos;
            this.xPos = xPos;
        } else System.out.println("Can't move there");
        //throw new IllegalArgumentException("Can't move there");

    }

    @Override
    public boolean canCapture(int xPos, int yPos, Board board) {
        if(xPos < 0 || yPos < 0 || yPos > 8 || xPos > 8){
            System.out.println("Can't capture there");
            return false;
        }
        Piece currPiece = board.getBoard()[yPos-1][xPos-1];
        if(canMove(xPos,yPos,board)  && currPiece != null && !currPiece.getColor().equals(this.color)){
            board.editBoard(this, this.xPos, this.yPos, xPos, yPos);
            this.yPos = yPos;
            this.xPos = xPos;
            return true;
        } else System.out.println("Can't capture there");
        //throw new IllegalArgumentException("Can't capture there");
        return false;
    }

    @Override
    public boolean isAttacked(Piece piece) {return false;}

    @Override
    public String getColor() { return this.color; }
    @Override
    public String toString(){ return UniversalMethods.changeColor(this.color) + "Queen " + UniversalMethods.changeCord(xPos) + yPos + "\u001B[0m"; }
}
