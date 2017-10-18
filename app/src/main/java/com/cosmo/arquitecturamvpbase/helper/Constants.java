package com.cosmo.arquitecturamvpbase.helper;

/**
 * Created by leidyzulu on 16/09/17.
 */

public class Constants {

    public final static String TAG_APP = "*EMPANADAS*";

    public final static String URL_BASE = "https://shoppingproducts.herokuapp.com";
    public final static int TIME_OUT = 6;
    public final static String ITEM_PRODUCT = "Itemproduct";
    public static final String REQUEST_TIMEOUT_ERROR_MESSAGE = "La solicitud está tardando demasiado. Por favor inténtalo nuevamente.";
    public static final int DEFAUL_ERROR_CODE = 0;
    public static final String DEFAUL_ERROR = "Ha ocurrido un error, intentalo nuevamente.";
    public static final int UNAUTHORIZED_ERROR_CODE = 401;
    public static final int NOT_FOUND_ERROR_CODE = 404;

    //Información relacionada con Base de datos
    public final  static  String DATABASE_NAME = "shopping_db.db";
    public final static  int DATABASE_VERSION = 2;

    public static final String TITLE_PRODUCT = "Productos";
    public static final String TITLE_CONTACT = "Constantes";
    public static final String EMPTY = "Vacio";

    public static final String PREFERENCES = "share_preferences";
    public static final int MODE_PRIVATE = 1;
}
