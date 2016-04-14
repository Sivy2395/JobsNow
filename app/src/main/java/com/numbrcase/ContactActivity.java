package com.numbrcase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.test_2.R;

import java.util.ArrayList;

public class ContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        doTheThing();

    }

    private void doTheThing() {

        final ListView listview = (ListView) findViewById(R.id.profilelistview);
        String[] values = new String[] { "1", "2", "3",
                "4", "5"};

        final ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < values.length; ++i) {
            list.add(values[i]);
        }
        MyArrayAdapter adapter = new MyArrayAdapter(getApplicationContext(), values, false, R.layout.row_media);
        adapter.addContact(new Contact("Facebook", true));
        adapter.addContact(new Contact("Facebook", true));
        adapter.addContact(new Contact("Facebook", true));
        adapter.addContact(new Contact("Facebook", true));
        adapter.addContact(new Contact("Facebook", true));

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
