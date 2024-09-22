package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Tree {

    protected Object contenido;
    private Link left = new EmptyLink();
    private Link right = new EmptyLink();

    public Tree( Object contenido ) {
        this.contenido = contenido;
    }

    public List<Object> dfs() {
        List<Object> dfs = new ArrayList<>();
        prepareDfs( dfs );
        return dfs;
    }

    public void prepareDfs( List<Object> dfs ) {
        dfs.add( carga() );
        left.prepareDfs( dfs );
        right.prepareDfs( dfs );
    }

    public List<Object> bfs() {
        List<Object> result = new ArrayList<>();
        Queue<Tree> queue = new LinkedList<>();
        queue.add(this);

        while (!queue.isEmpty()) {
            Tree node = queue.poll();
            result.add(node.carga());

            node.left.prepareBfs(queue);
            node.right.prepareBfs(queue);
        }
        return result;
    }

    public Tree atLeft( Tree leftTree ) {
        left = left.atLeft( leftTree );
        return this;
    }

    public Tree atRight( Tree rightTree ) {
        right = right.atRight( rightTree );
        return this;
    }

    public Tree right() {
        return right.getFromRight();
    }

    public Tree left() {
        return left.getFromLeft();
    }

    public Object carga() {
        return contenido;
    }
}
