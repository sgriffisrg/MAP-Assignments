package com.example.cashregister;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;


import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ListView productList;
    NumberPicker numberPick;
    Button managerButton;
    Button buyButton;
    ArrayList<Products> listOfProducts;
    TextView productName;
    TextView totalPrice;
    TextView totalStockPurchased;
    ProductManager manager;
    ObjectManageApp app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        app = ((ObjectManageApp)getApplication());
        productList = findViewById(R.id.productList);
        listOfProducts = app.products.products;
        manager = app.products;
        ProductBaseAdapter adapter = new ProductBaseAdapter(listOfProducts, this);
        productList.setAdapter(adapter);
        productName = findViewById(R.id.name);
        totalPrice = findViewById(R.id.totalPrice);
        managerButton = findViewById(R.id.managerButton);
        numberPick = findViewById(R.id.numberPicker);
        productList.setFastScrollEnabled(true);
        buyButton = findViewById(R.id.buy);
        numberPick.setMinValue(0);
        totalStockPurchased = findViewById(R.id.totalStockPurchased);
        productName.setText(app.currentProduct.productName);
        totalPrice.setText(String.valueOf(app.currentProduct.price));
        totalStockPurchased.setText(String.valueOf(app.currentProduct.quantity));
        numberPick.setMaxValue(app.totalQuantity);
        numberPick.setValue(app.currentProduct.quantity);
        productList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                app.currentProduct.productName = listOfProducts.get(i).productName;
                app.totalQuantity = listOfProducts.get(i).quantity;
                numberPick.setMaxValue(app.totalQuantity);
                productName.setText(app.currentProduct.productName);
                app.basePrice = listOfProducts.get(i).price;
                totalPrice.setText(String.valueOf(app.basePrice * numberPick.getValue()));
                app.index = i;
                app.currentProduct.quantity = numberPick.getValue();
                app.currentProduct.price = app.basePrice * app.currentProduct.quantity;
                totalStockPurchased.setText(String.valueOf(app.currentProduct.quantity));
                totalPrice.setText(String.valueOf(app.currentProduct.price));
                if(listOfProducts.get(app.index).soldOut)totalStockPurchased.setText(getString(R.string.soldOut));
            }
        });
        numberPick.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                app.currentProduct.quantity = i1;
                app.currentProduct.price = app.basePrice * i1;
                totalStockPurchased.setText(String.valueOf(app.currentProduct.quantity));
                totalPrice.setText(String.valueOf(app.currentProduct.price));
            }
        });

        managerButton.setOnClickListener(this);
        buyButton.setOnClickListener(this);
    }

    public void makeToast(boolean type){
        Toast error;
        if(type)
             error = Toast.makeText(this, "Please ensure that all fields are filled out, and that you're not buying more than we have in stock", Toast.LENGTH_LONG);
        else
            error = Toast.makeText(this, "Thank you for your purchase of " + totalStockPurchased.getText() + " " + productName.getText(), Toast.LENGTH_SHORT);

        error.show();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if(id == R.id.buy){
            if(manager.purchaseValidate(numberPick.getValue(), String.valueOf(productName.getText()))){
                makeToast(false);
                app.products.Receipts(app.currentProduct.quantity, app.currentProduct.productName, app.currentProduct.price, app.index);
                listOfProducts = app.products.products;
                manager = app.products;
                ProductBaseAdapter adapter = new ProductBaseAdapter(listOfProducts, this);
                productList.setAdapter(adapter);
                numberPick.setMaxValue(app.totalQuantity - app.currentProduct.quantity);
                if(listOfProducts.get(app.index).soldOut)totalStockPurchased.setText(getString(R.string.soldOut));
                app.currentProduct = new Products();
            }
            else
                makeToast(true);
        }
        else{
            Intent managerIntent = new Intent(this, MainActivity_Manager.class);
            startActivity(managerIntent);
        }
    }
}