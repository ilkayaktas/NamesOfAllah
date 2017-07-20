package com.mobss.namesofAllah.views.activities.home;


import com.mobss.namesofAllah.model.app.AllahinIsimleri;
import com.mobss.namesofAllah.views.activities.base.MvpPresenter;

import java.util.List;

/**
 * Created by ilkay on 12/03/2017.
 */

public interface MainMvpPresenter<V extends MainMvpView> extends MvpPresenter<V> {
	
	void initiateNamesInDatabase();
	
	List<AllahinIsimleri> getTumIsimler();
	
	AllahinIsimleri getIsim(int sira);
}
