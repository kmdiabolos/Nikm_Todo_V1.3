package com.example.nikm_todo_v13.Models;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.example.nikm_todo_v13.DB.DayDao;
import com.example.nikm_todo_v13.DB.DayData;
import com.example.nikm_todo_v13.DB.DayDatabase;
import com.example.nikm_todo_v13.DB.EveryDao;
import com.example.nikm_todo_v13.DB.EveryData;

import java.util.List;

public class EveryViewModel extends AndroidViewModel {

    DayDatabase db;
    public EveryViewModel(@NonNull Application application) {
        super(application);

        db = Room.databaseBuilder(application, DayDatabase.class, "todo-dbs").build();
    }


    public LiveData<List<EveryData>> getEveryInfo(){
        return db.everyDao().getEveryInfo();
    }

    public void insertEvery(EveryData everyData) {
        new EveryViewModel.InsertAsyncTaskEvery(db.everyDao()).execute(everyData);
    }
    private static class InsertAsyncTaskEvery extends AsyncTask<EveryData, Void, Void> {
        private EveryDao mEveryDao;

        public InsertAsyncTaskEvery(EveryDao todoDao) {
            this.mEveryDao = todoDao;
        }

        @Override
        protected Void doInBackground(EveryData... everyData) {
            mEveryDao.insert(everyData[0]);
            return null;
        }
    }
    public void deleteEvery(EveryData everyData){ new EveryViewModel.DeleteAsyncTaskEvery(db.everyDao()).execute(everyData);}
    private  static class DeleteAsyncTaskEvery extends AsyncTask<EveryData,Void,Void>{
        private EveryDao mDayDao;

        public DeleteAsyncTaskEvery(EveryDao everyDao){this.mDayDao = everyDao;}

        @Override
        protected Void doInBackground(EveryData... everyData){
            mDayDao.delete(everyData[everyData.length-1]);
            return null;
        }
    }
}
