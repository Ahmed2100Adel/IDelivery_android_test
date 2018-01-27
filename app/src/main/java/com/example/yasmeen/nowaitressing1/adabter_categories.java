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
 * Created by NgocTri on 11/15/2015.
 */
public class adabter_categories extends BaseAdapter {

    private Context mContext;
    private List<product_categories> mProductList;

    //Constructor

    public adabter_categories(Context mContext, List<product_categories> mProductList) {
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
        View v = View.inflate(mContext, R.layout.row_catergory, null);
        TextView tvName = (TextView)v.findViewById(R.id.tv_name);
        ImageView imageView =(ImageView)v.findViewById(R.id.imageView);
        //Set text for TextView
        tvName.setText(mProductList.get(position).getName());

        Glide.with(mContext)
                .load(mProductList.get(position).getUrl())
                .into(imageView);
        //Save product id to tag
        v.setTag(mProductList.get(position).getId());

        return v;
    }
}
