package com.example.nprezive.cs3270a3;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentIndividual extends Fragment {


    OnGameBtnSelectedListener mCallback;
    Button btnRock, btnPaper, btnScissors;

    public FragmentIndividual() {
        // Required empty public constructor
    }

    public interface OnGameBtnSelectedListener {
        public void onGameBtnClicked(Winner winner);
    }


    @Override
    public void onAttach(Context context){
        super.onAttach(context);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (OnGameBtnSelectedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnGameBtnSelectedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_individual, container, false);

        btnRock = rootView.findViewById(R.id.btn_rock);
        btnPaper = rootView.findViewById(R.id.btn_paper);
        btnScissors = rootView.findViewById(R.id.btn_scissors);

        btnRock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { mCallback.onGameBtnClicked(whoWon(Choice.ROCK)); }
        });
        btnPaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { mCallback.onGameBtnClicked(whoWon(Choice.PAPER)); }
        });
        btnScissors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { mCallback.onGameBtnClicked(whoWon(Choice.SCISSORS)); }
        });

        return rootView;
    }

    private Winner whoWon(Choice playerChoice) {
        Choice phoneChoice = Choice.values()[(int)(Math.random() * 3)];
        switch (playerChoice) {
            case ROCK:
                switch (phoneChoice) {
                    case ROCK: return Winner.TIE;
                    case PAPER: return Winner.PHONE;
                    case SCISSORS: return Winner.ME;
                }
            case PAPER:
                switch (phoneChoice) {
                    case ROCK: return Winner.ME;
                    case PAPER: return Winner.TIE;
                    case SCISSORS: return Winner.PHONE;
                }
            case SCISSORS:
                switch (phoneChoice) {
                    case ROCK: return Winner.PHONE;
                    case PAPER: return Winner.ME;
                    case SCISSORS: return Winner.TIE;
                }
            default: return null;
        }
    }

    public enum Choice {ROCK, PAPER, SCISSORS}
    public enum Winner {PHONE, ME, TIE}

}
