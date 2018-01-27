package com.example.yasmeen.nowaitressing1;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatCallback;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by NgocTri on 11/15/2015.
 */
public class adabter_menu extends BaseAdapter {

    private Context mContext;
    private List<product> mProductList;
    private String picture ="http://192.168.2.3/xampp/Food12.jpg";

    //Constructor

    public adabter_menu(Context mContext, List<product> mProductList) {
        this.mContext = mContext;
        this.mProductList = mProductList;
    }

    @Override
    public int getCount() {
        return mProductList.size();
    }

    @Override
    public Object getItem(int position) {
        return mProductList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(mContext, R.layout.rowmenu, null);
        TextView tvName = (TextView)v.findViewById(R.id.tv_name);
        TextView tvPrice = (TextView)v.findViewById(R.id.tv_price);
        TextView tvDescription = (TextView)v.findViewById(R.id.tv_description);
        ImageView imageView = (ImageView)v.findViewById(R.id.imageView);
        TextView tv_priceMedium = (TextView)v.findViewById(R.id.tv_pricemedium);
        TextView tv_priceLarge = (TextView)v.findViewById(R.id.tv_priceLarge);
        TextView seemore = (TextView)v.findViewById(R.id.seemore);
        //Set text for TextView
        tvName.setText(mProductList.get(position).getName());
        tvPrice.setText(String.valueOf(mProductList.get(position).getPrice()) + "$");
        int lengthofDescription =mProductList.get(position).getDescription().length();
        if(lengthofDescription>30){
            seemore.setVisibility(View.VISIBLE);
            tvDescription.setText(mProductList.get(position).getDescription());

        }else {
            tvDescription.setText(mProductList.get(position).getDescription());
        }

        if(mProductList.get(position).getPrice_medium().equals("0")){
            tv_priceMedium.setText(mContext.getResources().getString(R.string.noSize));
        }else{
        tv_priceMedium.setText(mProductList.get(position).getPrice_medium() + "$");}
        if(mProductList.get(position).getPrice_large().equals("0")){
            tv_priceLarge.setText(mContext.getResources().getString(R.string.noSize));

        }else{
        tv_priceLarge.setText(mProductList.get(position).getPrice_large() + "$");}

        picture =mProductList.get(position).getImageOfItem();
        if (picture.equals("")){
            Glide.with(mContext)
                        .load("http://192.168.2.3/xampp/Food12.jpg")
                    .into(imageView);
        }else{
        Glide.with(mContext)
                .load(mProductList.get(position).getImageOfItem())
                .into(imageView);}

        //Save product id to tag
        v.setTag(mProductList.get(position).getId());

        return v;
    }

}
