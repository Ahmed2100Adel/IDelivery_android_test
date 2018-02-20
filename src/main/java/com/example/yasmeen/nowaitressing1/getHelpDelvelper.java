package com.example.yasmeen.nowaitressing1;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class getHelpDelvelper extends AppCompatActivity {
ProgressBar pb_getHelp;
    EditText massage_getHelp,advantages,disadvantages;
String txtMasage,txtadvan,txtdisadvan;
    externerl_database db;
    Cursor c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_help_delvelper);
        pb_getHelp =(ProgressBar)findViewById(R.id.pb_getHelp);
        massage_getHelp =(EditText) findViewById(R.id.massage_getHelp);
        advantages =(EditText) findViewById(R.id.advantages);
        disadvantages =(EditText) findViewById(R.id.disadvantages);
        db= new externerl_database(this);

    }
    public void send_to_developer(View view){
        pb_getHelp.setVisibility(View.VISIBLE);
        txtMasage =massage_getHelp.getText().toString();
        txtadvan = advantages.getText().toString();
        txtdisadvan=disadvantages .getText().toString();
        c=db.SelectnumsOfOrders(this);
        stringRequestAddingMassage();
        pb_getHelp.setVisibility(View.GONE);
        massage_getHelp.setText("");
        advantages.setText("");
        disadvantages.setText("");
        this.finish();
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.back_developer,menu);
        return super.onCreateOptionsMenu(menu);
    }
    public void back(MenuItem item)
    {
        this.finish();
    }


    public void stringRequestAddingMassage(){
        String serever_url1 = "http://192.168.2.5/xampp/food/insertMassageToDeveloper.php";


        try {
            final StringRequest stringRequest = new StringRequest(Request.Method.POST, serever_url1,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.contains("Undefined")) {
                                Toast.makeText(getHelpDelvelper.this, "Undefined ", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(getHelpDelvelper.this, "your massage has been sent", Toast.LENGTH_LONG).show();
                                pb_getHelp.setVisibility(View.GONE);

                                // showJson(response);
                            }
                        }


                    }
                    , new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getHelpDelvelper.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    Toast.makeText(getHelpDelvelper.this, "no select", Toast.LENGTH_LONG).show();

                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("massage",txtMasage );
                    params.put("advantages", txtadvan);
                    params.put("disadvantages",txtdisadvan );
                    params.put("nameOfSender","Ahmed Adel");
                    params.put("emailOfSender","ahmed2368@gmail.com");
                    params.put("numsOfOrders",String.valueOf(c.getCount()));

                    return params;
                }
            };
            MySingleTon.getMinstance(getHelpDelvelper.this).addRequestQueuex(stringRequest);

        } catch (Exception e) {
            Toast.makeText(getHelpDelvelper.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}


