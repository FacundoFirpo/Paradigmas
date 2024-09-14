package tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tree {

    protected Object contenido;
    protected Tree left;
    protected Tree right;

    public Tree( Object contenido ) {
        this.contenido = contenido;
        this.left = createEmptyNode();
        this.right = createEmptyNode();
    }

    protected Tree createEmptyNode() {
        return new EmptyTree();
    }

    public List<Object> dfs() {
        List<Object> dfs = new ArrayList<Object>();
        forDfs( dfs );
        return dfs;
    }

    public void forDfs( List<Object> dfs ) {
        dfs.add( carga() );
        left.forDfs( dfs );
        right.forDfs( dfs );
    }

    public List<Object> bfs() {
        List<Object> bfs = new ArrayList<Object>();
        bfs.add( carga() );
        forBfs( bfs );
        bfs.removeAll( Arrays.asList((Object) null) );
        return bfs;
    }

    public void forBfs( List<Object> bfs ) {
        bfs.add( left.carga() );
        bfs.add( right.carga() );
        left.forBfs( bfs );
        right.forBfs( bfs );
    }

    public Tree atLeft( Tree leftTree ) {
        left = leftTree;
        return this;
    }

    public Tree atRight( Tree rightTree ) {
        right = rightTree;
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

    protected Tree getFromRight() {
        return this;
    }

    protected Tree getFromLeft() {
        return this;
    }
}
