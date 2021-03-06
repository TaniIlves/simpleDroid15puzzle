package ivs.ilves.simple15puzzle;

//import android.content.Context;
//import android.content.DialogInterface;
//import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
//import android.widget.Toast;
import java.util.Arrays;
import java.util.Collections;

/**
 * Main class
 */
public class MainActivity extends AppCompatActivity {
    private static String[] nextTurnArray;
    private static Integer turnCount = 0;
    //private static Integer resultCode = 0;

    /**
     * Get method for NextTurnArray.
     *
     * @return Returning changed array as global field.
     */
    public static String[] getNextTurnArray() {
        return nextTurnArray;
    }

    /**
     * Set method for NextTurnArray.
     *
     * @param nextTurnArray Setting value for changed array as global field.
     */
    public static void setNextTurnArray(String[] nextTurnArray) {
        MainActivity.nextTurnArray = nextTurnArray;
    }

    /**
     * Main Method.
     *
     * @param savedInstanceState Autogenerated param.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] randomArray;
        TextView CountField = findViewById(R.id.countShow);
        Button reloadBtn = findViewById(R.id.reloadBtn);

        // Making the first array.
        String[] startArray = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "0"};


        if (getNextTurnArray() == null) {

            // Create and returning the randomized array.
            randomArray = RandomizeArray(startArray);

        } else {

            randomArray = getNextTurnArray();
            turnCount += 1;
            CountField.setText("Count of turns: " + String.valueOf(turnCount));

            if (Arrays.equals(startArray, randomArray)) {
                CountField.setText("You win!!! You make " + String.valueOf(turnCount) + " turns");
                reloadBtn.setVisibility(View.VISIBLE);
            }
        }

        // Generating the game field and returning zero button ID.
        String zeroBtnID = GameFieldGenerator(randomArray);

        // Setting up 4 pressable keys and recreating array with use pressed key.
        SetPressedKeys(zeroBtnID, randomArray);

    }

    /**
     * Create the new randomized Array for starting new game.
     *
     * @param startArray Starting array.
     * @return Return the randomized "randomArray".
     */
    public String[] RandomizeArray(String[] startArray) {

        String[] randomArray = startArray;
        Collections.shuffle(Arrays.asList(randomArray));

        return randomArray;
    }

