package com.example.yasmeen.nowaitressing1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.CompoundButton;
import android.widget.Toast;

/**
 * Created by yasmeen on 12/22/2017.
 */

public class externerl_database extends SQLiteOpenHelper {
    public externerl_database(Context context) {
        super(context, "informations", null, 1);
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE orders (id TEXT,name TEXT,quantity TEXT,size TEXT,comments TEXT,priceSmall TEXT,priceMedium TEXT,priceLarge TEXT,url TEXT,moreInfo TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE insetAllOrders (id INTEGER PRIMARY KEY AUTOINCREMENT ,url TEXT,name TEXT,morInfo TEXT,price TEXT,priceMedium priceLarge,quantity TEXT,size,TEXT,comment TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE userinfo (id INTEGER PRIMARY KEY AUTOINCREMENT ,name TEXT,email TEXT,urlpic TEXT,urlbackground TEXT,phone TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public String insetOrders (Context context,String id,String name,String quantity,String size,String comments,String priceSmall,String priceMedium,String priceLarge,String url,String moreInfo)
    {
        ContentValues cv = new ContentValues();
        cv.put("name" , name);
        cv.put("id " , id );
        cv.put("quantity" , quantity);
        cv.put("size" , size);
        cv.put("comments" , comments);
        cv.put("priceSmall" , priceSmall);
        cv.put("priceMedium" , priceMedium);
        cv.put("priceLarge" , priceLarge);
        cv.put("url" , url);
        cv.put("moreInfo" , moreInfo);
        SQLiteDatabase db= this.getWritableDatabase();
        db.insert("orders",null,cv);
        //////
        Toast.makeText(context,"order inserted",Toast.LENGTH_SHORT).show();

        return "";
    }
    public String insertUser (Context context,String name,String email,String urlpic,String urlbackground,String phone)
    {
        ContentValues cv = new ContentValues();
        cv.put("name" , name);
        cv.put("email " , email );
        cv.put("urlpic" , urlpic);
        cv.put("urlbackground" , urlbackground);
        cv.put("phone" , phone);

        SQLiteDatabase db= this.getWritableDatabase();
        db.insert("userinfo",null,cv);
        //////
        Toast.makeText(context,"user  inserted",Toast.LENGTH_SHORT).show();

        return "";
    }


    public String insetAllOrders (Context context,String url,String name,String morInfo,String price,String priceMedium,String priceLarge,String quantity,String size ,String comments)
    {
        ContentValues cv = new ContentValues();
        cv.put("url" , url);
        cv.put("name" , name);
        cv.put("morInfo" , morInfo);
        cv.put("price" , price);
        cv.put("priceMedium" , priceMedium);
        cv.put("priceLarge" , priceLarge);
        cv.put("quantity" , quantity);
        cv.put("size" , size);
        cv.put("comments" , comments);
        SQLiteDatabase db= this.getWritableDatabase();
        db.insert("orders",null,cv);
        //////
        Toast.makeText(context,"order inserted",Toast.LENGTH_SHORT).show();

        return "";
    }
    public Cursor selectOrders(Context context)
    {
        Cursor c ;
        SQLiteDatabase db =this.getReadableDatabase();
        c =db.rawQuery("SELECT * FROM orders ",null);
        if(c.getCount()<1) {
            Toast.makeText(context, "there's no orders", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "orders will come", Toast.LENGTH_SHORT).show();
        }

            return c ;
    }
    public void deletOrders(Context context)
    {

        SQLiteDatabase db= this.getWritableDatabase();
        db.delete("orders", null,null);
        //db.execSQL("DELETE * FROM tb1");
        Toast.makeText(context,"Canceled your Order",Toast.LENGTH_SHORT).show();

    }
    public void deletOneOrder(Context context,String id)
    {

        SQLiteDatabase db= this.getWritableDatabase();
        db.delete("orders", "id = "+id,null);
        //db.execSQL("DELETE * FROM tb1");
        Toast.makeText(context,"Canceled your Order",Toast.LENGTH_SHORT).show();

    }
    public String update (Context context,String id,String quantity,String size,String comments){
        ContentValues cv = new ContentValues();
        //cv.put("name" , name);
        cv.put("quantity" , quantity);
        cv.put("size" , size);
        cv.put("comments" , comments);
       // cv.put("priceSmall" , priceSmall);
       // cv.put("priceMedium" , priceMedium);
      //  cv.put("priceLarge" , priceLarge);
        //cv.put("url" , url);
        SQLiteDatabase db= this.getWritableDatabase();
        db.update("orders",cv,"id = "+id,null);
        Toast.makeText(context,"updated sucrss",Toast.LENGTH_LONG).show();

        //////

        return "";
    }
    public void deletUser(Context context)
    {
        Toast.makeText(context,"deleted and log out",Toast.LENGTH_LONG).show();

        SQLiteDatabase db= this.getWritableDatabase();
        db.delete("userinfo", null,null);
        //db.execSQL("DELETE * FROM tb1");

    }
    public Cursor selectUsers(Context context)
    {
        Cursor c ;
        SQLiteDatabase db =this.getReadableDatabase();
        c =db.rawQuery("SELECT * FROM userinfo ",null);


        return c ;
    }
}
