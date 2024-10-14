package rover;

public class Right extends Instructions {

    public void work(Rover rover) {
        rover.turnRight();
    }

    public boolean letter(char order) {
        return order == 'r';
    }
}
