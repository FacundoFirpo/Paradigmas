package tree;

import java.util.List;
import java.util.Queue;

public class EmptyLink extends Link {

    public Tree getFromRight() {
        throw new RuntimeException("Nada a la diestra!");
    }

    public Tree getFromLeft() {
        throw new RuntimeException("Nada a la siniestra!");
    }

    public void prepareDfs( List<Object> dfs ) {}

    public void prepareBfs(Queue<Tree> queue) {}
}
