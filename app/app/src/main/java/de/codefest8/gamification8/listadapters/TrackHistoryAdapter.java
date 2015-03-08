package de.codefest8.gamification8.listadapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import de.codefest8.gamification8.R;
import de.codefest8.gamification8.models.TripDTO;

public class TrackHistoryAdapter extends ArrayAdapter<TripDTO> {

    private final Context context;
    private final TripDTO[] values;

    public TrackHistoryAdapter(Context context, TripDTO[] values)
    {
        super(context, R.layout.element_trackhistory, values);
        this.context = context;
        this.values = values;
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.element_trackhistory, parent, false);
        TextView textView = (TextView) view.findViewById(R.id.history_row_label);
        textView.setText("dummy date");
        return view;
    }
}
