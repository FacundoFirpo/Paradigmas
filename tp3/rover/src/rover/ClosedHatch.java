package rover;

public class ClosedHatch extends Hatch {

    public boolean isOpen() {
        return false;
    }

    public boolean isClosed() {
        return true;
    }

    public Hatch openOtherHatch() {
        return new OpenHatch();
    }

    public Hatch close( Hatch otherHatch ){
        return otherHatch.closeWithOtherClosed();
    }

    public Hatch closeWithOtherClosed(){
        throw new RuntimeException(cantCloseHatches);
    }

    public boolean collect(){
        throw new RuntimeException(cantCollect);
    }
}
