package com.example.nikm_todo_v13.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.nikm_todo_v13.DB.DayData;
import com.example.nikm_todo_v13.DB.EveryData;
import com.example.nikm_todo_v13.Fragments.DayDoFragment;
import com.example.nikm_todo_v13.Fragments.EveryDoFragment;
import com.example.nikm_todo_v13.Fragments.FinishDoFragment;
import com.example.nikm_todo_v13.MainData;
import com.example.nikm_todo_v13.Models.MainViewModel;
import com.example.nikm_todo_v13.R;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_add, btn_nav_open, btn_nav_d, btn_nav_m, btn_nav_e, btn_nav_f;
    DrawerLayout drawerLayout;
    View drawerView;

    String nowPage = "day";
    MainViewModel viewModel;

    DayDoFragment dayDo;
    EveryDoFragment everyDo;
    FinishDoFragment finishDo;
    ArrayList<DayData> fakeDay;
    ArrayList<EveryData> fakeEvery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn_add = findViewById(R.id.btn_add);
        btn_nav_open = findViewById(R.id.btn_nav_open);
        btn_nav_d = findViewById(R.id.btn_nav_d);
        btn_nav_e = findViewById(R.id.btn_nav_e);
        btn_nav_m = findViewById(R.id.btn_nav_m);
        btn_nav_f = findViewById(R.id.btn_nav_f);
        drawerLayout = (DrawerLayout) findViewById(R.id.layout_main);
        drawerView = (View) findViewById(R.id.layout_drawer);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        btn_add.setOnClickListener(this);
        btn_nav_open.setOnClickListener(this);
        btn_nav_d.setOnClickListener(this);
        btn_nav_e.setOnClickListener(this);
        btn_nav_m.setOnClickListener(this);
        btn_nav_f.setOnClickListener(this);


    }

    @Override
    protected void onResume() {
        ArrayList<DayData> dayList = new ArrayList<>();
        ArrayList<EveryData> everyList = new ArrayList<>();
        super.onResume();
        viewModel.getEveryToDayInfo().observe(this, some -> {
            dayList.addAll(some);
            changedFragments(nowPage, dayList, everyList);
        });
        viewModel.getInfo().observe(this, info -> {
            dayList.addAll(info);
            changedFragments(nowPage, dayList, everyList);
        });
        viewModel.getEveryInfo().observe(this, info -> {
            everyList.addAll(info);
            changedFragments(nowPage, dayList, everyList);
        });

    }

    private void changedFragments(String fragmentName, ArrayList<DayData> dayList, ArrayList<EveryData> everyList) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        switch (fragmentName) {
            case "day":
                dayDo = new DayDoFragment();
                transaction.replace(R.id.frame_container, dayDo);
                dayDo.showItemList(dayList);
                transaction.commit();
                break;
            case "every":
                everyDo = new EveryDoFragment();
                transaction.replace(R.id.frame_container, everyDo);
                everyDo.showItemList(everyList);
                transaction.commit();
                break;
            case "finish":
                finishDo = new FinishDoFragment();
                transaction.replace(R.id.frame_container, finishDo);
                finishDo.showItemList(everyList);
                transaction.commit();
        }
    }

    @Override
    public void onClick(View v) {
        ArrayList<DayData> dayList;
        ArrayList<EveryData> everyList;

        dayList = checkedOnChange();
        everyList = checkedOnEveryChange();

        switch (v.getId()) {
            case R.id.btn_add:
                switch (nowPage) {
                    case "day":
                    case "every":
                        Intent intent = new Intent(this, DoActivity.class);
                        intent.putExtra("nowPage", nowPage);
                        startActivity(intent);
                        break;
                    case "month":
                        break;
                }
                break;
            case R.id.btn_nav_open:
                drawerLayout.openDrawer(drawerView);
                break;

            case R.id.btn_nav_d:
                changedFragments("day", dayList, everyList);
                nowPage = "day";
                drawerLayout.closeDrawer(drawerView);
                break;
            case R.id.btn_nav_e:
                changedFragments("every", dayList, everyList);
                nowPage = "every";
                drawerLayout.closeDrawer(drawerView);
                break;
            case R.id.btn_nav_f:
                changedFragments("finish", fakeDay, fakeEvery);
                nowPage = "finish";
                drawerLayout.closeDrawer(drawerView);
        }
    }

    private ArrayList<DayData> checkedOnChange() {
        ArrayList<DayData> dayList = new ArrayList<>();

        viewModel.getEveryToDayInfo().observe(this, some -> {

            dayList.addAll(some);
        });
        viewModel.getInfo().observe(this, info -> {

            dayList.addAll(info);
        });
        return dayList;
    }

    private ArrayList<EveryData> checkedOnEveryChange() {
        ArrayList<EveryData> everyList = new ArrayList<>();
        viewModel.getEveryInfo().observe(this, info -> {
            everyList.addAll(info);
        });
        return everyList;
    }
}
