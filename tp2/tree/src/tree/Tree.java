package tree;

import java.util.ArrayList;
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
        List<Tree> pila = new ArrayList<Tree>();
        pila.add( this );

        while (!pila.isEmpty()) {
            Tree vertice = pila.removeLast();
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

    public List<Object> bfs() {

        List<Object> bfs = new ArrayList<Object>();
        List<Tree> cola = new ArrayList<Tree>();
        cola.add( this );

        while (!cola.isEmpty()) {
            Tree vertice = cola.removeFirst();
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
