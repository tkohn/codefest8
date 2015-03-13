package de.codefest8.gamification8;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.service.notification.NotificationListenerService;
import android.support.v4.app.NotificationCompat;

public class RecordService extends Service {

    NotificationManager notificationManager;

    public RecordService() {

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle("AixCruise");
        builder.setContentText("Recording in process ...");
        builder.setCategory(Notification.CATEGORY_PROGRESS);
        builder.setOngoing(true);
        builder.setSmallIcon(R.mipmap.home);
        builder.setProgress(10, 0, true);
        Notification notification = builder.build();
        notification.flags = Notification.FLAG_NO_CLEAR;
        notificationManager.notify(0, notification);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
