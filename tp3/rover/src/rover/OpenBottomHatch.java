package rover;

public class OpenBottomHatch extends Instructions {

    public void work(Rover rover) {
        rover.openBottomHatch();
    }

    public boolean letter(char order) {
        return order == 'o';
    }
}
