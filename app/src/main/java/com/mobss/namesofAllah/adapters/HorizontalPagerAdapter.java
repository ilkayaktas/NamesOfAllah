package com.mobss.namesofAllah.adapters;

import android.support.v4.view.PagerAdapter;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobss.namesofAllah.R;
import com.mobss.namesofAllah.events.FavorySelectedEvent;
import com.mobss.namesofAllah.model.app.AllahinIsimleri;
import com.mobss.namesofAllah.views.activities.base.BaseActivity;

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
    
    
    public HorizontalPagerAdapter(final BaseActivity activity, List<AllahinIsimleri> allahinIsimleri) {
        this.activity = activity;
        this.AllahinIsimleriList = allahinIsimleri;
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

        view = mLayoutInflater.inflate(R.layout.fragment_horizontal_item, container, false);
        viewHolder = new ViewHolder(view);
        view.setTag(viewHolder);
        
        if(previousPosition != position){
            previousPosition = position;
            clickCounter = 0;
        }
    
        viewHolder.imageOfName.setImageResource(activity.getResources().getIdentifier(AllahinIsimleriList.get(position).resim, "drawable", activity.getPackageName()));
        viewHolder.nameOfAllah.setText(AllahinIsimleriList.get(position).isim);
        viewHolder.nameOfAllah.setTypeface(activity.regularText);
        viewHolder.meaningOfName.setText(AllahinIsimleriList.get(position).aciklama);
        viewHolder.meaningOfName.setTypeface(activity.robotoThinText);
        viewHolder.meaningOfName.setMovementMethod(new ScrollingMovementMethod());
        viewHolder.meaningOfName.setClickable(false);

        if(AllahinIsimleriList.get(position).isFavory){
            viewHolder.favoriIcon.setImageResource(R.drawable.ic_favoried);
        } else{
            viewHolder.favoriIcon.setImageResource(R.drawable.ic_favory);
        }
        viewHolder.favoriIcon.setOnClickListener(new FavoriClicked(position));

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
        @BindView(R.id.imageview_card_favoriIcon)ImageView favoriIcon;
        @BindView(R.id.imageview_card_imageOfName)ImageView imageOfName;
        @BindView(R.id.textview_card_nameOfAllah)TextView nameOfAllah;
        @BindView(R.id.textview_card_meaningOfName)TextView meaningOfName;

        ViewHolder(View view){
            ButterKnife.bind(this, view);
        }
    }
    
    class FavoriClicked implements View.OnClickListener{
    
        int position= -1;
    
        public FavoriClicked(int position) {
            this.position = position;
        }
    
        @Override
        public void onClick(View v) {
            if(AllahinIsimleriList.get(position).isFavory){
                viewHolder.favoriIcon.setImageResource(R.drawable.ic_favory);
                AllahinIsimleriList.get(position).isFavory = false;
            } else{
                viewHolder.favoriIcon.setImageResource(R.drawable.ic_favoried);
                AllahinIsimleriList.get(position).isFavory = true;
            }
    
            FavorySelectedEvent<AllahinIsimleri> event = new FavorySelectedEvent(AllahinIsimleriList.get(position));
            EventBus.getDefault().post(event);
        }
    }
    
}
