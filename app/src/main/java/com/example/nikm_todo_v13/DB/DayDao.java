package com.example.nikm_todo_v13.DB;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.nikm_todo_v13.MainData;

import java.util.List;

@Dao
public interface DayDao {

    @Query("SELECT id,tv_time,tv_do,tv_value FROM DayData")
    LiveData<List<DayData>> getInfo();

    @Query("SELECT id,tv_time, tv_do, tv_value FROM EveryData")
    LiveData<List<DayData>> getEveryToDayInfo();

    @Query("SELECT id,tv_time, tv_do, tv_value FROM EveryData")
    LiveData<List<EveryData>> getEveryInfo();



    @Insert
    void insert(DayData dayData);

    @Insert
    void insertEvery(EveryData everyData);

    @Delete
    void delete(DayData mainData);

    @Delete
    void deleteEvery(EveryData mainData);
}
