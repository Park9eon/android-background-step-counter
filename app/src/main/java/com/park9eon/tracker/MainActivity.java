package com.park9eon.tracker;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class MainActivity extends AppCompatActivity {

    private StepCountHelper stepCountHelper;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.textView = findViewById(R.id.textView);
        this.stepCountHelper = new StepCountHelper(getApplicationContext());

        List<StepCount> stepCountList = this.stepCountHelper.findAll();
        StringBuilder builder = new StringBuilder();
        for (StepCount stepCount : stepCountList) {
            builder.append(stepCount.toString());
            builder.append("\n");
        }
        textView.setText(builder.toString());
        startService(new Intent(this, StepCountBackgroundService.class));
    }

    @Override
    protected void onDestroy() {
        this.stepCountHelper.close();
        super.onDestroy();
    }
}
