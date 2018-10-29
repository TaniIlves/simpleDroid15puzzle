package ivs.ilves.simple15puzzle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;

import ivs.ilves.simple15puzzle.GameBoard.Board;


/**
 * Main class
 */
public class MainActivity extends AppCompatActivity {

    //
    // INIT: Static Fields
    //
    static String[] startArray;
    private static String[] nextTurnArray;
    private static int turnCount = 0;


    /**
     * GET: The method for NextTurnArray.
     *
     * @return The changed Array from the static Field.
     */
    public static String[] getNextTurnArray() {
        return nextTurnArray;
    }

    /**
     * SET: Method for NextTurnArray.
     *
     * @param nextTurnArray The value for changed Array as static Field.
     */
    public static void setNextTurnArray(String[] nextTurnArray) {
        MainActivity.nextTurnArray = nextTurnArray;
    }

    /**
     * BEGIN: The Main Method.
     *
     * @param savedInstanceState Some autogenerated param.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] randomArray;
        TextView countField = findViewById(R.id.countShow);
        Button reloadBtn = findViewById(R.id.reloadBtn);

        //
        // CREATE: The first Array
        //
        if (turnCount == 0) {
            startArray = Board.createStartArray();
        }

        if (getNextTurnArray() == null) {

            //
            // CREATE:
            // RETURN: The randomized Array
            //
            randomArray = Board.randomizeArray(startArray);

        } else {

            randomArray = getNextTurnArray();

            turnCount += 1;

            countField.setText("Count of turns: " + String.valueOf(turnCount));

            if (Arrays.equals(startArray, randomArray)) {
                countField.setText("You win!!! You make " + String.valueOf(turnCount) + " turns");
                reloadBtn.setVisibility(View.VISIBLE);
            }
        }

        //
        // CREATE: The game field
        // RETURN: The ID for zero Button.
        //
        String zeroBtnIndex = generateGameField(randomArray);

        //
        // SET: Four pressable keys
        // CREATE: The new array with use pressed Button
        // RETURN: The changed Array
        //
        setPressedButtons(zeroBtnIndex, randomArray);
    }
    // EOF: The Main method


    //
    // BEGIN: Methods area
    //


    /**
     * CREATE: The new game field by randomized Array
     *
     * @param randomArray The randomized Array of elements for game field creation
     * @return The 'zeroBtnID' Index
     */
    public String generateGameField(String[] randomArray) {

        int numberButton = 0;
        int firstIndex = 1;
        String zeroButtonID = "0";

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; ) {
                int secondIndex = j + 1;

                String buttonIndex = "c" + (String.valueOf(firstIndex)) +
                        "x" + (String.valueOf(secondIndex));

                //
                // GET: Button ID
                //
                Button buttonID = getButtonID(buttonIndex);

                buttonID.setText(randomArray[numberButton]);

                if (Integer.valueOf(randomArray[numberButton]) == 0) {
                    buttonID.setVisibility(View.INVISIBLE);
                    zeroButtonID = buttonIndex;
                }
                numberButton++;
                j++;
            }
            firstIndex++;
        }
        return zeroButtonID;
    }

    /**
     * SET: Buttons as may be pressed.
     *
     * @param zeroBtnID   The ID for zero Button
     * @param randomArray The main Array for set clickable Buttons
     */
    public void setPressedButtons(String zeroBtnID, final String[] randomArray) {

        String activeButtonH1 = "0";
        String activeButtonW1 = "0";
        String activeButtonH2 = "0";
        String activeButtonW2 = "0";

        if (!zeroBtnID.equals("0")) {
            String firstIndex = String.valueOf(zeroBtnID.charAt(1));
            String secondIndex = String.valueOf(zeroBtnID.charAt(3));

            int activeCellH1 = Integer.valueOf(secondIndex) - 1;
            int activeCellH2 = Integer.valueOf(secondIndex) + 1;
            int activeCellW1 = Integer.valueOf(firstIndex) - 1;
            int activeCellW2 = Integer.valueOf(firstIndex) + 1;

            if (activeCellH1 < 5 && activeCellH1 > 0) {
                activeButtonH1 = "c" + firstIndex + "x" + activeCellH1;
            }
            if (activeCellH2 < 5 && activeCellH2 > 0) {
                activeButtonH2 = "c" + firstIndex + "x" + activeCellH2;
            }
            if (activeCellW1 < 5 && activeCellW1 > 0) {
                activeButtonW1 = "c" + activeCellW1 + "x" + secondIndex;
            }
            if (activeCellW2 < 5 && activeCellW2 > 0) {
                activeButtonW2 = "c" + activeCellW2 + "x" + secondIndex;
            }
        }

        final String[] aroundButtons = new String[]{activeButtonW1, activeButtonH1,
                activeButtonW2, activeButtonH2};

        for (int n = 0; n < aroundButtons.length; ) {

            //
            // GET: The Button ID for OnClickListener
            //
            Button buttonNumID = getButtonID(aroundButtons[n]);

            if (!aroundButtons[n].equals("0")) {

                final int keyIndex = n;

                buttonNumID.setOnClickListener(new View.OnClickListener() {

                    /**
                     * Override onClick method for ClickListener
                     * @param v View v
                     */
                    @Override
                    public void onClick(View v) {

                        changeArrayPosition(aroundButtons[keyIndex], randomArray);
                    }
                });
            }
            n++;
        }
    }

    /**
     * CHANGE: Buttons position.
     *
     * @param pressedKeyName The name of pressed Button
     * @param randomArray    The main Array for change position, when any Button was pressed.
     */
    public void changeArrayPosition(String pressedKeyName, String[] randomArray) {

        String valuePressedKey;
        Integer zeroKeyPos = null;
        Integer pressedKeyPos = null;

        //
        // GET: The pressed Button ID.
        //
        Button pressedBtn = getButtonID(pressedKeyName);

        valuePressedKey = String.valueOf(pressedBtn.getText());

        int posNum = 0;
        for (String aRandomArray : randomArray) {
            if (aRandomArray.equals(valuePressedKey)) {
                pressedKeyPos = posNum;
            }
            if (aRandomArray.equals("0")) {
                zeroKeyPos = posNum;
            }
            posNum++;
        }

        if (pressedKeyPos != null && zeroKeyPos != null) {
            String temp = randomArray[pressedKeyPos];
            randomArray[pressedKeyPos] = randomArray[zeroKeyPos];
            randomArray[zeroKeyPos] = temp;
        }

        //
        // SET: The new Array for next turn.
        //
        MainActivity.setNextTurnArray(randomArray);

        //
        // Reload MainActivity
        //
        overridePendingTransition(0, 0);
        Intent refresh = new Intent(this, MainActivity.class);
        startActivity(refresh);
        overridePendingTransition(0, 0);
        this.finish();
    }

    /**
     * GET: The button ID with use Button Index
     *
     * @param buttonName The Button Index
     * @return The Button ID
     */
    public Button getButtonID(String buttonName) {
        int resID = getResources().getIdentifier(buttonName, "id", getPackageName());
        return findViewById(resID);
    }
    // EOF: Methods area
}
///******///   TRASH CODE AREA   ///******///


