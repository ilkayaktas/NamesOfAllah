package com.mobss.namesofAllah.views.activities.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.RelativeLayout;

import com.gigamole.infinitecycleviewpager.HorizontalInfiniteCycleViewPager;
import com.mobss.namesofAllah.R;
import com.mobss.namesofAllah.adapters.HorizontalPagerAdapter;
import com.mobss.namesofAllah.events.FavorySelectedEvent;
import com.mobss.namesofAllah.model.app.AllahinIsimleri;
import com.mobss.namesofAllah.views.activities.base.BaseActivity;
import com.mobss.namesofAllah.views.widgets.dialogs.rateme.Config;
import com.mobss.namesofAllah.views.widgets.dialogs.rateme.RateMe;
import com.yalantis.jellytoolbar.listener.JellyListener;
import com.yalantis.jellytoolbar.widget.JellyToolbar;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainMvpView {

	/******/
	@BindView(R.id.parent_layout) RelativeLayout parent_layout;
	@BindView(R.id.viewpager_main_isimler_container) HorizontalInfiniteCycleViewPager horizontalInfiniteCycleViewPager;

	private JellyToolbar toolbar;
	private AutoCompleteTextView editText;
	private JellyListener jellyListener = new JellyListener() {
		@Override
		public void onCancelIconClicked() {
			if (TextUtils.isEmpty(editText.getText())) {
				toolbar.collapse();
			} else {
				editText.getText().clear();
			}
		}
	};


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


		/******/
		toolbar = (JellyToolbar) findViewById(R.id.jellytoolbar_main_toolbar);
		toolbar.getToolbar().setNavigationIcon(R.mipmap.ic_launcher);
		toolbar.setJellyListener(jellyListener);
		toolbar.getToolbar().setPadding(0, getStatusBarHeight(), 0, 0);

		editText = (AutoCompleteTextView) LayoutInflater.from(this).inflate(R.layout.edittext_toolbar, null);
		editText.setBackgroundResource(R.color.colorTransparent);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, COUNTRIES);
		editText.setAdapter(adapter);

		toolbar.setContentView(editText);

		getWindow().getDecorView().setSystemUiVisibility(
				View.SYSTEM_UI_FLAG_LAYOUT_STABLE
						| View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

		setGradientBackground(parent_layout);

		horizontalInfiniteCycleViewPager.setAdapter(new HorizontalPagerAdapter(this, isimler));

		/******/
	}

	private static final String[] COUNTRIES = new String[] {
			"Allah", "Rahman", "Rahim", "Gaffar", "Rauf"
	};

	private int getStatusBarHeight() {
		int result = 0;
		int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
		if (resourceId > 0) {
			result = getResources().getDimensionPixelSize(resourceId);
		}
		return result;
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
}
