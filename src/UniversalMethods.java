

public class UniversalMethods {


    /**
     * This method takes the x-position value between 1-8 inclusive and returns a
     * corresponding string value of the alphabet (a-h) as a chessboard coordinate
     *
     * @param xPos the x-position value between 1-8 inclusive
     * @return a corresponding string value of the alphabet (a-h) as a chessboard coordinate
     */
    public static String changeCord(int xPos) {
        switch (xPos) {
            case 1:
                return "a";
            case 2:
                return "b";
            case 3:
                return "c";
            case 4:
                return "d";
            case 5:
                return "e";
            case 6:
                return "f";
            case 7:
                return "g";
            case 8:
                return "h";
        }
        // in case input is not between 1-8 inclusive, throw exception
        throw new IllegalArgumentException("Wrong X-Coordinate, must be between 1-8 inclusive");
    }


    /**
     * This method takes a string value of a chess piece color (either "Black" or "White")
     * and returns the color code for the color.
     *
     * @param color a string value of a chess piece color (either "Black" or "White")
     * @return the color code for the color
     */
    public static String changeColor(String color) {
        if (color.equals("Black")) return "\u001B[47m" + "\u001B[30m";
        return "\u001B[47m";
    }


    /**
     * This method takes a string value of a chessboard coordinate (a-h) and returns the
     * corresponding integer position value
     *
     * @param cord a string value of a chessboard coordinate (a-h)
     * @return the corresponding integer position value
     */
    public static int changeLetCord(String cord) {
        switch (cord) {
            case "a":
                return 1;
            case "b":
                return 2;
            case "c":
                return 3;
            case "d":
                return 4;
            case "e":
                return 5;
            case "f":
                return 6;
            case "g":
                return 7;
            case "h":
                return 8;
        }
        // in case input is not between 1-8 inclusive, throw exception
        throw new IllegalArgumentException("The coordinate entered is invalid must be between a-h");
    }


    /**
     * This method takes a Player object, a Board object, and a string representation
     * of a chess move, and executes the move on the board if it is valid. If the move is not
     * valid, the method prints an error message.
     *
     * @param player a Player object representing the player making the move
     * @param board  a Board object representing the current state of the game board
     * @param move   a string representation of the move to be executed
     */
    public static void move(Player player, Player otherPlayer, Board board, String move) {
        assert move.length() < 10 && move.length() > 4;
        assert board != null;
        boolean isCapture = false;
        move = move.toLowerCase();

        if (move.contains("x")) {
            isCapture = true;
            move = move.replace("x", "");
        }

        if (move.length() < 5) {
            throw new IllegalArgumentException("Invalid move entry");
        }

        String specialValue = "";

        // pawn wants to promote -- incomplete
        if (move.contains("=")) {
            assert move.startsWith("p");

            if (move.endsWith("+") || move.endsWith("#")) {
                specialValue = move.substring(move.length() - 1);
                move = move.replace(specialValue, "");
            }

            move = move.replace("=", "");


            String pieceName = move.substring(move.length() - 1);
            move = move.replace(pieceName, "");

            if (move.length() == 5) {
                Object[][] pieceMove = getMoveSequence(move);
                Piece thisPiece = board.getBoard()[(int) pieceMove[1][1] - 1][(int) pieceMove[1][0] - 1];

                if (thisPiece == null) throw new IllegalArgumentException("No piece on specified coordinate");

                Pawn thisPawn = (Pawn) thisPiece;

                if (!thisPawn.getColor().equals(player.getColor())) {
                    throw new IllegalArgumentException("Can't move " + thisPawn.getColor() + "'s pieces");
                }

                if (!specialValue.equals("#") && !specialValue.equals("+")) {
                    if (isCapture) thisPiece.canCapture((int) pieceMove[2][0], (int) pieceMove[2][1], board);
                    else thisPiece.move((int) pieceMove[2][0], (int) pieceMove[2][1], board);
                }

                if (specialValue.equals("+")) {
                    if (otherPlayer.isChecked(thisPiece)) System.out.println("King in check");
                    else System.out.println("king is not in check");
                } else if (specialValue.equals("#")) {
                    if (otherPlayer.isCheckmated(thisPiece)) otherPlayer.setMated();
                    else System.out.println("king is not checkmated");
                }

                thisPawn.promote(pieceName, (int) pieceMove[2][0], (int) pieceMove[2][1], board);
                board.setPreviousMoves(new Object[]{thisPawn, move, isCapture});
            }

            return;
        }


        specialValue = move.substring(move.length() - 1);

        if (specialValue.equals("+") || specialValue.equals("#")) move = move.replace(specialValue, "");
        if (move.length() == 5) {
            Object[][] pieceMove = getMoveSequence(move);
            Piece thisPiece = board.getBoard()[(int) pieceMove[1][1] - 1][(int) pieceMove[1][0] - 1];
            if (thisPiece == null) throw new IllegalArgumentException("No piece on specified coordinate");

            if (!thisPiece.getColor().equals(player.getColor())) {
                throw new IllegalArgumentException("Can't move " + thisPiece.getColor() + "'s pieces");
            }

            if (isCapture) thisPiece.canCapture((int) pieceMove[2][0], (int) pieceMove[2][1], board);
            else thisPiece.move((int) pieceMove[2][0], (int) pieceMove[2][1], board);

            if (specialValue.equals("+")) {
                if (otherPlayer.isChecked(thisPiece)) System.out.println("King in check");
                else System.out.println("king is not in check");
            } else if (specialValue.equals("#")) {
                System.out.println(otherPlayer.isCheckmated(thisPiece));
                if (otherPlayer.isCheckmated(thisPiece)) otherPlayer.setMated();
                else System.out.println("king is not checkmated");
            }

            board.setPreviousMoves(new Object[]{thisPiece, move, isCapture});

        } else {
            throw new IllegalArgumentException("Move sequence entered is invalid. Contains illegal characters or too many characters");
        }
    }


