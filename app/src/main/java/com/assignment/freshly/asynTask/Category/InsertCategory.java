package com.assignment.freshly.asynTask.Category;

import android.content.Context;
import android.os.AsyncTask;

import com.assignment.freshly.DatabaseConfig.DatabaseClient;
import com.assignment.freshly.entity.Category;

public class InsertCategory extends AsyncTask<Category, Void, Long> {
    private Context context;

    public InsertCategory(Context context){
        this.context = context;
    }


    @Override
    protected Long doInBackground(Category... categories) {
        return DatabaseClient.getInstance(context).freshlyDatabase.categoryDao().insertCategory(categories[0]);
    }
}
