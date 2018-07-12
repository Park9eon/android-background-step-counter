package com.park9eon.tracker;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class StepCountBackgroundService extends JobService {

    private static final String TAG = "BackgroundService";
    private StepCountHelper stepCountHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "Service on create");
        this.stepCountHelper = new StepCountHelper(getApplicationContext());
        this.stepCountHelper.start();
    }

    @Override
    public void onDestroy() {
        this.stepCountHelper.close();
        super.onDestroy();
    }

    @Override
    public boolean onStartJob(JobParameters params) {
        Log.i(TAG, "on start job: " + params.getJobId());
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.i(TAG, "on stop job: " + params.getJobId());
        return false;
    }
}
