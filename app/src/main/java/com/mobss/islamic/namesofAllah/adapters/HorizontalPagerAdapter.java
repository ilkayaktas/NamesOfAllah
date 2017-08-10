package com.mobss.islamic.namesofAllah.adapters;

import android.support.v4.view.PagerAdapter;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobss.namesofAllah.R;
import com.mobss.islamic.namesofAllah.events.FavorySelectedEvent;
import com.mobss.islamic.namesofAllah.model.app.AllahinIsimleri;
import com.mobss.islamic.namesofAllah.views.activities.base.BaseActivity;
import com.varunest.sparkbutton.SparkButton;
import com.varunest.sparkbutton.SparkEventListener;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by GIGAMOLE on 7/27/16.
 */
public class HorizontalPagerAdapter extends PagerAdapter {

    private BaseActivity activity;
    private List<AllahinIsimleri> AllahinIsimleriList;
    private LayoutInflater mLayoutInflater;
    private int clickCounter = 0;
    private int previousPosition = -1;
    private View view = null;
    private ViewHolder viewHolder;
    
    
    public HorizontalPagerAdapter(final BaseActivity activity, List<AllahinIsimleri> AllahinIsimleri) {
        this.activity = activity;
        this.AllahinIsimleriList = AllahinIsimleri;
        mLayoutInflater = LayoutInflater.from(activity);
    }

    @Override
    public int getCount() {
        return AllahinIsimleriList.size();
    }

    @Override
    public int getItemPosition(final Object object) {
        return POSITION_NONE;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        final AllahinIsimleri isim = AllahinIsimleriList.get(position);

        view = mLayoutInflater.inflate(R.layout.fragment_horizontal_item, container, false);
        viewHolder = new ViewHolder(view);
        view.setTag(viewHolder);
        
        if(previousPosition != position){
            previousPosition = position;
            clickCounter = 0;
        }

        viewHolder.imageOfName.setImageResource(activity.getResources().getIdentifier(isim.resim, "drawable", activity.getPackageName()));
        viewHolder.nameOfAllah.setText(isim.isim);
        viewHolder.nameOfAllah.setTypeface(activity.regularText);
        viewHolder.meaningOfName.setText(isim.aciklama);
        viewHolder.meaningOfName.setTypeface(activity.robotoThinText);
        viewHolder.meaningOfName.setMovementMethod(new ScrollingMovementMethod());
        viewHolder.meaningOfName.setClickable(false);

        if(isim.isFavory)viewHolder.favoriIcon.setChecked(true);

        viewHolder.favoriIcon.setEventListener(new SparkEventListener(){
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

        container.addView(view);
        return view;
    }

    @Override
    public boolean isViewFromObject(final View view, final Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(final ViewGroup container, final int position, final Object object) {
        container.removeView((View) object);
    }

    public class ViewHolder{
        @BindView(R.id.textview_card_zikirSayisi) TextView zikirSayisi;
        @BindView(R.id.imageview_card_favoriIcon)SparkButton favoriIcon;
        @BindView(R.id.imageview_card_imageOfName)ImageView imageOfName;
        @BindView(R.id.textview_card_nameOfAllah)TextView nameOfAllah;
        @BindView(R.id.textview_card_meaningOfName)TextView meaningOfName;

        ViewHolder(View view){
            ButterKnife.bind(this, view);
        }
    }
    

}
