package com.example.calculator;

import android.util.Log;

import java.util.ArrayList;
import java.lang.Math;

public class Calculator {
    private String totalOrError = "";
    private ArrayList equation = new ArrayList();

    public boolean removeOne(){
        if(equation.size() != 0) {
            equation.remove(equation.size()-1);
            return true;
        }
        return false;
    }

    public int getSize(){
        return equation.size();
    }

    public String getEquation(){
        String equationStr = "";
        for(int i = 0; i < equation.size(); i++){
            equationStr = equationStr + equation.get(i) + " ";
        }
        return equationStr;
    }

    public void clear(){
        equation.clear();
    }

    public void push(String numOrOp){
        equation.add(numOrOp);
        System.out.println(equation.size());
    }

    public String calculate(){
        return totalOrError;
    }
}
