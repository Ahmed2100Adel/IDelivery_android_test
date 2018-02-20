package com.example.yasmeen.nowaitressing1;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class myFavorites extends AppCompatActivity {
externerl_database db;
    Cursor c ;
    private ListView lvProduct;
    private adabter_menu adapter;
    private List<product> mProductList;
    ///////////////
    String category;
    int theid= 1;
    String theOneItem ,thePrice,theMoreInfo,theUrl;
    String priceMedium;
    String priceLarge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_favorites);
        lvProduct =(ListView)findViewById(R.id.listview_product);
        db= new externerl_database(this);
        mProductList = new ArrayList<>();
        StringRequestSqLite();

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
                Intent i  =new Intent(myFavorites.this,moreDataOfItem.class);
                Bundle b = new Bundle();
                b.putString("theNameOfItem",theOneItem );
                if(String.valueOf(mProductList.get(position).getPrice()).equals("0")){
                    b.putString("thePrice","0");

                }else{ b.putString("thePrice",thePrice);}

                //b.putString("thePrice",thePrice );
                b.putString("theMoreInfo",theMoreInfo );
                if(theUrl.equals("")){
                    b.putString("theUrl","http://192.168.2.3/xampp/Food12.jpg" );

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
    public void StringRequestSqLite(){
        c=db.selectFavoratesFood(this);
        while(c.moveToNext()){
            Toast.makeText(this, c.getString(1), Toast.LENGTH_SHORT).show();
            mProductList.add(new product(c.getString(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4),c.getString(5),c.getString(6)));

        }
        adapter = new adabter_menu(getApplicationContext(), mProductList);
        lvProduct.setAdapter(adapter);

    }
    @Override
    protected void onRestart() {
        super.onRestart();
        mProductList.clear();
        StringRequestSqLite();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.back_developer, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public void back(MenuItem item) {
        this.finish();
    }
}
