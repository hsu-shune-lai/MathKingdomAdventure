package com.example.mathkingdomadventure4;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.media.MediaPlayer;
import android.widget.Button;
import android.widget.TextView;

public class Failed extends AppCompatActivity {
    private Button chooseLevel;
    private Button restart;
    private Button exit;
    private TextView total;
    MediaPlayer failMusic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_failed);
        chooseLevel = findViewById(R.id.chooseLevelButton);
        restart = findViewById(R.id.restartButton);
        total = findViewById(R.id.total);
        exit = findViewById(R.id.exitButton);

        // Get the score from the intent
        int score = getIntent().getIntExtra("score", 0);
        int totalQuestions = getIntent().getIntExtra("totalQuestions", 0);
        total.setText(score + "/" + totalQuestions);
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sourceLevel = getIntent().getStringExtra("source_level");
                Intent replayIntent;
                switch (sourceLevel) {
                    case "Level0":
                        replayIntent = new Intent(Failed.this, Level0.class);
                        break;
                    case "Level1":
                        replayIntent = new Intent(Failed.this, Level1.class);
                        break;
                    case "Level2":
                        replayIntent = new Intent(Failed.this, Level2.class);
                        break;
                    default:
                        replayIntent = null;
                }

                if (replayIntent != null) {
                    startActivity(replayIntent);
                }

            }
        });

        if (failMusic == null) {
            failMusic = MediaPlayer.create(this, R.raw.fail);
        }
        failMusic.setLooping(true);
        failMusic.start();
        chooseLevel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Failed.this, ChooseLevel.class);
                        startActivity(intent);
                    }
                });
        exit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Failed.this, MainActivity.class);
                        startActivity(intent);
                    }
                });
            }
    @Override
    protected void onPause() {
        super.onPause();
        failMusic.pause();
    }}
