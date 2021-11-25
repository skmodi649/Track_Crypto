package com.example.trackcrypto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

        //Setting up the method for getting data from the volley(API)
        getCurrencyData();
    }

    private void getCurrencyData(){
        loading.setVisibility(View.VISIBLE);
        String url = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                loading.setVisibility(View.GONE);
                try{
                    JSONArray dataArray = response.getJSONArray("data");
                    for(int i = 0 ; i < dataArray.length() ; i++){
                        JSONObject jsonObject = dataArray.getJSONObject(i);
                        String name = jsonObject.getString("name");
                        String symbol = jsonObject.getString("symbol");
                        JSONObject quote = jsonObject.getJSONObject("quote");
                        JSONObject USD = quote.getJSONObject("USD");
                        double price = USD.getDouble("price");
                        model_rv_currencyArrayList.add(new Model_RV_currency(name , symbol , price));
                    }
                    currency_rv_adapter.notifyDataSetChanged();
                }
                catch (JSONException e){
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this , "Failed to extract json data!" , Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loading.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this , "Failed to acess the data!" , Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String , String> headers = new HashMap<>();
                headers.put("X-CMC_PRO_API_KEY","903ed07e-9390-4532-853a-436c07fe5d59");
                return headers;
            }
        };
        requestQueue.add(jsonObjectRequest);
    }
}