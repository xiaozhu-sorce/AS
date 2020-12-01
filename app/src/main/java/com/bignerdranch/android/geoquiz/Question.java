package com.bignerdranch.android.geoquiz;

public class Question
{
    private int mTextResId;
    private boolean mAnswerTrue;
    private int misAnswer;

    public int getTextResId() {
        return mTextResId;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }

    public int getisAnswer(){
        return misAnswer;
    }

    public void setisAnswer(int isanswer){
        misAnswer=isanswer;
    }

    public Question(int textResId, boolean answerTrue,int isanswer)
    {
        mTextResId=textResId;
        mAnswerTrue=answerTrue;
        misAnswer=isanswer;
    }
}
