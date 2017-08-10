package com.mobss.islamic.namesofAllah.views.activities.home;

import android.animation.Animator;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.mobss.islamic.namesofAllah.adapters.SlidingViewPagerAdapter;
import com.mobss.islamic.namesofAllah.events.FavorySelectedEvent;
import com.mobss.islamic.namesofAllah.model.app.AllahinIsimleri;
import com.mobss.islamic.namesofAllah.utils.AppConstants;
import com.mobss.islamic.namesofAllah.utils.KeyboardUtils;
import com.mobss.islamic.namesofAllah.views.activities.base.BaseActivity;
import com.mobss.islamic.namesofAllah.views.activities.listview.ListViewActivity;
import com.mobss.islamic.namesofAllah.views.fragments.slidingviewpager.ShadowTransformer;
import com.mobss.islamic.namesofAllah.views.widgets.dialogs.rateme.Config;
import com.mobss.islamic.namesofAllah.views.widgets.dialogs.rateme.RateMe;
import com.mobss.namesofAllah.R;
import com.varunest.sparkbutton.SparkButton;
import com.varunest.sparkbutton.SparkEventListener;
import com.yalantis.jellytoolbar.widget.JellyToolbar;

import org.greenrobot.eventbus.EventBus;
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
//	@BindView(R.id.viewpager_main_isimler_container) HorizontalInfiniteCycleViewPager horizontalInfiniteCycleViewPager;
	@BindView(R.id.vp_main_isimler_container) ViewPager slidingViewPagerViewPager;
	@BindView(R.id.iv_main_language)SparkButton languageIcon;

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

		setGradientBackground(parent_layout);

		setOnClickListenerForSparkLanguageButton();

		setOnClickListenerForJellyToolbarSelection();

		List<AllahinIsimleri> isimler = mPresenter.getTumIsimler();
//		horizontalInfiniteCycleViewPager.setAdapter(new HorizontalPagerAdapter(this, isimler));
		setOnboardPages(isimler);
	}

	@Override
	protected void onStart() {
		super.onStart();
		RateMe.onStart(this);
		RateMe.showRateDialogIfNeeded(this);
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
	protected void onResume() {
		super.onResume();
		
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

		mPresenter.updateIsim(isim);
	}

	private void setOnClickListenerForSparkLanguageButton(){
		
		setLanguageIcon();
				
		languageIcon.setEventListener(new SparkEventListener(){
			@Override
			public void onEvent(ImageView button, boolean buttonState) {
				createCustomDialog(R.layout.settings_dialog).show();
			}

			@Override
			public void onEventAnimationEnd(ImageView button, boolean buttonState) {
				languageIcon.setChecked(false);
			}

			@Override
			public void onEventAnimationStart(ImageView button, boolean buttonState) {

			}
		});

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
						slidingViewPagerViewPager.setCurrentItem(AllahinIsmi.sira-1);
						KeyboardUtils.hideSoftInput(MainActivity.this);
					}
				}
			}
		});
	}

	public void setOnboardPages(List<AllahinIsimleri> isimler) {

		SlidingViewPagerAdapter fragmentAdapter = new SlidingViewPagerAdapter(this, isimler, getSupportFragmentManager());
		ShadowTransformer mCardShadowTransformer = new ShadowTransformer(slidingViewPagerViewPager, fragmentAdapter);
		mCardShadowTransformer.enableScaling(true);
		slidingViewPagerViewPager.setAdapter(fragmentAdapter);
		slidingViewPagerViewPager.setPageTransformer(false, mCardShadowTransformer);
	}

	@OnClick(R.id.iv_main_goback)
	public void bottombarGobackClicked(View v){
		YoYo.with(Techniques.FadeIn)
				.duration(200)
				.repeat(1)
				.onEnd(new YoYo.AnimatorCallback() {
					@Override
					public void call(Animator animator) {
//						horizontalInfiniteCycleViewPager.setCurrentItem(0, true);
						slidingViewPagerViewPager.setCurrentItem(0);
					}
				})
				.playOn(v);
	}

	@OnClick(R.id.iv_main_random)
	public void bottombarRandomClicked(View v){
		YoYo.with(Techniques.FadeIn)
				.duration(200)
				.repeat(1)
				.onEnd(new YoYo.AnimatorCallback() {
					@Override
					public void call(Animator animator) {
						Random generator = new Random();
						int i = generator.nextInt(100);
//						horizontalInfiniteCycleViewPager.setCurrentItem(i, true);
						slidingViewPagerViewPager.setCurrentItem(i);
					}
				})
				.playOn(v);
	}

	@OnClick(R.id.iv_main_favorites)
	public void bottombarFavoritesClicked(View v){
		YoYo.with(Techniques.FadeIn)
				.duration(200)
				.repeat(1)
				.onEnd(new YoYo.AnimatorCallback() {
					@Override
					public void call(Animator animator) {
						Intent intent = new Intent(MainActivity.this, ListViewActivity.class);
						intent.putExtra(AppConstants.ACTIVITY_PARAM, true);
						startActivity(intent);
					}
				})
				.playOn(v);
	}

	@OnClick(R.id.iv_main_list)
	public void bottombarListClicked(View v){
		YoYo.with(Techniques.FadeIn)
				.duration(200)
				.repeat(1)
				.onEnd(new YoYo.AnimatorCallback() {
					@Override
					public void call(Animator animator) {
						startActivity(ListViewActivity.class);
					}
				})
				.playOn(v);
	}

	private void setLanguageIcon(){
		String lang = mPresenter.getPreferredLanguage();
		if(lang.equals(AppConstants.LANGUAGE_EN)){
			languageIcon.setActiveImage(R.drawable.ic_english);
			languageIcon.setInactiveImage(R.drawable.ic_english);
		} else if(lang.equals(AppConstants.LANGUAGE_TR)){
			languageIcon.setActiveImage(R.drawable.ic_turkish);
			languageIcon.setInactiveImage(R.drawable.ic_turkish);
		}
	}
	
	private Dialog createCustomDialog(int layoutId){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		// Get the layout inflater
		LayoutInflater inflater = getLayoutInflater();

		// Pass null as the parent view because its going in the dialog layout
		View layoutview = inflater.inflate(layoutId, null);
		builder.setView(layoutview);

		final Dialog dialog = builder.create();

		layoutview.findViewById(R.id.layoutEnglish).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				dialog.dismiss();
				
				mPresenter.setDatabaseCreatedStatus(false);
				mPresenter.setPreferredLanguage(AppConstants.LANGUAGE_EN);
				
				languageIcon.setActiveImage(R.drawable.ic_english);
				languageIcon.setInactiveImage(R.drawable.ic_english);

				// Use the Builder class for convenient dialog construction
				AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
				builder.setMessage("To change language, please restart application.").show();
			}
		});

		layoutview.findViewById(R.id.layoutTurkce).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				dialog.dismiss();
				
				mPresenter.setDatabaseCreatedStatus(false);
				mPresenter.setPreferredLanguage(AppConstants.LANGUAGE_TR);
				
				languageIcon.setActiveImage(R.drawable.ic_turkish);
				languageIcon.setInactiveImage(R.drawable.ic_turkish);

				// Use the Builder class for convenient dialog construction
				AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
				builder.setMessage("Dili değiştirmek için lütfen uygulamayı yeniden başlatın.").show();
			}
		});

		return dialog;
	}

}
