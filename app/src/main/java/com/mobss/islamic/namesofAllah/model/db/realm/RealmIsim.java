package com.mobss.islamic.namesofAllah.model.db.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by ilkay on 23/12/2016.
 */

public class RealmIsim extends RealmObject {
    @PrimaryKey
    public int sira;
    public String isim;
    public String aciklama;
    public String resim;
    public int zikirSayisi;
    public boolean isFavory;

    public RealmIsim clone(RealmIsim fromThisObject){
        RealmIsim newObj = new RealmIsim();

        newObj.sira = fromThisObject.sira;
        newObj.isim = fromThisObject.isim;
        newObj.aciklama = fromThisObject.aciklama;
        newObj.resim = fromThisObject.resim;
        newObj.zikirSayisi = fromThisObject.zikirSayisi;
        newObj.isFavory = fromThisObject.isFavory;

        return newObj;
    }
}
