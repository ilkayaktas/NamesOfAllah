package com.mobss.islamic.namesofAllah.views.fragments.slidingviewpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobss.islamic.namesofAllah.R;
import com.mobss.islamic.namesofAllah.events.FavorySelectedEvent;
import com.mobss.islamic.namesofAllah.model.app.AllahinIsimleri;
import com.mobss.islamic.namesofAllah.views.activities.base.BaseFragment;
import com.varunest.sparkbutton.SparkButton;
import com.varunest.sparkbutton.SparkEventListener;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by iaktas on 14.03.2017.
 */

public class SlidingViewPagerFragment extends BaseFragment implements SlidingViewPagerMvpView {
    private static final String BUNDLE_ISIM_NO = "bundle_isim_no";
    @Inject
    SlidingViewPagerMvpPresenter<SlidingViewPagerMvpView> mPresenter;

    @BindView(R.id.cv_main_isimkarti) CardView cardView;
    @BindView(R.id.textview_card_zikirSayisi) TextView zikirSayisi;
    @BindView(R.id.imageview_card_favoriIcon)SparkButton favoriIcon;
    @BindView(R.id.imageview_card_imageOfName)ImageView imageOfName;
    @BindView(R.id.textview_card_nameOfAllah)TextView nameOfAllah;
    @BindView(R.id.textview_card_meaningOfName)TextView meaningOfName;

    public static SlidingViewPagerFragment newInstance(AllahinIsimleri isim){
        Bundle args = new Bundle();
        args.putInt(BUNDLE_ISIM_NO, isim.sira);
        SlidingViewPagerFragment fragment = new SlidingViewPagerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_horizontal_item, container, false);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this, view));

        mPresenter.onAttach(this);

        int sira = getArguments().getInt(BUNDLE_ISIM_NO);

        mPresenter.getIsim(sira);

        return view;
    }

    @Override
    public void onDestroyView() {
        mPresenter.onDetach();
        super.onDestroyView();
    }

    @Override
    public void drawIsim(final AllahinIsimleri isim) {
        imageOfName.setImageResource(getBaseActivity().getResources().getIdentifier(isim.resim, "drawable", getBaseActivity().getPackageName()));
        nameOfAllah.setText(isim.isim);
        nameOfAllah.setTypeface(getBaseActivity().regularText);
        meaningOfName.setText(isim.aciklama);
        meaningOfName.setTypeface(getBaseActivity().robotoThinText);
        meaningOfName.setMovementMethod(new ScrollingMovementMethod());
        meaningOfName.setClickable(false);

        if(isim.isFavory)favoriIcon.setChecked(true);

        favoriIcon.setEventListener(new SparkEventListener(){
            @Override
            public void onEvent(ImageView button, boolean buttonState) {
                if(buttonState){
                    isim.isFavory = true;
                } else{
                    isim.isFavory = false;
                }
                FavorySelectedEvent<AllahinIsimleri> event = new FavorySelectedEvent<>(isim);
                EventBus.getDefault().post(event);
            }

            @Override
            public void onEventAnimationEnd(ImageView button, boolean buttonState) {

            }

            @Override
            public void onEventAnimationStart(ImageView button, boolean buttonState) {

            }
        });
    }

    public CardView getCardView() {
        return cardView;
    }
}
