package rover;

public class Coordinates {

    private int xPosition;
    private int yPosition;

    public Coordinates(int x, int y){
        xPosition = x;
        yPosition = y;
    }

    public int getX(){
        return xPosition;
    }

    public int getY(){
        return yPosition;
    }

    public void addYCoo() {
        yPosition++;
    }

    public void subYCoo() {
        yPosition--;
    }

    public void addXCoo() {
        xPosition++;
    }

    public void subXCoo() {
        xPosition--;
    }
}
