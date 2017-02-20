package com.example.deanlawrence.retrofit;

import com.example.deanlawrence.retrofit.Models.JokeData;

import java.util.List;

/**
 * Created by deanlawrence on 13/2/17.
 */

interface JokeInterface {

    void returnedJokeList (List<JokeData> jokeList);

    void showJokePopup (JokeData jokeData);
}
