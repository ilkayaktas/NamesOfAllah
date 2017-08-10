package com.mobss.islamic.namesofAllah.views.activities.listview;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;

import com.mobss.islamic.namesofAllah.R;
import com.mobss.islamic.namesofAllah.adapters.RecyclerViewAdapter;
import com.mobss.islamic.namesofAllah.events.FavorySelectedEvent;
import com.mobss.islamic.namesofAllah.model.app.AllahinIsimleri;
import com.mobss.islamic.namesofAllah.utils.AppConstants;
import com.mobss.islamic.namesofAllah.utils.KeyboardUtils;
import com.mobss.islamic.namesofAllah.views.activities.base.BaseActivity;
import com.yalantis.jellytoolbar.widget.JellyToolbar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ilkay on 02/08/2017.
 */

public class ListViewActivity extends BaseActivity implements ListViewMvpView {
	
	@Inject
	ListViewMvpPresenter<ListViewMvpView> mPresenter;
	@Inject
	JellyToolbar toolbar;
	
	@BindView(R.id.another_layout) LinearLayout another_layout;
	@BindView(R.id.recycler_view) RecyclerView recyclerView;

	private boolean isFavoryScreen = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_another);

		getParameters();

		getActivityComponent().inject(this);
		
		setUnBinder(ButterKnife.bind(this));
		
		// Attach presenter
		mPresenter.onAttach(ListViewActivity.this);

		drawRecyclinView(isFavoryScreen);

		setGradientBackground(another_layout);

		setOnClickListenerForJellyToolbarSelection();
	}

	private void getParameters(){
		if(getIntent().getExtras() != null) {
			isFavoryScreen = getIntent().getExtras().getBoolean(AppConstants.ACTIVITY_PARAM);
		}
	}

	@Override
	protected void onStart() {
		super.onStart();
		EventBus.getDefault().register(this);
	}

	@Override
	protected void onStop() {
		super.onStop();
		EventBus.getDefault().unregister(this);
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

	private void drawRecyclinView(boolean isFavoryScreen){
		List<AllahinIsimleri> isimler;
		if(isFavoryScreen){
			isimler = mPresenter.getFavoriIsimler();
		} else{
			isimler = mPresenter.getTumIsimler();
		}

		RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(this, isimler);
		
		RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
		recyclerView.setLayoutManager(mLayoutManager);
		recyclerView.setItemAnimator(new DefaultItemAnimator());
		recyclerView.setAdapter(recyclerViewAdapter);
	}
	
	@Subscribe(threadMode = ThreadMode.MAIN)
	public void onMessageEvent(FavorySelectedEvent<AllahinIsimleri> event) {
		AllahinIsimleri isim = event.data;

		mPresenter.updateIsim(isim);
	}

	private void setOnClickListenerForJellyToolbarSelection(){
		final AutoCompleteTextView editText = (AutoCompleteTextView) toolbar.getContentView();
		editText.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
				String isim = (String) adapterView.getItemAtPosition(i);
				if(isim != null){
					AllahinIsimleri AllahinIsmi = mPresenter.getIsim(isim);
					if(AllahinIsmi != null){
						KeyboardUtils.hideSoftInput(ListViewActivity.this);
						RecyclerViewAdapter adapter = new RecyclerViewAdapter(ListViewActivity.this, AllahinIsmi);
						recyclerView.setAdapter(adapter);
						recyclerView.invalidate();
					}
				}
			}
		});
	}
}
