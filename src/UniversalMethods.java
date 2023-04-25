

public class UniversalMethods {
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
        throw new IllegalArgumentException("Wrong X-Coordinate, must be between 1-8 inclusive");
    }
    public static String changeColor(String color){
        if(color.equals("Black")) return "\u001B[47m" + "\u001B[30m";
        return "\u001B[47m";
    }
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
        throw new IllegalArgumentException("the coordinate entered is invalid must be between a-h");
    }
    public static boolean validMove(Board board, Piece piece, int x_cord, int y_cord){
        return board.getBoard()[x_cord][y_cord] == null;
    }

    public static boolean validCapture(Board board, Piece piece, int x_cord, int y_cord){
        return board.getBoard()[x_cord][y_cord] != null;
    }

    public static void getMove(String move){

    }
    public static String getPiece(String move){
        String pieceCord = move.substring(0,1).toLowerCase();
        switch (pieceCord){
            case "q": return "queen";
            case "k": return "king";
            case "b": return "bishop";
            case "n": return "knight";
            default: return "pawn";
        }
    }
    public static int[] getCord(String move){
        String cord = move.substring(move.length() - 2);
        String[] piece = cord.split("");
        return new int[]{changeLetCord(piece[0]), Integer.parseInt(piece[1])};
    }

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
    public static boolean isAttackingSquare(Piece piece, Board board, int xPos, int yPos){
        return piece.canMove(xPos, yPos, board);
    }
}
