package rover;

public class Backwards extends Instructions {

    public void work(Rover rover) {
        rover.moveBackwards();
    }

    public boolean letter(char order) {
        return order == 'b';
    }
}
