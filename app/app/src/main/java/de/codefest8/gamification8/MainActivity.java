package de.codefest8.gamification8;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.content.res.TypedArray;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


public class MainActivity extends ActionBarActivity {
    private final String ACTIVITY_NAME = "MainActivity";

    private DrawerLayout drawerLayout;
    private ListView drawerMenu;

    private String[] menuLabels;
    private TypedArray menuIcons;
    private DrawerElement[] menuEntries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);
        this.initDrawer();
        Fragment firstFragment = (Fragment) new HomeFragment();
        this.getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, firstFragment).commit();
    }

    @Override
    public void onBackPressed() {
        if (this.drawerLayout.isDrawerOpen(drawerMenu))
        {
            this.drawerLayout.closeDrawer(drawerMenu);
        }
        else
        {
            if (this.getSupportActionBar().getTitle() != menuLabels[0])
            {
                this.goToFragment(FragmentType.Home);
            }
            else
            {
                super.onBackPressed();
            }
        }
    }

    private void initDrawer() {
        menuLabels = getResources().getStringArray(R.array.drawer_elements_name);
        menuIcons = getResources().obtainTypedArray(R.array.drawer_elements_icon);
        menuEntries = new DrawerElement[menuLabels.length];
        for (int i = 0; i < menuLabels.length; i++)
        {
            menuEntries[i] = new DrawerElement(menuLabels[i], menuIcons.getResourceId(i, -1));
        }
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        drawerMenu = (ListView)findViewById(R.id.left_drawer);
        drawerMenu.setAdapter(new DrawerElementAdapter(this, menuEntries));
        drawerMenu.setOnItemClickListener(new DrawerItemClickListener());
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {

            switch (position)
            {
                default:
                case 0:
                    goToFragment(FragmentType.Home);
                    break;
                case 1:
                    goToFragment(FragmentType.FriendList);
                    break;
                case 2:
                    goToFragment(FragmentType.TrackHistory);
                    break;
                case 3:
                    goToFragment(FragmentType.AchievementList);
                    break;
                case 4:
                    goToFragment(FragmentType.About);
                    break;
            }
            drawerLayout.closeDrawer(drawerMenu);
        }
    }

    public void goToFragment(FragmentType type)
    {
        this.goToFragment(type, Bundle.EMPTY);
    }

    public void goToFragment(FragmentType type, Bundle bundle)
    {
        Fragment newFragment;
        switch (type)
        {
            default:
            case Home:
                newFragment = new HomeFragment();
                break;
            case FriendList:
                newFragment = new FriendListFragment();
                break;
            case FriendDetail:
                newFragment = new FriendDetailFragment();
                break;
            case TrackHistory:
                newFragment = new TrackHistoryFragment();
                break;
            case TrackDetail:
                newFragment = new TrackDetailFragment();
                break;
            case AchievementList:
                newFragment = new AchievementListFragment();
                break;
            case About:
                newFragment = new AboutFragment();
                break;
        }
        newFragment.setArguments(bundle);
        FragmentTransaction transaction = this.getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, newFragment).commit();
        getSupportActionBar().setTitle(type.name());
    }

}
