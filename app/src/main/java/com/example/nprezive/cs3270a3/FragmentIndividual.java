package com.example.nprezive.cs3270a3;


import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentIndividual extends Fragment {

    OnGameBtnSelectedListener mCallback;
    Button btnRock, btnPaper, btnScissors;
    View rootView;

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
        rootView = inflater.inflate(R.layout.fragment_individual, container, false);

        btnRock = (Button)rootView.findViewById(R.id.btn_rock);
        btnPaper = rootView.findViewById(R.id.btn_paper);
        btnScissors = rootView.findViewById(R.id.btn_scissors);

        btnRock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetState();
                btnRock.setBackgroundTintList(getResources().getColorStateList(R.color.colorAccent));
                setTxvsByChoice(Choice.ROCK);
            }
        });

        btnPaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetState();
                btnPaper.setBackgroundTintList(getResources().getColorStateList(R.color.colorAccent));
                setTxvsByChoice(Choice.PAPER);
            }
        });

        btnScissors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetState();
                btnScissors.setBackgroundTintList(getResources().getColorStateList(R.color.colorAccent));
                setTxvsByChoice(Choice.SCISSORS);
            }
        });

        return rootView;
    }

    private void setTxvsByChoice(Choice playerChoice) {
        //Display phone's choice
        Choice phoneChoice = Choice.values()[(int)(Math.random() * 3)];
        switch (phoneChoice) {
            case ROCK:
                ((TextView)rootView.findViewById(R.id.txv_phone_pick))
                        .setText(R.string.rock);
                break;
            case PAPER:
                ((TextView)rootView.findViewById(R.id.txv_phone_pick))
                        .setText(R.string.paper);
                break;
            case SCISSORS:
                ((TextView)rootView.findViewById(R.id.txv_phone_pick))
                        .setText(R.string.scissors);
                break;
        }

        //Display winner
        Winner winner = whoWon(playerChoice, phoneChoice);
        switch (winner) {
            case PHONE:
                ((TextView)rootView.findViewById(R.id.txv_game_result))
                        .setText(R.string.phone_wins);
                break;
            case ME:
                ((TextView)rootView.findViewById(R.id.txv_game_result))
                        .setText(R.string.you_win);
                break;
            case TIE:
                ((TextView)rootView.findViewById(R.id.txv_game_result))
                        .setText(R.string.its_a_tie);
                break;
        }

        //Callback to Activity with winner info
        mCallback.onGameBtnClicked(winner);
    }

    private Winner whoWon(Choice playerChoice, Choice phoneChoice) {
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

    private void resetState() {
        btnRock.setBackgroundTintList(ColorStateList.valueOf(Color.LTGRAY));
        btnPaper.setBackgroundTintList(ColorStateList.valueOf(Color.LTGRAY));
        btnScissors.setBackgroundTintList(ColorStateList.valueOf(Color.LTGRAY));
        ((TextView)rootView.findViewById(R.id.txv_phone_pick)).setText(R.string.question_mark);
        ((TextView)rootView.findViewById(R.id.txv_game_result)).setText(R.string.default_result);
    }

    public enum Choice {ROCK, PAPER, SCISSORS}
    public enum Winner {PHONE, ME, TIE}

}
