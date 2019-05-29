package com.luv2code.android.uvindex.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.luv2code.android.uvindex.R;
import com.luv2code.android.uvindex.database.UvIndexDatabase;
import com.luv2code.android.uvindex.entity.User;
import com.luv2code.android.uvindex.utils.DataGenerator;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private static final String LOGGER = "MainActivity";

    @BindView(R.id.etUsername)
    EditText etUsername;

    @BindView(R.id.etPassword)
    EditText etPassword;

    @BindView(R.id.btnLogin)
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        UvIndexDatabase database = UvIndexDatabase.getDatabase(this);
        DataGenerator.with(database).generateUsers();

        Log.i(LOGGER, "\n\n--------------- USERS IN DATABASE ---------------");
        List<User> users = database.userDao().findAll();
        for (User user : users) {
            Log.i(LOGGER, user.toString());
        }
        Log.i(LOGGER, "----------------------------------------------------\n\n");
    }

    @OnClick(R.id.btnLogin)
    public void loginUser() {
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        if (username.isEmpty() && password.isEmpty()) {
            Toast.makeText(this, "Please enter all username and password", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show();
        }
    }
}
