package tree;

import java.util.ArrayList;
import java.util.List;

public class Tree {

    private Object contenido;
    private Tree left = null;
    private Tree right = null;

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
                if (vertice.right != null) {
                    pila.add( vertice.right );
                }
                if (vertice.left != null) {
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
                if (vertice.left != null) {
                    cola.add( vertice.left );
                }
                if (vertice.right != null) {
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
        if (right == null) {
            throw new RuntimeException( "Nada a la diestra!" );
        }
        return right;
    }

    public Tree left() {
        if (left == null) {
            throw new RuntimeException( "Nada a la siniestra!" );
        }
        return left;
    }

    public Object carga() {
        return contenido;
    }
}
