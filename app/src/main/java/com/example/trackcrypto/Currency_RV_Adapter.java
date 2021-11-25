package com.example.trackcrypto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Currency_RV_Adapter extends RecyclerView.Adapter<Currency_RV_Adapter.ViewHolder> {
    private ArrayList<Model_RV_currency> model_rv_currencyArrayList;
    private Context context;
    private static DecimalFormat decimalFormat = new DecimalFormat("#.##");

    public Currency_RV_Adapter(ArrayList<Model_RV_currency> model_rv_currencyArrayList, Context context) {
        this.model_rv_currencyArrayList = model_rv_currencyArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public Currency_RV_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.currency_rv_item,parent,false);
        return new Currency_RV_Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Currency_RV_Adapter.ViewHolder holder, int position) {
        Model_RV_currency currency = model_rv_currencyArrayList.get(position);
        holder.currencyNameTV.setText(Model_RV_currency.getName());
        holder.symbolTV.setText(Model_RV_currency.getSymbol());
        holder.priceTV.setText('$'+decimalFormat.format(currency.getPrice()));
    }

    @Override
    public int getItemCount() {
        return model_rv_currencyArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView currencyNameTV , symbolTV , priceTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            currencyNameTV = itemView.findViewById(R.id.name);
            symbolTV = itemView.findViewById(R.id.symbol);
            priceTV = itemView.findViewById(R.id.price);
        }
    }
}
