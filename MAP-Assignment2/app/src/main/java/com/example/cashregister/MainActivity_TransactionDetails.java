package com.example.cashregister;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity_TransactionDetails extends AppCompatActivity {
    TextView name;
    TextView total;
    TextView quantity;
    TextView date;
    Receipt current;
    int i;
    ProductManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_transaction_details);
        name = findViewById(R.id.transProductName);
        total = findViewById(R.id.totalSpent);
        quantity = findViewById(R.id.quantityPurchased);
        date = findViewById(R.id.datePurchased);
        manager = ((ObjectManageApp)getApplication()).products;
        i = getIntent().getExtras().getInt("Index");
        current = manager.history.get(i);
        name.setText("Product Name: " + current.productName);
        total.setText("Total Price: " + current.total);
        quantity.setText("Total Purchased: " + current.amtPurchased);
        date.setText("Date Purchased: " + current.purchaseDate);

    }
}