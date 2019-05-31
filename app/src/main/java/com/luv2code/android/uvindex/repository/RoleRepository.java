package com.luv2code.android.uvindex.repository;

import android.app.Application;
import android.util.Log;

import com.luv2code.android.uvindex.dao.RoleDao;
import com.luv2code.android.uvindex.database.UvIndexDatabase;
import com.luv2code.android.uvindex.entity.Role;

import java.util.List;

/**
 * Created by lzugaj on 5/31/2019
 */

public class RoleRepository implements RoleDao {

    private static final String LOGGER = RoleRepository.class.getSimpleName();

    private RoleDao roleDao;

    public RoleRepository(Application application) {
        UvIndexDatabase uvIndexDatabase = UvIndexDatabase.getDatabase(application);
        this.roleDao = uvIndexDatabase.roleDao();
    }

    @Override
    public void insert(Role role) {
        roleDao.insert(role);
        Log.i(LOGGER, "Inserting new role in database.");
    }

    @Override
    public List<Role> findAll() {
        List<Role> roles = roleDao.findAll();
        Log.i(LOGGER, "Finding all roles in database.");
        return roles;
    }

    @Override
    public Role findRoleByUserId(Long userRoleId) {
        Role role = roleDao.findRoleByUserId(userRoleId);
        Log.i(LOGGER, "Finding role with id: " + userRoleId);
        return role;
    }
}
