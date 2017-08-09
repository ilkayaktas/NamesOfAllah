package com.mobss.namesofAllah.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.widget.CardView;
import android.view.ViewGroup;

import com.mobss.namesofAllah.model.app.AllahinIsimleri;
import com.mobss.namesofAllah.views.activities.base.BaseActivity;
import com.mobss.namesofAllah.views.fragments.another.AnotherFragment;
import com.mobss.namesofAllah.views.fragments.another.ShadowTransformer;

import java.util.ArrayList;
import java.util.List;

public class SlidingViewPagerAdapter extends FragmentStatePagerAdapter implements ShadowTransformer.CardAdapter {

    private BaseActivity activity;
    List<AllahinIsimleri> pages = new ArrayList<AllahinIsimleri>();
    private List<AnotherFragment> mFragments = new ArrayList<>();

    public SlidingViewPagerAdapter(BaseActivity activity, List<AllahinIsimleri> pages, FragmentManager fm) {
        super(fm);
        this.pages = pages;
        this.activity = activity;
        for (int i = 0; i < pages.size(); i++) {
            addCardFragment(pages.get(i));
        }

    }

    private float dpToPixels(int dp, Context context) {
        return dp * (context.getResources().getDisplayMetrics().density);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Object fragment = super.instantiateItem(container, position);
        mFragments.set(position, (AnotherFragment) fragment);
        return fragment;
    }

    @Override
    public float getBaseElevation() {
        return dpToPixels(0, activity);
    }

    @Override
    public CardView getCardViewAt(int position) {
        return mFragments.get(position).getCardView();
    }

    public void addCardFragment(AllahinIsimleri page) {
        mFragments.add(AnotherFragment.newInstance(page));
    }

    @Override
    public int getCount() {
        return pages.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);

    }


}
