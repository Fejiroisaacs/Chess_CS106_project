
public class Main {
    public static void main(String[] args) {

        System.out.println("This is the start of the chess app");
        Board chessBoard = new Board();
        Player white = new Player("White", chessBoard);
        Player black = new Player("Black", chessBoard);

        //pawn move tests
        System.out.println(chessBoard);
        Piece thePawn = chessBoard.getBoard()[1][0];
        System.out.println(thePawn);
        System.out.println(thePawn.getXPos() + " " + thePawn.getYPos());
        thePawn.move(1,4, chessBoard);
        System.out.println(chessBoard);

        Piece otherPawn = chessBoard.getBoard()[6][0];
        System.out.println(otherPawn);
        System.out.println(otherPawn.getXPos() + " " + otherPawn.getYPos());
        otherPawn.move(1,5, chessBoard);
        System.out.println(chessBoard);

        thePawn.move(1,5, chessBoard);
        System.out.println(chessBoard);
        // end of pawn tests

        //rook move tests
        //move up
        Piece thisRook = chessBoard.getBoard()[0][0];
        System.out.println(thisRook);
        System.out.println(thisRook.getXPos() + " " + thisRook.getYPos());
        thisRook.move(1,3, chessBoard);
        System.out.println(chessBoard);

        //move right
        thisRook.move(8,3, chessBoard);
        System.out.println(chessBoard);

        //move left
        thisRook.move(5,3, chessBoard);
        System.out.println(chessBoard);

        //move left
        thisRook.move(1,3, chessBoard);
        System.out.println(chessBoard);

        //move down
        thisRook.move(1,1, chessBoard);
        System.out.println(chessBoard);

        //illegal move test
        thisRook.move(25,3, chessBoard);
        System.out.println(chessBoard);

        // rook capture tests
        // rook up
        thisRook.move(1,3, chessBoard);
        System.out.println(chessBoard);

        //move right
        thisRook.move(5,3, chessBoard);
        System.out.println(chessBoard);

        // rook up
        thisRook.move(5,5, chessBoard);
        System.out.println(chessBoard);

        thisRook.move(2,5, chessBoard);
        System.out.println(chessBoard);
        // rook capture
        thisRook.canCapture(1,5, chessBoard);
        System.out.println(chessBoard);
        // rook capture
        thisRook.canCapture(1,8, chessBoard);
        System.out.println(chessBoard);
        // rook capture
        thisRook.canCapture(2,8, chessBoard);
        System.out.println(chessBoard);
        // end of rook tests
    }
}



