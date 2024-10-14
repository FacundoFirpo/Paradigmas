package rover;

public class CollectAir extends Instructions {

    public void work(Rover rover) {
        rover.collectAir();
    }

    public boolean letter(char order) {
        return order == 'a';
    }
}
