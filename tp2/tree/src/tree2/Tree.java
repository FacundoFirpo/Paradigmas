package tree2;

import java.util.*;

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
        List<Object> bfs = new ArrayList<Object>();
        bfs.add( carga() );
        prepareBfs( bfs );
        bfs.removeAll( Arrays.asList( (Object) null ) );
        return bfs;
    }

    public void prepareBfs( List<Object> bfs ) {
        bfs.add( left.carga() );
        bfs.add( right.carga() );
        left.prepareBfs( bfs );
        right.prepareBfs( bfs );
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
