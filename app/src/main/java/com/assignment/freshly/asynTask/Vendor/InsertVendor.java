package com.assignment.freshly.asynTask.Vendor;

import android.content.Context;
import android.os.AsyncTask;

import com.assignment.freshly.DatabaseConfig.DatabaseClient;
import com.assignment.freshly.dao.VendorDao;
import com.assignment.freshly.entity.Vendor;

public class InsertVendor extends AsyncTask<Vendor, Void, Long> {
    private Context context;

    public InsertVendor(Context context){
        this.context = context;
    }


    @Override
    protected Long doInBackground(Vendor... vendors) {
        VendorDao vendorDao = DatabaseClient.getInstance(context).freshlyDatabase.vendorDao();
        return vendorDao.insertVendor(vendors[0]);
    }
}
