package com.mobss.islamic.namesofAllah.controller.alarms.notification;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.RemoteViews;
import com.mobss.islamic.namesofAllah.R;
import com.mobss.islamic.namesofAllah.views.activities.home.MainActivity;
import com.mobss.islamic.namesofAllah.views.widgets.notification.MobssNotification;
import com.mobss.islamic.namesofAllah.views.widgets.notification.MobssNotificationBuilder;

import java.util.Random;

/**
 * Created by iaktas on 23.11.2017 at 14:41.
 */

public class MobssCustomNotificationService extends Service {
//    @Inject
//    DataManager dataManager;

    private static final String TAG = "NotificationService";
    private MobssNotification notification;

    @Override
    public void onCreate() {
        super.onCreate();

//        DaggerActivityComponent.builder()
//                .activityModule(new ActivityModule(this))
//                .applicationComponent(((App) getApplication()).getAppComponent())
//                .build().inject(this);

        Random generator = new Random();
        int i = generator.nextInt(100);

        RemoteViews contentView = new RemoteViews(getPackageName(), R.layout.notification_custom);
        contentView.setImageViewResource(R.id.image, R.mipmap.ic_launcher);
        contentView.setTextViewText(R.id.title, "Custom notification");
        contentView.setTextViewText(R.id.text, "This is a custom layout");

        notification = MobssNotificationBuilder.instance()
                .context(this)
                .invocationActivity(MainActivity.class)
                .remoteViews(contentView)
                .smallIcon(R.mipmap.ic_launcher)
                .build();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        notification.showNotification();

        return Service.START_STICKY;

    }
}
