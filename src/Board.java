import java.util.Arrays;

public class Board {
    private Piece[][] board;

    Board(){
        board = new Piece[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = new Rook(i, j, "White");
            }
        }
    }

    public Piece[][] getBoard() { return board; }
    public String toString()
    {
        StringBuilder value = new StringBuilder();
        // Loop through all rows
        for (Piece[] row : board) {

            // Loop through all columns of current row
            for (Piece x : row)
                value.append(x).append("  ");

            value.append("\n");
        }
        return value.toString();
    }
}
