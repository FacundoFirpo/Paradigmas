package rover;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

public class TestsRover {

    @Test
    public void test01NewRoverHasHatchesClosed() {
        Rover rover = new Rover(0, 0, new North() );
        assertTrue( rover.topHatchClosed() );
        assertTrue( rover.bottomHatchClosed() );
        assertFalse( rover.hasAirSample() );
        assertFalse( rover.hasDirtSample() );
    }

    @Test
    public void test02NoMovementWhenNoOrderGiven(){
        Rover rover = new Rover(0, 0, new North() );
        rover.execute(' ');
        assertArrayEquals( new int[]{0, 0}, rover.getPosition() );
        assertEquals( "North", rover.getOrientation() );
    }

    @Test
    public void test03InexistentInstructionDoesNothing(){
        Rover rover = new Rover(0, 0, new North() );
        rover.execute('z');
        assertArrayEquals( new int[]{0, 0}, rover.getPosition() );
        assertEquals( "North", rover.getOrientation() );
    }

    @Test
    public void test04RoverMovesForward(){
        Rover rover = new Rover(0, 0, new North() );
        rover.execute('f');
        assertArrayEquals( new int[]{0, 1}, rover.getPosition() );
        assertEquals( "North", rover.getOrientation() );
    }

    @Test
    public void test05RoverMovesBackwards(){
        Rover rover = new Rover(0, 0, new North() );
        rover.execute('b');
        assertArrayEquals( new int[]{0, -1}, rover.getPosition() );
        assertEquals( "North", rover.getOrientation() );
    }

    @Test
    public void test06RoverTurnsRight(){
        Rover rover = new Rover(0, 0, new North() );
        rover.execute('r');
        assertArrayEquals( new int[]{0, 0}, rover.getPosition() );
        assertEquals( "East", rover.getOrientation() );
    }

    @Test
    public void test07RoverTurnsLeft(){
        Rover rover = new Rover(0, 0, new North() );
        rover.execute('l');
        assertArrayEquals( new int[]{0, 0}, rover.getPosition() );
        assertEquals( "West", rover.getOrientation() );
    }

    @Test
    public void test08RoverCanMoveInDifferentDirection(){
        Rover rover = new Rover(0, 0, new North() );
        rover.execute("frf");
        assertArrayEquals( new int[]{1, 1}, rover.getPosition() );
        assertEquals( "East", rover.getOrientation() );
    }

    @Test
    public void test09RoverStopMovingIfWrongInstruction(){
        Rover rover = new Rover(0, 0, new North() );
        rover.execute("frfzrf");
        assertArrayEquals( new int[]{1, 1}, rover.getPosition() );
        assertEquals( "East", rover.getOrientation() );
    }

    @Test
    public void test10RoverCanReceiveManyInstructions(){
        Rover rover = new Rover(0, 0, new North() );
        rover.execute("frf");
        rover.execute("lfrfllzsr");
        assertArrayEquals( new int[]{2, 2}, rover.getPosition() );
        assertEquals( "West", rover.getOrientation() );
    }

    @Test
    public void test10RoverCanOpenTopHatch(){
        Rover rover = new Rover(0, 0, new North() );
        rover.execute("O");
        assertFalse( rover.topHatchClosed() );
    }

    @Test
    public void test11RoverCanOpenBottomHatch(){
        Rover rover = new Rover(0, 0, new North() );
        rover.execute("o");
        assertFalse( rover.bottomHatchClosed() );
    }

    @Test
    public void test12RoverCantOpenBothHatches(){
        Rover rover = new Rover(0, 0, new North() );
        assertThrowsLike( Hatch.bothHatchesCantOpen, () -> rover.execute("Oo") );
    }

    @Test
    public void test13RoverCanCloseTopHatch(){
        Rover rover = new Rover(0, 0, new North() );
        rover.execute('O');
        assertFalse( rover.topHatchClosed() );
        rover.execute('c');
        assertTrue( rover.topHatchClosed() );
    }

    @Test
    public void test14RoverCanCloseBottomHatch(){
        Rover rover = new Rover(0, 0, new North() );
        rover.execute('o');
        assertFalse( rover.bottomHatchClosed() );
        rover.execute('c');
        assertTrue( rover.bottomHatchClosed() );
    }

    @Test
    public void test15RoverCantCloseHatchIfAlreadyClosed(){
        Rover rover = new Rover(0, 0, new North() );
        assertThrowsLike( Hatch.cantCloseHatches, () -> rover.execute('c') );
    }

    @Test
    public void test16RoverCanCollectAirSample(){
        Rover rover = new Rover(0, 0, new North() );
        rover.execute("Oa");
        assertTrue( rover.hasAirSample() );
    }

    @Test
    public void test17RoverCanCollectTwoAirSample(){
        Rover rover = new Rover(0, 0, new North() );
        rover.execute("Oaa");
        assertTrue( rover.hasAirSample() );
    }

    @Test
    public void test18RoverCantCollectAirSampleIfTopHatchClosed(){
        Rover rover = new Rover(0, 0, new North() );
        assertThrowsLike( Hatch.cantCollect, () -> rover.execute("oa") );
    }

    @Test
    public void test19RoverCanCollectDirtSample(){
        Rover rover = new Rover(0, 0, new North() );
        rover.execute("oi");
        assertTrue( rover.hasDirtSample() );
    }

    @Test
    public void test20RoverCanCollectTwoDirtSample(){
        Rover rover = new Rover(0, 0, new North() );
        rover.execute("oii");
        assertTrue( rover.hasDirtSample() );
    }

    @Test
    public void test21RoverCantCollectDirtSampleIfBottomHatchClosed(){
        Rover rover = new Rover(0, 0, new North() );
        assertThrowsLike( Hatch.cantCollect, () -> rover.execute("Oi") );
    }

    private static void assertThrowsLike( String expectedMsg, Executable expression ) {
        assertEquals( expectedMsg,
                assertThrows( RuntimeException.class, expression ).getMessage() );
    }
}
