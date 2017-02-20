package com.example.deanlawrence.retrofit.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by deanlawrence on 9/2/17.
 */

public class JokeData implements Parcelable {

    Integer id;
    String joke;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }

    JokeData(){

    }

    private JokeData(Parcel in){
        id = in.readInt();
        joke = in.readString();
    }

    public static final Parcelable.Creator<JokeData> CREATOR = new Parcelable.Creator<JokeData>() {
        public JokeData createFromParcel(Parcel in) {
            return new JokeData(in);
        }

        public JokeData[] newArray(int size) {
            return new JokeData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(joke);
    }
}
