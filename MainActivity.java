package com.example.mathkingdomadventure4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {
    Button btnStart;
    private MediaPlayer homeMusic;
    Switch switchBtn;
    ImageView soundOn, soundOff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnStart = findViewById(R.id.btnStart);
        switchBtn = findViewById(R.id.switchBtn);
        soundOn = findViewById(R.id.songOn);
        soundOff = findViewById(R.id.songOff);
        switchBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    if (homeMusic == null) {
                        homeMusic = MediaPlayer.create(MainActivity.this, R.raw.home);
                    }
                    homeMusic.setLooping(true);
                    homeMusic.start();

                    homeMusic.start();
                    soundOn.setVisibility(View.VISIBLE);
                    soundOff.setVisibility(View.INVISIBLE);
                    Toast.makeText(MainActivity.this, "On", Toast.LENGTH_SHORT).show();

                }
                else {
                    homeMusic.pause();
                    soundOff.setVisibility(View.VISIBLE);
                    soundOn.setVisibility(View.INVISIBLE);
                    Toast.makeText(MainActivity.this, "Off", Toast.LENGTH_SHORT).show();
                }
            }
        });
        // Check if the switch is off, then turn on the sound
        if (!switchBtn.isChecked()) {
            switchBtn.setChecked(true);
        }
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (MainActivity.this, ChooseLevel.class);

                startActivity(intent);
            }
        });
    }
    @Override
    protected void onPause() {
        super.onPause();
        homeMusic.pause();
    }
}


