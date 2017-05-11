package com.example.ellis.haxddesign.Presenter;

import android.app.DialogFragment;
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
import com.example.ellis.haxddesign.R;

public class MainScreen extends AppCompatActivity implements View.OnClickListener, LoginDialogFragment.LoginDialogListener{
    private Button start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        Backendless.initApp("73B8E514-FB28-B17E-FF84-3BF6B88BD000",
                "929DA8BC-4FDE-CFAF-FF66-0B4B156DCB00", "v1");

        DialogFragment newFragment = new LoginDialogFragment();
        newFragment.show(getFragmentManager(), "Log In");
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

    @Override
    public void onDialogPositiveClick(final LoginDialogFragment dialog) {
        Backendless.UserService.login(dialog.getEmail(), dialog.getPassword(), new AsyncCallback<BackendlessUser>() {
            @Override
            public void handleResponse(BackendlessUser response) {
                Toast.makeText(MainScreen.this, "Logged In", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Toast.makeText(MainScreen.this, ""+fault.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDialogNegativeClick(LoginDialogFragment dialog) {
        startActivity(new Intent(this, RegisterActivity.class));
    }
}
