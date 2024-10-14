package rover;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class Instructions {

    public static ArrayList<Instructions> instructions = new ArrayList<Instructions>( Arrays.asList( new Forward(), new Backwards(), new Right(), new Left(), new OpenTopHatch(), new OpenBottomHatch(), new CloseHatches(), new CollectAir(), new CollectDirt() ));

    public abstract void work( Rover rover );

    public static void instructionFor( char order, Rover rover ){
        instructions.stream()
                .filter( instruction -> instruction.letter( order ) )
                .forEach( instruction -> instruction.work( rover ) );
    }

    public abstract boolean letter(char order);
}
