package com.cosmo.arquitecturamvpbase.helper;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.cosmo.arquitecturamvpbase.dao.ProductDao;

/**
 * Created by victorhugosernasuarez on 30/9/17.
 */

public class DataBase {

    private final Context context;
    //Nos ayuda para la creación de cada una de las tablas
    private DataBaseHelper dataBaseHelper;

    //Lista de DAOS a emplear
    public static ProductDao productDao;

    public DataBase(Context context) {
        this.context = context;
    }

    public DataBase open(){
        try{
            dataBaseHelper = new DataBaseHelper(context);
            SQLiteDatabase sdb = dataBaseHelper.getWritableDatabase();

            productDao = new ProductDao(sdb);
            return this;
        }catch (SQLException ex){
            Log.e("SQL OPEN " , ex.getMessage());
            throw ex;
        }
    }

    public void close(){
       dataBaseHelper.close();
    }

}
