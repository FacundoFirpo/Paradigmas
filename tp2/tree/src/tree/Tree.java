package tree;

import java.util.List;

public class Tree {

    private Object contenido;
    private Tree left = new EmptyTree();
    private Tree right = new EmptyTree();

    public Tree( Object contenido ) {
        this.contenido = new Node( contenido );
    }

    public List dfs() {
        return null;
    }

    public List bfs() {
        return null;
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
        return right;
    }

    public Tree left() {
        return left;
    }

    public Object carga() {
        return contenido;
    }
}
