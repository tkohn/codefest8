package de.codefest8.gamification8;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by koerfer on 07.03.2015.
 */
public class FriendListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_friendlist, container, false);
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        return true;
    }


}
