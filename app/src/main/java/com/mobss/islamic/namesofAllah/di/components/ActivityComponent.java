/*
 * Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.mindorks.com/license/apache-v2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.mobss.islamic.namesofAllah.di.components;


import com.mobss.islamic.namesofAllah.controller.alarms.notification.MobssCustomNotificationService;
import com.mobss.islamic.namesofAllah.di.annotations.PerActivity;
import com.mobss.islamic.namesofAllah.di.modules.ActivityModule;
import com.mobss.islamic.namesofAllah.views.activities.base.BaseActivity;
import com.mobss.islamic.namesofAllah.views.activities.home.MainActivity;
import com.mobss.islamic.namesofAllah.views.activities.listview.ListViewActivity;
import com.mobss.islamic.namesofAllah.views.activities.splash.SplashScreenActivity;
import com.mobss.islamic.namesofAllah.views.fragments.slidingviewpager.SlidingViewPagerFragment;
import dagger.Component;

/**
 * Created by iaktas on 24/04/17.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(BaseActivity baseActivity);

    void inject(MainActivity activity);
    
    void inject(SplashScreenActivity activity);
    
    void inject(ListViewActivity activity);

    void inject(SlidingViewPagerFragment fragment);

    void inject(MobssCustomNotificationService service);
}
