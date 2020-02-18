package com.example.nikm_todo_v13.DB;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.nikm_todo_v13.MainData;

@Database(entities = {DayData.class, EveryData.class, MainData.class}, version =1  ,exportSchema = false)
public abstract class DayDatabase extends RoomDatabase {
    abstract public DayDao dayDao();
}