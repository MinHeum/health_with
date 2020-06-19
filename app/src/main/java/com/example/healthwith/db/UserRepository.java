package com.example.healthwith.db;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class UserRepository {
    private UserDao mUserDao;
    private LiveData<User> mUser;

    public UserRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        mUserDao = db.userDao();
        mUser = db.userDao().getUser();
    }

    public LiveData<User> getUser() {return mUser;}
    public void insert(User user) { new insertAsyncTask(mUserDao).execute(user);}

    private static class insertAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDao mUserDao;

        insertAsyncTask(UserDao dao) {
            mUserDao = dao;
        }
        @Override
        protected Void doInBackground(User... users) {
            mUserDao.insertUser(users[0]);
            return null;
        }
    }
}
