package rover;

public class West extends Orientation {

    public void moveForward(Rover rover) {
        rover.moveWest();
    }

    public void moveBackwards(Rover rover) {
        rover.moveEast();
    }

    public Orientation turnLeft() {
        return new South();
    }

    public Orientation turnRight() {
        return new North();
    }

    public String toString() {
        return "West";
    }
}
