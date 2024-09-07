package tree;

import com.sun.source.tree.EmptyStatementTree;

public class Node {

    private Object contenido;
    private Tree left = new EmptyTree();
    private Tree right = new EmptyTree();

    public Node( Object contenido ) {
        this.contenido = contenido;
    }

    public void addLeft( Tree leftTree) {
        left = leftTree;
    }

    public void addRight( Tree rightTree) {
        right = rightTree;
    }

    public Tree left() {
        return left;
    }

    public Tree right() {
        return right;
    }

    public Tree carga() {
        return left.carga() + right.carga();
    }

}
