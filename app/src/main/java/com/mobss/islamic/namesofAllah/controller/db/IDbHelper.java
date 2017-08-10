package com.mobss.islamic.namesofAllah.controller.db;


import com.mobss.islamic.namesofAllah.model.app.AllahinIsimleri;

import java.util.List;

/**
 * Created by iaktas on 24/04/17.
 */

public interface IDbHelper {
	List<AllahinIsimleri> getTumIsimler();
	
	AllahinIsimleri getIsim(int sira);

	AllahinIsimleri getIsim(String isim);

	void updateIsim(AllahinIsimleri isim);

	List<AllahinIsimleri> getFavoriIsimler();
}
