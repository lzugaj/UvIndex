package com.luv2code.android.uvindex.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.luv2code.android.uvindex.entity.UvIndex;

import java.util.List;

/**
 * Created by lzugaj on 5/31/2019
 */

@Dao
public interface UvIndexDao {

    @Insert
    void insert(UvIndex uvIndex);

    @Query("select * from uvindex")
    List<UvIndex> findAll();

    @Query("select * from uvindex where uvIndex_id = :uvIndexId")
    UvIndex findUvIndex(Long uvIndexId);

}
