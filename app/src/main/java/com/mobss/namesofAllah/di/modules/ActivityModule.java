package com.mobss.namesofAllah.di.modules;

import android.app.Activity;
import android.graphics.Typeface;

import com.mobss.namesofAllah.controller.IDataManager;
import com.mobss.namesofAllah.controller.services.MobssAsyncTask;
import com.mobss.namesofAllah.controller.strategy.Strategy;
import com.mobss.namesofAllah.di.annotations.ActivityContext;
import com.mobss.namesofAllah.di.annotations.PerActivity;
import com.mobss.namesofAllah.views.activities.home.MainMvpPresenter;
import com.mobss.namesofAllah.views.activities.home.MainMvpView;
import com.mobss.namesofAllah.views.activities.home.MainPresenter;
import com.mobss.namesofAllah.views.activities.splash.SplashScreenMvpPresenter;
import com.mobss.namesofAllah.views.activities.splash.SplashScreenMvpView;
import com.mobss.namesofAllah.views.activities.splash.SplashScreenPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ilkay on 10/03/2017.
 */

@Module
public class ActivityModule {
    Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    Activity provideActivity(){
        return activity;
    }
    
    @Provides
    @PerActivity
    Typeface provideTypeface(){
        return Typeface.createFromAsset(activity.getAssets(), "fonts/Sketch.ttf");
    }
    
    @Provides
    @PerActivity
    @ActivityContext
    Typeface provideTypefaceForActivity(){
        return Typeface.createFromAsset(activity.getAssets(), "fonts/gothic.TTF");
    }
    
    @Provides
    @PerActivity
    SplashScreenMvpPresenter<SplashScreenMvpView> provideSplashScreenPresenter(IDataManager IDataManager) {
        return new SplashScreenPresenter<>(IDataManager);
    }
    
    @Provides
    @PerActivity
    MainMvpPresenter<MainMvpView> providesMainPresenter(IDataManager IDataManager){
        return new MainPresenter<>(IDataManager);
    }
    
    @Provides
    @PerActivity
    MobssAsyncTask providesMobssAsyncTask(Activity activity, Strategy strategy){
        return new MobssAsyncTask(activity, strategy);
    }

}
