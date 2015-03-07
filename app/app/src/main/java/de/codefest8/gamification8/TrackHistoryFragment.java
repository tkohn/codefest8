package de.codefest8.gamification8;

import android.app.AlertDialog;
import android.support.v4.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import de.codefest8.gamification8.models.TripDTO;


public class TrackHistoryFragment extends ListFragment {
    AlertDialog loadingDataDialog;
    TripDTO[] trips;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        trips = new TripDTO[] { };
        TrackHistoryAdapter adapter = new TrackHistoryAdapter(this.getActivity(), trips);
        setListAdapter(adapter);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.dialog_loading_data).setTitle(R.string.dialog_loading_data);
        loadingDataDialog = builder.create();

        loadData();

        return inflater.inflate(R.layout.fragment_trackhistory, container, false);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        ((MainActivity)this.getActivity()).goToFragment(FragmentType.TrackDetail);
    }

    private void loadData() {
        loadingDataDialog.show();

        loadingDataDialog.dismiss();
    }
}
