package com.example.nikm_todo_v13.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nikm_todo_v13.DB.DayData;
import com.example.nikm_todo_v13.MainAdapter;
import com.example.nikm_todo_v13.MainData;
import com.example.nikm_todo_v13.Models.MainViewModel;
import com.example.nikm_todo_v13.R;

import java.util.ArrayList;

public class DayDoFragment extends Fragment {
    MainAdapter mainAdapter;
    View view;
    Button btn_add;
    RecyclerView recyclerView;
    MainViewModel mainViewModel;
    ArrayList<MainData> arrayList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_daydo, container, false);
        btn_add = view.findViewById(R.id.btn_add);
        recyclerView = view.findViewById(R.id.rv_day);
        recyclerView.setLayoutManager(new LinearLayoutManager(DayDoFragment.this.getContext()));
        recyclerView.setAdapter(mainAdapter);
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                mainViewModel.deleteDay(mainAdapter.getArrayAt(viewHolder.getAdapterPosition()));
           //     mainViewModel.insertFinish(mainAdapter.getArrayAt(viewHolder.getAdapterPosition()));
                System.out.println("delete");
            }
        }).attachToRecyclerView(recyclerView);
        return view;
    }

    public void showItemList(ArrayList<DayData> data) {
        mainAdapter = new MainAdapter(data);
        mainAdapter.notifyDataSetChanged();
    }
}
