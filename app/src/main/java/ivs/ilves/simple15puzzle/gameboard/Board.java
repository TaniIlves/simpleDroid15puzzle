package ivs.ilves.simple15puzzle.gameboard;

import java.util.Arrays;
import java.util.Collections;

import ivs.ilves.simple15puzzle.tools.DebugTools;


public class Board {

    private static Integer boardSize = 0;


    /**
     * GET: Game board size
     *
     * @return Size of game board
     */
    public static Integer getBoardSize() {
        return boardSize;
    }

    /**
     * SET: Redefined size of game board (default size = 16)
     *
     * @param boardSize Size of game board
     */
    public static void setBoardSize(Integer boardSize) {
        Board.boardSize = boardSize;
    }


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
    public static String[] createStartArray(Integer boardSize) {

        String[] startArray = new String[boardSize];

        for (int i = 0; i < boardSize - 1; i++) {
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
