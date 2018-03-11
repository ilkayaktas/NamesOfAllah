package com.mobss.islamic.namesofAllah;

import android.app.Application;
import com.evernote.android.job.JobManager;
import com.mobss.islamic.namesofAllah.controller.job.MobssJobCreator;
import com.mobss.islamic.namesofAllah.di.components.ApplicationComponent;
import com.mobss.islamic.namesofAllah.di.components.DaggerApplicationComponent;
import com.mobss.islamic.namesofAllah.di.modules.ApplicationModule;
import io.realm.Realm;

public class App extends Application {
	
	ApplicationComponent appComponent;
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		Realm.init(this);
		
		initializeInjector();

		JobManager.create(this).addJobCreator(new MobssJobCreator());
		
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