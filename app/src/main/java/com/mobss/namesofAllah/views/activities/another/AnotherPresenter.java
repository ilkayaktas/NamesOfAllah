package com.mobss.namesofAllah.views.activities.another;


import com.mobss.namesofAllah.model.app.AllahinIsimleri;
import com.mobss.namesofAllah.views.activities.base.BasePresenter;

import java.util.List;

/**
 * Created by ilkay on 02/08/2017.
 */

public class AnotherPresenter<V extends AnotherMvpView> extends BasePresenter<V>
		implements AnotherMvpPresenter<V> {
	public AnotherPresenter(com.mobss.namesofAllah.controller.IDataManager IDataManager) {
		super(IDataManager);
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
	public void updateIsim(AllahinIsimleri isim) {
		getIDataManager().updateIsim(isim);
	}

	@Override
	public List<AllahinIsimleri> getFavoriIsimler() {
		List<AllahinIsimleri> isimler = getIDataManager().getFavoriIsimler();

		for (AllahinIsimleri isim : isimler) {
			System.out.println(isim.isim + " " + isim.isFavory);
		}
		return isimler;
	}
}
