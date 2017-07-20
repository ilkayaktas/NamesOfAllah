package com.mobss.namesofAllah.controller.db.initializer.parser;

import java.io.File;
import java.io.InputStream;


public interface IFileParser<E>{
	E parseFile(File file);
	E parseInputStream(InputStream inputStream);
}
