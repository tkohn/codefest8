package de.codefest8.gamification8;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import de.codefest8.gamification8.models.UserDTO;

public class FriendsListAdapter extends ArrayAdapter<UserDTO> {

    private final Context context;
    private final UserDTO[] values;

    public FriendsListAdapter(Context context, UserDTO[] values) {
        super(context, R.layout.row_history, values);
        this.context = context;
        this.values = values;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.row_history, parent, false);
        TextView textView = (TextView) view.findViewById(R.id.label);
        textView.setText(values[position].getName());
        return view;
    }
}
