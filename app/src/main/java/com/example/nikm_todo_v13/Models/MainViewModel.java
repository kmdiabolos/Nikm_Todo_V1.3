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
import com.example.nikm_todo_v13.DB.EveryData;
import com.example.nikm_todo_v13.MainData;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    DayDatabase db;
    public MainViewModel(@NonNull Application application) {
        super(application);

        db = Room.databaseBuilder(application, DayDatabase.class, "todo-dbs").build();
    }

    public LiveData<List<DayData>> getInfo() {
        return db.dayDao().getInfo();
    }

    public LiveData<List<DayData>> getEveryToDayInfo(){
        return db.dayDao().getEveryToDayInfo();
    }

    public LiveData<List<EveryData>> getEveryInfo(){
        return db.dayDao().getEveryInfo();
    }

    public void insertEvery(EveryData everyData) {
        new MainViewModel.InsertAsyncTaskEvery(db.dayDao()).execute(everyData);
    }
    private static class InsertAsyncTaskEvery extends AsyncTask<EveryData, Void, Void> {
        private DayDao mEveryDao;

        public InsertAsyncTaskEvery(DayDao todoDao) {
            this.mEveryDao = todoDao;
        }

        @Override
        protected Void doInBackground(EveryData... everyData) {
            mEveryDao.insertEvery(everyData[0]);

            return null;
        }
    }
    public void insertDay(DayData dayData) {
        new InsertAsyncTaskDay(db.dayDao()).execute(dayData);
    }
    private static class InsertAsyncTaskDay extends AsyncTask<DayData, Void, Void> {
        private DayDao mDayDao;

        public InsertAsyncTaskDay(DayDao todoDao) {
            this.mDayDao = todoDao;
        }

        @Override
        protected Void doInBackground(DayData... dayData) {
            mDayDao.insert(dayData[0]);
            return null;
        }
    }/*

    public void insertFinish(MainData dayData){
        new InsertAsyncTaskFinish(db.dayDao()).execute(dayData);
    }
    private static class InsertAsyncTaskFinish extends AsyncTask<MainData,Void,Void>{
        private DayDao mFInishDao;
        public InsertAsyncTaskFinish(DayDao todoDao){this.mFInishDao = todoDao; }

        @Override
        protected Void doInBackground(MainData... dayData) {
            mFInishDao.insert(dayData[0]);
            return null;
        }
    }
*/





    public void deleteEvery(EveryData everyData){ new MainViewModel.DeleteAsyncTaskEvery(db.dayDao()).execute(everyData);}
    private  static class DeleteAsyncTaskEvery extends AsyncTask<EveryData,Void,Void>{
        private DayDao mDayDao;

        public DeleteAsyncTaskEvery(DayDao everyDao){this.mDayDao = everyDao;}

        @Override
        protected Void doInBackground(EveryData... everyData){
            mDayDao.deleteEvery(everyData[everyData.length-1]);
            return null;
        }
    }
    //insert부분







    public void deleteDay(DayData dayData){ new DeleteAsyncTask(db.dayDao()).execute(dayData);}
    private  static class DeleteAsyncTask extends AsyncTask<DayData,Void,Void>{
        private DayDao mDayDao;

        public DeleteAsyncTask(DayDao dayDao){this.mDayDao = dayDao;}

        @Override
        protected Void doInBackground(DayData... dayData){
            mDayDao.delete(dayData[dayData.length-1]);
            return null;
        }
    }
}
