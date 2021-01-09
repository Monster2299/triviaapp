package com.mehedi.triviaapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.mehedi.triviaapp.data.AnswerListAsyncResponse;
import com.mehedi.triviaapp.data.QuestionBank;
import com.mehedi.triviaapp.model.Questions;

import java.util.ArrayList;
import java.util.List;

public class trivia extends AppCompatActivity implements View.OnClickListener {

    private TextView txtques;
    private ImageButton imgprev;
    private ImageButton imgnext;
    private Button btnright;
    private  Button btnwrong;
    private CardView card;
    private TextView count;
    private int currentQuestionIndex = 0;
    private List<Questions> questionsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia);

        txtques = findViewById(R.id.ques);
        imgprev = findViewById(R.id.imgprev);
        imgnext = findViewById(R.id.imgnext);
        btnright = findViewById(R.id.btnright);
        btnwrong = findViewById(R.id.btnwrong);
        count = findViewById(R.id.count);
        card = findViewById(R.id.cardview);

        imgnext.setOnClickListener(this);
        imgprev.setOnClickListener(this);
        btnright.setOnClickListener(this);
        btnwrong.setOnClickListener(this);

        questionsList = new QuestionBank().getQuestions(new AnswerListAsyncResponse() {
            @Override
            public void processFinished(ArrayList<Questions> questionsArrayList) {

                txtques.setText(questionsArrayList.get(currentQuestionIndex).getAnswer());
               // Log.d("test", "processFinished: "+questionsArrayList);

            }
        });

        //Log.d("main", "onCreate: "+questionsList);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imgprev :
                if(currentQuestionIndex !=0){
                    currentQuestionIndex = (currentQuestionIndex - 1) % questionsList.size();
                    updateQuestion();
                }
                break;
            case R.id.imgnext :
               currentQuestionIndex = (currentQuestionIndex + 1) % questionsList.size();
               updateQuestion();
                break;
            case R.id.btnright :
                questionTrue(true);
                updateQuestion();
                break;
            case R.id.btnwrong :
                questionTrue(false);
                updateQuestion();
                break;
        }
    }

    private void questionTrue(boolean userChoice) {
        boolean correctAns = questionsList.get(currentQuestionIndex).isAnswerStatus();
        int toastmsg;
        if (userChoice == correctAns){
            fadeAnimation();
            toastmsg = R.string.correct;
        }
        else {
            toastmsg=R.string.Wrong;
            shakeAnimation();
        }
        Toast.makeText(getApplicationContext(),toastmsg,Toast.LENGTH_SHORT).show();
    }

    private void updateQuestion() {
            String question = questionsList.get(currentQuestionIndex).getAnswer();
            txtques.setText(question);
            count.setText(currentQuestionIndex+1+"/"+questionsList.size());
    }
    private void fadeAnimation(){
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f,0.0f);

        alphaAnimation.setDuration(350);
        alphaAnimation.setRepeatCount(1);
        alphaAnimation.setRepeatMode(Animation.REVERSE);

        card.setAnimation(alphaAnimation);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                card.setCardBackgroundColor(Color.GREEN);
                txtques.setTextColor(Color.YELLOW);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                card.setCardBackgroundColor(Color.YELLOW);
                txtques.setTextColor(Color.BLACK);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

    private void shakeAnimation(){
        Animation shake = AnimationUtils.loadAnimation(trivia.this,R.anim.shake_animation);
        card.setAnimation(shake);
        shake.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                card.setCardBackgroundColor(Color.RED);
                txtques.setTextColor(Color.YELLOW);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                card.setCardBackgroundColor(Color.YELLOW);
                txtques.setTextColor(Color.BLACK);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}