package ivs.ilves.simple15puzzle.GameBoard;

import java.util.Arrays;
import java.util.Collections;

import ivs.ilves.simple15puzzle.Tools.DebugTools;


public class Board {

    String ButtonIndex;
    Integer ButtonID;
    String ButtonValue;

    /**
     * CREATE: The new randomized Array for starting new game.
     *
     * @param resortedArray Starting array.
     * @return Return the randomized 'randomArray'.
     */
    public static String[] randomizeArray(String[] resortedArray) {

        Collections.shuffle(Arrays.asList(resortedArray));

        //
        // DEBUG: Log action
        //
        DebugTools.writeLog('d', "resortedArray = " + Arrays.toString(resortedArray));

        return resortedArray;
    }

    /**
     * CREATE: The init array for starting game
     *
     * @return The started array
     */
    public static String[] createStartArray() {

        int arrayLength = 16;

        String[] startArray = new String[arrayLength];

        for (int i = 0; i < arrayLength - 1; i++) {
            startArray[i] = String.valueOf(i + 1);
        }
        startArray[15] = String.valueOf(0);

        //
        // DEBUG: Log action
        //
        DebugTools.writeLog('d', "startArray = " + Arrays.toString(startArray));

        return startArray;
    }
}
