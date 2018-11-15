package com.unex.proyectoasee_nogymmembership.DBUtils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class RoutineManagerHelper extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "routines.db";
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";

    private static final String DICTIONARY_TABLE_CREATE =
            "CREATE TABLE " + DBContract.Routine.TABLE_NAME + " (" +
                    DBContract.Routine._ID + " INTEGER PRIMARY KEY," +
                    DBContract.Routine.COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP +
                    DBContract.Routine.COLUMN_NAME_TYPE + TEXT_TYPE + COMMA_SEP +
                    DBContract.Routine.COLUMN_NAME_STATUS + TEXT_TYPE + " );" ;

    private static final String SQL_DELETE_TODOS =
            "DROP TABLE IF EXISTS " + DBContract.Routine.TABLE_NAME;

    public RoutineManagerHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE routine( _id INTEGER PRIMARY KEY, name TEXT, type TEXT, status TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_TODOS);
        onCreate(db);
    }
}
