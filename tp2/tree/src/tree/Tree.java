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

        List visitados = new ArrayList();
        List pila = new ArrayList();
        pila.add( this );

        while (!pila.isEmpty()) {
            Tree vertice = (Tree) pila.removeLast();
            if (!visitados.contains( vertice )) {
                visitados.add( vertice );
                if (vertice.right.carga() != null) {
                    pila.add( vertice.right );
                }
                if (vertice.left.carga() != null) {
                    pila.add( vertice.left );
                }
            }
        }

        List dfs = new ArrayList();
        for (Object vertice : visitados) {
            dfs.add( ((Tree) vertice).contenido );
        }

        return dfs;
    }

    public List bfs() {

        List visitados = new ArrayList();
        List cola = new ArrayList();
        cola.add( this );

        while (!cola.isEmpty()) {
            Tree vertice = (Tree) cola.remove( 0 );
            if (!visitados.contains( vertice )) {
                visitados.add( vertice );
                if (vertice.left.carga() != null) {
                    cola.add( vertice.left );
                }
                if (vertice.right.carga() != null) {
                    cola.add( vertice.right );
                }
            }
        }

        List bfs = new ArrayList();
        for (Object vertice : visitados) {
            bfs.add( ((Tree) vertice).contenido );
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
