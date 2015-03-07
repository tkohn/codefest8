package de.codefest8.gamification8;

import android.app.AlertDialog;
import android.support.v4.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import de.codefest8.gamification8.models.TripDTO;


public class HistoryFragment extends ListFragment {
    AlertDialog loadingDataDialog;
    TripDTO[] trips;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        trips = new TripDTO[] { };
        HistoryElementAdapter adapter = new HistoryElementAdapter(this.getActivity(), trips);
        setListAdapter(adapter);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.dialog_loading_data).setTitle(R.string.dialog_loading_data);
        loadingDataDialog = builder.create();

        loadData();

        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Toast.makeText(this.getActivity(), "entry " + Integer.toString(position) + " clicked", Toast.LENGTH_SHORT).show();
    }

    private void loadData() {
        loadingDataDialog.show();

        loadingDataDialog.dismiss();
    }
}
