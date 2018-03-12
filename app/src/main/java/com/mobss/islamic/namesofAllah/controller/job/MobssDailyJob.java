package com.mobss.islamic.namesofAllah.controller.job;

import android.support.annotation.NonNull;
import com.evernote.android.job.DailyJob;
import com.evernote.android.job.JobManager;
import com.evernote.android.job.JobRequest;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by ilkayaktas on 12.03.2018 at 01:13.
 */

public class MobssDailyJob extends DailyJob {
    public static final String TAG = "daily_remainder_job";



    @NonNull
    @Override
    protected DailyJobResult onRunDailyJob(@NonNull Params params) {
        return DailyJobResult.SUCCESS;
    }

    public static void scheduleJob() {
        // check whether a sync job is scheduled or not. If it is already scheduled, stop there itself and return back.
        Set<JobRequest> jobRequests = JobManager.instance().getAllJobRequestsForTag(MobssDailyJob.TAG);
        if (!jobRequests.isEmpty()) {
            return;
        }

        // schedule between 1 and 6 AM
        DailyJob.schedule(new JobRequest.Builder(TAG),
                TimeUnit.HOURS.toMillis(1),
                TimeUnit.HOURS.toMillis(6));
    }

}
