package de.codefest8.gamification8;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import de.codefest8.gamification8.models.AchievementDTO;
import de.codefest8.gamification8.models.TripDTO;

/**
 * Created by koerfer on 07.03.2015.
 */
public class AchievementListFragment extends ListFragment {

    private AchievementDTO[] achievements;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        achievements = new AchievementDTO[]{new AchievementDTO(), new AchievementDTO()};
        AchievementListAdapter adapter = new AchievementListAdapter(this.getActivity(), achievements);
        setListAdapter(adapter);

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
                Toast.makeText(this.getActivity(), "Reset achievement", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}
