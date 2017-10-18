package com.cosmo.arquitecturamvpbase.presenter;

import android.database.SQLException;
import android.util.Log;

import com.cosmo.arquitecturamvpbase.R;
import com.cosmo.arquitecturamvpbase.helper.DataBase;
import com.cosmo.arquitecturamvpbase.model.Product;
import com.cosmo.arquitecturamvpbase.repository.MapperError;
import com.cosmo.arquitecturamvpbase.repository.ProductRepository;
import com.cosmo.arquitecturamvpbase.repository.RepositoryError;
import com.cosmo.arquitecturamvpbase.views.activities.IProductView;

import java.util.ArrayList;

import retrofit.RetrofitError;

/**
 * Created by leidyzulu on 16/09/17.
 */

public class ProductPresenter extends BasePresenter<IProductView> {

    private static final String TAG_CLASS = "ProductPresenter";
    private ProductRepository productRepository;

    public ProductPresenter() {
        productRepository = new ProductRepository();
    }

    //Lista de procedimientos encargados de consultar al web service por la lista de productos

    public void getListProduct() {
        if (getValidateInternet().isConnected()) {
           // syncronizedProducts();

            Log.i(TAG_CLASS,"Consultando productos internet");
            createThreadProduct();
        } else {
            Log.i(TAG_CLASS,"Consultando productos locales");
            createThreadProductLocal();
            //getView().showAlertDialogInternet(R.string.error, R.string.validate_internet);
        }
    }

    private void createThreadProduct() {
        // getView().showProgress(R.string.loading_message);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                getProductList();
            }
        });
        thread.start();
    }

    private void createThreadProductLocal() {
        // getView().showProgress(R.string.loading_message);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                getProductListLocal();
            }
        });
        thread.start();
    }


    private void getProductListLocal() {
        try {
            ArrayList<Product> productArrayList = DataBase.productDao.fetchAllProducts();
            getView().showProductList(productArrayList);

        } catch (SQLException ex) {
            Log.i(TAG_CLASS,"Excepcion sql getProductListLocal "+ex.getMessage());
            getView().showAlertError(R.string.error, ex.getMessage());

        }
    }

    private void getProductListLocalNoSync(){
        try {
            ArrayList<Product> productArrayList = DataBase.productDao.fetchProductsNoSync();
            getView().showProductList(productArrayList);

        } catch (SQLException ex) {
            Log.i(TAG_CLASS,"Excepcion sql getProductListNoSync "+ex.getMessage());
            getView().showAlertError(R.string.error, ex.getMessage());

        }
    }

    private void getProductList() {

        try {
            ArrayList<Product> productArrayList = productRepository.getProductList();
            getView().showProductList(productArrayList);

        } catch (RetrofitError retrofitError) {
            RepositoryError repositoryError = MapperError.convertRetrofitErrorToRepositoryError(retrofitError);
            Log.i(TAG_CLASS,"Excepcion sql getProductList ");
            getView().showAlertError(R.string.error, repositoryError.getMessage());

        }/*finally {
            getView().hideProgress();
        }*/
    }



}
