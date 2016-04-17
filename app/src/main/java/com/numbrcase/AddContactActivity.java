package com.numbrcase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;

import com.test_2.R;

import java.util.ArrayList;
import java.util.List;

public class AddContactActivity extends AppCompatActivity {

    private ListView listview;
    List<Contact> values = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_contact, menu);
        return true;
    }

    public void openSocialMedias(View view) {
        PopupMenu popup = new PopupMenu(this, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.social_media_menu, popup.getMenu());
        popup.show();

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int socialMediaID = 0;

                switch (item.getItemId()) {
                    case R.id.facebook:
                        socialMediaID = SocialMediaIDs.FACEBOOK;
                        break;

                    case R.id.instagram:
                        socialMediaID = SocialMediaIDs.INSTAGRAM;
                        break;

                    case R.id.twitter:
                        socialMediaID = SocialMediaIDs.TWITTER;
                        break;
                }


                doTheThing(socialMediaID);
                return true;
            }
        });

    }


    public void doTheThing(int socialMediaID) {

        listview = (ListView) findViewById(R.id.social_medias_list_view);

        SocialMedia socialMedia = new SocialMedia(socialMediaID);
        Contact contact = new Contact("George Thiruvathukal", "Requested in Roger Parks, IL on 10/1/2015", false);
        contact.addSocialMedia(socialMedia);

        values.add(contact);

        MyArrayAdapter adapter = new MyArrayAdapter(this, values, R.layout.row_add_media);
        listview.setAdapter(adapter);

        updateListViewSize(listview);
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

}
