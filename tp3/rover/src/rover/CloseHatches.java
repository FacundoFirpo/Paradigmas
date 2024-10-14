package rover;

public class CloseHatches extends Instructions {

    public void work(Rover rover) {
        rover.closeHatches();
    }

    public boolean letter(char order) {
        return order == 'c';
    }
}
