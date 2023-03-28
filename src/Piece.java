public interface Piece {
    int getXPos();
    int getYPos();
    boolean canMove(int xPos, int yPos, Board board);
    void move(int xPos, int yPos);
    boolean canCapture(int xPos, int yPos, Board board);
    boolean isAttacked();
    String getColor();
    @Override
    String toString();
}
