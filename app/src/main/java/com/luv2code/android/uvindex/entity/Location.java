package com.luv2code.android.uvindex.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by lzugaj on 5/31/2019
 */

@Entity(tableName = "location")
public class Location {

    @PrimaryKey
    @ColumnInfo(name = "location_id")
    private Long id;

    @ColumnInfo(name = "city_name")
    private String cityName;

    @ColumnInfo(name = "city_code")
    private String cityCode;

    public Location(Long id, String cityName, String cityCode) {
        this.id = id;
        this.cityName = cityName;
        this.cityCode = cityCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", cityName='" + cityName + '\'' +
                ", cityCode='" + cityCode + '\'' +
                '}';
    }
}
