package com.luv2code.android.uvindex.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.luv2code.android.uvindex.model.User;

/**
 * Created by lzugaj on 5/27/2019
 */

@Dao
public interface UserDao {

    @Query("select * from User u where u.username = :username and u.password = :password")
    User findUser(String username, String password);

}
