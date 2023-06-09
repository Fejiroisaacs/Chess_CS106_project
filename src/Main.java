import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        System.out.println("This is the start of the chess app\n");

        howToUse();

        // run tests some errors would happen, ignore them
        try {
            test();
        } catch (Exception ignored){ }

        playChess();

    }


    /**
     * This method explains how to use this program
     */
    public static void howToUse(){

        // start by creating the players and the chessboard
        Board chessBoard = new Board(); // the chessboard
        Player white = new Player("White", chessBoard); // white player
        Player black = new Player("Black", chessBoard); // black player

        System.out.println("This is the chessboard representation");

        System.out.println(chessBoard);

        System.out.println("Getting to know the pieces, input purposes.\n");

        System.out.println("Piece notations");
        System.out.println("K - King \nQ - Queen \nR - Rook \nB - Bishop \nN - Knight \nP - Pawn\n");

        System.out.println("To move a piece, enter the piece notation, i.e. k or q (case doesn't matter)\n");

        System.out.println("Then, enter the position of the piece, i.e. pd2 (referring to the pawn on d2 at the start of the game)\n");

        System.out.println("Then, enter the square (position) you want to move the piece to, i.e. pd2d4 (The start of the famous london system)\n");

        System.out.println("Results of previous tested move");

        // moving the piece
        UniversalMethods.move(white, black, chessBoard, "pd2d4");

        System.out.println(chessBoard);

        System.out.println("To capture a piece, enter the piece notation, it's position, the capture symbol (x), and the position we want to capture on");
        System.out.println("i.e. pd4xe5 \n");

        System.out.println("Now, lets move \n");

        // putting a piece on the square we want to capture on
        UniversalMethods.move(black, white, chessBoard, "pe7e5");
        System.out.println(chessBoard);

        // now, lets capture
        System.out.println("Now, capture");
        UniversalMethods.move(white, black, chessBoard, "pd4xe5");

        System.out.println(chessBoard);

        System.out.println("To check, enter the move as normal but add a '+' symbol at the end of your move entry");
        System.out.println("i.e., bf8b4+, here we move blacks bishop to check the king\n");

        UniversalMethods.move(black, white, chessBoard, "bf8b4+");

        System.out.println(chessBoard);

        System.out.println("If you want to checkmate, do the same as above but enter '#' instead of '+'\n");

        System.out.println("To castle, enter the move o-o (king side castle) or o-o-o (queen side castle)\n");

        System.out.println("For promoting, enter your move as usual but add '=' and the name of the piece you want to promote to");
        System.out.println("i.e. pd7d8=R (promoting the d7 pawn to a rook)");
        System.out.println("Note: only pawns can promote. You can promote into a check or checkmate i.e pd7d8=R(+/#)");
        System.out.println("You can also capture into a promotion i.e. pd7xe8=R. Check/checkmate is also allowed. Just add the symbol at the end i.e. pd7xe8=R(+/#)\n");

        System.out.println("Other notes:\n");
        System.out.println("For general piece moves, ensure you specify a piece then its location(square), then a capture if you are capturing");
        System.out.println("Then the location (square) you are trying to capture on, then a promotion if you are promoting, and finally a check/checkmate\n");

        System.out.println("Have fun using my chess app :)\n\n\n\n");

    }

    /**
     * this method creates the chessboard, both players, and starts the game
     */
    public static void playChess(){

        Board chessBoard = new Board(); // the chessboard
        Player white = new Player("White", chessBoard); // white player
        Player black = new Player("Black", chessBoard); // black player



        System.out.println("---------------------------------------  CHESS  ----------------------------------------");
        System.out.println(chessBoard); // prints the chessboard

        // here, we get the first move, white's move. we do this here because of our implementation of switching
        // player moves in the gameInProgress method
        String move = someHelperFunction(white, chessBoard); // gets the users move and checks valid moves

        while(chessBoard.getPreviousMoves().isEmpty()){ // loop until chessboard
            try {
                UniversalMethods.move(white, black , chessBoard, move); // tries to move a piece
            } catch (Exception exception){

                String error = exception.toString().substring(36);
                System.out.println(error);

                move = someHelperFunction(white, chessBoard); // gets the move
            }
        }

        // switches the turn to black
        white.setMyTurn(false);
        black.setMyTurn(true);

        System.out.println(chessBoard);

        gameInProgress(chessBoard, white, black);


    }

    /**
     *  this method keeps the game playing
     * @param chessBoard the chessboard we are playing on
     * @param white the first player
     * @param black the second player
     */
    public static void gameInProgress(Board chessBoard, Player white, Player black){
        boolean hasEnded = false;

        // loop until game ends. Game ends after either player has been checkmated
        while(!hasEnded) {

            boolean success = false; // condition for success explained below

            while(!success) {
                Piece lastMovedPiece = (Piece) chessBoard.getPreviousMoves().get(chessBoard.getPreviousMoves().size()-1)[0];
                int currentSize = chessBoard.getPreviousMoves().size(); // getting the initial size of the previous

                try {
                    String move = someHelperFunction(white, chessBoard); // gets a valid move

                    while(kingInCheck(chessBoard, white, black, move)){
                        System.out.println("The move you entered keeps your king in check/ is invalid (piece is pinned)");
                        move = someHelperFunction(white, chessBoard);
                    }

                    // checks if the move is a castle
                    if(move.contains("o-o")){
                        UniversalMethods.castle(chessBoard, white.getTurn() ? white : black, move); // try to castle
                    } else{
                        // moves the piece
                        UniversalMethods.move(white.getTurn() ? white : black, white.getTurn() ? black : white, chessBoard, move);
                    }

                } catch (Exception exception) {
                    String error = exception.toString().substring(35);
                    System.out.println(error);
                }

                // while loop is only successful if a piece has actually been moved and has been added to the list of
                // previous moves
                // the second condition checks if the previous moves list was updated since the loop started.
                // if it was, then the board changed and either user made a valid move.
                if (lastMovedPiece != null && (lastMovedPiece.getColor().equals(white.getTurn() ? white.getColor() : black.getColor())
                        || chessBoard.getPreviousMoves().size() > currentSize) ){

                    success = true;

                }

                System.out.println(chessBoard); // display the chessboard after moving
            }

            // setting players turn
            if (white.getTurn()) {
                // its white's turn, so we switch to black's turn
                white.setMyTurn(false);
                black.setMyTurn(true);
            } else {
                // its black's turn, so we switch back to white's turn
                white.setMyTurn(true);
                black.setMyTurn(false);
                chessBoard.setPreviousBoardPositions(); // set the previous board positions after black plays
            }

            // checking if either player was mated or stalemated.
            if(white.getIsMated()){
                System.out.println("Black wins");
                hasEnded = true;
            } else if(black.getIsMated()){
                System.out.println("White wins");
                hasEnded = true;
            } else if (white.getTurn() && white.isStalemated()) {
                System.out.println("Game over, a draw, black stalemated");
                hasEnded = true;
            } else if(black.getTurn() && black.isStalemated()){
                System.out.println("Game over, a draw, white stalemated");
                hasEnded = true;
            } else if (DrawChecks.threefoldDraw(chessBoard)) {
                System.out.println("A draw, threefold repetition");
                hasEnded = true;
            } else if (DrawChecks.fiftyMove(chessBoard)) {
                System.out.println("A draw, fifty move rule");
                hasEnded = true;
            }

        }
    }

    /**
     * this method gets the users move returns if it is a valid move. uses another method to determine
     * validity of the move
     * @param white first player
     * @param board the chess board
     * @return the users move
     */
    private static String someHelperFunction(Player white, Board board){

        Scanner userInput = new Scanner(System.in); // the input reader

        // displays whose turn it is
        if(white.getTurn()) System.out.println("White's turn, enter your move: ");
        else System.out.println("Black's turn, enter your move: ");

        String move = userInput.next(); // stores the entered move
        boolean validMove = validMoveTest(move, board); // checks if the entered move is valid

        if (!validMove){ // if the first move is invalid we get new moves until we get a valid move
            while(!validMove){
                System.out.println("Invalid entry, try again");

                if(white.getTurn()) System.out.println("White's turn, enter your move: ");
                else System.out.println("Black's turn, enter your move: ");

                move = userInput.next(); // gets the new entered move
                validMove = validMoveTest(move, board); // checks if the new move entered is valid
            }
        }
        return move; // returns the users move
    }

    /**
     * this method checks the entered move and determines if it is valid or not
     * @param move the entered move
     * @return if a move is valid or not
     */
    private static boolean validMoveTest(String move, Board board){
        move = move.toLowerCase();

        if(move.length() < 3 || move.length() > 9) return false;

        if(move.contains("x")){ // captures take precedence
            if(move.indexOf("x") != 3) return false; // always appears in the 4th position of the string. If not, it's an invalid move
            move = move.replace("x", ""); // remove the capture symbol
        }

        if(move.contains("+")){ // checks for checks symbol
            if(move.indexOf("+") != move.length()-1) return false; // if the check symbol isn't at the end, it's an invalid move
            move = move.replace("+", ""); // removes the check sign
        }

        if(!move.contains("x") && move.contains("o-o")){ // can't castle and capture at the same time
            return move.equals("o-o") || move.equals("o-o-o"); // check if it's a queen or king side castle
        }

        if(move.contains("#")){ // checks for the checkmate symbol
            if(move.indexOf("#") != move.length()-1) return false; // if the checkmate symbol isn't at the end, it's an invalid move
            move = move.replace("#",""); // removes the checkmate symbol
        }

        if(move.contains("=")){ // checks for the promotes symbol
            if(move.indexOf("=") != move.length()-2) return false; // if the promotes symbol doesn't precede another character, it's an invalid move
            move = move.substring(0, move.length()-2); // removes the promotes symbol and the piece we want to promote to.
            // no need to check if the piece entered is valid because we auto promote to a queen if other pieces aren't specified.
        }

        if(move.length() != 5) return false; // after filtering the entered move, we check if it has a length of 5. if not, it's an invalid move
        // why? because we specify the name of the piece i.e. p,1,k,n,b then the position of the piece x,y (letter, number)
        // then the coordinate the piece is trying to go to, same explanation as earlier.

        // checks if the first letter is one of the move is a valid piece
        String firstLetter = move.substring(0,1); // gets the first letter of the move
        if(!(firstLetter.equals("p") || firstLetter.equals("q") || firstLetter.equals("k")
         || firstLetter.equals("n") || firstLetter.equals("b") || firstLetter.equals("r"))) return false;

        // now, we check the remaining characters, if the second and fourth coordinate are letters and if the
        // third and fifth digits are numbers
        boolean validInputSequence =  Character.isLetter(move.charAt(1)) && Character.isDigit(move.charAt(2))
                && Character.isLetter(move.charAt(3)) && Character.isDigit(move.charAt(4));

        // checks if there's a piece on a specified position after we've checked if the entered move is valid
        if(validInputSequence) return pieceOnSpecifiedPos(move.substring(1,3), board);

        return false; // returns false if there is no pieceOnSpecifiedPosition
    }

    /**
     * this method returns if there's a piece on a given coordinate or not
     * @param cord the coordinate of the board we want to check if there is a piece on
     * @param board the chess board
     * @return if there's a piece on the specified position
     */
    public static boolean pieceOnSpecifiedPos(String cord, Board board){
        if(cord.length() != 2) return false; // if the entered coordinate isn't of length 2, returns false

        // gets the conversion between
        int[] initialMoveArr = {UniversalMethods.changeLetCord(cord.substring(0,1)), Integer.parseInt(cord.substring(1,2))};

        // gets the piece on the specified position
        Piece thisPiece = board.getBoard()[initialMoveArr[1]-1][initialMoveArr[0]-1];

        // returns if there's a piece or not
        return thisPiece != null;
    }

    /**
     * this method determines if a king is in check or not
     * @param board the chessboard
     * @param white the first player
     * @param black the second player
     * @param move the move we want to check if removes king from check
     * @return if the king is in check or not
     */
    public static boolean kingInCheck(Board board, Player white, Player black, String move){
        if(white.getTurn()){ // if either king is not in check, return false
            if(!white.getMyking().discoveryCheck(board)) return false;
        } else if(black.getTurn()){
            if(!black.getMyking().discoveryCheck(board)) return false;
        }

        Board testBoard = board; // use a helper board because we don't want to change the original board

        UniversalMethods.move(white.getTurn() ? white : black, white.getTurn() ? black : white, testBoard, move);

        if(white.getTurn()){ // after we have edited the fake board, we check if the king is still in check
            return white.getMyking().discoveryCheck(testBoard);
        } else if(black.getTurn()){
            return black.getMyking().discoveryCheck(testBoard);
        } else return false;
    }

    /**
     * this method runs some tests, used for debugging
     */
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



