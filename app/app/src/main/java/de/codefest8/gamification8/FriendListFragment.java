package de.codefest8.gamification8;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import de.codefest8.gamification8.models.UserDTO;
import de.codefest8.gamification8.network.FriendsResolver;
import de.codefest8.gamification8.network.ResponseCallback;

public class FriendListFragment extends ListFragment {
    private final static String LOG_TAG = "FriendsListFragment";
    AlertDialog loadingDataDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.dialog_loading_data).setTitle(R.string.dialog_loading_data);
        loadingDataDialog = builder.create();

        loadData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_friendlist, container, false);
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
        inflater.inflate(R.menu.contextmenu_friendlist, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.action_unfriend:
                break;
        }
        return true;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater = this.getActivity().getMenuInflater();
        inflater.inflate(R.menu.menu_friendlist, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    class FriendsResolverCallback implements ResponseCallback {

        @Override
        public void success(JSONObject response) {

        }

        @Override
        public void successArray(JSONArray response) {
            loadingDataDialog.dismiss();
            UserDTO[] users = new UserDTO[response.length()];
            try {
                for (int i = 0; i < response.length(); i++) {
                    JSONObject object = response.getJSONObject(i);
                    users[i] = UserDTO.fromJson(object);
                }
            } catch (JSONException ex) {
                UserMessagesHandler.getInstance().registerError("Error while parsing friends list response.");
                Log.e(LOG_TAG, ex.toString());
            }

            FriendsListAdapter adapter = new FriendsListAdapter(getActivity(), users);
            setListAdapter(adapter);
        }

        @Override
        public void fail(int code, String message) {
            loadingDataDialog.dismiss();
            UserMessagesHandler.getInstance().registerError("Could not load friends list.");
        }
    }

    private void loadData() {
        loadingDataDialog.show();
        FriendsResolver resolver = new FriendsResolver(new FriendsResolverCallback(),GlobalState.getInstance().getUser());
        resolver.doRequestArray();
    }
}
