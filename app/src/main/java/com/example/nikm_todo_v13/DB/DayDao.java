package com.example.nikm_todo_v13.DB;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DayDao {

    @Query("SELECT id,tv_time, tv_do, tv_value FROM DayData")
    LiveData<List<DayData>> getInfo();

    @Insert
    void insert(DayData dayData);

    @Update
    void update(DayData mainData);

    @Delete
    void delete(DayData mainData);
}
