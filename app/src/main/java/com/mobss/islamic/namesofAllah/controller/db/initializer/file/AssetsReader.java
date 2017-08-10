package com.mobss.islamic.namesofAllah.controller.db.initializer.file;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.IOException;
import java.io.InputStream;

public class AssetsReader implements IFileReader {

	private final String assetsPath;
	private Context context;
	private String[] dosyalar;
	/**
	 *
	 * @param context
	 * @param assetsPath The path is the subdirectory of assets. If the files are under assets directory, the parameter should be "."
     */
	public AssetsReader(Context context, String assetsPath){
		this.context = context;
		this.assetsPath = assetsPath;
	}

	@Override
	public InputStream[] getInputStreams() {
		try {
			AssetManager assetManager = context.getAssets();
			dosyalar = assetManager.list(assetsPath);

			InputStream[] allFiles = new InputStream[dosyalar.length];

			for (int i = 0; i < dosyalar.length; i++) {
				allFiles[i] = assetManager.open(assetsPath+"/"+ dosyalar[i]);
			}

			return allFiles;

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public String[] getFileNames(){
		return dosyalar;
	}

}
