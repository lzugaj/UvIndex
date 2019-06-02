package com.luv2code.android.uvindex.repository;

import android.app.Application;
import android.util.Log;

import com.luv2code.android.uvindex.dao.LocationDao;
import com.luv2code.android.uvindex.database.UvIndexDatabase;
import com.luv2code.android.uvindex.entity.Location;

import java.util.List;

/**
 * Created by lzugaj on 5/31/2019
 */

public class LocationRepository implements LocationDao {

    private static final String LOGGER = LocationRepository.class.getSimpleName();

    private LocationDao locationDao;

    public LocationRepository(Application application) {
        UvIndexDatabase uvIndexDatabase = UvIndexDatabase.getDatabase(application);
        this.locationDao = uvIndexDatabase.locationDao();
    }

    @Override
    public void insert(Location location) {
        locationDao.insert(location);
        Log.i(LOGGER, "Inserting new location in database.");
    }

    @Override
    public List<Location> findAll() {
        List<Location> locations = locationDao.findAll();
        Log.i(LOGGER, "Finding all locations in database.");
        return locations;
    }

    @Override
    public Location findLocationByUvIndexId(Long uvIndexLocationId) {
        Location location = locationDao.findLocationByUvIndexId(uvIndexLocationId);
        Log.i(LOGGER, "Finding location with id: " + uvIndexLocationId);
        return location;
    }

    @Override
    public List<String> findAllDistinctLocations() {
        List<String> locations = locationDao.findAllDistinctLocations();
        Log.i(LOGGER, "Finding all distinct locations");
        return locations;
    }
}
