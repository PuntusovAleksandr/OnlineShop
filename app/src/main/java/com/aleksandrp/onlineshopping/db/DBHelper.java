package com.aleksandrp.onlineshopping.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by AleksandrP on 10.06.2016.
 */
public class DBHelper extends SQLiteOpenHelper implements ValuesDB {


    private Context context;

    public DBHelper(Context context) {
        super(context, NAME_DB, null, VERSION_DB);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG_DB, "Start ::: " + CREATE_TABLE_PRODUCT);
        db.execSQL(CREATE_TABLE_PRODUCT);
        Log.i(TAG_DB, "Finish  ::: ");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
