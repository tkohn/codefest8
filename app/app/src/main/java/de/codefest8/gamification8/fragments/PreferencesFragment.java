package de.codefest8.gamification8.fragments;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.support.annotation.Nullable;
import android.support.v4.preference.PreferenceFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Arrays;
import java.util.Set;

import de.codefest8.gamification8.Preferences;
import de.codefest8.gamification8.R;

/**
 * Created by Koerfer on 13.03.2015.
 */
public class PreferencesFragment extends PreferenceFragment {

    public static final String noDeviceString = "- no device -";
    public static final String noDeviceToken = "-";

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        View v = super.onCreateView(layoutInflater, viewGroup, savedInstanceState);
        v.setBackground(getResources().getDrawable(R.drawable.main_background));
        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Preferences prefs = Preferences.getInstance(this.getActivity());
        this.addPreferencesFromResource(R.xml.prefs_main);
        final EditTextPreference usernamePref = (EditTextPreference) findPreference("txtUsername");
        usernamePref.setDefaultValue(prefs.username);
        usernamePref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                prefs.username = (String) newValue;
                prefs.save();
                return true;
            }
        });
        final EditTextPreference passwordPref = (EditTextPreference) findPreference("txtPassword");
        passwordPref.setDefaultValue(prefs.password);
        passwordPref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                prefs.password = (String) newValue;
                prefs.save();
                return true;
            }
        });
        final ListPreference btDevicePref = (ListPreference) findPreference("listBtDev");
        CharSequence[][] devStrings = getBluetoothPairedDevices();
        btDevicePref.setEntries(devStrings[0]);
        btDevicePref.setEntryValues(devStrings[1]);
        if (Arrays.asList(btDevicePref.getEntryValues()).contains(prefs.bluetoothDevToken))
        {
            btDevicePref.setDefaultValue(prefs.bluetoothDevToken);
        } else {
            btDevicePref.setDefaultValue(btDevicePref.getEntryValues()[0]);
        }
        int index = Arrays.asList(btDevicePref.getEntryValues()).indexOf(prefs.bluetoothDevToken);
        btDevicePref.setSummary(btDevicePref.getEntries()[index]);
        btDevicePref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                CharSequence[][] devStrings = getBluetoothPairedDevices();
                btDevicePref.setEntries(devStrings[0]);
                btDevicePref.setEntryValues(devStrings[1]);
                return false;
            }
        });
        btDevicePref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                int index = Arrays.asList(btDevicePref.getEntryValues()).indexOf(newValue);
                btDevicePref.setSummary(btDevicePref.getEntries()[index]);
                prefs.bluetoothDevToken = (String)newValue;
                prefs.save();
                return true;
            }
        });
        final CheckBoxPreference upOnlyOnWifiPref = (CheckBoxPreference) findPreference("chkUpOnlyWifi");
        upOnlyOnWifiPref.setDefaultValue(prefs.uploadOnlyOnWifi);
        upOnlyOnWifiPref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                prefs.uploadOnlyOnWifi = (boolean) newValue;
                prefs.save();
                return true;
            }
        });
    }

    private CharSequence[][] getBluetoothPairedDevices() {
        BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();
        CharSequence[][] devices;
        if (btAdapter != null && btAdapter.isEnabled()) {
            Set<BluetoothDevice> pairedDevices = btAdapter.getBondedDevices();
            devices = new CharSequence[2][pairedDevices.size() + 1];
            int devCounter = 1;
            for (BluetoothDevice dev : pairedDevices) {
                devices[0][devCounter] = dev.getName();
                devices[1][devCounter] = dev.getAddress();
                devCounter++;
            }
        }
        else
        {
            devices = new CharSequence[2][1];
        }
        devices[0][0] = noDeviceString;
        devices[1][0] = noDeviceToken;
        return devices;
    }
}
