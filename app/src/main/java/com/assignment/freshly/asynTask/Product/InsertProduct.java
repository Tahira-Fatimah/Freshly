package com.assignment.freshly.asynTask.Product;

import android.content.Context;
import android.os.AsyncTask;

import com.assignment.freshly.DatabaseConfig.DatabaseClient;
import com.assignment.freshly.dao.ProductDao;
import com.assignment.freshly.entity.Product;

public class InsertProduct extends AsyncTask<Product, Void, Long> {
    private Context context;

    public InsertProduct(Context context){
        this.context = context;
    }


    @Override
    protected Long doInBackground(Product... products) {
        ProductDao productDao = DatabaseClient.getInstance(context).freshlyDatabase.productDao();
        return  productDao.insert(products[0]);
    }
}
