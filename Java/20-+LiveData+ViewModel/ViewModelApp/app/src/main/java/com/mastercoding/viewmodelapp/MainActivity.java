package com.mastercoding.viewmodelapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;

import com.mastercoding.viewmodelapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    MyViewModel viewModel;
    ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainBinding = DataBindingUtil.setContentView(this,
                R.layout.activity_main);


        viewModel = new ViewModelProvider(this)
                .get(MyViewModel.class);

        // Link the Databinding with ViewModel
        mainBinding.setMyviewmodel(viewModel);

        viewModel.defineCounter();

        // Observing the LiveData
        viewModel.getCounter().observe(this,
                new Observer<Integer>() {
                    @Override
                    public void onChanged(Integer counter) {
                        // Update the UI when the LiveData Changes
                        mainBinding.textView2.setText(""+counter);
                    }
                });


    }


}