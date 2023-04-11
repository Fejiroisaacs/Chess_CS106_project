import java.util.ArrayList;

public class Player {
    private final String color;
    private boolean myTurn;
    private final Board board;
    private final ArrayList<Piece> myPieces = new ArrayList<>();

    public Player(String color, Board board) {
        this.color = color;
        this.board = board;
        if(color.equals("White")) myTurn = true;
    }
    public void setMyTurn(boolean myTurn){
        this.myTurn = myTurn;
    }
    public boolean getMove(){
        return this.myTurn;
    }
    public void updatePieces() {
        for (int i = 7; i >= 0; i--) {
            for (int j = 0; j < 8; j++) {
                Piece currPiece = board.getBoard()[i][j];
                if (currPiece.getColor().equals(this.color)) {
                    myPieces.add(currPiece);
                }
            }
        }
    }
//    public boolean isCheckmated(){
//        Piece king = myPieces.get(myPieces.indexOf(King));
//    }
    public String getColor() {
        return this.color;
    }
}
