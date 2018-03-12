package com.mobss.islamic.namesofAllah.controller.job;

import android.app.AlarmManager;
import android.support.annotation.NonNull;
import com.evernote.android.job.Job;
import com.evernote.android.job.JobManager;
import com.evernote.android.job.JobRequest;

import java.util.Set;

/**
 * Created by ilkayaktas on 12.03.2018 at 01:13.
 */

public class MobssJob extends Job {
    public static final String TAG = "daily_remainder_job";

    @NonNull
    @Override
    protected Result onRunJob(@NonNull Params params) {
        // run job here
        return Result.SUCCESS;
    }

    public static void scheduleJob() {
        // check whether a sync job is scheduled or not. If it is already scheduled, stop there itself and return back.
        Set<JobRequest> jobRequests = JobManager.instance().getAllJobRequestsForTag(MobssJob.TAG);
        if (!jobRequests.isEmpty()) {
            return;
        }

        new JobRequest.Builder(MobssJob.TAG)
                .setPeriodic(AlarmManager.INTERVAL_DAY)
                .setUpdateCurrent(true) // cancel any preexisting job with the same tag while being scheduled.
//                .setRequiredNetworkType(JobRequest.NetworkType.CONNECTED) // since the sync service needs network connection
//                .setRequirementsEnforced(true) // makes sure that all the requirements are met before starting the sync service, if at least one of the requirements is not met then the sync service will be rescheduled and not run
                .build()
                .schedule();
    }
}
