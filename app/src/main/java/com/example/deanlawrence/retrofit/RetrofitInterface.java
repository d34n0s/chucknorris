package com.example.deanlawrence.retrofit;

import com.example.deanlawrence.retrofit.Models.Joke;
import com.example.deanlawrence.retrofit.Models.Jokes;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by deanlawrence on 8/2/17.
 */

public interface RetrofitInterface {

    @GET("/jokes/random")
    Call<Joke> joke();

    @GET("/jokes/random/{number}")
    Call<Jokes> jokes(@Path("number") String number);

}
