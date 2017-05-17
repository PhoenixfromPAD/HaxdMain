package com.example.ellis.haxddesign.Presenter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.example.ellis.haxddesign.Model.Player;
import com.example.ellis.haxddesign.R;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText emailET, usernameET, passwordET, passwordConfirmET;
    private Button cancelBtn, registerBtn;
    private String email, password, username;
    private BackendlessUser user;
    private Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Backendless.initApp(this, "73B8E514-FB28-B17E-FF84-3BF6B88BD000",
                "929DA8BC-4FDE-CFAF-FF66-0B4B156DCB00", "v1");

        wireWidgets();
    }

    private void wireWidgets() {
        emailET = (EditText) findViewById(R.id.register_editText_email);
        usernameET = (EditText) findViewById(R.id.register_editText_username);
        passwordConfirmET = (EditText) findViewById(R.id.register_editText_passwordConfirm);
        passwordET = (EditText) findViewById(R.id.register_editText_password);

        cancelBtn = (Button) findViewById(R.id.register_button_cancel);
        registerBtn = (Button) findViewById(R.id.register_button_register);

        cancelBtn.setOnClickListener(this);
        registerBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.register_button_cancel) {
            finish();
        } else if (id == R.id.register_button_register) {
            register();
        }
    }

    private void register() {
        if(emailET.getText().toString().trim() == null && usernameET.getText().toString().trim() == null &&
                passwordET.getText().toString().trim() == null && passwordConfirmET.getText().toString().trim() == null) {
            Toast.makeText(this, "Empty Fields!", Toast.LENGTH_SHORT).show();
        } else if (!passwordET.getText().toString().trim().equals(passwordConfirmET.getText().toString().trim())) {
            Toast.makeText(this, "Passwords Don't Match", Toast.LENGTH_SHORT).show();
        } else {
            email = emailET.getText().toString().trim();
            password = passwordET.getText().toString().trim();
            username = usernameET.getText().toString().trim();

            user = new BackendlessUser();
            user.setEmail(email);
            user.setPassword(password);
            user.setProperty("username", username);
            Backendless.UserService.register(user, new AsyncCallback<BackendlessUser>() {
                @Override
                public void handleResponse(BackendlessUser response) {
                    Toast.makeText(RegisterActivity.this, "Account Created!", Toast.LENGTH_SHORT).show();
                    player = new Player();
                    player.busy = false;
                    player.hcr = 0;
                    player.user = user;
                    player.name = username;
                    user.setProperty("player", player);
                    Backendless.Persistence.of(Player.class).save((Player) user.getProperty("player"), new AsyncCallback<Player>() {
                        @Override
                        public void handleResponse(Player response) {
                            Toast.makeText(RegisterActivity.this, "Player Made", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {
                            Toast.makeText(RegisterActivity.this, ""+fault.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                @Override
                public void handleFault(BackendlessFault fault) {
                    Toast.makeText(RegisterActivity.this, "" + fault.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
