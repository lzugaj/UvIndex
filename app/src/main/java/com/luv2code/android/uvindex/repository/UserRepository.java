package com.luv2code.android.uvindex.repository;

import android.app.Application;
import android.util.Log;

import com.luv2code.android.uvindex.dao.UserDao;
import com.luv2code.android.uvindex.database.UvIndexDatabase;
import com.luv2code.android.uvindex.entity.User;

import java.util.List;

/**
 * Created by lzugaj on 5/29/2019
 */

public class UserRepository implements UserDao {

    private static final String LOGGER = UserRepository.class.getSimpleName();

    private UserDao userDao;

    public UserRepository(Application application) {
        UvIndexDatabase uvIndexDatabase = UvIndexDatabase.getDatabase(application);
        this.userDao = uvIndexDatabase.userDao();
    }

    @Override
    public final void insert(User users) {
        userDao.insert(users);
        Log.i(LOGGER, "Inserting new user in database.");
    }

    @Override
    public List<User> findAll() {
        List<User> users = userDao.findAll();
        Log.i(LOGGER, "Finding all users in database.");
        return users;
    }
}
