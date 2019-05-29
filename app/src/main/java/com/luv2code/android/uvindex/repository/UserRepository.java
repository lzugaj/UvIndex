package com.luv2code.android.uvindex.repository;

import android.util.Log;

import com.luv2code.android.uvindex.dao.UserDao;
import com.luv2code.android.uvindex.entity.User;

import java.util.List;

/**
 * Created by lzugaj on 5/29/2019
 */

public class UserRepository implements UserDao {

    private static final String LOGGER = "UserRepository";

    private static UserRepository instance;

    private UserDao userDao;

    public UserRepository(UserDao userDao) {
        this.userDao = userDao;
    }

    public static UserRepository getInstance(UserDao userDao) {
        if (instance == null) {
            instance = new UserRepository(userDao);
        }

        return instance;
    }

    @Override
    public List<User> findAll() {
        List<User> users = userDao.findAll();
        Log.i(LOGGER, "Finding all users in database.");
        return users;
    }

    @Override
    public final void insert(User users) {
        userDao.insert(users);
        Log.i(LOGGER, "Inserting new users in database.");
    }
}
