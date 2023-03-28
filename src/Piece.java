public interface Piece {
    int getXPos();
    int getYPos();
    boolean canMove(int xPos, int yPos);
    void move(int xPos, int yPos);
    boolean canCapture(int xPos, int yPos);
    boolean isAttacked();
    String getColor();
}
