public class Player {
    private final String color;
    private boolean move;

    public Player(String color) {
        this.color = color;
    }
    public void setMove(boolean move){
        this.move = move;
    }
    public boolean getMove(){
        return this.move;
    }
    public String getColor() {
        return this.color;
    }
}
