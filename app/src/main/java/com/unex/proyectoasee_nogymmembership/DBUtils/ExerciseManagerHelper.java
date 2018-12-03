package com.unex.proyectoasee_nogymmembership.DBUtils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ExerciseManagerHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "exercises.db";
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";

    private static final String DICTIONARY_TABLE_CREATE =
            "CREATE TABLE " + DBContract.Exercise.TABLE_NAME + " (" +
                    DBContract.Exercise._ID + " INTEGER PRIMARY KEY," +
                    DBContract.Exercise.COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP +
                    DBContract.Exercise.COLUMN_NAME_DESC + TEXT_TYPE + COMMA_SEP +
                    DBContract.Exercise.COLUMN_NAME_IMAGE + TEXT_TYPE + " );" ;

    private static final String SQL_DELETE_TODOS =
            "DROP TABLE IF EXISTS " + DBContract.Exercise.TABLE_NAME;

    public ExerciseManagerHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE exercise ( _id INTEGER PRIMARY KEY, name TEXT, description TEXT, imageURI TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_TODOS);
        onCreate(db);
    }
}
