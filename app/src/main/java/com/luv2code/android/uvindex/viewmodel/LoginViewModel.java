package com.luv2code.android.uvindex.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.util.Log;

import com.luv2code.android.uvindex.entity.User;
import com.luv2code.android.uvindex.repository.UserRepository;

import java.util.List;

/**
 * Created by lzugaj on 5/31/2019
 */

public class LoginViewModel extends AndroidViewModel {

    private static final String LOGGER = UserRepository.class.getSimpleName();

    private UserRepository userRepository;

    public LoginViewModel(Application application) {
        super(application);
        this.userRepository = new UserRepository(application);
    }

    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        Log.i(LOGGER, "Successfully found all users in database.");
        return users;
    }

    public User findUser(String username, String password) {
        List<User> users = getAllUsers();
        User searchedUser = null;
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username) && user.getPassword().equalsIgnoreCase(password)) {
                searchedUser = user;
            }
        }

        return searchedUser;
    }

    public int userRole(User user) {
        return user.getRoleId();
    }
}
