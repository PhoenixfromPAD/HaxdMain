package com.example.ellis.haxddesign.Presenter;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.ellis.haxddesign.Model.Game;
import com.example.ellis.haxddesign.R;

import static com.example.ellis.haxddesign.R.id.coordinatorLayout;

public class HackerActivity extends AppCompatActivity {
    private TextView progress;
    private ProgressBar download;
    private int prog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hacker);

        Game game = new Game();
        wireWidgets();
        progress.setText("0%");
        download.setProgress(0);
        download.setMax(100);
        prog = download.getProgress();
        new CountDownTimer(game.getDownloadTime(), game.getTick()) {

            public void onTick(long millisUntilFinished) {
                download.setProgress(prog += 1);
                progress.setText("" + download.getProgress() + "%");
                if(download.getProgress() == 100){
                    onFinish();
                    cancel();
                }
            }

            public void onFinish() {
                Snackbar snackbar = Snackbar.make(findViewById(coordinatorLayout), "Hack Complete", Snackbar.LENGTH_INDEFINITE).setAction("Next", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Return to Target Select
                    }
                });
                snackbar.show();
            }
        }.start();
    }

    private void wireWidgets() {
        progress = (TextView) findViewById(R.id.textViewProgress);
        download = (ProgressBar) findViewById(R.id.progressBarTarget);
    }


}