    /**
     * This method takes a string value of a move (e.g., "q", "k", "b", or "n") and returns
     * the name of the piece being moved (e.g., "pawn", "knight", "bishop", "rook", "queen",
     * or "king")
     *
     * @param move a string value of a move (e.g., "q", "k", "b", or "n")
     * @return the name of the piece being moved (e.g., "pawn", "knight", "bishop", "rook",
     * "queen", or "king")
     */
    public static String getPiece(String move) {
        String pieceCord = move.substring(0, 1).toLowerCase();
        switch (pieceCord) {
            case "q":
                return "Queen";
            case "k":
                return "King";
            case "b":
                return "Bishop";
            case "n":
                return "Knight";
            default:
                return "Pawn";
        }
    }


    /**
     * This method takes a string representation of a chess move and returns a 2D array containing
     * the sequence of moves to be made. The 2D array contains information on the start position,
     * the end position, and the piece type.
     *
     * @param move a string representation of a chess move
     * @return a 2D array containing the sequence of moves to be made
     */
    public static Object[][] getMoveSequence(String move) {

        String pieceType = getPiece(move.substring(0, 1));

        String initCord = move.substring(move.length() - 4, move.length() - 2);
        Object[] initialMoveArr = {changeLetCord(initCord.substring(0, 1)), Integer.parseInt(initCord.substring(1, 2))};

        String finalCord = move.substring(move.length() - 2);
        Object[] finalMoveArr = {changeLetCord(finalCord.substring(0, 1)), Integer.parseInt(finalCord.substring(1, 2))};

        return new Object[][]{{pieceType}, initialMoveArr, finalMoveArr};
    }


