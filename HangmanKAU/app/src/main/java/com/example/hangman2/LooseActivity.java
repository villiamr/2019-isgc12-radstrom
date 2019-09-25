package com.example.hangman2;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class LooseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loose);
        Button btn_play = findViewById(R.id.playAgain);
        Button btn_main = findViewById(R.id.mainMenu);

        // Startar ett förlustljud.
        MediaPlayer soundtrack= MediaPlayer.create(LooseActivity.this,R.raw.loose);
        soundtrack.start();

        // Lyssnare som skickar dig vidare till "spelplanen" om man trycker på knappen.
        btn_play.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(LooseActivity.this, GameActivity.class);
                startActivity(intent);
            }
        });
        // Lyssnare som skickar dig vidare till "startmenyn" om man trycker på knappen.
        btn_main.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(LooseActivity.this, MainActivity.class);
                startActivity(intent);
            }

        });

    }
}
