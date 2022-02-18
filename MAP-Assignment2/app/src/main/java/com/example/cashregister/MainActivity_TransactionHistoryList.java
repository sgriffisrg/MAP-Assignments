package com.example.cashregister;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity_TransactionHistoryList extends AppCompatActivity {
    RecyclerView receiptList;
    ReceiptRecyclerAdapter recyclerAdapter;
    ProductManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_transaction_history_list);
        manager = ((ObjectManageApp)getApplication()).products;
        receiptList = findViewById(R.id.ReceiptList);
        recyclerAdapter = new ReceiptRecyclerAdapter(manager.history, this);
        receiptList.setAdapter(recyclerAdapter);
        receiptList.setLayoutManager(new LinearLayoutManager(this));
    }
}