    /**
     * This method takes the current x and y coordinates, the x and y coordinates of the
     * destination position, and a board object. It returns a boolean value indicating
     * whether the move is valid for a rook piece.
     *
     * @param thisxPos the current x coordinate
     * @param thisyPos the current y coordinate
     * @param xPos     the x coordinate of the destination position
     * @param yPos     the y coordinate of the destination position
     * @param board    a Board object
     * @return true if move is valid for a rook piece, false if move is not valid for a rook piece
     */
    public static boolean canMoveRook(int thisxPos, int thisyPos, int xPos, int yPos, Board board) {
        // pre-conditions
        if (Math.abs(thisxPos - xPos) > 0 && Math.abs(thisyPos - yPos) > 0)
            return false; // both x and y positions cannot change
        if (xPos < 0 || yPos < 0 || yPos > 8 || xPos > 8) return false;
        // end of pre-conditions

        int hold = thisxPos;

        if (xPos > thisxPos) {
            while (hold < xPos - 1) {
                hold++;
                if (board.getBoard()[thisyPos - 1][hold - 1] != null) return false;
            }
        } else if (xPos < thisxPos) {
            while (hold > xPos + 1) {
                hold--;
                if (board.getBoard()[thisyPos - 1][hold - 1] != null) return false;
            }
        } else if (yPos > thisyPos) {
            hold = thisyPos;
            while (hold < yPos - 1) {
                hold++;
                if (board.getBoard()[hold - 1][thisxPos - 1] != null) return false;
            }
        } else if (yPos < thisyPos) {
            hold = thisyPos;
            while (hold > yPos + 1) {
                hold--;
                if (board.getBoard()[hold - 1][thisxPos - 1] != null) return false;
            }
        }
        return true;
    }


    /**
     * This method takes the current x and y coordinates, the x and y coordinates of the
     * destination position, and a board object. It returns a boolean value indicating
     * whether the move is valid for a bishop piece
     *
     * @param thisxPos the current x coordinate
     * @param thisyPos the current y coordinate
     * @param xPos     the x coordinate of the destination position
     * @param yPos     the y coordinate of the destination position
     * @param board    a Board object
     * @return true if move is valid for a bishop piece, false if move is not valid for a bishop piece
     */
    public static boolean canMoveBishop(int thisxPos, int thisyPos, int xPos, int yPos, Board board) {
        // pre-conditions
        assert (Math.abs(thisxPos - xPos) > 0 && Math.abs(thisyPos - yPos) > 0);
        assert (Math.abs(thisxPos - xPos) < 8 && Math.abs(thisyPos - yPos) < 8);
        if (Math.abs(thisxPos - xPos) - Math.abs(thisyPos - yPos) != 0) return false;
        if (xPos < 0 || yPos < 0 || yPos > 8 || xPos > 8) return false;
        // end of pre-conditions

        int holdX = thisxPos;
        int holdY = thisyPos;
        int moveDiff = Math.abs(thisxPos - xPos);
        if (xPos > thisxPos && yPos > thisyPos) {
            while (moveDiff > 1) {
                holdX++;
                holdY++;
                if (board.getBoard()[holdY - 1][holdX - 1] != null) return false;
                moveDiff--;
            }
        } else if (xPos < thisxPos && yPos < thisyPos) {
            while (moveDiff > 1) {
                holdX--;
                holdY--;
                if (board.getBoard()[holdY - 1][holdX - 1] != null) return false;
                moveDiff--;
            }
        } else if (xPos < thisxPos && yPos > thisyPos) {
            while (moveDiff > 1) {
                holdX--;
                holdY++;
                if (board.getBoard()[holdY - 1][holdX - 1] != null) return false;
                moveDiff--;
            }
        } else if (xPos > thisxPos && yPos < thisyPos) {
            while (moveDiff > 1) {
                holdX++;
                holdY--;
                if (board.getBoard()[holdY - 1][holdX - 1] != null) return false;
                moveDiff--;
            }
        }
        return true;
    }


    /**
     * This method determines whether a given piece is attacking a square at a specified
     * position on the board
     *
     * @param piece the piece to check for attacks
     * @param board the current Board object
     * @param xPos  the x coordinate of the square to check
     * @param yPos  the y coordinate of the square to check
     * @return true if the given piece in the given board is attacking the square at
     * (xPos, yPos), false otherwise
     */
    public static boolean isAttackingSquare(Piece piece, Board board, int xPos, int yPos) {
        return piece.canMove(xPos, yPos, board);
    }

