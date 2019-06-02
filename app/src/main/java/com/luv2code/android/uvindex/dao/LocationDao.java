package com.luv2code.android.uvindex.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.luv2code.android.uvindex.entity.Location;

import java.util.List;

/**
 * Created by lzugaj on 5/31/2019
 */

@Dao
public interface LocationDao {

    @Insert
    void insert(Location location);

    @Query("select * from location")
    List<Location> findAll();

    @Query("select * from location where location_id = :uvIndexLocationId")
    Location findLocationByUvIndexId(Long uvIndexLocationId);

    @Query("select distinct city_name from location")
    List<String> findAllDistinctLocations();

    @Query("select * from location where city_name = :cityName")
    Location findLocationsByCityName(String cityName);

}
