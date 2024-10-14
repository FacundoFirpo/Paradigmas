package rover;

public class North extends Orientation {
    public void moveForward(Rover rover) {
        rover.moveNorth();
    }

    public void moveBackwards(Rover rover) {
        rover.moveSouth();
    }

    public Orientation turnLeft() {
        return new West();
    }

    public Orientation turnRight() {
        return new East();
    }

    public String toString() {
        return "North";
    }
}
