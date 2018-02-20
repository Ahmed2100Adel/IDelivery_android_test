package com.example.yasmeen.nowaitressing1;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class seeingMore extends AppCompatActivity {
    private ListView lvProduct;
    private adabter_seemore adapter;
    private List<product_seemore> mProductList;
    TextView total;
    Cursor c;
    String theTotal;
    int thetotalquan;
    externerl_database db;
    int theid = 1;
    int el3dad,els3r, Total;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seeing_more);
        total =(TextView)findViewById(R.id.total_seemore) ;
        lvProduct = (ListView) findViewById(R.id.listview_product);

        mProductList = new ArrayList<>();
        db =new externerl_database(this);
        stringRequest();
        lvProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(seeingMore.this,editOneItem.class);
                Bundle b = new Bundle();
                b.putString("name", mProductList.get(position).getName());
                b.putString("size", mProductList.get(position).getSize());
                b.putString("comment", mProductList.get(position).getcomment());
                b.putString("url", mProductList.get(position).getUrl());
                b.putString("quantity", mProductList.get(position).getQuantity());
                b.putString("id", mProductList.get(position).getId());
                b.putString("price",mProductList.get(position).getPrice());
                b.putString("priceMedium",mProductList.get(position).getPriceMedium());
                b.putString("priceLarge",mProductList.get(position).getPriceLarge());
                b.putString("moreInfo",mProductList.get(position).getMoreInfo());

                i.putExtras(b);
                startActivity(i);
                Toast.makeText(seeingMore.this,String.valueOf(position), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void stringRequest() {
        c =db.selectOrders(this);
        while(c.moveToNext()){
           mProductList.add(new product_seemore(c.getString(0),c.getString(1),c.getString(8),c.getString(2),c.getString(3),c.getString(5),c.getString(4),c.getString(6),c.getString(7),c.getString(9)));
            //Toast.makeText(this, c.getString(7), Toast.LENGTH_SHORT).show();
            //            Toast.makeText(this, c.getString(5), Toast.LENGTH_SHORT).show();
            String elhagm=c.getString(3).toString();
           // Toast.makeText(this,elhagm, Toast.LENGTH_SHORT).show();
            if(elhagm.equals("Small")){
                el3dad =Integer.valueOf(c.getString(2).toString());
                els3r=Integer.valueOf(c.getString(5).toString());
                Total+=el3dad*els3r;
            }else if(elhagm.equals("medium")){
                el3dad =Integer.valueOf(c.getString(2).toString());
                els3r=Integer.valueOf(c.getString(6).toString());
                Total+=el3dad*els3r;
            }else if(elhagm.equals("large")){
                el3dad =Integer.valueOf(c.getString(2).toString());
                els3r=Integer.valueOf(c.getString(7).toString());
                Total+=el3dad*els3r;

            }


        }
        total.setText(String.valueOf(String.valueOf(Total)));
       adapter = new adabter_seemore(getApplicationContext(), mProductList);
       lvProduct.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.seeing_more, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void back(MenuItem item) {

        this.finish();
    }
    public void send(MenuItem item) {
        c=db.selectUsers(this);
        if(c.getCount()<1){
            Toast.makeText(this, "please sign in first", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this,LoginActivity.class);
            startActivity(i);
        }else{
        Intent i = new Intent(this,sendingOrders.class);
        startActivity(i);
        Toast.makeText(this, "send", Toast.LENGTH_SHORT).show();}
    }

    @Override
    protected void onRestart() {
        super.onRestart();
       // ArrayAdapter adapter;
        //this.onRestart();
       // ArrayAdapter sampleAdapter = (ArrayAdapter)lvProduct.getAdapter();
        //sampleAdapter.clear();
        //List<String> subject_list;
        //subject_list = new ArrayList<String>(Arrays.asList(subjects));
        mProductList.clear();
        Total=0;
        stringRequest();

        //adapter = new adabter_seemore(getApplicationContext(), mProductList);
       // lvProduct.setAdapter(adapter);
        Toast.makeText(this, "restart", Toast.LENGTH_SHORT).show();
    }

    private void total(){

    }





}