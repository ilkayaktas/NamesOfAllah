package com.mobss.islamic.namesofAllah.views.fragments.slidingviewpager;


import com.mobss.islamic.namesofAllah.model.app.AllahinIsimleri;
import com.mobss.islamic.namesofAllah.views.activities.base.BasePresenter;

/**
 * Created by iaktas on 14.03.2017.
 */

public class SlidingViewPagerPresenter<V extends SlidingViewPagerMvpView> extends BasePresenter<V> implements SlidingViewPagerMvpPresenter<V> {
    public SlidingViewPagerPresenter(com.mobss.islamic.namesofAllah.controller.IDataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void getIsim(int sira) {
        AllahinIsimleri isim = getIDataManager().getIsim(sira);
        getMvpView().drawIsim(isim);
    }
}
