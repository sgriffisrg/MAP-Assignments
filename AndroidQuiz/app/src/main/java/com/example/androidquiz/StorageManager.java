package com.example.androidquiz;

import android.app.Activity;
import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class StorageManager {
    static String fileName = "score.txt";
    FileOutputStream fos;
    FileInputStream fis;

    public void updateScoreToFile(Activity context, int correct, int amountOfQ){
        StringBuffer stringBuffer = new StringBuffer();
        ArrayList<String> numbers;
        int totalCorrect, totalQuestions, read;
        char temp, temp2;
        try{
            fis = context.openFileInput(fileName);
            InputStreamReader inputStreamReader = new InputStreamReader(fis, StandardCharsets.UTF_8);

            if((read = inputStreamReader.read()) == -1){
                totalCorrect = 0;
                totalQuestions = 0;
            }
            else {
                //takes the entire line in the file and sends it to another function to split up the score/questions
                stringBuffer.append((char)read);
                while((read = inputStreamReader.read()) != -1)
                    stringBuffer.append((char)read);
                numbers = getInts(stringBuffer.toString());
                totalCorrect = Integer.parseInt(numbers.get(0));
                totalQuestions = Integer.parseInt(numbers.get(1));
                fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
                fos.write("".getBytes());
                fos.close();

            }
            fos = context.openFileOutput(fileName, Context.MODE_APPEND);

            totalCorrect += correct;
            totalQuestions += amountOfQ;

            fos.write((totalCorrect + "/" + totalQuestions).getBytes());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //uses a / as a delimiter to split apart the string.
    public ArrayList<String> getInts(String stringInt){
        int index = 0;
        boolean passed = false;
        ArrayList<String> temp = new ArrayList<>();
        for(int i = 0; i < stringInt.toCharArray().length; i++){
            if(stringInt.toCharArray()[i] == '/') {
                temp.add(stringInt.substring(index, i));
                index = i + 1;
                passed = true;
            }
            if(passed && i == stringInt.toCharArray().length - 1)
                temp.add(stringInt.substring(index, i+1));
        }
        return temp;
    }

    public void deleteContents(Activity context){
        try{
            fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            fos.write("".getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getScore(Activity context){
        StringBuffer stringBuffer = new StringBuffer();
        try{
            fos = context.openFileOutput(fileName, Context.MODE_APPEND);
            fis = context.openFileInput(fileName);
            InputStreamReader inputStreamReader = new InputStreamReader(fis, StandardCharsets.UTF_8);
            int read;
            if((read = inputStreamReader.read()) == -1)
                stringBuffer.append(" 0/0");
            else {
                stringBuffer.append(" ");
                stringBuffer.append((char) read);
                while ((read = inputStreamReader.read()) != -1)
                    stringBuffer.append((char) read);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            try {
                fos.close();
                fis.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return stringBuffer.toString();
    }
}
