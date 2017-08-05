package com.mobss.namesofAllah.views.activities.splash;


import com.mobss.namesofAllah.di.annotations.PerActivity;
import com.mobss.namesofAllah.views.activities.base.MvpPresenter;

/**
 * Created by ilkay on 11/03/2017.
 */

@PerActivity
public interface SplashScreenMvpPresenter<V extends SplashScreenMvpView> extends MvpPresenter<V> {
	String getPreferredLanguage();
}
