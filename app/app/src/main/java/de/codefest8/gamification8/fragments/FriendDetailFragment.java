package de.codefest8.gamification8.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import de.codefest8.gamification8.BundleKeys;
import de.codefest8.gamification8.GlobalState;
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

    AlertDialog loadingDataDialog;
    long userId;
    UserDTO user;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.setHasOptionsMenu(true);
        Bundle exchangeBundle = this.getArguments();
        userId = exchangeBundle.getLong(BundleKeys.KEY_USER_ID);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        loadingDataDialog = builder.setMessage(R.string.dialog_loading_data).setTitle(R.string.dialog_loading_data).create();

        loadData();

        view = inflater.inflate(R.layout.fragment_frienddetail, container, false);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.menu_friend, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch(item.getItemId())
        {
            case R.id.action_unfriend:
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Warning");
                builder.setMessage("Do you want to remove " + user.getName() + " from your friend list?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "You removed " + user.getName() + " from your friend list!", Toast.LENGTH_SHORT).show();
                        ((MainActivity)getActivity()).goToFragment(FragmentType.FriendList);
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

    public void fillInUserData() {
        ((TextView) view.findViewById(R.id.friend_name_value)).setText(user.getName());
    }

    public void fillInTripsData() {
        TripDTO[] trips = new TripDTO[user.getTrips().size()];
        TrackHistoryAdapter adapter = new TrackHistoryAdapter(this.getActivity(), user.getTrips().toArray(trips));
        ((ListView)view.findViewById(R.id.friend_trips_list)).setAdapter(adapter);
        ((ListView)view.findViewById(R.id.friend_trips_list)).setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                long tripId = user.getTrips().get(position).getId();
                Bundle exchangeBundle = new Bundle();
                exchangeBundle.putLong("KEY_TRIP_ID", tripId);
                ((MainActivity)getActivity()).goToFragment(FragmentType.TrackDetail, exchangeBundle);
            }
        });
    }

    public void fillInAchievements() {
        AchievementDTO[] achievements = new AchievementDTO[user.getAchievements().size()];
        AchievementListAdapter adapter = new AchievementListAdapter(this.getActivity(), user.getAchievements().toArray(achievements));
        ((ListView)view.findViewById(R.id.friend_achievements_list)).setAdapter(adapter);
//        ((ListView)view.findViewById(R.id.friend_achievements_list)).setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                ((MainActivity)getActivity()).goToFragment(FragmentType.AchievementDetail);
//            }
//        });
    }

    class UserResolverCallback implements ResponseCallback {
        @Override
        public void success(JSONObject response) {
            user = UserDTO.fromJson(response);
            fillInUserData();
            TripsResolver resolver = new TripsResolver(new UserTripsResolverCallback(), userId);
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
            }

            fillInTripsData();

            AchievementsResolver resolver = new AchievementsResolver(new UserAchivementsResolverCallback(), userId);
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
