package com.example.cashregister;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.Date;

public class Receipt implements Parcelable {
    String purchaseDate;
    int amtPurchased;
    String productName;
    double total;

    public Receipt(int amtBought, String name, double price){
        purchaseDate = (new Date().toString());
        //Log.d("TAG", "Receipt: " + purchaseDate);
        amtPurchased = amtBought;
        productName = name;
        total = price;
    }

    protected Receipt(Parcel in) {
        amtPurchased = in.readInt();
        productName = in.readString();
        total = in.readDouble();
        purchaseDate = in.readString();
    }

    public static final Creator<Receipt> CREATOR = new Creator<Receipt>() {
        @Override
        public Receipt createFromParcel(Parcel in) {
            return new Receipt(in);
        }

        @Override
        public Receipt[] newArray(int size) {
            return new Receipt[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(amtPurchased);
        parcel.writeString(productName);
        parcel.writeString(purchaseDate);
        parcel.writeDouble(total);
    }
}
