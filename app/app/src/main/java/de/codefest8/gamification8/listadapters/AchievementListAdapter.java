package de.codefest8.gamification8.listadapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import de.codefest8.gamification8.R;
import de.codefest8.gamification8.models.AchievementDTO;

public class AchievementListAdapter extends ArrayAdapter<AchievementDTO> {

    private final Context context;
    private final AchievementDTO[] values;

    public AchievementListAdapter(Context context, AchievementDTO[] values)
    {
        super(context, R.layout.element_achievementlist, values);
        this.context = context;
        this.values = values;
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.element_achievementlist, parent, false);
        TextView labelView = (TextView) view.findViewById(R.id.label);
        TextView descrView = (TextView) view.findViewById(R.id.description);
        labelView.setText(values[position].getName());
        descrView.setText(Integer.toString(values[position].getPoints()) + " points achieved in this achievement!");
        return view;
    }

}
