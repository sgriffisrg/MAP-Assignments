package com.example.androidquiz;

import android.app.Activity;

import java.util.ArrayList;
import java.util.Random;

public class MainActivityManager {
    ArrayList<QuestionClass> questions = new ArrayList<QuestionClass>();
    StorageManager storageManager;
    private static final int TOTALQUESTION = 8;
    int currentTotalQuestions;
    ArrayList<QuestionClass> currentlyUsedQuestions;
    int index = 0;
    int totalCorrect = 0;

    public MainActivityManager(){
        questions.add(new QuestionClass(true ,R.string.q1, R.color.Aqua));
        questions.add(new QuestionClass(false ,R.string.q2, R.color.Aquamarine));
        questions.add(new QuestionClass(true ,R.string.q3, R.color.CadetBlue));
        questions.add(new QuestionClass(false ,R.string.q4, R.color.ForestGreen));
        questions.add(new QuestionClass(true ,R.string.q5, R.color.Azure));
        questions.add(new QuestionClass(false ,R.string.q6, R.color.Cyan));
        questions.add(new QuestionClass(true ,R.string.q7, R.color.YellowGreen));
        questions.add(new QuestionClass(false ,R.string.q8, R.color.Purple));
        questions.add(new QuestionClass(true ,R.string.q9, R.color.Gray));
        questions.add(new QuestionClass(false ,R.string.q10, R.color.LawnGreen));
        questions.add(new QuestionClass(true ,R.string.q11, R.color.Violet));
        questions.add(new QuestionClass(false ,R.string.q12, R.color.Lavender));
        questions.add(new QuestionClass(true ,R.string.q13, R.color.LemonChiffon));
        questions.add(new QuestionClass(false ,R.string.q14, R.color.Khaki));
        questions.add(new QuestionClass(true ,R.string.q15, R.color.teal_200));
        questions.add(new QuestionClass(false ,R.string.q16, R.color.LightPink));
        questions.add(new QuestionClass(true ,R.string.q17, R.color.Pink));
        questions.add(new QuestionClass(false ,R.string.q18, R.color.LightBlue));
        questions.add(new QuestionClass(true ,R.string.q19, R.color.SkyBlue));
        questions.add(new QuestionClass(false ,R.string.q20, R.color.Wheat));
        currentTotalQuestions = TOTALQUESTION;
        storageManager = new StorageManager();
        currentlyUsedQuestions = getRandom();
    }

    //gets a random assortment of questions from the 20 questions in the arraylist. The amount is determined by the currentTotalQuestions variable
    public ArrayList<QuestionClass> getRandom(){
        Random rand = new Random();
        ArrayList<QuestionClass> temp = new ArrayList<>(questions);
        ArrayList<QuestionClass> newList = new ArrayList<>();
        for (int i = 0; i < currentTotalQuestions; i++) {
            int randomIndex = rand.nextInt(temp.size());
            newList.add(temp.get(randomIndex));
            temp.remove(randomIndex);
        }
        return newList;
    }

    //This is to completely reset the questions and variables, as well as saving the score if the sent boolean is true
    public void save(Activity context, boolean saved){
        if(saved)
            storageManager.updateScoreToFile(context, totalCorrect, currentTotalQuestions);
        currentlyUsedQuestions = getRandom();
        index = 0;
        totalCorrect = 0;
    }
}
