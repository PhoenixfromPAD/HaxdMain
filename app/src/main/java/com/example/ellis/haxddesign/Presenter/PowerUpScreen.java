package com.example.ellis.haxddesign.Presenter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ellis.haxddesign.R;

public class PowerUpScreen extends AppCompatActivity {
    private Button pauseUse, terminateUse, locateUse, slowUse, buttonback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power_up_screen);
        wireWidgets();

        pauseUse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //pause on use
            }
        });
        terminateUse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //terminate on use
            }
        });
    }

    private void wireWidgets() {
        pauseUse = (Button) findViewById(R.id.buttonPauseUse);
        terminateUse = (Button) findViewById(R.id.buttonTerminateUse);
        locateUse = (Button) findViewById(R.id.buttonLocateUse);
        slowUse = (Button) findViewById(R.id.buttonSlowUse);
        buttonback = (Button) findViewById(R.id.buttonBack);
    }
}
