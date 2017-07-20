package com.mobss.namesofAllah.controller.db.initializer.file;

import java.io.InputStream;

public interface IFileReader {
	InputStream[] getInputStreams();
	String[] getFileNames();
}
