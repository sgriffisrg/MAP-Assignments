package com.example.cashregister;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ProductBaseAdapter extends BaseAdapter {
    ArrayList<Products> productList;
    Context context;

    public ProductBaseAdapter(ArrayList<Products> list, Context context){
        this.context = context;
        productList = list;
    }
    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Object getItem(int i) {
        return productList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.product_row_layout,null);

        TextView priceText = view.findViewById(R.id.Price);
        TextView nameText = view.findViewById(R.id.productName);
        TextView stockText = view.findViewById(R.id.amtInStock);

        priceText.setText(String.valueOf(productList.get(i).price));
        nameText.setText(String.valueOf(productList.get(i).productName));
        stockText.setText(String.valueOf(productList.get(i).quantity));
        return view;
    }
}
