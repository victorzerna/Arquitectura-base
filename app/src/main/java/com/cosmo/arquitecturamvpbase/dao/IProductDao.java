package com.cosmo.arquitecturamvpbase.dao;

import com.cosmo.arquitecturamvpbase.model.Product;

import java.util.ArrayList;

/**
 * Created by victorhugosernasuarez on 30/9/17.
 */

public interface IProductDao {

    //Obtener los atributos de BD
    public ArrayList<Product> fetchAllProducts();
    public boolean createProduct(Product product);
    public boolean deleteProduct(Product product);
    public boolean updateProduct(Product product);

    public boolean syncProductsLocal();
}
