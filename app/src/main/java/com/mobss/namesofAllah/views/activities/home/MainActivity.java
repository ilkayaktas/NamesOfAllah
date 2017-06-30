package com.mobss.namesofAllah.views.activities.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.mobss.islamic.namesofAllah.R;
import com.mobss.namesofAllah.views.activities.base.BaseActivity;

public class MainActivity extends BaseActivity implements MainMvpView {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public static Intent getStartIntent(Context context) {
		Intent intent = new Intent(context, MainActivity.class);
		return intent;
	}
}
