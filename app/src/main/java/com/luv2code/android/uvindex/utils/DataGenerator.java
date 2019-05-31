package com.luv2code.android.uvindex.utils;

import com.luv2code.android.uvindex.database.UvIndexDatabase;
import com.luv2code.android.uvindex.entity.User;

/**
 * Created by lzugaj on 5/29/2019
 */

@SuppressWarnings("unchecked")
public class DataGenerator {

    private static DataGenerator instance;

    private static UvIndexDatabase database;

    public static DataGenerator with(UvIndexDatabase appDatabase) {
        if (database == null) {
            database = appDatabase;
        }

        if (instance == null) {
            instance = new DataGenerator();
        }

        return instance;
    }

    public void generateUsers() {
        if (database == null) {
            return;
        }

        User user1 = new User("lzugaj", "luka123", 1);
        User user2 = new User("dbukvic", "dbukvic12", 1);
        User user3 = new User("admin", "admin", 0);

        database.clearAllTables();

        database.userDao().insert(user1);
        database.userDao().insert(user2);
        database.userDao().insert(user3);
    }
}
