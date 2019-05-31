package com.luv2code.android.uvindex.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by lzugaj on 5/31/2019
 */

@Entity(tableName = "uvindex",
        foreignKeys = @ForeignKey(entity = Location.class, parentColumns = "location_id", childColumns = "id_location"))
public class UvIndex {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "uvIndex_id")
    private Long id;

    @ColumnInfo(name = "uv_index")
    private String uvIndex;

    @ColumnInfo(name = "measurment_date")
    private String measurementDate; // Later change to LocalDate

    @ColumnInfo(name = "id_location", index = true)
    private Long locationId;

    public UvIndex(String uvIndex, String measurementDate, Long locationId) {
        this.uvIndex = uvIndex;
        this.measurementDate = measurementDate;
        this.locationId = locationId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUvIndex() {
        return uvIndex;
    }

    public void setUvIndex(String uvIndex) {
        this.uvIndex = uvIndex;
    }

    public String getMeasurementDate() {
        return measurementDate;
    }

    public void setMeasurementDate(String measurementDate) {
        this.measurementDate = measurementDate;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    @Override
    public String toString() {
        return "UvIndex{" +
                "id=" + id +
                ", uvIndex='" + uvIndex + '\'' +
                ", measurementDate='" + measurementDate + '\'' +
                ", locationId=" + locationId +
                '}';
    }
}
