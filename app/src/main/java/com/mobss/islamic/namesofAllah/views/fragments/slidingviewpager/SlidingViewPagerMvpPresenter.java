package com.mobss.islamic.namesofAllah.views.fragments.slidingviewpager;


import com.mobss.islamic.namesofAllah.views.activities.base.MvpPresenter;

/**
 * Created by iaktas on 14.03.2017.
 */

public interface SlidingViewPagerMvpPresenter<V extends SlidingViewPagerMvpView> extends MvpPresenter<V> {
    void getIsim(int sira);
}
