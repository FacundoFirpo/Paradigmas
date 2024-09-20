package tree2;

import java.util.List;

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

    public void prepareDfs( List<Object> dfs ) {}

    public void prepareBfs( List<Object> bfs ) {}
}
