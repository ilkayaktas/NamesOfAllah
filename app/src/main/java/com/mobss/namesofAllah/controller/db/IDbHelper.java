package com.mobss.namesofAllah.controller.db;


import com.mobss.namesofAllah.model.app.AllahinIsimleri;

import java.util.List;

/**
 * Created by iaktas on 24/04/17.
 */

public interface IDbHelper {
	List<AllahinIsimleri> getTumIsimler();
	
	AllahinIsimleri getIsim(int sira);
}
