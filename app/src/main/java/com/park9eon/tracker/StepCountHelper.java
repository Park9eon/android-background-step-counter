package com.park9eon.tracker;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import java.util.List;

public class StepCountHelper implements SensorEventListener {

    private static final String TAG = "BackgroundService";

    private Context context;
    private StepCountDatabaseHelper dbHelper;
    private SQLiteDatabase db;
    private SensorManager sensorManager;
    private Sensor sensor;

    public StepCountHelper(Context context) {
        this.context = context;
        this.dbHelper = new StepCountDatabaseHelper(context);
        this.db = this.dbHelper.getWritableDatabase();
        this.sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        if (this.sensorManager != null) {
            this.sensor = this.sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        save((long) sensorEvent.values[0]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    public void start() {
        this.sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_GAME);
    }

    public void save(long step) {
        StepCountDatabaseHelper.save(this.db, step);
    }

    public List<StepCount> findAll() {
        return StepCountDatabaseHelper.findAll(this.db);
    }

    public void close() {
        this.dbHelper.close();
    }

}