    /**
     * this method determines if a player can castle or not.
     *
     * @param board  the chessboard
     * @param player the current player
     * @param move   the castle move
     */
    public static void castle(Board board, Player player, String move) {

        if (move.length() < 3 || move.length() > 6)
            throw new IllegalArgumentException("Invalid castle move"); // invalid move

        boolean isCheck = false;

        if (move.contains("+")) { // if we castle into a check on the opponents king, need to keep track of it
            isCheck = true;
            move = move.replace("+", "");
        }

        King king = player.getMyking(); // gets this players king
        Rook rook;

        if (move.equals("o-o-o")) { // queen side castle

            // getting the right rook
            if (player.getColor().equals("White")) rook = (Rook) board.getBoard()[0][0];
            else rook = (Rook) board.getBoard()[7][0];

            // if there's no king or rook or king has moved or rook has moved, it's an invalid castle
            if (rook == null || king == null || king.getHasMoved() || rook.getHasMoved())
                throw new IllegalArgumentException("Invalid castling");

            if (isCheck) {
                if (queenSideCastle(king, rook, board)) {
                    king.discoveryCheck(board);
                }
            } else {
                queenSideCastle(king, rook, board);
            }
        } else if (move.equals("o-o")) { // king side castle

            // getting the right rook
            if (player.getColor().equals("White")) rook = (Rook) board.getBoard()[0][7];
            else rook = (Rook) board.getBoard()[7][7];

            // if there's no king or rook or king has moved or rook has moved, it's an invalid castle
            if (rook == null || king == null || king.getHasMoved() || rook.getHasMoved())
                throw new IllegalArgumentException("Invalid castling");

            if (isCheck) {
                if (kingSideCastle(king, rook, board)) {
                    king.discoveryCheck(board);
                }
                return;
            }
            kingSideCastle(king, rook, board);
        } else throw new IllegalArgumentException("Invalid castle move");
    }

    /**
     * this method determines if a player can perform a queen side castle
     *
     * @param king  the current player's king
     * @param rook  the current player's rook
     * @param board the chessboard
     * @return if we made a successful castle, throws exception otherwise
     */
    public static boolean queenSideCastle(King king, Rook rook, Board board) {

        // adds the castling move to the set of previous moves
        Object[] castle = new Object[]{null, "o-o-o"};
        board.setPreviousMoves(castle);

        if (king.getColor().equals("White")) { // checks if squares in-between king and rook are being attacked
            if (king.squareNotProtected(board, 2, 1) && king.squareNotProtected(board, 3, 1)
                    && king.squareNotProtected(board, 4, 1)) {

                rook.move(4, 1, board); // move the rook then jump the king
                king.setCoordinate(3, 1, board);

            }
        } else if (king.getColor().equals("Black")) { // checks if squares in-between king and rook are being attacked
            if (king.squareNotProtected(board, 2, 8) && king.squareNotProtected(board, 3, 8)
                    && king.squareNotProtected(board, 4, 8)) {

                rook.move(4, 8, board); // move the rook then jump the king
                king.setCoordinate(3, 8, board);

            }
        } else {
            board.getPreviousMoves().remove(castle); // if we can't castle, remove the castling move from the list of previous moves
            throw new IllegalArgumentException("Can't castle, square protected");
        }

        return true;
    }

    /**
     * this method determines if a player can perform a king side castle
     *
     * @param king  the current player's king
     * @param rook  the current player's rook
     * @param board the chessboard
     * @return if we made a successful castle, throws exception otherwise
     */
    public static boolean kingSideCastle(King king, Rook rook, Board board) {

        // adds the castling move to the set of previous moves
        Object[] castle = new Object[]{null, "o-o"};
        board.setPreviousMoves(castle);

        if (king.getColor().equals("White")) { // checks if squares in-between king and rook are being attacked
            if (king.squareNotProtected(board, 6, 1) && king.squareNotProtected(board, 7, 1)) {

                rook.move(6, 1, board); // move the rook then jump the king
                king.setCoordinate(7, 1, board);

            }
        } else if (king.getColor().equals("Black")) { // checks if squares in-between king and rook are being attacked
            if (king.squareNotProtected(board, 6, 8) && king.squareNotProtected(board, 7, 8)) {

                rook.move(6, 8, board); // move the rook then jump the king
                king.setCoordinate(7, 8, board);

            }
        } else {
            board.getPreviousMoves().remove(castle); // if we can't castle, remove the castling move from the list of previous moves
            throw new IllegalArgumentException("Can't castle, square protected");
        }

        return true;
    }


}



