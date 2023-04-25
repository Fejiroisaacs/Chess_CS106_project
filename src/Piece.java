public interface Piece {
    int getXPos();
    int getYPos();
    boolean canMove(int xPos, int yPos, Board board);
    void move(int xPos, int yPos, Board board);
    boolean canCapture(int xPos, int yPos, Board board);
    boolean isAttacking(Piece piece, Board board);
    String getColor();
    @Override
    String toString();
}
