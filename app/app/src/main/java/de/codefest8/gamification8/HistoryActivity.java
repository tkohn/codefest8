package de.codefest8.gamification8;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import de.codefest8.gamification8.models.Track;


public class HistoryActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        Track[] tracks = new Track[] {};
        HistoryElementAdapter adapter = new HistoryElementAdapter(this, tracks);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Toast.makeText(this, "entry " + Integer.toString(position) + " clicked", Toast.LENGTH_SHORT).show();
    }
}
