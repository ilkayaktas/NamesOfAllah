package com.mobss.namesofAllah.controller.db;


import com.mobss.namesofAllah.controller.db.crud.DatabaseManager;

import javax.inject.Inject;
import javax.inject.Singleton;


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
	
}
