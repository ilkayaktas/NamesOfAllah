package com.mobss.namesofAllah.di.modules;

import android.app.Application;
import android.content.Context;

import com.mobss.namesofAllah.controller.DataManager;
import com.mobss.namesofAllah.controller.IDataManager;
import com.mobss.namesofAllah.controller.api.ApiHelper;
import com.mobss.namesofAllah.controller.api.IApiHelper;
import com.mobss.namesofAllah.controller.db.DbHelper;
import com.mobss.namesofAllah.controller.db.IDbHelper;
import com.mobss.namesofAllah.controller.db.crud.DatabaseManager;
import com.mobss.namesofAllah.controller.db.crud.DatabaseMigration;
import com.mobss.namesofAllah.controller.db.crud.RealmManager;
import com.mobss.namesofAllah.controller.db.initializer.DatabaseCreator;
import com.mobss.namesofAllah.controller.db.initializer.file.AssetsReader;
import com.mobss.namesofAllah.controller.db.initializer.file.IFileReader;
import com.mobss.namesofAllah.controller.db.initializer.inflator.Inflator;
import com.mobss.namesofAllah.controller.db.initializer.inflator.IsimlerInflator;
import com.mobss.namesofAllah.controller.db.initializer.parser.IFileParser;
import com.mobss.namesofAllah.controller.db.initializer.parser.JsonIsimlerParser;
import com.mobss.namesofAllah.controller.pref.IPreferenceHelper;
import com.mobss.namesofAllah.controller.pref.PreferenceHelper;
import com.mobss.namesofAllah.di.annotations.ApplicationContext;

import java.util.Locale;

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
	
	@Provides
	@Singleton
	DatabaseCreator provideDatabaseCreator( Inflator isimInflator,
	                                        IFileReader isimFileReader,
	                                        IFileParser isimFileParser){
		
		return new DatabaseCreator(app,databaseManager,isimInflator,isimFileReader,isimFileParser);
		
	}
	
	@Provides
	IFileReader provideKategoriFileReaderInterface(){
		
		String preSetLanguage = dataManager.getPreferredLanguage();
		
		if(preSetLanguage == null){
			preSetLanguage = Locale.getDefault().getLanguage();
		}
		
		// device language is Turkish
		if (preSetLanguage.equals("tr")) {
			dataManager.setPreferredLanguage("tr");
			return new AssetsReader(app, "json_tr");
		} else{
			dataManager.setPreferredLanguage("en");
			return new AssetsReader(app, "json_en");
		}
		
	}
	
	
	@Provides
	IFileParser provideKategoriFileParser(){
		return new JsonIsimlerParser();
	}
	
	
	@Provides
	Inflator provideKavramInflator(){
		return new IsimlerInflator();
	}
}
