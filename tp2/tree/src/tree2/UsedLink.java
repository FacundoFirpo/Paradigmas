package tree2;

import java.util.List;

public class UsedLink extends Link{

    private Tree node;

    public UsedLink( Tree node ){
        this.node = node;
    }

    public Tree getFromLeft(){
        return node;
    }

    public Tree getFromRight(){
        return node;
    }

    public Object carga(){
        return node.carga();
    }

    public void prepareDfs( List<Object> dfs ){
        node.prepareDfs( dfs );
    }

    public void prepareBfs( List<Object> bfs ){
        node.prepareBfs( bfs );
    }
}
