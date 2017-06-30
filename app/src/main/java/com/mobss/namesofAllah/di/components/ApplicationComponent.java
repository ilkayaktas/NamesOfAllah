package com.mobss.namesofAllah.di.components;


import android.app.Application;
import android.content.Context;

import com.mobss.namesofAllah.App;
import com.mobss.namesofAllah.controller.IDataManager;
import com.mobss.namesofAllah.di.annotations.ApplicationContext;
import com.mobss.namesofAllah.di.modules.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ilkay on 26/02/2017.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(App app);
    
    @ApplicationContext
    Context context();
    
    Application application();
    
    IDataManager getDataManager();
    
}
