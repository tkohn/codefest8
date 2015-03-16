package de.codefest8.gamification8.services;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.util.Arrays;

import de.codefest8.gamification8.MainActivity;
import de.codefest8.gamification8.R;

public class UploadService extends Service {

    private NotificationCompat.Builder builder;
    private NotificationManager notificationManager;

    public UploadService() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startNotification();
        uploadTask.execute("");
        return super.onStartCommand(intent, flags, startId);
    }

    private AsyncTask<String, Integer, Void> uploadTask = new AsyncTask<String, Integer, Void>() {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(String... params) {
            int i = 0;
            while (i < 100)
            {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException iex) {
                    Log.d("AixCruise", iex.getMessage());
                }
                i = i + 10;
                publishProgress(i);
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            builder.setProgress(100, values[0], false);
            notificationManager.notify(NOTIFICATION_ID, builder.build());
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            stopSelf();
            super.onPostExecute(aVoid);
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDestroy() {
        stopNotification();
        super.onDestroy();
    }

    private final static int NOTIFICATION_ID = 2;

    private void startNotification() {
        // build ongoing progress notification
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        builder = new NotificationCompat.Builder(this);
        builder.setContentTitle("AixCruise - Uploading");
        builder.setCategory(Notification.CATEGORY_PROGRESS);
        builder.setOngoing(true);
        builder.setSmallIcon(R.mipmap.home);
        builder.setProgress(100, 0, false);
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

}
