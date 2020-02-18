package com.example.nikm_todo_v13.DB;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.example.nikm_todo_v13.MainData;



@Entity
public class DayData extends MainData {

    private String day_time;
    private String day_do;
    private String day_value;


    public DayData(String tv_time, String tv_do, String tv_value){
        super(tv_time, tv_do, tv_value);
        this.day_time = tv_time;
        this.day_do =tv_do;
        this.day_value=tv_value;
    }



    public String getDay_time() {
        return day_time;
    }

    public void setDay_time(String day_time) {
        this.day_time = day_time;
    }

    public String getDay_do() {
        return day_do;
    }

    public void setDay_do(String day_do) {
        this.day_do = day_do;
    }

    public String getDay_value() {
        return day_value;
    }

    public void setDay_value(String day_value) {
        this.day_value = day_value;
    }

}
