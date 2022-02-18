package com.example.cashregister;

import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity_Restock extends AppCompatActivity implements View.OnClickListener {
    ArrayList<Products> listOfProducts;
    ListView productList;
    int index;
    Button restock;
    Button cancel;
    EditText amtToBeRestocked;
    TextView productBeingStocked;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_restock);
        productList = findViewById(R.id.restockProductList);
        listOfProducts = ((ObjectManageApp)getApplication()).products.products;
        ProductBaseAdapter adapter = new ProductBaseAdapter(listOfProducts, this);
        productList.setAdapter(adapter);

        restock = findViewById(R.id.Restock);
        cancel = findViewById(R.id.Cancel);
        amtToBeRestocked = findViewById(R.id.amtToRestock);
        productBeingStocked = findViewById(R.id.productBeingRestocked);
        cancel.setOnClickListener(this);
        restock.setOnClickListener(this);

        productList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                productBeingStocked.setText(listOfProducts.get(i).productName);
                index = i;
            }
        });
    }
    public boolean validate(){
        if(String.valueOf(amtToBeRestocked.getText()).equals(""))
            return false;

        return true;
    }

    public void makeToast(boolean type){
        Toast error;
        if(type)
            error = Toast.makeText(this, "Please ensure that all fields are filled out", Toast.LENGTH_LONG);
        else
            error = Toast.makeText(this, "Thank you for restocking " + productBeingStocked.getText(), Toast.LENGTH_SHORT);

        error.show();
    }

    @Override
    public void onClick(View view){
        int id = view.getId();

        if(id == R.id.Restock){
            if(validate()){
                ((ObjectManageApp)getApplication()).products.products.get(index).stockIncrease(parseInt(String.valueOf(amtToBeRestocked.getText())));
                makeToast(false);
                listOfProducts = ((ObjectManageApp)getApplication()).products.products;
                ProductBaseAdapter adapter = new ProductBaseAdapter(listOfProducts, this);
                productList.setAdapter(adapter);
                amtToBeRestocked.setText("");
                amtToBeRestocked.clearFocus();
            }
            else
                makeToast(true);
        }
        else{
            Intent backIntent = new Intent(this, MainActivity_Manager.class);
            startActivity(backIntent);
        }
    }
}