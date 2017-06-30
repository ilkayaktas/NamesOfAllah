package com.mobss.namesofAllah;

import android.app.Application;

import com.mobss.namesofAllah.controller.IDataManager;
import com.mobss.namesofAllah.di.components.ApplicationComponent;
import com.mobss.namesofAllah.di.modules.ApplicationModule;

import javax.inject.Inject;

import io.realm.Realm;

public class App extends Application {
	
	ApplicationComponent appComponent;
	
	@Inject
	IDataManager mIDataManager;
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		Realm.init(this);
		
		initializeInjector();
		
	}
	
	private void initializeInjector(){
		appComponent = DaggerApplicationComponent.builder()
				.applicationModule(new ApplicationModule(this))
				.build();
		
		appComponent.inject(this);
		
	}
	
	public ApplicationComponent getAppComponent(){
		return appComponent;
	}
	
}