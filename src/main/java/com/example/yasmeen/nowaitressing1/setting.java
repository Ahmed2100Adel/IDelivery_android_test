package com.example.yasmeen.nowaitressing1;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

public class setting extends AppCompatActivity {
CheckBox cheklaod;
    externerl_database db;
    Cursor c ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        cheklaod =(CheckBox)findViewById(R.id.cheklaod);
        db=new externerl_database(this);
        c=db.selectloadimages(this);
        while(c.moveToNext()){
            if (c.getString(1).equals("true")){
                cheklaod.setChecked(true);
                Toast.makeText(this, "true", Toast.LENGTH_SHORT).show();
            }else{
                cheklaod.setChecked(false);
                Toast.makeText(this, "false", Toast.LENGTH_SHORT).show();
            }
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.save_setting, menu);
        return super.onCreateOptionsMenu(menu);

    }

    public void save(MenuItem item) {
        if(cheklaod.isChecked()){
          db.updateLoadImages(this,"true","1");
        }else{
          db.updateLoadImages(this,"false","1");

        }
        this.finish();
    }

}
