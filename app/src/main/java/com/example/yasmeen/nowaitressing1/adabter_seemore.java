package com.example.yasmeen.nowaitressing1;

import android.content.Context;
import android.media.Image;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by NgocTri on 11/15/2015.
 */
public class adabter_seemore extends BaseAdapter {
    int thetotal;
    private Context mContext;
    private List<product_seemore> mProductList;
    externerl_database db;
    //Constructor

    public adabter_seemore(Context mContext, List<product_seemore> mProductList) {
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
        View v = View.inflate(mContext, R.layout.row_see_more, null);
        TextView tvName = (TextView)v.findViewById(R.id.tv_name_seemore);
        ImageView imageView =(ImageView)v.findViewById(R.id.imageOfSeeMore);
        TextView tv_quantity_seemore =(TextView) v.findViewById(R.id.tv_quantity_seemore);
        TextView tv_size =(TextView)v.findViewById(R.id.tv_size1_seemore);
        TextView total11_seemore =(TextView)v.findViewById(R.id.total11_seemore) ;
        TextView comments=(TextView)v.findViewById(R.id.comments);
        CheckBox iwilltakeit=(CheckBox)v.findViewById(R.id.iwilltakeit);
        // TextView tvDescription = (TextView)v.findViewById(R.id.tv_description);
        //Set text for TextView
        if(!iwilltakeit.isChecked()){
            iwilltakeit.setChecked(true);
            Toast.makeText(mContext, "you can cancel it from inside", Toast.LENGTH_SHORT).show();
        }
        tvName.setText(mProductList.get(position).getName());
        //tvDescription.setText(mProductList.get(position).getDescription());
        Glide.with(mContext)
                .load(mProductList.get(position).getUrl())
                .into(imageView);
        tv_quantity_seemore.setText(mProductList.get(position).getQuantity());
            tv_size.setText(mProductList.get(position).getSize());
            String elhagm=mProductList.get(position).getSize();
            if(elhagm.equals("Small")){
                int els3r =Integer.valueOf(mProductList.get(position).getPrice().toString());
                int el3d3d =Integer.valueOf(mProductList.get(position).getQuantity().toString());
                thetotal =els3r*el3d3d;
                total11_seemore.setText(String.valueOf(thetotal)+"$" +" / "+ mProductList.get(position).getPrice().toString());
            } else if(elhagm.equals("medium")){
                int els3r =Integer.valueOf(mProductList.get(position).getPriceMedium().toString());
                int el3d3d =Integer.valueOf(mProductList.get(position).getQuantity().toString());
                thetotal =els3r*el3d3d;
                total11_seemore.setText(String.valueOf(thetotal)+"$" +" / "+ mProductList.get(position).getPriceMedium().toString());
            }else if(elhagm.equals("large")){
                int els3r =Integer.valueOf(mProductList.get(position).getPriceLarge().toString());
                int el3d3d =Integer.valueOf(mProductList.get(position).getQuantity().toString());
                thetotal =els3r*el3d3d;
                total11_seemore.setText(String.valueOf(thetotal)+"$" +" / "+ mProductList.get(position).getPriceLarge().toString());
            }


        //db.deletOrders(mContext);



        comments.setText(mProductList.get(position).getcomment());
        //Save product id to tag
        v.setTag(mProductList.get(position).getId());

        return v;
    }
}
