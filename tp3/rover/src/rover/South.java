package rover;

public class South extends Orientation {

    public void moveForward(Rover rover) {
        rover.moveSouth();
    }

    public void moveBackwards(Rover rover) {
        rover.moveNorth();
    }

    public Orientation turnLeft() {
        return new East();
    }

    public Orientation turnRight() {
        return new West();
    }

    public String toString() {
        return "South";
    }
}
