package com.example.hangman2;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class WinActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);
        Button btn_play = findViewById(R.id.playAgain);
        Button btn_main = findViewById(R.id.mainMenu);

        // Startar ett vinstljud.
        MediaPlayer soundtrack= MediaPlayer.create(WinActivity.this,R.raw.win);
        soundtrack.start();

        // Lyssnare som skickar dig vidare till "spelplanen" om man trycker på knappen.
        btn_play.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(WinActivity.this, GameActivity.class);
                startActivity(intent);
            }

        });
        btn_main.setOnClickListener(new OnClickListener() {
            // Lyssnare som skickar dig vidare till "startmenyn" om man trycker på knappen.
            public void onClick(View v) {
                Intent intent = new Intent(WinActivity.this, MainActivity.class);
                startActivity(intent);
            }

        });

    }
}
