package com.mobss.namesofAllah.views.fragments.another;


import com.mobss.namesofAllah.views.activities.base.MvpPresenter;

/**
 * Created by iaktas on 14.03.2017.
 */

public interface AnotherFragmentMvpPresenter<V extends AnotherFragmentMvpView> extends MvpPresenter<V> {
    void getIsim(int sira);
}
