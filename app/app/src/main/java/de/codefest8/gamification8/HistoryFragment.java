package de.codefest8.gamification8;

import android.support.v4.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.GregorianCalendar;

import de.codefest8.gamification8.models.TripDTO;


public class HistoryFragment extends ListFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TripDTO[] tracks = new TripDTO[] { };
        HistoryElementAdapter adapter = new HistoryElementAdapter(this.getActivity(), tracks);
        setListAdapter(adapter);
        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Toast.makeText(this.getActivity(), "entry " + Integer.toString(position) + " clicked", Toast.LENGTH_SHORT).show();
    }
}
