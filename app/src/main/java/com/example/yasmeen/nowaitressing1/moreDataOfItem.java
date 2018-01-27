package com.example.yasmeen.nowaitressing1;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

public class moreDataOfItem extends AppCompatActivity {
    String theNameOfItem, thePrice, theMoreInfo, theUrl,  catogery ,id;
    String theGirth ="Small";
    ImageView imageView;
    TextView nameOfItem, moreInfo, price, quantitiy,comments,priceMedium_moredata,priceLarge_moredat,total,seemore;
    int thequantitiy,lengthofmoreInfo;
    CheckBox checbox_small, checkbox_medium, checkbox_large;
    Button saveItem;
    externerl_database db;
    Cursor c;
    String priceMedium,priceLarge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_data_of_item);
        imageView = (ImageView) findViewById(R.id.imageViewOfItem);
        nameOfItem = (TextView) findViewById(R.id.nameOfItem);
        price = (TextView) findViewById(R.id.price);
        moreInfo = (TextView) findViewById(R.id.moreInfo);
        total=(TextView)findViewById(R.id.total_moreData);
        saveItem = (Button) findViewById(R.id.saveItem);
        quantitiy = (TextView) findViewById(R.id.quantitiy);
        comments =(TextView)findViewById(R.id.comments);
        priceMedium_moredata =(TextView)findViewById(R.id.price_medium);
        priceLarge_moredat =(TextView)findViewById(R.id.price_large);
        checbox_small = (CheckBox) findViewById(R.id.checkbox_small);
        checkbox_medium = (CheckBox) findViewById(R.id.checkbox_medium);
        checkbox_large = (CheckBox) findViewById(R.id.checkbox_large);
        seemore = (TextView) findViewById(R.id.seemore);

        db = new externerl_database(this);
        Bundle b = getIntent().getExtras();
        theNameOfItem = b.getString("theNameOfItem");
        thePrice = b.getString("thePrice");
        theMoreInfo = b.getString("theMoreInfo");
        theUrl = b.getString("theUrl");
        catogery = b.getString("category");
        priceMedium=b.getString("priceMedium");
        priceLarge=b.getString("priceLarge");
        id =b.getString("id");
        total.setText(thePrice);
        Glide.with(this)
                .load(theUrl)
                .into(imageView);
        nameOfItem.setText(theNameOfItem);
        moreInfo.setText(theMoreInfo);
        if(Integer.valueOf(thePrice)==0){
           // String no =@string/small
            price.setText( getString(R.string.noSize));

        }else{

            price.setText(thePrice.toString()+ "$");
        }
        //price.setText(thePrice + "$");
        quantitiy.setText("1");
        if(Integer.valueOf(priceMedium)==0){
            priceMedium_moredata.setText(getString(R.string.noSize));

        }else{

        priceMedium_moredata.setText(priceMedium.toString()+ "$");
        }
        if(Integer.valueOf(priceLarge)==0){
            priceLarge_moredat.setText(getString(R.string.noSize));

        }else{
        priceLarge_moredat.setText(priceLarge.toString()+ "$");}
        checbox_small.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checkbox_medium.setChecked(false);
                    checkbox_large.setChecked(false);
                    theGirth = "Small";
                    total();

                } else {

                }
            }
        });
        checkbox_medium.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if(Integer.valueOf(priceMedium)==0){
                        checkbox_medium.setChecked(false);
                        checbox_small.setChecked(true);
                        Toast.makeText(moreDataOfItem.this, "We are sorry no medium size for this", Toast.LENGTH_SHORT).show();
                    }else {
                        checbox_small.setChecked(false);
                        checkbox_large.setChecked(false);
                        theGirth = "medium";
                        total();
                    }
                } else {

                }
            }
        });
        checkbox_large.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if(Integer.valueOf(priceLarge)==0){
                        checkbox_large.setChecked(false);
                        checkbox_medium.setChecked(true);
                        Toast.makeText(moreDataOfItem.this, "We are sorry no large size for this", Toast.LENGTH_SHORT).show();
                    }else {
                        checbox_small.setChecked(false);
                        checkbox_medium.setChecked(false);
                        theGirth = "large";
                        total();
                    }
                } else {

                }
            }
        });
        lengthofmoreInfo =theMoreInfo.length();
        if((lengthofmoreInfo>25)){
            seemore.setVisibility(View.VISIBLE);
        }

    }

    public void plus(View view) {

        thequantitiy = Integer.valueOf(quantitiy.getText().toString());
        thequantitiy++;
        String thenum1 = String.valueOf(thequantitiy);
        quantitiy.setText(thenum1);
        total();


    }

    public void minus(View view) {
        thequantitiy = Integer.valueOf(quantitiy.getText().toString());
        if (thequantitiy==1){}else{
        thequantitiy--;
        String thenum = String.valueOf(thequantitiy);
        quantitiy.setText(thenum);
            total();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.addmore_item, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void back(MenuItem item) {
        this.finish();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
    }

    public void saveItem(View view) {
        if(checbox_small.isChecked()||checkbox_medium.isChecked()||checkbox_large.isChecked()){
            //db.insetOrders(this,id, theNameOfItem, quantitiy.getText().toString(), theGirth, comments.getText().toString(), thePrice,priceMedium,priceLarge, theUrl);
            db.insetOrders(this,id, theNameOfItem, quantitiy.getText().toString(), theGirth, comments.getText().toString(), thePrice,priceMedium,priceLarge, theUrl,theMoreInfo);
                this.finish();
        }else{
            Toast.makeText(this, "You should Select the size", Toast.LENGTH_SHORT).show();
        }


        /* c = db.selectOrders(this);
         c= db.selectOrders(this);
        if(c.getCount()>0) {
            Toast.makeText(this, "d", Toast.LENGTH_LONG).show();
            while (c.moveToNext()) {
                Toast.makeText(this, "jkah hu", Toast.LENGTH_SHORT).show();

                String name = c.getString(0);
                String quan = c.getString(1);
                String size = c.getString(2);
                Toast.makeText(this, name + quan + size, Toast.LENGTH_SHORT).show();
            }
        }*/


    }
    private void total(){
        if(checbox_small.isChecked()){
            int el3dad =Integer.valueOf(quantitiy.getText().toString());
            int els3r=Integer.valueOf(thePrice);
            total.setText(String.valueOf(el3dad*els3r));
        }else if(checkbox_medium.isChecked()){
            int el3dad =Integer.valueOf(quantitiy.getText().toString());
            int els3r=Integer.valueOf(priceMedium);
            total.setText(String.valueOf(el3dad*els3r));
        }else  if (checkbox_large.isChecked()){
            int el3dad =Integer.valueOf(quantitiy.getText().toString());
            int els3r=Integer.valueOf(priceLarge);
            total.setText(String.valueOf(el3dad*els3r));
        }
    }
    /*public void allinfo(View view){
        //Toast.makeText(this, "all info", Toast.LENGTH_SHORT).show();
        //AlertDialog.Builder mbuilder =new AlertDialog.Builder(moreDataOfItem.this);
        //View mview =getLayoutInflater().inflate(R.layout.activity_dialog_of_all_info,null);
        //TextView mEmail =(TextView)mview.findViewById(R.id.etallinfo);
        Intent i =new Intent(this,etcustomdialog.class);
            startActivity(i);
    }*/
    public void seemore(View view){
        final AlertDialog.Builder mbuilder=new AlertDialog.Builder(moreDataOfItem.this);
        final View mview =getLayoutInflater().inflate(R.layout.activity_etcustomdialog,null);
        TextView etmoreInfo = (TextView) mview.findViewById(R.id.tvdialog);
        Button cancelButton =(Button)mview.findViewById(R.id.cancel_actionn);
        etmoreInfo.setText(theMoreInfo);

        mbuilder.setView(mview);
        final AlertDialog dialog = mbuilder.create();
        dialog.show();
        cancelButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

    }



}
