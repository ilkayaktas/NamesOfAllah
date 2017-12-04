package com.mobss.islamic.namesofAllah.adapters;

import android.support.v7.widget.RecyclerView;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobss.islamic.namesofAllah.R;
import com.mobss.islamic.namesofAllah.model.events.FavorySelectedEvent;
import com.mobss.islamic.namesofAllah.model.app.AllahinIsimleri;
import com.mobss.islamic.namesofAllah.views.activities.base.BaseActivity;
import com.varunest.sparkbutton.SparkButton;
import com.varunest.sparkbutton.SparkEventListener;

import org.greenrobot.eventbus.EventBus;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by iaktas on 26.05.2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private BaseActivity activity;
    private List<AllahinIsimleri> isimler;
    
    public RecyclerViewAdapter(BaseActivity activity, List<AllahinIsimleri> isimler) {
        this.activity = activity;
        this.isimler = isimler;
    }

    public RecyclerViewAdapter(BaseActivity activity, AllahinIsimleri isim) {
        this.activity = activity;
        this.isimler = Arrays.asList(isim);
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater =  LayoutInflater.from(parent.getContext());
        View convertView = inflater.inflate(R.layout.cardview_name_item, parent, false);

        return new ViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        final AllahinIsimleri isim = isimler.get(position);
    
        viewHolder.imageOfName.setImageResource(activity.getResources().getIdentifier(isim.resim, "drawable", activity.getPackageName()));
        viewHolder.nameOfAllah.setText(isim.isim);
        viewHolder.nameOfAllah.setTypeface(activity.regularText);
        viewHolder.meaning.setText(isim.aciklama);
        viewHolder.meaning.setTypeface(activity.robotoThinText);
        viewHolder.meaning.setMovementMethod(new ScrollingMovementMethod());

        if(isim.isFavory){
            viewHolder.favoriIcon.setChecked(true);
        } else{
            viewHolder.favoriIcon.setChecked(false);
        }

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
    
    }
    

    @Override
    public int getItemCount() {
        return isimler.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.iv_carditem_imageOfName) ImageView imageOfName;
        @BindView(R.id.tv_carditem_nameOfAllah) TextView nameOfAllah;
        @BindView(R.id.tv_carditem_meaningOfName)TextView meaning;
        @BindView(R.id.iv_carditem_favoriIcon) SparkButton favoriIcon;

        public ViewHolder(View view){
            super(view);
            ButterKnife.bind(this, view);
        }
    }
    
}
