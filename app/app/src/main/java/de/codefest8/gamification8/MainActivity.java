package de.codefest8.gamification8;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.content.res.TypedArray;
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
                this.selectItem(0);
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
            selectItem(position);
        }
    }

    private void selectItem(int position) {
        Fragment newFragment;
        switch (position)
        {
            default:
            case 0:
                newFragment = new HomeFragment();
                break;
            case 1:
                newFragment = new TrackHistoryFragment();
                break;
            case 2:
                newFragment = new HomeFragment();
                break;
            case 3:
                newFragment = new AboutFragment();
                break;
        }

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_container, newFragment).commit();

        this.drawerLayout.closeDrawers();
        this.getSupportActionBar().setTitle(menuLabels[position]);
    }

}
