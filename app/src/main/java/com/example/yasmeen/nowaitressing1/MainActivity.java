package com.example.yasmeen.nowaitressing1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
Button order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        order = (Button) findViewById(R.id.order);
    }
    public void order(View view) {
        Intent i =new Intent(this,theMenu.class);
        Bundle b = new Bundle();
        b.putString("category", "meet");
        i.putExtras(b);
        startActivity(i);

    }
    public void delivery (View view){
        Intent i = new Intent(this,delivery.class);

        startActivity(i);
    }
}
