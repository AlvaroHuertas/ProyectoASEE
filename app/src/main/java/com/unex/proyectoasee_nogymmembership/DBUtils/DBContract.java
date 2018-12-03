package com.unex.proyectoasee_nogymmembership.DBUtils;

import android.provider.BaseColumns;

public class DBContract {

    private DBContract() {}

    public static class Routine implements BaseColumns {
        public static final String TABLE_NAME = "routine";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_TYPE = "type";
        public static final String COLUMN_NAME_STATUS = "status";
    }

    public static class Exercise implements BaseColumns{
        public static final String TABLE_NAME = "exercise";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_DESC = "description";
        public static final String COLUMN_NAME_IMAGE = "imageURI";
    }
}
