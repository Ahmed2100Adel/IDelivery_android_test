package com.example.yasmeen.nowaitressing1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    NavigationView navigationView;
    externerl_database db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        db=new externerl_database(this);
        setSupportActionBar(toolbar);
        checkFirstRun();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();
               Cursor c=db.selectUsers(Main2Activity.this);
                if(c.getCount()<1){
                    Snackbar.make(view, "please sign in first", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }else {
                    Intent i = new Intent(Main2Activity.this, liveChat.class);
                    startActivity(i);
                }
               // Intent i = new Intent(Main2Activity.this,liveChat.class);
                //startActivity(i);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

         navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            externerl_database db =new externerl_database(this);
            Cursor c ;
            c=db.selectUsers(this);
            if(c.getCount()<1){
                Toast.makeText(this, "please sign in first", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(this,LoginActivity.class);
                startActivity(i);
            }else {
                Intent i = new Intent(this, yourProfile.class);
                startActivity(i);
            }
        } else if (id == R.id.nav_history) {
            Intent i = new Intent(this,history.class);
            startActivity(i);

        } else if (id == R.id.nav_favorites) {
            Intent i = new Intent(this,myFavorites.class);
            startActivity(i);

        }
        else if (id == R.id.nav_setting) {
            Intent i = new Intent(this,setting.class);
            startActivity(i);

        } else if (id == R.id.nav_offer) {
            Intent i = new Intent(this,offers.class);
            startActivity(i);

        } else if (id == R.id.nav_share) {
           String message = "https://stackoverflow.com/questions/6814268/android-share-on-facebook-twitter-mail-ecc";
            Intent share = new Intent(Intent.ACTION_SEND);
            share.setType("text/plain");
            share.putExtra(Intent.EXTRA_TEXT, message);
            startActivity(Intent.createChooser(share, "share our app "));

        } else if (id == R.id.nav_help) {
            Intent i = new Intent(this,getHelpDelvelper.class);
            startActivity(i);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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

    @Override
    protected void onRestart() {
        super.onRestart();
        navigationView.setNavigationItemSelectedListener(this);

    }
    private void checkFirstRun() {

        final String PREFS_NAME = "MyPrefsFile";
        final String PREF_VERSION_CODE_KEY = "version_code";
        final int DOESNT_EXIST = -1;

        // Get current version code
        int currentVersionCode = BuildConfig.VERSION_CODE;

        // Get saved version code
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        int savedVersionCode = prefs.getInt(PREF_VERSION_CODE_KEY, DOESNT_EXIST);

        // Check for first run or upgrade
        if (currentVersionCode == savedVersionCode) {

            // This is just a normal run
            return;

        } else if (savedVersionCode == DOESNT_EXIST) {
            Toast.makeText(this, "should be first run", Toast.LENGTH_SHORT).show();
          db.insertloadimages(this);

            // TODO This is a new install (or the user cleared the shared preferences)

        } else if (currentVersionCode > savedVersionCode) {

            // TODO This is an upgrade
        }

        // Update the shared preferences with the current version code
        prefs.edit().putInt(PREF_VERSION_CODE_KEY, currentVersionCode).apply();
    }
}
