package de.codefest8.gamification8.fragments;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import de.codefest8.gamification8.MainActivity;
import de.codefest8.gamification8.R;
import de.codefest8.gamification8.UserMessagesHandler;
import de.codefest8.gamification8.listadapters.AchievementListAdapter;
import de.codefest8.gamification8.listadapters.TrackHistoryAdapter;
import de.codefest8.gamification8.models.AchievementDTO;
import de.codefest8.gamification8.models.TripDTO;
import de.codefest8.gamification8.models.TripDataDTO;
import de.codefest8.gamification8.models.UserDTO;
import de.codefest8.gamification8.network.AchievementsResolver;
import de.codefest8.gamification8.network.TripsResolver;
import de.codefest8.gamification8.network.ResponseCallback;
import de.codefest8.gamification8.network.UserResolver;

public class FriendDetailFragment extends Fragment {
    private static final String LOG_TAG = "FriendDetailFragment";
    public static final String BUNDLE_KEY_USER_ID = "user_id";

    AlertDialog loadingDataDialog;
    long userId;
    UserDTO user;
    View view;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle exchangeBundle = ((MainActivity)this.getActivity()).getExchangeBundle();
        userId = exchangeBundle.getLong(BUNDLE_KEY_USER_ID);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.dialog_loading_data).setTitle(R.string.dialog_loading_data);
        loadingDataDialog = builder.create();

        loadData();

        view = inflater.inflate(R.layout.fragment_frienddetail, container, false);
        return view;
    }

    public void fillInUserData() {
        ((TextView)view.findViewById(R.id.friend_name_value)).setText(user.getName());
    }

    public void fillInTripsData() {
        TripDTO[] trips = new TripDTO[] { new TripDTO(), new TripDTO() };
        TrackHistoryAdapter adapter = new TrackHistoryAdapter(this.getActivity(), trips);
        ((ListView)view.findViewById(R.id.friend_trips_list)).setAdapter(adapter);
    }

    public void fillInAchievements() {
        AchievementDTO[] achievements = new AchievementDTO[] { new AchievementDTO(), new AchievementDTO() };
        AchievementListAdapter adapter = new AchievementListAdapter(this.getActivity(), achievements);
        ((ListView)view.findViewById(R.id.friend_trips_list)).setAdapter(adapter);
    }

    class UserResolverCallback implements ResponseCallback {
        @Override
        public void success(JSONObject response) {
            user = UserDTO.fromJson(response);
            fillInUserData();
            TripsResolver resolver = new TripsResolver(new UserTripsResolverCallback(), user);
            resolver.doRequestArray();
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

    class UserTripsResolverCallback implements ResponseCallback {

        @Override
        public void success(JSONObject response) {

        }

        @Override
        public void successArray(JSONArray response) {
            ArrayList<TripDTO> trips = new ArrayList<TripDTO>();
            try {
                for (int i = 0; i < response.length(); i++) {
                    JSONObject object = response.getJSONObject(i);
                    trips.add(TripDTO.fromJson(object));
                }
                user.setTrips(new ArrayList<>(trips));
            } catch (JSONException ex) {
                UserMessagesHandler.getInstance().registerError("Error while parsing trips list response.");
                Log.e(LOG_TAG, ex.toString());
            }

            fillInTripsData();

            AchievementsResolver resolver = new AchievementsResolver(new UserAchivementsResolverCallback(), user);
            resolver.doRequestArray();
        }

        @Override
        public void fail(int code, String message) {
            loadingDataDialog.dismiss();
            UserMessagesHandler.getInstance().registerError("Could not load trips list.");
        }
    }

    class UserAchivementsResolverCallback implements ResponseCallback {

        @Override
        public void success(JSONObject response) {

        }

        @Override
        public void successArray(JSONArray response) {
            loadingDataDialog.dismiss();

            ArrayList<AchievementDTO> achievements = new ArrayList<AchievementDTO>();
            try {
                for (int i = 0; i < response.length(); i++) {
                    JSONObject object = response.getJSONObject(i);
                    achievements.add(AchievementDTO.fromJson(object));
                }
                user.setAchievements(new ArrayList<>(achievements));
            } catch (JSONException ex) {
                UserMessagesHandler.getInstance().registerError("Error while parsing achievements list response.");
                Log.e(LOG_TAG, ex.toString());
            }

            fillInAchievements();
        }

        @Override
        public void fail(int code, String message) {
            loadingDataDialog.dismiss();
            UserMessagesHandler.getInstance().registerError("Could not load achievements list.");
        }
    }

    private void loadData() {
        loadingDataDialog.show();
        UserResolver resolver = new UserResolver(new UserResolverCallback(), userId);
        resolver.doRequestSingle();
    }
}
