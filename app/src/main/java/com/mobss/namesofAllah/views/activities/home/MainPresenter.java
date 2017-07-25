package com.mobss.namesofAllah.views.activities.home;


import com.mobss.namesofAllah.controller.db.initializer.DatabaseCreator;
import com.mobss.namesofAllah.model.app.AllahinIsimleri;
import com.mobss.namesofAllah.views.activities.base.BasePresenter;

import java.util.List;

/**
 * Created by ilkay on 12/03/2017.
 */

public class MainPresenter<V extends MainMvpView> extends BasePresenter<V>
		implements MainMvpPresenter<V>{
	DatabaseCreator databaseCreator;
	
	public MainPresenter(com.mobss.namesofAllah.controller.IDataManager IDataManager, DatabaseCreator databaseCreator) {
		super(IDataManager);
		this.databaseCreator = databaseCreator;
	}
	
	@Override
	public void initiateNamesInDatabase() {
		
		// all necessary files are handled in dagger
		databaseCreator.createDb();
		
	}
	
	@Override
	public List<AllahinIsimleri> getTumIsimler() {
		List<AllahinIsimleri> isimler = getIDataManager().getTumIsimler();
		
		for (AllahinIsimleri isim : isimler) {
			System.out.println(isim.isim);
			
		}
		return isimler;
	}
	
	@Override
	public AllahinIsimleri getIsim(int sira) {
		return getIDataManager().getIsim(sira);
	}
}
