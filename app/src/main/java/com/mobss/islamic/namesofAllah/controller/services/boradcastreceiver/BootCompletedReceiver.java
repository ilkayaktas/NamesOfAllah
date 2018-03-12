package com.mobss.islamic.namesofAllah.controller.services.boradcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.mobss.islamic.namesofAllah.controller.alarms.notification.DailyNotificationAlarm;
import com.mobss.islamic.namesofAllah.controller.pref.PreferenceHelper;

/**
 * Created by iaktas on 23.11.2017 at 08:13.
 */

public class BootCompletedReceiver extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent ıntent) {

        Log.i("NamesofAllah", "onReceive: Boot is completed ");

        setAlarm(context);
    }

    private void setAlarm(Context context){
        if (new PreferenceHelper(context).getDailyNotification()){
            new DailyNotificationAlarm(context).setAt14();
        }

    }
}
