package com.mobss.namesofAllah.controller.db.initializer;

import com.mobss.namesofAllah.controller.db.crud.DatabaseManager;
import com.mobss.namesofAllah.controller.db.initializer.file.IFileReader;
import com.mobss.namesofAllah.controller.db.initializer.inflator.Inflator;
import com.mobss.namesofAllah.controller.db.initializer.parser.IFileParser;

import javax.inject.Inject;
import javax.inject.Singleton;


/**
 * Created by ilkay on 19/11/2016.
 */

@Singleton
public class DatabaseCreator {
    DatabaseManager databaseManager;
    Inflator isimInflator;
    IFileReader isimFileReader;
    IFileParser isimFileParser;
    
    @Inject
    public DatabaseCreator(DatabaseManager databaseManager,
                           Inflator isimInflator,
                           IFileReader isimFileReader,
                          IFileParser isimFileParser) {

        this.databaseManager = databaseManager;
        this.isimInflator = isimInflator;
        this.isimFileReader = isimFileReader;
        this.isimFileParser = isimFileParser;

    }

    public void createDb(){
        readIsimler();
    }
    
    private void readIsimler(){
        isimInflator.inflate(isimFileReader, isimFileParser, databaseManager);
    }
}