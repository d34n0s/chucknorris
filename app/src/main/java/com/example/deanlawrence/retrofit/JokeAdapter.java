package com.example.deanlawrence.retrofit;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.deanlawrence.retrofit.Models.JokeData;

import java.util.List;

/**
 * Created by deanlawrence on 13/2/17.
 */

class JokeAdapter extends RecyclerView.Adapter<JokeAdapter.ViewHolder> {

    private String TAG = getClass().getSimpleName();

    //Data
    private List<JokeData> mJokeData;

    private JokeInterface ji;



    JokeAdapter(List<JokeData> data, JokeInterface ji) {

        mJokeData = data;
        this.ji = ji;

    }

    //View
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        TextView tv_joke;


        ViewHolder(View itemView) {
            super(itemView);

            tv_joke = (TextView) itemView.findViewById(R.id.tv_joke);

            tv_joke.setClickable(true);
            tv_joke.setOnClickListener(this);

        }


        @Override
        public void onClick(View v) {

            JokeData jd = mJokeData.get(getAdapterPosition());

            switch (v.getId()) {

                case R.id.tv_joke:

                    Log.d(TAG, "Joke Clicked: pos:"+ getAdapterPosition() + ", id:" + jd.getId());

                    ji.showJokePopup(jd);

                    break;

            }

        }

    }

    @Override
    public int getItemCount() {

        return mJokeData.size();

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.joke_cell, viewGroup, false);
        ViewHolder pvh = new ViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        JokeData jd = mJokeData.get(i);

        viewHolder.tv_joke.setText(jd.getJoke() == null ? "" : Html.fromHtml(jd.getJoke()));

    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}

