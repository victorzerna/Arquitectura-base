package com.cosmo.arquitecturamvpbase.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import com.cosmo.arquitecturamvpbase.schemes.IProductsScheme;

/**
 * Created by victorhugosernasuarez on 30/9/17.
 */

class DataBaseHelper extends SQLiteOpenHelper{

    public DataBaseHelper(Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
    }

    //Acá se debe realizar la implementación de las demás tablas del modelo de base de datos
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(IProductsScheme.PRODUCT_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(Constants.DATABASE_NAME, "Actualización de versión a: " + newVersion);
        //db.execSQL("DROP TABLE IF EXIST " + IProductsScheme.PRODUCT_TABLE);

        switch (newVersion) {
            case 1:
                db.execSQL("DROP TABLE IF EXIST " + IProductsScheme.PRODUCT_TABLE);

            case 2:
                db.execSQL(IProductsScheme.PRODUCT_TABLE_ALTER_V2);
        }

    }

}
