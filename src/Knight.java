public class Knight implements Piece{
    private int xPos;
    private int yPos;
    private final String color;
    private String[] knightMoves = new String[8];

    public Knight(int xPos, int yPos, String color) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.color = color;
    }

    @Override
    public int getXPos() { return this.xPos; }

    @Override
    public int getYPos() { return this.yPos; }

    public void setKnightMoves(){
        int[] hold = new int[8];

        // corner cases here dealt with in enhanced for-loop below
        hold[0] = (this.xPos + 2) * 10 + (this.yPos + 1);
        hold[1] = (this.xPos + 1) * 10 + (this.yPos + 2);
        hold[2] = (this.xPos - 2) * 10 + (this.yPos + 1);
        hold[3] = (this.xPos - 1) * 10 + (this.yPos + 2);
        if(this.yPos >= 1) { //corner case 1
            hold[4] = (this.xPos + 2) * 10 + (this.yPos - 1);
            hold[6] = (this.xPos - 2) * 10 + (this.yPos - 1);
        }
        if(this.yPos >= 2) { // corner case 2
            hold[5] = (this.xPos + 1) * 10 + (this.yPos - 2);
            hold[7] = (this.xPos - 1) * 10 + (this.yPos - 2);
        }

        int counter = 0;
        for(int cord: hold){ // don't want to add invalid moves to the move list
            if(cord > 0 && cord%10 <= 8 && cord/100 <= 8)  this.knightMoves[counter] = String.valueOf(cord);
            counter++;
        }

    }
    @Override
    public boolean canMove(int xPos, int yPos, Board board) {
        if(xPos > 8 || xPos < 0 || yPos > 8 || yPos < 0) return false; // knight cannot move off the board
        setKnightMoves();
        int tryMove = (xPos * 10) + yPos;
        for(String moves : this.knightMoves){
            if(String.valueOf(tryMove).equals(moves)) return true;
        }
        return false;
    }
    @Override
    public void move(int xPos, int yPos, Board board){

        if(board.getBoard()[yPos-1][xPos-1] == null && canMove(xPos, yPos, board)) {
            board.editBoard(this, this.xPos, this.yPos, xPos, yPos);
            this.yPos = yPos;
            this.xPos = xPos;
        } else System.out.println("Can't move there");
        //throw new IllegalArgumentException("Can't move there");

    }

    @Override
    public boolean canCapture(int xPos, int yPos, Board board) {
        Piece currPiece = board.getBoard()[yPos-1][xPos-1];
        if(canMove(xPos,yPos,board)  && currPiece != null && !currPiece.getColor().equals(this.color)){
            board.editBoard(this, this.xPos, this.yPos, xPos, yPos);
            this.yPos = yPos;
            this.xPos = xPos;
            return true;
        } else System.out.println("Can't capture there");
        //throw new IllegalArgumentException("Can't capture there");
        return false;
    }

    @Override
    public boolean isAttacked(Piece piece) {
        return false;
    }

    @Override
    public String getColor() { return this.color; }
    @Override
    public String toString(){ return UniversalMethods.changeColor(this.color) + "Knigt " + UniversalMethods.changeCord(xPos) + yPos + "\u001B[0m"; }
}
