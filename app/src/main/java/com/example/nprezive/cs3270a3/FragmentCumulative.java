package com.example.nprezive.cs3270a3;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentCumulative extends Fragment {
    private View rootView;
    private int gamesPlayed, phoneWins, myWins, tieWins = 0;

    public FragmentCumulative() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_cumulative, container, false);
        rootView.findViewById(R.id.btn_reset_counts).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Reset private variables
                gamesPlayed = phoneWins = myWins = tieWins = 0;

                //Reset TextViews
                ((TextView)rootView.findViewById(R.id.txv_games_played)).setText(R.string.zero);
                ((TextView)rootView.findViewById(R.id.txv_my_wins)).setText(R.string.zero);
                ((TextView)rootView.findViewById(R.id.txv_phone_wins)).setText(R.string.zero);
                ((TextView)rootView.findViewById(R.id.txv_tie_wins)).setText(R.string.zero);

                //Reset button background colors

                //Display Toast
                Toast.makeText(getContext(), R.string.counts_are_reset, Toast.LENGTH_SHORT)
                        .show();
            }
        });
        return rootView;
    }

    public void updateCounts(FragmentIndividual.Winner winner) {
        gamesPlayed++;
        ((TextView)rootView.findViewById(R.id.txv_games_played))
                .setText(Integer.toString(gamesPlayed));
        switch (winner) {
            case ME:
                myWins++;
                ((TextView)rootView.findViewById(R.id.txv_my_wins))
                        .setText(Integer.toString(myWins));
                break;
            case PHONE:
                phoneWins++;
                ((TextView)rootView.findViewById(R.id.txv_phone_wins))
                        .setText(Integer.toString(phoneWins));
                break;
            case TIE:
                tieWins++;
                ((TextView)rootView.findViewById(R.id.txv_tie_wins))
                        .setText(Integer.toString(tieWins));
                break;
        }
    }
}
