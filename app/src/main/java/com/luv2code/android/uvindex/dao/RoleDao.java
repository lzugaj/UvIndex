package com.luv2code.android.uvindex.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.luv2code.android.uvindex.entity.Role;

import java.util.List;

/**
 * Created by lzugaj on 5/31/2019
 */

@Dao
public interface RoleDao {

    @Query("select * from role")
    List<Role> findAll();

    @Query("select * from role where role_id = :userRoleId")
    Role findRoleByUserId(Long userRoleId);

    @Insert
    void insert(Role role);

}
