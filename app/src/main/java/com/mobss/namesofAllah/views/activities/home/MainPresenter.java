package com.mobss.namesofAllah.views.activities.home;


import com.mobss.namesofAllah.views.activities.base.BasePresenter;

/**
 * Created by ilkay on 12/03/2017.
 */

public class MainPresenter<V extends MainMvpView> extends BasePresenter<V>
		implements MainMvpPresenter<V>{
	
	public MainPresenter(com.mobss.namesofAllah.controller.IDataManager IDataManager) {
		super(IDataManager);
	}
	
}
