package com.numbrcase.activities;

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

import com.numbrcase.common.SocialMediaIDs;
import com.numbrcase.dao.ContactDB;
import com.numbrcase.model.Contact;
import com.numbrcase.model.ContactImpl;
import com.numbrcase.model.ContactArrayAdapter;
import com.numbrcase.model.SocialMedia;
import com.numbrcase.model.SocialMediaImpl;
import com.test_2.R;

import java.util.ArrayList;
import java.util.List;

import io.github.yavski.fabspeeddial.FabSpeedDial;
import io.github.yavski.fabspeeddial.SimpleMenuListenerAdapter;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ListView contactLV;
    private ListView requestLV;

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

        // Add and Search Nearby buttons
        FabSpeedDial fabSpeedDial = (FabSpeedDial) findViewById(R.id.fab_speed_dial);
        fabSpeedDial.setMenuListener(new SimpleMenuListenerAdapter() {
            @Override
            public boolean onMenuItemSelected(MenuItem menuItem) {

                if (menuItem.getItemId() == R.id.add)
                    startActivity(new Intent(getApplicationContext(), AddContactActivity.class));

                else if (menuItem.getItemId() == R.id.search_nearby)
                    startActivity(new Intent(getApplicationContext(), SearchNearbyActivity.class));

                return false;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        configureRequestListView();
        configureContactListView();
    }

    private void configureRequestListView() {

        requestLV = (ListView) findViewById(R.id.requestlistview);

        List<SocialMedia> sMedias = new ArrayList<>();
        sMedias.add(new SocialMediaImpl(SocialMediaIDs.FACEBOOK , "faceID"));
        sMedias.add(new SocialMediaImpl(SocialMediaIDs.INSTAGRAM, "instaID"));
        sMedias.add(new SocialMediaImpl(SocialMediaIDs.LINKEDIN , "jpcqseventos"));
        sMedias.add(new SocialMediaImpl(SocialMediaIDs.TWITTER  , "twitterID"));

        List<Contact> values = new ArrayList<>();
        values.add(new ContactImpl("Bill Gates", "Requested in Roger Parks, IL on 10/1/2015", Contact.REQUESTED, sMedias, "+1 773 987 1921"));
        values.add(new ContactImpl("Muhammad Ali", "Requested in Roger Parks, IL on 10/1/2015", Contact.REQUESTED, sMedias, "+1 773 987 1922"));
        values.add(new ContactImpl("Charles Darwin", "Requested in Roger Parks, IL on 10/1/2015", Contact.REQUESTED, sMedias, "+1 773 987 1923"));
        values.add(new ContactImpl("Elvis Presley", "Requested in Roger Parks, IL on 10/1/2015", Contact.REQUESTED, sMedias, "+1 773 987 1924"));


        ContactArrayAdapter adapter = new ContactArrayAdapter(this, values, R.layout.row_request);

        requestLV.setAdapter(adapter);

        requestLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {

                Contact contact = ((Contact) requestLV.getAdapter().getItem(position));

                Intent intent = new Intent(getApplicationContext(), AcceptRequestActivity.class);
                intent.putExtra("contact", contact);

                startActivity(intent);
            }
        });

        updateListViewSize(requestLV);
    }


    private void configureContactListView() {

        contactLV = (ListView) findViewById(R.id.contactlistview);

        List<SocialMedia> sMedias = new ArrayList<>();
        sMedias.add(new SocialMediaImpl(SocialMediaIDs.FACEBOOK , "faceID"));
        sMedias.add(new SocialMediaImpl(SocialMediaIDs.INSTAGRAM, "instaID"));
        sMedias.add(new SocialMediaImpl(SocialMediaIDs.LINKEDIN , "jpcqseventos"));
        sMedias.add(new SocialMediaImpl(SocialMediaIDs.TWITTER  , "twitterID"));

        List<Contact> values = new ArrayList<>();
        values.add(new ContactImpl("George Thiruvathukal", "Requested in Roger Parks, IL on 10/1/2015", Contact.ADDED, sMedias, "+1 773 987 1921"));
        values.add(new ContactImpl("Albert Einstein", "Requested in Roger Parks, IL on 10/1/2015", Contact.ADDED, sMedias, "+1 773 987 1922"));
        values.add(new ContactImpl("Paul McCartney", "Requested in Roger Parks, IL on 10/1/2015", Contact.ADDED, sMedias, "+1 773 987 1923"));
        values.add(new ContactImpl("Leonardo da Vinci", "Requested in Roger Parks, IL on 10/1/2015", Contact.ADDED, sMedias, "+1 773 987 1924"));
        values.add(new ContactImpl("Dalai Lama", "Requested in Roger Parks, IL on 10/1/2015", Contact.ADDED, sMedias, "+1 773 987 1925"));

        // Get data from database
        ContactDB contactDB = new ContactDB(this);
        List<Contact> contacts = contactDB.getAllContacts(getApplicationContext());
        values.addAll(contacts);

        ContactArrayAdapter adapter = new ContactArrayAdapter(this, values, R.layout.row_contact);

        contactLV.setAdapter(adapter);

        contactLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {

                ContactImpl contact = ((ContactImpl) contactLV.getAdapter().getItem(position));

                Intent intent = new Intent(getApplicationContext(), ContactActivity.class);
                intent.putExtra("contact", contact);

                startActivity(intent);
            }
        });

        updateListViewSize(contactLV);
    }

    /**
     * Method required to expand a ListView since Android do not support a ListView inside
     * a ScrollView
     */
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
