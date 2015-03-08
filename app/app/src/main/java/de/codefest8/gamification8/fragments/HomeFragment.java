package de.codefest8.gamification8.fragments;

import android.app.AlertDialog;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import de.codefest8.gamification8.R;

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

        startButton = (CheckBox)view.findViewById(R.id.start_button);
        recentAchievesTitle = view.findViewById(R.id.recent_achieves_title);
        recentAchievesFragment = view.findViewById(R.id.short_achievements);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox clicked = (CheckBox)v;
                if (clicked.isChecked()) {
                    recentAchievesTitle.setVisibility(View.INVISIBLE);
                    recentAchievesFragment.setVisibility(View.INVISIBLE);
                } else {
                    recentAchievesTitle.setVisibility(View.VISIBLE);
                    recentAchievesFragment.setVisibility(View.VISIBLE);
                }
            }
        });

        return view;
    }
}
