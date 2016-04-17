package com.numbrcase;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.test_2.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.github.yavski.fabspeeddial.FabSpeedDial;
import io.github.yavski.fabspeeddial.SimpleMenuListenerAdapter;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        configureRequestListView();
        configureContactListView();

        FabSpeedDial fabSpeedDial = (FabSpeedDial) findViewById(R.id.fab_speed_dial);
        fabSpeedDial.setMenuListener(new SimpleMenuListenerAdapter() {
            @Override
            public boolean onMenuItemSelected(MenuItem menuItem) {

                if (menuItem.getItemId() == R.id.add) {

                    Intent intent = new Intent(getApplicationContext(), AddContactActivity.class);
                    startActivity(intent);

                }
                else if (menuItem.getItemId() == R.id.search_nearby) {

                    Intent intent = new Intent(getApplicationContext(), SearchNearbyActivity.class);
                    startActivity(intent);

                }

                return false;
            }
        });

    }

    private void configureRequestListView() {

        final ListView listview = (ListView) findViewById(R.id.requestlistview);
//        String[] values = new String[] { "1", "2", "3",
//                "4", };
//
//        final ArrayList<String> list = new ArrayList<>();
//        for (int i = 0; i < values.length; ++i) {
//            list.add(values[i]);
//        }

        List<Contact> values = new ArrayList<Contact>();
        values.add(new Contact("Bill Gates", true));
        values.add(new Contact("Muhammad Ali", true));
        values.add(new Contact("Charles Darwin", true));
        values.add(new Contact("Elvis Presley", true));

        MyArrayAdapter adapter = new MyArrayAdapter(this, values, R.layout.row_request);
//        adapter.addContact(new Contact("Bill Gates", true));
//        adapter.addContact(new Contact("Muhammad Ali", true));
//        adapter.addContact(new Contact("Charles Darwin", true));
//        adapter.addContact(new Contact("Elvis Presley", true));

        listview.setAdapter(adapter);

        updateListViewSize(listview);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {

                    Contact cTest = new Contact("Leandro");

                    Intent intent = new Intent(getApplicationContext(), ContactActivity.class);
                    intent.putExtra("here", cTest);

                    startActivity(intent);
            }
        });

    }


    private void configureContactListView() {

        final ListView listview = (ListView) findViewById(R.id.contactlistview);
//        String[] values = new String[] {"1", "2",
//                "3", "4", "5", "6", "7", "8", "9",
//                "10" };
//
//        final ArrayList<String> list = new ArrayList<>();
//        for (int i = 0; i < values.length; ++i) {
//            list.add(values[i]);
//        }


        List<Contact> values = new ArrayList<Contact>();
        values.add(new Contact("George Thiruvathukal", "Requested in Roger Parks, IL on 10/1/2015", false));
        values.add(new Contact("Albert Einstein", "Requested in Roger Parks, IL on 10/1/2015", false));
        values.add(new Contact("Paul McCartney", "Requested in Roger Parks, IL on 10/1/2015", false));
        values.add(new Contact("Leonardo da Vinci", "Requested in Roger Parks, IL on 10/1/2015", false));
        values.add(new Contact("Dalai Lama", "Requested in Roger Parks, IL on 10/1/2015", false));
        values.add(new Contact("Neil Armstrong", "Requested in Roger Parks, IL on 10/1/2015", false));
        values.add(new Contact("Donald Trump", "Requested in Roger Parks, IL on 10/1/2015", false));
        values.add(new Contact("Barack Obama", "Requested in Roger Parks, IL on 10/1/2015", false));
        values.add(new Contact("Steve Jobs", "Requested in Roger Parks, IL on 10/1/2015", false));
        values.add(new Contact("J.K.Rowling", "Requested in Roger Parks, IL on 10/1/2015", false));


        MyArrayAdapter adapter = new MyArrayAdapter(this, values, R.layout.row_contact);

        listview.setAdapter(adapter);

        updateListViewSize(listview);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {

                Contact cTest = new Contact("Leandro");

                Intent intent = new Intent(getApplicationContext(), ContactActivity.class);
                intent.putExtra("here", cTest);

                startActivity(intent);
            }
        });

    }

    private void updateListViewSize(ListView listview) {
        int totalHeight = 0;
        for (int i = 0; i < listview.getAdapter().getCount(); i++) {
            View listItem = listview.getAdapter().getView(i, null, listview);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listview.getLayoutParams();
        params.height = totalHeight + (listview.getDividerHeight() * (listview.getCount() - 1));
        listview.setLayoutParams(params);
        listview.requestLayout();

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
        /*if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.account) {
            // Handle the camera action
        } else if (id == R.id.settings) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
