package com.alex.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.alex.siriwaveview.SiriWaveView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SiriWaveView waveView = (SiriWaveView)findViewById(R.id.siriWaveView);
    }
}
