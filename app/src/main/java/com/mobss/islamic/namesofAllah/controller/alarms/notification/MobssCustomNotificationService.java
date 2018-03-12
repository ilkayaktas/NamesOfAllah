package com.mobss.islamic.namesofAllah.controller.alarms.notification;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.RemoteViews;
import com.mobss.islamic.namesofAllah.App;
import com.mobss.islamic.namesofAllah.R;
import com.mobss.islamic.namesofAllah.controller.DataManager;
import com.mobss.islamic.namesofAllah.model.app.AllahinIsimleri;
import com.mobss.islamic.namesofAllah.views.activities.home.MainActivity;
import com.mobss.islamic.namesofAllah.views.widgets.notification.MobssNotification;
import com.mobss.islamic.namesofAllah.views.widgets.notification.MobssNotificationBuilder;

import javax.inject.Inject;
import java.util.Random;

/**
 * Created by iaktas on 23.11.2017 at 14:41.
 */

public class MobssCustomNotificationService extends Service {
    @Inject
    DataManager dataManager;

    private static final String TAG = "NotificationService";
    private MobssNotification notification;

    @Override
    public void onCreate() {
        super.onCreate();

        ((App)getApplication()).getAppComponent().inject(this);

        Random generator = new Random();
        int i = generator.nextInt(100);
        AllahinIsimleri isim = dataManager.getIsim(i);

        RemoteViews contentView = new RemoteViews(getPackageName(), R.layout.notification_custom);
        contentView.setImageViewResource(R.id.image, getResources().getIdentifier(isim.resim, "drawable", getPackageName()));
        contentView.setTextViewText(R.id.title, isim.isim);
        contentView.setTextViewText(R.id.text, isim.aciklama);

        notification = MobssNotificationBuilder.instance()
                .context(this)
                .invocationActivity(MainActivity.class)
                .remoteViews(contentView)
                .paramId(isim.sira)
                .build();
    }

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
