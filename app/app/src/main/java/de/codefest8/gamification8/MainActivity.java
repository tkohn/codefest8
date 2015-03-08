package de.codefest8.gamification8;

import android.content.res.Configuration;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.content.res.TypedArray;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import de.codefest8.gamification8.fragments.AboutFragment;
import de.codefest8.gamification8.fragments.AchievementListFragment;
import de.codefest8.gamification8.fragments.FragmentType;
import de.codefest8.gamification8.fragments.FriendDetailFragment;
import de.codefest8.gamification8.fragments.FriendsListFragment;
import de.codefest8.gamification8.fragments.HomeFragment;
import de.codefest8.gamification8.fragments.TrackDetailFragment;
import de.codefest8.gamification8.fragments.TrackHistoryFragment;
import de.codefest8.gamification8.listadapters.DrawerElementAdapter;
import de.codefest8.gamification8.models.UserDTO;

public class MainActivity extends ActionBarActivity {
    private final String ACTIVITY_NAME = "MainActivity";

    private DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    private ListView drawerMenu;

    private String[] menuLabels;
    private TypedArray menuIcons;
    private DrawerElement[] menuEntries;

    private String currentTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UserMessagesHandler.getInstance().setApplicationContext(getApplicationContext());

        UserDTO user = new UserDTO();
        user.setId(0);
        user.setName("James Bond");
        user.setPassword("topsecret");
        GlobalState.getInstance().setUser(user);

        this.setContentView(R.layout.activity_main);
        this.currentTitle = ((String[])getResources().getStringArray(R.array.drawer_elements_name))[0];
        this.initDrawer();
        Fragment firstFragment = new HomeFragment();
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

        drawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                drawerLayout,         /* DrawerLayout object */
                R.mipmap.home,  /* nav drawer icon to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description */
                R.string.drawer_close  /* "close drawer" description */
        ) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(currentTitle);
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle(R.string.drawler_active);
            }
        };

        // Set the drawer toggle as the DrawerListener
        drawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

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
                newFragment = new FriendsListFragment();
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

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }


}