package rover;

public class OpenTopHatch extends Instructions {

    public void work(Rover rover) {
        rover.openTopHatch();
    }

    public boolean letter(char order) {
        return order == 'O';
    }
}
