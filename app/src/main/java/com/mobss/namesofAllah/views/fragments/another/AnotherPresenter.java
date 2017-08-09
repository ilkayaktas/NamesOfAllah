package com.mobss.namesofAllah.views.fragments.another;


import com.mobss.namesofAllah.controller.DataManager;
import com.mobss.namesofAllah.views.activities.base.BasePresenter;

/**
 * Created by iaktas on 14.03.2017.
 */

public class AnotherPresenter<V extends AnotherMvpView> extends BasePresenter<V> implements AnotherMvpPresenter<V> {
    public AnotherPresenter(DataManager dataManager) {
        super(dataManager);
    }

}
