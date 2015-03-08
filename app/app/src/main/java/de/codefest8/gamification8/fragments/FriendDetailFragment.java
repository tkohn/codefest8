package de.codefest8.gamification8.fragments;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import de.codefest8.gamification8.GlobalState;
import de.codefest8.gamification8.MainActivity;
import de.codefest8.gamification8.R;
import de.codefest8.gamification8.UserMessagesHandler;
import de.codefest8.gamification8.listadapters.FriendsListAdapter;
import de.codefest8.gamification8.models.UserDTO;
import de.codefest8.gamification8.network.FriendsResolver;
import de.codefest8.gamification8.network.ResponseCallback;
import de.codefest8.gamification8.network.UserResolver;

public class FriendDetailFragment extends Fragment {
    public static final String BUNDLE_KEY_USER_ID = "user_id";

    AlertDialog loadingDataDialog;
    long userId;
    UserDTO user;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle exchangeBundle = ((MainActivity)this.getActivity()).getExchangeBundle();
        userId = exchangeBundle.getLong(BUNDLE_KEY_USER_ID);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.dialog_loading_data).setTitle(R.string.dialog_loading_data);
        loadingDataDialog = builder.create();

        loadData();

        return inflater.inflate(R.layout.fragment_frienddetail, container, false);
    }

    public void fillInUserData() {

    }

    class UserResolverCallback implements ResponseCallback {

        @Override
        public void success(JSONObject response) {
            loadingDataDialog.dismiss();
            user = UserDTO.fromJson(response);
            fillInUserData();
        }

        @Override
        public void successArray(JSONArray response) {

        }

        @Override
        public void fail(int code, String message) {
            loadingDataDialog.dismiss();
            UserMessagesHandler.getInstance().registerError("Could not load friends list.");
        }
    }

    private void loadData() {
        loadingDataDialog.show();
        UserResolver resolver = new UserResolver(new UserResolverCallback(), userId);
        resolver.doRequestSingle();
    }
}
