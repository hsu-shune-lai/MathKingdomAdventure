package com.example.mathkingdomadventure4;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.media.MediaPlayer;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;
public class ChooseLevel extends AppCompatActivity {
    Button btn0, btn1, btn2,home;
    MediaPlayer homeMusic;
    Switch switchBtn;
    ImageView soundOn, soundOff;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_level);
        btn0 = findViewById(R.id.btnStart1);
        btn1 = findViewById(R.id.btnStart2);
        btn2 = findViewById(R.id.btnStart3);
        home = findViewById(R.id.homeButton);
        switchBtn = findViewById(R.id.switchBtn);
        soundOn = findViewById(R.id.songOn);
        soundOff = findViewById(R.id.songOff);
        switchBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    if (homeMusic == null) {
                        homeMusic = MediaPlayer.create(ChooseLevel.this, R.raw.home);
                    }

                    homeMusic.setLooping(true);
                    homeMusic.start();

                    homeMusic.start();
                    soundOn.setVisibility(View.VISIBLE);
                    soundOff.setVisibility(View.INVISIBLE);
                    Toast.makeText(ChooseLevel.this, "On", Toast.LENGTH_SHORT).show();

                } else {
                    homeMusic.pause();
                    soundOff.setVisibility(View.VISIBLE);
                    soundOn.setVisibility(View.INVISIBLE);
                    Toast.makeText(ChooseLevel.this, "Off", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Check if the switch is off, then turn on the sound
        if (!switchBtn.isChecked()) {
            switchBtn.setChecked(true);
        }
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseLevel.this, Level0.class);
                startActivity(intent);
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ChooseLevel.this, Level1.class);
                startActivity(i);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ChooseLevel.this, Level2.class);
                startActivity(i);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ChooseLevel.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
    @Override
    protected void onPause() {
        super.onPause();
        homeMusic.pause();
    }}

