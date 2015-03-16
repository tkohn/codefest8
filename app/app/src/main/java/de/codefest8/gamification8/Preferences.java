package de.codefest8.gamification8;

import android.content.Context;
import android.content.SharedPreferences;

import de.codefest8.gamification8.fragments.PreferencesFragment;

/**
 * Created by koerfer on 15.03.2015.
 */
public class Preferences {

    private static Preferences instance;

    public static Preferences getInstance(Context context)
    {
        if (instance == null)
        {
            instance = new Preferences(context);
        }
        return instance;
    }

    public static final String PREFS_KEY = "aixcruise_prefs";

    public static final String FILENAMES_KEY = "filenames";

    private static final String USERNAME_KEY = "username";
    private static final String PASSWORD_KEY = "password";
    private static final String BLUETOOTH_DEV_TOKEN_KEY = "bluetoothdevtoken";
    private static final String UPLOAD_ON_WIFI_ONLY_KEY = "uploadonwifionly";

    public String username;
    public String password;
    public String bluetoothDevToken;
    public boolean uploadOnlyOnWifi;

    private Context context;

    public Preferences(Context context)
    {
        this.context = context;
        load();
    }

    private void load() {
        SharedPreferences prefs = this.context.getSharedPreferences(PREFS_KEY, 0);
        this.username = prefs.getString(USERNAME_KEY, "");
        this.password = prefs.getString(PASSWORD_KEY, "");
        this.bluetoothDevToken = prefs.getString(BLUETOOTH_DEV_TOKEN_KEY, PreferencesFragment.noDeviceToken);
        this.uploadOnlyOnWifi = prefs.getBoolean(UPLOAD_ON_WIFI_ONLY_KEY, true);
    }

    public void save() {
        SharedPreferences prefs = this.context.getSharedPreferences(PREFS_KEY, 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(USERNAME_KEY, this.username);
        editor.putString(PASSWORD_KEY, this.password);
        editor.putString(BLUETOOTH_DEV_TOKEN_KEY, this.bluetoothDevToken);
        editor.putBoolean(UPLOAD_ON_WIFI_ONLY_KEY, this.uploadOnlyOnWifi);
        editor.commit();
    }

}
