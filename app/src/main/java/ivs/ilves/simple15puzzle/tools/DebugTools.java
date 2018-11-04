package ivs.ilves.simple15puzzle.tools;

import android.util.Log;


public class DebugTools {

    public static void writeLog(char logLevel, String messageText ) {
        switch (logLevel) {
            case 'v':
                Log.v("MyLOG.VERBOSE", messageText);
                break;
            case 'd':
                Log.d("MyLOG.DEBUG", messageText);
                break;
            case 'i':
                Log.i("MyLOG.INFO", messageText);
                break;
            case 'w':
                Log.w("MyLOG.WARNING", messageText);
                break;
            case 'e':
                Log.e("MyLOG.ERROR", messageText);
                break;
        }
    }
}