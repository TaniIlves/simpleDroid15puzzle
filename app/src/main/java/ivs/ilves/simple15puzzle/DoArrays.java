package ivs.ilves.simple15puzzle;

import java.util.Arrays;
import java.util.Collections;

public class DoArrays extends MainActivity{

    /**
     * Create the new randomized Array for starting new game.
     *
     * @param startArray Starting array.
     * @return Return the randomized "randomArray".
     */
    static String[] RandomizeArray(String[] startArray) {

        String[] randomArray = startArray;
        Collections.shuffle(Arrays.asList(randomArray));

        return randomArray;
    }
}
