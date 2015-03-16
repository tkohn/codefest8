package de.codefest8.gamification8.services;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class UploadReceiver extends BroadcastReceiver {

    private static boolean alarmSet;

    private static final long REPEAT_DURATION = 1000 * 60 * 5;

    public UploadReceiver() {

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        startUploadService(context);
        registerUploadAlarm(context);
    }

    public static void initiateRepeatingUpload(Context context) {
        registerUploadAlarm(context);
        startUploadService(context);
    }

    private static void startUploadService(Context context) {
        Intent serviceIntent = new Intent(context, UploadService.class);
        context.startService(serviceIntent);
    }

    private static void registerUploadAlarm(Context context) {
        if (!alarmSet) {
            alarmSet = true;
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            Intent newIntent = new Intent(context, UploadReceiver.class);
            PendingIntent alarmIntent = PendingIntent.getBroadcast(context, 0, newIntent, 0);
            alarmManager.cancel(alarmIntent);
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, REPEAT_DURATION, REPEAT_DURATION, alarmIntent);
        }
    }
}
