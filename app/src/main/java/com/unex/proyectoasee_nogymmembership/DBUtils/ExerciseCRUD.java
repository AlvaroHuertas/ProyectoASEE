package com.unex.proyectoasee_nogymmembership.DBUtils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.unex.proyectoasee_nogymmembership.Models.Exercise;

import java.util.ArrayList;
import java.util.List;


public class ExerciseCRUD {

    private ExerciseManagerHelper mDbHelper;
    private static ExerciseCRUD mInstance;

    private Context context;

    private ExerciseCRUD(Context context) {
        this.context = context;
        mDbHelper = new ExerciseManagerHelper(context);
    }

    public static ExerciseCRUD getInstance(Context context){
        if (mInstance == null)
            mInstance = new ExerciseCRUD(context);
        return mInstance;
    }


    public List<Exercise> getAll(){
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                DBContract.Exercise._ID,
                DBContract.Exercise.COLUMN_NAME_NAME,
                DBContract.Exercise.COLUMN_NAME_DESC,
                DBContract.Exercise.COLUMN_NAME_IMAGE
        };

        String selection = null;
        String[] selectionArgs = null;

        String sortOrder = null;

        Cursor cursor = db.query(       //Patrçon objectQuery
                DBContract.Exercise.TABLE_NAME,           // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );


        ArrayList<Exercise> items = new ArrayList<>();
        if(cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                items.add(getExerciseFromCursor(cursor));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return items;
    }

    public long insert(Exercise item){
        // Gets the data repository in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(DBContract.Exercise.COLUMN_NAME_NAME, item.getName());
        values.put(DBContract.Exercise.COLUMN_NAME_DESC, item.getDescription());
        values.put(DBContract.Exercise.COLUMN_NAME_IMAGE, item.getURI());

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(DBContract.Exercise.TABLE_NAME, null, values);

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
        db.delete(DBContract.Exercise.TABLE_NAME, selection, selectionArgs);
    }

    private Exercise getExerciseFromCursor(Cursor cursor) {

        long ID = cursor.getInt(cursor.getColumnIndex(DBContract.Exercise._ID));
        String name = cursor.getString(cursor.getColumnIndex(DBContract.Exercise.COLUMN_NAME_NAME));
        String description = cursor.getString(cursor.getColumnIndex(DBContract.Exercise.COLUMN_NAME_DESC));
        String imageURI = cursor.getString(cursor.getColumnIndex(DBContract.Exercise.COLUMN_NAME_IMAGE));

        Exercise item = new Exercise(ID, name, description, imageURI);
        return item;
    }

    public void close(){
        if (mDbHelper!=null) mDbHelper.close();
    }
}
