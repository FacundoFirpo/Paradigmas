package tree;

import java.util.ArrayList;
import java.util.List;

public class Tree {

    protected Object contenido;
    protected Tree left = EmptyTree.getInstance();
    protected Tree right = EmptyTree.getInstance();

    public Tree( Object contenido ) {
        this.contenido = contenido;
    }

    public List dfs() {

        List<Object> dfs = new ArrayList<Object>();
        List<Tree> pila = new ArrayList<Tree>();
        pila.add( this );

        while (!pila.isEmpty()) {
            Tree vertice = (Tree) pila.removeLast();
            dfs.add(vertice.carga());
            if (vertice.right.carga() != null) {
                pila.add(vertice.right);
            }
            if (vertice.left.carga() != null) {
                pila.add(vertice.left);
            }
        }

        return dfs;
    }

    public List bfs() {

        List<Object> bfs = new ArrayList<Object>();
        List<Tree> cola = new ArrayList<Tree>();
        cola.add( this );

        while (!cola.isEmpty()) {
            Tree vertice = (Tree) cola.removeFirst();
            bfs.add( vertice.carga() );
            if (vertice.left.carga() != null) {
                cola.add( vertice.left );
            }
            if (vertice.right.carga() != null) {
                cola.add( vertice.right );
            }
        }

        return bfs;

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
