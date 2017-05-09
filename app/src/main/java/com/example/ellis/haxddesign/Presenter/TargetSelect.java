package com.example.ellis.haxddesign.Presenter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ellis.haxddesign.R;

public class TargetSelect extends AppCompatActivity {
    private Button findTarget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target_select);

        findTarget = (Button) findViewById(R.id.buttonFindTarget);

        findTarget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Move to hacker Activity
            }
        });
    }
}
