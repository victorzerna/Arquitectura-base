package com.cosmo.arquitecturamvpbase.synchronizer;

import android.content.ContentValues;
import android.nfc.Tag;
import android.util.Log;

import com.cosmo.arquitecturamvpbase.R;
import com.cosmo.arquitecturamvpbase.dao.ProductDao;
import com.cosmo.arquitecturamvpbase.helper.DataBase;
import com.cosmo.arquitecturamvpbase.model.Product;
import com.cosmo.arquitecturamvpbase.presenter.ProductPresenter;
import com.cosmo.arquitecturamvpbase.repository.MapperError;
import com.cosmo.arquitecturamvpbase.repository.ProductRepository;
import com.cosmo.arquitecturamvpbase.repository.RepositoryError;

import java.util.ArrayList;

import retrofit.RetrofitError;

/**
 * Created by victorhugosernasuarez on 12/10/17.
 */

public class Synchronizer {


    private static final String TAG_CLASS = "Synchronizer";
    public static Synchronizer instance;
    private ProductRepository productRepository;

    public static Synchronizer getInstance() {
        if(instance == null){
            instance = new Synchronizer();
        }
        return instance;
    }

    public void executeSyncLocalToServer(boolean isConnected) {
        //Se debe realizar la verificaciòn de la conexion con el fin de sincronizar los
        //productos que estan guardados de forma local

        Log.i(TAG_CLASS,"Ingresando a  executeSyncLocalToServer "+isConnected);
        if(isConnected){
            syncronizedProducts();
            Log.i(TAG_CLASS,"conected "+isConnected);
        }else{
            Log.i(TAG_CLASS,"no conected "+isConnected);
        }

        Log.i( TAG_CLASS, "network changed: " + isConnected);
    }


    private void syncronizedProducts() {
        try{
            ArrayList<Product> productArrayList = DataBase.productDao.fetchAllProducts();
            //ArrayList<Product> productArrayList = DataBase.productDao.fetchProductsNoSync();
            Log.i(TAG_CLASS,"Sincronizando productos locales --> "+productArrayList.size());
            int actProducts = 0;
            int noActProduct = 0;
            Product tempProduct;
            productRepository = new ProductRepository();
            for (Product product: productArrayList) {
                Log.i(TAG_CLASS,"en For realizando Sync --> "+product.getName());

                tempProduct = productRepository.createProduct(product);
                if(tempProduct != null){
                    //Log.i(TAG_CLASS," eliminando local --> "+DataBase.productDao.deleteProduct(product));

                    Log.i(TAG_CLASS," eliminando local --> "+DataBase.productDao.updateProduct(product));
                    actProducts++;

                }else{
                    noActProduct++;
                }


            }

        }catch(RetrofitError retrofitError){
            Log.i(TAG_CLASS,"error retrofit "+retrofitError.getMessage());
            RepositoryError repositoryError = MapperError.convertRetrofitErrorToRepositoryError(retrofitError);
            //getView().showAlertError(R.string.error, repositoryError.getMessage());
        }
    }

}
