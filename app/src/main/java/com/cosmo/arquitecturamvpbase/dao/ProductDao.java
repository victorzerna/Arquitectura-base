package com.cosmo.arquitecturamvpbase.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.cosmo.arquitecturamvpbase.helper.Constants;
import com.cosmo.arquitecturamvpbase.model.Product;
import com.cosmo.arquitecturamvpbase.providers.DbContentProvider;
import com.cosmo.arquitecturamvpbase.schemes.IProductsScheme;

import java.util.ArrayList;

/**
 * Created by victorhugosernasuarez on 30/9/17.
 */

public class ProductDao extends DbContentProvider implements IProductsScheme, IProductDao{


    private static final String TAG_CLASS = "ProductDAO";
    private Cursor cursor;
    private ContentValues initialValues;

    public ProductDao(SQLiteDatabase db) {
        super(db);
    }

    @Override
    public ArrayList<Product> fetchAllProducts() {
        ArrayList<Product> productList = new ArrayList<>();
        cursor = super.query(PRODUCT_TABLE, PRODUCT_COLUMNS,null, null,COLUMN_PRODUCT_NAME);

        if(cursor != null){
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                Product product = cursorToEntity(cursor);
                productList.add(product);

                cursor.moveToNext();
            }
            cursor.close();
        }
        return productList;
    }

    @Override
    public boolean createProduct(Product product) {
        setContentValueProduct(product);

        try{
            return super.insert(PRODUCT_TABLE, getContentValue()) >= 0;
            /*long totalInsert = super.insert(PRODUCT_TABLE, getContentValue());
            if(totalInsert == -1){
                return false;
            }
            return true;*/
        }catch (SQLiteConstraintException ex){

            Log.e("DbErrorCreateProduct", ex.getMessage());
        }

        return false;
    }

    @Override
    public boolean deleteProduct(Product product) {

        try {
            String[] args = new String[] {product.getId()};
            Log.i(TAG_CLASS,DELETE_PRODUCT_SINC);
            if(super.delete(PRODUCT_TABLE, DELETE_PRODUCT_SINC, args)>0) {
                return true;
            }
        }catch (SQLException ex){

        }
        return false;
    }

    @Override
    public boolean updateProduct(Product product) {
        try {

            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN_PRODUCT_ISSYNC,"0");

            Log.i(TAG_CLASS,COLUMN_PRODUCT_NAME + " = " + product.getName());
            if(super.update(PRODUCT_TABLE, contentValues, COLUMN_PRODUCT_NAME + " = '" + product.getName() +"'" , null)>0) {
                return true;
            }
        }catch (SQLException ex){

        }
        return false;
    }

    @Override
    public boolean syncProductsLocal() {

        return false;
    }

    private void setContentValueProduct(Product product) {
        initialValues = new ContentValues();
        initialValues.put(COLUMN_ID, product.getId());
        initialValues.put(COLUMN_PRODUCT_NAME, product.getName());
        initialValues.put(COLUMN_PRODUCT_DESCRIPTION, product.getDescription());
        initialValues.put(COLUMN_PRODUCT_PRICE, product.getPrice());
        initialValues.put(COLUMN_PRODUCT_QUANTITY, product.getQuantity());
        initialValues.put(COLUMN_PRODUCT_ISSYNC, product.isSync());
    }

    private ContentValues getContentValue() {
        return initialValues;
    }

    @Override
    protected Product cursorToEntity(Cursor cursor) {
        Product product = new Product();
        int idIndex;
        int nameIndex;
        int descriptionIndex;
        int quantityIndex;
        int priceIndex;

        //Se valida que exista la columna, dado que puede cambiar la tabla y tener mas elementos
        //Para esto se emplea la validaciòn de cada una de las columnas
        if(cursor.getColumnIndex(COLUMN_ID) != -1){
            idIndex = cursor.getColumnIndexOrThrow(COLUMN_ID);
            product.setId(cursor.getString(idIndex));
        }

        if(cursor.getColumnIndex(COLUMN_PRODUCT_NAME) != -1){
            nameIndex = cursor.getColumnIndexOrThrow(COLUMN_PRODUCT_NAME);
            product.setName(cursor.getString(nameIndex));
        }

        if(cursor.getColumnIndex(COLUMN_PRODUCT_DESCRIPTION) != -1){
            descriptionIndex = cursor.getColumnIndexOrThrow(COLUMN_PRODUCT_DESCRIPTION);
            product.setDescription(cursor.getString(descriptionIndex));
        }

        if(cursor.getColumnIndex(COLUMN_PRODUCT_PRICE) != -1){
            priceIndex = cursor.getColumnIndexOrThrow(COLUMN_PRODUCT_PRICE);
            product.setPrice(cursor.getString(priceIndex));
        }

        if(cursor.getColumnIndex(COLUMN_PRODUCT_QUANTITY) != -1){
            quantityIndex = cursor.getColumnIndexOrThrow(COLUMN_PRODUCT_QUANTITY);
            product.setQuantity(cursor.getString(quantityIndex));
        }



        return product;
    }


    public ArrayList<Product> fetchProductsNoSync() {
        ArrayList<Product> productList = new ArrayList<>();

        String[] args = new String[] {"'0'"};
        //Cursor c = db.rawQuery(" SELECT codigo,nombre FROM Usuarios WHERE nombre=? ", args);

        cursor = super.rawQuery(SELECT_NO_SYNC, args);

        if(cursor != null){
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                Product product = cursorToEntity(cursor);
                productList.add(product);

                cursor.moveToNext();
            }
            cursor.close();
        }
        return productList;
    }
}
