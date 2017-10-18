package com.cosmo.arquitecturamvpbase.repository;

import com.cosmo.arquitecturamvpbase.helper.ServicesFactory;
import com.cosmo.arquitecturamvpbase.model.DeleteResponse;
import com.cosmo.arquitecturamvpbase.model.Product;
import com.cosmo.arquitecturamvpbase.model.User;
import com.cosmo.arquitecturamvpbase.services.IServices;

import java.util.ArrayList;

import retrofit.RetrofitError;

/**
 * Created by victorhugosernasuarez on 17/10/17.
 */

public class LoginRepository implements ILoginRepository{

    private IServices services;

    public LoginRepository() {
        ServicesFactory servicesFactory = new ServicesFactory();
        services = (IServices) servicesFactory.getInstance(IServices.class);
    }

    @Override
    public User loginUser(User user) throws RepositoryError{

        try {
            User userLogin = services.getUserLogin(user.getEmail(), user.getPassword());
            return userLogin;
        }catch (RetrofitError retrofitError){
            throw MapperError.convertRetrofitErrorToRepositoryError(retrofitError);
        }
    }

}
