package com.example.yasmeen.nowaitressing1;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class yourProfile extends AppCompatActivity {
externerl_database db;
    Cursor c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_profile);
        db= new externerl_database(this);
      c=db.selectUsers(this);


    }
}
