package com.mobss.namesofAllah.views.activities.another;


import com.mobss.namesofAllah.views.activities.base.BasePresenter;

/**
 * Created by ilkay on 02/08/2017.
 */

public class AnotherPresenter<V extends AnotherMvpView> extends BasePresenter<V>
		implements AnotherMvpPresenter<V> {
	public AnotherPresenter(com.mobss.namesofAllah.controller.IDataManager IDataManager) {
		super(IDataManager);
	}
}
