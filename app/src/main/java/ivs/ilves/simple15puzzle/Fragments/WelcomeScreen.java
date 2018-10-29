package ivs.ilves.simple15puzzle.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ivs.ilves.simple15puzzle.R;


public class WelcomeScreen extends Fragment {


    public WelcomeScreen() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //
        // Inflate the layout for this fragment
        //
        return inflater.inflate(R.layout.fragment_welcome_screen, container, false);
    }

}