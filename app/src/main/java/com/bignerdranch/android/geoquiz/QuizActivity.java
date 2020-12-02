package com.bignerdranch.android.geoquiz;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class QuizActivity extends AppCompatActivity {
    private Button mTrueButton;
    private Button mFalsxeButton;
    private Button mNextButton;
    private Button mPrevButton;
    private Button mCheatButton;
    private TextView mQuestionTextView;
    private static final String TAG="QuizActivity";
    private static final String KEY_INDEX="index";
    private static final String KEY_ANSWER="answer";
    private double correctAnswer=0;
    private double answerLength=0;

    private Question[] mQuestionBank =new Question[]{     //问题数组
            new Question(R.string.question_australia,true,0),
            new Question(R.string.question_oceans,true,0),
            new Question(R.string.question_mideast,false,0),
            new Question(R.string.question_africa,false,0),
            new Question(R.string.question_america,true,0),
            new Question(R.string.question_asia,true,0)//填充数组，使用构造器
    };
    private int mCurrentIndex=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreat() called");
        setContentView(R.layout.activity_quiz);//实例化组件并将它们放在屏幕上

        if(savedInstanceState!=null){       //检查存储的Bundle信息
            mCurrentIndex=savedInstanceState.getInt(KEY_INDEX,0);
            int[] answerList=savedInstanceState.getIntArray(KEY_ANSWER);
            for (int i=0;i<mQuestionBank.length;i++){
                mQuestionBank[i].setisAnswer(answerList[i]);
            }
        }

        mQuestionTextView=(TextView)findViewById(R.id.question_text_view);//引用生成的视图对象，问题数组文字
        mQuestionTextView.setOnClickListener(new View.OnClickListener() { //设置监听器，使用匿名内部类;
            @Override
            public void onClick(View v) {
                mCurrentIndex=(mCurrentIndex+1)%mQuestionBank.length;
                updateQuestion();//以上两行使点击问题也可以更新为下一个问题
            }
        });

        mTrueButton=(Button)findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        mFalsxeButton=(Button)findViewById(R.id.false_button);
        mFalsxeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vi) {
                checkAnswer(false);
            }
        });

        updateQuestion();//起始更新，使页面打开之后有问题存在！
        mCheatButton=(Button)findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(QuizActivity.this,CheatActivity.class);
                startActivity(intent);
            }
        });

        mNextButton=(Button)findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex=(mCurrentIndex+1)%mQuestionBank.length;
                updateQuestion();//以上两行用于更新为下一个问题
                answerLength++;
                if (answerLength==mQuestionBank.length){//当answerLength长度和问题数量长度一致的时候进行输出
                    double i=correctAnswer/mQuestionBank.length;
                    double sorce=i*100;
                    Toast.makeText(QuizActivity.this,"评分"+sorce+"%",Toast.LENGTH_SHORT).show();
                }
            }
        });

        mPrevButton=(Button)findViewById(R.id.prev_button);
        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCurrentIndex==0)
                    mCurrentIndex=mQuestionBank.length-1;//此条件用于避免刚开始点击prev按钮而无法返回到最后一个问题
                else
                    mCurrentIndex=(mCurrentIndex-1)%mQuestionBank.length;
                updateQuestion();//更新数组
            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.d(TAG,"onStart()  called");
    }

    @Override
    protected  void onResume(){
        super.onResume();
        Log.d(TAG,"onResume() called");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.d(TAG,"onPause() called");
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState){   //覆盖onSaveInstanceState（Bundle）方法，将数据保存在bundle中，然后字啊onCreat（Bundle）中取回这些数据
        super.onSaveInstanceState(savedInstanceState);
        Log.d(TAG,"onSaveInstanceState() called");
        savedInstanceState.putInt(KEY_INDEX,mCurrentIndex);//设置键-值对
        int[] answerList=new int[mQuestionBank.length];
        for (int i=0;i<answerList.length;i++){
            answerList[i]=mQuestionBank[i].getisAnswer();
        }

        savedInstanceState.putIntArray(KEY_ANSWER,answerList);
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.d(TAG,"onStop() called");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d(TAG,"onDestroy() called");
    }

    private void ButtonEnabled(){
        if (mQuestionBank[mCurrentIndex].getisAnswer()!=0){
            mTrueButton.setEnabled(false);//setEnabled(false)为禁用掉按钮的id
            mFalsxeButton.setEnabled(false);
        }
        else {
            mTrueButton.setEnabled(true);//setEnabled(true)为恢复按钮的id
            mFalsxeButton.setEnabled(true);
        }
    }

    private void updateQuestion(){
        int question=mQuestionBank[mCurrentIndex].getTextResId();//获取第几个问题的具体内容
        mQuestionTextView.setText(question);//设置具体问题？？
        ButtonEnabled();
    }

    private void checkAnswer(boolean userPressedTrue){    //根据用户行为检查
        boolean answerIsTrue=mQuestionBank[mCurrentIndex].isAnswerTrue();
        int messageResId=0;

        if (userPressedTrue==answerIsTrue){
            mQuestionBank[mCurrentIndex].setisAnswer(1);
            messageResId=R.string.correct_toast;
            correctAnswer++;
        }
        else{
            mQuestionBank[mCurrentIndex].setisAnswer(-1);
            messageResId=R.string.incorrect_toast;
        }

        Toast.makeText(QuizActivity.this,messageResId,Toast.LENGTH_SHORT).show();//创建提示消息，mskeText（Context context，int resID<资源ID>，int duration、）

        ButtonEnabled();//根据isanswer的具体值更新按钮的状态
    }
}