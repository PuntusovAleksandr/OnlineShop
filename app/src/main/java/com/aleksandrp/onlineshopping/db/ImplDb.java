package com.aleksandrp.onlineshopping.db;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.aleksandrp.onlineshopping.fragment.SaveFragment;
import com.aleksandrp.onlineshopping.model.ItemProduct;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AleksandrP on 10.06.2016.
 */
public class ImplDb implements ValuesDB {

    private DBHelper dbHelper;
    private Context context;
    private ContentValues contentValues;
    private SQLiteDatabase database;
    private Cursor cursor;

    private static ImplDb db;

    private ImplDb(Context context) {
        this.context = context;
        openDb();
    }

    public synchronized void openDb() {
        if (dbHelper == null) {
            dbHelper = new DBHelper(context);
            database = dbHelper.getWritableDatabase();
        }
    }

    public static ImplDb getInstanceDB(Context context) {
        if (db == null) {
            db = new ImplDb(context);
        }
        return db;
    }

    public void close() {
        if (database != null) {
            database.close();
            database = null;
        }
        if (dbHelper != null) {
            dbHelper.close();
            dbHelper = null;
        }
    }

    public boolean isHaveProduct(int mId) {
        String mStrId = String.valueOf(mId);
        boolean exists = false;
        openDb();
        try {
            cursor = database.rawQuery("select * from yourTable where _id=%s",
                    new String[]{mStrId});
             exists = (cursor.getCount() > 0);
            cursor.close();
        }catch (SQLiteException e) {
            Log.e(ValuesDB.TAG_DB, e.getStackTrace().toString());
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return exists;
        }


    /**
     * get all products in database
     * @return
     */
    public ArrayList<ItemProduct> getAllProducts() {
        ArrayList<ItemProduct> mProducts = new ArrayList<ItemProduct>();
        ItemProduct mProduct;
        openDb();
        try {
            cursor = database.query(TABLE_PRODUCT, null, null, null, null, null, null);
            if (cursor.moveToFirst()) {
                do {
                    mProduct = new ItemProduct(
                            cursor.getInt(cursor.getColumnIndex(COLUMN_ID)),
                            cursor.getString(cursor.getColumnIndex(COLUMN_TITLE)),
                            cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)),
                            cursor.getString(cursor.getColumnIndex(COLUMN_PRICE)),
                            cursor.getString(cursor.getColumnIndex(COLUMN_SMALL_ICON)),
                            cursor.getInt(cursor.getColumnIndex(COLUMN_SMALL_ICON)) == 0,
                            cursor.getString(cursor.getColumnIndex(COLUMN_BIG_ICON))
                    );
                    mProducts.add(mProduct);
                } while (cursor.moveToNext());
            }
        } catch (SQLiteException e) {
            Log.e(ValuesDB.TAG_DB, e.getStackTrace().toString());
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return mProducts;
    }

    /**
     * put product
     */
    public void putItemProduct(ItemProduct mProduct) {
        openDb();
        contentValues = new ContentValues();
        contentValues.put(ValuesDB.COLUMN_ID, mProduct.getListing_id());
        contentValues.put(ValuesDB.COLUMN_TITLE, mProduct.getTitle());
        contentValues.put(ValuesDB.COLUMN_DESCRIPTION, mProduct.getDescription());
        contentValues.put(ValuesDB.COLUMN_PRICE, mProduct.getPrice());
        contentValues.put(ValuesDB.COLUMN_SMALL_ICON, mProduct.getIcon_url_small());
        contentValues.put(ValuesDB.COLUMN_BIG_ICON, mProduct.getIcon_url_big_size());
        contentValues.put(ValuesDB.COLUMN_IS_SAVE, mProduct.isSaved() ? 1 : 0);
        try {
            database.insert(ValuesDB.TABLE_PRODUCT, null, contentValues);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteProduct( int mId ) {
        openDb();
        database.delete(ValuesDB.TABLE_PRODUCT,
                ValuesDB.COLUMN_ID + " = " + mId, null);
    }


}
