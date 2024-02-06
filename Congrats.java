package com.example.mathkingdomadventure4;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.media.MediaPlayer;
public class Congrats extends AppCompatActivity {
    private Button chooseLevel;
    private Button replay;
    private Button exit;
    private TextView total;
    MediaPlayer passMusic;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congrats);
        chooseLevel = findViewById(R.id.chooseLevelButton);
        replay = findViewById(R.id.replayButton);
        total = findViewById(R.id.total);
        exit = findViewById(R.id.exitButton);
        // Get the score from the intent
        int score = getIntent().getIntExtra("score", 0);
        int totalQuestions = getIntent().getIntExtra("totalQuestions", 0);
        total.setText(score + "/" + totalQuestions);
        replay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sourceLevel = getIntent().getStringExtra("source_level");
                Intent replayIntent;
                switch (sourceLevel) {
                    case "Level0":
                        replayIntent = new Intent(Congrats.this, Level0.class);
                        break;
                    case "Level1":
                        replayIntent = new Intent(Congrats.this, Level1.class);
                        break;
                    case "Level2":
                        replayIntent = new Intent(Congrats.this, Level2.class);
                        break;

                    default:
                        replayIntent = null;
                }
                if (replayIntent != null) {
                    startActivity(replayIntent);
                }

            }
        });
        if (passMusic == null) {
            passMusic = MediaPlayer.create(Congrats.this, R.raw.pass);
        }
        passMusic.setLooping(true);
        passMusic.start();
        chooseLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Congrats.this, ChooseLevel.class);
                startActivity(intent);
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Congrats.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
    @Override
    protected void onPause() {
        super.onPause();
        passMusic.pause();
    }}
