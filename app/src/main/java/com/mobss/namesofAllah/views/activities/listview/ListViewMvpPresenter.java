package com.mobss.namesofAllah.views.activities.listview;

import com.mobss.namesofAllah.model.app.AllahinIsimleri;
import com.mobss.namesofAllah.views.activities.base.MvpPresenter;

import java.util.List;

/**
 * Created by ilkay on 02/08/2017.
 */

public interface ListViewMvpPresenter<V extends ListViewMvpView> extends MvpPresenter<V> {

    List<AllahinIsimleri> getTumIsimler();

    void updateIsim(AllahinIsimleri isim);

    List<AllahinIsimleri> getFavoriIsimler();
}
