package com.example.trackcrypto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ProgressBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText searchEdit;
    private RecyclerView recyclerView;
    private ProgressBar loading;
    private ArrayList<Model_RV_currency> model_rv_currencyArrayList;
    private Currency_RV_Adapter currency_rv_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchEdit = findViewById(R.id.Search);
        recyclerView = findViewById(R.id.currencies);
        loading = findViewById(R.id.progress);

        model_rv_currencyArrayList = new ArrayList<>();
        currency_rv_adapter = new Currency_RV_Adapter(model_rv_currencyArrayList , this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(currency_rv_adapter);
    }
}