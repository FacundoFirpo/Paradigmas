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
        List<Object> result = new ArrayList<>();
        Stack<Tree> stack = new Stack<>();
        stack.push(this);

        while (!stack.isEmpty()) {
            Tree node = stack.pop();
            result.add(node.carga());

            try {
                stack.push(node.right());
            } catch (RuntimeException e) {
            }

            try {
                stack.push(node.left());
            } catch (RuntimeException e) {
            }
        }
        return result;
    }

    public List<Object> bfs() {
        List<Object> result = new ArrayList<>();
        Queue<Tree> queue = new LinkedList<>();
        queue.add(this);

        while (!queue.isEmpty()) {
            Tree node = queue.poll();
            result.add(node.carga());

            try {
                queue.add(node.left());
            } catch (RuntimeException e) {
            }

            try {
                queue.add(node.right());
            } catch (RuntimeException e) {
            }
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
