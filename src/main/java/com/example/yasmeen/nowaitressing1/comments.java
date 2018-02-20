package com.example.yasmeen.nowaitressing1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class comments extends AppCompatActivity {
    private ListView lvProduct;
    private adabter_comments adapter;
    private List<product_comments> mProductList;
    String idOfComment;
    ProgressBar pb;
    TextView textOfComment;
    int idnum;
    String textcommentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        pb =(ProgressBar)findViewById(R.id.pb_comment) ;
        textOfComment=(TextView)findViewById(R.id.textOfCommentUser);
        lvProduct = (ListView) findViewById(R.id.listView_comment);
        mProductList = new ArrayList<>();

        Bundle b = getIntent().getExtras();
        idOfComment = b.getString("id");
        Toast.makeText(this, idOfComment, Toast.LENGTH_SHORT).show();
        pb.setVisibility(View.VISIBLE);
        stringRequest();


    }
    private void stringRequest() {

        String serever_url = "http://192.168.2.5/xampp/food/selectComments.php";


        try {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, serever_url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.contains("Undefined")) {
                                Toast.makeText(comments.this, "Undefined ", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(comments.this, "selected success", Toast.LENGTH_LONG).show();
                                pb.setVisibility(View.GONE);

                                showJson(response);
                            }
                        }


                    }
                    , new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(comments.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    Toast.makeText(comments.this, "no select", Toast.LENGTH_LONG).show();

                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("idoOfFood", idOfComment);
                    return params;
                }
            };
            MySingleTon.getMinstance(comments.this).addRequestQueuex(stringRequest);

        } catch (Exception e) {
            Toast.makeText(comments.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    private void showJson(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("result");
            for (int i = 0 ; i < jsonArray.length() ; i ++){
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
              //  Toast.makeText(this,jsonObject1.getString("comment") , Toast.LENGTH_SHORT).show();
                mProductList.add(new product_comments(String.valueOf(idnum++),jsonObject1.getString("comment")));

                // mProductList.add(new product(jsonObject1.getString("id"), jsonObject1.getString("name"), jsonObject1.getString("price"),jsonObject1.getString("info").toString(),jsonObject1.getString("url").toString(),
                 //       jsonObject1.getString("priceMedium").toString(),jsonObject1.getString("priceLarge").toString()));

            }

           adapter = new adabter_comments(getApplicationContext(), mProductList);
            lvProduct.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

public void sendComment(View view){
    textcommentUser =textOfComment.getText().toString();
    stringRequestAddingComment();
    textOfComment.setText("");
    //mProductList.clear();
   // lvProduct.invalidateViews();
    //adapter.getData().clear();
   // adapter.getData().addAll(objects);
    // fire the event
    //mProductList.clear();
    //stringRequest();
    //adapter.notifyDataSetChanged();

}
public void stringRequestAddingComment(){
    String serever_url1 = "http://192.168.2.5/xampp/food/insertComment.php";


    try {
        final StringRequest stringRequest = new StringRequest(Request.Method.POST, serever_url1,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.contains("Undefined")) {
                            Toast.makeText(comments.this, "Undefined ", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(comments.this, "your comment has been inserted", Toast.LENGTH_LONG).show();
                            pb.setVisibility(View.GONE);
                            mProductList.clear();
                            stringRequest();
                           // showJson(response);
                        }
                    }


                }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(comments.this, error.getMessage(), Toast.LENGTH_LONG).show();
                Toast.makeText(comments.this, "there was problem while uploading your comment", Toast.LENGTH_LONG).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("idoOfFood", idOfComment);
                params.put("comment",textcommentUser );
                params.put("senderName","Ahmed Adel" );
                params.put("senderEmail","Ahmed@gmail.com" );

                return params;
            }
        };
        MySingleTon.getMinstance(comments.this).addRequestQueuex(stringRequest);

    } catch (Exception e) {
        Toast.makeText(comments.this, e.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
}

