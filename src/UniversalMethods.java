

public class UniversalMethods {


    /**
     * This method takes the x-position value between 1-8 inclusive and returns a
     * corresponding string value of the alphabet (a-h) as a chessboard coordinate
     * @param xPos the x-position value between 1-8 inclusive
     * @return a corresponding string value of the alphabet (a-h) as a chessboard coordinate
     */
    public static String changeCord(int xPos){
        switch (xPos){
            case 1 : return "a";
            case 2 : return "b";
            case 3 : return "c";
            case 4 : return "d";
            case 5 : return "e";
            case 6 : return "f";
            case 7 : return "g";
            case 8 : return "h";
        }
        // in case input is not between 1-8 inclusive, throw exception
        throw new IllegalArgumentException("Wrong X-Coordinate, must be between 1-8 inclusive");
    }


    /**
     * This method takes a string value of a chess piece color (either "Black" or "White")
     * and returns the color code for the color.
     * @param color a string value of a chess piece color (either "Black" or "White")
     * @return the color code for the color
     */
    public static String changeColor(String color){
        if(color.equals("Black")) return "\u001B[47m" + "\u001B[30m";
        return "\u001B[47m";
    }


    /**
     * This method takes a string value of a chessboard coordinate (a-h) and returns the
     * corresponding integer position value
     * @param cord a string value of a chessboard coordinate (a-h)
     * @return the corresponding integer position value
     */
    public static int changeLetCord(String cord){
        switch (cord){
            case "a": return 1;
            case "b": return 2;
            case "c": return 3;
            case "d": return 4;
            case "e": return 5;
            case "f": return 6;
            case "g": return 7;
            case "h": return 8;
        }
        // in case input is not between 1-8 inclusive, throw exception
        throw new IllegalArgumentException("the coordinate entered is invalid must be between a-h");
    }


    /**
     * This method takes a board object, a piece object, and the x and y coordinates of
     * the destination position. It returns a boolean value indicating whether the move
     * is valid or not
     * @param board a Board object
     * @param piece a Piece object
     * @param x_cord x-coordinate
     * @param y_cord y-coordinate
     * @return true if move is valid, false if move is not valid
     */
    public static boolean validMove(Board board, Piece piece, int x_cord, int y_cord){
        return piece.canMove(x_cord, y_cord, board) && board.getBoard()[y_cord][x_cord] == null;
    }


    /**
     * This method takes a board object, a piece object, and the x and y coordinates of
     * the destination position. It returns a boolean value indicating whether the move
     * results in a valid capture or not
     * @param board a Board object
     * @param piece a Piece object
     * @param x_cord x-coordinate
     * @param y_cord y-coordinate
     * @return true if move is a valid capture, false if move is not a valid capture
     */
    public static boolean validCapture(Board board, Piece piece, int x_cord, int y_cord){
        return piece.canCapture(x_cord, y_cord, board);
    }


    /**
     *
     * @param player
     * @param board
     * @param move
     */
    public static void move(Player player, Board board, String move){
        assert move.length() < 10 && move.length() > 4 ;
        assert board != null;
        boolean isCapture = false;
        move = move.toLowerCase();

        if(move.contains("x")){
            isCapture = true;
            move = move.replace("x","");
        }

        String specialValue = move.substring(move.length()-1);
        if(specialValue.equals("+") || specialValue.equals("#")) move = move.replace(specialValue, "");
        if(move.length() == 5) {
            Object[][] pieceMove = getMoveSequence(move);
            Piece thisPiece = board.getBoard()[(int) pieceMove[1][1]-1][(int) pieceMove[1][0]-1];

            if(!thisPiece.getColor().equals(player.getColor())) {
                System.out.println("Can't move " + thisPiece.getColor() +"'s pieces");
                return;
            }

            if (isCapture) thisPiece.canCapture((int) pieceMove[2][0], (int) pieceMove[2][1], board);
            else thisPiece.move((int) pieceMove[2][0], (int) pieceMove[2][1], board);


            if (specialValue.equals("+")) {
                if (player.isChecked(thisPiece)) System.out.println("King in check");
                else System.out.println("king is not in check");
            } else if (specialValue.equals("#")) {
                if (player.isCheckmated(thisPiece)) player.setMated();
                else System.out.println("king is not checkmated");
            }

            board.setPreviousMoves(new Object[]{thisPiece, move});

        } else System.out.println("Move sequence entered is invalid. Contains illegal characters or too many characters");
    }




    /**
     * This method takes a string value of a move (e.g., "q", "k", "b", or "n") and returns
     * the name of the piece being moved (e.g., "pawn", "knight", "bishop", "rook", "queen",
     * or "king")
     * @param move a string value of a move (e.g., "q", "k", "b", or "n")
     * @return the name of the piece being moved (e.g., "pawn", "knight", "bishop", "rook",
     * "queen", or "king")
     */
    public static String getPiece(String move){
        String pieceCord = move.substring(0,1).toLowerCase();
        switch (pieceCord){
            case "q": return "Queen";
            case "k": return "King";
            case "b": return "Bishop";
            case "n": return "Knight";
            default: return "Pawn";
        }
    }


