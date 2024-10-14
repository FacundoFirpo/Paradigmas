package rover;

public class OpenHatch extends Hatch {

    public boolean isOpen() {
        return true;
    }

    public boolean isClosed() {
        return false;
    }

    public Hatch openOtherHatch(){
        throw new RuntimeException(bothHatchesCantOpen);
    }

    public Hatch close( Hatch otherHatch ){
        return new ClosedHatch();
    }

    public Hatch closeWithOtherClosed(){
        return new ClosedHatch();
    }

    public boolean collect(){
        return true;
    }
}
