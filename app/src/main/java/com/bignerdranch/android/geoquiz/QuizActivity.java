package com.bignerdranch.android.geoquiz;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import static android.widget.Toast.LENGTH_LONG;

public class QuizActivity extends AppCompatActivity {
    private Button mTrueButton;
    private Button mFalsxeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mTrueButton=(Button)findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(QuizActivity.this,R.string.correct_toast, LENGTH_LONG);
                toast.setGravity(Gravity.TOP, 0, 0);
                toast.show();
            }
        });
        mFalsxeButton=(Button)findViewById(R.id.false_button);
        mFalsxeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vi) {
                Toast toast = Toast.makeText(QuizActivity.this,R.string.incorrect_toast, LENGTH_LONG);
                toast.setGravity(Gravity.TOP , 0, 0);
                toast.show();
            }
        });
    }
}
