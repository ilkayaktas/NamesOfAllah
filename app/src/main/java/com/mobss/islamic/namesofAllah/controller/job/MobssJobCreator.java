package com.mobss.islamic.namesofAllah.controller.job;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.evernote.android.job.Job;
import com.evernote.android.job.JobCreator;

/**
 * Created by ilkayaktas on 12.03.2018 at 01:12.
 */

public class MobssJobCreator implements JobCreator {
    @Nullable
    @Override
    public Job create(@NonNull String tag) {
        switch (tag) {
            case MobssJob.TAG:
                return new MobssJob();
            default:
                return null;
        }
    }
}
