package com.mobss.namesofAllah.views.activities.another;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.widget.LinearLayout;

import com.mobss.namesofAllah.R;
import com.mobss.namesofAllah.adapters.RecyclerViewAdapter;
import com.mobss.namesofAllah.events.FavorySelectedEvent;
import com.mobss.namesofAllah.model.app.AllahinIsimleri;
import com.mobss.namesofAllah.views.activities.base.BaseActivity;
import com.yalantis.jellytoolbar.widget.JellyToolbar;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ilkay on 02/08/2017.
 */

public class AnotherActivity extends BaseActivity implements AnotherMvpView {
	
	@Inject
	AnotherMvpPresenter<AnotherMvpView> mPresenter;
	@Inject
	JellyToolbar toolbar;
	
	@BindView(R.id.another_layout) LinearLayout another_layout;
	@BindView(R.id.recycler_view) RecyclerView recyclerView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_another);
		
		getActivityComponent().inject(this);
		
		setUnBinder(ButterKnife.bind(this));
		
		// Attach presenter
		mPresenter.onAttach(AnotherActivity.this);

		drawRecyclinView();

		setGradientBackground(another_layout);
	}
	
	@Override
	protected void onDestroy() {
		mPresenter.onDetach();
		super.onDestroy();
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			
			finish();
			
			return true;
		} else {
			return super.onKeyDown(keyCode, event);
		}
	}

	private void drawRecyclinView(){
		List<AllahinIsimleri> isimler = mPresenter.getTumIsimler();
		
		RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(this, isimler);
		
		RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
		recyclerView.setLayoutManager(mLayoutManager);
		recyclerView.setItemAnimator(new DefaultItemAnimator());
		recyclerView.setAdapter(recyclerViewAdapter);
	}
	
	@Subscribe(threadMode = ThreadMode.MAIN)
	public void onMessageEvent(FavorySelectedEvent<AllahinIsimleri> event) {
		AllahinIsimleri isim = event.data;
	}
}
