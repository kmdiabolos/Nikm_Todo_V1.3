package com.example.nikm_todo_v13.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Switch;
import android.widget.TimePicker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.nikm_todo_v13.DB.DayData;
import com.example.nikm_todo_v13.DB.EveryData;
import com.example.nikm_todo_v13.Models.MainViewModel;
import com.example.nikm_todo_v13.R;

public class DoActivity extends AppCompatActivity {
    String do_day, mn, hour;
    String stars = "★★★★☆";
    String temp = "0";
    EditText et_do_day;
    Switch check;
    float numStars;
    Boolean checkAlarm = false;
    RatingBar rating;
    String nowPage;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_do);
        et_do_day = (EditText) findViewById(R.id.et_do_day);
        RatingBar rating = (RatingBar) findViewById(R.id.ratingBar);
        TimePicker tp = (TimePicker) findViewById(R.id.timePicker);
        check = (Switch) findViewById(R.id.switch1);
        hour = String.valueOf(tp.getHour());
        mn = String.valueOf(tp.getMinute());
        numStars = rating.getRating();
        MainViewModel viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        Intent intent = getIntent();
        nowPage = intent.getStringExtra("nowPage");
        check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checkAlarm = true;
                } else
                    checkAlarm = false;
            }
        });
        tp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                hour = String.valueOf(hourOfDay);
                mn = String.valueOf(minute);
            }
        });
        rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                numStars = ratingBar.getRating();

                switch ((int) numStars) {
                    case 5:
                        stars = "★★★★★";
                        break;
                    case 4:
                        stars = "★★★★☆";
                        break;
                    case 3:
                        stars = "★★★☆☆";
                        break;
                    case 2:
                        stars = "★★☆☆☆";
                        break;
                    case 1:
                        stars = "★☆☆☆☆";
                        break;
                    default:
                        stars = "☆☆☆☆☆";
                        break;
                }
            }
        });

        Button btn_signup = (Button) findViewById(R.id.btn_do_signup);
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                do_day = et_do_day.getText().toString();
                if (hour.length() == 1) {
                    hour = temp.concat(hour);
                }
                if (mn.length() == 1) {
                    mn = temp.concat(mn);
                }
                switch(nowPage){
                    case "day":
                        viewModel.insertDay(new DayData(hour+":"+mn,do_day,stars));
                        break;
                    case "every":
                        viewModel.insertEvery(new EveryData(hour+":"+mn,do_day,stars));
                        break;
                    case"month":
                        break;
                }
                finish();
            }
        });
        Button btn_cancel = (Button) findViewById(R.id.btn_do_cancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
