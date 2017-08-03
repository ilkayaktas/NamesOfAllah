package com.mobss.namesofAllah.views.activities.another;

import com.mobss.namesofAllah.model.app.AllahinIsimleri;
import com.mobss.namesofAllah.views.activities.base.MvpPresenter;

import java.util.List;

/**
 * Created by ilkay on 02/08/2017.
 */

public interface AnotherMvpPresenter<V extends AnotherMvpView> extends MvpPresenter<V> {

    List<AllahinIsimleri> getTumIsimler();
}
