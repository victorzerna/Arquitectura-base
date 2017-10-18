package com.cosmo.arquitecturamvpbase.schemes;

/**
 * Created by victorhugosernasuarez on 30/9/17.
 */

public interface IProductsScheme {

    //Elementos de la tabla Productos
    String PRODUCT_TABLE = "products";
    String COLUMN_ID = "_id";
    String COLUMN_PRODUCT_NAME = "product_name";
    String COLUMN_PRODUCT_DESCRIPTION = "product_description";
    String COLUMN_PRODUCT_QUANTITY = "product_quantity";
    String COLUMN_PRODUCT_PRICE = "product_price";
    String COLUMN_PRODUCT_ISSYNC = "product_synk";

    //Script para crear la tabla si esta no existe
    String PRODUCT_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS "+ PRODUCT_TABLE +" ("
            + COLUMN_ID +" TEXT PRIMARY KEY, "
            + COLUMN_PRODUCT_NAME + " TEXT NOT NULL, "
            + COLUMN_PRODUCT_QUANTITY + " TEXT, "
            + COLUMN_PRODUCT_PRICE + " TEXT, "
            + COLUMN_PRODUCT_DESCRIPTION + " TEXT"
            +");";

    //Arreglo de strings que tiene la tabla
    String [] PRODUCT_COLUMNS = new String[]{
            COLUMN_ID, COLUMN_PRODUCT_NAME, COLUMN_PRODUCT_DESCRIPTION, COLUMN_PRODUCT_QUANTITY, COLUMN_PRODUCT_PRICE
    };

    //Script para alterar la tabla
    String PRODUCT_TABLE_ALTER_V2 = "ALTER TABLE "+ PRODUCT_TABLE + " ADD COLUMN "+ COLUMN_PRODUCT_ISSYNC + " TEXT";

    String SELECT_NO_SYNC = " SELECT "+COLUMN_ID+","+COLUMN_PRODUCT_NAME+","+COLUMN_PRODUCT_QUANTITY+","+COLUMN_PRODUCT_PRICE+
            ","+COLUMN_PRODUCT_DESCRIPTION+","+COLUMN_PRODUCT_ISSYNC+" FROM "+PRODUCT_TABLE+" WHERE "+COLUMN_PRODUCT_ISSYNC+
            " = ? ";

    String DELETE_PRODUCT_SINC = "DELETE FROM "+ PRODUCT_TABLE + " WHERE "+COLUMN_ID+ " = ?";

    String UPDATE_PRODUCT_SYNC = "UPDATE "+ PRODUCT_TABLE + " SET "+COLUMN_PRODUCT_ISSYNC +"= '0' WHERE "+COLUMN_PRODUCT_ISSYNC +
            " = '1' AND "+COLUMN_PRODUCT_NAME +"= ?" ;


}
