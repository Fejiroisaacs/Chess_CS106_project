public class Knight implements Piece{
    private int xPos;
    private int yPos;
    private final String color;

    public Knight(int xPos, int yPos, String color) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.color = color;
    }

    @Override
    public int getXPos() {
        return 0;
    }

    @Override
    public int getYPos() {
        return 0;
    }

    @Override
    public boolean canMove(int xPos, int yPos, Board board) {
        return false;
    }

    @Override
    public void move(int xPos, int yPos) {
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
    public String getColor() {
        return null;
    }
}
