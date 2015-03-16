package de.codefest8.gamification8.service;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

import de.codefest8.gamification8.fragments.PreferencesFragment;

/**
 * Created by koerfer on 14.03.2015.
 */
public class ValueRecorder {

    private static final String LOG_TAG = "AixCruise";

    private Context context;

    private boolean obdReady;

    private BluetoothAdapter bluetoothAdapter;
    private BluetoothDevice obdDevice;
    private BluetoothSocket obdSocket;

    private FileOutputStream fileStream;

    private String locationProvider;
    private LocationManager locationManager;
    private LocationListener locationListener;

    public ValueRecorder(Context context) {
        this.context = context;
    }

    public void startRecord(final String deviceToken) {
        obdReady = false;
        if (!deviceToken.equals(PreferencesFragment.noDeviceToken)) {
            bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            if (bluetoothAdapter != null && bluetoothAdapter.isEnabled()) {
                obdDevice = bluetoothAdapter.getRemoteDevice(deviceToken);
                UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
                try {
                    obdSocket = obdDevice.createInsecureRfcommSocketToServiceRecord(uuid);
                    obdSocket.connect();
                    obdReady = true;
                } catch (Exception ex) {
                    Log.d("AixCruise", ex.getMessage());
                }
            }
        }
        // Saving
        File path = new File(context.getExternalFilesDir(null).getAbsolutePath() + "/tracks");
        if (!path.exists()) path.mkdirs();
        File file = new File(path, "newtrack.txt");
        try {
            fileStream = new FileOutputStream(file, false);
        } catch (Exception ex) {
        }
        // Location
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        locationProvider = "";
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            locationProvider = LocationManager.GPS_PROVIDER;
        } else {
            if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
                locationProvider = LocationManager.NETWORK_PROVIDER;
            }
        }
        if (locationProvider != "") {
            locationListener = new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    record(location);
                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }

                @Override
                public void onProviderEnabled(String provider) {
                }

                @Override
                public void onProviderDisabled(String provider) {

                }
            };
            locationManager.requestLocationUpdates(locationProvider, 1000, 0, locationListener);
        } else {
            // start Timer for record without android location updates
        }
    }

    private void record(Location location)
    {
        if (location != null)
        {
            try
            {
                String android_latitude = Double.toString(location.getLatitude());
                String android_longitude = Double.toString(location.getLongitude());
                String android_altitude = Double.toString(location.getAltitude());
                String android_bearing = Double.toString(location.getBearing());
                String android_speed = Double.toString(location.getSpeed());
                String android_time = Double.toString(location.getTime());
            }
            catch (Exception ex) { }
        }
    }

    public void finishRecord() {
        if (locationManager != null && locationListener != null) {
            this.locationManager.removeUpdates(locationListener);
        }
        if (obdSocket != null && obdSocket.isConnected()) {
            try {
                obdSocket.close();
            } catch (IOException ioex) {
                Log.d("AixCruise", ioex.getMessage());
            }
        }
        if (this.fileStream != null)
        {
            try {
                this.fileStream.flush();
                this.fileStream.close();
            } catch (IOException ioex) {
                Log.d("AixCruise", ioex.getMessage());
            }
        }
    }

}
