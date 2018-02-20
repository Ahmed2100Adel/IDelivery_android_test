package com.example.yasmeen.nowaitressing1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class offers extends AppCompatActivity {
    private ListView lvProduct;
    private adabter_offers adapter;
    private List<productOffers> mProductList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offers);
        lvProduct = (ListView) findViewById(R.id.listview_offers);

        mProductList = new ArrayList<>();
        stringRequest();
        lvProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Do something
                //Ex: display msg with product id get from view.getTag
                Intent i = new Intent(offers.this, moreDataOfOffer.class);
                Bundle b = new Bundle();
                b.putString("id", mProductList.get(position).getId());
                b.putString("name", mProductList.get(position).getName());
                b.putString("url",mProductList.get(position).getUrl());
                b.putString("numsOfOrders", mProductList.get(position).getNumsOfOrders());
                b.putString("inDays", mProductList.get(position).getInDays());
                b.putString("howMuchMinus", mProductList.get(position).getHowMuchMinus());
                b.putString("howMuchPercentage", mProductList.get(position).getHowMuchPercentage());
                b.putString("serialNumber", mProductList.get(position).getSerialNumber());
                i.putExtras(b);
                startActivity(i);
            }
        });
    }
    private void stringRequest() {
        String serever_url = "http://192.168.2.5/xampp/food/selectoOfers.php";


        try {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, serever_url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.contains("Undefined")) {
                                Toast.makeText(offers.this, "Undefined ", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(offers.this, "selected success", Toast.LENGTH_LONG).show();
                                showJson(response);

                            }
                        }


                    }
                    , new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(offers.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    Toast.makeText(offers.this, "no select", Toast.LENGTH_LONG).show();

                }
            }) {
            };
            MySingleTon.getMinstance(offers.this).addRequestQueuex(stringRequest);

        } catch (Exception e) {
            Toast.makeText(offers.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
//                mProductList.add(new product(11, jsonObject1.getString("name"), jsonObject1.getString("price"),jsonObject1.getString("url").toString()));

    private void showJson(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("result");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
               // Toast.makeText(this,  jsonObject1.getString("name"), Toast.LENGTH_SHORT).show();
                mProductList.add(new productOffers( jsonObject1.getString("id"), jsonObject1.getString("name"),jsonObject1.getString("url"), jsonObject1.getString("numsOfOrders"),jsonObject1.getString("inDays"), jsonObject1.getString("howMuchMinus"),jsonObject1.getString("howMuchPercentage"), jsonObject1.getString("serialNumber")));

            }

            adapter = new adabter_offers(getApplicationContext(), mProductList);
            lvProduct.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.back_developer,menu);

        return super.onCreateOptionsMenu(menu);
    }
    public void back(MenuItem item)
    {
        this.finish();
    }

}
