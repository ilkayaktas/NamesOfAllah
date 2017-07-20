package com.mobss.namesofAllah.controller.db.initializer.inflator;


import com.mobss.namesofAllah.controller.db.crud.DatabaseManager;
import com.mobss.namesofAllah.controller.db.initializer.file.IFileReader;
import com.mobss.namesofAllah.controller.db.initializer.parser.IFileParser;

import java.io.InputStream;

public abstract class Inflator {
	
	abstract void read(IFileReader dirReader);
	abstract void parse(IFileParser fileParser);
	abstract void save(DatabaseManager databaseManager);

	protected InputStream[] inputStreams;

	public void inflate(IFileReader dirReader, IFileParser fileParser, DatabaseManager databaseManager){
		read(dirReader);
		
		parse(fileParser);
		
		save(databaseManager);
	}
}
