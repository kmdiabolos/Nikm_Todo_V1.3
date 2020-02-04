package com.example.nikm_todo_v13.DB;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface EveryDao {

    @Query("SELECT id,tv_time, tv_do, tv_value FROM EveryData")
    LiveData<List<EveryData>> getEveryInfo();

    @Query("SELECT id,tv_time, tv_do, tv_value FROM EveryData")
    LiveData<List<DayData>> getEveryToDayInfo();
    @Insert
    void insert(EveryData everyData);

    @Update
    void update(EveryData everyData);

    @Delete
    void delete(EveryData everyData);
}
