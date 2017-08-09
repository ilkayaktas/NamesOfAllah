package com.mobss.namesofAllah.views.fragments.another;


import com.mobss.namesofAllah.model.app.AllahinIsimleri;
import com.mobss.namesofAllah.views.activities.base.BasePresenter;

/**
 * Created by iaktas on 14.03.2017.
 */

public class AnotherFragmentPresenter<V extends AnotherFragmentMvpView> extends BasePresenter<V> implements AnotherFragmentMvpPresenter<V> {
    public AnotherFragmentPresenter(com.mobss.namesofAllah.controller.IDataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void getIsim(int sira) {
        AllahinIsimleri isim = getIDataManager().getIsim(sira);
        getMvpView().drawIsim(isim);
    }
}
