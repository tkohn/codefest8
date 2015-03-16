package de.codefest8.gamification8.services;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.text.format.Time;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Collections;
import java.util.Set;
import java.util.UUID;

import de.codefest8.gamification8.MainActivity;
import de.codefest8.gamification8.Preferences;
import de.codefest8.gamification8.R;
import de.codefest8.gamification8.fragments.PreferencesFragment;

public class RecordService extends Service {

    public static final String TRACK_FOLDER = "/tracks";

    private NotificationCompat.Builder builder;
    private NotificationManager notificationManager;

    public RecordService()
    {
        super();
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startNotification();
        String bluetoothDevToken = Preferences.getInstance(this).bluetoothDevToken;
        asyncRecorder.execute(bluetoothDevToken);
        return super.onStartCommand(intent, flags, startId);
    }

    private AsyncTask<String, Long, String> asyncRecorder = new AsyncTask<String, Long, String>() {

        private String fileName;

        private long stepCounter;
        private boolean obdReady;
        private boolean gpsReady;

        private BluetoothAdapter bluetoothAdapter;
        private BluetoothDevice obdDevice;
        private BluetoothSocket obdSocket;

        private FileOutputStream fileStream;

        private String locationProvider;
        private LocationManager locationManager;
        private LocationListener locationListener;

        @Override
        protected void onPreExecute() {
            stepCounter = 0;
            obdReady = false;
            gpsReady = false;
            // File
            File path = new File(RecordService.this.getExternalFilesDir(null).getAbsolutePath() + TRACK_FOLDER);
            if (!path.exists()) path.mkdirs();
            fileName = Integer.toString(Calendar.getInstance().get(Calendar.SECOND)) + ".txt";
            File file = new File(path, fileName);
            try {
                fileStream = new FileOutputStream(file, false);
            } catch (Exception ex) {
            }
            // Location
            locationManager = (LocationManager) RecordService.this.getSystemService(Context.LOCATION_SERVICE);
            locationProvider = "";
            if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                locationProvider = LocationManager.GPS_PROVIDER;
                gpsReady = true;

            } else {
                if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
                    locationProvider = LocationManager.NETWORK_PROVIDER;
                    gpsReady = true;
                }
            }
            if (gpsReady) {
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

        @Override
        protected String doInBackground(String... params) {
            if (!params[0].equals(PreferencesFragment.noDeviceToken)) {
                bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
                if (bluetoothAdapter != null && bluetoothAdapter.isEnabled()) {
                    obdDevice = bluetoothAdapter.getRemoteDevice(params[0]);
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
            while (!this.isCancelled()) {

            }
            return null;
        }

        private void record(Location location) {
            if (location != null) {
                String android_latitude = Double.toString(location.getLatitude());
                String android_longitude = Double.toString(location.getLongitude());
                String android_altitude = Double.toString(location.getAltitude());
                String android_bearing = Double.toString(location.getBearing());
                String android_speed = Double.toString(location.getSpeed());
                String android_time = Double.toString(location.getTime());
                try {
                    fileStream.write((android_time + "," + android_latitude + "," + android_longitude + "," + android_altitude).getBytes());
                } catch (IOException ioex) {
                    Log.d("AixCruise", ioex.getMessage());
                }
            }
            stepCounter++;
            publishProgress(stepCounter);
        }

        @Override
        protected void onProgressUpdate(Long... values) {
            builder.setContentText(generateNotificationText(obdReady, gpsReady, values[0]));
            notificationManager.notify(NOTIFICATION_ID, builder.build());
            super.onProgressUpdate(values);
        }

        @Override
        protected void onCancelled(String s) {
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
            if (this.fileStream != null) {
                try {
                    this.fileStream.flush();
                    this.fileStream.close();
                    saveFileName(this.fileName);
                } catch (IOException ioex) {
                    Log.d("AixCruise", ioex.getMessage());
                }
            }
            super.onCancelled();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        asyncRecorder.cancel(true);
        stopNotification();
        super.onDestroy();
    }

    private final static int NOTIFICATION_ID = 1;

    private void startNotification() {
        // build ongoing progress notification
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        builder = new NotificationCompat.Builder(this);
        builder.setContentTitle("AixCruise - Recording");
        builder.setContentText(generateNotificationText(false, false, 0));
        builder.setCategory(Notification.CATEGORY_PROGRESS);
        builder.setOngoing(true);
        builder.setSmallIcon(R.mipmap.home);
        builder.setProgress(10, 0, true);
        Intent activityIntent = new Intent(this, MainActivity.class);
        activityIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        builder.setContentIntent(PendingIntent.getActivity(this, 0, activityIntent, 0));
        // start notification
        startForeground(NOTIFICATION_ID, builder.build());
    }

    private void stopNotification() {
        notificationManager.cancel(NOTIFICATION_ID);
        stopForeground(true);
    }

    private static final String unicodeMark = "\u2714";
    private static final String unicodeBallot = "\u2718";

    private static String generateNotificationText(boolean obdReady, boolean gpsReady, long stepCounter) {
        return " OBD " + (obdReady ? unicodeMark : unicodeBallot) + " GPS " + (gpsReady ? unicodeMark : unicodeBallot) + " Frames: " + Long.toString(stepCounter);
    }

    private void saveFileName(String fileName)
    {
        SharedPreferences prefs = this.getSharedPreferences(Preferences.PREFS_KEY, 0);
        SharedPreferences.Editor editor = prefs.edit();
        Set<String> fileNames = prefs.getStringSet(Preferences.FILENAMES_KEY, Collections.EMPTY_SET);
        fileNames.add(fileName);
        editor.putStringSet(Preferences.FILENAMES_KEY, fileNames);
        editor.commit();
    }
}
