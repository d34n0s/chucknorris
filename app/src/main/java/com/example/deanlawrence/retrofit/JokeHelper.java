package com.example.deanlawrence.retrofit;

import android.util.Log;

import com.example.deanlawrence.retrofit.Models.Joke;
import com.example.deanlawrence.retrofit.Models.JokeData;
import com.example.deanlawrence.retrofit.Models.Jokes;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by deanlawrence on 13/2/17.
 */

class JokeHelper {

    private RetrofitInterface ri;
    private JokeInterface ji;

    private List<JokeData> jokeList;

    private String TAG = getClass().getSimpleName();

    JokeHelper(JokeInterface ji){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.icndb.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ri = retrofit.create(RetrofitInterface.class);

        this.ji = ji;

        jokeList = new ArrayList<>();

    }

    void getJoke(int numberOfJokes){

        Log.d(TAG, "getJoke: " + numberOfJokes);

        if(numberOfJokes > 1){

            getMultipleJokes(numberOfJokes);

        } else {

            getAJoke();

        }

    }

    private void getAJoke(){

        Log.d(TAG,"getAJoke()");

        jokeList.clear();

        Call<Joke> jokeCall = ri.joke();

        jokeCall.enqueue(new Callback<Joke>() {
            @Override
            public void onResponse(Call<Joke> call, Response<Joke> response) {

                Log.d(TAG,"success");

                jokeList.add(response.body().getValue());

                ji.returnedJokeList(jokeList);

            }

            @Override
            public void onFailure(Call<Joke> call, Throwable t) {

                Log.d(TAG,"fail: " + t.toString());

            }
        });

    }

    private void getMultipleJokes(int i){

        Log.d(TAG,"getMultipleJokes()");

        jokeList.clear();

        Call<Jokes> jokesCall = ri.jokes(String.valueOf(i));

        jokesCall.enqueue(new Callback<Jokes>() {
            @Override
            public void onResponse(Call<Jokes> call, Response<Jokes> response) {

                Log.d(TAG,"success");

                for(int i = 0; i < response.body().getValue().size(); i++){

                    jokeList.add(response.body().getValue().get(i));

                }

                ji.returnedJokeList(jokeList);

            }

            @Override
            public void onFailure(Call<Jokes> call, Throwable t) {

                Log.d(TAG,"fail: " + t.toString());

            }
        });

    }
}
