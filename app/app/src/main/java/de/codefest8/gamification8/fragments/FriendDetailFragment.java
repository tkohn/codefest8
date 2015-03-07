package de.codefest8.gamification8.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.codefest8.gamification8.R;

/**
 * Created by koerfer on 07.03.2015.
 */
public class FriendDetailFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_frienddetail, container, false);
    }

}
