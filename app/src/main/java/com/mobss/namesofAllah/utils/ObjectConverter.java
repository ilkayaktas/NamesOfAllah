package com.mobss.namesofAllah.utils;


import com.mobss.namesofAllah.model.app.AllahinIsimleri;
import com.mobss.namesofAllah.model.db.realm.RealmIsim;
import com.mobss.namesofAllah.model.json.JsonIsim;

/**
 * Created by ilkay on 14/11/2016.
 */

public class ObjectConverter {

    public static RealmIsim convertIsim(JsonIsim jsonIsim){
//        RealmResults<RealmIsim> results = Data.getInstance().getDatabaseManager().get(RealmIsim.class, "sira", jsonIsim.sira);
        int zikirSayisi = 0;
//
//        if(results != null && results.size() > 0){
//            zikirSayisi = results.first().zikirSayisi;
//        }

        RealmIsim realmIsim = new RealmIsim();
        realmIsim.sira = jsonIsim.sira;
        realmIsim.aciklama = jsonIsim.aciklama;
        realmIsim.isim = jsonIsim.isim;
        realmIsim.resim = jsonIsim.resim;
        realmIsim.zikirSayisi = zikirSayisi;
        realmIsim.isFavory = false;

        return realmIsim;
    }
    
    public static AllahinIsimleri convert(RealmIsim isim) {
        AllahinIsimleri AllahinIsimleri = new AllahinIsimleri();
    
        AllahinIsimleri.sira = isim.sira;
        AllahinIsimleri.aciklama = isim.aciklama;
        AllahinIsimleri.isim = isim.isim;
        AllahinIsimleri.resim = isim.resim;
        AllahinIsimleri.zikirSayisi = isim.zikirSayisi;
        AllahinIsimleri.isFavory = isim.isFavory;

        return AllahinIsimleri;
    }

    public static RealmIsim convert(AllahinIsimleri isim) {
        RealmIsim realmIsim = new RealmIsim();
        realmIsim.sira = isim.sira;
        realmIsim.isFavory = isim.isFavory;
        realmIsim.aciklama = isim.aciklama;
        realmIsim.isim = isim.isim;
        realmIsim.resim = isim.resim;
        realmIsim.zikirSayisi = isim.zikirSayisi;

        return realmIsim;
    }
}
