package ivs.ilves.simple15puzzle.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ivs.ilves.simple15puzzle.R;


public class GameField extends Fragment {

    //
    // Required empty public constructor
    //
    public GameField() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //
        // INFLATE: The layout for this fragment
        //
        return inflater.inflate(R.layout.fragment_game_field, container, false);
    }
}
