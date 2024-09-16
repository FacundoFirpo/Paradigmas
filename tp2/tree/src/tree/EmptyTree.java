package tree;

import java.util.List;

public class EmptyTree extends Tree {

    protected EmptyTree() {
        super(null);
        this.left = null;
        this.right = null;
    }

    protected Tree createEmptyNode() {
        return null;
    }

    public void prepareDfs( List<Object> dfs ) {}

    public void prepareBfs( List<Object> bfs ) {}

    public Object carga(){
        return null;
    }

    protected Tree getFromRight(){
        throw new RuntimeException( "Nada a la diestra!" );
    }

    protected Tree getFromLeft(){
        throw new RuntimeException( "Nada a la siniestra!" );
    }
}
