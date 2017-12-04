package com.mobss.islamic.namesofAllah.controller.alarms.notification;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Calendar;

/**
 * Created by iaktas on 3.12.2017 at 21:36.
 */

public class DailyNotificationAlarm {
    private static final String TAG = "DailyNotificationAlarm";
    private AlarmManager alarmManager = null;
    private PendingIntent pendingIntent = null;

    public DailyNotificationAlarm(Context context) {
        this.alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(context, MobssCustomNotificationService.class);
        pendingIntent = PendingIntent.getService(context, 147411, intent, 0);
    }

    public void set(Calendar calendar){
        // Sets an alarm - note this alarm will be lost if the phone is turned off and on again
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);

        Log.d(TAG, "Alarm is set: " + calendar.getTime().toString());
    }

    public void cancel(){
        if (alarmManager != null && pendingIntent != null){
            alarmManager.cancel(pendingIntent);
        }
    }
}
