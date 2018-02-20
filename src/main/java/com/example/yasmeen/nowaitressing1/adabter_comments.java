package com.example.yasmeen.nowaitressing1;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by yasmeen on 1/28/2018.
 */

public class adabter_comments  extends BaseAdapter {

    private Context mContext;
    private List<product_comments> mProductList;
    // private String picture ="http://192.168.2.3/xampp/Food12.jpg";

    //Constructor

    public adabter_comments(Context mContext, List<product_comments> mProductList) {
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
        View v = View.inflate(mContext, R.layout.row_comment, null);
        TextView textOfComment = (TextView)v.findViewById(R.id.textOfComment);
        //Set text for TextView
        textOfComment.setText(mProductList.get(position).getTextOfComment());

        //Save product id to tag*/
        v.setTag(mProductList.get(position).getId());

        return v;
    }
}


