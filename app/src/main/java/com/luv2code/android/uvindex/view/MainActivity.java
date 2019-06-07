package com.luv2code.android.uvindex.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.luv2code.android.uvindex.R;
import com.luv2code.android.uvindex.database.UvIndexDatabase;
import com.luv2code.android.uvindex.entity.Role;
import com.luv2code.android.uvindex.entity.User;
import com.luv2code.android.uvindex.utils.DataGenerator;
import com.luv2code.android.uvindex.viewmodel.LoginViewModel;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

import static com.luv2code.android.uvindex.utils.AppConstants.ADMIN;
import static com.luv2code.android.uvindex.utils.AppConstants.KEY_USER;
import static com.luv2code.android.uvindex.utils.AppConstants.RETURN_LOGIN_KEY;

public class MainActivity extends AppCompatActivity {

    private static final String LOGGER = MainActivity.class.getSimpleName();

    @BindView(R.id.etUsername)
    EditText etUsername;

    @BindView(R.id.etPassword)
    EditText etPassword;

    @BindView(R.id.btnLogin)
    Button btnLogin;

    private LoginViewModel loginViewModel;

    private final int reqCodeUserIntent = 57;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        init();
    }

    private void init() {
        UvIndexDatabase database = UvIndexDatabase.getDatabase(this);
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
        if (user.getUsername() != null) {
            Role role = loginViewModel.findingUserRole(user);
            if (role.getCodeName().equals(ADMIN)) {
                // Admin intent
                Toast.makeText(this, "Hello Admin", Toast.LENGTH_SHORT).show();
            } else {
                // User intent
                goToUserActivity(user.getUsername());
            }
        } else {
            Toast.makeText(this, "Wrong username or password. Please try again.", Toast.LENGTH_LONG).show();
        }

        resetEditFields();
    }

    private void resetEditFields() {
        etUsername.setText(null);
        etPassword.setText(null);
    }

    private void goToUserActivity(String username) {
        Intent userIntent = new Intent(MainActivity.this, UserActivity.class);
        userIntent.putExtra(KEY_USER, username);
        startActivityForResult(userIntent, reqCodeUserIntent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == reqCodeUserIntent && resultCode == RESULT_OK) {
            String result = Objects.requireNonNull(data).getStringExtra(RETURN_LOGIN_KEY);
            Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
        }

        init();
    }
}
