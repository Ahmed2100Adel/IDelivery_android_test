package com.example.yasmeen.nowaitressing1;

import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
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
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class theMenu extends AppCompatActivity {
    private ListView lvProduct;
    private adabter_menu adapter;
    private List<product> mProductList;
    String category;
    TextView thecategory;
    int theid= 1;
    String theOneItem , allOrderd,thePrice,theMoreInfo,theUrl;
    TextView orders;
    externerl_database db ;
    Cursor c ;
    String priceMedium;
    String priceLarge;
    TextView problem_accured;
    Button seemoreBuuton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_menu);
        orders =(TextView)findViewById(R.id.orders);
        lvProduct = (ListView) findViewById(R.id.listview_product);
        problem_accured =(TextView)findViewById(R.id.problem_accured);
        seemoreBuuton =(Button)findViewById(R.id.seemoreBuuton);
        db =new externerl_database(this);
        Bundle b = getIntent().getExtras();
        category = b.getString("category");
        thecategory = (TextView) findViewById(R.id.category);
        thecategory.setText(category);
        mProductList = new ArrayList<>();
        c =db.selectOrders(this);
        while(c.moveToNext()){
            orders.append("("+c.getString(1)+") "+"("+c.getString(2)+") "+"("+c.getString(3)+") "+"("+c.getString(4)+")"+" | ");
        }
        stringRequest();
        lvProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Do something
                //Ex: display msg with product id get from view.getTag
                //    Intent i = new Intent(theMenu.this,MainActivity.class);
              //  startActivity(i);
                theOneItem =String.valueOf(mProductList.get(position).getName());
                thePrice =String.valueOf(mProductList.get(position).getPrice());
                theMoreInfo =String.valueOf(mProductList.get(position).getDescription());
                theUrl =String.valueOf(mProductList.get(position).getImageOfItem());
                priceMedium=String.valueOf(mProductList.get(position).getPrice_medium());
                priceLarge=String.valueOf(mProductList.get(position).getPrice_large());

              /* db.insetOrders(theMenu.this,theOneItem);
                orders.append(" "+theOneItem+" ");*/
               Intent i  =new Intent(theMenu.this,moreDataOfItem.class);
                Bundle b = new Bundle();
                b.putString("theNameOfItem",theOneItem );
                if(String.valueOf(mProductList.get(position).getPrice()).equals("0")){
                    b.putString("thePrice","0");

                }else{ b.putString("thePrice",thePrice);}

                //b.putString("thePrice",thePrice );
                b.putString("theMoreInfo",theMoreInfo );
                if(theUrl.equals("")){
                    b.putString("theUrl","http://192.168.2.5/xampp/Food12.jpg");

                }else{
                b.putString("theUrl",theUrl );}
                b.putString("category",category);
                b.putString("id",mProductList.get(position).getId());
                if(String.valueOf(mProductList.get(position).getPrice_large()).equals("0")){
                    b.putString("priceLarge","0");

                }else{ b.putString("priceLarge",priceMedium);}

                if(String.valueOf(mProductList.get(position).getPrice_medium()).equals("0")){
                    b.putString("priceMedium","0");

                }else{ b.putString("priceMedium",priceMedium);}

                b.putString("priceMedium",priceMedium);
                b.putString("priceLarge",priceLarge);
                i.putExtras(b);
                startActivity(i);

                // orders.append(" "+theOneItem+" ");
             //   Toast.makeText(getApplicationContext(),theOneItem , Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void stringRequest() {
        String serever_url = "http://192.168.2.5/xampp/food/select.php";


        try {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, serever_url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.contains("Undefined")) {
                                Toast.makeText(theMenu.this, "Undefined ", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(theMenu.this, "selected success", Toast.LENGTH_LONG).show();
                                showJson(response);

                            }
                        }


                    }
                    , new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(theMenu.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    Toast.makeText(theMenu.this, "no select", Toast.LENGTH_LONG).show();
                    mProductList.clear();
                    lvProduct.setVisibility(View.GONE);
                    orders.setVisibility(View.GONE);
                    seemoreBuuton.setVisibility(View.GONE);
                    thecategory.setVisibility(View.GONE);
                    problem_accured.setVisibility(View.VISIBLE);

                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("category", category);
                    return params;
                }
            };
            MySingleTon.getMinstance(theMenu.this).addRequestQueuex(stringRequest);

        } catch (Exception e) {
            Toast.makeText(theMenu.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
//                mProductList.add(new product(11, jsonObject1.getString("name"), jsonObject1.getString("price"),jsonObject1.getString("url").toString()));

    private void showJson(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("result");
            for (int i = 0 ; i < jsonArray.length() ; i ++){
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                mProductList.add(new product(jsonObject1.getString("id"), jsonObject1.getString("name"), jsonObject1.getString("price"),jsonObject1.getString("info").toString(),jsonObject1.getString("url").toString(),
                        jsonObject1.getString("priceMedium").toString(),jsonObject1.getString("priceLarge").toString()));

            }

            adapter = new adabter_menu(getApplicationContext(), mProductList);
            lvProduct.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        MenuItem item = menu.findItem(R.id.search);
/*
        SearchView searchView = (SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.get().filter(newText);

                return false;
            }
        });*/
        return super.onCreateOptionsMenu(menu);
    }
    public void restart(MenuItem item)
    {


        this.finish();
    }
    public void search(MenuItem item)
    {



    }
    public void reload(MenuItem item)
    {mProductList.clear();
        stringRequest();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
    public void seeMore(View view){
        Intent i = new Intent(this,seeingMore.class);
        startActivity(i);

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        orders.setText("");
        mProductList.clear();
        stringRequest();
        c=db.selectOrders(this);
        while(c.moveToNext()){
            orders.append("("+c.getString(1)+") "+"("+c.getString(2)+") "+"("+c.getString(3)+") "+"("+c.getString(4)+")"+" | ");
        }

    }
public void problemAcccured(View view){
    problem_accured.setVisibility(View.GONE);
    orders.setVisibility(View.VISIBLE);
    seemoreBuuton.setVisibility(View.VISIBLE);
    thecategory.setVisibility(View.VISIBLE);
    lvProduct.setVisibility(View.VISIBLE);
    stringRequest();
}


}
