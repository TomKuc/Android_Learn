package com.mastercoding.viewmodelapp;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {


    private MutableLiveData<Integer> counter = new MutableLiveData<>();

    public void defineCounter(){
        counter.setValue(0);
    }

    public void increaseCounter(View view){
        // Retrieve the current value from LiveData
        int currentValue = counter.getValue() !=null ? counter.getValue():0;

        // Increase Value by 1
        counter.setValue(currentValue+1);
    }

    public LiveData<Integer> getCounter() {
        return counter;
    }
}
