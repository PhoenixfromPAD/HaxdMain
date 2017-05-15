package com.example.ellis.haxddesign.Presenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.example.ellis.haxddesign.Model.Player;
import com.example.ellis.haxddesign.R;

public class MainScreen extends AppCompatActivity implements View.OnClickListener{
    private Button start;
    private BackendlessUser user;
    Player player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        Backendless.initApp(this, "73B8E514-FB28-B17E-FF84-3BF6B88BD000",
                "929DA8BC-4FDE-CFAF-FF66-0B4B156DCB00", "v1");
        user = null;
        //TODO Remove
        Backendless.UserService.login("rbruner360@gmail.com", "12345", new AsyncCallback<BackendlessUser>() {
            @Override
            public void handleResponse(BackendlessUser response) {
                Toast.makeText(MainScreen.this, "Logged In", Toast.LENGTH_SHORT).show();
                user = Backendless.UserService.CurrentUser();
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Toast.makeText(MainScreen.this, ""+fault.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        user = Backendless.UserService.CurrentUser();
        player = new Player();
        player.busy = false;
        player.hcr = 0;
        player.user = user;
        Backendless.Data.of(Player.class).save(player, new AsyncCallback<Player>() {
            @Override
            public void handleResponse(Player response) {
                Toast.makeText(MainScreen.this, "Player made", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Toast.makeText(MainScreen.this, "PLayer Not Made", Toast.LENGTH_SHORT).show();
            }
        });

        wireWidgets();
    }


    private void wireWidgets() {
        start = (Button) findViewById(R.id.buttonStart);

        start.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.buttonStart) {
            Intent i = new Intent(MainScreen.this, TargetSelect.class);
            startActivity(i);
            finish();
        }
    }


}
