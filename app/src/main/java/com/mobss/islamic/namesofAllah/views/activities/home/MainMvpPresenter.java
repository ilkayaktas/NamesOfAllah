package com.mobss.islamic.namesofAllah.views.activities.home;


import com.mobss.islamic.namesofAllah.model.app.AllahinIsimleri;
import com.mobss.islamic.namesofAllah.views.activities.base.MvpPresenter;

import java.util.List;

/**
 * Created by ilkay on 12/03/2017.
 */

public interface MainMvpPresenter<V extends MainMvpView> extends MvpPresenter<V> {
	
	void initiateNamesInDatabase();
	
	List<AllahinIsimleri> getTumIsimler();
	
	AllahinIsimleri getIsim(int sira);

	AllahinIsimleri getIsim(String isim);

	void updateIsim(AllahinIsimleri isim);

	void setPreferredLanguage(String language);

	String getPreferredLanguage();

	void setDailyNotification(boolean isAccepted);

	boolean getDailyNotification();
	
	void setDatabaseCreatedStatus(boolean isCreated);
}
