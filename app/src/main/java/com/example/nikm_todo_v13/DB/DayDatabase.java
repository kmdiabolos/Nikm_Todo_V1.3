package com.example.nikm_todo_v13.DB;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {DayData.class, EveryData.class}, version = 2 ,exportSchema = false)
public abstract class DayDatabase extends RoomDatabase {
    abstract public DayDao dayDao();
    abstract public EveryDao everyDao();
}