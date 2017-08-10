package com.mobss.islamic.namesofAllah.views.activities.splash;


import com.mobss.islamic.namesofAllah.di.annotations.PerActivity;
import com.mobss.islamic.namesofAllah.views.activities.base.MvpPresenter;

/**
 * Created by ilkay on 11/03/2017.
 */

@PerActivity
public interface SplashScreenMvpPresenter<V extends SplashScreenMvpView> extends MvpPresenter<V> {
	String getPreferredLanguage();
}
