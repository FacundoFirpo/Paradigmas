package tree;

import java.util.List;
import java.util.Queue;

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

    public void prepareDfs( List<Object> dfs ){
        node.prepareDfs( dfs );
    }

    public void prepareBfs(Queue<Tree> queue) {
        queue.add(node);
    }
}
