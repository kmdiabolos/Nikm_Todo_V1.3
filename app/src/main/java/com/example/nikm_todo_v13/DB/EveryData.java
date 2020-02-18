package com.example.nikm_todo_v13.DB;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.nikm_todo_v13.MainData;

@Entity
public class EveryData extends MainData {

    private String every_time;
    private String every_do;
    private String every_value;


    public EveryData(String tv_time, String tv_do, String tv_value) {
        super(tv_time, tv_do, tv_value);
        this.every_time = tv_time;
        this.every_do = tv_do;
        this.every_value = tv_value;
    }


    public String getEvery_time() {
        return every_time;
    }

    public void setEvery_time(String every_time) {
        this.every_time = every_time;
    }

    public String getEvery_do() {
        return every_do;
    }

    public void setEvery_do(String every_do) {
        this.every_do = every_do;
    }

    public String getEvery_value() {
        return every_value;
    }

    public void setEvery_value(String every_value) {
        this.every_value = every_value;
    }
}
