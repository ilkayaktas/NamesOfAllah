package com.mobss.islamic.namesofAllah.views.activities.listview;


import com.mobss.islamic.namesofAllah.model.app.AllahinIsimleri;
import com.mobss.islamic.namesofAllah.views.activities.base.BasePresenter;

import java.util.List;

/**
 * Created by ilkay on 02/08/2017.
 */

public class ListViewPresenter<V extends ListViewMvpView> extends BasePresenter<V>
		implements ListViewMvpPresenter<V> {
	public ListViewPresenter(com.mobss.islamic.namesofAllah.controller.IDataManager IDataManager) {
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

	@Override
	public AllahinIsimleri getIsim(String isim) {
		return getIDataManager().getIsim(isim);
	}
}
