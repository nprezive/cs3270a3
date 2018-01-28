package com.example.nprezive.cs3270a3;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity
        implements FragmentIndividual.OnGameBtnSelectedListener {


    FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fm = getSupportFragmentManager();

        fm.beginTransaction()
                .replace(R.id.individual_frag, new FragmentIndividual(), "fragContainer1")
                .replace(R.id.cumulative_frag, new FragmentCumulative(), "fragContainer2")
                .commit();
    }


    @Override
    public void onGameBtnClicked(FragmentIndividual.Winner winner) {
        ((FragmentCumulative)(fm.findFragmentByTag("fragContainer2")))
                .updateCounts(winner);
    }
}
