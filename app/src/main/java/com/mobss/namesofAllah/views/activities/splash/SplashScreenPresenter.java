package com.mobss.namesofAllah.views.activities.splash;


import com.mobss.namesofAllah.views.activities.base.BasePresenter;

/**
 * Created by ilkay on 11/03/2017.
 */

public class SplashScreenPresenter <V extends SplashScreenMvpView> extends BasePresenter<V>
		implements SplashScreenMvpPresenter<V>{
	
	public SplashScreenPresenter(com.mobss.namesofAllah.controller.IDataManager IDataManager) {
		super(IDataManager);
	}
	
}
