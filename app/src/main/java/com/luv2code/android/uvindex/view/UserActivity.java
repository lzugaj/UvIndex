package com.luv2code.android.uvindex.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.luv2code.android.uvindex.R;
import com.luv2code.android.uvindex.adapter.UserUvIndexAdapter;
import com.luv2code.android.uvindex.database.UvIndexDatabase;
import com.luv2code.android.uvindex.dialog.ExitDialog;
import com.luv2code.android.uvindex.entity.UvIndex;
import com.luv2code.android.uvindex.utils.DataGenerator;
import com.luv2code.android.uvindex.viewmodel.UserViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.luv2code.android.uvindex.utils.AppConstants.EXIT_DIALOG;
import static com.luv2code.android.uvindex.utils.AppConstants.KEY_USER;
import static com.luv2code.android.uvindex.utils.AppConstants.RETURN_LOGIN_KEY;

public class UserActivity extends AppCompatActivity {

    @BindView(R.id.btnDateFrom)
    Button btnDateFrom;

    @BindView(R.id.btnDateTo)
    Button btnDateTo;

    @BindView(R.id.rvUvIndexes)
    RecyclerView rvUvIndexes;

    private UvIndexDatabase database;

    private List<UvIndex> uvIndexes;

    private UserUvIndexAdapter adapter;

    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        ButterKnife.bind(this);

        userIntentContent();
        init();
        setAdapter();
        setRecycler();
    }

    private void userIntentContent() {
        String username;
        Intent aboutUsIntent = getIntent();
        if (aboutUsIntent.hasExtra(KEY_USER)) {
            username = aboutUsIntent.getStringExtra(KEY_USER);
            Toast.makeText(this, "Hello, " + username, Toast.LENGTH_LONG).show();
        }
    }

    private void init() {
        database = UvIndexDatabase.getDatabase(this);
        DataGenerator.with(database).generateUvIndexes();
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
    }

    private void setAdapter() {
        uvIndexes = new ArrayList<>();
        uvIndexes.addAll(userViewModel.getAllUvIndexes());
        adapter = new UserUvIndexAdapter(uvIndexes, this.userViewModel);
    }

    private void setRecycler() {
        rvUvIndexes.setHasFixedSize(true);
        rvUvIndexes.setLayoutManager(new LinearLayoutManager(this));
        rvUvIndexes.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.app_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_exit) {
            ExitDialog exitDialog = new ExitDialog();
            exitDialog.setCancelable(false);
            exitDialog.show(getSupportFragmentManager(), EXIT_DIALOG);
            return true;
        } else if (id == R.id.action_logout) {
            String message = "Goodbye! :)";
            Intent loginIntent = new Intent();
            loginIntent.putExtra(RETURN_LOGIN_KEY, message);
            setResult(RESULT_OK, loginIntent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setBtnDateText(String value) {
        btnDateFrom.setText(value);
    }
}
