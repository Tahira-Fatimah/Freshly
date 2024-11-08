package com.assignment.freshly.asynTask.Customer;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.assignment.freshly.DatabaseConfig.DatabaseClient;
import com.assignment.freshly.dao.CustomerDao;
import com.assignment.freshly.entity.Customer;

public class GetCustomer extends AsyncTask<String, Void, Customer> {
    private Context context;
    private OnLoginResultListener listener;

    public GetCustomer(Context context, OnLoginResultListener listener){
        this.context = context;
        this.listener = listener;
    }

    @Override
    protected Customer doInBackground(String... strings) {
        CustomerDao customerDao = DatabaseClient.getInstance(context).freshlyDatabase.customerDao();
        return customerDao.getCustomerByEmailAndPassword(strings[0], strings[1]);
    }

    @Override
    protected void onPostExecute(Customer customer) {
        if (customer != null) {
            listener.onLoginSuccess(customer.getC_id());
        } else {
            Toast.makeText(context, "Invalid email or password", Toast.LENGTH_SHORT).show();
            listener.onLoginFailed();
        }
    }

    public interface OnLoginResultListener {
        void onLoginSuccess(int customerId);
        void onLoginFailed();
    }

}
