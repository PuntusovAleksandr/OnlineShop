package com.aleksandrp.onlineshopping.db;


/**
 * Created by Aleksandr on 25.09.2015.
 */
public interface ValuesDB {


    String TAG_DB = "My_Db";

    String NAME_DB = "level_1";

    int VERSION_DB = 1;

    String TABLE_PRODUCT = "Product";

    String COLUMN_ID = "id_product";
    String COLUMN_TITLE = "title";
    String COLUMN_PRICE = "price";
    String COLUMN_DESCRIPTION = "description";
    String COLUMN_BIG_ICON = "big_icon";
    String COLUMN_SMALL_ICON = "small_icon";
    String COLUMN_IS_SAVE = "save";

    String CREATE_TABLE_PRODUCT = "CREATE TABLE IF NOT EXISTS " +
            TABLE_PRODUCT + " (\n" +
            COLUMN_ID + "          INTEGER   primary key  NOT NULL, \n" +
            COLUMN_TITLE + "          TEXT    NOT NULL, \n" +
            COLUMN_PRICE + "          TEXT    NOT NULL, \n" +
            COLUMN_DESCRIPTION + "          TEXT    NOT NULL, \n" +
            COLUMN_BIG_ICON + "    TEXT, \n" +
            COLUMN_SMALL_ICON + "    TEXT, \n" +
            COLUMN_IS_SAVE + " INTEGER    NOT NULL\n" +
            "                               DEFAULT ('0'));";


}
