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
        return false;
    }

    @Override
    public void move(int xPos, int yPos, Board board) {
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
    public String toString(){ return "Queen " + UniversalMethods.changeCord(xPos) + yPos; }
}
