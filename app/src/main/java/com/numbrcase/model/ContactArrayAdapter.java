package com.numbrcase.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.test_2.R;

import java.util.List;

/**
 * Adater that will manipulate the content of a Listview
 */
public class ContactArrayAdapter extends ArrayAdapter<Contact> {

    private final Context context;
    private final List<Contact> values;

    private int layout;

    public ContactArrayAdapter(Context context, List<Contact> values, int layout) {
        super(context, -1, values);
        this.context = context;

        this.values = values;
        this.layout = layout;
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

        }

        return rowView;
    }
}
