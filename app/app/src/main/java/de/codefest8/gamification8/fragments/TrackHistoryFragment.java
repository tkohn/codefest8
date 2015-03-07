package de.codefest8.gamification8.fragments;

import android.app.AlertDialog;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import de.codefest8.gamification8.MainActivity;
import de.codefest8.gamification8.R;
import de.codefest8.gamification8.listadapters.TrackHistoryAdapter;
import de.codefest8.gamification8.models.TripDTO;


public class TrackHistoryFragment extends ListFragment {

    private AlertDialog loadingDataDialog;
    TripDTO[] trips;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        trips = new TripDTO[] { new TripDTO(), new TripDTO() };
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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        registerForContextMenu(this.getListView());
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = this.getActivity().getMenuInflater();
        inflater.inflate(R.menu.contextmenu_trackhistory, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.action_share:
                break;
            case R.id.action_export:
                break;
            case R.id.action_delete:
                break;
        }
        return true;
    }

    private void loadData() {
        loadingDataDialog.show();

    }
}
