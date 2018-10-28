package ivs.ilves.simple15puzzle;

import android.util.Log;

import java.util.Arrays;
import java.util.Collections;

class DoArrays {

    /**
     * Create the new randomized Array for starting new game.
     *
     * @param resortedArray Starting array.
     * @return Return the randomized "randomArray".
     */
    static String[] RandomizeArray(String[] resortedArray) {

        Collections.shuffle(Arrays.asList(resortedArray));

        // Logging part
        Log.i("LOG.INFO: resortedArray", Arrays.toString(resortedArray));

        return resortedArray;
    }

    static String[] StartArrayGenerator() {

        int arrayLength = 16;
        String[] startArray = new String[arrayLength];

        for (int i = 0; i < arrayLength - 1; i++) {
            startArray[i] = String.valueOf(i + 1);
        }
        startArray[15] = String.valueOf(0);

        // Logging part
        Log.i("LOG.INFO: startArray", Arrays.toString(startArray));

        return startArray;
    }
}
