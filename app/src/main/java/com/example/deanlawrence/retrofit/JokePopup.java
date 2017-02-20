package com.example.deanlawrence.retrofit;

import android.app.Dialog;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.Html;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;


/**
 * Created by deanlawrence on 14/2/17.
 */

public class JokePopup extends DialogFragment implements View.OnClickListener{

    int mJokeId = 0;
    String mJoke = "";

    TextView jokeId;
    Button close;

    TextView joke;

    View rootView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.joke_popup, container);

        initialiseView();

        if(savedInstanceState == null) {

            getArgs();

        } else {

            mJokeId = savedInstanceState.getInt("jokeId",0);
            mJoke = savedInstanceState.getString("joke","");

        }

        setData();

        return rootView;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



    }

    private void initialiseView(){

        jokeId = (TextView) rootView.findViewById(R.id.tv_id);
        close = (Button) rootView.findViewById(R.id.b_close);
        close.setOnClickListener(this);

        joke = (TextView) rootView.findViewById(R.id.tv_joke);

    }

    private void getArgs(){

        mJokeId = getArguments().getInt("jokeId", 0);
        mJoke = getArguments().getString("joke","");

    }

    private void setData(){

        jokeId.setText(getString(R.string.jokeId,mJokeId));
        joke.setText(Html.fromHtml(mJoke));

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);

        // request a window without the title
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        //get the screen size from activity and work out 75% fill size
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        Double sWidth = size.x * .95;
        Double sHeight = size.y * .95;

        //create a new layout params object to set the dialog size params
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = sWidth.intValue();
        lp.height = sHeight.intValue();
        lp.dimAmount = .7f;

        dialog.getWindow().requestFeature(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

        //set the size of the dialog from the
        dialog.getWindow().setAttributes(lp);

        return dialog;
    }

    @Override
    public void onClick(View v) {

            switch (v.getId()){

                case R.id.b_close:
                    dismiss();
                    break;

            }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("jokeId",mJokeId);
        outState.putString("joke",mJoke);

    }
}
