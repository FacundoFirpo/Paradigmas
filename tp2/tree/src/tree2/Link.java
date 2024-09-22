package tree2;

import java.util.List;
import java.util.Queue;

public abstract class Link {

    public Link atLeft( Tree leftTree ) {
        return new UsedLink( leftTree );
    }

    public Link atRight( Tree rightTree ) {
        return new UsedLink( rightTree );
    }

    public abstract Tree getFromLeft();

    public abstract Tree getFromRight();

    public abstract void prepareDfs(List<Object> dfs);

    public abstract void prepareBfs(Queue<Tree> queue);  // New abstract method for BFS

}
