package com.unex.proyectoasee_nogymmembership.DBUtils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.unex.proyectoasee_nogymmembership.Models.Routine;

import java.util.ArrayList;
import java.util.List;

public class RoutineCRUD {

    private RoutineManagerHelper mDbHelper;
    private static RoutineCRUD mInstance;

    private Context context;

    private RoutineCRUD(Context context) {
        this.context = context;
        mDbHelper = new RoutineManagerHelper(context);
    }

    public static RoutineCRUD getInstance(Context context){
        if (mInstance == null)
            mInstance = new RoutineCRUD(context);
        return mInstance;
    }

    public List<Routine> getAll(){
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                DBContract.Routine._ID,
                DBContract.Routine.COLUMN_NAME_NAME,
                DBContract.Routine.COLUMN_NAME_TYPE,
                DBContract.Routine.COLUMN_NAME_STATUS
        };

        String selection = null;
        String[] selectionArgs = null;

        String sortOrder = null;

        Cursor cursor = db.query(       //Patrçon objectQuery
                DBContract.Routine.TABLE_NAME,           // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );


        ArrayList<Routine> items = new ArrayList<>();
        if(cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                items.add(getRoutineFromCursor(cursor));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return items;
    }

    public long insert(Routine item){
        // Gets the data repository in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(DBContract.Routine.COLUMN_NAME_NAME, item.getName());
        values.put(DBContract.Routine.COLUMN_NAME_TYPE, item.getType());
        values.put(DBContract.Routine.COLUMN_NAME_STATUS, item.getStatus().name());

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(DBContract.Routine.TABLE_NAME, null, values);

        return newRowId;
    }

    public void deleteAll() {
        // Gets the data repository in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Define 'where' part of query.
        String selection = null;
        // Specify arguments in placeholder order.
        String[] selectionArgs = null;

        // Issue SQL statement.
        db.delete(DBContract.Routine.TABLE_NAME, selection, selectionArgs);
    }

    public int updateStatus(long ID, Routine.Status status) {

        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        Log.d("RoutineCRUD","Item ID: "+ID);

        // New value for one column
        ContentValues values = new ContentValues();
        values.put(DBContract.Routine.COLUMN_NAME_STATUS, status.name());

        // Which row to update, based on the ID
        String selection = DBContract.Routine._ID + " = ?";
        String[] selectionArgs = { Long.toString(ID) };

        int count = db.update(
                DBContract.Routine.TABLE_NAME,
                values,
                selection,
                selectionArgs);

        return count;
    }

    private Routine getRoutineFromCursor(Cursor cursor) {

        long ID = cursor.getInt(cursor.getColumnIndex(DBContract.Routine._ID));
        String name = cursor.getString(cursor.getColumnIndex(DBContract.Routine.COLUMN_NAME_NAME));
        String type = cursor.getString(cursor.getColumnIndex(DBContract.Routine.COLUMN_NAME_TYPE));
        String status = cursor.getString(cursor.getColumnIndex(DBContract.Routine.COLUMN_NAME_STATUS));

        Routine item = new Routine(ID, name, type, status);
    return item;
    }

    public void close(){
        if (mDbHelper!=null) mDbHelper.close();
    }

}
