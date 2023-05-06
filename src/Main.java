import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        System.out.println("This is the start of the chess app");

        //test();

        playChess();

    }

    public static void playChess(){

        Board chessBoard = new Board();
        Player white = new Player("White", chessBoard);
        Player black = new Player("Black", chessBoard);

        Scanner userInput = new Scanner(System.in);
        String move = "";
        boolean hasEnded = false;
        boolean hasMoved;


        System.out.println("Chess");
        System.out.println(chessBoard);

        move = someHelperFunction(white, userInput, chessBoard);
        while(chessBoard.getPreviousMoves().isEmpty()){
            try {
                UniversalMethods.move(white, black , chessBoard, move);
            } catch (Exception e){
                System.out.println("some error happened, invalid input?");

                move = someHelperFunction(white, userInput, chessBoard);
            }
        }
        white.setMyTurn(false);
        black.setMyTurn(true);

        System.out.println(chessBoard);

        while(!hasEnded) {
            hasMoved = false;

            move = someHelperFunction(white, userInput, chessBoard);

            boolean success = false;

            while(!success) {
                try {

                    Piece lastMovedPiece = (Piece) chessBoard.getPreviousMoves().get(chessBoard.getPreviousMoves().size()-1)[0];
                    UniversalMethods.move(white.getTurn() ? white : black, white.getTurn() ? black : white, chessBoard, move);

                    if (lastMovedPiece.getColor().equals(white.getTurn() ? white.getColor() : black.getColor()))
                        hasMoved = true;
                    success = true;
                } catch (Exception exception) {
                    System.out.println("some error happened, Invalid input?");
                    move = someHelperFunction(white, userInput, chessBoard);
                }
            }

            if(hasMoved) {
                // setting players turn
                if (white.getTurn()) {
                    white.setMyTurn(false);
                    black.setMyTurn(true);
                    System.out.println("got here instead");
                } else {
                    System.out.println("Should be here");
                    white.setMyTurn(true);
                    black.setMyTurn(false);
                }
            }

            // checking if either player was mated.
            if(white.getIsMated()){
                System.out.println("Black wins");
                hasEnded = true;
            } else if(black.getIsMated()){
                System.out.println("White wins");
                hasEnded = true;
            }
            System.out.println(chessBoard);
        }

    }

    /**
     *
     * @param white first player
     * @param userInput the Scanner
     * @param board the chess board
     * @return the users move
     */
    private static String someHelperFunction(Player white, Scanner userInput, Board board){

        // showing whose turn it is
        if(white.getTurn()) System.out.println("Whites turn, enter your move: ");
        else System.out.println("Blacks turn, enter your move: ");

        String move = userInput.next();
        boolean validMove = partialMoveTest(move, board);

        if (!validMove){
            while(!validMove){
                System.out.println("Invalid entry");
                if(white.getTurn()) System.out.println("Whites turn, enter your move: ");
                else System.out.println("Blacks turn, enter your move: ");
                move = userInput.next();
                validMove = partialMoveTest(move, board);
            }
        }
        return move;
    }
    /**
     *
     * @param move the entered move
     * @return if a move is valid or not
     */
    private static boolean partialMoveTest(String move, Board board){
        move = move.toLowerCase();

        if(move.length() < 5) return false;

        if(move.contains("x")){
            if(move.indexOf("x") != 3) return false;
            move = move.replace("x", "");
        }

        if(move.contains("+")){
            if(move.indexOf("+") != move.length()-1) return false;
            move = move.replace("+", "");
        }

        if(move.contains("#")){
            if(move.indexOf("#") != move.length()-1) return false;
            move = move.replace("#","");
        }

        if(move.contains("=")){
            if(move.indexOf("=") != move.length()-2) return false;
            move = move.substring(0, move.length()-2);
        }

        if(move.length() != 5) return false;

        boolean validInputSequence =  Character.isLetter(move.charAt(0)) && Character.isLetter(move.charAt(1)) &&
                Character.isDigit(move.charAt(2)) && Character.isLetter(move.charAt(3)) && Character.isDigit(move.charAt(4));

        if(validInputSequence) return pieceOnSpecifiedPos(move.substring(1,3), board);

        return false;
    }

    /**
     *
     * @param cord the coordinate of the board we want to check if there is a piece on
     * @param board the chess board
     * @return if there's a piece on the specified position
     */
    public static boolean pieceOnSpecifiedPos(String cord, Board board){
        if(cord.length() != 2) return false;

        Object[] initialMoveArr = {UniversalMethods.changeLetCord(cord.substring(0,1)), Integer.parseInt(cord.substring(1,2))};

        Piece thisPiece = board.getBoard()[(int) initialMoveArr[1]-1][(int) initialMoveArr[0]-1];

        if(thisPiece == null) System.out.println("No piece on specified input");

        return thisPiece != null;
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

        King thisKing = (King) chessBoard.getBoard()[7][4];
        System.out.println(thisKing);

        thisKing.move(6,8,chessBoard);
        System.out.println(chessBoard);

        thisKing.canCapture(5,7,chessBoard);
        System.out.println(chessBoard);

        System.out.println(thisKing.checkmated(chessBoard));

        thisQueen.move(5,7, chessBoard);
        System.out.println(chessBoard);

        thisKing.move(6,8, chessBoard);
        System.out.println(chessBoard);

        thisKing.move(4,6, chessBoard);
        System.out.println(chessBoard);

        thisKing.canCapture(6,7, chessBoard);
        System.out.println(chessBoard);

        thisKing.move(7,8, chessBoard);
        System.out.println(chessBoard);

        thisQueen.move(6,6, chessBoard);
        System.out.println(chessBoard);

        thisKing.canCapture(6,6, chessBoard);
        System.out.println(chessBoard);



        UniversalMethods.move(white, black, chessBoard, "pf2f4");
        System.out.println(chessBoard);

        UniversalMethods.move(white, black, chessBoard, "rb8xc8");
        System.out.println(chessBoard);

        //enPassant tests for white
//        UniversalMethods.move(white, chessBoard, "pg2g4");
//        System.out.println(chessBoard);
//
//        UniversalMethods.move(white, chessBoard, "pg4g5");
//        System.out.println(chessBoard);
//
//        UniversalMethods.move(black, chessBoard, "ph7h5");
//        System.out.println(chessBoard);
//
//        UniversalMethods.move(white, chessBoard, "pg5xh6");
//        System.out.println(chessBoard);
//
//        UniversalMethods.move(white, chessBoard, "ph6h5");
//        System.out.println(chessBoard);

        //enPassant tests for black
        UniversalMethods.move(black, white, chessBoard, "ph7h5");
        System.out.println(chessBoard);

        UniversalMethods.move(black, white, chessBoard, "ph5h4");
        System.out.println(chessBoard);

        UniversalMethods.move(white, black, chessBoard, "pg2g4");
        System.out.println(chessBoard);

        UniversalMethods.move(black, white, chessBoard, "ph4xg3");
        System.out.println(chessBoard);

        UniversalMethods.move(black, white, chessBoard, "pg3g4");
        System.out.println(chessBoard);

        // promoting pawn tests
        UniversalMethods.move(white, black, chessBoard, "ph2h4");
        System.out.println(chessBoard);
        UniversalMethods.move(white, black, chessBoard, "ph4h5");
        System.out.println(chessBoard);
        UniversalMethods.move(white, black, chessBoard, "ph5h6");
        System.out.println(chessBoard);
        UniversalMethods.move(white, black, chessBoard, "ph6h7");
        System.out.println(chessBoard);
        UniversalMethods.move(white, black, chessBoard, "ph7h8=q");
        System.out.println(chessBoard);


        // promoted piece tests -- default promoting is a queen
        UniversalMethods.move(white, black, chessBoard, "qh8xd8");
        System.out.println(chessBoard);

        // pieces can capture king -- fixed but still having a null pointer exception thrown here, need to fix
        UniversalMethods.move(white, black, chessBoard, "qd8f6+");
        System.out.println(chessBoard);


    }
}



