package com.example.mathkingdomadventure4;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class Level1 extends AppCompatActivity {
    private TextView questionNumber, score, question, timer;
    private RadioGroup questionRadio;
    private RadioButton rb1,rb2,rb3,rb4;
    private Button nextBtn;
    private List<QuestionModel> questionList;
    private QuestionModel currentQuestion;
    int totalQuestion;
    int qCounter = 0;
    ColorStateList dfRbColor;
    boolean answered;
    int correctScore;
    CountDownTimer countDownTimer;
    private MediaPlayer homeMusic;
    Switch switchBtn;
    ImageView soundOn, soundOff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level1);
        questionNumber = findViewById(R.id.questionNumber);
        score = findViewById(R.id.score);
        question = findViewById(R.id.question);

        questionRadio = findViewById(R.id.radioGroup);
        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        rb3 = findViewById(R.id.rb3);
        rb4 = findViewById(R.id.rb4);
        nextBtn = findViewById(R.id.btnNext);
        timer =findViewById(R.id.timer);
        switchBtn = findViewById(R.id.switchBtn);
        soundOn = findViewById(R.id.songOn);
        soundOff = findViewById(R.id.songOff);

        switchBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    if (homeMusic == null) {
                        homeMusic = MediaPlayer.create(Level1.this, R.raw.level1);
                    }

                    homeMusic.setLooping(true);
                    homeMusic.start();

                    homeMusic.start();
                    soundOn.setVisibility(View.VISIBLE);
                    soundOff.setVisibility(View.INVISIBLE);
                    Toast.makeText(Level1.this, "On", Toast.LENGTH_SHORT).show();

                }
                else {
                    homeMusic.pause();
                    soundOff.setVisibility(View.VISIBLE);
                    soundOn.setVisibility(View.INVISIBLE);
                    Toast.makeText(Level1.this, "Off", Toast.LENGTH_SHORT).show();
                }
            }
        });
        // Check if the switch is off, then turn on the sound
        if (!switchBtn.isChecked()) {
            switchBtn.setChecked(true);
        }

        dfRbColor = rb1.getTextColors();

        questionList = new ArrayList<>();

        addQuestion();

        totalQuestion = questionList.size();
        // Shuffle the questionList at the start
        Collections.shuffle(questionList);

        showNextQuestion();

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answered == false) {
                    if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked() || rb4.isChecked()) {
                        checkAnswer();
                        countDownTimer.cancel();
                    }
                    else {
                        Toast.makeText(Level1.this, "Select The Answer", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    showNextQuestion();
                }
            }
        });
    }

    private void addQuestion() {
        questionList.add(new QuestionModel("2 * 2", "2","4","6","8",2));
        questionList.add(new QuestionModel("4 / 2", "1","2","3","4",2));
        questionList.add(new QuestionModel("3 * 2", "6","7","8","9",1));
        questionList.add(new QuestionModel("5 + 6", "8","9","10","11",4));
        questionList.add(new QuestionModel("9 / 3", "1","2","3","4",3));
        questionList.add(new QuestionModel("2 + 2", "3","4","5","6",2));
        questionList.add(new QuestionModel("4 - 2", "1","2","3","4",2));
        questionList.add(new QuestionModel("1 + 6", "7","8","9","10",1));
        questionList.add(new QuestionModel("5 + 5", "8","9","10","11",3));
        questionList.add(new QuestionModel("3 - 2", "1","2","3","4",1));

    }

    private void showNextQuestion() {

        if (qCounter == totalQuestion) {
            finishQuiz();
            return;
        }

        questionRadio.clearCheck();
        rb1.setTextColor(dfRbColor);
        rb2.setTextColor(dfRbColor);
        rb3.setTextColor(dfRbColor);
        rb4.setTextColor(dfRbColor);

        if (qCounter < totalQuestion) {

            Timer();
            currentQuestion = questionList.get(qCounter);
            question.setText(currentQuestion.getQuestion());
            rb1.setText(currentQuestion.getAnswer1());
            rb2.setText(currentQuestion.getAnswer2());
            rb3.setText(currentQuestion.getAnswer3());
            rb4.setText(currentQuestion.getAnswer4());

            qCounter ++;

            nextBtn.setText("Submit");
            questionNumber.setText("Question" + qCounter + "/" + totalQuestion);
            answered = false;
        }
        else {
            finish();
        }
    }

    private void checkAnswer() {
        answered = true;

        RadioButton rbSelected = findViewById(questionRadio.getCheckedRadioButtonId());

        int answerNo = questionRadio.indexOfChild(rbSelected) + 1;

        if (answerNo == currentQuestion.getCorrectAnswerNumber()) {
            correctScore ++;
            score.setText("Score: " + correctScore);
        }

        rb1.setTextColor(Color.RED);
        rb2.setTextColor(Color.RED);
        rb3.setTextColor(Color.RED);
        rb4.setTextColor(Color.RED);

        switch (currentQuestion.getCorrectAnswerNumber()) {
            case 1:
                rb1.setTextColor(Color.GREEN);
                break;
            case 2:
                rb2.setTextColor(Color.GREEN);
                break;
            case 3:
                rb3.setTextColor(Color.GREEN);
                break;
            case 4:
                rb4.setTextColor(Color.GREEN);
                break;
        }

        if(qCounter < totalQuestion){
            nextBtn.setText("Next");
        }
        else {
            nextBtn.setText("Finish");
        }
    }
    private void Timer() {
        countDownTimer = new CountDownTimer(21000, 1000) {
            @Override
            public void onTick(long l) {
                timer.setText("00:" + l/1000);
            }

            @Override
            public void onFinish() {
                showNextQuestion();
            }
        }.start();
    }
    private void finishQuiz() {
        if (correctScore >= totalQuestion * 0.5){
            Intent intent = new Intent(Level1.this, Congrats.class);
            intent.putExtra("score", correctScore);
            intent.putExtra("totalQuestions", totalQuestion);
            intent.putExtra("source_level","Level1");
            startActivity(intent);
        }
        else {
            Intent intent = new Intent(Level1.this, Failed.class);
            intent.putExtra("score", correctScore);
            intent.putExtra("totalQuestions", totalQuestion);
            intent.putExtra("source_level","Level1");
            startActivity(intent);
        }}
    public void stopMusic() {
        if (homeMusic != null) {
            homeMusic.stop();
            homeMusic = null;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopMusic();
    }
}
