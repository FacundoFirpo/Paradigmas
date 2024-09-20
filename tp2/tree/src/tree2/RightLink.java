package tree2;

public class RightLink extends Link {

    private Tree node;

    public RightLink( Tree node ) {
        this.node = node;
    }

    public Tree getFromRight() {
        return node;
    }

    public Tree getFromLeft() {
        return null;
    }

    public Object carga() {
        return node.carga();
    }
}
