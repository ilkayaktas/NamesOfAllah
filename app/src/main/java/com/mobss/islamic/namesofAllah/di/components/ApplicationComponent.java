package com.mobss.islamic.namesofAllah.di.components;


import android.app.Application;
import android.content.Context;
import com.mobss.islamic.namesofAllah.App;
import com.mobss.islamic.namesofAllah.controller.IDataManager;
import com.mobss.islamic.namesofAllah.controller.alarms.notification.MobssCustomNotificationService;
import com.mobss.islamic.namesofAllah.controller.db.crud.DatabaseManager;
import com.mobss.islamic.namesofAllah.di.annotations.ApplicationContext;
import com.mobss.islamic.namesofAllah.di.modules.ApplicationModule;
import dagger.Component;

import javax.inject.Singleton;

/**
 * Created by ilkay on 26/02/2017.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(App app);

    void inject(MobssCustomNotificationService service);

    @ApplicationContext
    Context context();
    
    Application application();
    
    IDataManager getDataManager();
    
    DatabaseManager getDatabaseManager();
    
}
