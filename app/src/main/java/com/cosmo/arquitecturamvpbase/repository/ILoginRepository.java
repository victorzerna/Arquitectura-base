package com.cosmo.arquitecturamvpbase.repository;

import com.cosmo.arquitecturamvpbase.model.User;

/**
 * Created by victorhugosernasuarez on 17/10/17.
 */

public interface ILoginRepository {

    public User loginUser(User user) throws RepositoryError;
}
