package rover;

public class Forward extends Instructions {

    public void work(Rover rover) {
        rover.moveForward();
    }

    public boolean letter(char order) {
        return order == 'f';
    }
}
