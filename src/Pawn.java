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
        return yPos - this.yPos == 2 && this.firstMove || yPos - this.yPos == 1;
    }

    @Override
    public void move(int xPos, int yPos, Board board) {
        if(canMove(xPos, yPos, board)) pawnMove(board);
        else throw new IllegalArgumentException("Can't move there");

    }

    public void pawnMove(Board board){
        this.yPos += 1;
        board.editBoard(this, this.xPos, this.yPos, this.xPos, this.yPos);
    }

    @Override
    public boolean canCapture(int xPos, int yPos, Board board) {

        return false;
    }

    @Override
    public boolean isAttacked(Piece piece) { return false; }

    @Override
    public String getColor(){ return this.color; }
    @Override
    public String toString(){ return UniversalMethods.changeColor(this.color) + " Pawn " + UniversalMethods.changeCord(xPos) + yPos + "\u001B[0m"; }


}
