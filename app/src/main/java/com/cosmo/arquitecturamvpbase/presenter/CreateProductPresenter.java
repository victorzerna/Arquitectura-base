package com.cosmo.arquitecturamvpbase.presenter;

import android.database.SQLException;
import android.nfc.Tag;
import android.util.Log;

import com.cosmo.arquitecturamvpbase.R;
import com.cosmo.arquitecturamvpbase.helper.DataBase;
import com.cosmo.arquitecturamvpbase.model.Product;
import com.cosmo.arquitecturamvpbase.repository.IProductRepository;
import com.cosmo.arquitecturamvpbase.repository.ProductRepository;
import com.cosmo.arquitecturamvpbase.views.activities.ICreateProductView;

import java.util.UUID;

import retrofit.RetrofitError;

/**
 * Created by jersonsuaza on 9/19/17.
 */

public class CreateProductPresenter extends BasePresenter<ICreateProductView> {

    private static final String TAG_CLASS = "CreatePRoductPresenter";
    private IProductRepository productRepository;

    public CreateProductPresenter(IProductRepository productRepository){
        this.productRepository = productRepository;
    }

    /*public void createNewProduct(Product product) {
        if (getValidateInternet().isConnected()){
            createThreadCreateProduct(product);
        }else{
            getView().showAlertInternet(R.string.error, R.string.validate_internet);
        }
    }*/

    public void createNewProduct(String name, String description, String price, String quantity) {
        Log.i(TAG_CLASS,"CreateNewProduct");
        Product product = new Product();
        //Para generar el ID autoincremental
        product.setId(UUID.randomUUID().toString());
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setQuantity(quantity);

        if (getValidateInternet().isConnected()){
            Log.i(TAG_CLASS,"conection internet");
            //product.setSync(true);
            createThreadCreateProductService(product);
        }else{
            Log.i(TAG_CLASS,"connection local");
            product.setSync(0);
            createThreadCreateProductLocal(product);
            //getView().showAlertInternet(R.string.error, R.string.validate_internet);
        }
    }

    public void createThreadCreateProductLocal(final Product product) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                //createNewProductService(product);
                createNewProductLocal(product);
            }


        });
        thread.start();
    }


    public void createThreadCreateProductService(final Product product) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                createNewProductService(product);
                //createNewProductLocal(product);
            }


        });
        thread.start();
    }

    private void createNewProductLocal(Product product) {
        try{
            boolean isSaved = DataBase.productDao.createProduct(product);

            getView().showResultCreateNewProduct(isSaved);
        }catch (SQLException ex){
            getView().showResultCreateNewProduct(false);
        }
    }


    private void createNewProductService(Product product){
        try{
            productRepository.createProduct(product);
            getView().showResultCreateNewProduct(true);
        }catch (RetrofitError retrofitError){
            Log.i(TAG_CLASS," Error creando producto createNewProductService");
            getView().showResultCreateNewProduct(false);
        }
    }

}
