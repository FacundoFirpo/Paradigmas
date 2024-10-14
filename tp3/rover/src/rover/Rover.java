package rover;

public class Rover {

    private Coordinates coordinates;
    private Orientation orientation;
    private Hatch topHatch = new ClosedHatch();
    private Hatch bottomHatch = new ClosedHatch();
    private boolean airSample = false;
    private boolean dirtSample = false;

    public Rover(int x, int y, Orientation startingOrientation ) {
        coordinates = new Coordinates( x, y );
        orientation = startingOrientation;
    }

    public boolean topHatchClosed() {
        return topHatch.isClosed();
    }

    public boolean bottomHatchClosed() {
        return bottomHatch.isClosed();
    }

    public int[] getPosition() {
        int[] position = { coordinates.getX(), coordinates.getY() };
        return position;
    }

    public String getOrientation() {
        return orientation.toString();
    }

    public void execute( String instruction ) {
        instruction.chars()
                .forEach( order -> execute( (char) order ) );
    }

    public void execute( char order ) {
        Instructions.instructionFor( order,this );
    }

    public void moveNorth(){
        coordinates.addYCoo();
    }

    public void moveSouth(){ coordinates.subYCoo(); }

    public void moveEast(){ coordinates.addXCoo(); }

    public void moveWest(){
        coordinates.subXCoo();
    }

    public void moveForward() {
        orientation.moveForward( this );
    }

    public void moveBackwards() {
        orientation.moveBackwards( this );
    }

    public void turnRight() {
        orientation = orientation.turnRight();
    }

    public void turnLeft() {
        orientation = orientation.turnLeft();
    }

    public void openTopHatch() {
        topHatch = topHatch.open( bottomHatch );
    }

    public void openBottomHatch() {
        bottomHatch = bottomHatch.open( topHatch );
    }

    public void closeHatches() {
        topHatch = topHatch.close( bottomHatch );
        bottomHatch = new ClosedHatch();
    }

    public void collectAir() {
        airSample = topHatch.collect();
    }

    public void collectDirt() {
        dirtSample = bottomHatch.collect();
    }

    public boolean hasAirSample() {
        return airSample;
    }

    public boolean hasDirtSample() {
        return dirtSample;
    }
}
