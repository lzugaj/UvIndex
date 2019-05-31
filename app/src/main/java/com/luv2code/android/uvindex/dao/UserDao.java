package com.luv2code.android.uvindex.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.luv2code.android.uvindex.entity.User;

import java.util.List;

/**
 * Created by lzugaj on 5/27/2019
 */

@SuppressWarnings("ALL")
@Dao
public interface UserDao {

    @Query("select * from user")
    List<User> findAll();

    @Insert
    void insert(User user);

}
