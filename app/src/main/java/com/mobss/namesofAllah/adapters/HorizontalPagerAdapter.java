package com.mobss.namesofAllah.adapters;

import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobss.namesofAllah.R;
import com.mobss.namesofAllah.model.app.AllahinIsimleri;
import com.mobss.namesofAllah.views.activities.base.BaseActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by GIGAMOLE on 7/27/16.
 */
public class HorizontalPagerAdapter extends PagerAdapter {

    private BaseActivity activity;
    private List<AllahinIsimleri> AllahinIsimleris;
    private LayoutInflater mLayoutInflater;
    private int clickCounter = 0;
    private int previousPosition = -1;
    private View view = null;

    public HorizontalPagerAdapter(final BaseActivity activity, List<AllahinIsimleri> allahinIsimleri) {
        this.activity = activity;
        this.AllahinIsimleris = allahinIsimleri;
        mLayoutInflater = LayoutInflater.from(activity);
    }

    @Override
    public int getCount() {
        return AllahinIsimleris.size();
    }

    @Override
    public int getItemPosition(final Object object) {
        return POSITION_NONE;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {

        final ViewHolder viewHolder;

            view = mLayoutInflater.inflate(R.layout.fragment_horizontal_item, container, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);

        if(previousPosition != position){
            previousPosition = position;
            clickCounter = 0;
        }





        viewHolder.nameOfAllah.setText(AllahinIsimleris.get(position).isim);
        viewHolder.imageOfName.setImageResource(R.drawable.ic_01_allah);
        viewHolder.meaningOfName.setText(AllahinIsimleris.get(position).aciklama);

        if(AllahinIsimleris.get(position).isFavory){
            viewHolder.favoriIcon.setImageResource(R.drawable.ic_favoried);
        } else{
            viewHolder.favoriIcon.setImageResource(R.drawable.ic_favory);
        }

        viewHolder.favoriIcon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(AllahinIsimleris.get(position).isFavory){
                    viewHolder.favoriIcon.setImageResource(R.drawable.ic_favory);
                    AllahinIsimleris.get(position).isFavory = false;
                } else{
                    viewHolder.favoriIcon.setImageResource(R.drawable.ic_favoried);
                    AllahinIsimleris.get(position).isFavory = true;
                }

//                EventBus.getDefault().post(new FavoriSelectedEvent(AllahinIsimleris.get(position)));
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
        @BindView(R.id.imageview_card_favoriIcon)ImageView favoriIcon;
        @BindView(R.id.imageview_card_imageOfName)ImageView imageOfName;
        @BindView(R.id.textview_card_nameOfAllah)TextView nameOfAllah;
        @BindView(R.id.textview_card_meaningOfName)TextView meaningOfName;

        ViewHolder(View view){
            ButterKnife.bind(this, view);
        }
    }
}
