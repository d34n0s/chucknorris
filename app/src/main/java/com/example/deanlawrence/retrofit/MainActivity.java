package com.example.deanlawrence.retrofit;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSeekBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import com.example.deanlawrence.retrofit.Models.JokeData;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, JokeInterface{

    String TAG = getClass().getSimpleName();

    AppCompatSeekBar seekBar;
    Button b_get;
    Button b_clear;
    RecyclerView rv_jokes;

    JokeHelper jh;

    ArrayList<JokeData> jokeDataList;

    LinearLayoutManager layoutManager;
    JokeAdapter ja;

    final static String prefsFilename = "jokeSharedPrefsDataFile";
    static SharedPreferences prefsSP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jh = new JokeHelper(this);


        if(savedInstanceState == null || !savedInstanceState.containsKey("jokeDataList")){

            jokeDataList = new ArrayList<>();

        } else {

            jokeDataList = savedInstanceState.getParcelableArrayList("jokeDataList");

        }

        prefsSP = getSharedPreferences(prefsFilename, Context.MODE_PRIVATE);

        initialiseView();

    }

    private void initialiseView(){

        seekBar = (AppCompatSeekBar) findViewById(R.id.seekBar);

        b_get = (Button) findViewById(R.id.b_get);
        b_get.setOnClickListener(this);

        b_clear = (Button) findViewById(R.id.b_clear);
        b_clear.setOnClickListener(this);

        rv_jokes = (RecyclerView) findViewById(R.id.rv_jokes);

        layoutManager = new LinearLayoutManager(this);

        rv_jokes.setLayoutManager(layoutManager);

        ja = new JokeAdapter(jokeDataList, this);

        rv_jokes.setAdapter(ja);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                b_get.setText(getResources().getString(R.string.b_get_text,progress + 1));

                saveNumberOfJokesRequestedLastTime();

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBar.setProgress(getNumberOfJokesRequestedLastTime());

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.b_get:

                jh.getJoke(seekBar.getProgress() + 1);

                break;

            case R.id.b_clear:

                jokeDataList.clear();
                ja.notifyDataSetChanged();

                break;

        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelableArrayList("jokeDataList",jokeDataList);

    }

    private void saveNumberOfJokesRequestedLastTime(){

        SharedPreferences.Editor editor = prefsSP.edit();

        editor.putInt("seekBar", seekBar.getProgress());

        editor.apply();

    }

    private int getNumberOfJokesRequestedLastTime(){

         return prefsSP.getInt("seekBar", 0);

    }

    @Override
    public void returnedJokeList(List<JokeData> jokeList) {

        Log.d(TAG, "returnedJokeList size: " + jokeList.size());

        jokeDataList.addAll(jokeList);
        ja.notifyDataSetChanged();

    }

    @Override
    public void showJokePopup(JokeData jokeData) {

        DialogFragment jokePopup = new JokePopup();

        Bundle bundle = new Bundle();

        bundle.putInt("jokeId", jokeData.getId());
        bundle.putString("joke", jokeData.getJoke());

        jokePopup.setArguments(bundle);

        jokePopup.show(getSupportFragmentManager(),"alert_preview");

    }
}