    /**
     *
     * @param move a string value of a move
     * @return the x and y coordinates of the destination position
     */
    public static Object[][] getMoveSequence(String move){

        String pieceType = getPiece(move.substring(0,1));

        String initCord = move.substring(move.length() - 4, move.length() - 2);
        Object[] initialMoveArr = {changeLetCord(initCord.substring(0,1)), Integer.parseInt(initCord.substring(1,2))};

        String finalCord = move.substring(move.length() - 2);
        Object[] finalMoveArr = {changeLetCord(finalCord.substring(0,1)), Integer.parseInt(finalCord.substring(1,2))};

        return new Object[][] {{pieceType}, initialMoveArr, finalMoveArr};
    }


    /**
     * This method takes the current x and y coordinates, the x and y coordinates of the
     * destination position, and a board object. It returns a boolean value indicating
     * whether the move is valid for a rook piece.
     * @param thisxPos the current x coordinate
     * @param thisyPos the current y coordinate
     * @param xPos the x coordinate of the destination position
     * @param yPos the y coordinate of the destination position
     * @param board a Board object
     * @return true if move is valid for a rook piece, false if move is not valid for a rook piece
     */
    public static boolean canMoveRook(int thisxPos, int thisyPos, int xPos, int yPos, Board board){
        // pre-conditions
        if (Math.abs(thisxPos - xPos) > 0 && Math.abs(thisyPos - yPos) > 0) return false ; // both x and y positions cannot change
        if(xPos < 0 || yPos < 0 || yPos > 8 || xPos > 8) return false;
        // end of pre-conditions

        int hold = thisxPos;

        if (xPos > thisxPos) {
            while(hold < xPos-1){
                hold++;
                if(board.getBoard()[thisyPos-1][hold-1] != null) return false;
            }
        } else if (xPos < thisxPos) {
            while(hold > xPos+1){
                hold--;
                if(board.getBoard()[thisyPos-1][hold-1] != null) return false;
            }
        } else if (yPos > thisyPos) {
            hold = thisyPos;
            while(hold < yPos-1){
                hold++;
                if(board.getBoard()[hold-1][thisxPos-1] != null) return false;
            }
        } else if (yPos < thisyPos) {
            hold = thisyPos;
            while(hold > yPos+1){
                hold--;
                if(board.getBoard()[hold-1][thisxPos-1] != null) return false;
            }
        }
        return true;
    }


    /**
     * This method takes the current x and y coordinates, the x and y coordinates of the
     * destination position, and a board object. It returns a boolean value indicating
     * whether the move is valid for a bishop piece
     * @param thisxPos the current x coordinate
     * @param thisyPos the current y coordinate
     * @param xPos the x coordinate of the destination position
     * @param yPos the y coordinate of the destination position
     * @param board a Board object
     * @return true if move is valid for a bishop piece, false if move is not valid for a bishop piece
     */
    public static boolean canMoveBishop(int thisxPos, int thisyPos, int xPos, int yPos, Board board){
        // pre-conditions
        assert  (Math.abs(thisxPos - xPos) > 0 && Math.abs(thisyPos - yPos) > 0);
        assert  (Math.abs(thisxPos - xPos) < 8 && Math.abs(thisyPos - yPos) < 8);
        if(Math.abs(thisxPos - xPos) - Math.abs(thisyPos - yPos) != 0) return false;
        if(xPos < 0 || yPos < 0 || yPos > 8 || xPos > 8) return false;
        // end of pre-conditions

        int holdX = thisxPos;
        int holdY = thisyPos;
        int moveDiff = Math.abs(thisxPos - xPos);
        if(xPos > thisxPos && yPos > thisyPos){
            while(moveDiff > 1){
                holdX++;
                holdY++;
                if(board.getBoard()[holdY-1][holdX-1] != null) return false;
                moveDiff--;
            }
        } else if(xPos < thisxPos && yPos < thisyPos){
            while(moveDiff > 1){
                holdX--;
                holdY--;
                if(board.getBoard()[holdY-1][holdX-1] != null) return false;
                moveDiff--;
            }
        } else if(xPos < thisxPos && yPos > thisyPos){
            while(moveDiff > 1){
                holdX--;
                holdY++;
                if(board.getBoard()[holdY-1][holdX-1] != null) return false;
                moveDiff--;
            }
        }else if(xPos > thisxPos && yPos < thisyPos){
            while(moveDiff > 1){
                holdX++;
                holdY--;
                if(board.getBoard()[holdY-1][holdX-1] != null) return false;
                moveDiff--;
            }
        }
        return true;
    }


    /**
     * This method determines whether a given piece is attacking a square at a specified
     * position on the board
     * @param piece the piece to check for attacks
     * @param board the current Board object
     * @param xPos the x coordinate of the square to check
     * @param yPos the y coordinate of the square to check
     * @return true if the given piece in the given board is attacking the square at
     * (xPos, yPos), false otherwise
     */
    public static boolean isAttackingSquare(Piece piece, Board board, int xPos, int yPos){
        return piece.canMove(xPos, yPos, board);
    }


}


