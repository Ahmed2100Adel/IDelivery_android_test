package com.example.yasmeen.nowaitressing1;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class signUp extends AppCompatActivity {
TextView input_name,input_email,input_password,input_confirmpassword;
    String textofname,textofemail,textofpassword,textofconfirmpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        input_name =(TextView)findViewById(R.id.input_name);
        input_email =(TextView)findViewById(R.id.input_email);
        input_password =(TextView)findViewById(R.id.input_password);
        input_confirmpassword =(TextView)findViewById(R.id.input_confirmpassword);
    }
    public void next(View view){
        textofname =input_name.getText().toString();
        textofemail =input_email.getText().toString();
        textofpassword =input_password.getText().toString();
        textofconfirmpassword =input_confirmpassword.getText().toString();
        if(!textofname.equals("")&& !textofemail.equals("")&&!textofpassword.equals("")&&!textofconfirmpassword.equals("")
                &&textofpassword.equals(textofconfirmpassword)&&textofemail.contains("@")
                ){


             }
         if (textofname.equals("")){
            //Toast.makeText(this, "please insert your name", Toast.LENGTH_SHORT).show();
             Snackbar.make(view, "please insert your name", Snackbar.LENGTH_LONG)
                     .setAction("Action", null).show();

         }
         if(textofemail.equals("")){
            //Toast.makeText(this, "please insert your email", Toast.LENGTH_SHORT).show();
             Snackbar.make(view, "please insert your email", Snackbar.LENGTH_LONG)
                     .setAction("Action", null).show();
        }
         if(textofpassword.equals("")){
           // Toast.makeText(this, "please insert your email ", Toast.LENGTH_SHORT).show();
             Snackbar.make(view, "please insert your password", Snackbar.LENGTH_LONG)
                     .setAction("Action", null).show();
        }
         if(textofconfirmpassword.equals("")){
            //Toast.makeText(this, "please confirm your password", Toast.LENGTH_SHORT).show();
             Snackbar.make(view, "please insert your cofirm Password", Snackbar.LENGTH_LONG)
                     .setAction("Action", null).show();
        }
        if(!textofemail.contains("@")){
            Snackbar.make(view, "this email is invalid", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }
    public void hasOne(View view){
        Intent i  = new Intent(this,LoginActivity.class);
        startActivity(i);
    }
}
