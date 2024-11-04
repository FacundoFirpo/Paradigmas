package rover;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

public class TestsRover {

    @Test
    public void test01NewRoverHasHatchesClosed() {
        Rover rover = roverAt00LookingNorth();
        assertTrue( rover.topHatchClosed() );
        assertTrue( rover.bottomHatchClosed() );
        assertFalse( rover.hasAirSample() );
        assertFalse( rover.hasDirtSample() );
    }

    @Test
    public void test02NoMovementWhenNoOrderGiven(){
        assertPosition(roverAt00LookingNorthWithInstruction(' '), 0, 0, "North");
    }

    @Test
    public void test03InexistentInstructionDoesNothing(){
        assertPosition(roverAt00LookingNorthWithInstruction('z'), 0, 0, "North");
    }

    @Test
    public void test04RoverTurnsRight(){
        Rover rover = roverAt00LookingNorth();
        assertPosition(rover.execute('r'), 0, 0, "East");
        assertPosition(rover.execute('r'), 0, 0, "South");
        assertPosition(rover.execute('r'), 0, 0, "West");
        assertPosition(rover.execute('r'), 0, 0, "North");
    }

    @Test
    public void test05RoverTurnsLeft(){
        Rover rover = roverAt00LookingNorth();
        assertPosition(rover.execute('l'), 0, 0, "West");
        assertPosition(rover.execute('l'), 0, 0, "South");
        assertPosition(rover.execute('l'), 0, 0, "East");
        assertPosition(rover.execute('l'), 0, 0, "North");
    }

    @Test
    public void test06RoverMovesForward(){
        Rover rover = roverAt00LookingNorth();
        assertPosition(rover.execute('f'), 0, 1, "North");
        assertPosition(rover.execute("rf"), 1, 1, "East");
        assertPosition(rover.execute("rf"), 1, 0, "South");
        assertPosition(rover.execute("rf"), 0, 0, "West");
    }

    @Test
    public void test07RoverMovesBackwards(){
        Rover rover = roverAt00LookingNorth();
        assertPosition(rover.execute('b'), 0, -1, "North");
        assertPosition(rover.execute("rb"), -1, -1, "East");
        assertPosition(rover.execute("rb"), -1, 0, "South");
        assertPosition(rover.execute("rb"), 0, 0, "West");
    }

    @Test
    public void test08RoverCanMoveInDifferentDirection(){
        assertPosition(roverAt00LookingNorthWithInstruction("frf"), 1, 1, "East");
    }

    @Test
    public void test09RoverStopMovingIfWrongInstruction(){
        assertPosition(roverAt00LookingNorthWithInstruction("frfzrf"), 1, 1, "East");
    }

    @Test
    public void test10RoverCanReceiveManyInstructions(){
        assertPosition(roverAt00LookingNorthWithInstruction("frf").execute("lfrfllzsr"), 2, 2, "West");
    }

    @Test
    public void test11RoverCanOpenTopHatch(){
        assertFalse( roverAt00LookingNorthWithInstruction('O').topHatchClosed() );
    }

    @Test
    public void test12RoverCanOpenBottomHatch(){
        assertFalse( roverAt00LookingNorthWithInstruction('o').bottomHatchClosed() );
    }

    @Test
    public void test13RoverCantOpenBothHatches(){
        assertThrowsLike( Hatch.bothHatchesCantOpen, () -> roverAt00LookingNorthWithInstruction("Oo") );
    }

    @Test
    public void test14RoverCanCloseTopHatch(){
        Rover rover = roverAt00LookingNorthWithInstruction('O');
        assertFalse( rover.topHatchClosed() );
        assertTrue( rover.execute('c').topHatchClosed() );
    }

    @Test
    public void test15RoverCanCloseBottomHatch(){
        Rover rover = roverAt00LookingNorthWithInstruction('o');
        assertFalse( rover.bottomHatchClosed() );
        assertTrue( rover.execute('c').bottomHatchClosed() );
    }

    @Test
    public void test16RoverCantCloseHatchIfAlreadyClosed(){
        assertThrowsLike( Hatch.cantCloseHatches, () -> roverAt00LookingNorthWithInstruction('c') );
    }

    @Test
    public void test17RoverCanCollectAirSample(){
        assertTrue( roverAt00LookingNorthWithInstruction("Oa").hasAirSample() );
    }

    @Test
    public void test18RoverCanCollectTwoAirSample(){
        assertTrue( roverAt00LookingNorthWithInstruction("Oaa").hasAirSample() );
    }

    @Test
    public void test19RoverCantCollectAirSampleIfTopHatchClosed(){
        assertThrowsLike( Hatch.cantCollect, () -> roverAt00LookingNorthWithInstruction("oa") );
    }

    @Test
    public void test20RoverCanCollectDirtSample(){
        assertTrue( roverAt00LookingNorthWithInstruction("oi").hasDirtSample() );
    }

    @Test
    public void test21RoverCanCollectTwoDirtSample(){
        assertTrue( roverAt00LookingNorthWithInstruction("oii").hasDirtSample() );
    }

    @Test
    public void test22RoverCantCollectDirtSampleIfBottomHatchClosed(){
        assertThrowsLike( Hatch.cantCollect, () -> roverAt00LookingNorthWithInstruction("Oi") );
    }

    @Test
    public void test23RoverCanMoveAfterCollectingAirSample(){
        assertPosition(roverAt00LookingNorthWithInstruction("Oafr"), 0, 1, "East");
    }

    @Test
    public void test24RoverCanMoveAfterCollectingDirtSamples(){
        assertPosition(roverAt00LookingNorthWithInstruction("oifr"), 0, 1, "East");
    }

    @Test
    public void test25RoverCanCollectAirSampleAfterCollectingDirtSample(){
        Rover rover = roverAt00LookingNorthWithInstruction("oicOa");
        assertTrue( rover.hasAirSample() );
        assertTrue( rover.hasDirtSample() );
    }

    private Rover roverAt00LookingNorth() {
        return new Rover(0, 0, new North());
    }

    private Rover roverAt00LookingNorthWithInstruction( String instruction ) {
        return roverAt00LookingNorth().execute(instruction);
    }

    private Rover roverAt00LookingNorthWithInstruction( char instruction ) {
        return roverAt00LookingNorth().execute(instruction);
    }

    private void assertPosition(Rover rover, int x, int y, String North) {
        assertArrayEquals(new int[]{x, y}, rover.getPosition());
        assertEquals(North, rover.getOrientation());
    }

    private static void assertThrowsLike( String expectedMsg, Executable expression ) {
        assertEquals( expectedMsg,
                assertThrows( RuntimeException.class, expression ).getMessage() );
    }
}
