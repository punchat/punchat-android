package com.punchat.presentation.base;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.punchat.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
    }
}
