package com.example.deanlawrence.retrofit.Models;

/**
 * Created by deanlawrence on 8/2/17.
 */

public class Joke {


    String type;
    JokeData value = new JokeData();

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public JokeData getValue() {
        return value;
    }

    public void setValue(JokeData value) {
        this.value = value;
    }


}
