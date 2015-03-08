package de.codefest8.gamification8.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import de.codefest8.gamification8.GlobalState;
import de.codefest8.gamification8.MainActivity;
import de.codefest8.gamification8.R;
import de.codefest8.gamification8.UserMessagesHandler;
import de.codefest8.gamification8.listadapters.TrackHistoryAdapter;
import de.codefest8.gamification8.models.TripDTO;
import de.codefest8.gamification8.network.ResponseCallback;
import de.codefest8.gamification8.network.TripsResolver;


public class TrackHistoryFragment extends ListFragment {
    private static final String LOG_TAG = "TrackHistoryFragment";
    private TripDTO[] trips;
    private AlertDialog loadingDataDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.dialog_loading_data).setTitle(R.string.dialog_loading_data);
        loadingDataDialog = builder.create();

        loadData();

        return inflater.inflate(R.layout.fragment_trackhistory, container, false);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        GlobalState.getInstance().setTrip(trips[position]);
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
        final AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId())
        {
            case R.id.action_share:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Sharing my AixCruise trip with you #codeFEST8");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
                break;
            case R.id.action_save:

                break;
            case R.id.action_delete:
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Warning");
                builder.setMessage("Do you want to delete the trip with id '" + trips[info.position].getId() + "'?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "Trip with id '" + trips[info.position].getId() + "' deleted!", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.create().show();
                break;
        }
        return true;
    }

    public void fillInTripsData() {
        TrackHistoryAdapter adapter = new TrackHistoryAdapter(this.getActivity(), trips);
        setListAdapter(adapter);
    }

    class UserTripsResolverCallback implements ResponseCallback {

        @Override
        public void success(JSONObject response) {

        }

        @Override
        public void successArray(JSONArray response) {
            trips = new TripDTO[response.length()];
            try {
                for (int i = 0; i < response.length(); i++) {
                    JSONObject object = response.getJSONObject(i);
                    trips[i] = TripDTO.fromJson(object);
                }
            } catch (JSONException ex) {
                UserMessagesHandler.getInstance().registerError("Error while parsing trips list response.");
                Log.e(LOG_TAG, ex.toString());
            }
            loadingDataDialog.dismiss();
            fillInTripsData();
        }

        @Override
        public void fail(int code, String message) {
            loadingDataDialog.dismiss();
            UserMessagesHandler.getInstance().registerError("Could not load trips list.");
        }
    }

    private void loadData() {
        loadingDataDialog.show();
        TripsResolver resolver = new TripsResolver(new UserTripsResolverCallback(), GlobalState.getInstance().getUser());
        resolver.doRequestArray();
    }
}
