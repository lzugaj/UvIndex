package com.luv2code.android.uvindex.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.luv2code.android.uvindex.dao.LocationDao;
import com.luv2code.android.uvindex.dao.RoleDao;
import com.luv2code.android.uvindex.dao.UserDao;
import com.luv2code.android.uvindex.dao.UvIndexDao;
import com.luv2code.android.uvindex.entity.Location;
import com.luv2code.android.uvindex.entity.Role;
import com.luv2code.android.uvindex.entity.User;
import com.luv2code.android.uvindex.entity.UvIndex;

import static com.luv2code.android.uvindex.utils.AppConstants.DATABASE_NAME;
import static com.luv2code.android.uvindex.utils.AppConstants.DATABASE_VERSION;

/**
 * Created by lzugaj on 5/29/2019
 */

@Database(entities = {User.class, Role.class, Location.class, UvIndex.class}, version = DATABASE_VERSION, exportSchema = false)
public abstract class UvIndexDatabase extends RoomDatabase {

    public abstract UserDao userDao();

    public abstract RoleDao roleDao();

    public abstract LocationDao locationDao();

    public abstract UvIndexDao uvIndexDao();

    private static volatile UvIndexDatabase INSTANCE;

    public static UvIndexDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (UvIndexDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            UvIndexDatabase.class, DATABASE_NAME)
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }

        return INSTANCE;
    }
}
