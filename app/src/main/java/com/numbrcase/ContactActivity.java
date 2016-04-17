package com.numbrcase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.test_2.R;

import java.util.ArrayList;
import java.util.List;

public class ContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        doTheThing();

    }

    private void doTheThing() {

        final ListView listview = (ListView) findViewById(R.id.profilelistview);

        List<Contact> values = new ArrayList<Contact>();
        values.add(new Contact("George Thiruvathukal", "Requested in Roger Parks, IL on 10/1/2015", false));
        values.add(new Contact("Albert Einstein", "Requested in Roger Parks, IL on 10/1/2015", false));
        values.add(new Contact("Paul McCartney", "Requested in Roger Parks, IL on 10/1/2015", false));
        values.add(new Contact("Leonardo da Vinci", "Requested in Roger Parks, IL on 10/1/2015", false));
        values.add(new Contact("Dalai Lama", "Requested in Roger Parks, IL on 10/1/2015", false));


        MyArrayAdapter adapter = new MyArrayAdapter(this, values, R.layout.row_media);
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
        listview.setFocusable(false);
    }

}
