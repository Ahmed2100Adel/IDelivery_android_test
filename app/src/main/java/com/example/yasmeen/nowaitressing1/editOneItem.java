package com.example.yasmeen.nowaitressing1;

import android.content.Intent;
import android.database.Cursor;
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

public class editOneItem extends AppCompatActivity {
String bname ,bsize,bcomment,bUrl,bquantity,id,bprice,priceMedium,priceLarge,theGirth,bmoreInfo;
    TextView nameOfEdit ,commentsEdit ,quantitiyEdit,priceEdit,price_mediumEdit,price_largEdit,total_moreDataEdit,moreInfo ,seemore;
    CheckBox checkBoxsmall,checkboxMedium,checkboxLarge;
    ImageView imageOfEdit;
    int thequantitiy,lengthofmoreInfo;
    externerl_database db ;
    Cursor c ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_one_item);

        nameOfEdit =(TextView)findViewById(R.id.nameOfEdit);
        db =new externerl_database(this);
        checkBoxsmall =(CheckBox)findViewById(R.id.checkbox_smallEdit) ;
        checkboxMedium =(CheckBox)findViewById(R.id.checkbox_mediumEdit) ;
        checkboxLarge =(CheckBox)findViewById(R.id.checkbox_largeEdit) ;
        commentsEdit =(TextView) findViewById(R.id.commentsEdit);
        imageOfEdit =(ImageView) findViewById(R.id.imageOfEdit);
        quantitiyEdit =(TextView) findViewById(R.id.quantitiyEdit);
        priceEdit =(TextView) findViewById(R.id.priceEdit);
        price_mediumEdit =(TextView) findViewById(R.id.price_mediumEdit);
        price_largEdit =(TextView) findViewById(R.id.price_largEdit);
        total_moreDataEdit =(TextView) findViewById(R.id.total_moreDataEdit);
        moreInfo =(TextView) findViewById(R.id.infoEdit);
        seemore = (TextView) findViewById(R.id.seemore);







        Bundle b = getIntent().getExtras();
        bname = b.getString("name");
        bsize = b.getString("size");
        bmoreInfo = b.getString("moreInfo");
        bcomment =b.getString("comment");
        id=b.getString("id");
        bUrl =b.getString("url");
        priceMedium=b.getString("priceMedium");
        priceLarge=b.getString("priceLarge");
        bquantity =b.getString("quantity");
        bprice =b.getString("price");

        if(bprice.equals("0")){
            priceEdit.setText("no size for it");
        }else {
            priceEdit.setText(bprice + "$");
        }
        quantitiyEdit.setText(bquantity);
        thequantitiy = Integer.valueOf(quantitiyEdit.getText().toString());
        if(priceMedium.equals("0")){
            price_mediumEdit.setText("no size for this");

        }else{
        price_mediumEdit.setText(priceMedium+"$");}
        if(priceLarge.equals("0")){
            price_largEdit.setText("no size for this");
        }else{
        price_largEdit.setText(priceLarge+"$");}
        nameOfEdit.setText(bname);
        if(bsize.equals("Small")){
            checkBoxsmall.setChecked(true);
            theGirth="Small";
        }else if(bsize.equals("medium")){
            checkboxMedium.setChecked(true);
            theGirth="medium";

        }else if(bsize.equals("large")){
            checkboxLarge.setChecked(true);
            theGirth="large";

        }
        commentsEdit.setText(bcomment);
        Glide.with(this)
                .load(bUrl)
                .into(imageOfEdit);


        checkBoxsmall.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if(Integer.valueOf(bprice)==0){
                        checkBoxsmall.setChecked(false);
                        checkboxMedium.setChecked(true);
                        Toast.makeText(editOneItem.this, "We are sorry no small size for this", Toast.LENGTH_SHORT).show();

                    }else {
                        checkboxMedium.setChecked(false);
                        checkboxLarge.setChecked(false);
                        theGirth = "Small";
                        total();
                    }
                } else {

                }
            }
        });
        checkboxMedium.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if(Integer.valueOf(priceMedium)==0){
                        checkboxMedium.setChecked(false);
                        checkBoxsmall.setChecked(true);
                        Toast.makeText(editOneItem.this, "We are sorry no medium size for this", Toast.LENGTH_SHORT).show();
                    }else {
                        checkBoxsmall.setChecked(false);
                        checkboxLarge.setChecked(false);
                        theGirth = "medium";
                        total();
                    }
                } else {

                }
            }
        });
        checkboxLarge.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if(Integer.valueOf(priceLarge)==0){
                        checkboxLarge.setChecked(false);
                        checkboxMedium.setChecked(true);
                        Toast.makeText(editOneItem.this, "We are sorry no large size for this", Toast.LENGTH_SHORT).show();
                    }else {
                        checkBoxsmall.setChecked(false);
                        checkboxMedium.setChecked(false);
                        theGirth = "large";
                        total();
                    }
                } else {

                }
            }
        });
        total();
        moreInfo.setText(bmoreInfo);

        //Toast.makeText(this, theGirth, Toast.LENGTH_SHORT).show();
        lengthofmoreInfo =bmoreInfo.length();
        if((lengthofmoreInfo>25)){
            seemore.setVisibility(View.VISIBLE);
        }


    }
    public void plus(View view) {

        thequantitiy++;
        String thenum1 = String.valueOf(thequantitiy);
        quantitiyEdit.setText(thenum1);
      total();


    }

    public void minus(View view) {
        thequantitiy = Integer.valueOf(quantitiyEdit.getText().toString());
        if (thequantitiy==1){}else{
            thequantitiy--;
            String thenum = String.valueOf(thequantitiy);
            quantitiyEdit.setText(thenum);
            total();
        }
    }
    private void total() {
        if (checkBoxsmall.isChecked()) {
            int el3dad = Integer.valueOf(quantitiyEdit.getText().toString());
            int els3r = Integer.valueOf(bprice);
            total_moreDataEdit.setText(String.valueOf(el3dad * els3r));
        } else if (checkboxMedium.isChecked()) {
            int el3dad = Integer.valueOf(quantitiyEdit.getText().toString());
            int els3r = Integer.valueOf(priceMedium);
            total_moreDataEdit.setText(String.valueOf(el3dad * els3r));
        } else if (checkboxLarge.isChecked()) {
            int el3dad = Integer.valueOf(quantitiyEdit.getText().toString());
            int els3r = Integer.valueOf(priceLarge);
            total_moreDataEdit.setText(String.valueOf(el3dad * els3r));
        }
    }
        public void changeedit(View view){
            Toast.makeText(this, "chage", Toast.LENGTH_SHORT).show();
            db.update(this,id,quantitiyEdit.getText().toString(),theGirth,commentsEdit.getText().toString());
           // Intent i = new Intent(this,seeingMore.class);
            //startActivity(i);
            this.finish();
        }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.deletofitem, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void delet(MenuItem item) {

        db.deletOneOrder(this,id);
        this.finish();
    }
    public void seemore(View view){
        final AlertDialog.Builder mbuilder=new AlertDialog.Builder(editOneItem.this);
        final View mview =getLayoutInflater().inflate(R.layout.activity_etcustomdialog,null);
        TextView etmoreInfo = (TextView) mview.findViewById(R.id.tvdialog);
        Button cancelButton =(Button)mview.findViewById(R.id.cancel_actionn);
        etmoreInfo.setText(bmoreInfo);

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




