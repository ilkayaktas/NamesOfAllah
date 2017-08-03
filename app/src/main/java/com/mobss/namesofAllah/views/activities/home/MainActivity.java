package com.mobss.namesofAllah.views.activities.home;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.gigamole.infinitecycleviewpager.HorizontalInfiniteCycleViewPager;
import com.mobss.namesofAllah.R;
import com.mobss.namesofAllah.adapters.HorizontalPagerAdapter;
import com.mobss.namesofAllah.events.FavorySelectedEvent;
import com.mobss.namesofAllah.model.app.AllahinIsimleri;
import com.mobss.namesofAllah.views.activities.another.AnotherActivity;
import com.mobss.namesofAllah.views.activities.base.BaseActivity;
import com.mobss.namesofAllah.views.widgets.dialogs.rateme.Config;
import com.mobss.namesofAllah.views.widgets.dialogs.rateme.RateMe;
import com.yalantis.jellytoolbar.widget.JellyToolbar;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements MainMvpView {

	/******/
	@BindView(R.id.parent_layout) RelativeLayout parent_layout;
	@BindView(R.id.viewpager_main_isimler_container) HorizontalInfiniteCycleViewPager horizontalInfiniteCycleViewPager;

	@Inject
	JellyToolbar toolbar;
	@Inject
	MainMvpPresenter<MainMvpView> mPresenter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		getActivityComponent().inject(this);
		
		setUnBinder(ButterKnife.bind(this));
		
		RateMe.init(new Config(5, 10)); // 5 gün ya da 10 defa uygulama başlattıktan sonra
		
		// Attach presenter
		mPresenter.onAttach(MainActivity.this);
		
		mPresenter.initiateNamesInDatabase();

		List<AllahinIsimleri> isimler = mPresenter.getTumIsimler();

		setGradientBackground(parent_layout);

		horizontalInfiniteCycleViewPager.setAdapter(new HorizontalPagerAdapter(this, isimler));

	}

	@Override
	protected void onStart() {
		super.onStart();
		RateMe.onStart(this);
		RateMe.showRateDialogIfNeeded(this);
	}
	
	@Override
	protected void onStop() {
		super.onStop();
	}
	
	@Override
	protected void onDestroy() {
		mPresenter.onDetach();
		super.onDestroy();
	}
	
	public static Intent getStartIntent(Context context) {
		Intent intent = new Intent(context, MainActivity.class);
		return intent;
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
		
	@Subscribe(threadMode = ThreadMode.MAIN)
	public void onMessageEvent(FavorySelectedEvent<AllahinIsimleri> event) {
		AllahinIsimleri isim = event.data;
	}

	@OnClick(R.id.iv_main_favorites)
	public void bottombarFavoritesClicked(View v){
		YoYo.with(Techniques.Tada)
				.duration(200)
				.repeat(1)
				.onStart(new YoYo.AnimatorCallback() {
					@Override
					public void call(Animator animator) {

					}
				})
				.playOn(v);
	}

	@OnClick(R.id.iv_main_goback)
	public void bottombarGobackClicked(View v){
		YoYo.with(Techniques.Tada)
				.duration(200)
				.repeat(1)
				.onStart(new YoYo.AnimatorCallback() {
					@Override
					public void call(Animator animator) {
//						horizontalInfiniteCycleViewPager.setCurrentItem(0);
					}
				})
				.playOn(v);
	}

	@OnClick(R.id.iv_main_list)
	public void bottombarListClicked(View v){
		YoYo.with(Techniques.Tada)
				.duration(200)
				.repeat(1)
				.onStart(new YoYo.AnimatorCallback() {
					@Override
					public void call(Animator animator) {
						startActivity(AnotherActivity.class);
					}
				})
				.playOn(v);
	}

	@OnClick(R.id.iv_main_random)
	public void bottombarRandomClicked(View v){
		YoYo.with(Techniques.Tada)
				.duration(200)
				.repeat(1)
				.onStart(new YoYo.AnimatorCallback() {
					@Override
					public void call(Animator animator) {
						Random generator = new Random();
						int i = generator.nextInt(100);
						Toast.makeText(MainActivity.this, ""+i, Toast.LENGTH_SHORT).show();
//						horizontalInfiniteCycleViewPager.setCurrentItem(i);
					}
				})
				.playOn(v);
	}
}
