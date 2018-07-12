package com.park9eon.tracker;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class JobHelper {

    private Context context;

    JobHelper(Context context, int jobId){
        this.context = context;
        JobInfo.Builder builder = new JobInfo.Builder(jobId, new ComponentName(context, StepCountBackgroundService.class));
        builder.setOverrideDeadline(0);
        JobScheduler jobScheduler = (JobScheduler) this.context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
        jobScheduler.schedule(builder.build());
    }
}
