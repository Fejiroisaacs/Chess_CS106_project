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
}
