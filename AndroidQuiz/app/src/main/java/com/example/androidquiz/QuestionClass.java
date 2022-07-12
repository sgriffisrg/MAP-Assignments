package com.example.androidquiz;

public class QuestionClass {
    boolean trueOFalse;
    int question;
    int colour;

    public QuestionClass(boolean tOrF, int q, int clr){
        trueOFalse = tOrF;
        question = q;
        colour = clr;
    }

    public boolean checkCorrect(boolean tf){
        if(tf == trueOFalse)
            return true;
        else
            return false;
    }
}
