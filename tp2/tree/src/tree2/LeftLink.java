package tree2;

public class LeftLink extends Link {

    private Tree node;

    public LeftLink( Tree node ) {
        this.node = node;
    }

    public Tree getFromLeft() {
        return node;
    }

    public Tree getFromRight() {
        return null;
    }

    public Object carga() {
        return node.carga();
    }
}
