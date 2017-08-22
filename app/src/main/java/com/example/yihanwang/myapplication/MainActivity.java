package com.example.yihanwang.myapplication;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private TextView topText;
    private TextView subText;
    private Typeface tf1;
    private Typeface tf2;
    private Typeface tf3;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
//    private ActionBarDrawerToggle mDrawerToggle;
    private String[] mPlanetTitles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_drawer);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        mPlanetTitles = getResources().getStringArray(R.array.planets_array);
        // Set the adapter for the list view
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, mPlanetTitles));
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        mDrawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerClosed(View view) {
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
                getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_drawer);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setHomeAsUpIndicator(null);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        });

        button = (Button) findViewById(R.id.mapButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent subscription = new Intent(getApplicationContext(), MapActivity.class);
                startActivity(subscription);

            }
        });

        topText = (TextView) findViewById(R.id.topTextView);
        subText = (TextView) findViewById(R.id.subTextView);
        tf1 = Typeface.createFromAsset(getAssets(), "Bauhaus-93.ttf");
        tf2 = Typeface.createFromAsset(getAssets(), "Abadi_MT Condensed_Light.ttf");
        tf3 = Typeface.createFromAsset(getAssets(), "Aclonica.ttf");
        topText.setTypeface(tf1);
        subText.setTypeface(tf2);
        button.setTypeface(tf3);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                mDrawerLayout.closeDrawer(GravityCompat.START);
            }
            else {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    /* The click listner for ListView in the navigation drawer */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//            selectItem(position);
//            System.out.println("xxx");
        }
    }
}
