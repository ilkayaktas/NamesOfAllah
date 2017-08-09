package com.mobss.namesofAllah.views.activities.splash;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.mobss.namesofAllah.R;
import com.mobss.namesofAllah.utils.AppConstants;
import com.mobss.namesofAllah.views.activities.base.BaseActivity;
import com.mobss.namesofAllah.views.activities.home.MainActivity;

import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by ilkay on 11/03/2017.
 */

public class SplashScreenActivity extends BaseActivity implements SplashScreenMvpView{
	
	@Inject
	SplashScreenMvpPresenter<SplashScreenMvpView> mPresenter;

	@BindView(R.id.textview_splashscreen_slogan) TextView slogan;
	
	/** Duration of wait **/
	private final int SPLASH_DISPLAY_LENGTH = 1500;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_splashscreen);
		
		getActivityComponent().inject(this);
		
		setUnBinder(ButterKnife.bind(this));
		
		mPresenter.onAttach(SplashScreenActivity.this);
		
		changeUIFontsAndLanguage();
		
		startHandler();
	}
	
	private void startHandler() {
		/* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
		new Handler().postDelayed(new Runnable(){
			@Override
			public void run() {
                /* Create an Intent that will start the Menu-Activity. */
				Intent mainIntent = new Intent(SplashScreenActivity.this,MainActivity.class);
				SplashScreenActivity.this.startActivity(mainIntent);
				SplashScreenActivity.this.finish();
			}
		}, SPLASH_DISPLAY_LENGTH);
	}
	
	private void changeUIFontsAndLanguage(){
		slogan.setTypeface(robotoThinText);
		
		String lang = mPresenter.getPreferredLanguage();
		
		if(lang != null && lang.equals(AppConstants.LANGUAGE_EN)){
			slogan.setText(getLocaleStringResource(new Locale(AppConstants.LANGUAGE_EN), R.string.splashscreen_slogan, this));
		} else if(lang != null && lang.equals(AppConstants.LANGUAGE_TR)){
			slogan.setText(getLocaleStringResource(new Locale(AppConstants.LANGUAGE_TR), R.string.splashscreen_slogan, this));
		} else{
			slogan.setText(getLocaleStringResource(new Locale(AppConstants.LANGUAGE_EN), R.string.splashscreen_slogan, this));
		}
	}
	
	@Override
	protected void onDestroy() {
		mPresenter.onDetach();
		super.onDestroy();
	}
	
	@Override
	public void openMainActivity() {
		Intent intent = MainActivity.getStartIntent(SplashScreenActivity.this);
		startActivity(intent);
		finish();
		
	}
	
	public static String getLocaleStringResource(Locale requestedLocale, int resourceId, Context context) {
		String result;
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) { // use latest api
			Configuration config = new Configuration(context.getResources().getConfiguration());
			config.setLocale(requestedLocale);
			result = context.createConfigurationContext(config).getText(resourceId).toString();
		}
		else { // support older android versions
			Resources resources = context.getResources();
			Configuration conf = resources.getConfiguration();
			Locale savedLocale = conf.locale;
			conf.locale = requestedLocale;
			resources.updateConfiguration(conf, null);
			
			// retrieve resources from desired locale
			result = resources.getString(resourceId);
			
			// restore original locale
			conf.locale = savedLocale;
			resources.updateConfiguration(conf, null);
		}
		
		return result;
	}
}
