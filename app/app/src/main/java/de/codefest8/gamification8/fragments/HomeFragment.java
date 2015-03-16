package de.codefest8.gamification8.fragments;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import de.codefest8.gamification8.R;
import de.codefest8.gamification8.service.RecordService;

public class HomeFragment extends Fragment {

    private CheckBox startButton;
    private View recentAchievesTitle;
    private View recentAchievesFragment;
    AlertDialog loadingDataDialog;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.dialog_loading_data).setTitle(R.string.dialog_loading_data);
        loadingDataDialog = builder.create();

        boolean serviceRunning = isRecordServiceRunning();

        startButton = (CheckBox)view.findViewById(R.id.start_button);
        recentAchievesTitle = view.findViewById(R.id.recent_achieves_title);
        recentAchievesFragment = view.findViewById(R.id.short_achievements);

        startButton.setChecked(serviceRunning);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox clicked = (CheckBox)v;
                if (clicked.isChecked()) {
                    startService();
                    recentAchievesTitle.setVisibility(View.INVISIBLE);
                    recentAchievesFragment.setVisibility(View.INVISIBLE);
                } else {
                    stopService();
                    recentAchievesTitle.setVisibility(View.VISIBLE);
                    recentAchievesFragment.setVisibility(View.VISIBLE);
                }
            }
        });
        //noinspection ResourceType
        recentAchievesTitle.setVisibility(boolToVisibility(!serviceRunning));
        //noinspection ResourceType
        recentAchievesFragment.setVisibility(boolToVisibility(!serviceRunning));

        return view;
    }

    private void startService() {
        Intent serviceStartIntent = new Intent(getActivity(), RecordService.class);
        getActivity().startService(serviceStartIntent);
    }

    private void stopService() {
        Intent serviceStopIntent = new Intent(getActivity(), RecordService.class);
        getActivity().stopService(serviceStopIntent);
    }

    private boolean isRecordServiceRunning() {
        ActivityManager activityManager = (ActivityManager) getActivity().getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : activityManager.getRunningServices(Integer.MAX_VALUE)) {
            if (RecordService.class.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    private static int boolToVisibility(boolean b) {
        if (b) {
            return View.VISIBLE;
        } else {
            return View.INVISIBLE;
        }
    }
}
