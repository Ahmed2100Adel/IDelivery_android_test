package com.example.yasmeen.nowaitressing1;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by NgocTri on 11/15/2015.
 */
public class delivery extends AppCompatActivity {
    private GridView lvProduct;
    private adabter_categories adapter;
    private List<product_categories> mProductList;
    int theId = 1;
    externerl_database db;
    TextView problem_accured;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);
        problem_accured =(TextView)findViewById(R.id.problem_accured);
        lvProduct = (GridView) findViewById(R.id.GV);

        mProductList = new ArrayList<>();
        //Add sample data for list
        //We can get data from DB, webservice here
        stringRequest();
        db=new externerl_database(this);

        //Init adapter


        lvProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Do something
                //Ex: display msg with product id get from view.getTag
                Intent i = new Intent(delivery.this, theMenu.class);
                Bundle b = new Bundle();
                b.putString("category", mProductList.get(position).getName());
                i.putExtras(b);
                startActivity(i);
            }
        });
    }

    private void stringRequest() {
        String serever_url = "http://192.168.2.5/xampp/food/SelectCategories.php";


        try {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, serever_url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.contains("Undefined")) {
                                Toast.makeText(delivery.this, "Undefined ", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(delivery.this, "selected success", Toast.LENGTH_LONG).show();
                                showJson(response);

                            }
                        }


                    }
                    , new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(delivery.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    Toast.makeText(delivery.this, "no select", Toast.LENGTH_LONG).show();
                    mProductList.clear();
                    lvProduct.setVisibility(View.GONE);
                    problem_accured.setVisibility(View.VISIBLE);

                }
            }) {
            };
            MySingleTon.getMinstance(delivery.this).addRequestQueuex(stringRequest);

        } catch (Exception e) {
            Toast.makeText(delivery.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
//                mProductList.add(new product(11, jsonObject1.getString("name"), jsonObject1.getString("price"),jsonObject1.getString("url").toString()));

    private void showJson(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("result");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                mProductList.add(new product_categories(theId++, jsonObject1.getString("category"), jsonObject1.getString("background")));

            }

            adapter = new adabter_categories(getApplicationContext(), mProductList);
            lvProduct.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem item = menu.findItem(R.id.search);
        return super.onCreateOptionsMenu(menu);

    }

    public void restart(MenuItem item) {


        this.finish();
    }

    public void search(MenuItem item) {


    }

    public void reload(MenuItem item) {
        mProductList.clear();
        stringRequest();
    }



    public void seeMore(View view) {
        Intent i = new Intent(this, seeingMore.class);
        startActivity(i);

    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(R.drawable.cancell)
                .setTitle("Closing Your Order")
                .setMessage("Are you sure you want to close Your Order?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        db.deletOrders(delivery.this);
                        finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }

    public void problemAcccured(View view){
        problem_accured.setVisibility(View.GONE);
        lvProduct.setVisibility(View.VISIBLE);
        stringRequest();

    }
}


