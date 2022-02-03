package com.example.calculator;

import android.util.Log;

import java.util.ArrayList;
import java.lang.Math;

public class Calculator {
    private String totalOrError = "ERROR";
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
        Double total = 0.0;
        String firstDouble = "";
        String operator = "";
        String nextDouble = "";
        for(int i = 0; i < equation.size(); i++){
            System.out.println(operator + " " + firstDouble + " " + nextDouble);
            if(firstDouble == "" || operator == "" || nextDouble == "") { //extracts the user inputs from the arrayList
                if (i > 2) {
                    if(operator == "")
                        operator = (String)equation.get(i);
                    else
                        nextDouble = (String)equation.get(i);
                } else {
                    if(firstDouble == "")
                        firstDouble = (String)equation.get(i);
                    else if(operator == "")
                        operator = (String)equation.get(i);
                    else
                        nextDouble = (String)equation.get(i);
                }
            }
            //checks if a full equation is possible
            if(!(firstDouble == "" || operator == "" || nextDouble == "")){
                try{//if any if the parseDoubles throw an exception is will catch it and return an ERROR
                    switch(operator){
                        case "+":
                            total = Double.parseDouble(firstDouble) + Double.parseDouble(nextDouble);
                            firstDouble = total.toString();
                            nextDouble = "";
                            operator = "";
                            break;
                        case "-":
                            total = Double.parseDouble(firstDouble) - Double.parseDouble(nextDouble);
                            firstDouble = total.toString();
                            nextDouble = "";
                            operator = "";
                            break;
                        case "*":
                            total = Double.parseDouble(firstDouble) * Double.parseDouble(nextDouble);
                            firstDouble = total.toString();
                            nextDouble = "";
                            operator = "";
                            break;
                        case "/":
                            total = Double.parseDouble(firstDouble) / Double.parseDouble(nextDouble);
                            firstDouble = total.toString();
                            nextDouble = "";
                            operator = "";
                            break;
                        case "%":
                            total = Double.parseDouble(firstDouble) % Double.parseDouble(nextDouble);
                            firstDouble = total.toString();
                            nextDouble = "";
                            operator = "";
                            break;
                        case "MIN":
                            total = Math.min(Double.parseDouble(firstDouble), Double.parseDouble(nextDouble));
                            firstDouble = total.toString();
                            nextDouble = "";
                            operator = "";
                            break;
                        case "MAX":
                            total = Math.max(Double.parseDouble(firstDouble), Double.parseDouble(nextDouble));
                            firstDouble = total.toString();
                            nextDouble = "";
                            operator = "";
                            break;
                        case "POW":
                            total = Math.pow(Double.parseDouble(firstDouble), Double.parseDouble(nextDouble));
                            firstDouble = total.toString();
                            nextDouble = "";
                            operator = "";
                            break;

                        default://If there happens to be a number where an operator should be it will return an error
                            totalOrError = "ERROR";
                            return totalOrError;
                            //break;
                    }
                }catch(Exception e){
                    totalOrError = "ERROR";
                    return totalOrError;
                }
            }
        }
        totalOrError = firstDouble;
        return totalOrError;
    }
}
