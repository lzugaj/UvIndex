package com.luv2code.android.uvindex.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.util.Log;

import com.luv2code.android.uvindex.entity.Location;
import com.luv2code.android.uvindex.entity.UvIndex;
import com.luv2code.android.uvindex.repository.LocationRepository;
import com.luv2code.android.uvindex.repository.UvIndexRepository;

import java.util.List;

/**
 * Created by lzugaj on 5/31/2019
 */

public class UserViewModel extends AndroidViewModel {

    private static final String LOGGER = UserViewModel.class.getSimpleName();

    private LocationRepository locationRepository;

    private UvIndexRepository uvIndexRepository;

    public UserViewModel(Application application) {
        super(application);
        this.locationRepository = new LocationRepository(application);
        this.uvIndexRepository = new UvIndexRepository(application);
    }

    public List<Location> getAllLocations() {
        List<Location> locations = locationRepository.findAll();
        Log.i(LOGGER, "Successfully found all locations in database.");
        return locations;
    }

    public List<UvIndex> getAllUvIndexes() {
        List<UvIndex> uvIndexes = uvIndexRepository.findAll();
        Log.i(LOGGER, "Successfully found all uvIndexes in database.");
        return uvIndexes;
    }

    public Location findLocation(UvIndex uvIndex) {
        Location location = locationRepository.findLocationByUvIndexId(uvIndex.getLocationId());
        Log.i(LOGGER, "Successfully found uvindex location with city name: " + location.getCityName());
        return location;
    }

    public UvIndex findUvIndex(UvIndex uvIndex) {
        UvIndex searchedUvIndex = uvIndexRepository.findUvIndex(uvIndex.getId());
        Log.i(LOGGER, "Successfully found uvindex with id: " + uvIndex.getUvIndex());
        return searchedUvIndex;
    }

    public List<String> getAllDistinctLocations() {
        List<String> locations = locationRepository.findAllDistinctLocations();
        Log.i(LOGGER, "Successfully found all distinct locations in database.");
        return locations;
    }

    public Location findLocationByUvIndexId(String uvIndex) {
        Location location = locationRepository.findLocationsByCityName(uvIndex);
        Log.i(LOGGER, "Successfully found uvindex location with city name: " + location.getCityName());
        return location;
    }

}
