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

public class MyArrayAdapter extends ArrayAdapter<Contact> {
    private final Context context;
    private final List<Contact> values;

    private List<Contact> contacts = new ArrayList<>();

    private int layout;

    public MyArrayAdapter(Context context, List<Contact> values, int layout) {
        super(context, -1, values);
        this.context = context;
        this.values = values;

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

        switch (layout) {
            case R.layout.row_contact:
                ((TextView)  rowView.findViewById(R.id.contact_name)).setText(values.get(position).getName());
                ((ImageView) rowView.findViewById(R.id.icon)        ).setImageResource(R.drawable.ic_menu_camera);
                break;

            case R.layout.row_request:
                ((TextView) rowView.findViewById(R.id.request_name)).setText(values.get(position).getName());
                ((ImageView) rowView.findViewById(R.id.icon)).setImageResource(R.drawable.ic_menu_camera);
                break;

            case R.layout.row_media:
                ((TextView) rowView.findViewById(R.id.media_name)).setText(values.get(position).getName());
                ((TextView) rowView.findViewById(R.id.media_ID)).setText(values.get(position).getRequestPlace());
                ((ImageView) rowView.findViewById(R.id.icon)).setImageResource(R.drawable.my_ic_facebook);
                break;

            case R.layout.row_add_media:
//                ((TextView) rowView.findViewById(R.id.media_name)).setText(values.get(position).getName());
//                ((TextView) rowView.findViewById(R.id.media_ID)).setText(values.get(position).getRequestPlace());

                values.get(position).getSocialMedias().get(0);

                ((ImageView) rowView.findViewById(R.id.social_media_icon)).setImageResource(values.get(position).getSocialMedias().get(0).getMediaID());
                break;
        }

        return rowView;
    }
}
