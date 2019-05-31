package com.luv2code.android.uvindex.repository;

import android.app.Application;
import android.util.Log;

import com.luv2code.android.uvindex.dao.UvIndexDao;
import com.luv2code.android.uvindex.database.UvIndexDatabase;
import com.luv2code.android.uvindex.entity.UvIndex;

import java.util.List;

/**
 * Created by lzugaj on 5/31/2019
 */

public class UvIndexRepository implements UvIndexDao {

    private static final String LOGGER = UvIndexRepository.class.getSimpleName();

    private UvIndexDao uvIndexDao;

    public UvIndexRepository(Application application) {
        UvIndexDatabase uvIndexDatabase = UvIndexDatabase.getDatabase(application);
        this.uvIndexDao = uvIndexDatabase.uvIndexDao();
    }

    @Override
    public void insert(UvIndex uvIndex) {
        uvIndexDao.insert(uvIndex);
        Log.i(LOGGER, "Inserting new uvIndex in database.");
    }

    @Override
    public List<UvIndex> findAll() {
        List<UvIndex> uvIndexes = uvIndexDao.findAll();
        Log.i(LOGGER, "Finding all uvIndexes in database.");
        return uvIndexes;
    }

    @Override
    public UvIndex findUvIndex(Long uvIndexId) {
        UvIndex uvIndex = uvIndexDao.findUvIndex(uvIndexId);
        Log.i(LOGGER, "Finding uvIndexe with id: " + uvIndexId);
        return uvIndex;
    }
}
