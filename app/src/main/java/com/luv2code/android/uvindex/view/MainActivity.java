package com.luv2code.android.uvindex.view;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.luv2code.android.uvindex.R;
import com.luv2code.android.uvindex.database.UvIndexDatabase;
import com.luv2code.android.uvindex.entity.User;
import com.luv2code.android.uvindex.utils.DataGenerator;
import com.luv2code.android.uvindex.viewmodel.LoginViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class MainActivity extends AppCompatActivity {

    private static final String LOGGER = MainActivity.class.getSimpleName();

    @BindView(R.id.etUsername)
    EditText etUsername;

    @BindView(R.id.etPassword)
    EditText etPassword;

    @BindView(R.id.btnLogin)
    Button btnLogin;

    private UvIndexDatabase database;

    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        init();

        Log.i(LOGGER, "\n\n--------------- USERS IN DATABASE ---------------");
        List<User> users = loginViewModel.getAllUsers();
        for (User user : users) {
            Log.i(LOGGER, user.toString());
        }
        Log.i(LOGGER, "----------------------------------------------------\n\n");
    }

    private void init() {
        database = UvIndexDatabase.getDatabase(this);
        DataGenerator.with(database).generateUsers();
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
    }

    @OnTextChanged({ R.id.etUsername, R.id.etPassword })
    public void onTextChanged() {
        btnLogin.setEnabled(!TextUtils.isEmpty(etUsername.getText().toString().trim()) &&
                !TextUtils.isEmpty(etPassword.getText().toString().trim()));
    }

    @OnClick(R.id.btnLogin)
    public void loginUser() {
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        User user = loginViewModel.findUser(username, password);
        if (user != null) {
            int roleNumber = loginViewModel.userRole(user);
            if (roleNumber == 0) {
                // Admin intent
                Toast.makeText(this, "Hello Admin", Toast.LENGTH_SHORT).show();
            } else {
                // User intent
                Toast.makeText(this, "Hello User", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Wrong username or password. Please try again.", Toast.LENGTH_LONG).show();
        }
    }
}
