public class UniversalMethods {
    public static String changeCord(int xPos){
        if(xPos == 1){
            return "a";
        } else if(xPos == 2){
            return "b";
        } else if (xPos == 3) {
            return "c";
        }else if (xPos == 4) {
            return "d";
        }else if (xPos == 5) {
            return "e";
        }else if (xPos == 6) {
            return "f";
        }else if (xPos == 7) {
            return "g";
        }else if (xPos == 8) {
            return "h";
        } else {
            throw new IllegalArgumentException("Wrong X-Coordinate");
        }
    }
    public static String changeColor(String color){
        if(color.equals("Black")) return "\u001B[47m" + "\u001B[30m";
        return "\u001B[47m";
    }
}
