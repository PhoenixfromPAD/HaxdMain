package com.example.ellis.haxddesign.Presenter;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ellis.haxddesign.R;

/**
 * Created by Randy Bruner on 5/9/17.
 */

public class LoginDialogFragment extends DialogFragment {
    public final String TAG = "LoginDialogFragment";
    private EditText emailET, passwordET;
    private String email, password;
    private LoginDialogListener mListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        emailET = (EditText) getActivity().findViewById(R.id.dialogLogin_editText_email);
        passwordET = (EditText) getActivity().findViewById(R.id.dialogLogin_editText_password);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Use this instance of the interface to deliver action events

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.dialog_login, null))
                // Add action buttons
                .setPositiveButton("Log In", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        if(emailET.getText().toString().trim() != null && passwordET.getText().toString().trim() != null) {
                            email = emailET.getText().toString().trim();
                            password = passwordET.getText().toString().trim();
                            mListener.onDialogPositiveClick(LoginDialogFragment.this, email, password);
                        } else {
                            Toast.makeText(getActivity(), "Blank Fields!", Toast.LENGTH_LONG).show();
                        }
                    }
                })
                .setNegativeButton("Register", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mListener.onDialogNegativeClick(LoginDialogFragment.this);
                    }
                });

        return builder.create();
    }

    /* The activity that creates an instance of this dialog fragment must
     * implement this interface in order to receive event callbacks.
     * Each method passes the DialogFragment in case the host needs to query it. */
    public interface LoginDialogListener {
        public void onDialogPositiveClick(DialogFragment dialog, String email, String Password);
        public void onDialogNegativeClick(DialogFragment dialog);
    }

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Context context) {
        Log.d(TAG, "onAttach: working");
        super.onAttach(context);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (LoginDialogListener) context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(context.toString()
                    + " must implement NoticeDialogListener");
        }
    }
    @Override
    public void onAttach(Activity activity) {
        Log.d(TAG, "onAttach: working");
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (LoginDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }


}
