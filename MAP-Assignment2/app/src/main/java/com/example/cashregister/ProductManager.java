package com.example.cashregister;
import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class ProductManager {
    ArrayList<Products> products = new ArrayList<>(0);
    ArrayList<Receipt> history = new ArrayList<>(0);
    public ProductManager(){
        Products pants = new Products(30, 15, false, "Pants");
        Products shirts = new Products(25, 20, false, "Shirts");
        Products hats = new Products(15, 25, false, "Hats");
        products.add(pants);
        products.add(shirts);
        products.add(hats);
    }


    public boolean purchaseValidate(int amtBought, String productName){
        boolean valid = false;
        Products check;
        switch(productName){
            case "Pants":
               check = products.get(0);
               if(check.quantity >= amtBought && amtBought > 0) valid = true;
                break;
            case "Shirts":
                check = products.get(1);
                if(check.quantity >= amtBought && amtBought > 0) valid = true;
                break;
            case "Hats":
                check = products.get(2);
                if(check.quantity >= amtBought && amtBought > 0) valid = true;
                break;
        }
        return valid;
    }

    public void Receipts(int amtBought, String productName, double total, int i){
        history.add(new Receipt(amtBought, productName, total));
        products.get(i).stockDecrease(amtBought);
    }
}
