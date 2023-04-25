public interface Piece {
    int getXPos(); // a-h position of the piece
    int getYPos(); //  1-8 position of the piece
    boolean canMove(int xPos, int yPos, Board board); // checks if a piece can move to a coordinate
    void move(int xPos, int yPos, Board board); // moves the piece
    boolean canCapture(int xPos, int yPos, Board board); // checks if a piece can capture at a position
    boolean isAttacking(Piece piece, Board board); // checks if a piece is attacking another piece(the parameter)
    String getColor(); // returns the color of the king "Black" or "White"
    @Override
    String toString(); // a unique display of the king with its color and its position
}
