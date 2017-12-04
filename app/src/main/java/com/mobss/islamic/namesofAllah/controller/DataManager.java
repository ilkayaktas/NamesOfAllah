package com.mobss.islamic.namesofAllah.controller;

import android.content.Context;
import com.mobss.islamic.namesofAllah.controller.api.IApiHelper;
import com.mobss.islamic.namesofAllah.controller.db.IDbHelper;
import com.mobss.islamic.namesofAllah.controller.pref.IPreferenceHelper;
import com.mobss.islamic.namesofAllah.di.annotations.ApplicationContext;
import com.mobss.islamic.namesofAllah.model.app.AllahinIsimleri;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;


/**
 * Created by ilkay on 11/03/2017.
 */

@Singleton
public class DataManager implements IDataManager {
	
	private final Context mContext;
	private final IDbHelper mIDbHelper;
	private final IPreferenceHelper mIPreferenceHelper;
	private final IApiHelper mIApiHelper;
	
	@Inject
	public DataManager(@ApplicationContext Context mContext, IDbHelper mIDbHelper, IPreferenceHelper mIPreferenceHelper, IApiHelper mIApiHelper) {
		this.mContext = mContext;
		this.mIDbHelper = mIDbHelper;
		this.mIPreferenceHelper = mIPreferenceHelper;
		this.mIApiHelper = mIApiHelper;
	}

	@Override
	public boolean getDatabaseCreatedStatus() {
		return mIPreferenceHelper.getDatabaseCreatedStatus();
	}

	@Override
	public void setDatabaseCreatedStatus(boolean isCreated) {
		mIPreferenceHelper.setDatabaseCreatedStatus(isCreated);
	}
	
	@Override
	public String getPreferredLanguage() {
		return mIPreferenceHelper.getPreferredLanguage();
	}
	
	@Override
	public void setPreferredLanguage(String language) {
		mIPreferenceHelper.setPreferredLanguage(language);
	}

	@Override
	public void setDailyNotification(boolean isAccepted) {
		mIPreferenceHelper.setDailyNotification(isAccepted);
	}

	@Override
	public boolean getDailyNotification() {
		return mIPreferenceHelper.getDailyNotification();
	}

	@Override
	public List<AllahinIsimleri> getTumIsimler() {
		return mIDbHelper.getTumIsimler();
	}
	
	@Override
	public AllahinIsimleri getIsim(int sira) {
		return mIDbHelper.getIsim(sira);
	}

	@Override
	public AllahinIsimleri getIsim(String isim) {
		return mIDbHelper.getIsim(isim);
	}

	@Override
	public void updateIsim(AllahinIsimleri isim) {
		mIDbHelper.updateIsim(isim);
	}

	@Override
	public List<AllahinIsimleri> getFavoriIsimler() {
		return mIDbHelper.getFavoriIsimler();
	}
}
