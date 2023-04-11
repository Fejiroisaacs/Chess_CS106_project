
public class Main {
    public static void main(String[] args) {

        System.out.println("This is the start of the chess app");
        Board chessBoard = new Board();
        Player white = new Player("White", chessBoard);
        Player black = new Player("Black", chessBoard);
        System.out.println(chessBoard);

    }
}



