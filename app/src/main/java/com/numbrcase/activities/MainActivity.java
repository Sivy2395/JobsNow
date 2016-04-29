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


        insertMyAccount();
    }

    /** Insert my own contact into the database */
    private void insertMyAccount() {
        ContactDB contactDB = new ContactDB(this);
        if (contactDB.numberOfRows() == 0) {
            Contact myself = new ContactImpl();

            // Android 6.0 permission manager sucks
            // TelephonyManager tMgr = (TelephonyManager)getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
            // myself.setPhone(tMgr.getLine1Number());

            contactDB.insertContact(myself);

            // TODO: delete me when the app is released
            insertContactsForTesting();
        }
    }

    /** Contacts inserted to test the application */
    private void insertContactsForTesting() {
        ContactDB contactDB = new ContactDB(this);

        // ********* REQUESTS ********* //
        List<SocialMedia> reqSMedias = new ArrayList<>();
        reqSMedias.add(new SocialMediaImpl(SocialMediaIDs.FACEBOOK , "faceID"));
        reqSMedias.add(new SocialMediaImpl(SocialMediaIDs.INSTAGRAM, "instaID"));
        reqSMedias.add(new SocialMediaImpl(SocialMediaIDs.LINKEDIN , "linkID"));
        reqSMedias.add(new SocialMediaImpl(SocialMediaIDs.TWITTER  , "twitterID"));

        List<Contact> reqContacts = new ArrayList<>();
        reqContacts.add(new ContactImpl("Bill Gates", "Requested in Roger Parks, IL on 10/1/2015", Contact.REQUESTED, reqSMedias, "+1 773 987 1921"));
        reqContacts.add(new ContactImpl("Muhammad Ali", "Requested in Roger Parks, IL on 10/1/2015", Contact.REQUESTED, reqSMedias, "+1 773 987 1922"));
        reqContacts.add(new ContactImpl("Charles Darwin", "Requested in Roger Parks, IL on 10/1/2015", Contact.REQUESTED, reqSMedias, "+1 773 987 1923"));
        reqContacts.add(new ContactImpl("Elvis Presley", "Requested in Roger Parks, IL on 10/1/2015", Contact.REQUESTED, reqSMedias, "+1 773 987 1924"));

        for (Contact c : reqContacts)
            contactDB.insertContact(c);

        // ********* CONTACTS ********* //
        List<SocialMedia> contSMedias = new ArrayList<>();
        contSMedias.add(new SocialMediaImpl(SocialMediaIDs.FACEBOOK , "faceID"));
        contSMedias.add(new SocialMediaImpl(SocialMediaIDs.INSTAGRAM, "instaID"));
        contSMedias.add(new SocialMediaImpl(SocialMediaIDs.LINKEDIN , "jpcqseventos"));
        contSMedias.add(new SocialMediaImpl(SocialMediaIDs.TWITTER  , "twitterID"));

        List<Contact> myContacts = new ArrayList<>();
        myContacts.add(new ContactImpl("George Thiruvathukal", "Requested in Roger Parks, IL on 10/1/2015", Contact.ADDED, contSMedias, "+1 773 987 1921"));
        myContacts.add(new ContactImpl("Albert Einstein", "Requested in Roger Parks, IL on 10/1/2015", Contact.ADDED, contSMedias, "+1 773 987 1922"));
        myContacts.add(new ContactImpl("Paul McCartney", "Requested in Roger Parks, IL on 10/1/2015", Contact.ADDED, contSMedias, "+1 773 987 1923"));
        myContacts.add(new ContactImpl("Leonardo da Vinci", "Requested in Roger Parks, IL on 10/1/2015", Contact.ADDED, contSMedias, "+1 773 987 1924"));
        myContacts.add(new ContactImpl("Dalai Lama", "Requested in Roger Parks, IL on 10/1/2015", Contact.ADDED, contSMedias, "+1 773 987 1925"));

        for (Contact c : myContacts)
            contactDB.insertContact(c);
    }

    @Override
    protected void onStart() {
        super.onStart();
        configureRequestListView();
        configureContactListView();
    }

    private void configureRequestListView() {
        ContactDB contactDB = new ContactDB(this);

        requestLV = (ListView) findViewById(R.id.requestlistview);

        List<Contact> requests = contactDB.getAllContactsByStatus(Contact.REQUESTED);

        ContactArrayAdapter adapter = new ContactArrayAdapter(this, requests, R.layout.row_request);

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
        ContactDB contactDB = new ContactDB(this);

        contactLV = (ListView) findViewById(R.id.contactlistview);

        List<Contact> myContacts = contactDB.getAllContactsByStatus(Contact.ADDED);

        ContactArrayAdapter adapter = new ContactArrayAdapter(this, myContacts, R.layout.row_contact);

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
            Intent intent = new Intent(getApplicationContext(), MyAccountActivity.class);
            Contact myself = new ContactDB(this).getData(1); // My account is the first row in the db

            intent.putExtra("contact", myself);
            startActivity(intent);
        } else if (id == R.id.settings) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
