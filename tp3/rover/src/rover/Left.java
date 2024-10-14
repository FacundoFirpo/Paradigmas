package rover;

public class Left extends Instructions {

    public void work(Rover rover) {
        rover.turnLeft();
    }

    public boolean letter(char order) {
        return order == 'l';
    }
}
