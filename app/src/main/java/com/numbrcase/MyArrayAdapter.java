package com.numbrcase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.test_2.R;

import java.util.ArrayList;
import java.util.List;

public class MyArrayAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] values;

    private List<Contact> contacts = new ArrayList<>();

    private boolean added;

    private int layout;

    public MyArrayAdapter(Context context, String[] values, boolean added, int layout) {
        super(context, -1, values);
        this.context = context;
        this.values = values;

        this.added = added;
        this.layout = layout;
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(layout, parent, false);;

        if (layout == R.layout.row_contact){
            TextView textView = (TextView) rowView.findViewById(R.id.contact_name);
            ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
            textView.setText(contacts.get(position).getName());

            String s = values[position];
            imageView.setImageResource(R.drawable.ic_menu_camera);
        }
        else if (layout == R.layout.row_request){
            TextView textView = (TextView) rowView.findViewById(R.id.request_name);
            ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
            textView.setText(contacts.get(position).getName());

            String s = values[position];
            imageView.setImageResource(R.drawable.ic_menu_camera);
        }
        else if (layout == R.layout.row_media) {
            TextView textView = (TextView) rowView.findViewById(R.id.media_name);
            ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
            textView.setText(contacts.get(position).getName());

            String s = values[position];
            imageView.setImageResource(R.drawable.my_ic_facebook);
        }

        return rowView;
    }
}
