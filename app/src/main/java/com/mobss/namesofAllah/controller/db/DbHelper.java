package com.mobss.namesofAllah.controller.db;


import com.mobss.namesofAllah.controller.db.crud.DatabaseManager;
import com.mobss.namesofAllah.model.app.AllahinIsimleri;
import com.mobss.namesofAllah.model.db.realm.RealmIsim;
import com.mobss.namesofAllah.utils.ObjectConverter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.realm.RealmResults;


/**
 * Created by ilkay on 12/03/2017.
 */

@Singleton
public class DbHelper implements IDbHelper {
	
	private final DatabaseManager databaseManager;
	
	@Inject
	public DbHelper(DatabaseManager databaseManager) {
		this.databaseManager = databaseManager;
	}
	
	@Override
	public List<AllahinIsimleri> getTumIsimler() {
		List<RealmIsim> isimler = ((RealmResults)databaseManager.getAll(RealmIsim.class)).sort("sira");
		List<AllahinIsimleri> AllahinIsimleri = new ArrayList<>();
		for (RealmIsim isim : isimler) {
			AllahinIsimleri.add(ObjectConverter.convert(isim));
		}
		return AllahinIsimleri;
	}
	
	@Override
	public AllahinIsimleri getIsim(int sira) {
		List<RealmIsim> isimler = databaseManager.get(RealmIsim.class, "sira", sira);
		List<AllahinIsimleri> AllahinIsimleri = new ArrayList<>();
		for (RealmIsim isim : isimler) {
			AllahinIsimleri.add(ObjectConverter.convert(isim));
		}
		return AllahinIsimleri.get(0);
	}

	@Override
	public void updateIsim(AllahinIsimleri isim) {
		RealmIsim realmIsim = ObjectConverter.convert(isim);
		databaseManager.saveOrUpdate(realmIsim);
	}

	@Override
	public List<AllahinIsimleri> getFavoriIsimler() {
		List<RealmIsim> isimler = ((RealmResults)databaseManager.get(RealmIsim.class, "isFavory", true)).sort("sira");
		List<AllahinIsimleri> AllahinIsimleri = new ArrayList<>();
		for (RealmIsim isim : isimler) {
			AllahinIsimleri.add(ObjectConverter.convert(isim));
		}
		return AllahinIsimleri;
	}
}
