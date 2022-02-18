package com.example.cashregister;

public class Products {
    double price;
    int quantity;
    boolean soldOut;
    String productName;

    public Products(){
        price = 0;
        quantity = 0;
        productName = "";
        soldOut = true;
    }
    public Products(double amt, int stock, boolean inStock, String name){
        this.productName = name;
        this.price = amt;
        this.quantity = stock;
        this.soldOut = inStock;
    }

    public void stockDecrease(int amtBought){
            this.quantity = this.quantity - amtBought;
            if(quantity == 0)
                soldOut = true;
    }
    public void stockIncrease(int amtRestocked){
        this.quantity = this.quantity + amtRestocked;
        this.soldOut = false;
    }
}
