package java_lang_programming.com.android_reactivex_demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java_lang_programming.com.android_reactivex_demo.ui.AsyncSubjectActivity;
import java_lang_programming.com.android_reactivex_demo.ui.ObservableActivity;
import java_lang_programming.com.android_reactivex_demo.ui.ObservableMapActivity;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Button observable;
    private Button observable_map;
    private Button async_subject;
    private Button behavior_subject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button observable_map = (Button) findViewById(R.id.observable_map);
        observable_map.setOnClickListener(view -> moveObservableMap());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

//
//        async_subject.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                moveAsyncSubjectActivity();
//            }
//        });

        observable = (Button) findViewById(R.id.observable);
        observable.setOnClickListener(view1 -> {
            moveObservableActivity();
        });

//        async_subject = (Button) findViewById(R.id.async_subject);
//        async_subject.setOnClickListener(view -> {
//            moveAsyncSubjectActivity();
//        });


//        behavior_subject = (Button) findViewById(R.id.behavior_subject);
//        behavior_subject.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                moveAsyncSubjectActivity();
//            }
//        });


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
        getMenuInflater().inflate(R.menu.main, menu);
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
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void moveObservableActivity() {
        Intent intent = new Intent(this, ObservableActivity.class);
        startActivity(intent);
    }

    private void moveObservableMap() {
        Intent intent = new Intent(this, ObservableMapActivity.class);
        startActivity(intent);
    }

    private void moveAsyncSubjectActivity() {
        Intent intent = new Intent(this, AsyncSubjectActivity.class);
        startActivity(intent);
    }
}
