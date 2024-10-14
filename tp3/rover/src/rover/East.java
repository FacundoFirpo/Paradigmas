package rover;

public class East extends Orientation {

    public void moveForward(Rover rover) {
        rover.moveEast();
    }

    public void moveBackwards(Rover rover) {
        rover.moveWest();
    }

    public Orientation turnLeft() {
        return new North();
    }

    public Orientation turnRight() {
        return new South();
    }

    public String toString() {
        return "East";
    }
}
