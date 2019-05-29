package com.luv2code.android.uvindex.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.luv2code.android.uvindex.dao.UserDao;
import com.luv2code.android.uvindex.model.User;

/**
 * Created by lzugaj on 5/27/2019
 */

@Database(entities = {User.class}, version = 1)
public abstract class UvIndexDatabase extends RoomDatabase {

    public abstract UserDao userDao();

    private static volatile UvIndexDatabase INSTANCE;

    public static UvIndexDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (UvIndexDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            UvIndexDatabase.class, "uv_index_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
