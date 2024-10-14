package rover;

public class CollectDirt extends Instructions {

    public void work(Rover rover) {
        rover.collectDirt();
    }

    public boolean letter(char order) {
        return order == 'i';
    }
}
