package com.luv2code.android.uvindex.repository.impl;

import android.app.Application;
import android.util.Log;

import com.luv2code.android.uvindex.dao.UserDao;
import com.luv2code.android.uvindex.database.UvIndexDatabase;
import com.luv2code.android.uvindex.exception.EntityNotFoundException;
import com.luv2code.android.uvindex.model.User;
import com.luv2code.android.uvindex.repository.UserRepository;

/**
 * Created by lzugaj on 5/27/2019
 */

public class UserRepositoryImpl implements UserRepository {

    private static final String TAG = "UserRepositoryImpl";

    private UserDao userDao;

    public UserRepositoryImpl(UserDao userDao, Application application) {
        UvIndexDatabase db = UvIndexDatabase.getDatabase(application);
        userDao = db.userDao();
        this.userDao = userDao;
    }

    @Override
    public User findOne(String username, String password) {
        User searchedUser = userDao.findUser(username, password);
        if (searchedUser == null) {
            throw new EntityNotFoundException("User", "username", username);
        }

        Log.d(TAG, "Finding user with username: " + username);
        return searchedUser;
    }
}
