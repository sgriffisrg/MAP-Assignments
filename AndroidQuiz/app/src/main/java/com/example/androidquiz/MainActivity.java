package com.example.androidquiz;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, QuizDialogFragment.DialogClickListener {

    FragmentManager fm = getSupportFragmentManager();
    ArrayList<QuestionClass> questions = new ArrayList<>();
    FragmentContainerView frag;
    Button trueButton;
    Button falseButton;
    MainActivityManager manager;
    int index;
    AlertDialog.Builder builder;
    AlertDialog.Builder builder2;
    ProgressBar progressBar;
    String value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        value = savedInstanceState.getString("value");
        builder = new AlertDialog.Builder(this);
        builder2 = new AlertDialog.Builder(this);
        frag  = findViewById(R.id.quizQuestions);
        manager = ((ObjectManageApp)getApplication()).manager;
        index = manager.index;
        questions = manager.currentlyUsedQuestions;
        progressBar = findViewById(R.id.progressBar);
        progressBar.setMax(manager.currentTotalQuestions);
        progressBar.setProgress(index);
        QuizFragment qF = QuizFragment.newInstance(questions.get(index).question);
        fm.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fm.beginTransaction().replace(R.id.quizQuestions, qF).commit();
        frag.setBackgroundColor(questions.get(index).colour);
        trueButton = findViewById(R.id.tr);
        falseButton = findViewById(R.id.fl);
        trueButton.setOnClickListener(this);
        falseButton.setOnClickListener(this);
        builder = new AlertDialog.Builder(this);
    }

    public void makeToast(boolean answer){
        Toast tOrf;
        if(answer)
            tOrf = Toast.makeText(this, getString(R.string.correct),Toast.LENGTH_SHORT);
        else
            tOrf = Toast.makeText(this, getString(R.string.incorrect), Toast.LENGTH_SHORT);
        tOrf.show();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        boolean answer;
        answer = id == R.id.tr;

        if(questions.get(index).checkCorrect(answer)) {
            makeToast(true);
            manager.totalCorrect++;
        }
        else
            makeToast(false);

        index++;
        manager.index++;
        if(index == manager.currentTotalQuestions) {
            progressBar.setProgress(index);
            builder.setTitle(getString(R.string.FinishedAlertTitle))
                    .setMessage(getString(R.string.FinishedAlertMessage) + " " + manager.totalCorrect + "/" + manager.currentTotalQuestions)
                    .setCancelable(false)
                    .setNegativeButton(getString(R.string.Ignore), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(MainActivity.this, R.string.CancelChangeMessage, Toast.LENGTH_SHORT).show();
                            reset(false);
                        }
                    })
                    .setPositiveButton(getString(R.string.saveNewQuestion), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(MainActivity.this, R.string.Saved, Toast.LENGTH_SHORT).show();
                            reset(true);
                        }
                    }).show();
        }
        else {
            progressBar.setProgress(index);
            QuizFragment qF = QuizFragment.newInstance(questions.get(index).question);
            fm.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            fm.beginTransaction().replace(R.id.quizQuestions, qF).commit();
            frag.setBackgroundColor(questions.get(index).colour);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.android_quiz_menu, menu);
        return true;
    }

    public void reset(boolean check){
        manager.save(this, check);
        index = 0;
        questions = manager.currentlyUsedQuestions;
        QuizFragment qF = QuizFragment.newInstance(questions.get(index).question);
        fm.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fm.beginTransaction().replace(R.id.quizQuestions, qF).commit();
        frag.setBackgroundColor(questions.get(index).colour);
        progressBar.setMax(manager.currentTotalQuestions);
        progressBar.setProgress(index);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        String average;
        switch(item.getItemId()){
            case R.id.average:
                average = manager.storageManager.getScore(this);
                builder2.setTitle(getString(R.string.averageTitle));
                builder2.setMessage(getString(R.string.averageMessageP1)+ "\n" + getString(R.string.averageMessageP2) + average + getString(R.string.averageMessageP3));
                builder2.setNegativeButton("OK",null);
                builder2.show();
                break;
            case R.id.changeQuestionAmount:
                QuizDialogFragment quizDialog = QuizDialogFragment.newInstance();
                quizDialog.show(fm, QuizDialogFragment.Tag);
                quizDialog.listener = this;
                break;
            case R.id.deleteAll:
                builder.setTitle(getString(R.string.deleteTitle))
                        .setMessage(getString(R.string.deleteMessage))
                        .setNegativeButton(getString(R.string.no), null)
                        .setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                manager.storageManager.deleteContents(MainActivity.this);
                                Toast.makeText(MainActivity.this,getString(R.string.deleteFinished), Toast.LENGTH_SHORT).show();
                            }
                        }).show();

                break;
            default:
                break;
        }

        return true;
    }

    @Override
    public void dialogListenerSave(String newQuestionAmt) {
        if(Integer.parseInt(newQuestionAmt) > 8 || Integer.parseInt(newQuestionAmt) <= 0)
            Toast.makeText(this, getString(R.string.CancelChangeMessage), Toast.LENGTH_SHORT).show();
        else {
            manager.currentTotalQuestions = Integer.parseInt(newQuestionAmt);
            reset(false);
            Toast.makeText(this, getString(R.string.SavedChangeMessage) + newQuestionAmt + getString(R.string.SavedChangeMessage1), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void dialogListenerCancel() {

    }
}