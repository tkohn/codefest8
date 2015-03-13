package de.codefest8.gamification8.fragments;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.support.annotation.Nullable;
import android.support.v4.preference.PreferenceFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Arrays;
import java.util.Set;

import de.codefest8.gamification8.R;

/**
 * Created by Koerfer on 13.03.2015.
 */
public class SettingsFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.addPreferencesFromResource(R.xml.prefs_main);
        final ListPreference btDevicePref = (ListPreference) findPreference("listBtDev");
        CharSequence[][] devStrings = getBluetoothPairedDevices();
        btDevicePref.setEntries(devStrings[0]);
        btDevicePref.setEntryValues(devStrings[1]);
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
                return true;
            }
        });
    }

    private CharSequence[][] getBluetoothPairedDevices()
    {
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
        CharSequence noDeviceStr = "- no device -";
        devices[0][0] = noDeviceStr;
        devices[1][0] = noDeviceStr;
        return devices;
    }
}
