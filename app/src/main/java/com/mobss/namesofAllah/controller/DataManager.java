package com.mobss.namesofAllah.controller;

import android.content.Context;

import com.mobss.namesofAllah.controller.api.IApiHelper;
import com.mobss.namesofAllah.controller.db.IDbHelper;
import com.mobss.namesofAllah.controller.pref.IPreferenceHelper;
import com.mobss.namesofAllah.di.annotations.ApplicationContext;
import com.mobss.namesofAllah.model.app.AllahinIsimleri;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;


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
	public void setDatabaseCreatedStatus() {
		mIPreferenceHelper.setDatabaseCreatedStatus();
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
	public List<AllahinIsimleri> getTumIsimler() {
		return mIDbHelper.getTumIsimler();
	}
	
	@Override
	public AllahinIsimleri getIsim(int sira) {
		return mIDbHelper.getIsim(sira);
	}
}
