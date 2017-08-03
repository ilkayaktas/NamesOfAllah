package com.mobss.namesofAllah.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobss.namesofAllah.R;
import com.mobss.namesofAllah.model.app.AllahinIsimleri;
import com.mobss.namesofAllah.views.activities.base.BaseActivity;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by iaktas on 26.05.2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter {
    private RecyclerView recyclerView;
    private BaseActivity activity;
    private List<AllahinIsimleri> isimler;
    private HashMap<Integer, Boolean> expandedViews;
    
    public RecyclerViewAdapter(BaseActivity activity, List<AllahinIsimleri> isimler, RecyclerView recyclerView) {
        this.activity = activity;
        this.isimler = isimler;
        this.recyclerView = recyclerView;
        this.expandedViews = new HashMap<>();
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

        ((ViewHolder)viewHolder).imageOfName.setImageResource(activity.getResources().getIdentifier(isim.resim, "drawable", activity.getPackageName()));
        ((ViewHolder)viewHolder).nameOfAllah.setText(isim.isim);
        ((ViewHolder)viewHolder).nameOfAllah.setTypeface(activity.regularText);
        ((ViewHolder)viewHolder).meaning.setText(isim.aciklama);
        ((ViewHolder)viewHolder).meaning.setTypeface(activity.robotoThinText);
    }
    

    @Override
    public int getItemCount() {
        return isimler.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.iv_carditem_imageOfName) ImageView imageOfName;
        @BindView(R.id.tv_carditem_nameOfAllah) TextView nameOfAllah;
        @BindView(R.id.tv_carditem_meaningOfName)TextView meaning;

        ViewHolder(View view){
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
