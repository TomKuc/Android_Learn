package com.mastercoding.databindingapp;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

public class MyClickHandler {

    Context context;

    public MyClickHandler(Context context) {
        this.context = context;
    }

    public void onButton1Clicked(View view){
        Toast.makeText(
                context,
                "First Button is Clicked",
                Toast.LENGTH_SHORT).show();

    }

}
