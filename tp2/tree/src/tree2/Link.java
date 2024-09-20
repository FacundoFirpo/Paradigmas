package tree2;

public abstract class Link {

    public Link atLeft( Tree leftTree ) {
        return new LeftLink( leftTree );
    }

    public Link atRight( Tree rightTree ) {
        return new RightLink( rightTree );
    }

    public abstract Tree getFromLeft();

    public abstract Tree getFromRight();

    public abstract Object carga();
}
