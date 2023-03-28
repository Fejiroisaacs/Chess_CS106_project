public class Rook implements Piece {
    private int xPos;
    private int yPos;
    private final String color;

    Rook(int xPos, int yPos, String color){
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
        return false;
    }

    @Override
    public void move(int xPos, int yPos, Board board){
        board.editBoard(this, this.xPos, this.yPos, xPos, yPos);
        this.yPos = yPos;
        this.xPos = xPos;
    }

    @Override
    public boolean canCapture(int xPos, int yPos, Board board) {
        return false;
    }

    @Override
    public boolean isAttacked() {
        return false;
    }

    @Override
    public String getColor() { return this.color; }
    @Override
    public String toString(){ return UniversalMethods.changeColor(this.color) + " Rook " + UniversalMethods.changeCord(xPos) + yPos + "\u001B[0m"; }
}
