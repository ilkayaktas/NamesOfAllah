package com.mobss.namesofAllah.controller.db.initializer.inflator;


import com.mobss.namesofAllah.controller.db.crud.DatabaseManager;
import com.mobss.namesofAllah.controller.db.initializer.file.IFileReader;
import com.mobss.namesofAllah.controller.db.initializer.parser.IFileParser;
import com.mobss.namesofAllah.model.db.realm.RealmIsim;
import com.mobss.namesofAllah.model.json.JsonIsim;
import com.mobss.namesofAllah.utils.ObjectConverter;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by ilkay on 23/12/2016.
 */

public class IsimlerInflator extends Inflator{
    ArrayList<JsonIsim> toBeSavedObjects = new ArrayList<>();
    @Override
    void read(IFileReader dirReader) {
        inputStreams = dirReader.getInputStreams();
    }

    @Override
    void parse(IFileParser fileParser) {
        for (InputStream file : inputStreams) {
            JsonIsim[] isimler = (JsonIsim[]) fileParser.parseInputStream(file);
            toBeSavedObjects.addAll(new ArrayList(Arrays.asList(isimler)));
        }
    }

    @Override
    void save(DatabaseManager databaseManager) {
        ArrayList<RealmIsim> kaydedilecekIsimler = new ArrayList<>();
        for (JsonIsim isim : toBeSavedObjects){
            RealmIsim rIsim = ObjectConverter.convertIsim(isim);
            kaydedilecekIsimler.add(rIsim);
        }

        databaseManager.saveOrUpdate(kaydedilecekIsimler);
    }
}
