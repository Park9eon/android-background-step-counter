package com.park9eon.tracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.*;

public class StepCountDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "dd";
    private static final int DB_VERSION = 1;

    private static final String TB_NAME = "tt";
    private static final String TB_ID = "id";
    private static final String TB_STEP = "step";
    private static final String TB_TIMESTAMP = "timestamp";

    StepCountDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public static void save(SQLiteDatabase db, Long step) {
        ContentValues values = new ContentValues();
        values.put(TB_STEP, step);
        values.put(TB_TIMESTAMP, System.currentTimeMillis());
        db.insertOrThrow(TB_NAME, null, values);
    }

    public static List<StepCount> findAll(SQLiteDatabase db) {
        ArrayList stepCountList = new ArrayList<StepCount>();
        Cursor cursor = db.query(TB_NAME, null, null, null, null, null, TB_TIMESTAMP + " DESC");
        while (cursor.moveToNext()) {
            StepCount stepCount = new StepCount();
            stepCount.setId(cursor.getLong(cursor.getColumnIndex(TB_ID)));
            stepCount.setStep(cursor.getLong(cursor.getColumnIndex(TB_STEP)));
            stepCount.setTimestamp(new Date(cursor.getLong(cursor.getColumnIndex(TB_TIMESTAMP))));
            stepCountList.add(stepCount);
        }
        cursor.close();
        return stepCountList;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TB_NAME + " (" + TB_ID + " INTEGER PRIMARY KEY, " + TB_STEP + " INTEGER NOT NULL, " + TB_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oVersion, int nVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TB_NAME);
        this.onCreate(db);
    }
}