    /**
     * Generate the new game field by randomized array.
     *
     * @param randomArray Posting randomized Array of elements for game field creation.
     * @return Return "zeroBtnID" string
     */
    public String GameFieldGenerator(String[] randomArray) {

        Integer numberButton = 0;
        Integer firstIndex = 1;
        String zeroBtnID = "0";

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; ) {
                Integer secondIndex = j + 1;

                String buttonID = "c" + (String.valueOf(firstIndex)) + "x" + (String.valueOf(secondIndex));
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                Button btnID = findViewById(resID);

                btnID.setText(randomArray[numberButton]);

                if (Integer.valueOf(randomArray[numberButton]) == 0) {
                    btnID.setVisibility(View.INVISIBLE);
                    zeroBtnID = buttonID;
                }
                numberButton++;
                j++;
            }
            firstIndex++;
        }
        return zeroBtnID;
    }

    /**
     * Setting up buttons as may be pressed.
     *
     * @param zeroBtnID ID for zeroBtn
     * @param randomArray Main array for setting clickable buttons.
     */
    public void SetPressedKeys(String zeroBtnID, final String[] randomArray) {

        String aroundButtonH1 = "0";
        String aroundButtonW1 = "0";
        String aroundButtonH2 = "0";
        String aroundButtonW2 = "0";

        if (!zeroBtnID.equals("0")) {
            String firstIndex = String.valueOf(zeroBtnID.charAt(1));
            String secondIndex = String.valueOf(zeroBtnID.charAt(3));

            Integer aroundCellH1 = Integer.valueOf(secondIndex) - 1;
            Integer aroundCellH2 = Integer.valueOf(secondIndex) + 1;
            Integer aroundCellW1 = Integer.valueOf(firstIndex) - 1;
            Integer aroundCellW2 = Integer.valueOf(firstIndex) + 1;

            if (aroundCellH1 < 5 && aroundCellH1 > 0) {
                aroundButtonH1 = "c" + firstIndex + "x" + aroundCellH1;
            }
            if (aroundCellH2 < 5 && aroundCellH2 > 0) {
                aroundButtonH2 = "c" + firstIndex + "x" + aroundCellH2;
            }
            if (aroundCellW1 < 5 && aroundCellW1 > 0) {
                aroundButtonW1 = "c" + aroundCellW1 + "x" + secondIndex;
            }
            if (aroundCellW2 < 5 && aroundCellW2 > 0) {
                aroundButtonW2 = "c" + aroundCellW2 + "x" + secondIndex;
            }
        }

        final String aroundButtons[] = new String[]{aroundButtonW1, aroundButtonH1, aroundButtonW2, aroundButtonH2};

        for (int n = 0; n < aroundButtons.length; ) {

            Integer stringID = getResources().getIdentifier(aroundButtons[n], "id", getPackageName());
            Button ButtonNumID = findViewById(stringID);

            if (!aroundButtons[n].equals("0")) {

                final int keyIndex = n;

                ButtonNumID.setOnClickListener(new View.OnClickListener() {

                    /**
                     * Override onClick method for ClickListener
                     * @param v View v
                     */
                    @Override
                    public void onClick(View v) {

                        ChangePosition(aroundButtons[keyIndex], randomArray);
                        //ButtonNumID.setText("Pressed");
                    }
                });
            }
            n++;
        }
    }

    /**
     * Method for change button position.
     *
     * @param pressedKeyName Name of pressed key.
     * @param randomArray    Main array for changing, when any key was pressed.
     */
    public void ChangePosition(String pressedKeyName, String[] randomArray) {

        String valuePressedKey;
        Integer zeroKeyPos = null;
        Integer pressedKeyPos = null;

        Integer pressedID = getResources().getIdentifier(pressedKeyName, "id", getPackageName());
        Button pressedBtn = findViewById(pressedID);

        valuePressedKey = String.valueOf(pressedBtn.getText());

        Integer posNum = 0;
        for (int n = 0; n < randomArray.length; n++) {
            if (randomArray[n].equals(valuePressedKey)) {
                pressedKeyPos = posNum;
            }
            if (randomArray[n].equals("0")) {
                zeroKeyPos = posNum;
            }
            posNum++;
        }

        if (pressedKeyPos != null && zeroKeyPos != null) {
            String temp = randomArray[pressedKeyPos];
            randomArray[pressedKeyPos] = randomArray[zeroKeyPos];
            randomArray[zeroKeyPos] = temp;
        }

        MainActivity.setNextTurnArray(randomArray);
        recreate();
    }
}

    /*
    public Integer CheckWin(String[] nextTurnArray, String[] startArray) {

        AlertDialog.Builder ad;
        Context context;

        boolean compareResult = compareArrays(startArray, nextTurnArray);

        if (compareResult) {

            context = MainActivity.this;
            String title = "You won!!!";
            String message = "Do you want to continue?";
            String reloadBtn = "Start again";
            String exitBtn = "Exit";

            ad = new AlertDialog.Builder(context);
            ad.setTitle(title);  // заголовок
            ad.setMessage(message); // сообщение
            ad.setPositiveButton(reloadBtn, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int arg1) {
                    Toast.makeText(context, "Вы сделали правильный выбор",
                            Toast.LENGTH_LONG).show();
                    resultCode = 1;
                }
            });
            ad.setNegativeButton(exitBtn, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int arg1) {
                    Toast.makeText(context, "Возможно вы правы", Toast.LENGTH_LONG)
                            .show();
                    resultCode = 2;
                }
            });
            ad.setCancelable(true);
            ad.setOnCancelListener(new DialogInterface.OnCancelListener() {
                public void onCancel(DialogInterface dialog) {
                    Toast.makeText(context, "Вы ничего не выбрали",
                            Toast.LENGTH_LONG).show();
                    resultCode = 0;
                }
            });

        } else {
            resultCode = 0;
        }
        return resultCode;
    }

    public static boolean compareArrays(String[] startArray, String[] randomArray) {
        boolean result = false;
        if (randomArray != null) {
            for (int i = 0; i < randomArray.length; i++) {
                if (randomArray[i].equals(startArray[i])) {

                    result = true;

                }
            }
        }

        return result;
    }

    */