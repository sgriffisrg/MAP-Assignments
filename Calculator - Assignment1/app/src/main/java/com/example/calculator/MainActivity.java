package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button advanced_btn;
    Button one_btn;
    Button two_btn;
    Button three_btn;
    Button four_btn;
    Button five_btn;
    Button six_btn;
    Button seven_btn;
    Button eight_btn;
    Button nine_btn;
    Button zero_btn;
    Button plus_btn;
    Button minus_btn;
    Button divide_btn;
    Button multiply_btn;
    Button POW_btn;
    Button MAX_btn;
    Button MIN_btn;
    Button clear_btn;
    Button equals_btn;
    Button remainder_btn;
    Button back_btn;
    Calculator calculate = new Calculator();
    int stdOrAdv = 0;
    TextView equation;
    LinearLayout advance;
    String equationStr = "";
    AlertDialog.Builder builder;
    boolean calculated = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        advance = (LinearLayout) findViewById(R.id.advanced);
        advance.setVisibility(View.GONE);
        builder = new AlertDialog.Builder(this);
        one_btn = findViewById(R.id.one);
        two_btn = findViewById(R.id.two);
        three_btn = findViewById(R.id.three);
        four_btn = findViewById(R.id.four);
        five_btn = findViewById(R.id.five);
        six_btn = findViewById(R.id.six);
        seven_btn = findViewById(R.id.seven);
        eight_btn = findViewById(R.id.eight);
        nine_btn = findViewById(R.id.nine);
        zero_btn = findViewById(R.id.zero);
        plus_btn = findViewById(R.id.plus);
        minus_btn = findViewById(R.id.minus);
        multiply_btn = findViewById(R.id.multiply);
        divide_btn = findViewById(R.id.divide);
        POW_btn = findViewById(R.id.POW);
        remainder_btn = findViewById(R.id.Remainder);
        MAX_btn = findViewById(R.id.maximum);
        MIN_btn = findViewById(R.id.minimum);
        clear_btn = findViewById(R.id.clear);
        equals_btn = findViewById(R.id.equals);
        back_btn = findViewById(R.id.back);
        equation = findViewById(R.id.equate);
        advanced_btn = findViewById(R.id.buttonAdvanced);

        advanced_btn.setOnClickListener(this);
        one_btn.setOnClickListener(this);
        two_btn.setOnClickListener(this);
        three_btn.setOnClickListener(this);
        four_btn.setOnClickListener(this);
        five_btn.setOnClickListener(this);
        six_btn.setOnClickListener(this);
        seven_btn.setOnClickListener(this);
        eight_btn.setOnClickListener(this);
        nine_btn.setOnClickListener(this);
        zero_btn.setOnClickListener(this);
        minus_btn.setOnClickListener(this);
        plus_btn.setOnClickListener(this);
        multiply_btn.setOnClickListener(this);
        divide_btn.setOnClickListener(this);
        equals_btn.setOnClickListener(this);
        clear_btn.setOnClickListener(this);
        MIN_btn.setOnClickListener(this);
        MAX_btn.setOnClickListener(this);
        POW_btn.setOnClickListener(this);
        remainder_btn.setOnClickListener(this);
        back_btn.setOnClickListener(this);
    }
    public void error(String title, String message){ //Function so I can create custom alerts
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setNegativeButton("OK",null);

        builder.show();
    }
    public void add(String next){//appends and adds the user inputs into the ArrayList in calculate and into the textview
        if(!calculated) {
            calculate.push(next);
            equationStr = equationStr + next + " ";
            equation.setText(equationStr);
        }
        else{
            equationStr = equationStr + "= " + next;
            equation.setText(equationStr);
        }
    }
    @Override
    public void onClick(View view){
        int id = view.getId();
        if(!calculated) {
            switch (id) {
                case R.id.buttonAdvanced://makes the advanced options appear and disappear
                    if (stdOrAdv == 0) {
                        advanced_btn.setText(getString(R.string.standard));
                        advance.setVisibility(View.VISIBLE);
                        stdOrAdv = 1;
                    } else {
                        advanced_btn.setText(getString(R.string.advanced));
                        advance.setVisibility(View.GONE);
                        stdOrAdv = 0;
                    }
                    break;

                case R.id.back:
                    if (calculate.removeOne()) {
                        equationStr = calculate.getEquation();
                        equation.setText(equationStr);
                    }
                    break;
                case R.id.clear:
                    calculate.clear();
                    equationStr = "";
                    equation.setText(equationStr);
                    break;
                case R.id.equals:
                    if(calculate.getSize() < 3){//makes sure that the user enters the minimum amount of inputs for a simple equation
                        error("Not Enough", "Please enter a full equation before pressing equals");
                    }
                    else {
                        calculated = true;
                        String result = calculate.calculate();
                        if(result == "ERROR"){
                            error(result, "Please follow the proper format of single digit operator single digit. E.g. 2 POW 4 + 7. Please clear to start over.");
                            add(result);
                        }
                        else{
                            add(result);
                        }
                    }
                    break;
                case R.id.zero:
                    add((String)zero_btn.getText());
                    break;
                case R.id.one:
                    add((String)one_btn.getText());
                    break;
                case R.id.two:
                    add((String)two_btn.getText());
                    break;
                case R.id.three:
                    add((String)three_btn.getText());
                    break;
                case R.id.four:
                    add((String)four_btn.getText());
                    break;
                case R.id.five:
                    add((String)five_btn.getText());
                    break;
                case R.id.six:
                    add((String)six_btn.getText());
                    break;
                case R.id.seven:
                    add((String)seven_btn.getText());
                    break;
                case R.id.eight:
                    add((String)eight_btn.getText());
                    break;
                case R.id.nine:
                    add((String)nine_btn.getText());
                    break;
                case R.id.plus:
                    add((String)plus_btn.getText());
                    break;
                case R.id.minus:
                    add((String)minus_btn.getText());
                    break;
                case R.id.multiply:
                    add((String)multiply_btn.getText());
                    break;
                case R.id.divide:
                    add((String)divide_btn.getText());
                    break;
                case R.id.Remainder:
                    add((String)remainder_btn.getText());
                    break;
                case R.id.POW:
                    add((String)POW_btn.getText());
                    break;
                case R.id.maximum:
                    add((String)MAX_btn.getText());
                    break;
                case R.id.minimum:
                    add((String)MIN_btn.getText());
                    break;
            }
        }
        else{ //The way I set up the app, is that once you hit equals it only allow the user to hit clear so they can start a new equation, every other button results into an alert
            if(id == R.id.clear){
                calculate.clear();
                equationStr = "";
                equation.setText(equationStr);
                calculated = false;
            }
            else
                error("Already Calculated", "Please clear the current equation before starting a new");
        }
    }
}