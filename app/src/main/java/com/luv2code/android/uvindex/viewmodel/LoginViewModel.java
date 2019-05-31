package com.luv2code.android.uvindex.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.util.Log;

import com.luv2code.android.uvindex.entity.Role;
import com.luv2code.android.uvindex.entity.User;
import com.luv2code.android.uvindex.exception.EntityNotFoundException;
import com.luv2code.android.uvindex.repository.RoleRepository;
import com.luv2code.android.uvindex.repository.UserRepository;

import java.util.List;
import java.util.Objects;

/**
 * Created by lzugaj on 5/31/2019
 */

public class LoginViewModel extends AndroidViewModel {

    private static final String LOGGER = LoginViewModel.class.getSimpleName();

    private UserRepository userRepository;

    private RoleRepository roleRepository;

    public LoginViewModel(Application application) {
        super(application);
        this.userRepository = new UserRepository(application);
        this.roleRepository = new RoleRepository(application);
    }

    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        Log.i(LOGGER, "Successfully found all users in database.");
        return users;
    }

    public List<Role> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        Log.i(LOGGER, "Successfully found all roles in database.");
        return roles;
    }

    public User findUser(String username, String password) {
        List<User> users = getAllUsers();
        User searchedUser = null;
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                searchedUser = user;
            }
        }

        if (searchedUser == null) {
            searchedUser = new User();
        }

        Log.i(LOGGER, "Successfully found user with username: " + Objects.requireNonNull(searchedUser).getUsername());
        return searchedUser;
    }

    public Role findingUserRole(User user) {
        Role role = roleRepository.findRoleByUserId(user.getRoleId());
        Log.i(LOGGER, "Successfully found user role with code name: " + role.getCodeName());
        return role;
    }
}
