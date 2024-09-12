package tree;

public class EmptyTree extends Tree {

    private static final EmptyTree INSTANCE = new EmptyTree();

    private EmptyTree() {
        super(null);
        this.left = null;
        this.right = null;
    }

    public static EmptyTree getInstance() {
        return INSTANCE;
    }
    public Object carga(){
        return null;
    }

    protected Tree getFromRight(){
        throw new RuntimeException( "Nada a la diestra!" );
    }

    protected Tree getFromLeft(){
        throw new RuntimeException( "Nada a la siniestra!" );
    }
}
