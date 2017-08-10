package com.mobss.islamic.namesofAllah.controller.db.initializer.inflator;


import com.mobss.islamic.namesofAllah.controller.db.initializer.file.IFileReader;
import com.mobss.islamic.namesofAllah.controller.db.initializer.parser.IFileParser;
import com.mobss.islamic.namesofAllah.controller.db.crud.DatabaseManager;

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
