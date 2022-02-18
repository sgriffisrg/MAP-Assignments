package com.example.cashregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity_Manager extends AppCompatActivity implements View.OnClickListener {
    Button restock;
    Button transHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_manager);
        restock = findViewById(R.id.restockBtn);
        transHistory = findViewById(R.id.historyBtn);

        restock.setOnClickListener(this);
        transHistory.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        Intent intent;
        if(id == R.id.restockBtn){
            intent = new Intent(this, MainActivity_Restock.class);
            startActivity(intent);
        }
        else{
            intent = new Intent(this, MainActivity_TransactionHistoryList.class);
            startActivity(intent);
        }
    }
}