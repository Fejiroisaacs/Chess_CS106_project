public class Bishop implements Piece{
    private int xPos;
    private int yPos;
    private final String color;

    public Bishop(int xPos, int yPos, String color) {
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
        return UniversalMethods.canMoveBishop(this.xPos, this.yPos, xPos, yPos, board);

//        // pre-conditions
//        assert  (Math.abs(this.xPos - xPos) > 0 && Math.abs(this.yPos - yPos) > 0);
//        assert  (Math.abs(this.xPos - xPos) < 8 && Math.abs(this.yPos - yPos) < 8);
//        if(Math.abs(this.xPos - xPos) - Math.abs(this.yPos - yPos) != 0) return false;
//        if(xPos < 0 || yPos < 0) return false;
//        // end of pre-conditions
//
//        int holdX = this.xPos;
//        int holdY = this.yPos;
//        int moveDiff = Math.abs(this.xPos - xPos);
//        if(xPos > this.xPos && yPos > this.yPos){
//            while(moveDiff > 1){
//                holdX++;
//                holdY++;
//                if(board.getBoard()[holdY-1][holdX-1] != null) return false;
//                moveDiff--;
//            }
//        } else if(xPos < this.xPos && yPos < this.yPos){
//            while(moveDiff > 1){
//                holdX--;
//                holdY--;
//                if(board.getBoard()[holdY-1][holdX-1] != null) return false;
//                moveDiff--;
//            }
//        } else if(xPos < this.xPos && yPos > this.yPos){
//            while(moveDiff > 1){
//                holdX--;
//                holdY++;
//                if(board.getBoard()[holdY-1][holdX-1] != null) return false;
//                moveDiff--;
//            }
//        }else if(xPos > this.xPos && yPos < this.yPos){
//            while(moveDiff > 1){
//                holdX++;
//                holdY--;
//                if(board.getBoard()[holdY-1][holdX-1] != null) return false;
//                moveDiff--;
//            }
//        }
//        System.out.println("this was true");
//        return true;
    }

    @Override
    public void move(int xPos, int yPos, Board board) {
        if(canMove(xPos, yPos, board) && board.getBoard()[yPos-1][xPos-1] == null) {
            board.editBoard(this, this.xPos, this.yPos, xPos, yPos);
            this.yPos = yPos;
            this.xPos = xPos;
        } else System.out.println("Can't move there");
        //throw new IllegalArgumentException("Can't move there")
    }

    @Override
    public boolean canCapture(int xPos, int yPos, Board board) {
        if(xPos < 0 || yPos < 0 || yPos > 8 || xPos > 8){
            System.out.println("Can't capture there");
            return false;
        }
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
    public boolean isAttacking(Piece piece, Board board) {
        return this.canMove(piece.getXPos(), piece.getYPos(), board);
    }

    @Override
    public String getColor() { return this.color; }
    @Override
    public String toString(){ return UniversalMethods.changeColor(this.color) + "Bishp " + UniversalMethods.changeCord(xPos) + yPos + "\u001B[0m"; }
}
