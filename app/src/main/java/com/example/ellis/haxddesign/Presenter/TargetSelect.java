package com.example.ellis.haxddesign.Presenter;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.backendless.Backendless;
import com.example.ellis.haxddesign.R;

public class TargetSelect extends AppCompatActivity {
    private Button findTarget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target_select);
        Backendless.initApp(this, "73B8E514-FB28-B17E-FF84-3BF6B88BD000",
                "929DA8BC-4FDE-CFAF-FF66-0B4B156DCB00", "v1");

        FragmentManager fm = getSupportFragmentManager();
        if(fm.findFragmentByTag("PlayerFragment")==null) {
            fm.beginTransaction()
                    .add(R.id.player_list_container, new PlayerFragment(), "SuperheroFragment")
                    .commit();
        }

        //findTarget = (Button) findViewById(R.id.buttonFindTarget);

        /*findTarget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TargetSelect.this, HackerActivity.class);
                startActivity(i);
                finish();
            }
        });
        */
    }
}
