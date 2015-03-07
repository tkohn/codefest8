package de.codefest8.gamification8;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by koerfer on 07.03.2015.
 */
public class DrawerElementAdapter extends ArrayAdapter<DrawerElement> {

    private final Context Context;
    private final DrawerElement[] Values;

    public DrawerElementAdapter(Context context, DrawerElement[] values)
    {
        super(context, R.layout.drawer_list_item, values);
        this.Context = context;
        this.Values = values;
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        DrawerElement currentElement = this.Values[position];
        LayoutInflater inflater = (LayoutInflater) this.Context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.drawer_list_item, parent, false);
        ImageView imgView = (ImageView) view.findViewById(R.id.icon);
        imgView.setImageResource(currentElement.getImageResourceID());
        TextView txtView = (TextView) view.findViewById(R.id.label);
        txtView.setText(currentElement.getLabel());
        return view;
    }

}
