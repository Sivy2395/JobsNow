package com.numbrcase.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.numbrcase.model.ContactImpl;
import com.numbrcase.model.MediaArrayAdapter;
import com.numbrcase.model.SocialMedia;
import com.numbrcase.model.SocialMediaImpl;
import com.test_2.R;

import java.util.List;

public class ContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        ContactImpl contact = (ContactImpl) getIntent().getSerializableExtra("contact");
        List<SocialMedia> socialMedias = contact.getSocialMedias();

        showContactInformation(contact);
        showMediasInformation(socialMedias);
    }

    private void showContactInformation(ContactImpl contact) {
        ((TextView)findViewById(R.id.contact_name)).setText(contact.getName());
        ((TextView)findViewById(R.id.phone_number)).setText(contact.getPhone());
    }

    private void showMediasInformation(List<SocialMedia> socialMedias) {

        final ListView listview = (ListView) findViewById(R.id.profilelistview);

        MediaArrayAdapter adapter = new MediaArrayAdapter(this, socialMedias, R.layout.row_media);
        listview.setAdapter(adapter);
        updateListViewSize(listview);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {

                SocialMediaImpl socialMedia = ((SocialMediaImpl) listview.getAdapter().getItem(position));

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(socialMedia.getLink()));
                startActivity(browserIntent);
            }
        });

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
        listview.setFocusable(false);
    }

}
