package com.example.cashregister;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ReceiptRecyclerAdapter extends
                    RecyclerView.Adapter<ReceiptRecyclerAdapter.ReceiptViewHolder>{
    ArrayList<Receipt> receipts;
    Context context;
    int i;

    public ReceiptRecyclerAdapter(ArrayList<Receipt> list, Context context){
        this.context = context;
        this.receipts = list;
    }

    @NonNull
    @Override
    public ReceiptViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.receipt_row_layout, parent,false);
        return new ReceiptViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReceiptViewHolder holder, int position) {
        holder.nameText.setText(receipts.get(position).productName);
        holder.quantityText.setText(String.valueOf(receipts.get(position).amtPurchased));
        holder.totalText.setText(String.valueOf(receipts.get(position).total));
        Receipt temp = receipts.get(position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent detailIntent = new Intent(context, MainActivity_TransactionDetails.class);
                detailIntent.putExtra("Index", holder.getAdapterPosition());
                context.startActivity(detailIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return receipts.size();
    }

    public static class ReceiptViewHolder extends RecyclerView.ViewHolder {
        TextView totalText;
        TextView quantityText;
        TextView nameText;

        public ReceiptViewHolder(@NonNull View itemView) {
            super(itemView);
            totalText = itemView.findViewById(R.id.priceTotal);
            nameText = itemView.findViewById(R.id.productNm);
            quantityText = itemView.findViewById(R.id.amtBought);


        }
    }
}
