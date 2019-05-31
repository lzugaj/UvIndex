package com.luv2code.android.uvindex.utils;

import com.luv2code.android.uvindex.database.UvIndexDatabase;
import com.luv2code.android.uvindex.entity.Location;
import com.luv2code.android.uvindex.entity.Role;
import com.luv2code.android.uvindex.entity.User;
import com.luv2code.android.uvindex.entity.UvIndex;

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

        Role adminRole = new Role(1L,"Admin role", "Admin");
        Role userRole = new Role(2L,"User role", "User");

        User user1 = new User("lzugaj", "luka123", userRole.getId());
        User user2 = new User("dbukvic", "dbukvic12", userRole.getId());
        User user3 = new User("admin", "admin", adminRole.getId());

        database.clearAllTables();

        database.roleDao().insert(adminRole);
        database.roleDao().insert(userRole);

        database.userDao().insert(user1);
        database.userDao().insert(user2);
        database.userDao().insert(user3);
    }

    public void generateUvIndexes() {
        if (database == null) {
            return;
        }

        Location location1 = new Location(1L, "Zagreb", "ZG");
        Location location2 = new Location(2L, "Split", "ST");
        Location location3 = new Location(3L, "Zadar", "ZD");
        Location location4 = new Location(4L, "Osijek", "OS");

        UvIndex uvIndex1 = new UvIndex("12FW", "2019-02-12", location1.getId());
        UvIndex uvIndex2 = new UvIndex("KF42", "2019-04-05", location1.getId());
        UvIndex uvIndex3 = new UvIndex("LF42", "2019-05-02", location2.getId());
        UvIndex uvIndex4 = new UvIndex("L21F", "2019-06-25", location1.getId());
        UvIndex uvIndex5 = new UvIndex("AD2D", "2019-07-22", location3.getId());
        UvIndex uvIndex6 = new UvIndex("312F", "2019-08-13", location2.getId());
        UvIndex uvIndex7 = new UvIndex("OKDP", "2019-09-17", location4.getId());

        database.clearAllTables();

        database.locationDao().insert(location1);
        database.locationDao().insert(location2);
        database.locationDao().insert(location3);
        database.locationDao().insert(location4);

        database.uvIndexDao().insert(uvIndex1);
        database.uvIndexDao().insert(uvIndex2);
        database.uvIndexDao().insert(uvIndex3);
        database.uvIndexDao().insert(uvIndex4);
        database.uvIndexDao().insert(uvIndex5);
        database.uvIndexDao().insert(uvIndex6);
        database.uvIndexDao().insert(uvIndex7);
    }
}
