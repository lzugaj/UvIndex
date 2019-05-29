package com.luv2code.android.uvindex.repository;

import com.luv2code.android.uvindex.model.User;

/**
 * Created by lzugaj on 5/27/2019
 */

public interface UserRepository {

    User findOne(String username, String password);

}
