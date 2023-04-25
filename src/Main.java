
public class Main {
    public static void main(String[] args) {


        System.out.println("This is the start of the chess app");

        test();


    }
    public static void test(){
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

        Piece cPawn = chessBoard.getBoard()[1][1];
        System.out.println(cPawn);
        System.out.println(cPawn.getXPos() + " " + cPawn.getYPos());
        cPawn.move(2,4, chessBoard);
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

        // bishop tests
        Piece thisBishop = chessBoard.getBoard()[0][2];
        System.out.println(thisBishop);
        System.out.println(thisBishop.getXPos() + " " + thisBishop.getYPos());

        // up left
        thisBishop.move(2,2, chessBoard);
        System.out.println(chessBoard);

        // up right
        thisBishop.move(4,4, chessBoard);
        System.out.println(chessBoard);

        // down right
        thisBishop.move(5,3, chessBoard);
        System.out.println(chessBoard);

        // up right
        thisBishop.move(1,7, chessBoard);
        System.out.println(chessBoard);

        // down right
        thisBishop.move(4,4, chessBoard);
        System.out.println(chessBoard);

        // down left
        thisBishop.move(1,1, chessBoard);
        System.out.println(chessBoard);

        thisBishop.canCapture(7,7, chessBoard);
        System.out.println(chessBoard);

        thisBishop.canCapture(6,8, chessBoard);
        System.out.println(chessBoard);

        thisBishop.canCapture(5,7, chessBoard);
        System.out.println(chessBoard);

        thisBishop.canCapture(7,7, chessBoard);
        System.out.println(chessBoard);
        // end of bishop tests bishop on h8

        // knight tests
        Piece thisKnight = chessBoard.getBoard()[0][1];
        System.out.println(thisKnight);
        System.out.println(thisKnight.getXPos() + " " + thisKnight.getYPos());

        thisKnight.move(3,3, chessBoard);
        System.out.println(chessBoard);

        thisKnight.move(5,4, chessBoard);
        System.out.println(chessBoard);

        thisKnight.move(6,6, chessBoard);
        System.out.println(chessBoard);

        thisKnight.canCapture(7,8, chessBoard);
        System.out.println(chessBoard);

        //invalid move test
        thisKnight.canCapture(8,7, chessBoard);
        System.out.println(chessBoard);

        thisKnight.move(8,6, chessBoard);
        System.out.println(chessBoard);

        //valid capture
        thisKnight.canCapture(6,7, chessBoard);
        System.out.println(chessBoard);

        //end of knight tests

        //queen tests
        Piece thisQueen = chessBoard.getBoard()[0][3];
        System.out.println(thisQueen);
        System.out.println(thisQueen.getXPos() + " " + thisQueen.getYPos());

        thisQueen.move(1,1, chessBoard);
        System.out.println(chessBoard);

        thisQueen.canCapture(8,8, chessBoard);
        System.out.println(chessBoard);

        thisQueen.canCapture(23,8, chessBoard);
        System.out.println(chessBoard);
    }
}



