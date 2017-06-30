package com.mobss.namesofAllah.views.activities.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.mobss.islamic.namesofAllah.R;
import com.mobss.namesofAllah.views.activities.base.BaseActivity;
import com.mobss.namesofAllah.views.activities.home.MainActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by ilkay on 11/03/2017.
 */

public class SplashScreenActivity extends BaseActivity implements SplashScreenMvpView{
	
	@Inject
	SplashScreenMvpPresenter<SplashScreenMvpView> mPresenter;

	@BindView(R.id.slogan) TextView slogan;
	
	/** Duration of wait **/
	private final int SPLASH_DISPLAY_LENGTH = 1000;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_splash_screen);
		
		getActivityComponent().inject(this);
		
		setUnBinder(ButterKnife.bind(this));
		
		mPresenter.onAttach(SplashScreenActivity.this);
		
		changeUIFonts();
		
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
	
	private void changeUIFonts(){
		slogan.setTypeface(typeface);
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
}
