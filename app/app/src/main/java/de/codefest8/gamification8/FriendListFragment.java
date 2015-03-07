package de.codefest8.gamification8;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by koerfer on 07.03.2015.
 */
public class FriendListFragment extends ListFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
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
}
