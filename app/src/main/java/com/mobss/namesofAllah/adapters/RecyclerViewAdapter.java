package com.mobss.namesofAllah.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
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
 * Created by iaktas on 26.05.2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter {
    private BaseActivity activity;
    private List<AllahinIsimleri> isimler;
    private ViewHolder viewHolder;
    
    public RecyclerViewAdapter(BaseActivity activity, List<AllahinIsimleri> isimler) {
        this.activity = activity;
        this.isimler = isimler;
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View convertView = inflater.inflate(R.layout.cardview_name_item, parent, false);

        return new ViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, final int position) {
        AllahinIsimleri isim = isimler.get(position);
    
        this.viewHolder = (ViewHolder)viewHolder;
        
        this.viewHolder.imageOfName.setImageResource(activity.getResources().getIdentifier(isim.resim, "drawable", activity.getPackageName()));
        this.viewHolder.nameOfAllah.setText(isim.isim);
        this.viewHolder.nameOfAllah.setTypeface(activity.regularText);
        this.viewHolder.meaning.setText(isim.aciklama);
        this.viewHolder.meaning.setTypeface(activity.robotoThinText);
        this.viewHolder.meaning.setMovementMethod(new ScrollingMovementMethod());
    
        if(isim.isFavory){
            this.viewHolder.favoriIcon.setImageResource(R.drawable.ic_favoried);
        } else{
            this.viewHolder.favoriIcon.setImageResource(R.drawable.ic_favory);
        }
        this.viewHolder.favoriIcon.setOnClickListener(new FavoriClicked(isim, position));
    
    }
    

    @Override
    public int getItemCount() {
        return isimler.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.iv_carditem_imageOfName) ImageView imageOfName;
        @BindView(R.id.tv_carditem_nameOfAllah) TextView nameOfAllah;
        @BindView(R.id.tv_carditem_meaningOfName)TextView meaning;
        @BindView(R.id.iv_carditem_favoriIcon) ImageView favoriIcon;

        ViewHolder(View view){
            super(view);
            ButterKnife.bind(this, view);
        }
    }
    
    class FavoriClicked implements View.OnClickListener{
    
        AllahinIsimleri isim;
        int position= -1;
        
        public FavoriClicked(AllahinIsimleri isim, int position) {
            this.isim = isim;
            this.position = position;
        }
        
        @Override
        public void onClick(View v) {
            if(isim.isFavory){
                viewHolder.favoriIcon.setImageResource(R.drawable.ic_favory);
                isim.isFavory = false;
            } else{
                viewHolder.favoriIcon.setImageResource(R.drawable.ic_favoried);
                isim.isFavory = true;
            }
            
            FavorySelectedEvent<AllahinIsimleri> event = new FavorySelectedEvent(isim);
            EventBus.getDefault().post(event);
        }
    }
}
