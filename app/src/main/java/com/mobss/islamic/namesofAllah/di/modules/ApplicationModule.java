package com.mobss.islamic.namesofAllah.di.modules;

import android.app.Application;
import android.content.Context;

import com.mobss.islamic.namesofAllah.controller.IDataManager;
import com.mobss.islamic.namesofAllah.controller.api.ApiHelper;
import com.mobss.islamic.namesofAllah.controller.api.IApiHelper;
import com.mobss.islamic.namesofAllah.controller.db.crud.DatabaseMigration;
import com.mobss.islamic.namesofAllah.controller.db.crud.RealmManager;
import com.mobss.islamic.namesofAllah.controller.pref.IPreferenceHelper;
import com.mobss.islamic.namesofAllah.controller.DataManager;
import com.mobss.islamic.namesofAllah.controller.db.DbHelper;
import com.mobss.islamic.namesofAllah.controller.db.IDbHelper;
import com.mobss.islamic.namesofAllah.controller.db.crud.DatabaseManager;
import com.mobss.islamic.namesofAllah.controller.pref.PreferenceHelper;
import com.mobss.islamic.namesofAllah.di.annotations.ApplicationContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by ilkay on 09/03/2017.
 */

@Module
public class ApplicationModule {
	
	private final Application app;
	private RealmConfiguration realmConfiguration = null;
	private DatabaseManager databaseManager = null;
	private DataManager dataManager = null;
	
	public ApplicationModule(Application app) {
		this.app = app;
	}
	
	@Provides
	@ApplicationContext
	Context provideContext() {
		return app;
	}
	
	@Provides
	Application provideApplication(){
		return app;
	}
	
	@Provides
	@Singleton
    IDataManager provideDataManager(@ApplicationContext Context context, IDbHelper mIDbHelper, IPreferenceHelper mIPreferenceHelper, IApiHelper mIApiHelper) {
		if(dataManager == null) {
			dataManager = new DataManager(context, mIDbHelper, mIPreferenceHelper, mIApiHelper);
		}
		return dataManager;
	}
	
	@Provides
	@Singleton
	IDbHelper provideDbHelper(DatabaseManager databaseManager) {
		return new DbHelper(databaseManager);
	}
	
	@Provides
	@Singleton
	DatabaseManager provideDatabaseManager(Realm realm){
		if(databaseManager == null) {
			databaseManager = new RealmManager(realm);
		}
		return databaseManager;
	}
	
	@Provides
	@Singleton
	Realm provideRealm(){
		
		if(realmConfiguration == null) {
			// Create a RealmConfiguration that saves the Realm file in the app's "files" directory.
			realmConfiguration = new RealmConfiguration.Builder()
					.name("islamic.db")
					.migration(new DatabaseMigration())
					.encryptionKey(new String("YhvPohxPIDXI8wneZTgYwFElAuSeWOhea8WILKRvuHeiOQYaz1RLZ4m0ZEaAP7Gc").getBytes())
					.schemaVersion(1)
					.build();
		}
		
		// Get a Realm instance for this thread
		Realm realm = Realm.getInstance(realmConfiguration);
		
		return realm;
	}
	
	@Provides
	@Singleton
	IPreferenceHelper providePreferencesHelper(@ApplicationContext Context context) {
		return new PreferenceHelper(context);
	}
	
	@Provides
	@Singleton
	IApiHelper provideApiHelper() {
		return new ApiHelper();
	}
	

}
