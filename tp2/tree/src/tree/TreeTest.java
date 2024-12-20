package tree;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TreeTest {

    @Test public void test01SimpleTreeSides() {
        assertThrowsLike( "Nada a la diestra!", () -> new Tree( 'a' ).right() );
        assertThrowsLike( "Nada a la siniestra!", () -> new Tree( 'a' ).left() );
        assertEquals( 'a', new Tree( 'a' ).carga() );
    }

    @Test public void test02SimpleTreeWalks() {
        assertEquals( 1, new Tree( 'a' ).dfs().size() );
        assertEquals( 'a', new Tree( 'a' ).dfs().getFirst() );
    }

    @Test public void test03AddingLeft() {
        Tree tree = new Tree( "1" ).atLeft( new Tree( "2" ) );

        assertThrowsLike( "Nada a la diestra!", () -> tree.right() );
        assertEquals( "2", tree.left().carga() );
        assertEquals( 2, tree.dfs().size() );
        assertEquals( "1", tree.dfs().getFirst() );
        assertEquals( "2", tree.dfs().getLast() );
    }

    @Test public void test04AddingRight() {
        Tree tree = new Tree( 'a' ).atRight( new Tree( 'b' ) );

        assertThrowsLike( "Nada a la siniestra!", () -> tree.left() );
        assertEquals( 'b', tree.right().carga() );
        assertEquals( 2, tree.dfs().size() );
        assertEquals( 'a', tree.dfs().getFirst() );
        assertEquals( 'b', tree.dfs().getLast() );
    }

    @Test public void test05AddingBoth() {
        List dfs = abcTree().dfs();

        assertEquals(   3, dfs.size() );
        assertEquals( 'a', dfs.getFirst() );
        assertEquals( 'c', dfs.getLast() );
    }

    @Test public void test06AddingLeftComposedTree() {
        List dfs = new Tree( 'x' ).atLeft( abcTree() ).dfs();

        assertEquals( 4, dfs.size() );
        assertEquals( 'x', dfs.getFirst() );
        assertEquals( 'c', dfs.getLast() );
    }

    @Test public void test07AddingLeftUnbalancedTree() {
        List dfs = new Tree( 'x' ).atLeft( abcTree() ).atRight( new Tree( 'y' ) ).dfs();

        assertEquals( 5, dfs.size() );
        assertEquals( 'x', dfs.getFirst() );
        assertEquals( 'a', dfs.get( 1 ) );
        assertEquals( 'b', dfs.get( 2 ) );
        assertEquals( 'c', dfs.get( 3 ) );
        assertEquals( 'y', dfs.getLast() );
    }

    @Test public void test08AddingRightComposedTree() {
        List dfs = new Tree( 'x' ).atRight( abcTree() ).dfs();

        assertEquals( 4, dfs.size() );
        assertEquals( 'x', dfs.getFirst() );
        assertEquals( 'c', dfs.getLast() );
    }

    @Test public void test09AddingRigthtUnbalancedTree() {
        List dfs = new Tree( 'x' ).atRight( abcTree() ).atLeft( new Tree( 'y' ) ).dfs();

        assertEquals( 5, dfs.size() );
        assertEquals( 'x', dfs.getFirst() );
        assertEquals( 'y', dfs.get( 1 ) );
        assertEquals( 'a', dfs.get( 2 ) );
        assertEquals( 'b', dfs.get( 3 ) );
        assertEquals( 'c', dfs.getLast() );
    }

    @Test public void test10SimpleTreeBfs() {
        assertEquals( 1, new Tree( 'a' ).bfs().size() );
        assertEquals( 'a', new Tree( 'a' ).bfs().getFirst() );
    }

    @Test public void test11AddingLeftBfs() {
        List dfs = new Tree( 'a' ).atLeft( new Tree( 'b' ) ).dfs();

        assertEquals( 2, dfs.size() );
        assertEquals( 'a', dfs.getFirst() );
        assertEquals( 'b', dfs.getLast() );
    }

    @Test public void test12AddingRightBfs() {
        List bfs = new Tree( 'a' ).atRight( new Tree( 'b' ) ).bfs();

        assertEquals( 2, bfs.size() );
        assertEquals( 'a', bfs.getFirst() );
        assertEquals( 'b', bfs.getLast() );
    }

    @Test public void test13AddingBothBfs() {
        List bfs = abcTree().bfs();

        assertEquals( 3, bfs.size() );
        assertEquals( 'a', bfs.getFirst() );
        assertEquals( 'c', bfs.getLast() );
    }

    @Test public void test14AddingLeftComposedTreeBfs() {
        List bfs = new Tree( 'x' ).atLeft( abcTree() ).bfs();

        assertEquals( 4, bfs.size() );
        assertEquals( 'x', bfs.getFirst() );
        assertEquals( 'a', bfs.get( 1 ) );
        assertEquals( 'b', bfs.get( 2 ) );
        assertEquals( 'c', bfs.getLast() );
    }

    @Test public void test15AddingLeftUnbalancedTreeBfs() {
        List bfs = new Tree( 'x' ).atLeft( abcTree() ).atRight( new Tree( 'y' ) ).bfs();

        assertEquals( 5, bfs.size() );
        assertEquals( 'x', bfs.getFirst() );
        assertEquals( 'a', bfs.get( 1 ) );
        assertEquals( 'y', bfs.get( 2 ) );
        assertEquals( 'b', bfs.get( 3 ) );
        assertEquals( 'c', bfs.getLast() );
    }

    @Test public void test16AddingRightComposedTreeBfs() {
        List bfs = new Tree( 'x' ).atRight( abcTree() ).bfs();

        assertEquals( 4, bfs.size() );
        assertEquals( 'x', bfs.getFirst() );
        assertEquals( 'c', bfs.getLast() );
    }

    @Test public void test17AddingRigthtUnbalancedTreeBfs() {
        List bfs = new Tree( 'x' ).atLeft( abcTree() ).atRight( new Tree( 't' ).atLeft( new Tree( 'u' ) ).atRight( new Tree( 'v' ) ) ).bfs();

        assertEquals( 7, bfs.size() );
        assertEquals( 'x', bfs.getFirst() );
        assertEquals( 'a', bfs.get( 1 ) );
        assertEquals( 't', bfs.get( 2 ) );
        assertEquals( 'b', bfs.get( 3 ) );
        assertEquals( 'c', bfs.get( 4 ) );
        assertEquals( 'u', bfs.get( 5 ) );
        assertEquals( 'v', bfs.getLast() );
    }

    private static Tree abcTree() {
        return new Tree( 'a' ).atLeft( new Tree( 'b' ) ).atRight( new Tree( 'c' ) );
    }

}
