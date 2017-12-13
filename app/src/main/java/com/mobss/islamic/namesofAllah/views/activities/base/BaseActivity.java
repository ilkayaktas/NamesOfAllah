package com.mobss.islamic.namesofAllah.views.activities.base;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import com.mobss.islamic.namesofAllah.App;
import com.mobss.islamic.namesofAllah.R;
import com.mobss.islamic.namesofAllah.di.annotations.ActivityContext;
import com.mobss.islamic.namesofAllah.di.components.ActivityComponent;
import com.mobss.islamic.namesofAllah.di.components.DaggerActivityComponent;
import com.mobss.islamic.namesofAllah.di.modules.ActivityModule;
import com.mobss.islamic.namesofAllah.views.widgets.gradientbckg.FlowingGradient;

import javax.inject.Inject;

/**
 * Created by ilkay on 09/03/2017.
 */

public class BaseActivity extends AppCompatActivity implements MvpView, BaseFragment.Callback{

	private ProgressDialog progressDialog;

	private ActivityComponent mActivityComponent;
	
	private Unbinder mUnBinder;

	@Inject
	public Typeface robotoThinText;

	@Inject
	@ActivityContext
	public Typeface regularText;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mActivityComponent = DaggerActivityComponent.builder()
				.activityModule(new ActivityModule(this))
				.applicationComponent(((App) getApplication()).getAppComponent())
				.build();

		mActivityComponent.inject(this);

	}

	public ActivityComponent getActivityComponent() {
		return mActivityComponent;
	}

	@Override
	public void showLoading() {
		hideLoading();
//		progressDialog = CommonUtils.showLoadingDialog(this);
	}

	@Override
	public void hideLoading() {
		if (progressDialog != null && progressDialog.isShowing()) {
			progressDialog.cancel();
		}
	}

	@Override
	public void onError(String message) {
		if (message != null) {
			showSnackBar(message);
		} else {
			showSnackBar(getString(R.string.all_errormessage));
		}
	}

	private void showSnackBar(String message) {
		Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT);
		View sbView = snackbar.getView();
		TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
		textView.setTextColor(ContextCompat.getColor(this, R.color.mobss_color_white));
		snackbar.show();
	}

	@Override
	public void onError(@StringRes int resId) {
		onError(getString(resId));
	}

	@Override
	public void onFragmentAttached() {

	}

	@Override
	public void onFragmentDetached(String tag) {

	}
	
	public void setUnBinder(Unbinder unBinder) {
		mUnBinder = unBinder;
	}
	
	@Override
	protected void onDestroy() {
		
		if (mUnBinder != null) {
			mUnBinder.unbind();
		}
		super.onDestroy();
	}
	
	public void changeActionBarFont(){
		// action bar id'si alınıyor
		int titleId = getResources().getIdentifier("action_bar_title", "id", "android");

		// action bar yazı fontu ve rengi değiştiriliyor
		TextView myActionBar = (TextView) findViewById(titleId);
		if(myActionBar != null) {
			myActionBar.setTextColor(Color.WHITE);
			myActionBar.setTypeface(regularText);
			myActionBar.setTextSize(20);
		}
	}
	
	public void changeActionBarColor(int color){
		getSupportActionBar().setBackgroundDrawable(new ColorDrawable(color));
	}
	
	public void startActivity(Class<?> newActivityClass){
		// intent oluştur
		Intent intent = new Intent(getBaseContext(), newActivityClass);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		
		
		// yeni Acitivity başlat
		startActivity(intent);
	}

	public void setGradientBackground(RelativeLayout relativeLayout) {

		FlowingGradient grad = new FlowingGradient();
		grad.setBackgroundResource(R.drawable.all_bggradienttranslate)
				.onRelativeLayout(relativeLayout)
				.setTransitionDuration(4000)
				.start();
	}

	public void setGradientBackground(LinearLayout linearLayout) {

		FlowingGradient grad = new FlowingGradient();
		grad.setBackgroundResource(R.drawable.all_bggradienttranslate)
				.onLinearLayout(linearLayout)
				.setTransitionDuration(4000)
				.start();
	}
}
