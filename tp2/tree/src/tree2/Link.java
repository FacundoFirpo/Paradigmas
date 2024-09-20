package tree2;

import java.util.List;

public abstract class Link {

    public Link atLeft( Tree leftTree ) {
        return new UsedLink( leftTree );
    }

    public Link atRight( Tree rightTree ) {
        return new UsedLink( rightTree );
    }

    public abstract Tree getFromLeft();

    public abstract Tree getFromRight();

    public abstract Object carga();

    public abstract void prepareDfs(List<Object> dfs);

    public abstract void prepareBfs(List<Object> bfs);
}
