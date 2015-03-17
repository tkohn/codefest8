package de.codefest8.gamification8.services;

import android.app.IntentService;
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

public class UploadService extends IntentService {

    private NotificationCompat.Builder builder;
    private NotificationManager notificationManager;

    public UploadService() {
        super(UploadService.class.getName());
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        startNotification();
        int i = 0;
        while (i < 100) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException iex) {
                Log.d("AixCruise", iex.getMessage());
            }
            i++;
            builder.setProgress(100, i, false);
            notificationManager.notify(NOTIFICATION_ID, builder.build());
        }
        stopNotification();
    }

    private final static int NOTIFICATION_ID = 2;

    private void startNotification() {
        // build ongoing progress notification
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        builder = new NotificationCompat.Builder(this);
        builder.setContentTitle("AixCruise - Uploading");
        builder.setContentText("\u21d2 filename.txt");
        builder.setCategory(Notification.CATEGORY_PROGRESS);
        builder.setOngoing(true);
        builder.setSmallIcon(R.mipmap.home);
        builder.setProgress(100, 0, false);
        // start notification
        startForeground(NOTIFICATION_ID, builder.build());
    }

    private void stopNotification() {
        notificationManager.cancel(NOTIFICATION_ID);
        stopForeground(true);
    }

}
