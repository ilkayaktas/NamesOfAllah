package com.mobss.islamic.namesofAllah.views.activities.home;


import com.mobss.islamic.namesofAllah.controller.IDataManager;
import com.mobss.islamic.namesofAllah.controller.db.initializer.DatabaseCreator;
import com.mobss.islamic.namesofAllah.model.app.AllahinIsimleri;
import com.mobss.islamic.namesofAllah.views.activities.base.BasePresenter;

import java.util.List;

/**
 * Created by ilkay on 12/03/2017.
 */

public class MainPresenter<V extends MainMvpView> extends BasePresenter<V>
		implements MainMvpPresenter<V>{
	DatabaseCreator databaseCreator;
	
	public MainPresenter(IDataManager IDataManager, DatabaseCreator databaseCreator) {
		super(IDataManager);
		this.databaseCreator = databaseCreator;
	}
	
	@Override
	public void initiateNamesInDatabase() {

		if(!getIDataManager().getDatabaseCreatedStatus()){
			// all necessary files are handled in dagger
			databaseCreator.createDb();

			getIDataManager().setDatabaseCreatedStatus(true);
		}

	}
	
	@Override
	public List<AllahinIsimleri> getTumIsimler() {
		List<AllahinIsimleri> isimler = getIDataManager().getTumIsimler();
		
		for (AllahinIsimleri isim : isimler) {
			System.out.println(isim.isim + " " + isim.isFavory);
			
		}
		return isimler;
	}
	
	@Override
	public AllahinIsimleri getIsim(int sira) {
		return getIDataManager().getIsim(sira);
	}

	@Override
	public AllahinIsimleri getIsim(String isim) {
		return getIDataManager().getIsim(isim);
	}

	@Override
	public void updateIsim(AllahinIsimleri isim) {
		getIDataManager().updateIsim(isim);
	}

	@Override
	public void setPreferredLanguage(String language) {
		getIDataManager().setPreferredLanguage(language);
	}

	@Override
	public String getPreferredLanguage() {
		return getIDataManager().getPreferredLanguage();
	}

	@Override
	public void setDailyNotification(boolean isAccepted) {
		getIDataManager().setDailyNotification(isAccepted);
	}

	@Override
	public boolean getDailyNotification() {
		return getIDataManager().getDailyNotification();
	}

	@Override
	public boolean containsDailyNotification() {
		return getIDataManager().containsDailyNotification();
	}

	@Override
	public void setDatabaseCreatedStatus(boolean isCreated) {
		getIDataManager().setDatabaseCreatedStatus(isCreated);
	}
}
