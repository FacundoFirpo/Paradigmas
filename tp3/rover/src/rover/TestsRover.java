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
    public void test08RoverCanInDifferentDirection(){
        Rover rover = new Rover(0, 0, new North() );
        rover.execute("frf");
        assertArrayEquals( new int[]{1, 1}, rover.getPosition() );
        assertEquals( "East", rover.getOrientation() );
    }

    @Test
    public void test09RoverCanOpenTopHatch(){
        Rover rover = new Rover(0, 0, new North() );
        rover.execute("O");
        assertFalse( rover.topHatchClosed() );
    }

    @Test
    public void test10RoverCanOpenBottomHatch(){
        Rover rover = new Rover(0, 0, new North() );
        rover.execute("o");
        assertFalse( rover.bottomHatchClosed() );
    }

    @Test
    public void test11RoverCantOpenBothHatches(){
        Rover rover = new Rover(0, 0, new North() );
        assertThrowsLike( Hatch.bothHatchesCantOpen, () -> rover.execute("Oo") );
    }

    @Test
    public void test12RoverCanCloseHatch(){
        Rover rover = new Rover(0, 0, new North() );
        rover.execute('O');
        assertFalse( rover.topHatchClosed() );
        rover.execute('c');
        assertTrue( rover.topHatchClosed() );
    }

    @Test
    public void test13RoverCantCloseHatchIfAlreadyClosed(){
        Rover rover = new Rover(0, 0, new North() );
        assertThrowsLike( Hatch.cantCloseHatches, () -> rover.execute('c') );
    }

    @Test
    public void test14RoverCanCollectAirSample(){
        Rover rover = new Rover(0, 0, new North() );
        rover.execute("Oa");
        assertTrue( rover.hasAirSample() );
    }

    @Test
    public void test15RoverCantCollectAirSampleIfHatchClosed(){
        Rover rover = new Rover(0, 0, new North() );
        assertThrowsLike( Hatch.cantCollect, () -> rover.execute("oa") );
    }

    @Test
    public void test16RoverCanCollectDirtSample(){
        Rover rover = new Rover(0, 0, new North() );
        rover.execute("oi");
        assertTrue( rover.hasDirtSample() );
    }

    @Test
    public void test17RoverCantCollectDirtSampleIfHatchClosed(){
        Rover rover = new Rover(0, 0, new North() );
        assertThrowsLike( Hatch.cantCollect, () -> rover.execute("Oi") );
    }

    private static void assertThrowsLike( String expectedMsg, Executable expression ) {
        assertEquals( expectedMsg,
                assertThrows( RuntimeException.class, expression ).getMessage() );
    }
}
