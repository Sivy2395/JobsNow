package com.numbrcase.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.PopupMenu;

import com.numbrcase.common.SocialMediaIDs;
import com.numbrcase.model.MediaArrayAdapter;
import com.numbrcase.model.SocialMedia;
import com.test_2.R;

import java.util.ArrayList;
import java.util.List;

public class AddContactActivity extends AppCompatActivity {

    private ListView listview;

    List<SocialMedia> socialMedias = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        listview = (ListView) findViewById(R.id.social_medias_list_view);
        MediaArrayAdapter adapter = new MediaArrayAdapter(this, new ArrayList<SocialMedia>(), R.layout.row_add_media);
        listview.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_contact, menu);
        return true;
    }

    /**
     * Method called whenever the button "Add Social Media" is pressed
     */
    public void openSocialMedias(View view) {
        PopupMenu popup = new PopupMenu(this, view);

        // Populate social_media_menu dinamically (maybe later)
        List<Integer> mediaIDs = SocialMediaIDs.getSocialMediaIDs();
        for (int i = 0; i < mediaIDs.size(); i++) {
            boolean mediaNotAlreadyAdded = true;

            if (listview != null) {
                for (int j = 0; j < listview.getAdapter().getCount(); j++) {
                    // If the social media have already been added into the list
                    if (mediaIDs.get(i) == ((SocialMedia) listview.getAdapter().getItem(j)).getMediaID()) {
                        mediaNotAlreadyAdded = false;
                        break;
                    }
                }
            }

            // If the media was not added or the listview was not created yet
            if (listview == null || mediaNotAlreadyAdded)
                popup.getMenu().add(0, mediaIDs.get(i), 0, SocialMediaIDs.getName(mediaIDs.get(i)));
        }

        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.social_media_menu, popup.getMenu());
        popup.show();

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                addMedia(item.getItemId());
                return true;
            }
        });

    }


    public void addMedia(int socialMediaID) {

        socialMedias.add(new SocialMedia(socialMediaID));
        MediaArrayAdapter adapter = new MediaArrayAdapter(this, socialMedias, R.layout.row_add_media);
        listview.setAdapter(adapter);

        updateListViewSize(listview);
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

}
