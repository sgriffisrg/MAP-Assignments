package com.example.cashregister;

import android.app.Application;

public class ObjectManageApp extends Application {
    ProductManager products = new ProductManager();
    Products currentProduct = new Products();
    int totalQuantity = 0;
    int index = 0;
    double basePrice;
}
