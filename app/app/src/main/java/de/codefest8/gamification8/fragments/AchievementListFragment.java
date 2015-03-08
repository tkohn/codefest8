package de.codefest8.gamification8.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import de.codefest8.gamification8.GlobalState;
import de.codefest8.gamification8.R;
import de.codefest8.gamification8.UserMessagesHandler;
import de.codefest8.gamification8.listadapters.AchievementListAdapter;
import de.codefest8.gamification8.models.AchievementDTO;
import de.codefest8.gamification8.models.TripDTO;
import de.codefest8.gamification8.network.AchievementsResolver;
import de.codefest8.gamification8.network.ResponseCallback;
import de.codefest8.gamification8.network.TripsResolver;

public class AchievementListFragment extends ListFragment {
    private static final String LOG_TAG = "AchievementListFragment";

    private AchievementDTO[] achievements;
    private AlertDialog loadingDataDialog;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.dialog_loading_data).setTitle(R.string.dialog_loading_data);
        loadingDataDialog = builder.create();

        loadData();

        return inflater.inflate(R.layout.fragment_achievementlist, container, false);
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
        inflater.inflate(R.menu.contextmenu_achievementlist, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        final AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId())
        {
            case R.id.action_share:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Sharing my AixCruise achievement with you #codeFEST8");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
                break;
            case R.id.action_reset:
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Warning");
                builder.setMessage("Do you want to reset the achievement" + achievements[info.position].getName() + "?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "Achievement '" + achievements[info.position].getName() + "' reset!", Toast.LENGTH_SHORT).show();
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

    class AchievementsResolverCallback implements ResponseCallback {

        @Override
        public void success(JSONObject response) {

        }

        @Override
        public void successArray(JSONArray response) {
            achievements = new AchievementDTO[response.length()];
            try {
                for (int i = 0; i < response.length(); i++) {
                    JSONObject object = response.getJSONObject(i);
                    achievements[i] = AchievementDTO.fromJson(object);
                }
            } catch (JSONException ex) {
                UserMessagesHandler.getInstance().registerError("Error while parsing achievements list response.");
                Log.e(LOG_TAG, ex.toString());
            }
            loadingDataDialog.dismiss();
            fillInData();
        }

        @Override
        public void fail(int code, String message) {
            loadingDataDialog.dismiss();
            UserMessagesHandler.getInstance().registerError("Could not load achievements list.");
        }
    }

    public void fillInData() {
        AchievementListAdapter adapter = new AchievementListAdapter(this.getActivity(), achievements);
        setListAdapter(adapter);
    }

    private void loadData() {
        loadingDataDialog.show();
        AchievementsResolver resolver = new AchievementsResolver(new AchievementsResolverCallback(), GlobalState.getInstance().getUser());
        resolver.doRequestArray();
    }
}
