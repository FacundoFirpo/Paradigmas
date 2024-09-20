package tree2;

public class EmptyLink extends Link {

    public Tree getFromRight() {
        throw new RuntimeException("Nada a la diestra!");
    }

    public Tree getFromLeft() {
        throw new RuntimeException("Nada a la siniestra!");
    }

    public Object carga() {
        return null;
    }
}
