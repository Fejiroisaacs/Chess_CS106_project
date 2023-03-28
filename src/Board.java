import java.util.Arrays;

public class Board {
    private Piece[][] board;

    Board(){
        board = new Piece[8][8]; // instantiating the board
        //adding the black and white pawns
        for (int j = 0; j < 8; j++) {
            board[1][j] = new Pawn(j+1, 1, "White");
            board[6][j] = new Pawn(j+1, 7, "Black");
        }

        //adding the black rooks
        board[7][0] = new Rook(1, 8, "Black");
        board[7][7] = new Rook(8, 8, "Black");
        //adding the black knights
        board[7][1] = new Knight(7, 8, "Black");
        board[7][6] = new Knight(2, 8, "Black");
        //adding the black Bishops
        board[7][2] = new Bishop(6, 8, "Black");
        board[7][5] = new Bishop(3, 8, "Black");
        //adding the black queen
        board[7][4] = new King(5, 8, "Black");
        //Adding the black queen
        board[7][3] = new Queen(4, 8, "Black");

        //adding white rooks
        board[0][0] = new Rook(1, 1, "White");
        board[0][7] = new Rook(8, 1, "White");
        //adding the white knights
        board[0][1] = new Knight(2, 1, "White");
        board[0][6] = new Knight(7, 1, "White");
        //adding the white Bishops
        board[0][2] = new Bishop(3, 1, "White");
        board[0][5] = new Bishop(6, 1, "White");
        //adding the white queen
        board[0][4] = new King(5, 1, "White");
        //Adding the white queen
        board[0][3] = new Queen(4, 1, "White");
    }

    public void editBoard(Piece piece, int initX, int initY, int finalX, int finalY){
        board[initX-1][initY-1] = null;
        board[finalX+1][finalY+1] = piece;
    }

    public Piece[][] getBoard() { return board; }

    public String toString() {
        StringBuilder value = new StringBuilder();
        value.append("----------------------------------------------------------------------------------------");
        value.append("\n");
        for(int i = 7; i >= 0; i--){
            value.append("|");
            for(int j = 0; j < 8; j++){
                Piece hold = board[i][j];
                if(hold != null) {
                    value.append(hold).append(" | ");
                } else {
                    value.append("   ").append(UniversalMethods.changeCord(i + 1)).append(j).append("   ").append(" | ");
                }
            }
            value.append("\n");
            value.append("----------------------------------------------------------------------------------------");
            value.append("\n");
        }

        return value.toString();
    }

}
