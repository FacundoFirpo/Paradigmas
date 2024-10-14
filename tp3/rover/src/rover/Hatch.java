package rover;

public abstract class Hatch {

    protected static final String bothHatchesCantOpen = "Both hatches can't be open at the same time";
    protected static final String cantCloseHatches = "Can't close hatches if already closed";
    protected static final String cantCollect = "Can't collect samples when hatch closed";

    public abstract boolean isOpen();
    public abstract boolean isClosed();

    public Hatch open( Hatch otherHatch ){
        return otherHatch.openOtherHatch();
    }

    public abstract Hatch openOtherHatch();

    public abstract Hatch close( Hatch otherHatch );

    public abstract boolean collect();

    public abstract Hatch closeWithOtherClosed();
}
