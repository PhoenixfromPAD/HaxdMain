package com.example.ellis.haxddesign.Presenter;

import android.content.Context;
import android.content.Intent;
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
import com.example.ellis.haxddesign.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText emailET, passwordET;
    private Button registerBtn, loginBtn;
    String email, password;
    private Context loginContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Backendless.initApp(this, "73B8E514-FB28-B17E-FF84-3BF6B88BD000",
                "929DA8BC-4FDE-CFAF-FF66-0B4B156DCB00", "v1");

        emailET = (EditText) findViewById(R.id.login_editText_email);
        passwordET = (EditText) findViewById(R.id.login_editText_password);

        registerBtn = (Button) findViewById(R.id.login_button_register);
        loginBtn = (Button) findViewById(R.id.login_button_login);

        registerBtn.setOnClickListener(this);
        loginBtn.setOnClickListener(this);
        loginContext = this;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.login_button_register) {
            Intent i = new Intent(this, RegisterActivity.class);
            startActivity(i);
        } else if (id == R.id.login_button_login) {
            login();
        }
    }

    private void login() {
        if(emailET.getText() == null || passwordET.getText() == null) {
            Toast.makeText(this, "Empty Fields", Toast.LENGTH_SHORT).show();
        } else {
            email = emailET.getText().toString().trim();
            password = passwordET.getText().toString().trim();
            Backendless.UserService.login(email, password, new AsyncCallback<BackendlessUser>() {
                @Override
                public void handleResponse(BackendlessUser response) {
                    Toast.makeText(LoginActivity.this, "Logged In", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(loginContext, MainScreen.class);
                    startActivity(i);
                    finish();
                }

                @Override
                public void handleFault(BackendlessFault fault) {
                    Toast.makeText(LoginActivity.this, ""+fault.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
