package rover;

public abstract class Orientation {

    public abstract void moveForward(Rover rover);
    public abstract void moveBackwards(Rover rover);

    public abstract Orientation turnLeft();
    public abstract Orientation turnRight();

    public abstract String toString();
}
