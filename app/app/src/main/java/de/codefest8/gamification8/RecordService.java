package de.codefest8.gamification8;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.service.notification.NotificationListenerService;
import android.support.v4.app.NotificationCompat;

public class RecordService extends Service {

    public RecordService() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        startNotification();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        stopNotification();
        super.onDestroy();
    }

    private final static int NOTIFICATION_ID = 1;

    private void startNotification() {
        // build ongoing progress notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle("AixCruise");
        builder.setContentText("Recording in process ...");
        builder.setCategory(Notification.CATEGORY_PROGRESS);
        builder.setOngoing(true);
        builder.setSmallIcon(R.mipmap.home);
        builder.setProgress(10, 0, true);
        builder.setContentIntent(PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 0));
        Notification notification = builder.build();
        notification.flags = Notification.FLAG_NO_CLEAR;
        // start notification
        startForeground(NOTIFICATION_ID, notification);
    }

    private void stopNotification() {
        stopForeground(true);
    }
}
