package com.example.deanlawrence.retrofit.Models;

import com.example.deanlawrence.retrofit.Models.JokeData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by deanlawrence on 8/2/17.
 */

public class Jokes {


    String type;
    List<JokeData> value = new ArrayList<>();

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<JokeData> getValue() {
        return value;
    }

    public void setValue(List<JokeData> value) {
        this.value = value;
    }


}
